package com.example.bookstoreapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadURL {

    public String readURL(String myURL) throws IOException {
        String data = "";
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(myURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }
            data = buffer.toString();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            inputStream.close();
            connection.disconnect();
        }
        return data;
    }
}
