package abady.chatapp.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.widget.Button;
import android.widget.RelativeLayout;

import abady.chatapp.chat.ChatActivity;
import abady.chatapp.register.RegisterActivity;

/**
 * Created by Sayed on 7/25/2017.
 */

public class LoginPresenter extends AppCompatActivity implements LoginContract.Presenter {


    @Override
    public void onSignin(String Email, String Password) {

    }

    @Override
    public void EnableButton(Button button) {
        button.setEnabled(true);
    }

    @Override
    public void DisableButton(Button button) {
        button.setEnabled(false);
    }


    @Override
    public boolean isLoginSuccessful() {
        return false;
    }

    @Override
    public void ShowFailureMessage() {

    }

    @Override
    public void MoveToChatActivity(Context context) {
        Intent intent = new Intent(context , ChatActivity.class);
        startActivity(intent);

    }

    @Override
    public void MoveToRegisterActivity(Context context) {
        Intent intent = new Intent(context , RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void ShowProgress(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    @Override
    public void ShowSuccessfulMessage(RelativeLayout layout) {
        Snackbar snackbar = Snackbar
                .make(layout, "Successfully Signin!", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
