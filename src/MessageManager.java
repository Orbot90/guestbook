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
@WebServlet("/gb/*")
public class MessageManager extends HttpServlet {

    SQLConnectivity sqlc;


    public MessageManager() throws SQLException, ClassNotFoundException {
        sqlc = new SQLConnectivity();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNum = (Integer)req.getAttribute("pagenum");
        Integer pages = 0;
        if(pageNum == null)
            pageNum = 0;
        try {
            int rows = sqlc.rowNum();
            pages = rows/5;
            if(rows%5 != 0) {
                pages++;
            }
            req.setAttribute("messages", sqlc.getMessages(5 * pageNum));
            req.setAttribute("pages", pages);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/guestbook.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("username");
        String text = req.getParameter("mess");
        Integer pages = 0;


            Date date = new Date();

                try {
                    addMessage(name, date, text);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

    }

    public void addMessage(String name, Date date, String message) throws SQLException {
        Message m = new Message(name, date, message);

        sqlc.addMessage(m);
    }




}
