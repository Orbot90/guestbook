<%@ page import="java.util.ArrayList" %>
<%@ page import="message.Message" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Enumeration" %>
<%--
  Created by IntelliJ IDEA.
  User: orbot
  Date: 02.03.15
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Гостевая книга</title>
  </head>
  <body>


  <h1>Гостевая книга</h1>
  <p>Оставьте мне здесь какое-нибудь сообщение. Пару ласковых или неласковых слов =)</p>

  <form action="./gb" method="post" accept-charset="UTF-8">

    <label for="name-field">Ваше имя:</label> <br />
    <input type="text" name="username" id="name-field" size="13">
    <br />

    <label for="mess-field">Ваше сообщение:</label> <br />
    <textarea name="mess" id="mess-field" rows="10"></textarea>
    <br />
    <input type="submit" value="Отправить">

    <br />
    <br />

    <%ArrayList<message.Message> messages = (ArrayList<message.Message>)request.getAttribute("messages");

      int pageNum = 0;
      try {
        pageNum = Integer.parseInt(request.getParameter("page")) - 1;
      } catch(NumberFormatException e) {}
      %>

    <%if(messages != null){%>

      <ul>
        <%int pages = messages.size()/5;
         if(messages.size()%5 != 0) {
            pages++;
          }
          for(int i = 0; i < pages; i++) {
            if((i) != pageNum) {
              out.print("<li  style=\"display: inline\"><a href='./?page=" + (i + 1) + "'>" + (i + 1) + "</a></li> ");
            } else {
              out.print("<li  style=\"display: inline\">" + (i + 1) + "</li> ");
            }
          }
        %>
      </ul>
    <%}%>

      <%if(messages != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        int stop = messages.size() - 1 - ((pageNum + 1) * 5);
        for (int i = messages.size() - 1 - (pageNum * 5); (i >= 0) && (i > stop); i--) {
          Message m = messages.get(i);
          String date = sdf.format(m.getDate());
          out.print(date + "<br />" + m.getName() + "<br />" + m.getMessage() + "<br /><br /><br />");
        }
      } else {
        pageContext.forward("./gb");
      }
    %>



  </form>



  </body>
</html>
