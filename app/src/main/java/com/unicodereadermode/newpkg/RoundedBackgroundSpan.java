package com.unicodereadermode.newpkg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class RoundedBackgroundSpan extends ReplacementSpan {

    private int cornerRadius = 20;
    private int backgroundColor = 0;
    private int textColor = 0;
    private int padding = 16; // Set your desired padding value
    private int circleHeightReduction = 0; // Set your desired reduction in circle height

    public RoundedBackgroundSpan(int bgColor, int textColor, int circleHeightReduction) {
        super();
        this.backgroundColor = bgColor;
        this.circleHeightReduction = circleHeightReduction;
        this.textColor = textColor;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        // Measure text width including padding
        float textWidth = paint.measureText(text, start, end) + 2 * padding;

        // Calculate the vertical center position
        float centerY = (top + bottom) / 2f;

        // Calculate the circle's top and bottom positions with height reduction
        float circleTop = centerY - (circleHeightReduction / 2f);
        float circleBottom = centerY + (circleHeightReduction / 2f);

        // Create a RectF with padding
        RectF rect = new RectF(x, circleTop, x + textWidth, circleBottom);

        // Draw the background
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);

        // Draw the text with padding
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x + padding, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end)) + 2 * padding;
    }
}