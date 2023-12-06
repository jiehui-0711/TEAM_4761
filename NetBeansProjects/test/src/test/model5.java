package test;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
//比對訓練集答案

public class model5 {

    public static void main(String args[]) throws IOException {
        String a="DATE";//種類 
        //我的答案
        BufferedReader br = new BufferedReader(new FileReader("out.txt", StandardCharsets.UTF_8)) ;
        //解答
        BufferedReader br1 = new BufferedReader(new FileReader("test/in1.txt", StandardCharsets.UTF_8)) ;
        String line;
        TreeSet<String > arr,arr1;
        arr=new TreeSet<>();arr1=new TreeSet<>();
        String n[];
        while ((line = br.readLine()) != null) {
            n=line.split("\t");
            if(n.length>1 && n[1].equals(a))arr.add(line);
        }
        double len=0.0;
        while ((line = br1.readLine()) != null) {
            n=line.split("\t");
            if(n[1].equals(a)){
                arr1.add(line);
                len+=1.0;
            }
        }
        int p1=0,p2=0;
        System.out.println("-----不是答案的資料-----");
        for(String i:arr){
            if(!arr1.contains(i)){
                 p1++;
                
                String n1[]=i.split("\t");
                String n2[]=n1[5].split("-");
            //if(n2.length==3 && Integer.parseInt(n2[1])<13 && Integer.parseInt(n2[2])<13) continue;
               System.out.println(i);
                int mid=Integer.parseInt(n1[2])+Integer.parseInt(n1[3]);
                mid/=2;
                String line2;
                int words=0;
                BufferedReader br2 = new BufferedReader(new FileReader("data/"+n1[0]+".txt", StandardCharsets.UTF_8)) ;
                while ((line2 = br2.readLine()) != null) {
                    words+=line2.length()+1;
                    if(words>mid){
                        System.out.println(line2);
                        break;
                    }
                }
                br2.close();
            }
        }
        System.out.println("-----沒處理到的資料-----");
        for(String i:arr1){
            if(!arr.contains(i)) {
                p2++;
                
                String n1[]=i.split("\t");
                 String n2[]=n1[5].split("-");
            //if(n2.length==3 && Integer.parseInt(n2[1])<13 && Integer.parseInt(n2[2])<13) continue;
               System.out.println(i);
                if(n1[0].contains("265")){
                 int w=2;   
                }
                int mid=Integer.parseInt(n1[2])+Integer.parseInt(n1[3]);
                mid/=2;
                String line2;
                int words=0;
                BufferedReader br2 = new BufferedReader(new FileReader("data/"+n1[0].trim()+".txt", StandardCharsets.UTF_8)) ;
                while ((line2 = br2.readLine()) != null) {
                    words+=line2.length()+1;
                    if(words>mid){
                        System.out.println(line2);
                        break;
                    }
                }
                br2.close();
            } 
        }
        
        System.out.println((1-p1/len)*100);
        System.out.println((1-p2/len)*100);
        System.out.println((((1-p1/len)*100)+((1-p2/len)*100))/2.0);
        br.close();br1.close();
    }
}
