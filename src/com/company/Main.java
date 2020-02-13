package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello World");
        APriori test = new APriori("retail.txt");
        test.secondPass();
        PCY test1 = new PCY("retail.txt");
        test1.secondPass();
    }
}
