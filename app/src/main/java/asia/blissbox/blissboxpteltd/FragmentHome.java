package asia.blissbox.blissboxpteltd;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    LinearLayout featureBox1,featureBox2,featureBox3,featureBox4,featureBox5;
    ImageButton btnGastro, btnStay, btnWellness, btnEnergize, btnMulti;
    TextView featureTitle1, featureTitle2, featureTitle3, featureTitle4, featureTitle5;
    ImageView ivFeature1, ivFeature2, ivFeature3, ivFeature4, ivFeature5;
    TextView featurePrice1, featurePrice2, featurePrice3, featurePrice4, featurePrice5;
    TextView featureDesc1, featureDesc2, featureDesc3, featureDesc4, featureDesc5;
    ObjectBoxes feature1, feature2, feature3, feature4, feature5;
    LinearLayout pbHome;

    private List<ObjectBoxes> allBoxes;



    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_home, container, false);

        String url = "https://dev.blissbox.asia/api/giftbox/all";

        GetGifts getGifts = new GetGifts();
        getGifts.execute(new String[]{url});

        pbHome = (LinearLayout) view.findViewById(R.id.loadingScreen);

        featureBox1 = (LinearLayout) view.findViewById(R.id.featureBox1);
        featureBox2 = (LinearLayout) view.findViewById(R.id.featureBox2);
        featureBox3 = (LinearLayout) view.findViewById(R.id.featureBox3);
        featureBox4 = (LinearLayout) view.findViewById(R.id.featureBox4);
        featureBox5 = (LinearLayout) view.findViewById(R.id.featureBox5);

        featureTitle1 = (TextView) view.findViewById(R.id.featureBoxOneTitle);
        featureTitle2 = (TextView) view.findViewById(R.id.featureBoxTwoTitle);
        featureTitle3 = (TextView) view.findViewById(R.id.featureBoxThreeTitle);
        featureTitle4 = (TextView) view.findViewById(R.id.featureBoxFourTitle);
        featureTitle5 = (TextView) view.findViewById(R.id.featureBoxFiveTitle);

        featurePrice1 = (TextView) view.findViewById(R.id.featureboxOnePrice);
        featurePrice2 = (TextView) view.findViewById(R.id.featureboxTwoPrice);
        featurePrice3 = (TextView) view.findViewById(R.id.featureboxThreePrice);
        featurePrice4 = (TextView) view.findViewById(R.id.featureboxFourPrice);
        featurePrice5 = (TextView) view.findViewById(R.id.featureboxFivePrice);

        featureDesc1 = (TextView) view.findViewById(R.id.featureBoxOneDesc);
        featureDesc2 = (TextView) view.findViewById(R.id.featureBoxTwoDesc);
        featureDesc3 = (TextView) view.findViewById(R.id.featureboxThreeDesc);
        featureDesc4 = (TextView) view.findViewById(R.id.featureboxFourDesc);
        featureDesc5 = (TextView) view.findViewById(R.id.featureboxFiveDesc);

        ivFeature1 = (ImageView) view.findViewById(R.id.ivFeatureOne);
        ivFeature2 = (ImageView) view.findViewById(R.id.ivFeatureTwo);
        ivFeature3 = (ImageView) view.findViewById(R.id.ivFeaturethree);
        ivFeature4 = (ImageView) view.findViewById(R.id.ivFeatureFour);
        ivFeature5 = (ImageView) view.findViewById(R.id.ivFeatureFive);

        btnGastro = (ImageButton) view.findViewById(R.id.gastroButton);
        btnEnergize = (ImageButton) view.findViewById(R.id.energizeButton);
        btnWellness = (ImageButton) view.findViewById(R.id.wellnessButton);
        btnStay = (ImageButton) view.findViewById(R.id.stayButton);
        btnMulti = (ImageButton) view.findViewById(R.id.multiButton);

        btnGastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentViewProduct();
                Bundle bundleObject = new Bundle();
                bundleObject.putInt("universe_id",2);
                bundleObject.putString("universe","Indulge");
                fragment.setArguments(bundleObject);
                replaceFragment(fragment);
            }
        });

        btnEnergize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentViewProduct();
                Bundle bundleObject = new Bundle();
                bundleObject.putInt("universe_id",3);
                bundleObject.putString("universe","Energize");
                fragment.setArguments(bundleObject);
                replaceFragment(fragment);
            }
        });

        btnWellness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentViewProduct();
                Bundle bundleObject = new Bundle();
                bundleObject.putInt("universe_id",5);
                bundleObject.putString("universe","Relax");
                fragment.setArguments(bundleObject);
                replaceFragment(fragment);
            }
        });

        btnStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentViewProduct();
                Bundle bundleObject = new Bundle();
                bundleObject.putInt("universe_id",4);
                bundleObject.putString("universe","Escape");
                fragment.setArguments(bundleObject);
                replaceFragment(fragment);
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentViewProduct();
                Bundle bundleObject = new Bundle();
                bundleObject.putInt("universe_id",1);
                bundleObject.putString("universe","Multitheme");
                fragment.setArguments(bundleObject);
                replaceFragment(fragment);
            }
        });



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
                    bocs.setThumbnail(box.getString("thumbnail"));
                    Log.e("giftBox_Add", "Giftbox_thumbnail " + String.valueOf(box.getString("thumbnail") + " Added"));
                    bocs.setInitial(box.getString("initial"));
                    Log.e("GiftBox_Add", "Giftbox_initial " + String.valueOf(box.getString("initial") + " Added"));
                    bocs.setPrice((box.getInt("price")));
                    Log.e("GiftBox_Add", "Giftbox_price " + String.valueOf(box.getInt("price") + " Added"));
                    bocs.setDescription(box.getString("description"));
                    Log.e("GiftBox_Add", "Giftbox_Description " + String.valueOf(box.getString("description") + " Added"));
                    bocs.setPdf_url(box.getString("pdf_url"));
                    bocs.setReview(box.getInt("review"));
                    bocs.setDeleted_at(box.getString("deleted_at"));
                    bocs.setCreated_at(box.getString("created_at"));
                    bocs.setUpdated_at(box.getString("updated_at"));
                    allBoxes.add(bocs);
                    Log.e("allboxes length", String.valueOf(allBoxes.size()));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("Looping", "loop Ended");
            feature1 = allBoxes.get(0);
            feature2 = allBoxes.get(1);
            feature3 = allBoxes.get(2);
            feature4 = allBoxes.get(3);
            feature5 = allBoxes.get(5);

            featureTitle1.setText(feature1.getName());
            featureDesc1.setText(feature1.getDescription());
            featurePrice1.setText("SGD "+String.valueOf(feature1.getPrice()));
            Picasso.with(getContext()).load("https://dev.blissbox.asia/storage/giftboxes/"+feature1.getThumbnail()).into(ivFeature1);
            featureBox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ActivityViewProduct.class);
                    intent.putExtra("gift",feature1);
                    startActivity(intent);
                }
            });

            featureTitle2.setText(feature2.getName());
            featureDesc2.setText(feature2.getDescription());
            featurePrice2.setText("SGD "+String.valueOf(feature2.getPrice()));
            Picasso.with(getContext()).load("https://dev.blissbox.asia/storage/giftboxes/"+feature2.getThumbnail()).into(ivFeature2);
            featureBox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ActivityViewProduct.class);
                    intent.putExtra("gift",feature2);
                    startActivity(intent);
                }
            });

            featureTitle3.setText(feature3.getName());
            featureDesc3.setText(feature3.getDescription());
            featurePrice3.setText("SGD "+String.valueOf(feature3.getPrice()));
            Picasso.with(getContext()).load("https://dev.blissbox.asia/storage/giftboxes/"+feature3.getThumbnail()).into(ivFeature3);
            featureBox3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ActivityViewProduct.class);
                    intent.putExtra("gift",feature3);
                    startActivity(intent);
                }
            });

            featureTitle4.setText(feature4.getName());
            featureDesc4.setText(feature4.getDescription());
            featurePrice4.setText("SGD "+String.valueOf(feature4.getPrice()));
            Picasso.with(getContext()).load("https://dev.blissbox.asia/storage/giftboxes/"+feature4.getThumbnail()).into(ivFeature4);
            featureBox4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ActivityViewProduct.class);
                    intent.putExtra("gift",feature4);
                    startActivity(intent);
                }
            });

            featureTitle5.setText(feature5.getName());
            featureDesc5.setText(feature5.getDescription());
            featurePrice5.setText("SGD "+String.valueOf(feature5.getPrice()));
            Picasso.with(getContext()).load("https://dev.blissbox.asia/storage/giftboxes/"+feature5.getThumbnail()).into(ivFeature5);
            featureBox5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ActivityViewProduct.class);
                    intent.putExtra("gift",feature5);
                    startActivity(intent);
                }
            });
        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
