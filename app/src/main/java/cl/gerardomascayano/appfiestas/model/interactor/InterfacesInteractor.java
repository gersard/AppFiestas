package cl.gerardomascayano.appfiestas.model.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.facebook.CallbackManager;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class InterfacesInteractor {

    // LOGIN
    public interface LoginInteractor{
        void loginWithFacebook(Activity activity, CallbackManager callbackManager);
        void loginWithGoogle(Activity activity);
        void loginActivityResultGoogle(Intent data);
        void checkLogged(Context context);
    }


}
