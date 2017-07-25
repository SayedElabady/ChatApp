package abady.chatapp.useraccess;

/**
 * Created by Sayed on 7/25/2017.
 */

public interface AccessContract {

     interface Login{

          void onLoginSuccess();

          void onLoginFailure();

    }
    interface Register{

        void onRegisterSuccess();

        void onRegisterFailure();

    }
    interface Presenter{

        void onSignin(String Email, String Password);

        void onSignup(String Name,String Email, String Password );

    }
}
