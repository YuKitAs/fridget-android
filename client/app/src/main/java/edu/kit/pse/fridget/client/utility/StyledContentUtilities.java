package edu.kit.pse.fridget.client.utility;

import android.text.Html;
import android.text.Spanned;

public class StyledContentUtilities {
    public static Spanned convertToSpanned(String styledContent) {
        return styledContent != null
                ? Html.fromHtml(styledContent)
                : getDefaultSpanned();
    }

    public static Spanned getDefaultSpanned() {
        return Html.fromHtml("<p></p>");
    }
}
