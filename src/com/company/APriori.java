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
                    System.out.println(s);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
