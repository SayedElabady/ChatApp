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




    }
    interface Model  {
        boolean isLoginSuccessful(String email , String Password);
    }

    interface Presenter{

        void onSignin(String Email, String Password);

        void EnableButton(Button button);

        void DisableButton(Button button);


        boolean isLoginSuccessful();

        void ShowFailureMessage();

        void MoveToChatActivity(Context context);

        void MoveToRegisterActivity(Context context);

        void ShowProgress(Context context);

        void ShowSuccessfulMessage(RelativeLayout layout);




    }
}
