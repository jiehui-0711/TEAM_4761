package test;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
//答案位置

public class model1 {

    public static void main(String args[]) throws IOException {
        String a = "STATE";
        ArrayList<String> arr = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("ans/" + a + ".txt", StandardCharsets.UTF_8));
        BufferedWriter out = new BufferedWriter(new FileWriter("loca/" + a + "_1.txt", StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            String n[] = line.split("\t");
            try (BufferedReader br1 = new BufferedReader(new FileReader("data/" + n[0] + ".txt", StandardCharsets.UTF_8))) {
                String line1;
                int words = 0,ls=0;
                while ((line1 = br1.readLine()) != null) {
                    words += line1.length()+1;
                    ls++;
                    if (words > (Integer.parseInt(n[3])+Integer.parseInt(n[2]))/2) {
                        out.write(n[0]+" "+ls+"行 "+n[4] +"-> "+line1+"\n");
                        break;
                    }
                }
                br1.close();
            } catch (IOException e) {
                System.out.print("");
            }
        }
        br.close();
        out.close();
    }

}

