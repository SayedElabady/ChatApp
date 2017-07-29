package abady.chatapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

    public void quitApp(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Quit the App")
                .setMessage("Are you sure you want to quit the app?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

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
