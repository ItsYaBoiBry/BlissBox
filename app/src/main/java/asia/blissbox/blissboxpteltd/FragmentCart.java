package asia.blissbox.blissboxpteltd;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCart extends Fragment {
    ListView lvCart;
    ArrayAdapter aa;
    ArrayList<ObjectBoxes> boxes;
    Button btnPayment;
    double totalPrice = 0;
    LinearLayout linearLayout;


    public FragmentCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_cart, container, false);

        lvCart = view.findViewById(R.id.lvCartItems);
        DBHelper dbHelper = new DBHelper(getContext());
        btnPayment = view.findViewById(R.id.proceedToPayment);
        btnPayment.setTextColor(Color.WHITE);
        boxes = dbHelper.getCartData();
        aa = new AdapterCartListView(getContext(), R.layout.custom_cart_list, boxes);
        lvCart.setAdapter(aa);

        if (boxes.isEmpty()) {
            btnPayment.setText("Cart is Empty");
        } else {
            for (int i = 0; i < boxes.size(); i++) {
                totalPrice = totalPrice + boxes.get(i).getPrice();
            }
            btnPayment.setText("Total Price: SGD" + totalPrice + "0" + "\nProceed to Payment");
        }

        lvCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                Intent intent = new Intent(getContext(), ActivityViewCartProduct.class);
//                intent.putExtra("box", boxes.get(position));
//                startActivity(intent);
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ActivityPayment.class);
//                intent.putExtra("price", totalPrice);
//                startActivity(intent);

            }
        });

        return view;
    }

}
