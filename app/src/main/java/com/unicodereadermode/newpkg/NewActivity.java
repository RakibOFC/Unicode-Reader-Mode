package com.unicodereadermode.newpkg;

import androidx.appcompat.app.AppCompatActivity;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.os.Bundle;

import com.unicodereadermode.R;

public class NewActivity extends AppCompatActivity {

    private final String[] stringsArr = {"بِسۡمِ", "اللهِ", "الرَّحۡمٰنِ", "الرَّحِيۡمِ", "اَلۡحَمۡدُ", "لِلّٰهِ", "لِلّٰهِ", "رَبِّ", "الۡعٰلَمِيۡنَۙ", "الرَّحۡمٰنِ", "الرَّحِيۡمِ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        TextView paragraphTextView = findViewById(R.id.text_view);
        // Button button = findViewById(R.id.button);

        // Start processing the text in the background
        paragraphTextView.setMovementMethod(LinkMovementMethod.getInstance());
        TextProcessingHelper.processTextInBackground(stringsArr, paragraphTextView::setText);

        /*button.setOnClickListener(v -> {

            Log.e("Info", "clicked...");
        });*/

        /*// Create a SpannableStringBuilder to build the paragraph
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

        paragraphTextView.post(() -> paragraphTextView.setText(spannableStringBuilder));
        paragraphTextView.setMovementMethod(LinkMovementMethod.getInstance());*/
    }
}