package com.unicodereadermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ReplacementSpan;

import androidx.annotation.DrawableRes;

public class RadiusBackgroundSpan extends ReplacementSpan {
    private int backgroundColor;
    private int radius;
    private int textColor;

    public RadiusBackgroundSpan(int backgroundColor, int radius) {
        this.backgroundColor = backgroundColor;
        this.radius = radius;
    }

    public RadiusBackgroundSpan(int backgroundColor, int radius, int textColor) {
        this.backgroundColor = backgroundColor;
        this.radius = radius;
        this.textColor = textColor;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end) + radius);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {

        // Draw the circular background
        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.FILL);

        float width = paint.measureText(text, start, end);
        float centerX = x + width;
        float centerY = (top + bottom) / 2f;

        canvas.drawCircle(centerX, centerY, radius, paint);

        // Draw the text
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);

        float textX = centerX - (width / 2);

        canvas.drawText(text, start, end, textX, (float) y, paint);
    }
}
