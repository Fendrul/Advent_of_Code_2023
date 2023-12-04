package be.strykers.utils;

import java.util.LinkedList;

public class LinkedChars extends LinkedList<Character> {

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
