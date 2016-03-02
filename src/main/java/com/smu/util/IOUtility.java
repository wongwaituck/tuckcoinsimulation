package com.smu.util;

/**
 * Created by WaiTuck on 03/03/2016.
 */
public class IOUtility {
    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
