package edu.kit.pse.fridget.client.viewmodel.common;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

public class StyledContentViewModel {
    public final MutableLiveData<String> liveDataPlainContent = new MutableLiveData<>();
    public final MutableLiveData<Boolean> liveDataBold = new MutableLiveData<>();
    public final MutableLiveData<Boolean> liveDataItalic = new MutableLiveData<>();
    public final MutableLiveData<Boolean> liveDataUnderline = new MutableLiveData<>();

    public StyledContentViewModel(String plainContent) {
        this.liveDataPlainContent.setValue(plainContent);
        this.liveDataBold.setValue(false);
        this.liveDataItalic.setValue(false);
        this.liveDataUnderline.setValue(false);
    }

    public void toggleBold() {
        liveDataBold.setValue(!liveDataBold.getValue());
    }

    public void toggleItalic() {
        liveDataItalic.setValue(!liveDataItalic.getValue());
    }

    public void toggleUnderline() {
        liveDataUnderline.setValue(!liveDataUnderline.getValue());
    }

    public String getHtmlContent() {
        return Html.toHtml(getSpannedContent()).trim();
    }

    public void setHtmlContent(String htmlContent) {
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

        liveDataPlainContent.setValue(spanned.toString());
        liveDataBold.setValue(bold);
        liveDataItalic.setValue(italic);
        liveDataUnderline.setValue(underline);
    }

    private Spanned getSpannedContent() {
        SpannableStringBuilder spannedContentBuilder = new SpannableStringBuilder(liveDataPlainContent.getValue());

        if (liveDataBold.getValue()) {
            spannedContentBuilder.setSpan(new StyleSpan(Typeface.BOLD), 0, liveDataPlainContent.getValue().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (liveDataItalic.getValue()) {
            spannedContentBuilder.setSpan(new StyleSpan(Typeface.ITALIC), 0, liveDataPlainContent.getValue().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (liveDataUnderline.getValue()) {
            spannedContentBuilder.setSpan(new UnderlineSpan(), 0, liveDataPlainContent.getValue().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return SpannedString.valueOf(spannedContentBuilder);
    }
}
