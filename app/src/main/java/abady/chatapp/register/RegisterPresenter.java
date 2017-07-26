package abady.chatapp.register;

import abady.chatapp.BaseActivity;

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
                .subscribe(authResult -> {
                            mView.showSuccessfulMessage();
                            mView.moveToChatActivity();
                        }
                        , throwable -> {
                            Throwable exception = throwable;
                            String message = exception.getMessage();

                            mView.showFailureMessage();

                        });

    }
}
