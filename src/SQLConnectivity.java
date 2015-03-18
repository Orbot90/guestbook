
import message.Message;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by orbot on 02.03.15.
 */
public class SQLConnectivity {
    private Connection connection;
    String drvrName = "com.mysql.jdbc.Driver";

    public SQLConnectivity() throws ClassNotFoundException, SQLException {
        Class.forName(drvrName);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/guestbook", "root", "");
    }

    public ArrayList<Message> getMessages(int first) throws SQLException {
        ArrayList<Message> messArr = new ArrayList<Message>();
        ResultSet rs;
        Statement st = connection.createStatement();


        rs = st.executeQuery("SELECT * FROM messages ORDER BY id DESC LIMIT " + first + ", 5");

        while (rs.next()) {
            String name = rs.getString("name");
            String message = rs.getString("message");
            java.util.Date date = (java.util.Date)rs.getObject("date");


            Message m = new Message(name, date, message);
            messArr.add(m);
        }


        return messArr;
    }

    public void addMessage(Message m) throws SQLException {
        Statement st = connection.createStatement();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String name = m.getName();
        String message = m.getMessage();
        String date = sdf.format(m.getDate());

        st.execute("INSERT INTO messages(name, date, message) VALUES('" + name + "', '" + date + "', '" + message + "')");
    }

    public int rowNum() throws SQLException {
        Integer rows = 0;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM messages");
        while(rs.next())
            rows = rs.getInt(1);
        return rows;
    }


}
