package test;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
//找答案集合

public class model2 {

    public static void main(String args[]) throws IOException {
        String a = "DOCTOR";
        TreeSet<String> set = new TreeSet<>();
        BufferedReader br = new BufferedReader(new FileReader("ans/" + a + ".txt", StandardCharsets.UTF_8));
        BufferedWriter out = new BufferedWriter(new FileWriter("set/set_" + a + ".txt", StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            String n[] = line.split("\t");
           set.add(n[4]);
        }
        for(String i:set ) out.write(i+"\n");
        br.close();
        out.close();
    }

}

