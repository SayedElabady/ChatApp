package abady.chatapp.register;

import abady.chatapp.BaseView;

/**
 * Created by Sayed on 7/25/2017.
 */

public interface RegisterContract {

    interface View extends BaseView<Presenter> {

        void onRegisterSuccess();

        void onRegisterFailure();

    }
    interface Presenter{


        void onSignup(String Email, String Password);

    }
}
