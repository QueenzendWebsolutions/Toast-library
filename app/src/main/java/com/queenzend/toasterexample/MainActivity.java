package com.queenzend.toasterexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.queenzend.toasterlibrary.ToasterMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToasterMessage.s(MainActivity.this,"Welcome to Marketing Platform");
    }
}