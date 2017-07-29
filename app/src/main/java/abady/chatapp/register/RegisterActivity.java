package abady.chatapp.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import abady.chatapp.BaseActivity;
import abady.chatapp.R;
import abady.chatapp.chat.ChatActivity;
import abady.chatapp.login.LoginActivity;
import abady.chatapp.login.LoginContract;
import abady.chatapp.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
public class RegisterActivity extends BaseActivity implements RegisterContract.View {


    @BindView(R.id.email_register_text) EditText emailRegisterText;
    @BindView(R.id.password_register_text) EditText passwordRegisterText;
    @BindView(R.id.repeated_password_text) EditText repeatedPasswordText;
    @BindView(R.id.activity_register) RelativeLayout relativeLayout;
    RegisterContract.Presenter mPresenter;
    Utils utils ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mPresenter = new RegisterPresenter();
        mPresenter.setView(this);
        utils = new Utils();
    }



    boolean Validate(){
        return (passwordRegisterText.getText().toString().equals( repeatedPasswordText.getText().toString()));
    }
    public void Register(View view) {
        if(Validate() == true){
            String Email = emailRegisterText.getText().toString();
            String Password = passwordRegisterText.getText().toString();
            // dummy till i update the project and do it.
            String NickName = "";
            mPresenter.registerIsClicked(Email , Password , NickName);


        } else {
            Snackbar snackbar = Snackbar
                    .make(relativeLayout, "Password is not identical!", Snackbar.LENGTH_SHORT);
            snackbar.show();

        }
    }

    @Override
    public void onBackPressed() {
        quitApp();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroyView();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registering...");
        progressDialog.show();
    }

    @Override
    public void moveToLoginActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSuccessfulMessage() {

        Snackbar snackbar = Snackbar
                .make(relativeLayout, "Successfully Registered!", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
    @OnClick(R.id.login_button)
    public void loginIsClicked(){
        moveToLoginActivity();
    }
    @Override
    public void showFailureMessage(String message) {
        Toast.makeText(RegisterActivity.this, "Failed Registration: "+message, Toast.LENGTH_SHORT).show();


    }
}
