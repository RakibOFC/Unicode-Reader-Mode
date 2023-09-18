package com.unicodereadermode;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.style.ReplacementSpan;

public class DynamicRadiusBackgroundSpan extends ReplacementSpan {
    private final int backgroundColor;
    private final int textColor;
    private final int padding;

    public DynamicRadiusBackgroundSpan(int backgroundColor, int textColor, int padding) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.padding = padding;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {

        Rect textBounds = new Rect();
        paint.getTextBounds(text.toString(), start, end, textBounds);
        int textWidth = textBounds.width();
        int radius = (textWidth + 2 * padding) / 2;

        // Add padding to both sides; The radius is at least 20
        // return Math.max(2 * radius, 2) + 2 * padding;
        return Math.max(2 * radius, 2);
    }

    /*@Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int textWidth = Math.round(paint.measureText(text, start, end));
        int radius = (textWidth + 2 * padding) / 2;

        // Calculate left and right positions for the circular background
        int left = Math.round(x - radius + padding); // Adjust left position to center text
        int right = left + textWidth + 2 * (radius - padding); // Adjust right position

        // Draw the circular background with fixed top and bottom, and responsive left and right
        paint.setColor(backgroundColor);
        paint.setAlpha(70);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(left, top, right, bottom, radius, radius, paint);

        // Calculate the text baseline position based on top and bottom
        int textBaseline = (bottom + top - paint.getFontMetricsInt().bottom - paint.getFontMetricsInt().top) / 2;

        // Draw the text, adjusting for the new left position
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, start, end, left, textBaseline, paint);
    }*/

    /*@Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {

        int textWidth = Math.round(paint.measureText(text, start, end));
        int radius = (textWidth + padding) / 2;

        // Calculate left and right positions for the circular background
        // int left = Math.round(x - padding); // Subtract padding from the left side
        int left = Math.round(x); // Subtract padding from the left side
        // int right = left + textWidth + 2 * padding; // Add padding to both sides
        int right = left + textWidth; // Add padding to both sides

        // Draw the circular background with fixed top and bottom, and responsive left and right
        paint.setColor(backgroundColor);
        paint.setAlpha(60);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(left, top, right, bottom, radius, radius, paint);

        // Draw the text
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, start, end, x, y, paint);
    }*/

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {

        int textWidth = Math.round(paint.measureText(text, start, end));
        int padding = 10; // Adjust padding as needed
        int radius = (textWidth + 2 * padding) / 2;

        // Draw the circular background
        paint.setColor(backgroundColor);
        paint.setAlpha(80);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x + textWidth / 2f, (top + bottom) / 2f, radius, paint);

        // Draw the text
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, start, end, x, y, paint);
    }
}
