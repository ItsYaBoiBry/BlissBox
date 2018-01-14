package asia.blissbox.blissboxpteltd;

/**
 * Created by bryan on 11/1/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterBoxes extends RecyclerView.Adapter<AdapterBoxes.ViewHolder> {

    private List<ObjectBoxes> boxesList;
    private Context context;
    String TAG = "BoxesAdapter";


    public AdapterBoxes(Context context, List<ObjectBoxes> boxesList) {
        this.context = context;
        this.boxesList = boxesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.box_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ObjectBoxes boxes = boxesList.get(position);

        //         + "\nId: " + (boxes.getGiftboxId()));
        //
        Log.e("giftbox link", "https://dev.blissbox.asia/storage/giftboxes/" + boxes.getThumbnail());
        Picasso.with(context).load("https://dev.blissbox.asia/storage/giftboxes/" + boxes.getThumbnail()).into(holder.thumbnail);

        //if set online use this code
        //Glide.with(context).load(boxList.get(position).getThumbnail());
        holder.title.setText(boxesList.get(position).getName());
        holder.price.setText("SGD "+String.valueOf(boxesList.get(position).getPrice()));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              Toast.makeText(context, "You clicked " + boxes.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ActivityViewProduct.class);
                intent.putExtra("gift", boxes);
                Log.e("AdapterBoxes", boxes.getName() + "\nID: " + boxes.getGiftboxId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return boxesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView thumbnail;
        public RelativeLayout linearLayout;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.price);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            linearLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);

        }
    }
}