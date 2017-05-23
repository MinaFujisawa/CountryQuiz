package com.derrick.park.countryquiz;

/**
 * Created by MinaFujisawa on 2017/05/21.
 */

public class Colors {
    private int colorName;
    private boolean isDeepColor;

    public Colors(int colorName, boolean isDeepColor) {
        this.colorName = colorName;
        this.isDeepColor = isDeepColor;
    }

    public int getColorName() {
        return colorName;
    }

    public boolean isDeepColor() {
        return isDeepColor;
    }
}
