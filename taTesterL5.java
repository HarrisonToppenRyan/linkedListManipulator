/* Written by Paul Houser
 *
 * test script to grade WWU CSCI 145 lab 5, 'integer linked list'
 * last updated Spring 2020
 *
 */

public class taTesterL5 {
  /* interface to implement pseudo function pointers */
  interface FunctionCall {
    void func();
  }


  /* array of functions */
  private static FunctionCall[] functions = new FunctionCall[] {
    new FunctionCall() { public void func() { testIsEmpty(); } },
    new FunctionCall() { public void func() { testInsertFront(); } },
    new FunctionCall() { public void func() { testInsertBack(); } },
    new FunctionCall() { public void func() { testRemoveFront(); } },
    new FunctionCall() { public void func() { testRemoveBack(); } },
    new FunctionCall() { public void func() { testRemoveAt(); } },
    new FunctionCall() { public void func() { testPrint(); } }
  };


  /* call from the array of functions */
  public static void func(int index) {
    functions[index].func();
  }


  /* main loop running each test within a try catch block */
  public static void main(String[] args) {
    for ( int i = 0; i < functions.length; i++ ) {
      try {
        func(i);
      } catch ( Exception e ) {
        System.out.println(e);
      } finally {
        System.out.println();
      }
    }
  }


  /* NOTE:
   * The first 3 removeAt test's all rely on the fact that either 0, 1 or 2
   * removals happened before them.
   *
   * If you add tests, add them after the ones that are between the dashed lines.
   */
  public static void testRemoveAt() {
    print("Testing removeAt()");
    print("This test relies on proper implementation of insertBack() and get()");

    //------ begining of the ones that need to be in order ---------------------
    IntegerLinkedList l = new IntegerLinkedList();
    int[] vals = {0, 1, 2, 3, 4, 5};
    int size = vals.length;

    /* populate the linked list */
    for ( int i = 0; i < vals.length; i++ ) {
      l.insertBack(i);
    }

    /* removing at the end of the linked list */
    l.removeAt(vals.length - 1);
    size--;
    /* check contents of linked list
     * parameters passed to compare:
     *  ridx = -1, ensures the if statement will never evaluate to true
     *  offset = 0, because there are no indices after what was removed
     */
    boolean passed = compare(vals, l, -1, size, 0, 1);
    if ( passed ) print("\tPassed removeAt() test 1");

    /* removing in the middle of the linked list */
    int rIdx = 3;
    int offset = 0;
    l.removeAt(rIdx);
    size--;
    passed = compare(vals, l, rIdx, size, offset, 2);
    if ( passed ) print("\tPassed removeAt() test 2");

    /* removing at the beginning of the linked list */
    l.removeAt(0);
    size--;
    offset = 1; // because we removed at 0
    passed = compare(vals, l, rIdx, size, offset, 3);
    if ( passed ) print("\tPassed removeAt() test 3");
    // -------- End of inorder tests -------------------------------------------
    
    /* removeAt() with negative index, should be error */
    print("");
    print("(1) Testing removeAt() with an invalid index, should be error:");
    l = new IntegerLinkedList();
    for ( int i = 0; i < vals.length; i++ ) {
      l.insertBack(i);
    }
    l.removeAt(-1);

    /* removeAt() with out of bounds index, should be error */
    print("(2) Testing removeAt() with an invalid index, should be error:");
    l.removeAt(vals.length+1);
  }


  public static boolean compare(int[] vals, IntegerLinkedList l,int rIdx, int size, int offset, int test) {
    boolean passed = true;
    for ( int i = 0; i < size; i++ ) {
      if ( i == rIdx - offset ) offset++;
      if ( vals[i+offset] != l.get(i) ) {
        passed = false;
        print("\tFailed removeAt() test " + test);
        break;
      }
    }
    return passed;
  }



  public static void testRemoveFront() {
    print("Testing removeFront()");
    print("This test relies on proper implementation if insertBack() and get()");

    IntegerLinkedList l = new IntegerLinkedList();
    int[] vals = {0, 1, 2, 3, 4, 5};

    /* populate the linked list */
    for ( int i = 0; i < vals.length; i++ ) {
      l.insertBack(i);
    }

    /* removeFront(), then ensure linked list reflects change */
    boolean passed = true;
    for ( int i = 1; i < vals.length; i++ ) {
      l.removeFront();
      for ( int j = i; j < vals.length; j++ ) {
        if ( vals[j] != l.get(j-i) ) {
          passed = false;
        }
      }
    }
    if ( passed ) print("\tPassed removeFront()");
    else print("\tFailed removeFront()");
  }


