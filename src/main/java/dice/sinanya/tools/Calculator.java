package dice.sinanya.tools;

import java.util.Collections;
import java.util.Stack;

/**
 * 算数表达式求值
 * 直接调用Calculator的类方法conversion()
 * 传入算数表达式，将返回一个浮点值结果
 * 如果计算过程错误，将返回一个NaN
 *
 * @author zhangxiaozhou
 */
public class Calculator {
    /**
     * 后缀式栈
     */
    private Stack<String> postfixStack = new Stack<>();
    /**
     * 运算符栈
     */
    private Stack<Character> opStack = new Stack<>();
    /**
     * 运用运算符ASCII码-40做索引的运算符优先级
     */
    private int[] operaPriority = new int[]{0, 3, 2, 1, -1, 1, 0, 2};

    public static double conversion(String expression) {
        double result;
        Calculator cal = new Calculator();
        try {
            expression = transform(expression);
            result = cal.calculate(expression);
        } catch (Exception e) {
            // 运算错误返回NaN
            return Double.NaN;
        }
        return result;
    }

    /**
     * 将表达式中负数的符号更改
     *
     * @param expression 例如-2+-1*(-3E-2)-(-1) 被转为 ~2+~1*(~3E~2)-(~1)
     * @return 返回修改后的字符串结果
     */
    private static String transform(String expression) {

        char[] arr = expression.toCharArray();
        char remainder = '~';
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if (i == 0) {
                    arr[i] = remainder;
                } else {
                    char c = arr[i - 1];
                    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == 'E' || c == 'e') {
                        arr[i] = remainder;
                    }
                }
            }
        }
        if (arr[0] == remainder || arr[1] == remainder) {
            arr[0] = '-';
            return "0" + new String(arr);
        } else {
            return new String(arr);
        }
    }

    /**
     * 按照给定的表达式计算
     *
     * @param expression 要计算的表达式例如:5+12*(3+5)/7
     * @return 计算后的结果
     */
    private double calculate(String expression) {
        Stack<String> resultStack = new Stack<>();
        prepare(expression);
        Collections.reverse(postfixStack);
        // 将后缀式栈反转
        String firstValue, secondValue, currentValue;
        // 参与计算的第一个值，第二个值和算术运算符
        while (!postfixStack.isEmpty()) {
            currentValue = postfixStack.pop();
            if (!isOperator(currentValue.charAt(0))) {
                // 如果不是运算符则存入操作数栈中
                currentValue = currentValue.replace("~", "-");
                resultStack.push(currentValue);
            } else {
                // 如果是运算符则从操作数栈中取两个值和该数值一起参与运算
                secondValue = resultStack.pop();
                firstValue = resultStack.pop();

                // 将负数标记符改为负号
                firstValue = firstValue.replace("~", "-");
                secondValue = secondValue.replace("~", "-");

                String tempResult = calculate(firstValue, secondValue, currentValue.charAt(0));
                resultStack.push(tempResult);
            }
        }
        return Double.valueOf(resultStack.pop());
    }

    /**
     * 数据准备阶段将表达式转换成为后缀式栈
     *
     * @param expression 待转换的表达式
     */
    private void prepare(String expression) {
        opStack.push(',');
        // 运算符放入栈底元素逗号，此符号优先级最低
        char[] arr = expression.toCharArray();
        int currentIndex = 0;
        // 当前字符的位置
        int count = 0;
        // 上次算术运算符到本次算术运算符的字符的长度便于或者之间的数值
        char currentOp, peekOp;
        // 当前操作符和栈顶操作符
        char comma = ',';
        for (int i = 0; i < arr.length; i++) {
            currentOp = arr[i];
            if (isOperator(currentOp)) {
                // 如果当前字符是运算符
                if (count > 0) {
                    postfixStack.push(new String(arr, currentIndex, count));
                    // 取两个运算符之间的数字
                }
                peekOp = opStack.peek();
                char openingParenthesis = '(';
                if (currentOp == ')') {
                    // 遇到反括号则将运算符栈中的元素移除到后缀式栈中直到遇到左括号
                    while (opStack.peek() != openingParenthesis) {
                        postfixStack.push(String.valueOf(opStack.pop()));
                    }
                    opStack.pop();
                } else {
                    while (currentOp != openingParenthesis && peekOp != comma && compare(currentOp, peekOp)) {
                        postfixStack.push(String.valueOf(opStack.pop()));
                        peekOp = opStack.peek();
                    }
                    opStack.push(currentOp);
                }
                count = 0;
                currentIndex = i + 1;
            } else {
                count++;
            }
        }

        boolean isLastChar = count > 1;
        boolean isLastCharButNotOperator = count == 1 && !isOperator(arr[currentIndex]);
        if (isLastChar || isLastCharButNotOperator) {
            // 最后一个字符不是括号或者其他运算符的则加入后缀式栈中
            postfixStack.push(new String(arr, currentIndex, count));
        }

        while (opStack.peek() != comma) {
            postfixStack.push(String.valueOf(opStack.pop()));
            // 将操作符栈中的剩余的元素添加到后缀式栈中
        }
    }

    /**
     * 判断是否为算术符号
     *
     * @param c 输入值
     * @return 是否
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    /**
     * 利用ASCII码-40做下标去算术符号优先级
     *
     * @param cur  比较运算符1
     * @param peek 比较运算符2
     * @return 是否左侧运算符优先
     */
    private boolean compare(char cur, char peek) {// 如果是peek优先级高于cur，返回true，默认都是peek优先级要低
        boolean result = false;
        int operaAscii = 40;
        if (operaPriority[(peek) - operaAscii] >= operaPriority[(cur) - operaAscii]) {
            result = true;
        }
        return result;
    }

    /**
     * 按照给定的算术运算符做计算
     *
     * @param firstValue  先运算的值
     * @param secondValue 后计算的值
     * @param currentOp   计算符号
     * @return 结果
     */
    private String calculate(String firstValue, String secondValue, char currentOp) {
        String result = "";
        switch (currentOp) {
            case '+':
                result = String.valueOf(ArithHelper.add(firstValue, secondValue));
                break;
            case '-':
                result = String.valueOf(ArithHelper.sub(firstValue, secondValue));
                break;
            case '*':
                result = String.valueOf(ArithHelper.mul(firstValue, secondValue));
                break;
            case '/':
                result = String.valueOf(ArithHelper.div(firstValue, secondValue));
                break;
            default:
                break;
        }
        return result;
    }
}