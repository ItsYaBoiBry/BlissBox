package asia.blissbox.blissboxpteltd;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyProfile extends Fragment {
    Button btnLogout;
    TextView welcomeText, userEmail;
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

        btnLogout = view.findViewById(R.id.logout);
        welcomeText = view.findViewById(R.id.welcomeName);
        userEmail = view.findViewById(R.id.userEmail);

        Log.e("name: ", name);
        welcomeText.setText("Welcome " + name + "!");
        userEmail.setText(email);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                adb.setTitle("Log out");
                adb.setMessage("Are you sure you want to log out?");
                adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.edit().clear().commit();
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

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, someFragment);
        transaction.commit();
    }

}
