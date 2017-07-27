package abady.chatapp.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseListAdapter;

import abady.chatapp.BaseActivity;
import abady.chatapp.R;
import abady.chatapp.model.ChatMessage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ChatContract.View {

    @BindView(R.id.input) EditText input;
    ChatContract.Presenter mPresenter;

    private FirebaseListAdapter<ChatMessage> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mPresenter = new ChatPresenter();
        mPresenter.setView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out) {
           mPresenter.signOut();
        }

        return true;
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.fab)
    public void sendClicked(){
        String Message = input.getText().toString();
        mPresenter.sendIsClicked(Message);

        input.setText("");



    }

    @Override
    public void updateUI() {

    }
}
