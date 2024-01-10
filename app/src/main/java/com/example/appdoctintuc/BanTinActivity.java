package com.example.appdoctintuc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BanTinActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<TinTuc> mListTinTuc;
    private TinTucAdaper mTinTucAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_tin);

        InitializeUI();

        String url = getIntent().getStringExtra("url");
        if (url != null && !url.isEmpty()) {
            new GetListTinTuc().execute(url);
        } else {
            Log.e("BanTinActivity", "Invalid URL");
        }
    }

    private void InitializeUI() {
        mRecyclerView = findViewById(R.id.activityBanTin_recyclerView);
        mListTinTuc = new ArrayList<>();
        mTinTucAdapter = new TinTucAdaper(this, mListTinTuc);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayout);
        mRecyclerView.setAdapter(mTinTucAdapter);
    }

    class GetListTinTuc extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            return getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            if (document != null) {
                NodeList nodeListItem = document.getElementsByTagName("item");
                NodeList nodeListDescription = document.getElementsByTagName("description");
                for (int i = 0; i < nodeListItem.getLength(); i++) {
                    Element element = (Element) nodeListItem.item(i);

                    String title = parser.getValue(element, "title");
                    String link = parser.getValue(element, "link");
                    String img = "";
                    String description = "";
                    if (i + 1 < nodeListDescription.getLength()) {
                        description = nodeListDescription.item(i + 1).getTextContent();
                    }

                    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher matcher = p.matcher(description);
                    if (matcher.find())
                        img = matcher.group(1);

                    TinTuc tinTuc = new TinTuc(title, link, img);
                    mListTinTuc.add(tinTuc);
                }

                mTinTucAdapter.notifyDataSetChanged();
            } else {
                Log.e("BanTinActivity", "Document is null");
            }
            super.onPostExecute(s);
        }

        private String getData(String theUrl) {
            StringBuilder content = new StringBuilder();
            try {
                // create a url object
                URL url = new URL(theUrl);
                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();
                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                Log.e("BanTinActivity", "Error getting data", e);
            }
            return content.toString();
        }
    }
}
