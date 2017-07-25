package abady.chatapp.useraccess;

import abady.chatapp.BaseView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Sayed on 7/25/2017.
 */

public interface AccessContract {

     interface Login extends BaseView<Presenter> {

          void onLoginSuccess();

          void onLoginFailure();





    }
    interface Register extends BaseView<Presenter>{

        void onRegisterSuccess();

        void onRegisterFailure();

    }
    interface Presenter{

        void onSignin(String Email, String Password);

        void onSignup(String Email, String Password);

    }
}
