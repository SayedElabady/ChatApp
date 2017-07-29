package abady.chatapp.data;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import abady.chatapp.model.ChatMessage;
import abady.chatapp.util.Utils;
import durdinapps.rxfirebase2.RxFirebaseAuth;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import durdinapps.rxfirebase2.RxFirebaseUser;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

import static android.content.ContentValues.TAG;

/**
 * Created by Sayed on 7/26/2017.
 */

public class Firebase {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    Utils utils;

    private static Firebase instance;
    public synchronized static Firebase getInstance(){
        if(instance == null){
            instance = new Firebase();
        }
        return instance;
    }
    private Firebase() {
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        utils = new Utils();
    }

    public Maybe<AuthResult> signIn(String email, String password){
        return RxFirebaseAuth.signInWithEmailAndPassword(mAuth , email , password);
    }

    public Maybe<AuthResult> createUser(String email , String password , String NickName){

//        firebaseDatabase.getReference()
//                .child("nicknames")
//                .child(utils.replace(email))
//                .push()
//                .setValue(new ChatMessage(NickName));
        return RxFirebaseAuth.createUserWithEmailAndPassword(mAuth , email, password);

    }

    public String getNickName(String email){
         Object chatMessage = firebaseDatabase.getReference()
                .child("nicknames")
                .child(utils.replace(email));
                ChatMessage chatMessage1 = (ChatMessage) chatMessage;
        return chatMessage1.getNickName();
                //.toString();
    }
    public boolean isUserLogged(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null)
            return true;

        return false;
    }

    public void signOut(){
        if(mAuth != null)
        mAuth.signOut();
    }

    public void sendMessage(String message , String currentDateTime){
        String Email = mAuth.getCurrentUser().getEmail();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageText(message);
       chatMessage.setMessageDate(currentDateTime);

        chatMessage.setMessageSender(Email);
       // String NickName = firebaseDatabase.getReference().child("nicknames").child(Email).toString();
       // chatMessage.setNickName(NickName);
        firebaseDatabase
                .getReference()
                .child("Messages")
                .push()
                .setValue(chatMessage);

    }

    public FirebaseDatabase getDatabase(){
        return firebaseDatabase;
    }


   public void updateUserInfo(String UserName) {

       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
               .setDisplayName(UserName)
               .build();

       user.updateProfile(profileUpdates)
               .addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()) {
                           Log.d(TAG, "User profile updated.");
                       }
                   }
               });

   }

   public FirebaseUser getUser(){
       return mAuth.getCurrentUser();
   }
}
