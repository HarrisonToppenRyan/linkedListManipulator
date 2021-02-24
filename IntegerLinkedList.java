/* 
Author: Harrison Toppen
Description : Lab 5, CSCI 145
Date: Febuary 23rd, 2021
*/

public class IntegerLinkedList {
    private IntegerNode head;
    // if the list is empty 
    public boolean isEmpty() {
        return head == null;
    }
     // inserting front node
    public void insertFront(int val) {
        // creating new node and setting into the head of the linked list 
        IntegerNode node = new IntegerNode(val);
        node.setNext(head);
        head = node;
    }
    // inseting back node
    public void insertBack(int val) {
        // creatintg new node and inseting into the tail of the linked lsit 
        IntegerNode node = new IntegerNode(val);
        // if the list is empty, make it the head
        if(isEmpty()) {
            head = node;
        }
        // if not, inset it at the very end
        else {
            IntegerNode cur = head;
            while(cur.getNext() != null) {
                cur = cur.getNext();
            //update cur to point to its successor
            }  
            // set cur's successor to node 
            cur = node;
        }
    }
    // printing the linked list method 
    public void print() {
        IntegerNode cur = head;
        while(cur!= null) {
            System.out.printf("%d", cur.getValue());
            cur = cur.getNext();
            //update cur to point to its successor
            System.out.println();
        }
    }
    // get function 
    public int get(int index) {
        // your code here
        // loop though the index and return a value if of cur if cur is not null
        IntegerNode cur = head;
        for (int i = 0; i<index; i++) {
            if (cur == null) {
                System.out.println("Error");
            }
            cur = cur.getNext();
        }
        return cur.getValue();
    }
    // remove front of linked list 
    public void removeFront() {
        // your code here
        // if head is not null, make the node next to head the new head
        if (head != null) {
            head = head.getNext();
        }
    }
    // remove back of linked list 
    public void removeBack() {
        // your code here
        // if the head is not null and head's sucessor is not null, make head null
        // else move down the list until head's sucessor is null
        IntegerNode cur = head;
        if (head != null) {
            if (head.getNext() == null) {
                head = null;
            }
            else {
                IntegerNode next = head;
                while (next.getNext() != null) {
                   next = next.getNext();
                } 
            }
           // set the second to last node to null, removing the last node 
            cur.setNext(null);
        }
    }
    // removing a certaain indect method 
    public void removeAt(int index) {
        // if cur is null or index is less than zero, thow and out of bounds exception 
        IntegerNode cur = head; 
        if (index<0 || cur == null) {
            throw new IndexOutOfBoundsException();
        }
        // loop thought the list's index and set current to it's sucessor
        for (int i = 0; i < index - 1; i++) {
            cur = cur.getNext();
        }
        // create a new node to two nodes down and set current to that node, removing the specified index
        IntegerNode newNode = cur.getNext().getNext();
        cur.setNext(newNode);

    }
    
}
