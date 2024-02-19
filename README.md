# Calculator-App
This is a simple calculator application built using Java and Abstract Windows Toolkit (AWT). It provides basic arithmetic operations such as addition, subtraction, multiplication, and division. The interface is designed with buttons for numeric input and operators, along with functionalities to delete the last digit, clear the entire input, and perform decimal point operations.

Features
- Addition
- Subtraction
- Multiplication
- Division
- Decimal point input
- Clearing the last input
- Clearing the entire input
- Parentheses
Usage
- Run the application.
- Use the numeric buttons to input numbers.
- Use the operator buttons to perform arithmetic operations.
- Use the "AC" button to clear the entire input.
- Use the "DEL" button to delete the last digit.
- Use the "ANS" button to display the result of the operation.


The `EvaluateExpression.java` file contains a class `EvaluateExpression` with a static method `evaluate(String expression)` used to evaluate arithmetic expressions. Here's a breakdown of the code:

1. **Class `EvaluateExpression`**: This class contains methods for evaluating arithmetic expressions.

2. **Method `evaluate(String expression)`**: This method takes a string `expression` as input, which represents the arithmetic expression to be evaluated. It returns a `double` value, which is the result of evaluating the expression.

3. **Stacks for Numbers and Operators**: Two stacks are used to store numbers (`Stack<Double> numbers`) and operators (`Stack<Character> operators`) encountered in the expression.

4. **Handling Unary Minus**: The method handles unary minus by checking if the '-' character is preceded by another operator or at the beginning of the expression.

5. **Looping Through the Expression**: The method iterates through each character of the expression.

6. **Processing Digits and Decimals**: If the current character is a digit or a decimal point, it extracts the entire number (including decimals) and pushes it onto the numbers stack.

7. **Handling Parentheses**: If the current character is an open parenthesis '(', it pushes it onto the operators stack and updates the `openParenCount` variable. If it's a closing parenthesis ')', it pops operators and numbers from the stacks until an open parenthesis is encountered, solving the sub-expression within the parentheses.

8. **Processing Operators**: If the current character is an operator (+, -, *, /), it compares its precedence with the top operator on the stack. If it has higher precedence, it solves the sub-expression using the top operator and the last two numbers on the numbers stack.

9. **Solving Sub-Expressions**: After processing all characters, the method continues solving any remaining sub-expressions by popping operators and numbers from the stacks.

10. **Handling Exceptions**: The method throws exceptions for invalid expressions, such as division by zero or mismatched parentheses.

11. **Helper Methods**: The class includes helper methods `solve(char operator, double num1, double num2)` to perform arithmetic operations and `hasPrecedence(char oper1, char oper2)` to check operator precedence.

Overall, this class provides functionality to evaluate arithmetic expressions efficiently by utilizing stacks to handle operators and numbers.
