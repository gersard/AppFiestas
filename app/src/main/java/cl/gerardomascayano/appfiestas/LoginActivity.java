package cl.gerardomascayano.appfiestas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
import cl.gerardomascayano.appfiestas.presenter.InterfacesPresenter;
import cl.gerardomascayano.appfiestas.presenter.LoginPresenterImpl;
import cl.gerardomascayano.appfiestas.view.InterfacesView;

public class LoginActivity extends AppCompatActivity implements InterfacesView.LoginView {

    public static final int REQUEST_LOGIN_GOOGLE = 100;

    @BindView(R.id.logo_login)
    ImageView mLogoLogin;
    @BindView(R.id.btn_login_facebook)
    LoginButton mBtnLoginFacebook;

    private CallbackManager callbackManager;
    private InterfacesPresenter.LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCheckLogged(getApplicationContext());
        loginwithFacebook();
    }


    // FACEBOOK
    private void loginwithFacebook() {
        loginPresenter.onLoginWithFacebook();
        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
        callbackManager = CallbackManager.Factory.create();
        mBtnLoginFacebook.setReadPermissions("email", "user_friends", "public_profile");
        mBtnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("respuesta", object.toString());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();

                Profile profile = Profile.getCurrentProfile();
                Log.d("respuesta_profile", "First Name:" + profile.getFirstName());
                Log.d("respuesta_profile", "Last Name:" + profile.getLastName());
                Log.d("respuesta_profile", "Name:" + profile.getName());
                Log.d("respuesta_profile", "First Name:" + profile.getProfilePictureUri(800, 600));
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
        if (loginPresenter != null) {
            loginPresenter.onLoginwithGoogle(LoginActivity.this);
        }
    }

    // CREAR CUENTA
    @OnClick(R.id.btn_login_crear_cuenta)
    public void onBtnLoginCrearCuentaClicked() {


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_LOGIN_GOOGLE) {
                if (loginPresenter != null) {
                    loginPresenter.onLoginActivityResult(requestCode, data);
                }
            }
        }
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Login Exitoso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, "Login Error: "+error, Toast.LENGTH_SHORT).show();
    }
}
