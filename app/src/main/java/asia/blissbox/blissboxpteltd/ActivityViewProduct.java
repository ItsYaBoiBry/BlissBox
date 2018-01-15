package asia.blissbox.blissboxpteltd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivityViewProduct extends AppCompatActivity {


    TextView tvTitle, tvDescription, tvPrice;
    ImageView ivThumbnail;
    Button btnAddToCart, btnExplore;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        btnBack = findViewById(R.id.arrow_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Quicksand-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        tvTitle = findViewById(R.id.viewProductTitle);
        tvDescription = findViewById(R.id.viewProductDescription);
        tvPrice = findViewById(R.id.viewProductPrice);
        btnAddToCart = findViewById(R.id.addToCart);
        btnExplore = findViewById(R.id.exploreBoxNonCart);
        ivThumbnail = findViewById(R.id.imgThumbnail);
        TextView tvReview = findViewById(R.id.viewProductReviews);
        RatingBar rbReview = findViewById(R.id.viewProductRatings);

        Intent getIntent = getIntent();
        final ObjectBoxes boxes = (ObjectBoxes) getIntent.getSerializableExtra("gift");

//        final ObjectBoxes box = (ObjectBoxes) getIntent.getSerializableExtra("addBoxToCart");

        String title = boxes.getName();
        String image = boxes.getThumbnail();
        String description = boxes.getDescription();
        String price = String.valueOf(boxes.getPrice());
        tvTitle.setText(title);
        tvPrice.setText(price);
        tvDescription.setText(description);

//        tvTitle.setText(box.getName());
//        tvDescription.setText(box.getDescription());
//        tvPrice.setText(String.valueOf(box.getPrice())+"0");

        tvReview.setText(String.valueOf(boxes.getReview()) + " Reviews");
        rbReview.setRating(boxes.getReview());

//        Log.e("rating", String.valueOf(box.getReview()));

        Picasso.with(ActivityViewProduct.this).load("https://dev.blissbox.asia/storage/giftboxes/"+image).into(ivThumbnail);

//        Log.e("path","https://dev.blissbox.asia/images/"+box.getThumbnail());

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(ActivityViewProduct.this);
                dbHelper.insertIntoCart(boxes);
                finish();
            }
        });

//        btnExplore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivityViewProduct.this, ActivityExperience.class);
//                intent.putExtra("box_id",box.getGiftboxId());
//                startActivity(intent);
//            }
//        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

}