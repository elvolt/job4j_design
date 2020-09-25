package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = false;
        char ch = 102;
        byte b = 123;
        short s = 15432;
        int i = 123456;
        long l = 2147483647;
        float f = 1.15F;
        double d = 1234.12;
        String template = "Types: " + System.lineSeparator()
                + "boolean: {}, char: {}, byte: {}, short: {}, int: {}, long: {} "
                + "float: {}, double: {}";
        LOG.debug(template, bool, ch, b, s, i, l, f, d);
    }
}