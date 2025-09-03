package com.miniredis.server;

import java.util.Arrays;
import java.util.List;

public class CommandParser {
    public static List<String> parse(String input) {
        return Arrays.asList(input.trim().split("\\s+"));
    }
}
