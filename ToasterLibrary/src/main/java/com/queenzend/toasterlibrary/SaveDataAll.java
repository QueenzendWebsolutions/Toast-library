package com.queenzend.toasterlibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SaveDataAll {

    public static void CallServerDataForTest() {
        //System.out.println("Sum of numbers is: " + "10");
        Log.d("Sum of numbers is: " , "10");
    }


    static int CallServer(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        int result = CallServer(2,3);
        //System.out.println("Sum of numbers is: " + result);
        Log.d("Sum of numbers is1111: " , "110");
    }

    public static void CallServer11(final Context c, final String token) {
        //Toast.makeText(c, "welcome"+"-----"+email, Toast.LENGTH_SHORT).show();
        Toast.makeText(c,token, Toast.LENGTH_SHORT).show();
        class UserLogin extends AsyncTask<String, Void, String> {
            String loginUrl = "http://k2key.in/marketing_plateform_CI/UserController/saveDeviceInfo";
            String server_response;
            ProgressDialog prgDialog = new ProgressDialog(c);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                prgDialog.setMessage("Please wait...");
                prgDialog.show();
            }

            @Override
            protected String doInBackground(String... strings) {
                URL url;
                HttpURLConnection urlConnection;
                try {
                    url = new URL(loginUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);

                    //Toast.makeText(getApplicationContext(), "Inside do",Toast.LENGTH_LONG).show();

                    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());

                    try {
                        //SharedPreferences sharedPreferences = c.getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
                        //final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN), "");
                        //Toast.makeText(getApplicationContext(), "token"+ token, Toast.LENGTH_SHORT).show();
                        //Log.d("JSON INPUT token", token);
                        JSONObject object = new JSONObject();
                        object.put("device_id", token);

                        wr.write(object.toString());
                        //Toast.makeText(getApplicationContext(), "Object"+object.toString(), Toast.LENGTH_LONG).show();
                        Log.d("JSON INPUT", object.toString());
                        wr.flush();
                        wr.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Toast.makeText(getApplicationContext(), "Error 1:"+e.toString(), Toast.LENGTH_LONG).show();

                    }
                    urlConnection.connect();

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        server_response = readStream(urlConnection.getInputStream());
                        // Toast.makeText(getApplicationContext(), "server_response"+server_response, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "Error 2:"+e.toString(), Toast.LENGTH_LONG).show();
                }
                return null;
            }

//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                prgDialog.hide();
//                //Log.e("Response", "" + server_response);
//                //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
//                //final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN), "");
//                //Toast.makeText(c, "token" + "jayuuuuu", Toast.LENGTH_SHORT).show();
//               // Toast.makeText(c, "Device Token save successfully", Toast.LENGTH_LONG).show();
//            }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            prgDialog.hide();
            Log.e("Response", "" + server_response);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(server_response.toString());
                if (jsonObject.has("res_code")) {
                    if ((jsonObject.getString("res_code").contains("2"))) {

                        Toast.makeText(c, "Failed to save device token", Toast.LENGTH_LONG).show();
                        System.out.println("Invalid Login");

                    } else if((jsonObject.getString("res_code").contains("1"))) {

                        System.out.println("Inside onSuccess response" + jsonObject.getString("res_data"));

                        Toast.makeText(c, "Successfully save device token", Toast.LENGTH_LONG).show();

                        System.out.println("Successfully save device token");
                    }
                    else {
                        Toast.makeText(c, "Failed to save device token", Toast.LENGTH_LONG).show();
                        System.out.println("Failed to save device token");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
}

            private String readStream(InputStream inputStream) {
                BufferedReader reader = null;
                StringBuffer response = new StringBuffer();
                try {
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return response.toString();
            }
        }
        UserLogin ul = new UserLogin();
        ul.execute();
    }

}
