package com.queenzend.toasterlibrary;

import android.content.Context;
import android.widget.Toast;

public class ToasterMessage {

    public static void s(Context c, String message){

        Toast.makeText(c,message, Toast.LENGTH_SHORT).show();
    }
    public static void c(Context c){
    }
    public static void d(Context c){
    }

}