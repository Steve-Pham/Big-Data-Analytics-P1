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

    }
}
