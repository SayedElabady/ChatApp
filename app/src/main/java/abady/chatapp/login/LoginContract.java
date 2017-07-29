package abady.chatapp.login;

import android.content.Context;
import android.text.Layout;
import android.widget.Button;
import android.widget.RelativeLayout;

import abady.chatapp.BaseView;


/**
 * Created by Sayed on 7/25/2017.
 */

public interface LoginContract {

     interface View extends BaseView<Presenter> {

         void showProgress();

         void moveToChatActivity();

         void showSuccessfulMessage();

         void showFailureMessage(String message);

         void hideProgress();
     }


    interface Presenter{

        void signInIsClicked(String Email, String Password);

        void setView(View view);

        void isUserLoggedIn();

    }
}
