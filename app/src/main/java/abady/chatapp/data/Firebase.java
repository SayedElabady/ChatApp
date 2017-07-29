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
    }

    public Maybe<AuthResult> signIn(String email, String password){
        return RxFirebaseAuth.signInWithEmailAndPassword(mAuth , email , password);
    }

    public Maybe<AuthResult> createUser(String email , String password){
        return RxFirebaseAuth.createUserWithEmailAndPassword(mAuth , email, password);

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

    public void sendMessage(String message){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageText(message);
        String Email = mAuth.getCurrentUser().getEmail();
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
      // RxFirebaseUser.updateProfile(mAuth.getCurrentUser(), new UserProfileChangeRequest.Builder()
              // .setDisplayName(UserName).build());
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
   /* String UID = mAuth.getCurrentUser().getUid();
       DatabaseReference users = firebaseDatabase.getReference("users").child(UID);
       ChatMessage chatMessage = new ChatMessage(Email ,  UserName);
       RxFirebaseDatabase.setValue(users , chatMessage);
*/
   }

   public FirebaseUser getUser(){
       return mAuth.getCurrentUser();
   }
}
