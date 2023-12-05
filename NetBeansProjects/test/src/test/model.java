package test;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
//答案分類

public class model {
    
    public static void main(String args[]) throws IOException {
        String a = "AGE";
        String path[] = {"answer", "answer1", "answer2"};
        ArrayList<String> arr = new ArrayList<>();
        BufferedWriter out = new BufferedWriter(new FileWriter("ans/" + a + ".txt", StandardCharsets.UTF_8));
        for (String p : path) {
            BufferedReader br = new BufferedReader(new FileReader(p + ".txt", StandardCharsets.UTF_8));
            
            String line;
            while ((line = br.readLine()) != null) {
                String n[] = line.split("\t");
                if (n[1].equals(a)) {
                    arr.add(line);
                }
            }
            br.close();
        }
        for (String i : arr) {
            out.write(i + "\n");
        }
        out.close();
    }
    
}

class node {

    int n1, n2;
    String ind;

    node(String ind, int n1, int n2) {
        this.ind = ind;
        this.n1 = n1;
        this.n2 = n2;
    }
}
