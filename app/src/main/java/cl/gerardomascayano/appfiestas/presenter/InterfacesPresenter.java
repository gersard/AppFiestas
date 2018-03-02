package cl.gerardomascayano.appfiestas.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class InterfacesPresenter {

    // LOGIN
    public interface LoginPresenter{
        void onLoginSuccess();
        void onLoginError(String error);
        void onLoginWithFacebook();
        void onLoginwithGoogle(Activity activity);
        void onLoginActivityResult(int requestCode,Intent data);
        void onCheckLogged(Context context);
    }

}
