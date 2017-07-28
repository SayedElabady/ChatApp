package abady.chatapp.chat;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;

import abady.chatapp.BaseActivity;
import abady.chatapp.R;
import abady.chatapp.login.LoginActivity;
import abady.chatapp.model.ChatMessage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ChatContract.View {

    @BindView(R.id.messageArea)
    EditText input;
    @BindView(R.id.list_of_messages)
    ListView listView;
    //@BindView(R.id.message_text)TextView messageText;
    //@BindView(R.id.user_name) TextView messageSender;

    TextView messageSender;
    TextView messageText;
    ChatContract.Presenter mPresenter;
    private FirebaseListAdapter<ChatMessage> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mPresenter = new ChatPresenter();
        mPresenter.setView(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                updateUI();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
       // addListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out) {
           mPresenter.signOutAcc();

        }

        return true;
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.sendButton)
    public void sendClicked(){
        String Message = input.getText().toString();
        mPresenter.sendIsClicked(Message);

        input.setText("");

    }

    @Override
    public void updateUI() {

        listAdapter = new FirebaseListAdapter<ChatMessage>(this , ChatMessage.class
        , R.layout.message_by_me, getDatabase().getReference()) {
            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                ChatMessage model = getItem(position);

              String  name = model.getMessageSender();
               String  userEmail = mPresenter.getUserEmail();
                if (view == null) {
                    if(!userEmail.equals(name)){
                        view = mActivity.getLayoutInflater().inflate(R.layout.message_by_him, viewGroup, false);
                    }else {
                        model.setMessageSender("Me");
                        view = mActivity.getLayoutInflater().inflate(mLayout, viewGroup, false);
                    }
                }



                // Call out to subclass to marshall this model into the provided view
                populateView(view, model, position);
                return view;
            }

            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                String  name = model.getMessageSender();
                String Message = model.getMessageText();

                messageSender = (TextView) v.findViewById(R.id.user_name);
                messageText = (TextView) v.findViewById(R.id.message_text);

                messageSender.setText(name);
                messageText.setText(Message);
            }
        };
        listView.setAdapter(listAdapter);

    }

    @Override
    public void moveToLogInActivity() {
        Intent intent = new Intent(ChatActivity.this , LoginActivity.class);
        startActivity(intent);
    }
}
