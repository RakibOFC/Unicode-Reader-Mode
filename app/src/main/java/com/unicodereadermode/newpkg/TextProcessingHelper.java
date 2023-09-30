package com.unicodereadermode.newpkg;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
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

        for (int j = 0; j < 286; j++) {

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
            spannableStringBuilder.append(wordSpace);
        }

        return spannableStringBuilder;
    }

    public interface Callback {
        void onTextProcessed(SpannableStringBuilder spannableStringBuilder);
    }
}
