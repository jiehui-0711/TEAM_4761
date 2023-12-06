package test;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
//排序答案

public class model6 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("out.txt", StandardCharsets.UTF_8)) ;
        TreeSet<String> set=new TreeSet<>();
        String line;
        while ((line = br.readLine()) != null) {
            set.add(line);
        }
        br.close();
        BufferedWriter out = new BufferedWriter(new FileWriter("out.txt", StandardCharsets.UTF_8));
        for(String i:set) out.write(i+"\n");
       out.close();
    }
}
