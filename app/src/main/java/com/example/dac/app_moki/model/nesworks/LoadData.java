package com.example.dac.app_moki.model.nesworks;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dac on 11/4/2017.
 */

public class LoadData extends AsyncTask<String, Void, String>{
    String link= "";
    HashMap<String, String> lstProps;
    String data ="";

    public LoadData(String link){
        this.link = link;
    }
    public LoadData(String link,HashMap<String, String> lstProps){
        this.link = link;
        this.lstProps = lstProps;

    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(lstProps == null || lstProps.size() == 0){
                data = getDataByMethodGet(httpURLConnection);
            }
            else {
                data = getDataByMethodPost(httpURLConnection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    private String getDataByMethodGet(HttpURLConnection httpURLConnection){
        System.out.println("method get");
        try {
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String lineData = null;
            StringBuilder dataByGet = new StringBuilder();
            while ((lineData = bufferedReader.readLine()) != null){
                System.out.println(lineData);
                dataByGet.append(lineData);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            return dataByGet.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private String getDataByMethodPost(HttpURLConnection httpURLConnection){
        System.out.println("method post");
        String key= "";
        String value = "";
        String dataByPost = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder();

            int count = lstProps.size();

//            for(int i = 0; i < count; i++){
//                for(Map.Entry<String, String> item : lstProps.get(i).entrySet()){
//                    key = item.getKey();
//                    value = item.getValue();
//                }
//                builder.appendQueryParameter(key, value);
//            }

            // Lay mot tap hop cac entry
            Set set = lstProps.entrySet();
            // Lay mot iterator
            Iterator i = set.iterator();
            // Hien thi cac phan tu
            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                System.out.print(me.getKey() + ": ");
                System.out.println(me.getValue());
                builder.appendQueryParameter(key, value);
            }
            String query = builder.build().getEncodedQuery();
            System.out.println(builder);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            dataByPost = getDataByMethodGet(httpURLConnection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataByPost;
    }
}
