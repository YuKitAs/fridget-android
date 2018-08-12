package edu.kit.pse.fridget.client.utility;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtilities {
    private static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String DISPLAY_DATE_FORMAT = "yyyy-MM-dd";

    public synchronized static String convertToLocalTime(String isoDateTime) {
        // "synchronized" is required, because SimpleDateFormat is not thread safe!

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(ISO_DATE_TIME_FORMAT, Locale.getDefault());
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(isoDateTime);

            SimpleDateFormat dateFormatter = new SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.getDefault());
            dateFormatter.setTimeZone(TimeZone.getDefault());

            return dateFormatter.format(value);
        } catch (ParseException e) {
            Log.e(DateTimeUtilities.class.getSimpleName(), e.getMessage());
            return "-";
        }
    }
}
