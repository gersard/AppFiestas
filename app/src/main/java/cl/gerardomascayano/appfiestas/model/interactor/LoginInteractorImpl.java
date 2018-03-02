package cl.gerardomascayano.appfiestas.model.interactor;

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
    public void loginWithGoogle() {

    }
}
