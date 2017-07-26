package abady.chatapp.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import abady.chatapp.R;
import abady.chatapp.chat.ChatActivity;
import abady.chatapp.login.LoginActivity;
import abady.chatapp.login.LoginContract;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    @BindView(R.id.email_register_text) EditText emailRegisterText;
    @BindView(R.id.password_register_text) EditText passwordRegisterText;
    @BindView(R.id.repeated_password_text) EditText repeatedPasswordText;
    @BindView(R.id.activity_register) LinearLayout linearLayout;

    RegisterContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mPresenter = new RegisterPresenter();
    }
    boolean Validate(){
        return (passwordRegisterText.getText().toString().equals( repeatedPasswordText.getText().toString()));
    }

    public void Register(View view) {
        if(Validate()== true){
            mPresenter.registerIsClicked(emailRegisterText.getText().toString() , passwordRegisterText.getText().toString());

        } else {
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Password is not identical!", Snackbar.LENGTH_SHORT);
            snackbar.show();

        }
    }



    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void showProgress() {
        ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    @Override
    public void moveToChatActivity() {
        Intent intent = new Intent(RegisterActivity.this, ChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSuccessfulMessage() {
        Snackbar snackbar = Snackbar
                .make(linearLayout, "Successfully Registered!", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showFailureMessage() {
        Snackbar snackbar = Snackbar
                .make(linearLayout, "Failed to Regsiter", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