  public static void testRemoveBack() {
    print("Testing removeBack()");
    print("This test relies on proper implementation of insertBack() and get()");

    IntegerLinkedList l = new IntegerLinkedList();
    int[] vals = {0, 1, 2, 3, 4, 5};
    int size = vals.length;

    /* populate the linked list */
    for ( int i = 0; i < vals.length; i++ ) {
      l.insertBack(i);
    }

    /* removeBack(), then ensure linked list reflects change */
    /* repeated until linked list is empty */
    boolean passed = true;
    for ( int i = 0; i < vals.length; i++) {
      l.removeBack();
      size--;
      for ( int j = 0; j < size; j++ ) {
        if ( vals[j] != l.get(j) ) {
          passed = false;
        }
      }
    }
    if ( passed ) print("\tPassed removeBack()");
    else print("\tFailed removeBack()");
  }


  public static void testPrint() {
    print("VISUAL VERIFICATION FOR PRINT");
    print("Testing print()");
    print("This test relies on proper implementation of insertBack()");

    IntegerLinkedList l = new IntegerLinkedList();
    int[] vals = {0, 1, 2, 3, 4, 5};
    StringBuilder expected = new StringBuilder();
    /* populate the expected output while populating the linked list */
    for ( int i = 0; i < vals.length; i++ ) {
      expected.append(i);
      l.insertBack(vals[i]);
    }
    print("\tManually verify that the two outputs are identical:\n");
    print("\tExpected: " + expected.toString());
    print("\tActual:   ", 1); // the 1 tells the print function not to add a newline
    l.print();
  }


  public static void testInsertBack() {
    print("Testing insertBack()");
    print("This test relies on proper implementation of get().");

    /* test with single element linked list */
    IntegerLinkedList l = new IntegerLinkedList();
    l.insertBack(0);
    if ( 0 != l.get(0) ) {
      print("\tFailed insertBack() test 1");
    } else {
      print("\tPassed insertBack() test 1");
    }

    /* test with multi element linked list */
    l = new IntegerLinkedList();
    int[] vals = {0, 1, 2, 3, 4, 5};
    for ( int i = 0; i < vals.length; i++ ) {
      l.insertBack(vals[i]);
    }
    boolean passed = true;
    for ( int i = 0; i < vals.length; i++ ) {
      int actual = l.get(i);
      int expected = vals[i];
      if ( actual != expected ) {
        print("\tFailed insertBack() test 2");
        passed = false;
        break;
      }
    }
    if ( passed ) print("\tPassed insertBack() test 2");
  }


  public static void testInsertFront() {
    print("Testing insertFront()");
    print("This test relies on proper implementation of get().");

    /* single element test */
    IntegerLinkedList l = new IntegerLinkedList();
    l.insertFront(0);
    if ( 0 != l.get(0) ) {
      print("\tFailed insertFront() test 1");
    } else {
      print("\tPassed insertFront() test 1");
    }

    /* multi element test */
    l = new IntegerLinkedList();
    int[] vals = {0, 1, 2, 3, 4, 5};
    for ( int i = 0; i < vals.length; i++ ) {
      l.insertFront(vals[i]);
    }
    boolean passed = true;
    for ( int i = 0; i < vals.length; i++ ) {
      int actual = l.get(i);
      int expected = vals[-i + vals.length - 1]; // loop through array in reverse
      if ( actual != expected ) {
        print("\tFailed insertFront() test 2");
        passed = false;
        break;
      }
    }
    if ( passed ) print("\tPassed insertFront() test 2");
  }


  /* this ones self explanatory */
  public static void testIsEmpty() {
    System.out.println("Testing isEmpty()");

    IntegerLinkedList l = new IntegerLinkedList();
    if ( !l.isEmpty() ) {
      System.out.println("\tFailed isEmpty");
      return;
    }
    System.out.println("\tPassed isEmpty()");
  }

  /* saves you from typing 'System.out.print/ln/f' all the time
   * single argument of type String uses println(), a second arg
   * of type Integer uses print(). */
  private static void print(String str, Integer... mode) {
    Integer m = mode.length > 0 ? mode[0] : 0;
    if ( m == 0 ) System.out.println(str);
    else System.out.print(str);
  }
}