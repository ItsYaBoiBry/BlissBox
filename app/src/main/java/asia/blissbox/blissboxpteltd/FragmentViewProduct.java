package asia.blissbox.blissboxpteltd;


import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewProduct extends Fragment {
    private static final String TAG = "ViewProduct";
    LinearLayout ll;
    GridLayoutManager gridLayoutManager;
    int universe_id;
    String getTitle;
    TextView title;
    LinearLayout pbHome;
    ImageButton btnback;
    private RecyclerView recyclerView;
    private AdapterBoxes adapter;
    private List<ObjectBoxes> boxesList;
    private List<ObjectBoxes> allBoxes;


    public FragmentViewProduct() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_view_product, container, false);
        universe_id = getArguments().getInt("universe_id");
        getTitle = getArguments().getString("universe");

        pbHome = view.findViewById(R.id.loadingScreen);

        recyclerView = view.findViewById(R.id.recycler_view);
        boxesList = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new AdapterBoxes(getContext(), boxesList);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        ll = view.findViewById(R.id.viewProductsFragment);
        title = view.findViewById(R.id.viewProductTitle);
        title.setText(getTitle);
        btnback = view.findViewById(R.id.arrow_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        String url = "https://dev.blissbox.asia/api/giftbox/all";
        GetGifts getGifts = new GetGifts();
        getGifts.execute(url);

        return view;
    }


    private class GetGifts extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            OkHttpClient client = new OkHttpClient();
            com.squareup.okhttp.Request request =

                    new com.squareup.okhttp.Request.Builder()
                            .url(urls[0])
                            .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjI3MGY3NWFjOWU5ZTNhNTNhZGZjMmRkN2Q5NTlhMzc5NWNmZTQ3ZTA3OGY5MTI4NTYzZTZjMzliNzkyM2QxMGZiZDdkYzlmNWM0NWRlMWE1In0.eyJhdWQiOiI1IiwianRpIjoiMjcwZjc1YWM5ZTllM2E1M2FkZmMyZGQ3ZDk1OWEzNzk1Y2ZlNDdlMDc4ZjkxMjg1NjNlNmMzOWI3OTIzZDEwZmJkN2RjOWY1YzQ1ZGUxYTUiLCJpYXQiOjE1MTAyMTU2MzgsIm5iZiI6MTUxMDIxNTYzOCwiZXhwIjoxNTQxNzUxNjM4LCJzdWIiOiI3NiIsInNjb3BlcyI6W119.CXN2XeXEqOfrr4AcyAbb9cEL8lP5xOaL3woPQ3KzXkNFaSJpET0-sA1PJGjSERPcpXGU9SU_2drfllnHxGQmrL4PccUHQE8hTwAr8quQ6a5JAivuP2_n9X9gEx951Swy7XIFibFKR3lfLW19xhySbJgz3wLdhQDplfe4EyFZDkabOX37D5Mijms4DHFoY4BONsw3QvMfa2twoYLvYDUFvDhGTxJumvk9v_POR6PYNYsfetJ4O65IA-qonTT6bV1VBah78fun5Zv552aTvBfbcfzo1HgLeBkVXmsmaWc81QOuunardAugXtrW2uryDxVBb4jdgfurNQ8wby4xR6MtQK8rprFv2SlWmEQ7Yog8JBzB2uJbnOlR442Cfo7xG-WCqbFJqfMj3E9w67e-7R19mPlORSF8vSBcGbI_U6iSEgTjvY0EGDCxoYalL_S24iIVHWLadC8nTD8TcKHIe9mjUvYgClBN2dd8Rqy20TH3CwDHC-N03MIK8YvRm_icsodsM4wPbRJWK4NYibSIMrxekYdzrhp8N3JsoVLoh3hgW33aTi6YIVujwYbL4bdM5B8oLN0wEPdXV6lqVOsS9zAexSF4e5h4DtPZrPuMv8ybfbk0rHLmMV55EjaqbogRb4iJIEIYrzoPny44DFmf-uouhnvGrTWcVjaNSUZR7-clJOo")
                            .build();
            com.squareup.okhttp.Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.isSuccessful()) {
                try {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            Log.e("jsonString", result);
            pbHome.setVisibility(View.GONE);
            allBoxes = new ArrayList<>();
            try {
                JSONObject gifts = new JSONObject(result);
                Log.e("JSONRetrieve", gifts.toString());
                JSONArray jsonBoxes = gifts.getJSONArray("result");
                Log.e("JSON length", String.valueOf(jsonBoxes.length()));
                for (int i = 0; i < jsonBoxes.length(); i++) {
                    ObjectBoxes bocs = new ObjectBoxes();
                    JSONObject box = jsonBoxes.getJSONObject(i);
                    Log.e("looping", "looping through " + String.valueOf(i));
                    Log.e("giftbox_id", String.valueOf(box.getInt("id")));
                    bocs.setGiftboxId(box.getInt("id"));
                    Log.e("giftBox_Add", "Giftbox_id " + String.valueOf(box.getInt("id") + " Added"));
                    bocs.setName(box.getString("name"));
                    Log.e("giftBox_Add", "Giftbox_name " + String.valueOf(box.getString("name") + " Added"));
                    bocs.setUniverseId(box.getInt("universe_id"));
                    Log.e("giftBox_Add", "Giftbox_universe_id " + String.valueOf(box.getInt("universe_id") + " Added"));
                    bocs.setThumbnail(box.getString(

                            "thumbnail"));
                    Log.e("giftBox_Add", "Giftbox_thumbnail " + String.valueOf(box.getString("thumbnail") + " Added"));
                    bocs.setInitial(box.getString("initial"));
                    Log.e("GiftBox_Add", "Giftbox_initial " + String.valueOf(box.getString("initial") + " Added"));
                    bocs.setPrice((box.getInt("price")/10));
                    Log.e("GiftBox_Add", "Giftbox_price " + String.valueOf(box.getInt("price") + " Added"));
                    bocs.setDescription(box.getString("description"));
                    Log.e("GiftBox_Add", "Giftbox_Description " + String.valueOf(box.getString("description") + " Added"));
                    bocs.setPdf_url(box.getString("pdf_url"));
                    bocs.setReview(box.getInt("review"));
                    bocs.setDeleted_at(box.getString("deleted_at"));
                    bocs.setCreated_at(box.getString("created_at"));
                    bocs.setUpdated_at(box.getString("updated_at"));
                    allBoxes.add(bocs);
                }
                Log.e("Looping", "loop Ended");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            universe_id = getArguments().getInt("universe_id");
            Log.e("universe_id", String.valueOf(universe_id));
            int heightPixels = Resources.getSystem().getDisplayMetrics().heightPixels;
            Log.e("box size", String.valueOf(allBoxes.size()));

            for (int j = 0; j < allBoxes.size(); j++) {

                if ((allBoxes.get(j).getUniverseId()) == universe_id) {
                    ObjectBoxes boxs = allBoxes.get(j);
                    boxesList.add(boxs);

                } else {

                }
            }
            Log.e("boxList size", String.valueOf(boxesList.size()));
            if (boxesList.size() == 0)

            {
                TextView tvListNull = new TextView(getContext());
                tvListNull.setText("More Boxes Coming Soon!");
                tvListNull.setAllCaps(true);
                tvListNull.setPadding(0, (heightPixels / 3), 0, 0);
                tvListNull.setGravity(Gravity.CENTER);
                tvListNull.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                ll.addView(tvListNull);
            } else

            {

            }

            adapter.notifyDataSetChanged();

        }
    }


}
