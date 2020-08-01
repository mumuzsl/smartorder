package com.cqjtu.smartorder.util;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author mumu
 * @date 2020/7/27
 */
public class StringUtils {

    private StringUtils() {}

    // UTF-8 or "ISO-8859-1" for ISO Latin 1
    private static CharsetEncoder asciiEncoder = StandardCharsets.UTF_8.newEncoder();

    public static boolean isPureAscii(String v) {
        return asciiEncoder.canEncode(v);
    }

    public static boolean hasTextAndNoWhitespaceAndLessLength(String str, int len) {
        return org.springframework.util.StringUtils.hasText(str)
                && !org.springframework.util.StringUtils.containsWhitespace(str)
                && str.length() < len
                && onlyLetterOrNumber(str);
    }

    public static boolean and(String str, Predicate<String>... predicates) {
        if (predicates.length == 0) {
            return false;
        }
        if (predicates.length == 1) {
            return predicates[0].test(str);
        }
        boolean b = predicates[0].test(str);
        for (int i = 1; i < predicates.length; i++) {
            b = b && predicates[i].test(str);
        }
        return b;
    }

    public static boolean hasTextAndNoWhitespaceAndLessLength(String str, int len, Predicate<String> predicate) {
        return hasTextAndNoWhitespaceAndLessLength(str, len) && predicate.test(str);
    }


    public static boolean onlyLetterOrNumber(String str) {
        String regex = "^[a-z|0-9|]*$"; //汉字的Unicode取值范围
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str.toLowerCase());
        return match.find();
    }

}
