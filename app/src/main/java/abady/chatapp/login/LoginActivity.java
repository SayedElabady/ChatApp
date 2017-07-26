package abady.chatapp.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import abady.chatapp.R;
import abady.chatapp.chat.ChatActivity;
import abady.chatapp.register.RegisterActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.Login{

    @BindView(R.id.email_text) EditText usernameText;
    @BindView(R.id.password_text) EditText passwordText;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.activity_login) RelativeLayout relativeLayout;

    LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter();

    }


    public void onRegister(View view) {

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {

        mPresenter.onSignin(usernameText.getText().toString() , passwordText.getText().toString());


    }



    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter =  presenter;
    }

    @Override
    public void showProgress() {
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    @Override
    public void moveToChatActivity() {
        Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
        startActivity(intent);
    }
    @Override
    public void showSuccessfulMessage() {
        Snackbar snackbar = Snackbar

                .make(relativeLayout, "Successfully login!", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showFailureMessage() {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, "Failed to login", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

}
