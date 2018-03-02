package cl.gerardomascayano.appfiestas.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import cl.gerardomascayano.appfiestas.LoginActivity;
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
        if (loginView != null) {
            loginView.loginSuccess();
        }
    }

    @Override
    public void onLoginError(String error) {
        if (loginView != null) {
            loginView.loginError(error);
        }
    }

    @Override
    public void onLoginWithFacebook() {

    }

    @Override
    public void onLoginwithGoogle(Activity activity) {
        if (loginInteractor != null) {
            loginInteractor.loginWithGoogle(activity);
        }
    }

    @Override
    public void onLoginActivityResult(int requestCode, Intent data) {
        if (requestCode == LoginActivity.REQUEST_LOGIN_GOOGLE){
            if (loginInteractor != null) {
                loginInteractor.loginActivityResultGoogle(data);
            }
        }
    }

    @Override
    public void onCheckLogged(Context context) {
        if (loginInteractor != null) {
            loginInteractor.checkLogged(context);
        }
    }
}
