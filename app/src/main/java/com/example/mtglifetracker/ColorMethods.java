package com.example.mtglifetracker;

public class ColorMethods {
    public static boolean checkIfColorStringValid(String colorString) {
        if (colorString.length() != 7 || colorString.charAt(0) != '#') {
            return false;
        }
        for (int i = 1; i <= 6; i++) {
            char c = colorString.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFontBlackForBackground(String colorString) {
        double gamma = 2.2;

        double r = ((double) Integer.decode("0x" + colorString.substring(1, 3))) / 255;
        double g = ((double) Integer.decode("0x" + colorString.substring(3, 5))) / 255;
        double b = ((double) Integer.decode("0x" + colorString.substring(5, 7))) / 255;

        /*
        double l = 0.2126 * Math.pow(r, gamma);
        l += 0.7152 * Math.pow(g, gamma);
        l += 0.0722 * Math.pow(b, gamma);
        */

        double l = r * 0.299 + g * 0.587 + b * 0.114;

        if (l >= 0.44) {
            return true;
        }
        return false;
    }
}
