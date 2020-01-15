package com.cbs.dataload;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : TerryZhang
 * @description：mongodb 使用类
 * @date : 2020/1/15
 */
public class MongodbUtil {

    public MongodbUtil(){
        connectMongoDB();
    }

    public static MongoDatabase connectMongoDB(){
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = MongoClients.create();

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            System.out.println("Connect to database successfully");

            return  mongoDatabase;
//            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
//            System.out.println("集合 test 选择成功");
//
//            Document document = new Document("title", "MongoDB").
//                    append("description", "database").
//                    append("likes", 100).
//                    append("by", "Fly");
//            List<Document> documents = new ArrayList<Document>();
//            documents.add(document);
//            collection.insertMany(documents);
//            System.out.println("文档插入成功");

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }

    public static void delDoc(String table,String key,String value) {
        MongoDatabase mongoDatabase = null;
        boolean isBroken = false;
        try {
            mongoDatabase = connectMongoDB();
            //删除符合条件的第一个文档
            MongoCollection<Document> collection = mongoDatabase.getCollection(table);
            System.out.println("集合 "+table+" 选择成功");
            collection.deleteOne(Filters.eq(key, value));
        } catch (Exception e) {
            isBroken = true;
        }
    }

    public static void insertDoc(String table,String key,String value) {
        MongoDatabase mongoDatabase = null;
        boolean isBroken = false;
        try {
            mongoDatabase = connectMongoDB();
            MongoCollection<Document> collection = mongoDatabase.getCollection(table);
            System.out.println("集合 "+table+" 选择成功");

            Document document = new Document(key, value);
            collection.insertOne(document);
        } catch (Exception e) {
            isBroken = true;
        }
    }

    public static void updateDoc(String table,String Key,String beforeValue,String afterValue){
        MongoDatabase mongoDatabase = null;
        boolean isBroken = false;
        try {
            mongoDatabase = connectMongoDB();
            MongoCollection<Document> collection = mongoDatabase.getCollection(table);
            System.out.println("集合 "+table+" 选择成功");

            collection.updateMany(Filters.eq(Key, beforeValue), new Document("$set",new Document(Key,afterValue)));
        } catch (Exception e) {
            isBroken = true;
        }
    }


}
