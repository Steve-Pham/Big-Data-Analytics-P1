package com.company;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class APriori {

    private int threshold;
    private String fileName;
    private HashMap<Integer, Integer> count;
    private HashMap<String, Integer> frequentPairs;

    public APriori(String fileName) {
        this.fileName = fileName;
        this.threshold = 881;
        this.count = new HashMap<Integer, Integer>();
        this.frequentPairs = new HashMap<String, Integer>();
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void secondPass() {
        this.firstPass();
        String []line;
        String currentLine = "";
        FileReader retail = null;
        BufferedReader reader = null;
        ArrayList<Integer> currentKeys = new ArrayList<Integer>();

        try {
            retail = new FileReader(this.fileName);
            reader = new BufferedReader(retail);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while((currentLine = reader.readLine()) != null) {
                line = currentLine.split("\\s+");
                for (String s: line) {
                    int key = Integer.parseInt(s);
                        if (this.count.containsKey(key) && !currentKeys.contains(key)) {
                            currentKeys.add(key);
                        }
                }
                int length = currentKeys.size();
                for (int i = 0; i < length; i++) {
                    for (int j = i+1; j < length; j++) {
                        String pair;
                        int currentI = currentKeys.get(i);
                        int currentJ = currentKeys.get(j);

                        // Use lower number first, then higher number
                        // Concatenate numbers to get key
                        if (currentI < currentJ) {
                            pair = Integer.toString(currentKeys.get(i)) + Integer.toString(currentKeys.get(j));
                        } else {
                            pair = Integer.toString(currentKeys.get(j)) + Integer.toString(currentKeys.get(i));
                        }

                        if (this.frequentPairs.containsKey(pair)) {
                            this.frequentPairs.put(pair, this.frequentPairs.get(pair) + 1);
                        } else {
                            this.frequentPairs.put(pair, 1);
                        }
                    }
                }
                currentKeys.clear();
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        this.findFrequentPairs();
        System.out.println(this.frequentPairs.size());

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
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        this.findFrequentValues();
        System.out.println(this.count.size());
    }

    public void findFrequentPairs() {
        HashMap<String, Integer> temp = new HashMap<String, Integer>(this.frequentPairs);
        for (String key: temp.keySet()) {
            if (this.frequentPairs.get(key) < this.threshold) {
                this.frequentPairs.remove(key);
            }
        }
    }

    public void findFrequentValues() {
        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>(this.count);
        for (Integer key: temp.keySet()) {
            if (this.count.get(key) < this.threshold) {
                this.count.remove(key);
            }
        }
    }
}
