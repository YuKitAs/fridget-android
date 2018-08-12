package edu.kit.pse.fridget.client.viewmodel.common;

import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

public class StyledContentViewModel {
    private final String plainContent;

    private boolean bold;
    private boolean italic;
    private boolean underline;

    private StyledContentViewModel(String plainContent, boolean bold, boolean italic, boolean underline) {
        this.plainContent = plainContent;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
    }

    public void toggleBold() {
        bold = !bold;
    }

    public void toggleItalic() {
        italic = !italic;
    }

    public void toggleUnderline() {
        underline = !underline;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public Spanned getSpannedContent() {
        SpannableStringBuilder spannedContentBuilder = new SpannableStringBuilder(plainContent);

        if (bold) {
            spannedContentBuilder.setSpan(new StyleSpan(Typeface.BOLD), 0, plainContent.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (italic) {
            spannedContentBuilder.setSpan(new StyleSpan(Typeface.ITALIC), 0, plainContent.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (underline) {
            spannedContentBuilder.setSpan(new UnderlineSpan(), 0, plainContent.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return SpannedString.valueOf(spannedContentBuilder);
    }

    public String getHtmlContent() {
        return Html.toHtml(getSpannedContent());
    }

    public String getPlainContent() {
        return plainContent;
    }

    public static StyledContentViewModel fromPlainContent(String plainContent) {
        return new StyledContentViewModel(plainContent, false, false, false);
    }

    public static StyledContentViewModel fromHtmlContent(String htmlContent) {
        Spanned spanned = new SpannedString(Html.fromHtml(htmlContent));

        StyleSpan[] styleSpans = spanned.getSpans(0, spanned.length(), StyleSpan.class);
        UnderlineSpan[] underlineSpans = spanned.getSpans(0, spanned.length(), UnderlineSpan.class);

        boolean bold = false;
        boolean italic = false;
        boolean underline = false;

        for (StyleSpan styleSpan : styleSpans) {
            if (styleSpan.getStyle() == Typeface.BOLD) {
                bold = true;
            } else if (styleSpan.getStyle() == Typeface.ITALIC) {
                italic = true;
            }
        }

        if (underlineSpans.length > 0) {
            underline = true;
        }

        return new StyledContentViewModel(spanned.toString(), bold, italic, underline);
    }
}
