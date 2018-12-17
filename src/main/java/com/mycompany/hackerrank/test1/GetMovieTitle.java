/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hackerrank.test1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ustadho_1218
 */
public class GetMovieTitle {

    public static void main(String[] args) {
        String[] titles = getMovieTitles("ring", 1);
        System.out.println("Data : --------------------------");
        for(int i=0; i<titles.length; i++){
            System.out.println("    "+titles[i]);
        }
    }

    static String[] getMovieTitles(String substr, int startPage) {
        String response;
        int totalPages = Integer.MAX_VALUE;
        int page = 0;
        int per_page = 10;
        int total = 0;
        int total_pages = 0;
        List<String> titles = new ArrayList<>();
        while (startPage <= totalPages) {
            try {
                URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + startPage);
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                while ((response = in.readLine()) != null) {
                    JsonObject convertedObject = new Gson().fromJson(response, JsonObject.class);
                    totalPages = convertedObject.get("total_pages").getAsInt();
                    page = convertedObject.get("page").getAsInt();
                    per_page = convertedObject.get("per_page").getAsInt();
                    total_pages = convertedObject.get("total_pages").getAsInt();
                    JsonArray data = convertedObject.getAsJsonArray("data");
                    for (int i = 0; i < data.size(); i++) {
                        String title = data.get(i).getAsJsonObject().get("Title").getAsString();
                        titles.add(title);
                    }
                }
                in.close();
                startPage++;
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        Collections.sort(titles);
        System.out.println("page: "+page);
        System.out.println("per_page: "+per_page);
        System.out.println("total: "+total);
        System.out.println("total_pages: "+total_pages);
        return titles.toArray(new String[0]);
    }

}
