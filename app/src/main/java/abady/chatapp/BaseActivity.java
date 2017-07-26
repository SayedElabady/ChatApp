package abady.chatapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;

import abady.chatapp.data.Firebase;
import io.reactivex.Maybe;

/**
 * Created by Sayed on 7/26/2017.
 */

public class BaseActivity extends AppCompatActivity{
   private Firebase instance;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        instance = Firebase.getInstance();
    }
  public  Maybe<AuthResult> signIn(String email , String password){
        return instance.signIn(email , password);
    }
    public Maybe<AuthResult> createUser(String email , String password){
        return instance.signIn(email , password);
    }
    void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}
