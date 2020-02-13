package com.company;

import java.util.*;
import java.util.HashMap;
import java.io.*;

public class PCY {

    //declaring constructors
    private String fileName;//data file that gets passed
    private HashMap<Integer, Integer> count_items; //hashtable to count items
    //private HashMap<Integer, Integer> count_pairs; //hashtable to count frequent pairs
    private int threshold;
    private HashMap<String, Integer> frequentPairs;//hashtable to store  frequent pairs
    private HashMap<Integer, Integer> buckets;//hashtable to store bucket values
    final int bucketSize = 88200; //defines bucket size
    private BitSet bitBucket;

    //initialize with data file
    public PCY (String fileName) {
        this.fileName = fileName;
        this.threshold = 881;
        this.count_items = new HashMap<Integer, Integer>();
        this.frequentPairs = new HashMap<String, Integer>();
        this.buckets = new HashMap<Integer, Integer>();
    }

    //setup threshold
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    //second pass of pcy
    public void secondPass() {
        this.firstPass();

        String []line;
        String currentLine = "";
        FileReader retail = null;
        BufferedReader reader = null;
        ArrayList<Integer> currentKeys = new ArrayList<Integer>();

        try {
            retail = new FileReader(this.fileName);//allocates data file
            reader = new BufferedReader(retail);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((currentLine = reader.readLine()) != null) {
                line = currentLine.split("\\s+");

                for (String s: line) {//goes through array of individual string for each line
                    Integer key = Integer.parseInt(s);//changes to integer
                    if (!currentKeys.contains(key) && this.count_items.containsKey(key)) {
                        currentKeys.add(key);//adds unique keys to currentKeys array
                    }

                }
                //pairs
                int length = currentKeys.size();//size of the dynamic array
                for (int i = 0; i < length; i++) {
                    for (int j = i+1; j < length; j++) {
                        String pair;
                        int currentI = currentKeys.get(i);
                        int currentJ = currentKeys.get(j);

                        //execute hash function
                        int val = this.hashFunction(currentI, currentJ);

                        if (!this.bitBucket.get(val)){
                            continue;
                        }

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
            reader.close();//closes file
        } catch(IOException e) {
            e.printStackTrace();
        }
        this.findFrequentPairs();
        System.out.println(this.frequentPairs.size());
    }

    //function to read all instances of each item as well as the frequent pairs and assign to buckets
    public void firstPass() {
        String []line;
        String currentLine = "";
        FileReader retail = null;
        BufferedReader reader = null;
        System.out.println(new File(this.fileName).getAbsolutePath() + this.fileName);//outputs path to data file
        ArrayList<Integer> currentKeys = new ArrayList<Integer>();

        try {
            retail = new FileReader(this.fileName);//allocates data file
            reader = new BufferedReader(retail);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((currentLine = reader.readLine()) != null) {
                line = currentLine.split("\\s+");

                for (String s: line) {//goes through array of individual string for each line
                    Integer key = Integer.parseInt(s);//changes to integer
                    if (this.count_items.containsKey(key)) {
                        this.count_items.put(key, this.count_items.get(key) + 1);//updates hashtable
                    } else {
                        this.count_items.put(key, 1);//adds key and value if not already there
                }
                    if (!currentKeys.contains(key)) {
                        currentKeys.add(key);//adds unique keys to currentKeys array
                    }

                }
                //pairs
                int length = currentKeys.size();//size of the dynamic array
                for (int i = 0; i < length; i++) {
                    for (int j = i+1; j < length; j++) {
                        String pair;
                        int currentI = currentKeys.get(i);
                        int currentJ = currentKeys.get(j);

                        //execute hash function
                        int val = this.hashFunction(currentI, currentJ);

                        if ( !this.buckets.containsKey(val)){
                            this.buckets.put(val,1); //updates hashtable
                        }
                        else {
                            this.buckets.put(val, this.buckets.get(val)+1);
                        }

                    }
                }
                currentKeys.clear();

            }
            reader.close();//closes file
        } catch(IOException e) {
            e.printStackTrace();
        }

        //bitset function
        this.bitBucket = this.bitVector();

        //clear bucket hashmap
        this.buckets.clear();

        this.findFrequentValues();//seperates frequent items from the rest
        System.out.println(this.count_items.size());//outputs number of items that appear >= threshold

    }

/*---------functions------------------------------------------------------------------------------------------*/
    //function to remove items from hashtable that are <threshold
    public void findFrequentValues() {
        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>(this.count_items);
        for (Integer key: temp.keySet()) {
            if (this.count_items.get(key) < this.threshold) {
                this.count_items.remove(key);
            }
        }
    }

    //function to remove nonfrequent pairs from others
    public void findFrequentPairs() {
        HashMap<String, Integer> temp = new HashMap<String, Integer>(this.frequentPairs);
        for (String key: temp.keySet()) {
            if (this.frequentPairs.get(key) < this.threshold) {
                this.frequentPairs.remove(key);
            }
        }
    }
    //returns bucket key value
    public int hashFunction (int x, int y) {
        return (x + y)%this.bucketSize;
    }

    //returns bitset
    public BitSet bitVector () {
        BitSet temp = new BitSet(this.bucketSize);
        for (Integer key: this.buckets.keySet()){
            if (this.buckets.get(key) >= this.threshold){
                temp.set(key);
            }
        }
        return temp;
    }



}

