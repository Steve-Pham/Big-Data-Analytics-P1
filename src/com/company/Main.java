package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello World");
        final int totalData = 88162;
        int []chunks = {(int) (totalData*0.01), (int) (totalData*0.05),(int) (totalData*0.1), (int) (totalData*0.2), (int) (totalData*0.3), (int) (totalData*0.4),
                (int) (totalData*0.5), (int) (totalData*0.6), (int) (totalData*0.7), (int) (totalData*0.8), (int) (totalData*0.9), totalData};
        double []threshold = {0.01, 0.05, 0.1};

        APriori ap;
        PCY pc;

        for (int i = 0; i < threshold.length; i++) {
            for (int j = 0; j < chunks.length; j++) {
                ap = new APriori("retail.txt", chunks[j], threshold[i]);
                ap.secondPass();
            }
        }



        for (int i = 0; i < threshold.length; i++) {
            for (int j = 0; j < chunks.length; j++) {
                pc = new PCY("retail.txt", chunks[j], threshold[i]);
                pc.secondPass();
            }
        }
    }
}
