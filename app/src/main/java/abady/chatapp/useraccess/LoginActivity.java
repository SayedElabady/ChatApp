package abady.chatapp.useraccess;

import android.app.Activity;
import android.app.ProgressDialog;
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
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements AccessContract.Login{

    @BindView(R.id.email_text) EditText usernameText;
    @BindView(R.id.password_text) EditText passwordText;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.activity_login) RelativeLayout relativeLayout;

    AccessContract.Presenter AccessPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    public void onRegister(View view) {
        Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        loginButton.setEnabled(false);

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


    }

    @Override
    public void onLoginSuccess() {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, "Successfully Signin!", Snackbar.LENGTH_SHORT);
        snackbar.show();
        Intent intent = new Intent(LoginActivity.this , ChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailure() {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, "Invaild Password or email!", Snackbar.LENGTH_SHORT);
        snackbar.show();
        loginButton.setEnabled(true);
    }

    @Override
    public void setPresenter(@NonNull AccessContract.Presenter presenter) {
        AccessPresenter =  presenter;
    }
}
