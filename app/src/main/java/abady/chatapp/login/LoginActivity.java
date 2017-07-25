package abady.chatapp.login;

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

    }


    public void onRegister(View view) {
        Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        mPresenter.DisableButton(loginButton);

        mPresenter.ShowProgress(LoginActivity.this);

        mPresenter.onSignin(usernameText.getText().toString() , passwordText.getText().toString());

        if(mPresenter.isLoginSuccessful()){
            mPresenter.ShowSuccessfulMessage(relativeLayout);

            mPresenter.MoveToChatActivity(LoginActivity.this);
        }else {
            mPresenter.ShowFailureMessage();
            mPresenter.EnableButton(loginButton);
        }
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {
        
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter =  presenter;
    }
}
