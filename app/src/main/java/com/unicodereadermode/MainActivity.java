package com.unicodereadermode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.AutoScrollHelper;
import androidx.core.widget.NestedScrollView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view);
        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);

        // AutoScrollHelper scrollHelper = AutoScrollHelper.create(nestedScrollView);

        SpannableStringBuilder builder = new SpannableStringBuilder();

        int surahNo = 1;
        String surahName = getString(R.string.def_al_fatiha);
        String surahNameMean = getString(R.string.def_surah_mean);
        String wordSpace = "\u00A0\u00A0";
        String ayahSpace = "\u00A0\u00A0\u00A0";
        String[] stringsArr = {"بِسۡمِ", "اللهِ", "الرَّحۡمٰنِ", "الرَّحِيۡمِ", "اَلۡحَمۡدُ", "لِلّٰهِ", "لِلّٰهِ", "رَبِّ", "الۡعٰلَمِيۡنَۙ", "الرَّحۡمٰنِ", "الرَّحِيۡمِ"};

        SpannableString spanSurahName = new SpannableString(surahName);
        SpannableString spanSurahNameMean = new SpannableString(surahNameMean);

        spanSurahName.setSpan(new RelativeSizeSpan(0.9f), 0, surahName.length(), Spanned.SPAN_INTERMEDIATE);
        spanSurahName.setSpan(new ForegroundColorSpan(getColor(R.color.primary_2)), 0, surahName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanSurahNameMean.setSpan(new RelativeSizeSpan(0.7f), 0, surahNameMean.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.append(getTemp(surahNo)).append("\n")
                .append(spanSurahName).append("\n")
                .append(spanSurahNameMean).append("\n");

        builder.setSpan(new TypefaceSpan(getResources().getFont(R.font.siliguri)), 0, surahName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        for (int i = 0; i < 18; i++) {

            ArrayList<String> words = new ArrayList<>(Arrays.asList(stringsArr));

            for (int j = 0; j < words.size(); j++) {

                // Create a custom ClickableSpan that stores the clicked word
                final String clickedWord = words.get(j);
                final int ayahNo = i;
                final int wordIndex = j;
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                        Log.e("Info", "Ayah: " + (ayahNo + 1) + "\nIndex: " + wordIndex + "\nWord: " + clickedWord);
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
                builder.append(words.get(j)).append(wordSpace); // Append a single space
                builder.setSpan(clickableSpan, builder.length() - words.get(j).length() - wordSpace.length(), builder.length() - wordSpace.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // Append the Ayah number to the builder
            builder.append(getTemp(i)).append(ayahSpace);
        }

        // Set the spannable text to the TextView
        textView.setText(builder, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private SpannableString getTemp(int number) {

        String numberString = String.format(new Locale("bn"), "%d", number + 99);
        SpannableString spannableString = new SpannableString(numberString);

        int backgroundColor = getColor(R.color.primary_2);
        int textColor = Color.BLACK;
        int padding = 8;

        // spannableString.setSpan(new RadiusBackgroundSpan(backgroundColor, radius, textColor), 0, ayahNo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new DynamicRadiusBackgroundSpan(backgroundColor, textColor, padding), 0, numberString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(0.5f), 0, numberString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}