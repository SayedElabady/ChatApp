package abady.chatapp.util;

/**
 * Created by Sayed on 7/29/2017.
 */

public class Utils {
    public String formatString(String s){
        String ret = "";
        for(int i = 0 ; i < s.length() ; ++i){
            char x = s.charAt(i);
            if(x == '@')
                break;
            ret += x;
        }

        return ret;
    }
    public String replace(String x){
        return x.replace('.' , '*');
    }
}
