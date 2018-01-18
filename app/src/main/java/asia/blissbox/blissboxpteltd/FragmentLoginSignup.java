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

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class FragmentLoginSignup extends Fragment {
    TextInputEditText email, password;
    Button login;
    SignInButton gLogin;
    LoginButton fbLogin;
    CallbackManager callbackManager;
    PrefUtil prefUtil;


    public FragmentLoginSignup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        callbackManager = CallbackManager.Factory.create();
        View view =  inflater.inflate(R.layout.fragment_fragment_login_signup, container, false);

        prefUtil = new PrefUtil(getActivity());

        email = view.findViewById(R.id.etEmail);
        password = view.findViewById(R.id.etPassword);
        fbLogin = view.findViewById(R.id.fbLogin);
        login = view.findViewById(R.id.blissboxLogin);
        gLogin = view.findViewById(R.id.gLogin);
        fbLogin.setReadPermissions(Arrays.asList("public_profile", "email"));
        fbLogin.setFragment(this);
        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("result", String.valueOf(loginResult.getAccessToken()));
                String accessToken = loginResult.getAccessToken().getToken();
                prefUtil.saveAccessToken(accessToken);
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject jsonObject,
                                                    GraphResponse response) {

                                // Getting FB User Data
                                Bundle facebookData = getFacebookData(jsonObject);


                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,email,gender");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                deleteAccessToken();
            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));


            prefUtil.saveFacebookUserInfo(object.getString("first_name"),
                    object.getString("last_name"), object.getString("email"),
                    object.getString("gender"), profile_pic.toString());

        } catch (Exception e) {
            Log.d("error", e.getLocalizedMessage());
        }

        return bundle;
    }

    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null) {
                    //User logged out
                    prefUtil.clearToken();
                    LoginManager.getInstance().logOut();
                }
            }
        };
    }

}
