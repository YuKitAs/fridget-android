package edu.kit.pse.fridget.client.utility;

import android.graphics.Color;

public class MagnetColorUtilities {
    public static int convertMagnetColor(String magnetColor) {
        return magnetColor != null ? Color.parseColor(String.format("#%s", magnetColor)) : getDefaultMagnetColor();
    }

    public static int getDefaultMagnetColor() {
        return Color.parseColor("#FFFFFF");
    }
}