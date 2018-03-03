package cl.gerardomascayano.appfiestas.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import net.steamcrafted.loadtoast.LoadToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.gerardomascayano.appfiestas.R;
import cl.gerardomascayano.appfiestas.model.Account;
import cl.gerardomascayano.appfiestas.presenter.ActualizarDatosPresenterImpl;
import cl.gerardomascayano.appfiestas.presenter.InterfacesPresenter;
import cl.gerardomascayano.appfiestas.util.Funciones;
import cl.gerardomascayano.appfiestas.util.ui.CircleTransformPicasso;
import cl.gerardomascayano.appfiestas.view.InterfacesView;

public class ActualizarDatosActivity extends AppCompatActivity implements InterfacesView.ActualizarDatosView{

    @BindView(R.id.iv_avatar_actualizar)
    ImageView mIvAcatar;
    @BindView(R.id.edit_nombre_actualizar)
    EditText mEditNombre;
    @BindView(R.id.edit_email_actualizar)
    EditText mEditEmail;
    @BindView(R.id.edit_telefono_actualizar)
    EditText mEditTelefono;
    private LoadToast loadToast;
    private InterfacesPresenter.ActualizarDatosPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);
        ButterKnife.bind(this);
        presenter = new ActualizarDatosPresenterImpl(this);
        retrieveAccountData();
    }

    private void retrieveAccountData() {
        try{
            Bundle bundleAccount = getIntent().getExtras();
            if (bundleAccount != null){
                Account currentAccount = new Gson().fromJson(bundleAccount.getString("account"),Account.class);
                if (currentAccount != null) {
                    mEditNombre.setText(currentAccount.getName());
                    mEditEmail.setText(currentAccount.getEmail());
                    Picasso.with(getApplicationContext()).load(currentAccount.getImageUrl())
                            .transform(new CircleTransformPicasso())
                            .into(mIvAcatar);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btn_aceptar_actualizar)
    public void onActualizarClicked() {
        if (presenter != null) {
            presenter.updateData(null);
        }
    }

    @Override
    public void updateSuccess() {
        if (loadToast != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadToast.success();
                    Intent intent = new Intent(ActualizarDatosActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });

        }
    }

    @Override
    public void updateError(String error) {
        if (loadToast != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadToast.error();
                }
            });

        }
    }

    @Override
    public void showProgress() {
        if (presenter != null) {
            initLoadToast();
        }
    }

    @Override
    public void dismissProgress() {
        if (loadToast != null) {
            loadToast.hide();
        }

    }


    private void initLoadToast(){
        loadToast = new LoadToast(ActualizarDatosActivity.this);
        loadToast.setText("Cargando");
        loadToast.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        loadToast.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
        loadToast.setProgressColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        loadToast.setTranslationY((int) (metrics.heightPixels - Funciones.dpToPx(getApplicationContext(),120)));
        loadToast.show();
    }
}
