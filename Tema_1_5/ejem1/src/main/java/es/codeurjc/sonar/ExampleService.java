package es.codeurjc.sonar;

// 1. Unnecessary Imports
// CODE SMELL: Unnecessary imports should be removed

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExampleService {

    // 2. Code Duplication
    // CODE SMELL: Immediately return this expression instead of assigning it to the temporary variable "sum".

    public int task1(int a, int b) {
        return a + b;
    }

    public int task2(int a, int b) {
        int sum = a + b;
        return sum;
    }

    // 3. Code commented out + unreachable code
    // CODE SMELL: Replace this use of System.out by a logger.
    // CODE SMELL: This block of commented-out lines of code should be removed.

    public void println(String x) {
        if (getClass() == ExampleService.class) {
            System.out.println(String.valueOf(x));
        } else {
            synchronized (this) {
                System.out.println(x);
                // newLine();
            }
        }
    }

    // 4. String literals duplicated
    // CODE SMELL: Define a constant instead of duplicating this literal "action1" 3 times

    public void run() {
        println("action1");
        println("action1");
        println("action1");
    }

    // 5. Cognitive Complexity of functions should not be too high
    // CODE SMELL: Refactor the code to remove this label and the need for it.

    int sumOfPrimes(int max) { // +1
        int total = 0;
        OUT: for (int i = 1; i <= max; ++i) { // +1
            for (int j = 2; j < i; ++j) { // +1
                if (i % j == 0) { // +1
                    continue OUT;
                }
            }
            total += i;
        }
        return total;
    }

    // 6. Unused elements should be removed
    // CODE SMELL: Remove this useless assignment to local variable "value".
    // CODE SMELL: Remove this unused "c" local variable.
    // CODE SMELL: Remove this unused method parameter "b".
    // CODE SMELL: Remove this unused "foo" private field.

    private int foo = 42; // private field not used

    public int compute(int a, int b) { // b argument not used
        int c = 10; // local variable not used
        return a * 42;
    }

    public int run2() {
        int value = 10; // assignment not used
        value = compute(2, 5);
        return value;
    }

    // 7. Raw types should not be used
    // CODE SMELL: Provide the parametrized type for this generic

    List myList;
    Set mySet;

    // 8. Generic exceptions should never be thrown
    // BUG: Strings and Boxed types should be compared using "equals()".
    // CODE SMELL: Define and throw a dedicated exception instead of using a generic one.

    public void foo(String bar) throws Exception {
        if (bar.isEmpty()) {
            throw new Exception();
        }
        if (bar == "jello") {
            throw new Exception();
        }
        System.out.println("This is bar: " + bar);
    }

}
