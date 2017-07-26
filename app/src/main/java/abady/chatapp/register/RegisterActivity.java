package abady.chatapp.register;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import abady.chatapp.R;
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
    }
    boolean Validate(){
        return (passwordRegisterText.toString() == repeatedPasswordText.toString());
    }

    public void Register(View view) {
        if(!Validate()){
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Successfully Signin!", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else {
            mPresenter.onSignup(emailRegisterText.toString() , passwordRegisterText.toString());

        }
    }

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    public void onRegisterFailure() {

    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }


}