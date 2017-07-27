package abady.chatapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;

import abady.chatapp.data.AuthFirebase;
import abady.chatapp.data.Firebaseauth;
import io.reactivex.Maybe;

/**
 * Created by Sayed on 7/26/2017.
 */

public class BaseActivity extends AppCompatActivity{
   private static AuthFirebase instance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = AuthFirebase.getInstance();

    }
    public  Maybe<AuthResult> signIn(String email , String password){
        return instance.signIn(email , password);
    }
    public Maybe<AuthResult> createUser(String email , String password){
        return instance.createUser(email , password);
    }
    void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}
