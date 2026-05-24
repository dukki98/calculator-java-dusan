# Project: Calculator Java

## Code Metrics
* **Total Project LOC (Lines of Code):** 153 lines.
* **Calculator.java LOC:** 134 lines.
* **Start.java LOC:** 19 lines.

## Static Code Analysis Report

### Calculator.java
* Calculator.java – 6 – Globally shared static state (static float finalResult) causes thread-safety issues.
* Calculator.java – 18 – The method name "ToString" violates Java naming conventions (should start with a lowercase letter).
* Calculator.java – 19 – Inefficient string concatenation ("" + ...) creates unnecessary objects in memory.
* Calculator.java – 24 – The method name "Run" violates Java naming conventions (should start with a lowercase letter).
* Calculator.java – 33 – Potential StringIndexOutOfBoundsException if the passed expression string is empty.
* Calculator.java – 39 – Potential regex error; special characters like '+' and '*' are not escaped inside the split() method.
* Calculator.java – 43 – The loop skips checking the last character of the string due to the "length() - 1" condition.
* Calculator.java – 44 – Code duplication and complex logical checks for operators instead of using a helper function.
* Calculator.java – 63 – Bad practice of catching a generic Exception instead of a specific NumberFormatException.
* Calculator.java – 70 – The method name "Calculate" violates Java naming conventions (should start with a lowercase letter).
* Calculator.java – 77 – Unnecessary initialization and use of the "+=" operator on the local variable "result".
* Calculator.java – 95 – Missing division-by-zero check, which can cause uncontrolled Infinity or NaN values.
* Calculator.java – 113 – Heavy code duplication across redundant recursive calls for each individual arithmetic operation.

### Start.java
* Start.java – 6 – The local variable "Expression" violates Java naming conventions (should start with a lowercase letter).
* Start.java – 12 – Resource leak / Performance issue. The Scanner object is reinstantiated inside the while-loop on every iteration instead of being created once outside the loop.
* Start.java – 16 – Potential NullPointerException if the input stream closes unexpectedly and "Expression" becomes null before the .equals() check.
* Start.java – 17 – Closing the Scanner wrapped around System.in closes the underlying input stream, preventing any further console input if the loop logic were to change.
