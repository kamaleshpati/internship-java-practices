package com.cognitree.nonrepetedcharacter;

public class NonRepeatedCharacters {

    public Character findUnique(String input) {
        return findUnique(input, 0);
    }

    private Character findUnique(String input, int start) {
        if (start == input.length()) {
            return null;
        } else {
            char ch = input.charAt(start);
            if (input.lastIndexOf(ch) == start && input.indexOf(ch) == start)
                return ch;
            return findUnique(input, start + 1);
        }
    }
}
