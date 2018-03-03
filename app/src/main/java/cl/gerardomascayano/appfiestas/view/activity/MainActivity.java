package cl.gerardomascayano.appfiestas.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.gerardomascayano.appfiestas.R;
import cl.gerardomascayano.appfiestas.view.fragment.AmigosFragment;
import cl.gerardomascayano.appfiestas.view.fragment.EventosFragment;
import cl.gerardomascayano.appfiestas.view.fragment.PerfilFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_menu_main)
    BottomNavigationView mBottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBottomNavView.setOnNavigationItemSelectedListener(this);
        mBottomNavView.setSelectedItemId(R.id.action_eventos);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.action_perfil:
                fragment = PerfilFragment.newInstance();
                break;
            case R.id.action_eventos:
                fragment = EventosFragment.newInstance();
                break;
            case R.id.action_amigos:
                fragment = AmigosFragment.newInstance();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .commitAllowingStateLoss();
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
