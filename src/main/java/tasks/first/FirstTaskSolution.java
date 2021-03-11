package tasks.first;

import java.util.ArrayDeque;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        String res = startIndex + ",";
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        boolean[] booleans = new boolean[adjacencyMatrix.length];
        booleans[startIndex] = true;
        deque.offerLast(startIndex);
        for (int i = 0; i < adjacencyMatrix.length && !deque.isEmpty(); i++) {
            int num = deque.pollFirst();
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[num][j] && !booleans[j]) {
                    deque.offerLast(j);
                    booleans[j] = true;
                    res = res.concat(j + ",");
                }
            }
        }
        return res;
    }

    @Override
    public Boolean validateBrackets(String s) {
        String openingBraces = "([{";
        String closingBraces = ")]}";
        char[] input = s.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character c : input) {
            if (openingBraces.contains(c.toString())) {
                stack.add(c);
            } else if (closingBraces.contains(c.toString())) {
                if (stack.isEmpty()) {
                    return false;
                } else if (closingBraces.indexOf(c) == openingBraces.indexOf(stack.peekLast())) {
                    stack.removeLast();
                }
            }
        }
        return stack.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
        String [] strings = s.split(" ");
        ArrayDeque<Long> stack = new ArrayDeque<>();

        for (int i = 0; i < strings.length; i++) {
            if (isNumber(strings[i])) {
                stack.push(Long.parseLong(strings[i]));
            } else {
                long element1 = stack.pop();
                long element2 = stack.pop();

                switch (strings[i]) {
                    case "+":
                        stack.push(element1 + element2);
                        break;
                    case "-":
                        stack.push(element2 - element1);
                        break;
                    case "*":
                        stack.push(element1 * element2);
                        break;
                    case "/":
                        stack.push(element2/element1);
                        break;
                }
            }
        }
        if (!stack.isEmpty()) {
            return  stack.pop();
        }
        else {
            return null;
        }
    }
    private boolean isNumber(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
