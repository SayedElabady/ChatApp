package abady.chatapp.data;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import abady.chatapp.model.ChatMessage;
import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Maybe;

/**
 * Created by Sayed on 7/26/2017.
 */

public class Firebase {
    FirebaseAuth mAuth;

    private static Firebase instance;
    public synchronized static Firebase getInstance(){
        if(instance == null){
            instance = new Firebase();
        }
        return instance;
    }
    private Firebase() {
        mAuth = FirebaseAuth.getInstance();
    }

    public Maybe<AuthResult> signIn(String email, String password){
        return RxFirebaseAuth.signInWithEmailAndPassword(mAuth , email , password);
    }

    public Maybe<AuthResult> createUser(String email , String password){
        return RxFirebaseAuth.createUserWithEmailAndPassword(mAuth , email, password);

    }
    public boolean isUserLogged(){

        if(mAuth.getCurrentUser() != null)
            return true;

        return false;
    }

    public void signOut(){
        mAuth.signOut();
    }

    public void sendMessage(String message){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageText(message);
        chatMessage.setMessageSender(mAuth.getCurrentUser().getDisplayName());
        FirebaseDatabase.getInstance()
                .getReference()
                .push()
                .setValue(chatMessage);

    }

}
