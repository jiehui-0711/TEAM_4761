package test;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
//比對測試集答案

public class model51 {

    public static void main(String args[]) throws IOException {
        String a="TIME";//種類
        //我的答案
        BufferedReader br = new BufferedReader(new FileReader("out.txt", StandardCharsets.UTF_8)) ;
        //解答
        BufferedReader br1 = new BufferedReader(new FileReader("test/in2.txt", StandardCharsets.UTF_8)) ;
        String line;
        TreeSet<String > arr,arr1;
        arr=new TreeSet<>();arr1=new TreeSet<>();
        String n[];
        while ((line = br.readLine()) != null) {
            n=line.split("\t");
            if(n[1].equals(a))arr.add(line);
        }
        while ((line = br1.readLine()) != null) {
            n=line.split("\t");
            if(n[2].equals("12") && n[3].equals("20")) continue;
            if(n[1].equals(a))arr1.add(line);
        }
        
        System.out.println("-----不是答案的資料-----");
        for(String i:arr){
            if(!arr1.contains(i)){
                System.out.println(i);
                String n1[]=i.split("\t");
                int mid=Integer.parseInt(n1[2])+Integer.parseInt(n1[3]);
                mid/=2;
                String line2;
                /*BufferedReader br2 = new BufferedReader(new FileReader("data1/t2.tsv", StandardCharsets.UTF_8)) ;
                while ((line2 = br2.readLine()) != null) {
                    String n2[]=line2.split("\t");
                    if(n2[0].equals(n1[0]) && Integer.parseInt(n2[1])+n2[2].length()>mid){
                        System.out.println(n2[2]);
                        break;
                    }
                    
                }
                br2.close();*/
            }
        }
        System.out.println("-----沒處理到的資料-----");
        for(String i:arr1){
            if(!arr.contains(i)) {
                System.out.println(i);
                String n1[]=i.split("\t");
                int mid=Integer.parseInt(n1[2])+Integer.parseInt(n1[3]);
                mid/=2;
                String line2;
                /*BufferedReader br2 = new BufferedReader(new FileReader("data1/t2.tsv", StandardCharsets.UTF_8)) ;
                while ((line2 = br2.readLine()) != null) {
                    String n2[]=line2.split("\t");
                    if(n2[0].equals(n1[0]) && Integer.parseInt(n2[1])+n2[2].length()>mid){
                        System.out.println(n2[2]);
                        break;
                    }
                    
                }
                br2.close();*/
            } 
        }
        br.close();br1.close();
    }
}
