package abady.chatapp.chat;

import abady.chatapp.BaseActivity;
import abady.chatapp.BaseView;

/**
 * Created by Sayed on 7/27/2017.
 */

public class ChatPresenter extends BaseActivity implements ChatContract.Presenter{

        ChatContract.View mView ;

    @Override
    public void sendIsClicked(String Message) {
        sendMessage(Message);

        mView.updateUI();
    }

    @Override
    public void signOutAcc() {
        signOut();
        mView.moveToLogInActivity();
    }

    @Override
    public void setView(ChatContract.View view) {
        mView = view;
    }

    @Override
    public String getUserEmail() {
        return getUser().getEmail();
    }
}
