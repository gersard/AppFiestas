package cl.gerardomascayano.appfiestas.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.gerardomascayano.appfiestas.R;
import cl.gerardomascayano.appfiestas.model.Account;
import cl.gerardomascayano.appfiestas.presenter.InterfacesPresenter;
import cl.gerardomascayano.appfiestas.presenter.LoginPresenterImpl;
import cl.gerardomascayano.appfiestas.view.InterfacesView;

public class LoginActivity extends AppCompatActivity implements InterfacesView.LoginView {

    public static final int REQUEST_LOGIN_GOOGLE = 100;

    @BindView(R.id.logo_login)
    ImageView mLogoLogin;
    //    @BindView(R.id.btn_login_facebook)
//    Button mBtnLoginFacebook;
    private CallbackManager callbackManager;
    private InterfacesPresenter.LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCheckLogged(getApplicationContext());
    }


    // FACEBOOK
    @OnClick(R.id.btn_login_facebook)
    public void loginwithFacebook() {
        if (loginPresenter != null) {
            callbackManager = CallbackManager.Factory.create();
            loginPresenter.onLoginWithFacebook(this, callbackManager);
        }
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
        if (callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

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
    public void loginSuccess(Account account) {
        Intent intent = new Intent(getApplicationContext(),ActualizarDatosActivity.class);
        intent.putExtra("account",new Gson().toJson(account));
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, "Login Error: " + error, Toast.LENGTH_SHORT).show();
    }
}
