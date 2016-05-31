package nortti.ru.dogshelter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import nortti.ru.dogshelter.object.Dog;

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String[] urls = new String[]{
            "http://vao-priut.org/category/fotokatalog/sektor",
            "http://vao-priut.org/category/fotokatalog/sektor-b",
            "http://vao-priut.org/category/fotokatalog/sektor-s",
            "http://vao-priut.org/category/fotokatalog/sektor-d",
            "http://vao-priut.org/category/fotokatalog/koshki-priyuta"};
    ArrayList<Dog> dogs;
    LinearLayoutManager llm;
    Bitmap bitmap;
    private ArrayList<Bitmap> allImages = new ArrayList<Bitmap>();
    ArrayList<String> links = new ArrayList<>();
    Dog dog;
    private SwipeRefreshLayout refreshLayout;
    String url;
    RecyclerView recyclerView;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {

        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, urls[sectionNumber]);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        url = getArguments().getString(ARG_SECTION_NUMBER);
        new ParseMyPageTask().execute(url);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv);

        recyclerView.setHasFixedSize(true);

        llm = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(llm);

        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.SwipeContainer);
        refreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent, R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new ParseMyPageTask().execute(url);


            }
        });
        new PlaceholderFragment();

        return rootView;
    }


    class ParseMyPageTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            dogs = new ArrayList<>();
            final String HTTP_VAO_PRIUT_ORG = "http://vao-priut.org";
            StringBuilder sb = new StringBuilder();
            Document doc = null;
            ArrayList<String>ln = new ArrayList<String>();

            String name = null;

            try {

                doc = Jsoup.connect(params[0]).get();
                Elements dogs = doc.select("#main-content div.views-field-title span.field-content a");
                Elements imgs = doc.select("#main-content div.views-field-image-image a img");
                Elements links = doc.select("#main-content div.views-field-title a");



                for (int a = 0; a < dogs.size(); a++) {

                    String imgSrc = imgs.get(a).attr("src");
                    InputStream inp = new java.net.URL(imgSrc).openStream();
                    bitmap = BitmapFactory.decodeStream(inp);

                    PlaceholderFragment.this.dogs.add(new Dog(dogs.get(a).text(),bitmap,HTTP_VAO_PRIUT_ORG+links.get(a).attr("href").toString()));
                }






            } catch (IOException e) {
                e.printStackTrace();
            }

            return name;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(dogs);

            recyclerView.setAdapter(adapter);
            refreshLayout.setRefreshing(false);
        }
    }


}
