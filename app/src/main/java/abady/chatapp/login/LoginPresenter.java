package abady.chatapp.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.firebase.FirebaseNetworkException;

import abady.chatapp.BaseActivity;
import abady.chatapp.chat.ChatActivity;
import abady.chatapp.register.RegisterActivity;

/**
 * Created by Sayed on 7/25/2017.
 */

public class LoginPresenter extends BaseActivity implements LoginContract.Presenter {
        LoginContract.View mView;






    @Override
    public void signInIsClicked(String Email, String Password) {
        mView.showProgress();
        signIn(Email , Password)
                .subscribe(authResult -> {
                        mView.showSuccessfulMessage();
                        mView.moveToChatActivity();
                        }
                    , throwable -> {
                    Throwable exception = throwable;
                    String message = exception.getMessage();
                    mView.hideProgress();
                    mView.showFailureMessage(message);
                });
    }

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void isUserLoggedIn() {

        if(isUserLogged() == true){
            mView.moveToChatActivity();
        }

    }

    @Override
    public void destroyView() {
        mView = null;
    }


}





