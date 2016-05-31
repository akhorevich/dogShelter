package nortti.ru.dogshelter;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class DogInfoActivity extends AppCompatActivity {
    Intent i;
    String url;
    TextView txName;
    TextView txInfo;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_info);
        i = getIntent();
        url = i.getExtras().getString("link");
        txName = (TextView)findViewById(R.id.name);
        txInfo = (TextView)findViewById(R.id.info);
        new ParsingLinkTask().execute(url);

    }

    class ParsingLinkTask extends AsyncTask<String, Void, String> {
        String dog;
        String info;
        @Override
        protected String doInBackground(String... params) {
            Document doc = null;
            Element dname = null;
            Element dinfo = null;

            try {

                doc = Jsoup.connect(params[0]).get();
                dname = doc.select("#main-content div.node-title").first();
                dinfo = doc.select("#main-content div.content p").first();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dog = dname.text();
            info = dinfo.text();

            return title;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            txName.setText(dog);
            txInfo.setText(info);
        }
    }

}
