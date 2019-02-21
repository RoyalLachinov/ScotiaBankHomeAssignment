package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

public class NetworkConnection {


    /**
     * CHECK WHETHER NETWORK CONNECTION IS AVAILABLE OR NOT
     */
    public static boolean checkConnection(@NonNull Context context) {
        assert ((ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)) != null;
        return ((ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
