package cl.gerardomascayano.appfiestas.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.facebook.CallbackManager;

import cl.gerardomascayano.appfiestas.model.Account;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class InterfacesPresenter {

    // LOGIN
    public interface LoginPresenter{
        void onLoginSuccess(Account account);
        void onLoginError(String error);
        void onLoginWithFacebook(Activity activity, CallbackManager callbackManager);
        void onLoginwithGoogle(Activity activity);
        void onLoginActivityResult(int requestCode,Intent data);
        void onCheckLogged(Context context);
    }

}
