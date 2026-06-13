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
        float r = ((float) Integer.decode("0x" + colorString.substring(1, 3))) / 255.0f;
        float g = ((float) Integer.decode("0x" + colorString.substring(3, 5))) / 255.0f;
        float b = ((float) Integer.decode("0x" + colorString.substring(5, 7))) / 255.0f;

        float l = r * 0.299f + g * 0.587f + b * 0.114f;

        if (l >= 0.44f) {
            return true;
        }
        return false;
    }

    /*
     * 0 <= hue <= 360 (hue < 0 -> hue undefined)
     * 0 <= saturation <= 1 (saturation < 0 -> saturation undefined)
     * 0 <= value <= 1
     */
    public static float[] hexStringTohsv(String colorString) {
        float hue, saturation, value;
        float red = ((float) Integer.decode("0x" + colorString.substring(1, 3))) / 255.0f;
        float green = ((float) Integer.decode("0x" + colorString.substring(3, 5))) / 255.0f;
        float blue = ((float) Integer.decode("0x" + colorString.substring(5, 7))) / 255.0f;

        float min = Math.min(Math.min(red, green), blue);
        float max = Math.max(Math.max(red, green), blue);
        float chroma = max - min;

        hue = 0.0f;
        if (chroma == 0)
            hue = -1.0f;
        else if (red == max)
            hue = (((green - blue) / chroma) + 6) % 6;
        else if (green == max)
            hue = (((blue - red) / chroma) + 2) % 6;
        else if (blue == max)
            hue = (((red - green) / chroma) + 4) % 6;

        hue *= 60;

        value = max;
        if (value == 0) {
            saturation = -1.0f;
        } else {
            saturation = chroma / value;
        }

        return new float[]{hue, saturation, value};
    }

    /*
     * 0 <= hue <= 360
     * 0 <= saturation <= 1
     * 0 <= value <= 1
     */
    public static String hsvToHexString(float hue, float saturation, float value) {
        float red, green, blue;
        int red_int, green_int, blue_int;
        String red_string, green_string, blue_string;
        float chroma = saturation * value;
        float hue_6 = hue / 60.0f;
        float x = chroma * (1 - Math.abs(hue_6 % 2 - 1));

        if (hue_6 < 1.0f) {
            red = chroma;
            green = x;
            blue = 0.0f;
        } else if (hue_6 < 2.0f) {
            red = x;
            green = chroma;
            blue = 0.0f;
        } else if (hue_6 < 3.0f) {
            red = 0.0f;
            green = chroma;
            blue = x;
        } else if (hue_6 < 4.0f) {
            red = 0.0f;
            green = x;
            blue = chroma;
        } else if (hue_6 < 5.0f) {
            red = x;
            green = 0.0f;
            blue = chroma;
        } else {
            red = chroma;
            green = 0.0f;
            blue = x;
        }

        float m = value - chroma;
        red += m;
        green += m;
        blue += m;

        red_int = Math.round(red * 255);
        green_int = Math.round(green * 255);
        blue_int = Math.round(blue * 255);

        red_string = String.format("%02x", red_int);
        green_string = String.format("%02x", green_int);
        blue_string = String.format("%02x", blue_int);

        return "#" + red_string + green_string + blue_string;
    }
}
