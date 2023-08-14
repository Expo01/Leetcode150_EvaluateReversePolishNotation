import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(new Solution().evalRPN(tokens));
    }
}


class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st2 = new Stack<>();

        for(String s : tokens){
            if(s.equals("+") || s.equals("*") || s.equals("/") || s.equals("-")){
                int b = st2.pop();
                int a = st2.pop();
                int res = cal(a, b, s.charAt(0));
                st2.push(res);

            } else {
                st2.push(Integer.parseInt(s));
            }
        }
        return st2.pop();
    }
    private int cal(int a, int b, char oprtr){
        if(oprtr == '+'){
            return a + b;
        } else if(oprtr == '-'){
            return a - b;
        } else if(oprtr == '*'){
            return a * b;
        } else {
            return a / b;
        }
    }
}


//solution assuming no invalid inputs such as ( or a or some other non-int or operator strings
// right idea, but better solved with a stack so no storing variable needed for pior index or a count
//class Solution {
//    public int evalRPN(String[] tokens) {
//        int ans = 0;
//        int storedIndex = 0;
//        int count = 0;
//
//        for (int i = 0; i < tokens.length; i++) {
//            if (tokens[i] != "+" && tokens[i] != "-" && tokens[i] != "*" && tokens[i] != "/") {
//                count++;
//            } else if (count >= 2) {
//                if (tokens[i] == "+") {
//                    ans = Integer.parseInt(tokens[i - 2]) + Integer.parseInt(tokens[i - 1]);
//                } else if (tokens[i] == "-") {
//                    ans = Integer.parseInt(tokens[i - 2]) - Integer.parseInt(tokens[i - 1]);
//                } else if (tokens[i] == "*") {
//                    ans = Integer.parseInt(tokens[i - 2]) * Integer.parseInt(tokens[i - 1]);
//                } else {
//                    ans = Integer.parseInt(tokens[i - 2]) / Integer.parseInt(tokens[i - 1]);
//                }
//                count = 1;
//                if (i >= 3) { // suppose array is 1,2,+. count is >= 2, but i currently = 2. 2-3 = negative index
//                    storedIndex = i - 3;
//                }
//            } else {
//                if (tokens[i] == "+" && i> 1) {
//                    ans = Integer.parseInt(tokens[storedIndex]) + Integer.parseInt(tokens[i - 1]);
//                    storedIndex--;
//                } else if (tokens[i] == "-" && i> 1) {
//                    ans = Integer.parseInt(tokens[storedIndex]) - Integer.parseInt(tokens[i - 1]);
//                    storedIndex--;
//                } else if (tokens[i] == "*" && i> 1) {
//                    ans = Integer.parseInt(tokens[storedIndex]) * Integer.parseInt(tokens[i - 1]);
//                    storedIndex--;
//                } else if (tokens[i] == "/" && i> 1) {
//                    ans = Integer.parseInt(tokens[storedIndex]) / Integer.parseInt(tokens[i - 1]);
//                    storedIndex--;
//                }
//                 // needs to be encapsulated somehow.  is decremented to -1 on first int val and second?
//                // so when stored index called in condition in first block, its -1
//            }
//        }
//        return ans;
//    }
//}


// OPTION 2: no re-indexing and just store variables. say operator found,
// [int at index 2 : operator at index 4 : int at index 3] =  'ans' is stored
// we also need to store the int index prior to the first operand used in the equation which in this
// case is index 1. we also need an 'int' counter which starts at 0. using first/second iteration as ex
// after first '+' found, count will be 4 which is > 2. equation performed and count reset to 1 then we  // get to -11 where count = 2 so we perform operation using ans and index preceding the operator = -132
// but on the third pass, '/' directly follows the ans so we must retrieve the index but when we reassign
// the stored value, in if condition where count >= 2, we assign stored =[i - 3] but in this
// condition, stored--
// only problem now is how to convert a char to an operator? DON'T HAVE TO. say if array[i] = "+"
// then ans = array [i--] + array[i-]