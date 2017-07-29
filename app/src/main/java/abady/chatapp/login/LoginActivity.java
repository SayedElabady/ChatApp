package abady.chatapp.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import abady.chatapp.BaseActivity;
import abady.chatapp.R;
import abady.chatapp.chat.ChatActivity;
import abady.chatapp.register.RegisterActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.email_text) EditText usernameText;
    @BindView(R.id.password_text) EditText passwordText;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.activity_login) CoordinatorLayout coordinatorLayout;

    LoginContract.Presenter mPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        mPresenter = new LoginPresenter();
        mPresenter.setView(this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        if(item.getItemId() == R.id.menu_close_app){
            finish();
            System.exit(0);
        }
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.isUserLoggedIn();
    }

    @Override
    public void onBackPressed() {
        quitApp();
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroyView();
        super.onDestroy();
    }

    public void onRegister(View view) {

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
    @OnClick(R.id.login_button)
    public void login() {

        mPresenter.signInIsClicked(usernameText.getText().toString() , passwordText.getText().toString());

    }



    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter =  presenter;
    }

    @Override
    public void showProgress() {
         progressDialog = new ProgressDialog(LoginActivity.this);
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
        Toast.makeText(LoginActivity.this, "Welcome to TaskAPP", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showFailureMessage(String message) {

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Failed to login", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

}
