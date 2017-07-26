package abady.chatapp.register;

import abady.chatapp.BaseView;

/**
 * Created by Sayed on 7/25/2017.
 */

public interface RegisterContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void moveToChatActivity();

        void showSuccessfulMessage();

        void showFailureMessage();

    }
    interface Model  {
        boolean isRegisteredSuccessful(String email , String Password);
    }
    interface Presenter{


        void registerIsClicked(String Email, String Password);

    }
}
