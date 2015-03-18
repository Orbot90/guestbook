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

   
}
