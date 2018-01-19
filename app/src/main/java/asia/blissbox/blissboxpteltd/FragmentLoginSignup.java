package asia.blissbox.blissboxpteltd;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

public class FragmentLoginSignup extends Fragment {
    TextInputEditText email, password;
    Button login;
    SignInButton gLogin;
    LoginButton fbLogin;
    CallbackManager callbackManager;


    public FragmentLoginSignup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_fragment_login_signup, container, false);
        callbackManager = CallbackManager.Factory.create();

        email = view.findViewById(R.id.etEmail);
        password = view.findViewById(R.id.etPassword);
        fbLogin = view.findViewById(R.id.fbLogin);
        login = view.findViewById(R.id.blissboxLogin);
        gLogin = view.findViewById(R.id.gLogin);
        fbLogin.setReadPermissions("email");
        fbLogin.setFragment(this);
        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("result", String.valueOf(loginResult.getAccessToken()));

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }



}
