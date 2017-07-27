package abady.chatapp.data;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Maybe;


/**
 * Created by Sayed on 7/26/2017.
 */

public class Firebaseauth {
        private static Firebaseauth instance;
        public synchronized static Firebaseauth getInstance(){
        if(instance == null){
            instance = new Firebaseauth();
        }
            return instance;
        }

   private Firebaseauth(){


    }
   // public Maybe<AuthResult> signIn(String email, String password){
   //     return RxFirebaseAuth.signInWithEmailAndPassword(mAuth , email , password);
   // }

  //  public Maybe<AuthResult> createUser(String email , String password){
   //     return RxFirebaseAuth.createUserWithEmailAndPassword(mAuth , email, password);

  //  }

}
