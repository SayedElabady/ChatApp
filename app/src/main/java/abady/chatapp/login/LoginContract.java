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

     interface Login extends BaseView<Presenter> {

         void showProgress();

         void moveToChatActivity();

         void showSuccessfulMessage();

         void showFailureMessage();

     }


    interface Presenter{

        void onSignin(String Email, String Password);







    }
}