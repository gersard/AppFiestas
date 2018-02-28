package cl.gerardomascayano.appfiestas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.logo_login)
    ImageView mLogoLogin;
    @BindView(R.id.btn_login_facebook)
    LoginButton mBtnLoginFacebook;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginwithFacebook();
    }



    // FACEBOOK
    private void loginwithFacebook() {
        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
        callbackManager = CallbackManager.Factory.create();
        mBtnLoginFacebook.setReadPermissions("email","user_friends","public_profile");
        mBtnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("respuesta",object.toString());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields","id,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();

                Profile profile = Profile.getCurrentProfile();
                Log.d("respuesta_profile","First Name:"+profile.getFirstName());
                Log.d("respuesta_profile","Last Name:"+profile.getLastName());
                Log.d("respuesta_profile","Name:"+profile.getName());
                Log.d("respuesta_profile","First Name:"+profile.getProfilePictureUri(800,600));
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    // GOOGLE
    @OnClick(R.id.btn_login_google)
    public void onBtnLoginGoogleClicked() {

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        // GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // updateUI(account);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        GoogleSignInClient signInClient = GoogleSignIn.getClient(getApplicationContext(), signInOptions);

        startActivityForResult(signInClient.getSignInIntent(), 100);

    }

    // CREAR CUENTA
    @OnClick(R.id.btn_login_crear_cuenta)
    public void onBtnLoginCrearCuentaClicked() {


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {

                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d("NFO", "Email: " + account.getEmail());
                    Log.d("NFO", "Display Name: " + account.getDisplayName());
                    Log.d("NFO", "Given Name: " + account.getGivenName());
                    Log.d("NFO", "Photo Uri: " + account.getPhotoUrl());
//                    Log.d("NFO","Photo Uri: "+account.getAccount().name);

                } catch (ApiException e) {
                    e.printStackTrace();
                    Log.w("ErrorAuth", "signInResult:failed code=" + e.getStatusCode());
                }

            }
        }
    }
}
