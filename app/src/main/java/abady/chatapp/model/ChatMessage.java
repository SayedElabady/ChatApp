package abady.chatapp.model;

/**
 * Created by Sayed on 7/27/2017.
 */

public class ChatMessage {
    private String MessageText;

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    private String NickName;

    public ChatMessage(String NickName) {
        this.NickName = NickName;
    }
    public ChatMessage(){

    }
    public String getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(String messageDate) {
        MessageDate = messageDate;
    }

    private String MessageDate;

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public String getMessageSender() {
        return MessageSender;
    }

    public void setMessageSender(String messageSender) {
        MessageSender = messageSender;
    }

    private String MessageSender;
}
