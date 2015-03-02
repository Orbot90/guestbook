<%@ page import="java.util.ArrayList" %>
<%@ page import="message.Message" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

  <form action="/guestbook" method="post" accept-charset="UTF-8">

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
      if(messages != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        for (int i = messages.size() - 1; i >= 0; i--) {
          Message m = messages.get(i);
          String date = sdf.format(m.getDate());
          out.print(date + "<br />" + m.getName() + "<br />" + m.getMessage() + "<br /><br /><br />");
        }
      } else {
        pageContext.forward("/guestbook");
      }
    %>



  </form>



  </body>
</html>
