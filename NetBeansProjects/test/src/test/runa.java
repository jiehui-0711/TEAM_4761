package test;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;

public class runa {

    static HashSet<Integer> intset, engset, bengset, sengset, thofset, spset;
    static HashSet<String> set_city, set_country, set_duration, set_state, set_doc, set_bad_doc;
    static LinkedList<String> set_department, set_hospital;
    static int file_ind, words, ls;
    static String pre_doc, now, today;

    public static void main(String args[]) throws IOException {
        String data="data3";//data訓練集 data2驗證集 data3競賽集
        String inp_path = ".txt";
        String out_path = "out.txt";
        toset();
        pre_doc = " ";
        int datalen = 5400, fdatalen = 300000;
        BufferedWriter out = new BufferedWriter(new FileWriter(out_path, StandardCharsets.UTF_8));
        for (file_ind = 0; file_ind < datalen; file_ind++) {
            now = "";
            today = "";
            words = 0;
            ls = 0;
            inp_path = data+"/" + Integer.toString(file_ind) + ".txt";
            try (BufferedReader br = new BufferedReader(new FileReader(inp_path, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    int kl = line.length();
                    line = change(line, '\"', '@');
                    String s;
                    ArrayList<String> ss;
                    //-----------age--------
                    s = age(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //-----------city--------state
                    ss = city(line);
                    for (String i : ss) {
                        out.write(i + "\n");
                    }
                    //-----------country--------
                    s = country(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                        out.write(line + "\n");
                    }
                    //-----------date---------time
                    ss = date(line);
                    for (String i : ss) {
                        out.write(i + "\n");
                    }
                    //---------DEPARTMENT-----------
                    s = department(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //----------doctor------------
                    ss = doctor(line);
                    for (String i : ss) {
                        out.write(i + "\n");
                    }
                    //---------duration----------
                    s = duration(line, Integer.toString(file_ind));
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //---------hospital-----------
                    s = hospital(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //----------idnum--------
                    ss = idnum(line);
                    for (String i : ss) {
                        out.write(i + "\n");
                    }
                    //---------location-other
                    s = location(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //--------medicalrecoro----------
                    s = medicalrecoro(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                   
                    //--------patoent-----------
                    s = patient(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //----------phone
                    s = phone(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //---------street----------
                    s = street(line, 120);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //--------zip-----------
                    /*s = zip(line);
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }*/
                    //-----------------------
                    words += kl + 1;
                    ls++;
                }
                /*if(now.length()>0 && today.length()>0) {
                    out.write(now+"\t"+today+"\n");
                }*/
                br.close();
            } catch (IOException e) {
                System.out.print("");
            }
        }
        for (file_ind = 0; file_ind < fdatalen; file_ind++) {
            boolean patient_name = false;
            now = "";
            today = "";
            words = 0;
            ls = 0;
            inp_path = data+"/" + "file" + Integer.toString(file_ind) + ".txt";
            try (BufferedReader br = new BufferedReader(new FileReader(inp_path, StandardCharsets.UTF_8))) {
                String line;
                ArrayList<String> ss;
                boolean bo_stre = true;
                while ((line = br.readLine()) != null) {
                    int kl = line.length();
                    line = change(line, '\"', '@');
                    String s;
                    if (line.contains("DateOfBirth")) {
                        bo_stre = false;
                    }
                    //-----------age--------
                    s = age(line);
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    //-----------city--------state
                    ss = city(line);
                    for (String i : ss) {
                        out.write("file" + i + "\n");
                    }
                    //-----------country--------
                    s = country(line);
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    //-----------date---------time
                    ss = date(line);
                    for (String i : ss) {
                        out.write("file" + i + "\n");
                    }
                    //---------DEPARTMENT-----------
                    s = department(line);
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    //---------duration----------
                    s = duration(line, "file" + Integer.toString(file_ind));
                    if (!s.equals("nono")) {
                        out.write(s + "\n");
                    }
                    //-----------doctor------------
                    ss = doctor(line);
                    for (String i : ss) {
                        out.write("file" + i + "\n");
                    }
                    //---------hospital-----------
                    s = hospital(line);
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    //----------idnum--------
                    ss = idnum(line);
                    for (String i : ss) {
                        out.write("file" + i + "\n");
                    }
                    //---------location-other
                    //--------medicalrecoro----------
                    s = medicalrecoro(line);
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    s = medicalrecoro1(line);
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    //----------patient--------
                    s = patient1(line, patient_name);
                    if (patient_name) {
                        patient_name = false;
                    }
                    if (s.equals("name")) {
                        patient_name = true;
                    } else if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    //--------phone
                    s = phone(line);
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    //--------street
                    s = "nono";
                    if (bo_stre) {
                        s = street(line, 320);
                    }
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }
                    //--------zip
                    /*s = zip(line);
                    if (!s.equals("nono")) {
                        out.write("file" + s + "\n");
                    }*/
                    //-----------------------
                    words += kl + 1;
                    ls++;
                }
                /*if(now.length()>0 && today.length()>0) {
                    out.write("file"+now+"\t"+today+"\n");
                }*/
                br.close();
            } catch (IOException e) {
                System.out.print("");
            }
        }
        out.close();
    }

    //----------------------------------
    public static void toset() throws IOException {
        intset = new HashSet<>();
        engset = new HashSet<>();
        bengset = new HashSet<>();
        sengset = new HashSet<>();
        thofset = new HashSet<>();
        spset = new HashSet<>();
        set_doc = new HashSet<>();
        set_bad_doc = new HashSet<>();
        String bad_doc[] = {"TO", "BI", "BL", "BF", "JC", "LW", "EE", "MC", "LK", "LR", "LN", "ZH", "JL", "KT", "KJ", "JT", "RR", "ED", "SC", "CA", "LJ", "JS", "DN", "YL", "NM", "KM", "JB", "AP", "LM", "HM", "FP", "PG", "MS", "SW"};
        for (String i : bad_doc) {
            set_bad_doc.add(i);
        }
        set_department = new LinkedList<>();
        set_hospital = new LinkedList<>();
        for (int i = (int) '0'; i <= (int) '9'; i++) {
            intset.add(i);
        }
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            sengset.add(i);
            engset.add(i);
        }
        for (int i = (int) 'A'; i <= (int) 'Z'; i++) {
            bengset.add(i);
            engset.add(i);
        }
        set_city = new HashSet<>();
        set_country = new HashSet<>();
        set_duration = new HashSet<>();
        set_state = new HashSet<>();
        BufferedReader ci = new BufferedReader(new FileReader("set/set_city.txt", StandardCharsets.UTF_8));
        BufferedReader co = new BufferedReader(new FileReader("set/set_country.txt", StandardCharsets.UTF_8));
        BufferedReader du = new BufferedReader(new FileReader("set/set_duration.txt", StandardCharsets.UTF_8));
        BufferedReader sa = new BufferedReader(new FileReader("set/set_state.txt", StandardCharsets.UTF_8));
        String line;
        while ((line = ci.readLine()) != null) {
            set_city.add(line);
        }
        while ((line = co.readLine()) != null) {
            set_country.add(line);
        }
        while ((line = du.readLine()) != null) {
            set_duration.add(line);
        }
        while ((line = sa.readLine()) != null) {
            set_state.add(line);
        }
        BufferedReader de = new BufferedReader(new FileReader("set/set_department.txt", StandardCharsets.UTF_8));
        BufferedReader ho = new BufferedReader(new FileReader("set/set_hospital.txt", StandardCharsets.UTF_8));
        while ((line = de.readLine()) != null) {
            set_department.add(line);
        }
        while ((line = ho.readLine()) != null) {
            set_hospital.add(line);
        }
        de.close();
        ho.close();
        ci.close();
        co.close();
        du.close();
        sa.close();
        char thofs[] = {'t', 'h', 'o', 'f'};
        char sps[] = {'.', '/', '-'};
        for (int i : thofs) {
            thofset.add(i);
        }
        for (int i : sps) {
            spset.add(i);
        }
        String sp_doc[] = {"Ta", "cc", "ec", "mm", "ta", "cy", "ank", "bl", "rt", "vo"};
        for (String i : sp_doc) {
            set_doc.add(i);
        }
    }

    public static boolean inset(String n, String set[]) {
        for (String i : set) {
            if (n.contains(i)) {
                return (true);
            }
        }
        return false;
    }

    public static String get_ans(String n, int ind, String typ) {
        String ans = Integer.toString(file_ind) + "\t" + typ + "\t";
        int r = words + ind;
        ans += Integer.toString(r) + "\t" + Integer.toString(r + n.length()) + "\t" + n;
        return ans;
    }

    public static String get_ans1(String n, int ind, String typ, String n1) {
        String ans = Integer.toString(file_ind) + "\t" + typ + "\t";
        int r = words + ind;
        ans += Integer.toString(r) + "\t" + Integer.toString(r + n.length()) + "\t" + n + "\t" + n1;
        return ans;
    }

    public static String change(String n, char n1, char n2) {
        char nn[] = n.toCharArray();
        if (n.length() < 2) {
            return n;
        }
        if (n2 == '@') {
            int ind1 = nn[0] == '\"' ? 1 : 0;
            int ind2 = nn[n.length() - 1] == '\"' ? n.length() - 1 : n.length();
            n = n.substring(ind1, ind2);
            while (n.contains("\"\"")) {
                int c = n.indexOf("\"\"");
                n = n.substring(0, c) + n.substring(c + 1, n.length());
            }
            if (n.length() > 2 && n.charAt(0) == '\"') {
                n = n.substring(1, n.length());
            }
            return n;
        }
        for (int i = 0; i < n.length(); i++) {
            nn[i] = nn[i] == n1 ? n2 : nn[i];
        }
        n = new String(nn);
        return (n);
    }

    //-----------AGE-----------------
    public static String age(String n) {
        String sp[] = {"yr old", "F.", "yrs old", "years old", "M.", "year old", "y.o", "yo", "year-old", "old"};
        if (n.length() < 6) {
            return ("nono");
        }
        char n1[] = n.toCharArray();
        if (intset.contains((int) n1[0]) && intset.contains((int) n1[1])) {
            if (n1[2] == 'F' && n1[3] == ' ') {
                return (get_ans(n.substring(0, 2), 0, "AGE"));
            }
        }
        if (n1[2] == ' ' && n1[3] == 'F' && n1[4] == ' ') {
            return (get_ans(n.substring(0, 2), 0, "AGE"));
        }
        for (String p : sp) {
            if (n.contains(p)) {
                int ind2 = n.indexOf(p);
                if (ind2 - 1 >= 0 && engset.contains((int) n1[ind2 - 1])) {
                    continue;
                }
                if (ind2 + p.length() < n.length() && engset.contains((int) n1[ind2 + p.length()])) {
                    continue;
                }
                for (int i = 1; ind2 - 1 >= 0 && i < 3; i++) {
                    ind2 -= 1;
                    if (intset.contains((int) n1[ind2])) {
                        break;
                    }
                }
                ind2++;
                int ind1;
                for (ind1 = ind2 - 1; ind1 >= 0; ind1--) {
                    if (!intset.contains((int) n1[ind1])) {
                        break;
                    }
                }
                ind1++;
                if (ind1 == ind2) {
                    continue;
                }
                return (get_ans(n.substring(ind1, ind2), ind1, "AGE"));
            }
        }
        n1 = n.toCharArray();
        if (n.contains("age")) {
            int ind1 = n.indexOf("age");
            if (ind1 - 1 >= 0 && engset.contains((int) n1[ind1 - 1])) {
                return ("nono");
            }
            if (ind1 + 3 < n.length() && engset.contains((int) n1[ind1 + 3])) {
                return ("nono");
            }
            ind1 += 3;
            for (int i = 0; i < 3 && ind1 + 1 < n.length(); i++) {
                ind1++;
                if (intset.contains((int) n1[ind1])) {
                    break;
                }
            }
            int ind2;
            for (ind2 = ind1; ind2 < ind1 + 3 && ind2 + 1 <= n.length(); ind2++) {
                if (!intset.contains((int) n1[ind2])) {
                    break;
                }
            }
            if (ind1 == ind2) {
                return ("nono");
            }
            return (get_ans(n.substring(ind1, ind2), ind1, "AGE"));
        }
        return ("nono");
    }

    //-------------city---------------------------
    public static ArrayList<String> city(String n) {

        ArrayList arr = new ArrayList<>();
        if (n.length() < 7 || n.length() > 65) {
            return arr;
        }
        String np[] = n.split(" ");
        if (np.length < 2) {
            return arr;
        }
        if (n.contains("BOX")) {
            return arr;
        }
        for (int i = 0; i < np.length - 1; i++) {
            if (np[i].length() > 0 && !bengset.contains((int) np[i].charAt(0))) {
                return arr;
            }
        }
        char n1[] = n.toCharArray();
        for (int i = n.length() - 1; i >= n.length() - 4; i--) {
            if (!intset.contains((int) n1[i])) {
                return arr;
            }
        }
        for (int i = 0; i < n.length() - 4; i++) {
            if (intset.contains((int) n1[i])) {
                return arr;
            }
        }
        arr.add(get_ans(n.substring(n.length() - 4, n.length()), n.length() - 4, "ZIP"));

        n = n.substring(0, n.length() - 4).trim();
        boolean bo = false;
        for (String i : set_state) {
            for (int j = 0; j < 2; j++) {
                int u = n.length() - i.length() - j;
                if (u < 0) {
                    break;
                }
                String pp = n.substring(u, u + i.length());
                if (pp.equals(i)) {
                    arr.add(get_ans(i, u, "STATE"));
                    n = n.substring(0, u).trim();
                    bo = true;
                    break;
                }
            }
            if (bo) {
                break;
            }
        }

        arr.add(get_ans(n, 0, "CITY"));
        return arr;
    }

    //-------------country---------------------------
    public static String country(String n) {
        if (words < 200) {
            return ("nono");
        }
        for (String i : set_country) {
            if (n.contains(i)) {
                return (get_ans(i, n.indexOf(i), "COUNTRY"));
            }
        }
        return ("nono");
    }
    //--------------date--------------------------
    static String month[][] = {{"0", "0"}, {"January", "Jan"}, {"February", "Feb"}, {"March", "Mar"}, {"April", "Apr"}, {"May", "May"}, {"June", "Jun"}, {"July", "July"}, {"August", "Aug"}, {"September", "Sep"}, {"October", "Oct"}, {"November", "Nov"}, {"December", "Dec"}};

    public static ArrayList<String> date(String n) {
        ArrayList<String> arr = new ArrayList<>();

        char n1[] = n.toCharArray();
        boolean bo_yy = true;
        // now, today
        String sp_now[] = {"now", "today", "Today"};
        for (String spn : sp_now) {
            if (n.contains(spn)) {
                int ind = n.indexOf(spn);
                if (ind > 0 && engset.contains((int) n1[ind - 1])) {
                    continue;
                }
                if (ind + spn.length() < n.length() && engset.contains((int) n1[ind + spn.length()])) {
                    continue;
                }
                now = get_ans(spn, ind, "DATE");
            }
        }
        //20201103  199607180000
        if (n.contains("  ADDITIONAL REPORT 17/4/63.")) {
            n = n;
        }
        for (int i = 0; i <= n.length() - 8; i++) {
            if (n.contains("MRN no") && n.length() < 30) {
                break;
            }
            String s0 = n.substring(i, i + 8);
            String s1 = date1(s0.toCharArray());
            if (s1 != "nono") {
                arr.add(get_ans1(s0, i, "DATE", s1));//RETURN
                bo_yy = false;
            }
        }
        //-----------June--------------1 June 2065
        int ind1, ind2, ind3;
        String days1[] = {"", "", ""};
        for (int mos = 1; mos <= 12; mos++) {
            for (String mo : month[mos]) {
                if (n.contains(mo)) {
                    ind1 = n.indexOf(mo);
                    if (ind1 > 0 && engset.contains((int) n1[ind1 - 1])) {
                        continue;
                    }
                    if (ind1 + mo.length() + 2 < n.length() && engset.contains((int) n1[ind1 + mo.length()])) {
                        continue;
                    }
                    days1[1] = Integer.toString(mos);
                    days1[2] = "";
                    days1[0] = "";
                    for (ind2 = ind1 + mo.length() + 1; ind2 < n.length() && ind2 < ind1 + mo.length() + 6; ind2++) {
                        if (intset.contains((int) n1[ind2])) {
                            days1[2] += n1[ind2];
                        } else if (days1[2].length() > 0) {
                            break;
                        }
                    }
                    for (ind3 = ind1; ind3 >= 0 && ind3 > ind1 - 4; ind3--) {
                        if (intset.contains((int) n1[ind3])) {
                            days1[0] = n1[ind3] + days1[0];
                        } else if (days1[0].length() > 0) {
                            break;
                        }
                    }
                    ind3 += 1;
                    if (days1[0].length() == 0 && ind1 - 5 > 0) {
                        for (ind3 = ind1 - 5; ind3 >= 0 && ind3 > ind1 - 9; ind3--) {
                            String u = n.substring(ind3, ind3 + 5);
                            if (u.equals("th of") || u.equals("rd of")) {
                                int ind4;
                                for (ind4 = ind3 - 1; ind4 >= 0 && ind4 > ind3 - 4; ind4--) {
                                    if (intset.contains((int) n1[ind4])) {
                                        days1[0] = n1[ind4] + days1[0];
                                    } else if (days1[0].length() > 0) {
                                        break;
                                    }
                                }
                                ind3 = ind4 + 1;
                                break;
                            }
                        }
                    }
                    String dt = "";
                    if (days1[0].length() > 0) {
                        if (days1[0].length() == 1) {
                            days1[0] = "0" + days1[0];
                        }
                        ind1 = ind3;
                        dt = "-" + days1[0];
                    }
                    if (days1[1].length() == 1) {
                        days1[1] = "0" + days1[1];
                    }
                    if (days1[2].length() == 2) {
                        days1[2] = "20" + days1[2];
                    } else if (days1[2].length() == 1 || days1[2].length() == 3) {
                        continue;
                    }
                    dt = days1[2] + "-" + days1[1] + dt;
                    if (days1[0].length() < 1 && days1[2].length() < 1) {
                        continue;
                    }
                    arr.add(time(n, dt, ind1, ind2));
                    bo_yy = false;
                }
            }
        }
        //---------------21.6.2023 

        n1 = n.toCharArray();
        String days[] = {"", "", ""};
        int ind_d = 0;
        int inds = 0;
        for (int i = 0; i < n.length(); i++) {
            if (intset.contains((int) n1[i])) {
                if (days[0].length() == 0) {
                    inds = i;
                }
                days[ind_d] += n1[i];
            } else if (spset.contains((int) n1[i])) {
                if (days[ind_d].length() > 0) {
                    ind_d++;
                }
                if (ind_d == 3) {
                    String dt = date2(days);
                    if (dt.equals("nono")) {
                        for (int x = 0; x < 3; x++) {
                            days[x] = "";
                        }
                        ind_d = 0;
                    } else {
                        arr.add(time(n, dt, inds, i));//return
                        bo_yy = false;
                    }
                }
            } else {
                if (ind_d == 2) {
                    String dt = date2(days);
                    if (!dt.equals("nono")) {
                        arr.add(time(n, dt, inds, i));//return
                        bo_yy = false;
                    }
                }
                for (int x = 0; x < 3; x++) {
                    days[x] = "";
                }
                ind_d = 0;
            }
        }
        String dt1 = date2(days);
        n1 = n.toCharArray();
        if (!dt1.equals("nono") && intset.contains((int) n1[n.length() - 1])) {
            arr.add((time(n, dt1, inds, n.length())));//return
            bo_yy = false;
        }
        //----------2737-09-03 00:00:00
        if (n.length() < 21 && n.contains("00:00:00")) {
            arr.add(get_ans1(n, 0, "TIME", n.substring(0, 11).trim() + "T00:00:00"));
        }
        //---------------
        if (!bo_yy) {
            return arr;
        }
        for (int i = 0; i < n.length() - 4; i++) {
            if (i > 0 && n1[i - 1] != ' ') {
                continue;
            }
            if (i + 4 < n.length() && n1[i + 4] != '.') {
                continue;
            }
            if (intset.contains((int) n1[i]) && intset.contains((int) n1[i + 1]) && intset.contains((int) n1[i + 2]) && intset.contains((int) n1[i + 3])) {
                int u = Integer.parseInt(n.substring(i, i + 4));
                if (u > 2100 || u < 1900) {
                    continue;
                }
                arr.add(get_ans1(n.substring(i, i + 4), i, "DATE", n.substring(i, i + 4)));
                break;
            }
        }
        return arr;
    }

    public static String date2(String n[]) {
        for (String i : n) {
            if (i.length() == 0) {
                return ("nono");
            }
        }
        n[2] = n[2].length() == 2 ? "20" + n[2] : n[2];
        int n1[] = Arrays.stream(n).mapToInt((Integer::parseInt)).toArray();
        if (n1[0] <= 31 && n1[1] <= 12 && n1[2] > 1800 && n1[2] < 2500) {
            n[0] = n[0].length() == 1 ? "0" + n[0] : n[0];
            n[1] = n[1].length() == 1 ? "0" + n[1] : n[1];
            return (n[2] + "-" + n[1] + "-" + n[0]);
        }
        return ("nono");
    }

    public static String date1(char n[]) {
        if (n[0] != '1' && n[0] != '2') {
            return ("nono");
        }
        if (n[1] != '1' && n[1] != '0' && n[1] != '9' && n[1] != '8') {
            return ("nono");
        }
        if (n[4] != '1' && n[4] != '0') {
            return ("nono");
        }
        if (n[6] != '0' && n[6] != '1' && n[6] != '2' && n[6] != '3') {
            return ("nono");
        }
        for (int i = 2; i < 8; i++) {
            if (!intset.contains((int) n[i])) {
                return ("nono");
            }
        }
        int yy = Integer.parseInt(n[0] + "" + n[1] + "" + n[2] + "" + n[3]);
        int mm = Integer.parseInt(n[4] + "" + n[5]);
        int dd = Integer.parseInt(n[6] + "" + n[7]);
        if (yy > 2200 || yy < 1800 || mm > 12 || mm < 1 || dd > 31 || dd < 1) {
            return "nono";
        }
        String p = n[0] + "" + n[1] + "" + n[2] + "" + n[3] + "-" + n[4] + "" + n[5] + "-" + n[6] + "" + n[7];
        return (p);
    }

    //---------------time-----------------
    public static String time(String n, String d, int ind1, int ind2) {
        int indaaa = ind1 + 0, indbbb = ind2 * 1;
        char n1[] = n.toCharArray();
        //-------00:00:00
        String sp_hr[] = {"hrs", "Hrs", "hr"};
        if (n.contains("16th of October 2013 at 11:25am")) {
            n = n;
        }
        //------2013 at 10:15am
        int inda;
        if ((ind2 + 4 <= n.length() && n.subSequence(ind2 + 2, ind2 + 4).equals("at")) || (ind2 + 3 <= n.length() && n.subSequence(ind2 + 1, ind2 + 3).equals("at")) || (ind2 + 2 <= n.length() && n.subSequence(ind2, ind2 + 2).equals("at"))) {
            String t[] = {"", ""};
            int ind_t = 0;
            for (inda = ind2 + 2; inda < Math.min(ind2 + 12, n.length()); inda++) {
                if (intset.contains((int) n1[inda])) {
                    t[ind_t] += n1[inda];
                } else if (n1[inda] == ':' || n1[inda] == '.') {
                    if (t[0].length() > 0) {
                        ind_t = 1;
                    }
                } else {
                    String pk = "", pp = "";
                    if (inda + 2 <= n.length()) {
                        pk = n.substring(inda, inda + 2);
                        pk = pk.equals("pm") || pk.equals("PM") || pk.equals("Pm") ? "pm" : "";
                        pp = n.substring(inda, inda + 2);
                        pp = pp.equals("am") || pp.equals("AM") || pp.equals("Am") ? "am" : "";
                    }
                    String m = istime(t, pk);
                    for (String hrs : sp_hr) {
                        for (int i = 0; i < 2; i++) {
                            if (inda + i + hrs.length() <= n.length() && n.substring(inda + i, inda + i + hrs.length()).equals(hrs)) {
                                if (inda + i + hrs.length() < n.length() && engset.contains((int) n1[inda + i + hrs.length()])) {
                                    continue;
                                }
                                inda += i + hrs.length();
                                break;
                            }
                        }
                    }
                    if (!m.equals("nono")) {
                        while (inda - 1 > 0 && n1[inda - 1] == ':' || n1[inda - 1] == '.') {
                            inda--;
                        }
                        return get_ans1(n.substring(ind1, inda + Math.max(pk.length(), pp.length())), ind1, "TIME", d + m);
                    } else {
                        t[0] = "";
                        t[1] = "";
                        ind_t = 0;
                    }
                }

            }

            String m = istime(t, "");
            while (inda - 1 > 0 && n1[inda - 1] == ':' || n1[inda - 1] == '.') {
                inda--;
            }
            if (!m.equals("nono")) {
                return get_ans1(n.substring(ind1, inda), ind1, "TIME", d + m);
            }
        }
        //--------------on
        if (n.contains("12:51pm on 2.3.2015")) {
            n = n;
        }
        if ((ind1 > 3 && n.substring(ind1 - 4, ind1).equals(" on ")) || (ind1 > 2 && n.substring(ind1 - 3, ind1).equals(" on")) || (ind1 > 2 && n.substring(ind1 - 3, ind1).equals("on ")) || (ind1 > 6 && n.substring(ind1 - 7, ind1).equals("on the "))) {
            ind1 -= n.substring(ind1 - 4, ind1).equals(" on ") ? 4 : (n.substring(ind1 - 7, ind1).equals("on the ") ? 7 : 3);
            String zz = n.substring(Math.max(0, ind1 - 6), ind1 + 1);
            String pm = "";
            if (zz.contains("PM") || zz.contains("pm") || zz.contains("Pm")) {
                pm = "pm";
            }
            String tt[] = {"", ""};
            int ind_tt = 1;
            for (int i = ind1 - 1; i >= 0 && i > ind1 - 12; i--) {
                if (intset.contains((int) n1[i])) {
                    tt[ind_tt] = n1[i] + tt[ind_tt];
                } else if (n1[i] == ':' || n1[i] == '.') {
                    if (tt[1].length() > 0) {
                        ind_tt = 0;
                    }
                } else {
                    if (tt[0].length() == 0) {
                        tt[0] = tt[1];
                        tt[1] = "";
                    }

                    String m = istime(tt, pm);
                    if (!m.equals("nono")) {
                        return get_ans1(n.substring(i + 1, ind2), i + 1, "TIME", d + m);
                    } else {
                        tt[0] = "";
                        tt[1] = "";
                        ind_tt = 1;
                    }
                }
            }
            if (tt[0].length() == 0) {
                tt[0] = tt[1];
                tt[1] = "";
            }
            String m = istime(tt, pm);
            if (!m.equals("nono")) {
                return get_ans1(n.substring(0, ind2), 0, "TIME", d + m);
            }
        }
        //----------at 1217Hrson 27.2.14 by Dr. V. Guerett
        int inda1 = indaaa + 0, indb1 = indbbb + 0;
        for (String hrs : sp_hr) {
            for (inda1 = indaaa - hrs.length(); inda1 >= 0 && inda1 > indaaa - 8; inda1--) {
                if (n.substring(inda1, inda1 + hrs.length()).equals(hrs)) {
                    String ht = "";
                    int indc1;
                    for (indc1 = inda1 - 1; indc1 > inda1 - 6 && indc1 >= 0; indc1--) {
                        if (intset.contains((int) n1[indc1])) {
                            ht = n1[indc1] + ht;
                        } else {
                            break;
                        }
                    }
                    indc1++;
                    if (ht.length() == 4) {
                        ht = "T" + ht.substring(0, 2) + ":" + ht.substring(2, 4);
                        return get_ans1(n.substring(indc1, ind2), indc1, "TIME", d + ht);
                    } else if (ht.length() == 3) {
                        ht = "T" + ht.substring(0, 2) + ":" + "00";
                        return get_ans1(n.substring(indc1, ind2), indc1, "TIME", d + ht);
                    }
                }

            }
        }
        //------------Result to registrar by Dr K Royston 11:20am 25.1.13. 
        int inda2, indb2 = indbbb;
        for (int i = Math.max(0, indaaa - 2); i >= 0 && i > indaaa - 8; i--) {
            String k = n.substring(i, i + 2);
            int ind_tt = 1;
            if (k.equals("am") || k.equals("pm") || k.equals("Am") || k.equals("Pm") || k.equals("AM") || k.equals("PM")) {
                String tt[] = {"", ""};
                for (inda2 = i - 1; inda2 >= 0 && inda2 > i - 8; inda2--) {
                    if (intset.contains((int) n1[inda2])) {
                        tt[ind_tt] = n1[inda2] + tt[ind_tt];
                    } else if (n1[inda2] == ':' || n1[inda2] == '.') {
                        if (tt[1].length() > 0) {
                            ind_tt = 0;
                        }
                    } else {
                        if (tt[0].length() == 0) {
                            tt[0] = tt[1];
                            tt[1] = "";
                        }
                        String m = istime(tt, "");
                        if (!m.equals("nono")) {
                            return get_ans1(n.substring(inda2 + 1, indb2), inda2 + 1, "TIME", d + m);
                        } else {
                            tt[0] = "";
                            tt[1] = "";
                            ind_tt = 1;
                        }
                    }
                }
                if (tt[0].length() == 0) {
                    tt[0] = tt[1];
                    tt[1] = "";
                }
                String m = istime(tt, k.equals("pm") || k.equals("Pm") || k.equals("PM") ? "pm" : "");
                if (!m.equals("nono")) {
                    return get_ans1(n.substring(1, ind2), 0, "TIME", d + m);
                }
            }
        }
        if(n.contains("4/8/16 15:50pm")){
            n=n;
        }
        //---------4/8/16 15:50pm
        int indb4 = indbbb;
        String tt1[] = {"", ""};
        int ind_tt1 = 0;
        for (indb4 = indbbb; indb4 < indbbb + 9 && indb4 + 2 <= n.length(); indb4++) {
            String k = n.substring(indb4, indb4 + 2);
            if (k.equals("AM") || k.equals("Am") || k.equals("am") || k.equals("PM") || k.equals("Pm") || k.equals("pm")) {
                k=( k.equals("PM") || k.equals("Pm") || k.equals("pm"))?"pm":"am";
                if (!(indbbb + 3 > n.length() && engset.contains((int) n1[indb1 + 3]))) {
                    String m = istime(tt1, k);
                    if (!m.equals("nono")) {
                        return get_ans1(n.substring(indaaa , indb4+2), indaaa, "TIME", d + m);
                    }
                }
            }
            if (intset.contains((int) n1[indb4])) {
                tt1[ind_tt1] += n1[indb4];
            } else if (n1[indb4] == ':' || n1[indb4] == '.') {
                if (tt1[0].length() > 0) {
                    ind_tt1 = 1;
                }
            } else {
                tt1[0] = "";
                tt1[1] = "";
                ind_tt1 = 0;
            }
        }
        //-----------23-Sep-13 14:00
        int indb3;
        int ind_tt = 0;
        String tt[] = {"", ""};

        for (indb3 = indbbb; indb3 < n.length() && indb3 < indbbb + 8; indb3++) {
            if (intset.contains((int) n1[indb3])) {
                tt[ind_tt] += n1[indb3];
            } else if (n1[indb3] == ':') {
                if (tt[0].length() > 0) {
                    ind_tt = 1;
                }
            } else {
                if (tt[0].length() == 4) {
                    String t1 = d + "T" + tt[0].substring(0, 2) + ":" + tt[0].substring(2, 4);
                    return get_ans1(n.substring(indaaa, indb3), indaaa, "TIME", t1);
                } else if (tt[0].length() == 2) {
                    int pm = 0;
                    String t1 = d + "T";
                    if (indb3 + 2 < n.length()) {
                        String sb = n.substring(indb3, indb3 + 2);
                        if (sb.equals("pm") || sb.equals("Pm") || sb.equals("PM")) {
                            indb3 += 2;
                            pm = 12;
                        } else if (sb.equals("am") || sb.equals("Am") || sb.equals("AM")) {
                            indb3 += 2;
                            pm = 1;
                        }
                    }
                    if (pm == 0 && indb3 + 3 < n.length()) {
                        String sb = n.substring(indb3, indb3 + 3);
                        if (sb.equals(" pm") || sb.equals(" Pm") || sb.equals(" PM")) {
                            indb3 += 3;
                            pm = 12;
                        } else if (sb.equals("am") || sb.equals("Am") || sb.equals("AM")) {
                            indb3 += 3;
                            pm = 1;
                        }
                    }
                    if (pm == 12 && Integer.parseInt(tt[0]) < 12) {
                        tt[0] = Integer.toString(Integer.parseInt(tt[0]) + pm);
                    }
                    if (tt[1].length() == 0) {
                        t1 += tt[0] + ":00";
                        if (pm > 0) {
                            return get_ans1(n.substring(indaaa, indb3), indaaa, "TIME", t1);
                        }
                    } else if (tt[1].length() == 2) {
                        return get_ans1(n.substring(indaaa, indb3), indaaa, "TIME", t1 + tt[0] + ":" + tt[1]);
                    }

                }
            }
            int pm = 0;
            String t1 = d + "T";
            if (indb3 + 2 < n.length()) {
                String sb = n.substring(indb3, indb3 + 2);
                if (sb.equals("pm") || sb.equals("Pm") || sb.equals("PM")) {
                    indb3 += 2;
                    pm = 12;
                } else if (sb.equals("am") || sb.equals("Am") || sb.equals("AM")) {
                    indb3 += 2;
                    pm = 1;
                }
            }
            if (pm == 0 && indb3 + 3 < n.length()) {
                String sb = n.substring(indb3, indb3 + 3);
                if (sb.equals(" pm") || sb.equals(" Pm") || sb.equals(" PM")) {
                    indb3 += 3;
                    pm = 12;
                } else if (sb.equals("am") || sb.equals("Am") || sb.equals("AM")) {
                    indb3 += 3;
                    pm = 1;
                }
            }
            if (pm == 12 && Integer.parseInt(tt[0]) < 12) {
                tt[0] = Integer.toString(Integer.parseInt(tt[0]) + pm);
            }
            if (tt[1].length() == 0) {
                t1 += tt[0] + ":00";
                if (pm > 0) {
                    return get_ans1(n.substring(indaaa, indb3), indaaa, "TIME", t1);
                }
            } else if (tt[1].length() == 2) {
                return get_ans1(n.substring(indaaa, indb3), indaaa, "TIME", t1 + tt[0] + ":" + tt[1]);
            }
        }

        return get_ans1(n.substring(indaaa, indbbb), indaaa, "DATE", d);
    }

    public static void get_today(String n) {
        if (today.length() == 0) {
            today = n;
        } else {
            int n1[] = Arrays.stream(n.split("-")).mapToInt(Integer::parseInt).toArray();
            int n2[] = Arrays.stream(today.split("-")).mapToInt(Integer::parseInt).toArray();
            if (n1.length < 3 || n2.length < 3) {
                return;
            }
            if (n1[0] > n2[0]) {
                today = n;
            } else if (n1[0] == n2[0]) {
                if (n1[1] > n2[1]) {
                    today = n;
                } else if (n1[1] == n2[1]) {
                    if (n1[2] > n2[2]) {
                        today = n;
                    }
                }
            }
        }
    }

    public static String duration(String n, String file) {
        if (n.length() < 18) {
            return "nono";
        }
        String a = n.substring(0, 15);
        if (a.contains("year old") || a.contains("yr old")) {
            n = n.substring(15, n.length());
        }
        char n1[] = n.toCharArray();
        String sp[] = {"month", "months", "year", "yr", "yrs", "years", "week", "weeks", "wks", "wk", "days", "day","dy","dys"};
        for (String i : sp) {
            if (n.contains(i)) {
                int ind1 = n.indexOf(i);
                int ind2 = ind1 + i.length();
                if (ind1 - 1 >= 0 && engset.contains((int) n1[ind1 - 1])) {
                    continue;
                }
                if (ind2 < n.length() && engset.contains((int) n1[ind2])) {
                    continue;
                }
                int inda;
                String k="";
                for(inda=ind1;inda-1>0 && ( n1[inda-1]==' ' || intset.contains((int)n1[inda-1]));inda--){
                    if(n1[inda-1]!=' ') k=n1[inda-1]+k;
                }
                if(k.length()==0) continue;
                k="P"+k+(char) (i.charAt(0)-32);
                while(n1[inda]==' ') inda++;
                return(get_ans1(n.substring(inda,ind2),inda,"DURATION",k));
            }
        }

        return ("nono");
    }

    public static String istime(String n[], String p) {
        if (n[0].length() == 0) {
            return "nono";
        }
        if (n[0].length() == 4) {
            n[1] = n[0].substring(2, 4);
            n[0] = n[0].substring(0, 2);
        }
        int a = Integer.parseInt(n[0]);
        a += p.equals("pm") && a < 12 ? 12 : 0;
        if (a > 24) {
            return "nono";
        }
        n[0] = a < 10 ? "0" + Integer.toString(a) : Integer.toString(a);
        if (n[1].length() == 0) {
            return ("T" + n[0] + ":00");
        }
        if (Integer.parseInt(n[1]) > 60) {
            return ("nono");
        }
        if (n[1].length() == 1) {
            n[1] = "0" + n[1];
        }
        return ("T" + n[0] + ":" + n[1]);
    }

    public static String hospital(String n) {
        for (String i : set_hospital) {
            if (n.contains(i)) {
                return get_ans(i, n.indexOf(i), "HOSPITAL");
            }
        }
        return ("nono");
    }

    public static String department(String n) {
        char n1[] = n.toCharArray();

        for (String i : set_department) {
            if (n.contains(i)) {
                int ind = n.indexOf(i);
                if (ind > 0 && engset.contains((int) n1[ind - 1])) {
                    return "nono";
                }
                if (ind + i.length() < n.length() && engset.contains((int) n1[ind + i.length()])) {
                    return "nono";
                }
                return get_ans(i, n.indexOf(i), "DEPARTMENT");
            }
        }
        String sp_d1[] = {"Anatomical Pathology", "North", "SEALS", "HAPS", "IMVS", "Lowy", "DHM", "RHW", "SXR", "North", "Envoi pathology", "West", "WEST", "ACT", "Envoi pathology"};
        if (n.contains("Location") || n.contains("Report from")) {
            for (String i : sp_d1) {
                if (n.contains(i)) {
                    int ind = n.indexOf(i);
                    if (ind > 0 && engset.contains((int) n1[ind - 1])) {
                        return "nono";
                    }
                    if (ind + i.length() < n.length() && engset.contains((int) n1[ind + i.length()])) {
                        return "nono";
                    }
                    return get_ans(i, n.indexOf(i), "DEPARTMENT");
                }
            }
        }
        return ("nono");
    }

    public static ArrayList<String> idnum(String n) {
        ArrayList<String> arr = new ArrayList<>();
        char[] n1 = n.toCharArray();
        int na[] = new int[n.length()];
        int nap[] = {2, 2, 1, 2, 2, 2, 2};
        for (int i = 0; i < n.length(); i++) {
            na[i] = bengset.contains((int) n1[i]) ? 1 : intset.contains((int) n1[i]) ? 2 : 0;
        }
        for (int i = 0; i < n.length() - 6; i++) {
            int ind = 0;
            while (i + ind < n.length()) {
                if (ind < 7) {
                    if (na[i + ind] != nap[ind]) {
                        break;
                    }
                } else {
                    if (na[i + ind] == 0) {
                        break;
                    }
                }
                ind++;
            }
            if (ind > 6) {
                arr.add(get_ans(n.substring(i, ind + i), i, "IDNUM"));
            }
        }
        return arr;
    }

    public static String medicalrecoro(String n) {
        if (words > 600) {
            return ("nono");
        }
        char n1[] = n.toCharArray();
        String sp[] = {"MRN no:", "MRN:"};
        int ind1 = -1, ind2;
        for (String p : sp) {
            if (n.contains(p)) {
                ind1 = n.indexOf(p) + p.length();
                for (int i = 0; i < 3 && ind1 < n.length(); i++) {
                    if (intset.contains((int) n1[ind1])) {
                        break;
                    }
                    ind1++;
                }
                for (ind2 = ind1; ind2 < n.length(); ind2++) {
                    if (!intset.contains((int) n1[ind2])) {
                        break;
                    }
                }
                return (get_ans(n.substring(ind1, ind2), ind1, "MEDICALRECORD"));
            }
        }

        for (int i = 5; i < n.length() - 3; i++) {
            if (n1[i] != '.') {
                continue;
            }
            if (!(bengset.contains((int) n1[i + 1]) && bengset.contains((int) n1[i + 2]) && bengset.contains((int) n1[i + 3]))) {
                continue;
            }
            int inda;
            for (inda = i - 1; inda >= Math.max(0, i - 10); inda--) {
                if (!intset.contains((int) n1[inda])) {
                    break;
                }
            }
            inda++;
            if (i - inda < 4) {
                continue;
            }
            return (get_ans(n.substring(inda, i + 4), inda, "MEDICALRECORD"));
        }
        return "nono";
    }

    public static String medicalrecoro1(String n) {
        if (words > 150 || words == 12) {
            return "nono";
        }
        if (n.length() > 8 || n.length() < 6) {
            return "nono";
        }
        char n1[] = n.toCharArray();
        for (char i : n1) {
            if (!intset.contains((int) i)) {
                return "nono";
            }
        }
        return get_ans(n, 0, "MEDICALRECORD");
    }

    public static ArrayList doctor(String n) {
        ArrayList<String> arr = new ArrayList<>();
        if (n.length() < 5) {
            return arr;
        }
        char n1[] = n.toCharArray();
        String sp[] = {"Dr", "Dr.", "DR", "Prof", "Drs"};
        int ind1 = 0, ind2 = 0;
        for (String p : sp) {
            for (int t = 0; t < n.length() - p.length(); t++) {
                if (n.substring(t, t + p.length()).equals(p)) {
                    ind1 = t;
                    if (ind1 > 0 && bengset.contains((int) n1[ind1 - 1])) {
                        continue;
                    }
                    ind1 += p.length();
                    if (ind1 + 1 > n.length()) {
                        continue;
                    }
                    if (n1[ind1] != ' ') {
                        continue;
                    }
                    ind1++;
                    if (bengset.contains((int) n1[ind1])) {

                        ind2 = ind1 + 1;
                        if (ind2 < n.length() && n1[ind2] == ' ') {
                            //---------D Adaswq ,D D Fgafq
                            ind2++;
                            if (ind2 + 2 < n.length() && engset.contains((int) n1[ind2]) && n1[ind2 + 1] == ' ') {
                                ind2 += 2;
                            }
                            while (ind2 < n.length() && (engset.contains((int) n1[ind2]) || n1[ind2] == '-')) {
                                ind2++;
                            }
                            arr.add(get_ans(n.substring(ind1, ind2), ind1, "DOCTOR"));
                        } else {
                            //---------D Adaswq Dfdasd DFdas
                            for (ind2 = ind1 + 1; ind2 < n.length(); ind2++) {
                                if (n1[ind2 - 1] == ' ' && !bengset.contains((int) n1[ind2])) {
                                    break;
                                }
                                if (!engset.contains((int) n1[ind2]) && n1[ind2] != ' ' && n1[ind2] != '-' && n1[ind2] != '.' && n1[ind2] != '\'') {
                                    break;
                                }
                                if (n1[ind2] == '\'' && ind2 + 1 < n.length() && n1[ind2 + 1] == 's') {
                                    break;
                                }
                            }
                            while (ind2 > 1 && !engset.contains((int) n1[ind2 - 1])) {
                                ind2--;
                            }
                            if (ind2 - ind1 > 1) {
                                arr.add(get_ans(n.substring(ind1, ind2), ind1, "DOCTOR"));
                            }

                        }
                    }
                }
            }
        }
        //--------(TO:  YJ:  EV/mm 7.8.63)

        int inda, indb;
        for (inda = 0; inda < n.length() - 8; inda++) {
            if (n1[inda] != '(') {
                continue;
            }
            for (indb = inda + 1; indb < n.length() && indb < inda + 30; indb++) {
                if (n1[indb] == ')') {
                    String nn = n.substring(inda, indb + 1);
                    char nn1[] = nn.toCharArray();
                    for (String p : set_doc) {
                        if (nn.contains(p)) {
                            HashSet<String> set = new HashSet<>();
                            for (int i = Math.max(nn.indexOf(p) - 11, 1); i < nn.indexOf(p) - 1; i++) {
                                if (bengset.contains((int) nn1[i]) && bengset.contains((int) nn1[i + 1]) && !engset.contains((int) nn1[i - 1]) && !engset.contains((int) nn1[i + 2])) {
                                    String kk = nn.substring(i, i + 2);
                                    if (kk.equals("TO")) {
                                        continue;
                                    }
                                    if (set.contains(kk)) {
                                        set.clear();
                                        break;
                                    } else {
                                        set.add(kk);
                                    }
                                }
                            }

                            if (set.size() > 1) {
                                for (String i : set) {
                                    arr.add(get_ans(i, nn.indexOf(i) + inda, "DOCTOR"));
                                }
                            } else if (set.size() == 1) {
                                String ts = "";
                                for (String tts : set) {
                                    ts = tts;
                                }
                                if (!set_bad_doc.contains(ts)) {
                                    arr.add(get_ans(ts, nn.indexOf(ts) + inda, "DOCTOR"));
                                }
                            }
                        }
                    }
                    inda = indb;
                    break;
                }
            }
        }
        //------------13/9/64, F Iuchs.  ank
        if (n.length() < 30 && n.length() > 14) {
            boolean boa = true;
            for (int i = 0; i < 7; i++) {
                if (!intset.contains((int) n1[i]) && n1[i] != '/') {
                    boa = false;
                    break;
                }
                int inda1, indb1;
                for (inda1 = 6; inda1 < 13; inda1++) {
                    if (bengset.contains((int) n1[inda1]) && n1[inda1 + 1] == ' ' && bengset.contains((int) n1[inda1 + 2])) {
                        for (indb1 = inda1 + 2; indb1 < n.length(); indb1++) {
                            if (!engset.contains((int) n1[indb1])) {
                                break;
                            }
                        }
                        arr.add(get_ans(n.substring(inda1, indb1), inda1, "DOCTOR"));
                    }
                }
            }
        }
        //----------MICROSCOPIC:  (L Placek)
        if (n.length() < 30 && n.contains("MICROSCOPIC") && !n.contains("DR") && !n.contains("Dr") && !n.contains("dr")) {
            int inda2 = 0, indb2 = 0;
            for (inda2 = 11; inda2 < n.length() - 4; inda2++) {
                if (bengset.contains((int) n1[inda2]) && n1[inda2 + 1] == ' ' && bengset.contains((int) n1[inda2 + 2])) {
                    for (indb2 = inda2 + 2; indb2 < n.length(); indb2++) {
                        if (!engset.contains((int) n1[indb2])) {
                            break;
                        }
                    }
                    arr.add(get_ans(n.substring(inda2, indb2), inda2, "DOCTOR"));
                }
                if (bengset.contains((int) n1[inda2]) && n1[inda2 + 1] == '.' && n1[inda2 + 2] == ' ' && bengset.contains((int) n1[inda2 + 3])) {
                    for (indb2 = inda2 + 3; indb2 < n.length(); indb2++) {
                        if (!engset.contains((int) n1[indb2])) {
                            break;
                        }
                    }
                    arr.add(get_ans(n.substring(inda2, indb2), inda2, "DOCTOR"));
                }
            }

        }
        //----------Haematology registrar: WILBURN LUCAS MADGE
        if (n.length() > 30 && n.length() < 80 && n.contains("Haematology") && n.contains(":")) {
            int inda3, indb3;
            for (inda3 = n.indexOf(":"); inda3 < n.length(); inda3++) {
                if (bengset.contains((int) n1[inda3])) {
                    break;
                }
            }
            if (inda3 + 1 < n.length()) {
                for (indb3 = inda3 + 1; indb3 < n.length(); indb3++) {
                    if (!engset.contains((int) n1[indb3]) && n1[indb3] != ' ') {
                        break;
                    }
                }
                arr.add(get_ans(n.substring(inda3, indb3), inda3, "DOCTOR"));
            }
        }

        //----Result to Dr Delrossi / Celedon by Dr WHector (shown to MY / NL) at 12:10pm on 18/02/2017
        if (n.contains("(shown to") && n.indexOf("(shown to") + 20 < n.length()) {
            int ind_sh = n.indexOf("(shown to");
            for (int i = ind_sh + 9; i < ind_sh + 18; i++) {
                if (bengset.contains((int) n1[i]) && bengset.contains((int) n1[i + 1]) && !engset.contains((int) n1[i - 1]) && !bengset.contains((int) n1[i + 2])) {
                    String pps = n.substring(i, i + 2);
                    if (!pps.equals("MY") && !pps.equals("NL")) {
                        arr.add(get_ans(pps, i, "DOCTOR"));
                    }
                }
            }
        }
        //-----------bu EN
        if (n1[n.length() - 5] == 'b' && n1[n.length() - 4] == 'y' && n1[n.length() - 3] == ' ' && bengset.contains((int) n1[n.length() - 2]) && bengset.contains((int) n1[n.length() - 1])) {
            arr.add(get_ans(n.substring(n.length() - 2, n.length()), n.length() - 2, "DOCTOR"));
        }
        return arr;
    }

    public static String organization(String n, String file) {
        if (n.contains("for MSI proteins") && n.contains("(") && n.contains(")") && n.indexOf(")") - n.indexOf("(") > 12) {
            return (file + "\tORGANIZATION\t" + words + "\t" + words + "\t" + n);
        }
        if (n.contains("Source of material: ")) {
            return (file + "\tORGANIZATION\t" + words + "\t" + words + "\t" + n);
        }
        if (file.charAt(0) != 'f') {
            if (n.contains("HER SISH") || n.contains("HER2 SISH") || n.contains("HER-2 SISH")) {
                return (file + "\tORGANIZATION\t" + words + "\t" + words + "\t" + n);
            }
            if (n.contains("Immunostains for mismatch") || n.contains("Immunostains for MSI")) {
                return (file + "\tORGANIZATION\t" + words + "\t" + words + "\t" + n);
            }
        } else {
            if (words < 800 && n.contains("Distribution:") && !n.contains("NSW-CANCER-REGISTRY") && !n.contains("FILE-COPY") && n.length() > 14) {
                return (file + "\tORGANIZATION\t" + words + "\t" + words + "\t" + n);
            }
        }
        return ("nono");
    }

    public static String patient(String n) {
        if (words > 120) {
            return "nono";
        }
        if (n.length() < 2 || n.length() > 40) {
            return "nono";
        }
        if (!n.contains(", ")) {
            return "nono";
        }
        char n1[] = n.toCharArray();
        for (char i : n1) {
            if ((!engset.contains((int) i)) && i != ' ' && i != ',' && i != '-' && i != '\'') {
                return "nono";
            }
        }
        return get_ans(n.trim(), 0, "PATIENT");
    }

    public static String location(String n) {
        char n1[]=n.toCharArray();
        if(n.equals("JANBORWILL")) return(get_ans (n,0,"LOCATION-OTHER"));
        if (n.length() > 24 || n.length() < 6) {
            return "nono";
        }
        int ind = 0;
        char sp[] = {'P', 'O', 'B', 'O', 'X'};
    
        for (int i = 0; i < n.length(); i++) {
            if (n1[i] == sp[ind]) {
                ind++;
            }
            if (ind == 5) {
                break;
            }
        }
        if (ind == 5) {
            return(get_ans(n, 0, "LOCATION-OTHER"));
        }
        return "nono";
    }

    public static String patient1(String n, boolean bo) {
        if (n.equals("FacilityID")) {
            return ("nono");
        }
        if (n.length() < 2 || n.length() > 500) {
            return ("nono");
        }
        String n1 = n.trim();
        if (bo) {
            return (get_ans(n1, 0, "PATIENT"));
        }
        if (n1.equals("FirstName") || n1.equals("MiddleName") || n1.equals("LastName")) {
            return ("name");
        }

        if (words > 110 && words < 250 && n.length() > 5 && n.length() < 30) {
            boolean boa = true;
            char n2[] = n.toCharArray();
            for (char i : n2) {
                if (!bengset.contains((int) i) && i != ',' && i != ' ') {
                    boa = false;
                }
            }
            if (!n.contains(",")) {
                boa = false;
            }
            if (boa) {
                return get_ans(n.trim(), 0, "PATIENT");
            }
        }
        if (n.contains("(") && n.contains(")") && n.contains(", ")) {
            int w = n.indexOf("(") + 1;
            int w1 = n.indexOf(")");
            if (w1 - w < 8 || w1 - w > 13) {
                return "nono";
            }
            if (!bengset.contains((int) n.charAt(w + 2))) {
                return "nono";
            }
            for (int i = 0; i < 7; i++) {
                if (i == 2) {
                    continue;
                }
                if (!intset.contains((int) n.charAt(w + i))) {
                    return "nono";
                }
            }
            for (int i = w1 + 1; i < n.length(); i++) {
                if (engset.contains((int) n.charAt(i))) {
                    if (n.contains("HISTOPATHOLOGY REPORT:")) {
                        n = n.substring(0, n.length() - 22).trim();
                    }
                    if (n.length() - i > 3 && n.length() - i < 35) {
                        return get_ans(n.substring(i, n.length()).trim(), i, "PATIENT");
                    }
                }
            }
        }
        return "nono";
    }

    public static String street(String n, int ws) {
        if (words > ws) {
            return "nono";
        }
        if (ws == 320 && words < 175) {
            return "nono";
        }
        if (n.length() < 2) {
            return "nono";
        }
        if (n.length() > 30) {
            return "nono";
        }
        String fs[] = {"HISTOPATHOLOGY REPORT", "MSI SUPPLEMENTARY", "SEALS HISTOPATHOLOGY REPORT", "MSI IMMUNOHISTOCHEMISTRY"};
        for (String i : fs) {
            if (n.contains(i)) {
                return "nono";
            }
        }
        char n1[] = n.toCharArray();
        int ind = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n1[i] != ' ') {
                ind = i;
                break;
            }
        }
        if (!bengset.contains((int) n1[ind])) {
            return "nono";
        }
        for (char i : n1) {
            if (!engset.contains((int) i) && i != ' ') {
                return "nono";
            }
        }
        return get_ans(n.trim(), ind, "STREET");
    }

    /*public static String zip(String n) {
        if (words > 400) {
            return "nono";
        }
        n = n.trim();
        if (n.length() < 6) {
            return "nono";
        }
        char n1[] = n.toCharArray();
        if (n1[n.length() - 5] != ' ') {
            return "nono";
        }
        if (!bengset.contains((int) n1[0])) {
            return "nono";
        }
        for (int i = n.length() - 4; i < n.length(); i++) {
            if (!intset.contains((int) n1[i])) {
                return "nono";
            }
        }
        for (int i = 0; i < n.length() - 4; i++) {
            if (!engset.contains((int) n1[i]) && n1[i] != ' ') {
                return "nono";
            }
        }
        return get_ans(n.substring(n.length() - 4), n.length() - 4, "ZIP");
    }*/
    public static String phone(String n) {
        char n1[] = n.toCharArray();
        int ns[] = new int[n.length()];
        for (int i = 0; i < n.length(); i++) {
            ns[i] = intset.contains((int) n1[i]) ? 1 : (n1[i] == ' ' ? 2 : 0);
        }
        for (int i = 0; i <= n.length() - 9; i++) {
            int ind = 0;
            for (ind = 0; ind < 9; ind++) {
                if (ns[ind + i] != (ind == 4 ? 2 : 1)) {
                    break;
                }
            }
            if (ind == 9) {
                return get_ans(n.substring(i, i + 9), i, "PHONE");
            }
        }
        //-----(83829154)------
        n1 = n.toCharArray();
        for (int i = 0; i < n.length() - 10; i++) {
            boolean bo = true;
            /*if (n1[i] != '(') {
                continue;
            }
            if (n1[i + 9] != ')') {
                continue;
            }*/
            for (int j = i + 1; j < i + 9; j++) {
                if (!intset.contains((int) n1[j])) {
                    bo = false;
                }
            }
            
        }
        return "nono";
    }
}
