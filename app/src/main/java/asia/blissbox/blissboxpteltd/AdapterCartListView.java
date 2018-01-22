package asia.blissbox.blissboxpteltd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by bryan on 8/11/2017.
 */

public class AdapterCartListView extends ArrayAdapter<ObjectBoxes> {
    private ArrayList<ObjectBoxes> boxes;
    private Context context;
    private TextView tvName, tvPrice, tvSendTo;
    private ImageView ivThumbnail;

    public AdapterCartListView(Context context, int resource, ArrayList<ObjectBoxes> box) {
        super(context, resource, box);
        boxes = box;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_cart_list, parent, false);
        tvName = rowView.findViewById(R.id.cartTitle);
        tvPrice = rowView.findViewById(R.id.cartPrioe);
        tvSendTo = rowView.findViewById(R.id.cartSendTo);
        ivThumbnail = rowView.findViewById(R.id.cartThumbnail);
        Picasso.with(context).load("https://dev.blissbox.asia/storage/giftboxes/" + boxes.get(position).getThumbnail()).into(ivThumbnail);
        tvName.setText(boxes.get(position).getName());
        tvPrice.setText(String.valueOf(boxes.get(position).getPrice()) + "0");

        return rowView;
    }

}
