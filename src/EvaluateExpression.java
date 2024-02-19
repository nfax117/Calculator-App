
import java.util.Stack;

/**
 *
 * @author Nfax1
 */

public class EvaluateExpression {
    
    
    public static double evaluate(String expression) {
        
        // Use a stack to store operators and numbers
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        
        // Variable to track whether negative sign '-' should be considered as negative or subtraction
        boolean isNegative = true;
        
        // Variable to track count of open parentheses
        int openParenCount = 0;
        
        for(int i=0; i<expression.length(); i++) {
            
            char currChar = expression.charAt(i);
            
            // Check if current character is digit or decimal, if so push to numbers stack
            if(Character.isDigit(currChar) || (isNegative && currChar == '-') || currChar == '.') {
                StringBuilder numStr = new StringBuilder();
                
                // Handle unary minus
                if (isNegative && currChar == '-') {
                    numStr.append(currChar);
                    i++;
                    currChar = expression.charAt(i);
                }
                
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) { // Loop to get full integer
                    numStr.append(expression.charAt(i));
                    i++;
                }
                i--; // Need to move back one position since we're already pointing to the next character
                
                numbers.push(Double.parseDouble(numStr.toString()));
                
                // Reset isNegative flag
                isNegative = false;
            }
            
            // Check if current character is open parenthesis, if open, just need to push to operators stack
            else if(currChar == '(') {
                operators.push(currChar);
                openParenCount += 1;
                isNegative = true;
            }
            
            // Check if current character is closed parenthesis, if closed, need to solve expression within parenthesis
            else if(currChar == ')') {
                
                if (openParenCount == 0) {
                    throw new ArithmeticException("Invalid Parentheses");
                }
                                
                while (operators.peek() != '(') {
                    char operator = operators.pop();
                    double num2 = numbers.pop();
                    double num1 = numbers.pop();
                    double result = solve(operator, num1, num2);
                    System.out.println(num1 + " " + operator + " " + num2 + " is: " + result);
                    numbers.push(result);
                }
                
                operators.pop(); // Pop the opening parenthesis form stack
                openParenCount -= 1;
            }
            
            // Check if current character is an operator
            else if(currChar == '+' || currChar == '-' || currChar == '*' || currChar == '/') {

                // Loop while still operators to be applied to numbers and while the first operator has higher precedence
                while(!operators.isEmpty() && hasPrecedence(currChar, operators.peek())) {
                    char operator = operators.pop();
                    double num2 = numbers.pop();
                    double num1 = numbers.pop();
                    double result = solve(operator, num1, num2);
                    System.out.println(num1 + " " + operator + " " + num2 + " is: " + result);
                    numbers.push(result);
                }
               
                operators.push(currChar);
                
                // Set isNegative flag if next character is an operator
                isNegative = true;
            }
            
        }
        
        if (openParenCount != 0) {
            throw new ArithmeticException("Invalid Parentheses");
        }
        
        // Keep solving remaining sub expressions
        while(!operators.isEmpty()) {
            char operator = operators.pop();
            double num2 = numbers.pop();
            double num1 = numbers.pop();
            double result = solve(operator, num1, num2);
            System.out.println(num1 + " " + operator + " " + num2 + " is: " + result);
            numbers.push(result);
        }


        return numbers.pop(); // Final solution will be last element in numbers stack
        
    }
    
    
    // Function to solve sub expression (two numbers and one operator)
    private static double solve(char operator, double num1, double num2) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
        }
        return 0;
    }
    
    
    // Function that takes two operators and returns true is operator 1 has precedence over operator 2. False otherwise/
    private static boolean hasPrecedence(char oper1, char oper2) {
        
        if((oper1 == '*' || oper1 == '/') && (oper2 == '+' || oper2 == '-')) { // mult and div have higher precedence
            return true;
        }
        
        if (oper2 == '(' || oper2 == ')') { // Parenthesis always have higher precedence
            return false;
        }
        
        return true;
    }
    
}
