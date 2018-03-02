package cl.gerardomascayano.appfiestas.model.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class InterfacesInteractor {

    // LOGIN
    public interface LoginInteractor{
        void loginWithFacebook();
        void loginWithGoogle(Activity activity);
        void loginActivityResultGoogle(Intent data);
        void checkLogged(Context context);
    }


}
