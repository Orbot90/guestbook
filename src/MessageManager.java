import message.Message;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by orbot on 02.03.15.
 */
@WebServlet(urlPatterns = "/guestbook")
public class MessageManager extends HttpServlet {

    SQLConnectivity sqlc;
    Cache cache;

    public MessageManager() throws SQLException, ClassNotFoundException {
        sqlc = new SQLConnectivity();
        cache = new Cache(sqlc.getMessages());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("messages", cache.get());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/guestbook.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String text = req.getParameter("mess");
        Date date = new Date();

        try {
            addMessage(name, date, text);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("messages", cache.get());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/guestbook.jsp");
        rd.forward(req, resp);
    }

    public void addMessage(String name, Date date, String message) throws SQLException {
        Message m = new Message(name, date, message);
        cache.put(m);
        sqlc.addMessage(m);
    }

    public ArrayList<Message> getMessages() {
        return cache.get();
    }


}
