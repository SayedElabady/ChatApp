package abady.chatapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import abady.chatapp.data.Firebase;
import io.reactivex.Maybe;

/**
 * Created by Sayed on 7/26/2017.
 */

public class BaseActivity extends AppCompatActivity{
   private static Firebase instance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = Firebase.getInstance();

    }


    public  Maybe<AuthResult> signIn(String email , String password){
        return instance.signIn(email , password);
    }


    public String getNickName(String email){
        return instance.getNickName(email);
    }
    public Maybe<AuthResult> createUser(String email , String password , String UserName){
        return instance.createUser(email , password , UserName);
    }


    void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show());
    }


    public boolean isUserLogged(){
        return instance.isUserLogged();
    }


    public void signOut(){
        instance.signOut();
    }

    public void sendMessage(String Message , String currentDateTime){
        instance.sendMessage(Message, currentDateTime);
    }

    public FirebaseDatabase getDatabase(){
        return instance.getDatabase();
    }

    public void updateUserInfo(String UserName){
        instance.updateUserInfo( UserName);
    }

    public FirebaseUser getUser(){
        return instance.getUser();
    }

}
