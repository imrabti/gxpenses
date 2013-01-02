package com.nuvola.gxpenses.server.util;

import java.util.regex.Pattern;

public class ParserUtils {

    public static Pattern evaluateSectionCursor(String regExp) {
        return Pattern.compile(regExp);
    }

    public static Pattern evaluateAccountCursor(String regExp) {
        return Pattern.compile(regExp);
    }

    public static Pattern evaluateTransactionCursor(String regExp) {
        return Pattern.compile(regExp);
    }

    public static Double convertDecimal(String str) {
        if (str.length() == 0) {
            return new Double(0.0D);
        } else {
            return Double.valueOf(str);
        }
    }

}
