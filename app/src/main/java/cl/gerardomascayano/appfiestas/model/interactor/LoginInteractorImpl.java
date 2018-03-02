package cl.gerardomascayano.appfiestas.model.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import cl.gerardomascayano.appfiestas.LoginActivity;
import cl.gerardomascayano.appfiestas.presenter.InterfacesPresenter;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class LoginInteractorImpl implements InterfacesInteractor.LoginInteractor {

    private InterfacesPresenter.LoginPresenter loginPresenter;

    public LoginInteractorImpl(InterfacesPresenter.LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void loginWithFacebook() {

    }

    @Override
    public void loginWithGoogle(Activity activity) {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        GoogleSignInClient signInClient = GoogleSignIn.getClient(activity.getApplicationContext(), signInOptions);

        activity.startActivityForResult(signInClient.getSignInIntent(), LoginActivity.REQUEST_LOGIN_GOOGLE);
    }

    @Override
    public void loginActivityResultGoogle(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Log.d("NFO", "Email: " + account.getEmail());
            Log.d("NFO", "Display Name: " + account.getDisplayName());
            Log.d("NFO", "Given Name: " + account.getGivenName());
            Log.d("NFO", "Photo Uri: " + account.getPhotoUrl());
//                    Log.d("NFO","Photo Uri: "+account.getAccount().name);
            if (loginPresenter != null) {
                loginPresenter.onLoginSuccess();
            }

        } catch (ApiException e) {
            e.printStackTrace();
            if (loginPresenter != null) {
                loginPresenter.onLoginError("signInResult:failed code=" + e.getStatusCode());
            }
            Log.w("ErrorAuth", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void checkLogged(Context context) {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
         GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
         if (account != null){
             if (loginPresenter != null) {
                 loginPresenter.onLoginSuccess();
             }
         }else{
             // Chequear facebook
         }


    }
}
