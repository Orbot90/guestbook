
import message.Message;

import java.util.ArrayList;

/**
 * Created by orbot on 02.03.15.
 */
public class Cache {
    ArrayList<Message> messages;


    public Cache(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void put(Message m) {
        messages.add(m);
    }

    public ArrayList<Message> get() {
        return messages;
    }
}
