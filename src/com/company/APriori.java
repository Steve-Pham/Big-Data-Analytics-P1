package com.company;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class APriori {

    private int threshold;
    private String fileName;
    private HashMap<Integer, Integer> count;

    public APriori(String fileName) {
        this.fileName = fileName;
        this.threshold = 100;
        this.count = new HashMap<Integer, Integer>();
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void firstPass() {
        String []line;
        String currentLine = "";
        FileReader retail = null;
        BufferedReader reader = null;
        System.out.println(new File(this.fileName).getAbsolutePath() + this.fileName);
        try {

            retail = new FileReader(this.fileName);
            reader = new BufferedReader(retail);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((currentLine = reader.readLine()) != null) {
                line = currentLine.split("\\s+");
                for (String s: line) {
                    Integer key = Integer.parseInt(s);
                    if (count.containsKey(key)) {
                        count.put(key, count.get(key) + 1);
                    } else {
                        count.put(key, 1);
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        /*
        for (Integer key: count.keySet()) {
            System.out.println(key + " " + count.get(key));
        }

         */

    }
}
