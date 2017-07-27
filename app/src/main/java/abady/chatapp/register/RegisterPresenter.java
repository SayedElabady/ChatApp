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



    public RegisterPresenter(){
        mView = new RegisterActivity();
    }

    @Override
    public void registerIsClicked(String Email, String Password) {
        createUser(Email , Password)
                .doOnSuccess(
                        authResult ->  mView.showSuccessfulMessage()
                )
                .doOnComplete(
                        () ->   mView.moveToChatActivity()
                ).doOnError(
                        authResult -> mView.showFailureMessage("Hello")
                 )
                .subscribe(authResult -> {
                         //   mView.showSuccessfulMessage();
                            mView.moveToChatActivity();
                        }
                        , throwable -> {
                            Throwable exception = throwable;
                            String message = exception.getMessage();


                            mView.showFailureMessage(message);

                        })
        ;

    }
}
