package cl.gerardomascayano.appfiestas.model.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

import cl.gerardomascayano.appfiestas.model.Account;
import cl.gerardomascayano.appfiestas.view.activity.LoginActivity;
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
    public void loginWithFacebook(Activity activity, CallbackManager callbackManager) {
        //        mBtnLoginFacebook.setReadPermissions("email", "user_friends", "public_profile");
//        mBtnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.d("respuesta", object.toString());
//                    }
//                });
//
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,email,birthday,friends");
//                request.setParameters(parameters);
//                request.executeAsync();
//
//                Profile profile = Profile.getCurrentProfile();
//                Log.d("respuesta_profile", "First Name:" + profile.getFirstName());
//                Log.d("respuesta_profile", "Last Name:" + profile.getLastName());
//                Log.d("respuesta_profile", "Name:" + profile.getName());
//                Log.d("respuesta_profile", "First Name:" + profile.getProfilePictureUri(800, 600));
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                if (loginPresenter != null) {
//                    loginPresenter.onLoginError("Error: "+error.getMessage());
//                }
//            }
//        });


        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "user_friends", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                Log.d("respuesta_profile", "First Name:" + profile.getFirstName());
                Log.d("respuesta_profile", "Last Name:" + profile.getLastName());
                Log.d("respuesta_profile", "Name:" + profile.getName());
                Log.d("respuesta_profile", "First Name:" + profile.getProfilePictureUri(800, 600));

                if (loginPresenter != null) {
                    loginPresenter.onLoginSuccess(new Account("email-facebook",profile.getName()));
                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                if (loginPresenter != null) {
                    loginPresenter.onLoginError("Error: "+error.getMessage());
                }
            }
        });


    }

    @Override
    public void loginWithGoogle(Activity activity) {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        GoogleSignInClient signInClient = GoogleSignIn.getClient(activity.getApplicationContext(), signInOptions);

        activity.startActivityForResult(signInClient.getSignInIntent(), LoginActivity.REQUEST_LOGIN_GOOGLE);
    }

    @Override
    public void loginActivityResultGoogle(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Log.d("NFO", "Email: " + account.getEmail());
            Log.d("NFO", "Display Name: " + account.getDisplayName());
            Log.d("NFO", "Given Name: " + account.getGivenName());
            Log.d("NFO", "Photo Uri: " + account.getPhotoUrl());
//                    Log.d("NFO","Photo Uri: "+account.getAccount().name);
            if (loginPresenter != null) {
                loginPresenter.onLoginSuccess(new Account(account.getEmail(),account.getDisplayName()));
            }

        } catch (ApiException e) {
            e.printStackTrace();
            if (loginPresenter != null) {
                loginPresenter.onLoginError("signInResult:failed code=" + e.getStatusCode());
            }
            Log.w("ErrorAuth", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void checkLogged(Context context) {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
         GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
         if (account != null){
             if (loginPresenter != null) {
                 loginPresenter.onLoginSuccess(new Account(account.getEmail(),account.getDisplayName()));
             }
         }else{
             // Chequear facebook
             if (AccessToken.getCurrentAccessToken() != null){
                 if (loginPresenter != null) {
                     Profile profile = Profile.getCurrentProfile();
                     loginPresenter.onLoginSuccess(new Account("email-facebook",profile.getName()));
                 }
             }
         }


    }
}
