package cl.gerardomascayano.appfiestas.view;

import cl.gerardomascayano.appfiestas.model.Account;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class InterfacesView {

    // LOGIN
    public interface LoginView{
        void loginSuccess(Account account);
        void loginError(String error);
    }

    // ACTUALIZAR DATOS
    public interface ActualizarDatosView{
        void updateSuccess();
        void updateError(String error);
        void showProgress();
        void dismissProgress();
    }

}
