package com.ninestar.ninestartask.utils;

import android.annotation.SuppressLint;

import androidx.room.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ninestar.ninestartask.utils.Constants.DATE_FORMAT;

public class Utility {
    /* Splitting date string from time stamp string */
    public static final String getDate1(String timeStamp) {
        String finalDate = "";
        if (timeStamp != null && !timeStamp.isEmpty()) {
            String[] partedString = timeStamp.split("T");
            String date = partedString[0];
            String[] partedDate = date.split("-");
            finalDate = partedDate[2] + "/" + partedDate[1] + "/" + partedDate[0];
        }
        return finalDate;
    }
     /* Converting author list to single string object*/
    public static final String getAuthorString(List<String> authorArray) {
        String authorString = "";
        for (String author : authorArray) {
            authorString = authorString + author + ", ";
        }
        return authorString;
    }
}
