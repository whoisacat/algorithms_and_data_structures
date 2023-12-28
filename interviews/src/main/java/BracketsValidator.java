public class BracketsValidator {
    public boolean isValidOfMine(String s) {

        if (s.isEmpty()) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        boolean result = true;
        for (int i = 0; i < (s.length() / 2); i++) {
            try {
                if (getCloser(s.charAt(i)) != s.charAt(s.length() - i - 1)) {
                    result = false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return result;
    }

    //Runtime 1 ms Beats 98.53% Memory 41.7 MB Beats 5.25%
    public boolean isValidRuntimeOptimized(String s) {

        if (s.isEmpty()) {
            return true;
        }
        if ((s.length() & 1) != 0) {
            return false;
        }
        char[] stack = new char[s.length() / 2];
        int stackPointer = 0;
        for (char c : s.toCharArray()) {
            try {
                if (isOpening(c)) {
                    stack[stackPointer++] = c;
                } else {
                    if (getCloser(stack[--stackPointer]) != c) {
                        return false;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return stackPointer == 0;
    }

    //Runtime 2 ms Beats 79.58% Memory 41 MB Beats 23.32%
    public boolean isValidMemOptimised(String s) {

        if (s.isEmpty()) {
            return true;
        }
        if ((s.length() & 1) != 0) {
            return false;
        }
        char[] stack = new char[s.length() / 2];
        int stackPointer = 0;
        for (int i = 0; i < s.length(); i++) {
            try {
                if (isOpening(s.charAt(i))) {
                    stack[stackPointer++] = s.charAt(i);
                } else {
                    if (getCloser(stack[--stackPointer]) != s.charAt(i)) {
                        return false;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return stackPointer == 0;
    }

    private boolean isOpening(char c) throws Exception {
        switch (c) {
            case '(' : {
                return true;
            }
            case '[' : {
                return true;
            }
            case '{' : {
                return true;
            }
            case ')' : {
                return false;
            }
            case ']' : {
                return false;
            }
            case '}' : {
                return false;
            }
            default : throw new Exception();
        }
    }

    private char getCloser(char c) throws Exception {
        switch (c) {
            case '(' : return  ')';
            case '[' : return ']';
            case '{' : return '}';
            default : throw new Exception();
        }
    }

    public static void main(String[] args) {
        BracketsValidator sol = new BracketsValidator();
        String s = "()";
        System.out.println(sol.isValidOfMine(s));
        System.out.println(sol.isValidMemOptimised(s));
        System.out.println(sol.isValidRuntimeOptimized(s));
    }
}
