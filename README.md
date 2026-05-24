# Project: Calculator Java

## Code Metrics
* **Total Project LOC (Lines of Code):** 214 (153) lines.
* **Calculator.java LOC:** 188 (134) lines.
* **Start.java LOC:** 26 (19) lines.

## Static Code Analysis Report

### Calculator.java
* Calculator.java – 6 – Globally shared static state (static float finalResult) causes thread-safety issues.
* Calculator.java – 18 – The method name "ToString" violates Java naming conventions (should start with a lowercase letter).
* Calculator.java – 19 – Inefficient string concatenation ("" + ...) creates unnecessary objects in memory.
* Calculator.java – 24 – The method name "Run" violates Java naming conventions (should start with a lowercase letter).
* Calculator.java – 32 – Potential StringIndexOutOfBoundsException if the passed expression string is empty.
* Calculator.java – 34 – Magic number zero ('0 + expression') implicitly converted to string, creating redundant runtime objects.
* Calculator.java – 37 – Potential regex error; special characters like '+' and '*' inside Operations.ToString() are not escaped before being passed to split().
* Calculator.java – 41 – The loop skips checking the very last character of the string due to the "length() - 1" condition.
* Calculator.java – 42 – Code duplication and complex logical checks for operators inside the loop instead of a shared helper validation function.
* Calculator.java – 63 – Bad practice of catching a generic Exception instead of a specific NumberFormatException.
* Calculator.java – 74 – The method name "Calculate" violates Java naming conventions (should start with a lowercase letter).
* Calculator.java – 81 – Dead code / Unnecessary initialization of local variable "result" combined with an immediate "+=" override.
* Calculator.java – 98 – Missing division-by-zero validation logic, allowing uncontrolled Infinity or NaN results.
* Calculator.java – 110 – Heavy code duplication across redundant recursive paths for individual arithmetic handling blocks.
* Calculator.java – 111 – Redundant use of "+=" operator on a freshly initialized variable across all operation blocks.
* Calculator.java – 118 – Unbounded recursion increases the risk of a StackOverflowError on long expressions.
* Calculator.java – 137 – Flawed logic for mixed addition/subtraction index checking, breaking standard left-to-right math evaluation rules.
* Calculator.java – 162 – Redundant conditional blocks that copy-paste array mutation logic unnecessarily.
* 
### Start.java
* Start.java – 6 – The local variable "Expression" violates Java naming conventions (should start with a lowercase letter).
* Start.java – 12 – Resource leak / Performance issue. The Scanner object is reinstantiated inside the while-loop on every iteration instead of being created once outside the loop.
* Start.java – 16 – Potential NullPointerException if the input stream closes unexpectedly and "Expression" becomes null before the .equals() check.
* Start.java – 17 – Closing the Scanner wrapped around System.in closes the underlying input stream, preventing any further console input if the loop logic were to change.
