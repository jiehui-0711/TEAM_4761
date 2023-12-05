package test;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
//搜尋關鍵字

public class model3 {

    public static void main(String args[]) throws IOException {
        //在此陣列中打入關鍵字多個 搜尋關鍵字所在的檔案及位置
        String search[]={"year","week","month","yrs","wks","day"};
        ArrayList arr = new ArrayList<>();
        int file_len = 2100, file_len1 = 240000;
        for (int f = 0; f < file_len; f++) {
            try (BufferedReader br = new BufferedReader(new FileReader("data/"+f+".txt", StandardCharsets.UTF_8))) {
                String line;int ls=0;
                while ((line = br.readLine()) != null) {
                    boolean bo=false;ls++;
                    for(String i:search){
                        if(line.contains(i)) bo=true;
                    }
                    if(bo) System.out.println(f+" "+ls+"行 -> "+line);
                }
            } catch (IOException e) {
                int w=0;
            }
        }
        for (int f = 0; f < file_len1; f++) {
            try (BufferedReader br = new BufferedReader(new FileReader("data/file"+f+".txt", StandardCharsets.UTF_8))) {
                String line;int ls=0;
                while ((line = br.readLine()) != null) {
                    boolean bo=false;ls++;
                    for(String i:search){
                        if(line.contains(i)) bo=true;
                    }
                    if(bo) System.out.println("file"+f+" "+ls+"行 -> "+line);
                }
            } catch (IOException e) {
                int w=0;
            }
        }
    }
}
