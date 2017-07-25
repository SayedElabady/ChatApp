package abady.chatapp.register;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sayed on 7/25/2017.
 */

public class RegisterFirebase implements RegisterContract.Model{

    boolean ret;
    @Override
    public boolean isRegisteredSuccessful(String email, String Password) {
       ret = false;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, Password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           ret = true;

                        } else {
                            ret = false;

                        }

                    }
                });
        return ret;
    }

}
