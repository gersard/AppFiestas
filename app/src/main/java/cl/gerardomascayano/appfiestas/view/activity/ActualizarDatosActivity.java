package cl.gerardomascayano.appfiestas.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.gerardomascayano.appfiestas.R;
import cl.gerardomascayano.appfiestas.model.Account;

public class ActualizarDatosActivity extends AppCompatActivity {

    @BindView(R.id.iv_avatar_actualizar)
    ImageView mIvAcatar;
    @BindView(R.id.edit_nombre_actualizar)
    EditText mEditNombre;
    @BindView(R.id.edit_email_actualizar)
    EditText mEditEmail;
    @BindView(R.id.edit_telefono_actualizar)
    EditText mEditTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);
        ButterKnife.bind(this);

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
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btn_aceptar_actualizar)
    public void onActualizarClicked() {

    }
}
