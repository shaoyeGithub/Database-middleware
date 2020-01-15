package com.cbs.common;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : TerryZhang
 * @description：模拟打电话类
 * @date : 2020/1/7
 */
public class GenerateTel {

    List<Integer> allPrefix = new LinkedList<Integer>();

    public void SetAllPrefix(){
        allPrefix.add(182);
        allPrefix.add(138);
        allPrefix.add(159);
    }

    public List<Integer> getAllPrefix() {
        return allPrefix;
    }

    private static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    private static String[] telFirst="182,138,159".split(",");
    public static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getTel());
        }
    }


}
