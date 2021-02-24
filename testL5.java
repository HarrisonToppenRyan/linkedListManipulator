public class testL5 {
    public static void main(String[] args) {
        IntegerLinkedList outside = new IntegerLinkedList();
        outside.insertBack(5);
        outside.insertFront(4);
        outside.insertFront(3);
        outside.insertFront(2);
        outside.insertFront(1);
        outside.removeAt(4);
        outside.print();
    }
}   
