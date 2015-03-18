package message;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by orbot on 02.03.15.
 */
public class Message {
    private String message;
    private Date date;
    private String name;

    public Message(String name, Date date, String message) {
        this.date = date;
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public static boolean isReplaceable(String text, String tag) {

        int i = 0;
        boolean norm = true;
        int countOpens = 0;
        int countCloses = 0;
        if((text.indexOf("[" + tag + "]") > text.indexOf("[/" + tag + "]")) || (text.lastIndexOf("[" + tag + "]") > text.lastIndexOf("[/" + tag + "]"))) {
            return false;
        }

        while(i < text.length()) {
            i = text.substring(i).indexOf("[" + tag + "]");
            if (i!=-1) {
                countOpens++;
            }
        }

        i = 0;

        while(i < text.length()) {
            i = text.substring(i).indexOf("[/" + tag + "]");
            if(i!=-1) {
                countCloses++;
            }
        }

        if(countCloses != countOpens)
            norm = false;

        return norm;
    }
}
