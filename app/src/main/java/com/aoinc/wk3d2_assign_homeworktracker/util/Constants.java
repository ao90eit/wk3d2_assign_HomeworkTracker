package com.aoinc.wk3d2_assign_homeworktracker.util;

import android.content.Context;
import android.content.res.Resources;

import com.aoinc.wk3d2_assign_homeworktracker.R;

public class Constants {
    public static final Integer[] WEEK_NUMS = {1, 2, 3, 4, 5, 6, 7};
    public static final String FRAGMENT_BUNDLE_TAG = "fragment_bundle_tag";

    public static String[] getDayAbbrs(Context context) {
        Resources resources = context.getResources();

        return new String[] {
                resources.getString(R.string.abbr_day_1),
                resources.getString(R.string.abbr_day_2),
                resources.getString(R.string.abbr_day_3),
                resources.getString(R.string.abbr_day_4),
                resources.getString(R.string.abbr_day_5),
                resources.getString(R.string.abbr_day_6_7)
        };
    }
}
