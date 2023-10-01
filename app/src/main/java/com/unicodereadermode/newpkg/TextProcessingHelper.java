package com.unicodereadermode.newpkg;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.text.TextPaint;

import androidx.annotation.NonNull;

public class TextProcessingHelper {

    public static void processTextInBackground(final String[] stringsArr, final Callback callback) {

        new Thread(() -> {

            final SpannableStringBuilder spannableStringBuilder = createSpannableStringBuilder(stringsArr);

            new Handler(Looper.getMainLooper()).post(() ->
                    callback.onTextProcessed(spannableStringBuilder));

        }).start();
    }

    private static SpannableStringBuilder createSpannableStringBuilder(String[] stringsArr) {

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String wordSpace = "    ";

        // Create a circular background for the character "১"
        SpannableString spanOne = new SpannableString("১১১");
        spanOne.setSpan(new RoundedBackgroundSpan(Color.GRAY, Color.WHITE, 50), 0, spanOne.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanOne.setSpan(new RelativeSizeSpan(0.5f), 0, spanOne.length(), Spanned.SPAN_INTERMEDIATE);

        // Create a span for "আল-ফাতিহা" with reduced size
        SpannableString spanSurahName = new SpannableString("আল-ফাতিহা");
        spanSurahName.setSpan(new RelativeSizeSpan(0.9f), 0, spanSurahName.length(), Spanned.SPAN_INTERMEDIATE);

        // Append the formatted text to the SpannableStringBuilder
        spannableStringBuilder.append(spanOne).append("\n")
                .append(spanSurahName).append("\n");


        for (int j = 0; j < 100; j++) {

            for (int i = 0; i < stringsArr.length; i++) {

                String word = stringsArr[i];
                int startIndex = spannableStringBuilder.length();
                int endIndex = startIndex + word.length();

                spannableStringBuilder.append(word);

                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                        Log.e("Clicked Word: ", word);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint textPaint) {
                        super.updateDrawState(textPaint);

                        textPaint.setUnderlineText(false);
                        textPaint.setColor(Color.BLACK);
                    }
                };

                spannableStringBuilder.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                if (i < stringsArr.length - 1) {
                    spannableStringBuilder.append(wordSpace);
                }
            }

            SpannableString ayahNo = new SpannableString(String.valueOf(j + 1));
            ayahNo.setSpan(new RoundedBackgroundSpan(Color.GRAY, Color.WHITE, 50), 0, ayahNo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableStringBuilder.append(" ").append(ayahNo).append(wordSpace);
        }
        return spannableStringBuilder;
    }

    public interface Callback {
        void onTextProcessed(SpannableStringBuilder spannableStringBuilder);
    }
}
