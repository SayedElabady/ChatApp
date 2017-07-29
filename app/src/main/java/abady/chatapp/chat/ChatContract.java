package abady.chatapp.chat;

import abady.chatapp.BaseView;
import abady.chatapp.login.LoginContract;

/**
 * Created by Sayed on 7/27/2017.
 */

public interface ChatContract {
   interface View extends BaseView<Presenter>{
        void updateUI();

        void moveToLogInActivity();
   }

    interface Presenter{

        void sendIsClicked(String Message , String currentDateTime);

        void signOutAcc();

        void setView(View view);

        String getUserEmail();

        boolean isUserSignedIn();
    }
}
