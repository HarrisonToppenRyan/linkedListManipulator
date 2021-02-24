/* 
Author: Harrison Toppen-Ryan
Description : Lab 5, CSCI 145
Date: Febuary 23rd, 2021
*/

public class IntegerNode {
    private int value;
    private IntegerNode next;
    // Constructor to create an empty node
    public IntegerNode() {} // Constructor to create a node with a specific value
    public IntegerNode(int val) {
        this.value = val;
    }// Getter
    public int getValue() {
        return value;
    }
    // Setter
    public void setValue(int val) {
        this.value = val;
    }// get the next node
    public IntegerNode getNext() {
        return next;
    }// set the next node
   
    public void setNext(IntegerNode n) {
        this.next= n;
    }
}