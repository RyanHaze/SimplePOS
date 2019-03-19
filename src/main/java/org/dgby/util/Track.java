package org.dgby.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Track {
    private static Track track1 = null;
    private static Track track2 = null;
    private static Track track3 = null;

    private Character startSentinel;
    private Character endSentinel;
    private Character fieldSeperator;

    private Scanner scanner;
    private String[] format;

    private Track(Character startSentinel, Character endSentinel, Character fieldSeperator, String... format) {
        this.startSentinel = startSentinel;
        this.endSentinel = endSentinel;
        this.fieldSeperator = fieldSeperator;
        this.format = format;
    }

    private String callMethod(String name) {
        try {
            Method method = getClass().getDeclaredMethod("fetch" + name);
            return (String) method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return "";
        }
    }

    Map<String, String> parse(String data) {
        this.scanner = new Scanner(data);
        if (getChar() != startSentinel)
            System.out.println("There be dragons!");

        Map<String, String> dataMap = new HashMap<>();
        for (String name : format) {
            String methodData = callMethod(name).trim();
            if (methodData.length() > 0)
                dataMap.put(name, methodData);
        }
        return dataMap;
    }

    private Character getChar() {
        return scanner.findInLine(".").charAt(0);
    }

    private String getStringUntil(Integer max, Character character) {
        StringBuilder ret = new StringBuilder();
        Character nextChar;

        while ((ret.length() < max) && (scanner.hasNext())) {
            nextChar = getChar();
            if (nextChar == character)
                break;

            ret.append(nextChar);
        }

        return ret.toString();
    }

    private String fetchFC() {
        return getChar().toString();
    }

    private String fetchPAN() {
        return getStringUntil(20, fieldSeperator);
    }

    private String fetchNAME() {
        return getStringUntil(27, fieldSeperator);
    }

    private String fetchED() {
        return getStringUntil(4, fieldSeperator);
    }

    private String fetchSC() {
        return getStringUntil(3, fieldSeperator);
    }

    private String fetchDD() {
        return getStringUntil(100, endSentinel);
    }

    static Track track1Parser() {
        if (track1 == null)
            track1 = new Track('%', '?', '^', "FC", "PAN", "NAME", "ED", "SC", "DD");

        return track1;
    }

    static Track track2Parser() {
        if (track2 == null)
            track2 = new Track(';', '?', '=', "PAN", "ED", "SC", "DD");

        return track2;
    }

    static Track track3Parser() {
        if (track3 == null)
            track3 = new Track(';', '=', '?', "FC", "PAN", "DD");

        return track3;
    }
}