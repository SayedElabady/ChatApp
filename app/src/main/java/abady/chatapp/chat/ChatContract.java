package abady.chatapp.chat;

import abady.chatapp.BaseView;
import abady.chatapp.login.LoginContract;

/**
 * Created by Sayed on 7/27/2017.
 */

public interface ChatContract {
   interface View extends BaseView<Presenter>{
        void updateUI();
   }

    interface Presenter{

        void sendIsClicked(String Message);

        void signOut();

        void setView(View view);
    }
}
