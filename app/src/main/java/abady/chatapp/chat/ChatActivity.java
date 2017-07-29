package abady.chatapp.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import abady.chatapp.BaseActivity;
import abady.chatapp.R;
import abady.chatapp.holders.ChatHolder;
import abady.chatapp.login.LoginActivity;
import abady.chatapp.model.ChatMessage;
import abady.chatapp.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ChatContract.View {

    @BindView(R.id.messageArea)
    EditText input;
    @BindView(R.id.list_of_messages)
    RecyclerView recyclerView;


    ChatContract.Presenter mPresenter;

    private ListAdapter adapter;
    private ArrayList<ChatMessage> arrayList;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);


        mPresenter = new ChatPresenter();
        mPresenter.setView(this);

        arrayList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ListAdapter(this , arrayList , mPresenter.getUserEmail());
        recyclerView.setAdapter(adapter);
        getDatabase().getReference().child("Messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getValue() != null){
                    HashMap newMessage = (HashMap) dataSnapshot.getValue();
                    ChatMessage newChatMessage = new ChatMessage();
                    String name = (String) newMessage.get("messageSender");
                    String message = (String) newMessage.get("messageText");
                    String date = (String) newMessage.get("messageDate");
                    newChatMessage.setMessageSender(name);
                    newChatMessage.setMessageText(message);
                    newChatMessage.setMessageDate(date);
                 //   newChatMessage.setNickName(getNickName(name));
                    arrayList.add(newChatMessage);
                    adapter.notifyDataSetChanged();
                    linearLayoutManager.scrollToPosition(arrayList.size() - 1);

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                adapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mPresenter.isUserSignedIn())
            moveToLogInActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
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
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        mPresenter.sendIsClicked(Message , currentDateTimeString);

        input.setText("");

    }

    @Override
    public void updateUI() {


        adapter.notifyDataSetChanged();

    }

    @Override
    public void moveToLogInActivity() {
        Intent intent = new Intent(ChatActivity.this , LoginActivity.class);
        startActivity(intent);
    }
}
class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    ArrayList<ChatMessage> chat;
    Utils utils;
    String email;
    public ListAdapter(Context context , ArrayList<ChatMessage> chat , String email){
        this.context = context;
        this.chat = chat;
        this.email = email;
        utils = new Utils();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.message_by_me, parent, false);
            return new ChatHolder(view);

        } else if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.message_by_him, parent, false);
            return new ChatHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(holder instanceof ChatHolder){
           String name = chat.get(position).getMessageSender();
           String message = chat.get(position).getMessageText();
           String time = chat.get(position).getMessageDate();
          name = utils.formatString(name);
           if(getItemViewType(position) == 0)
                ((ChatHolder) holder).setName("Me");
           else
           ((ChatHolder) holder).setName(name);
           ((ChatHolder) holder).setMessage(message);
           ((ChatHolder) holder).setCurrentTime(time);
       }
    }
    @Override
    public int getItemViewType(int position) {
        if(chat.get(position).getMessageSender().equals( email)) return 0;
        else return  1;

    }
    @Override
    public int getItemCount() {
        return chat.size();
    }
}
