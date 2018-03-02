package cl.gerardomascayano.appfiestas.presenter;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class InterfacesPresenter {

    // LOGIN
    public interface LoginPresenter{
        void onLoginSuccess();
        void onLoginError(String error);
    }

}
