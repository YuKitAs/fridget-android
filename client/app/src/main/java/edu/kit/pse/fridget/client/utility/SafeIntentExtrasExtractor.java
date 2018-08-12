package edu.kit.pse.fridget.client.utility;

import android.content.Intent;
import android.os.Bundle;

public class SafeIntentExtrasExtractor {
    public static String getString(Intent intent, String fieldName) {
        if (intent == null) {
            return null;
        }

        Bundle bundle = intent.getExtras();

        if (bundle == null) {
            return null;
        }

        return bundle.getString(fieldName);
    }
}
