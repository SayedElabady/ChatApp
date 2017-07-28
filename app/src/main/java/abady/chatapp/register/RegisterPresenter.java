package abady.chatapp.register;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import abady.chatapp.BaseActivity;
import abady.chatapp.login.LoginActivity;

/**
 * Created by Sayed on 7/26/2017.
 */

public class RegisterPresenter extends BaseActivity implements RegisterContract.Presenter{
    RegisterContract.View mView ;

    @Override
    public void registerIsClicked(String Email, String Password , String UserName) {
        mView.showProgress();

        createUser(Email , Password)

                .subscribe(authResult -> {
                           mView.showSuccessfulMessage();
                            mView.moveToLoginActivity();
                        }
                        , throwable -> {
                            Throwable exception = throwable;
                            String message = exception.getMessage();

                            mView.showFailureMessage(message);

                        })
        ;


    }

    @Override
    public void updateUserName(String UserName) {
        updateUserInfo( UserName);
    }

    @Override
    public void setView(RegisterContract.View view) {
        mView = view;
    }
}
