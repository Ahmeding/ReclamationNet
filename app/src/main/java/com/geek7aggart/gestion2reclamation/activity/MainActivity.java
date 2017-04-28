package com.geek7aggart.gestion2reclamation.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geek7aggart.gestion2reclamation.R;
import com.geek7aggart.gestion2reclamation.utils.DBhelper;
import com.geek7aggart.gestion2reclamation.utils.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    private ProgressDialog pDialog;
    private EditText inputEmail, inputPassword;
    private static final String LOGIN_URL = "http://192.168.1.102:8081/Rest/login.php";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private String PARAM_EMAIL_VALUE;
    private String PARAM_PASSWORD_VALUE;
    Button btnlogin;
    private SessionManager session;
    private DBhelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.btnLogin);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new DBhelper(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, ListeReclamationActivity.class);
            startActivity(intent);
            finish();
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                //check for empty data in the form

                if (!(email.toString().trim().isEmpty()) && !(password.toString().trim().isEmpty())) {
                    PARAM_EMAIL_VALUE = email;
                    PARAM_PASSWORD_VALUE = password;

                    login(PARAM_EMAIL_VALUE, PARAM_PASSWORD_VALUE);
                } else {
                    Snackbar.make(btnlogin, "Please enter the credential", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

    }

    private void login(String email, String password) {
        String tag_string_req = "req_login";
        //Connecte to Server
        AsyncHttpClient client = new AsyncHttpClient();
        //Params
        RequestParams params = new RequestParams();
        params.put(PARAM_EMAIL, email);
        params.put(PARAM_PASSWORD, password);
        //Requete
        client.post(LOGIN_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                pDialog.setMessage("se connecter ...");
                showDialog();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                hideDialog();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("statusCode", statusCode + "");
                Log.d("onSuccess", "" + parseData(responseBody));
                if (parseData(responseBody)) {
                    session.setLogin(true);
                    //Toast.makeText(MainActivity.this, "Bienvenue", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, ListeReclamationActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "connection au serveur a échoué !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("onFailure", "" + error.getMessage());
                Toast.makeText(MainActivity.this, "connection au serveur a échoué !", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean parseData(byte[] src) {
        boolean _login = false;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(src);
            if (rootNode.has("user")) {
                _login = true;
                JsonNode user = rootNode.path("user");
                JsonNode name = user.path("name");
                JsonNode email = user.path("email");
                JsonNode password = user.path("password");
                db.addUser(name.toString(), email.toString(), password.toString());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return _login;
    }


    private void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }
}
