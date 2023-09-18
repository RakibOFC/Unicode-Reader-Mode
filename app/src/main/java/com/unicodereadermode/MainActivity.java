package com.unicodereadermode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        int backgroundColor = 0xFF54A5E6;
        String wordSpace = "  ";

        for (int i = 0; i < 8; i++) {

            String[] stringsArr = {"بِسۡمِ", "اللهِ", "الرَّحۡمٰنِ", "الرَّحِيۡمِ", "اَلۡحَمۡدُ", "لِلّٰهِ", "لِلّٰهِ", "رَبِّ", "الۡعٰلَمِيۡنَۙ", "الرَّحۡمٰنِ", "الرَّحِيۡمِ"};
            ArrayList<String> words = new ArrayList<>(Arrays.asList(stringsArr));

            for (String word : words) {

                // Create a custom ClickableSpan that stores the clicked word
                final String clickedWord = word;
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                        Log.e("Clicked Word", clickedWord);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);

                        // Remove underline from the text
                        ds.setUnderlineText(false);
                        ds.setColor(Color.BLACK);
                    }
                };

                // Append the word and set the ClickableSpan for that word
                builder.append(word).append(wordSpace); // Append a single space
                builder.setSpan(clickableSpan, builder.length() - word.length() - wordSpace.length(), builder.length() - wordSpace.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // Append the Ayah number to the builder
            builder.append(getTemp(i)).append("       "); // Append two spaces
        }


        // Set the spannable text to the TextView
        textView.setText(builder, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        /*String[] strings = {"بِسۡمِ اللهِ الرَّحۡمٰنِ الرَّحِيۡمِ", "اَلۡحَمۡدُ لِلّٰهِ رَبِّ الۡعٰلَمِيۡنَۙ", "الرَّحۡمٰنِ الرَّحِيۡمِ"};
        int[] resDrawables = {R.drawable.baseline_back_hand_24, R.drawable.baseline_check_circle_24, R.drawable.baseline_check_circle_24};

        SpannableStringBuilder sStringBuilder = new SpannableStringBuilder();

        for (int i = 0; i < strings.length; i++) {

            Drawable drawable = ResourcesCompat.getDrawable(getResources(), resDrawables[i], null);
            drawable.setBounds(0, 0, 40, 40);

            sStringBuilder.append(" ");
            int start = sStringBuilder.length();
            sStringBuilder.append(" ");
            int end = sStringBuilder.length();

            sStringBuilder.setSpan(new ImageSpan(drawable), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            sStringBuilder.append(strings[i]);
        }
        textView.setText(sStringBuilder);*/
    }

    private SpannableString getTemp(int ayahNoInt) {

        String ayahNo = String.valueOf(ayahNoInt + 99);
        SpannableString spannableString = new SpannableString(ayahNo);

        int backgroundColor = getColor(R.color.primary);
        int textColor = Color.BLACK;
        int padding = 8;

        // spannableString.setSpan(new RadiusBackgroundSpan(backgroundColor, radius, textColor), 0, ayahNo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new DynamicRadiusBackgroundSpan(backgroundColor, textColor, padding), 0, ayahNo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // spannableString.setSpan(new RelativeSizeSpan(0.8f), 0, ayahNo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}