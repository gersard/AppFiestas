package cl.gerardomascayano.appfiestas.presenter;

import cl.gerardomascayano.appfiestas.model.interactor.InterfacesInteractor;
import cl.gerardomascayano.appfiestas.model.interactor.LoginInteractorImpl;
import cl.gerardomascayano.appfiestas.view.InterfacesView;

/**
 * Created by Gerardo on 01-03-2018.
 */

public class LoginPresenterImpl implements InterfacesPresenter.LoginPresenter{

    private InterfacesView.LoginView loginView;
    private InterfacesInteractor.LoginInteractor loginInteractor;

    public LoginPresenterImpl(InterfacesView.LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginError(String error) {

    }
}
