package asia.blissbox.blissboxpteltd;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyProfile extends Fragment {
    Button btnLogout;
    Button btnMyProfile, btnPaymentHist, btnSettings;
    TextView btnMyGiftboxes;
    CardView latestBox;
    LinearLayout latestGift;
    TextView welcomeText;
    SharedPreferences sharedPreferences;


    public FragmentMyProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_my_profile, container, false);
        sharedPreferences = getContext().getSharedPreferences("session", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "User");
        String email = sharedPreferences.getString("email", "example@email.com");

        //Buttons
        btnLogout = view.findViewById(R.id.logout);
        btnSettings = view.findViewById(R.id.btnSettings);
        btnPaymentHist = view.findViewById(R.id.btnPaymentHistory);
        btnMyProfile = view.findViewById(R.id.btnProfile);

        //TextView
        btnMyGiftboxes = view.findViewById(R.id.btnVouchers);
        //CardView
        latestBox = view.findViewById(R.id.latest_voucher);
        //LinearLayout
        latestGift = view.findViewById(R.id.voucher_details);

        welcomeText = view.findViewById(R.id.welcomeName);



        Log.e("name: ", name);
        welcomeText.setText("Welcome " + name + "!");

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Link to Settings page
                Fragment fragment = new FragmentSettings();
                replaceNewFragment(fragment);
            }
        });

        btnPaymentHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Link to payment history page
            }
        });




        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                adb.setTitle("Log out");
                adb.setMessage("Are you sure you want to log out?");
                adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.edit().clear().apply();
                        Fragment fragment = new FragmentLoginSignup();
                        replaceFragment(fragment);
                    }
                });

                adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = adb.create();
                alertDialog.show();

            }
        });


        return view;
    }

    //Changing fragment without adding stack
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, someFragment);
        transaction.commit();
    }

    //changing fragment with added stack
    public void replaceNewFragment(Fragment newFragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left);
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
