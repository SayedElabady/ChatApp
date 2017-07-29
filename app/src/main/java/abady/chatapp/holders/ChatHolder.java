package abady.chatapp.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import abady.chatapp.R;

/**
 * Created by Sayed on 7/29/2017.
 */

public class ChatHolder extends RecyclerView.ViewHolder{
    private final TextView messageSender , messageText , currentTime;

    public ChatHolder(View itemView) {
        super(itemView);
       messageSender = (TextView) itemView.findViewById(R.id.user_name);
        messageText = (TextView) itemView.findViewById(R.id.message_text);
        currentTime = (TextView) itemView.findViewById(R.id.time);
    }


    public void setName(String name){
        messageSender.setText(name);
    }
    public void setMessage(String message){
        messageText.setText(message);
    }

    public void setCurrentTime(String currenttime) {
        currentTime.setText(currenttime);
    }
}
