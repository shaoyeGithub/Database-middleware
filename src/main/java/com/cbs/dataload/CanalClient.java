package com.cbs.dataload;

import java.io.*;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.fastjson.JSON;
/**
 * CanalClient  从mysql 生成 日志
 */
public class CanalClient {
    //file
    private static String path = CanalClient.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    static String conf_path = path.substring(0, path.lastIndexOf("/")) + "/application.properties";
    public static String canal_binlog_filename = "h"; //保存文件名
    public static String data_dir = path.substring(0, path.lastIndexOf("/"))+"/data"; //数据保存路径
    public static int sleep = 1000;
    public static String canal_print = "false";

    //mq
    public static String canal_mq = "redis"; // redis/rabbitmq/kafka

    public static void main(String args[]) {
      getBinlog();

    }

    public static void getBinlog(){
        // 创建链接
        //System.out.println(AddressUtils.getHostIp());
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("39.106.194.89",
                        11111), "example", "", "");
        int batchSize = 1000;

        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();

            System.out.println("connect success!\r\n startup...");

//            int totalEmptyCount = 120;
//            while (emptyCount < totalEmptyCount) {
//                MessageRecord message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
//                long batchId = message.getId();
//                int size = message.getEntries().size();
//                if (batchId == -1 || size == 0) {
//                    emptyCount++;
//                    System.out.println("empty count : " + emptyCount);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                    }
//                } else {
//                    emptyCount = 0;
//                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
//                    printEntry(message.getEntries());
//                }
//
//                connector.ack(batchId); // 提交确认
//                // connector.rollback(batchId); // 处理失败, 回滚数据
//            }
//            System.out.println("empty too many times, exit");
            RedisUtil.ConfigRedis();
            while (true){
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    //System.out.println("empty count : " + emptyCount);
                    try {
                        Thread.sleep(sleep); // 等待时间
                    } catch (InterruptedException e) {

                    }
                } else {
                    System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }
        } finally {
            connector.disconnect();
        }
    }

    private static void printEntry(List<Entry> entrys) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(new Date());

        ArrayList<String> dataArray = new ArrayList<String> ();

        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN
                    || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event " +
                        "has an error , data:" + entry.toString(), e);
            }
            EventType eventType = rowChage.getEventType();

            String header_str = "{\"binlog\":\"" + entry.getHeader().getLogfileName()+ ":" + entry.getHeader().getLogfileOffset() + "\"," +
                    "\"db\":\"" + entry.getHeader().getSchemaName() + "\"," +
                    "\"table\":\"" + entry.getHeader().getTableName() + "\",";
            String tableName = entry.getHeader().getTableName();

            for (RowData rowData : rowChage.getRowDatasList()) {
                String row_str = "\"eventType\":\"" + eventType +"\",";
                String before = "\"\"";
                String after = "\"\"";

                if (eventType == EventType.DELETE) {
                    after = printColumn(rowData.getBeforeColumnsList());
//                    redisDelete(tableName,rowData.getBeforeColumnsList());
                    mongodbDelete(tableName,rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    after = printColumn(rowData.getAfterColumnsList());
//                    redisInsert(tableName,rowData.getAfterColumnsList());
                    mongodbInsert(tableName,rowData.getAfterColumnsList());
                } else {
                    System.out.println("-------&gt; before");
                    before = printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------&gt; after");
                    after = printColumn(rowData.getAfterColumnsList());

                    mongdbUpdate(tableName,rowData.getBeforeColumnsList(),rowData.getAfterColumnsList());
//                    redisUpdate(tableName,rowData.getBeforeColumnsList());
//                    redisUpdate(tableName,rowData.getAfterColumnsList());
                }

                String row_data = header_str + row_str + "\"before\":" +before + ",\"after\":" + after + ",\"time\":\"" + timeStr +"\"}";
//                System.out.println(row_data);
                dataArray.add(row_data);
                save_data_logs(row_data);
            }
        }

