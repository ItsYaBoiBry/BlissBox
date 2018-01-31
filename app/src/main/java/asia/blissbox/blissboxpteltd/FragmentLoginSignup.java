package asia.blissbox.blissboxpteltd;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;


public class FragmentLoginSignup extends Fragment {
    LinearLayout loadingScreen;
    TextInputEditText email, password;
    Button login;
    SignInButton gLogin;
    Button fbCustomLogin;
    LoginButton fbLogin;
    TextView errorMessage;
    SharedPreferences sharedPreferences;
    CallbackManager callbackManager;
    private String details;


    public FragmentLoginSignup() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        sharedPreferences = getContext().getSharedPreferences("session", Context.MODE_PRIVATE);


        View view =  inflater.inflate(R.layout.fragment_fragment_login_signup, container, false);
        callbackManager = CallbackManager.Factory.create();
        loadingScreen = view.findViewById(R.id.loadingScreen);
        loadingScreen.setVisibility(View.GONE);
        errorMessage = view.findViewById(R.id.errorMessage);
        email = view.findViewById(R.id.etEmail);
        password = view.findViewById(R.id.etPassword);
        fbLogin = view.findViewById(R.id.fbLogin);
        login = view.findViewById(R.id.blissboxLogin);

        fbCustomLogin = view.findViewById(R.id.customfbLogin);
        fbCustomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbLogin.performClick();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingScreen.setVisibility(View.VISIBLE);
                Login login = new Login();
                login.execute("https://dev.blissbox.asia/api/login");
            }
        });

        gLogin = view.findViewById(R.id.gLogin);
        fbLogin.setReadPermissions(Arrays.asList("public_profile", "email"));
        fbLogin.setFragment(this);
        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("result", String.valueOf(loginResult.getAccessToken()));
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.wtf("LoginActivity", response.toString());


                                // Application code
                                try {
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    Log.e("facebook name: ", name);
                                    Toast.makeText(getContext(), "Email:" + email, Toast.LENGTH_LONG).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

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


        Log.e("resultCode", resultCode + "");
        Log.e("data", data.toString());
        try {
            JSONObject object = new JSONObject(data.toString());
            Log.e("jsonObject graphObject", object.getString("graphObject"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, someFragment);
        transaction.commit();
    }

    private class Login extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            OkHttpClient client = new OkHttpClient();


            RequestBody formBody = new FormEncodingBuilder()
                    .add("email", email.getText().toString())
                    .add("password", password.getText().toString())
                    .build();

            Request request =
                    new com.squareup.okhttp.Request.Builder()
                            .url(urls[0])
                            .post(formBody)
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
            int status;
            String message;
            loadingScreen.setVisibility(View.GONE);

            try {
                JSONObject results = new JSONObject(result);
                status = results.getInt("status");
                Log.e("status", String.valueOf(status));
                if (String.valueOf(status).equals("401")) {
                    Log.e("status 2: ", String.valueOf(status));
                    message = results.getString("message");
                    Log.e("message: ", message);
                    errorMessage.setText(R.string.loginError);

                } else {
                    message = results.getString("message");
                    JSONObject getToken = new JSONObject(message);
                    JSONObject getUser = new JSONObject(results.getString("user"));
                    String token = getToken.getString("token");
                    Log.e("Login status: ", String.valueOf(status));
                    Log.e("Login token: ", token);
                    ObjectUsers users = new ObjectUsers();
                    users.setId(getUser.getInt("id"));
                    users.setRole_id(getUser.getInt("role_id"));
                    int role_id = users.getRole_id();
                    Log.e("User's Role: ", String.valueOf(role_id));

                    if (String.valueOf(role_id).equals("1")) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Log.e("user's first name: ", getUser.getString("first_name"));
                        editor.putString("userJson", getUser.toString());
                        Log.e("userJSON String: ", getUser.toString()) ;
                        editor.putString("name", getUser.getString("first_name"));
                        editor.putString("email", getUser.getString("email"));
                        editor.putString("phone", getUser.getString("phone"));

                        editor.putString("postal_code", getUser.getString("postal_code"));

                        editor.putString("user", getUser.toString());
                        editor.putInt("status", status);
                        editor.putString("token", token);
                        editor.apply();
                        editor.commit();

                        Fragment fragment = new FragmentMyProfile();
                        replaceFragment(fragment);

                    } else if (String.valueOf(role_id).equals("2")) {

                        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                        adb.setTitle("Unavailable");
                        adb.setMessage("Our App is currently unavailable for our merchants.\nYou can access the dashboard via out website.\nSorry for any inconveniences caused");
                        adb.setPositiveButton("Go to website", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String url = "https://dashboard.blissbox.asia";
                                Intent a = new Intent(Intent.ACTION_VIEW);
                                a.setData(Uri.parse(url));
                                startActivity(a);
                            }
                        });

                        AlertDialog alertDialog = adb.create();
                        alertDialog.show();
                    } else if (String.valueOf(role_id).equals("3")) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                        adb.setTitle("Hi " + getUser.getString("first_name"));
                        adb.setMessage("Go fuck yourself");
                        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String url = "https://dashboard.blissbox.asia";
                                Intent a = new Intent(Intent.ACTION_VIEW);
                                a.setData(Uri.parse(url));
                                startActivity(a);
                            }
                        });
                        AlertDialog alertDialog = adb.create();
                        alertDialog.show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
