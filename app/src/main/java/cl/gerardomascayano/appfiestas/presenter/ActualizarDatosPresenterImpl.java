package cl.gerardomascayano.appfiestas.presenter;

import cl.gerardomascayano.appfiestas.model.Account;
import cl.gerardomascayano.appfiestas.view.InterfacesView;

/**
 * Created by Gerardo on 02-03-2018.
 */

public class ActualizarDatosPresenterImpl implements InterfacesPresenter.ActualizarDatosPresenter {

    private InterfacesView.ActualizarDatosView view;

    public ActualizarDatosPresenterImpl(InterfacesView.ActualizarDatosView view) {
        this.view = view;
    }

    @Override
    public void updateSuccess() {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void updateData(Account account) {
        if (view != null) {
            view.showProgress();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    view.updateError("Error");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
