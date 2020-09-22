package com.queenzend.toasterlibrary;

import android.content.Context;
import android.widget.Toast;

public class SaveDataAll {
    public static void CallServer(Context c, String emailInput){
        Toast.makeText(c,emailInput, Toast.LENGTH_SHORT).show();
    }
}
