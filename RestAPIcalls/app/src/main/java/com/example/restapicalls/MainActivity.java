package com.example.restapicalls;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int noOfKeysReceived;
    ArrayList<String> keys = new ArrayList<String>();
    ArrayList<String> srno= new ArrayList<String>();
    ArrayList<String> userName= new ArrayList<String>();
    ArrayList<String> password= new ArrayList<String>();
    ArrayList<String> acl= new ArrayList<String>();

    public void onClick(View view) {
//        String result ="{\n" +
//                "  \"range\": \"Sheet1!A1:D20\",\n" +
//                "  \"majorDimension\": \"ROWS\",\n" +
//                "  \"values\": [\n" +
//                "    [\n" +
//                "      \"Sr. No.\",\n" +
//                "      \"UserName\",\n" +
//                "      \"Password\",\n" +
//                "      \"ACL\"\n" +
//                "    ],\n" +
//                "    [\n" +
//                "      \"1\",\n" +
//                "      \"ankit\",\n" +
//                "      \"admin@123\",\n" +
//                "      \"ps_22t,ps_30t\"\n" +
//                "    ],\n" +
//                "    [\n" +
//                "      \"2\",\n" +
//                "      \"ayushi\",\n" +
//                "      \"123456\",\n" +
//                "      \"ps_ccm,ps_22t,ps_30t\"\n" +
//                "    ],\n" +
//                "    [\n" +
//                "      \"3\",\n" +
//                "      \"ajay\",\n" +
//                "      \"admin@123\",\n" +
//                "      \"patching_22t,patching_30t\"\n" +
//                "    ],\n" +
//                "    [\n" +
//                "      \"4\",\n" +
//                "      \"rishi\",\n" +
//                "      \"admin@123\",\n" +
//                "      \"*\"\n" +
//                "    ]\n" +
//                "  ]\n" +
//                "}";

        DownloadTask task = new DownloadTask();
        task.execute("https://sheets.googleapis.com/v4/spreadsheets/1o_ngu-6okFhVBsPcNkQTSo8oor_49ceuYKirCCqEDtE/values/Sheet1!A1:D20?key=AIzaSyDbeeTsQcMXaWliGIOFVoyp4tqeKLQPd0Q");
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("hrishi", "postExec"+result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                String values = jsonObject.getString("values");

                JSONArray jsonArrays = jsonObject.getJSONArray("values");
                int len = jsonArrays.length();
                Log.i("values ", "" + len);

                for (int i = 0; i < jsonArrays.length(); i++) {
                    JSONArray arrayList = jsonArrays.getJSONArray(i);
                    // noOfKeysReceived = arrayList.length();
                    Log.i("arrayList ", "" + arrayList.length());


                    if (i == 0) {
                        for (int j = 0; j < arrayList.length(); j++) {

                            String key = arrayList.getString(j);
                            Log.i("key", "" + (arrayList.get(j).toString()));

                            keys.add(arrayList.get(j).toString());
                        }
                        continue;
                    }

                    for (int j = 0; j < arrayList.length(); j++) {

                        switch (j) {
                            case 0:
                                srno.add(arrayList.get(j).toString());
                                break;
                            case 1:
                                userName.add(arrayList.get(j).toString());
                                break;
                            case 2:
                                password.add(arrayList.get(j).toString());
                                break;
                            case 3:
                                acl.add(arrayList.get(j).toString());
                                break;
                            default:
                                Log.i("Error", "No Supported Key");

                        }

//                Log.i("key", ""+ keyValues.get(j));
//                keyValues.add(arrayList.get(j).toString());
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,keyValues);

                    }


                }
            } catch (Exception e)
            {
                Log.i("Exception", ""+e);
            }

            for(int i =0; i<keys.size(); i++)
            {
                switch (i)
                {
                    case 0:
                        TextView keySrnoView = (TextView) findViewById(R.id.keysrno);
                        keySrnoView.setText(keys.get(i));
                        break;
                    case 1:
                        TextView KeyUsernameView = (TextView) findViewById(R.id.keyusername);
                        KeyUsernameView.setText(keys.get(i));
                        break;
                    case 2:
                        TextView keyPasswordView = (TextView) findViewById(R.id.keypassword);
                        keyPasswordView.setText(keys.get(i));
                        break;
                    case 3:
                        TextView keyACLView = (TextView) findViewById(R.id.keyacl);
                        keyACLView.setText(keys.get(i));
                        break;
                }
            }


            ListView srnoView = (ListView) findViewById(R.id.srno);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,srno);
            srnoView.setAdapter(arrayAdapter);

            ListView usernameView = (ListView) findViewById(R.id.username);
            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,userName);
            usernameView.setAdapter(arrayAdapter1);

            ListView passwordView = (ListView) findViewById(R.id.password);
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,password);
            passwordView.setAdapter(arrayAdapter2);

            ListView aclView = (ListView) findViewById(R.id.acl);
            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,acl);
            aclView.setAdapter(arrayAdapter3);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
