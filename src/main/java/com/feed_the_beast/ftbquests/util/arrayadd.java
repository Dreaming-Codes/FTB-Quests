package com.feed_the_beast.ftbquests.util;

public class arrayadd {
    public static String[] addX(int n, String arr[], String x)
    {
        int i;
        String newarr[] = new String[n + 1];
        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }
}
