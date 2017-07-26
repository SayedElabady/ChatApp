package abady.chatapp.data;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import durdinapps.rxfirebase2.RxCompletableHandler;
import durdinapps.rxfirebase2.RxFirebaseAuth;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import durdinapps.rxfirebase2.RxFirebaseQuery;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

import static com.google.android.gms.internal.zzt.TAG;

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

   private Firebase(){
        mAuth = FirebaseAuth.getInstance();

    }
    public Maybe<AuthResult> signIn(String email, String password){
        return RxFirebaseAuth.signInWithEmailAndPassword(mAuth , email , password);
    }

    public Maybe<AuthResult> createUser(String email , String password){
        return RxFirebaseAuth.createUserWithEmailAndPassword(mAuth , email, password);

    }

}