//        String[] strArr = dataArray.toArray(new String[]{});
//        try {
////            if(canal_mq.equals("rabbitmq")){
////                rabbitmq r = new rabbitmq();
////                r.push_rabbitmq(rabbitmq_conf,strArr);
////                //push_rabbitmq(strArr);
////            }else if(canal_mq.equals("redis")){
////                redis r = new redis();
////                r.push_redis(redis_conf, strArr);
////            }else if(canal_mq.equals("kafka")){
////
////            }
//            if (canal_mq.equals("redis")) {
//                RedisUtil r = new RedisUtil("example");
//                r.push_redis(redis_conf, strArr);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.out.println("push "+ canal_mq +" error!");
//        }
//        dataArray = null;
    }

    private static String printColumn(List<Column> columns) {
        Map<String, String> column_map = new HashMap<String, String>();
        for (Column column : columns) {
            String column_name = column.getName();
            String column_value = column.getValue();
            column_map.put(column_name, column_value);
        }
        return JSON.toJSONString(column_map);
    }

    private static void save_data_logs(String row_data){
        if (canal_print.equals("true")){
            System.out.println(row_data);
        }

        String ts = "yyyyMMdd";
        if (canal_binlog_filename.equals("y")){
            ts = "yyyy";
        }else if (canal_binlog_filename.equals("m")){
            ts = "yyyyMM";
        }else if (canal_binlog_filename.equals("d")){
            ts = "yyyyMMdd";
        }else if (canal_binlog_filename.equals("h")){
            ts = "yyyyMMddHH";
        }else if (canal_binlog_filename.equals("i")){
            ts = "yyyyMMddHHmm";
        }else{

        }
        SimpleDateFormat df2 = new SimpleDateFormat(ts);
        String timeStr2 = df2.format(new Date());
        String filename = data_dir + "/binlog_" + timeStr2 + ".log";

        System.out.println(filename);

        FileWriter writer;
        try {
            writer = new FileWriter(filename, true);
            writer.write(row_data + "\r\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("write file error!");
        }
    }

    private  static void redisInsert( String tableName,List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            RedisUtil redisUtil = new RedisUtil("redis");
            redisUtil.stringSet(tableName+":"+ columns.get(0).getValue(),json.toJSONString());
        }
    }

    private static  void redisUpdate( String tableName,List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            RedisUtil redisUtil = new RedisUtil("redis");
            redisUtil.stringSet(tableName+":"+ columns.get(0).getValue(),json.toJSONString());
        }
    }

    private static  void redisDelete(String tableName, List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            RedisUtil redisUtil = new RedisUtil("redis");
            redisUtil.delKey(tableName+":"+ columns.get(0).getValue());
        }
    }

    private  static void mongodbInsert( String tableName,List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            MongodbUtil mongodbUtil = new MongodbUtil();
            mongodbUtil.insertDoc(tableName,columns.get(0).getValue(),json.toJSONString());
        }
    }

    private static  void mongdbUpdate( String tableName,List<Column> beforeColumns,List<Column> afterColumns){
        JSONObject beforeJson = new JSONObject();
        JSONObject afterJson = new JSONObject();
        for (Column column : beforeColumns) {
            beforeJson.put(column.getName(), column.getValue());
        }
        for (Column column : afterColumns) {
            afterJson.put(column.getName(), column.getValue());
        }

        if(beforeColumns.size()>0){
            MongodbUtil mongodbUtil = new MongodbUtil();
            mongodbUtil.updateDoc(tableName,beforeColumns.get(0).getValue(),beforeJson.toJSONString(),afterJson.toJSONString());
        }
    }

    private static  void mongodbDelete(String tableName, List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            MongodbUtil mongodbUtil = new MongodbUtil();
            mongodbUtil.delDoc(tableName,columns.get(0).getValue(),json.toJSONString());
        }
    }
}



