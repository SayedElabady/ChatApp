package abady.chatapp.register;

import abady.chatapp.BaseView;

/**
 * Created by Sayed on 7/25/2017.
 */

public interface RegisterContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void moveToLoginActivity();

        void showSuccessfulMessage();

        void showFailureMessage(String message);

    }

    interface Presenter{


        void registerIsClicked(String Email, String Password );

        void updateUserName(String UserName);

        void setView(View view);
    }
}
