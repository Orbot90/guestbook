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
</head>
<body>

    <%ArrayList<message.Message> messages = (ArrayList<message.Message>)request.getAttribute("messages");

      int pageNum = 0;
      try {
        pageNum = Integer.parseInt(request.getParameter("page")) - 1;
        request.setAttribute("pagenum", pageNum);

      } catch(NumberFormatException e) {}
      %>

    <%if(messages != null){%>

      <ul>
        <%int pages = (Integer)request.getAttribute("pages");

          for(int i = 0; i < pages; i++) {
            if((i) != pageNum) {
              out.println("<li  style=\"display: inline\"><a href=# onclick=\"ajax('" + (i + 1) + "'); return false\">" + (i + 1) + "</a></li> ");
            } else {
              out.println("<li  style=\"display: inline\">" + (i + 1) + "</li> ");
            }
          }
        %>
      </ul>
    <%}%>

      <%if(messages != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        for (Message m : messages) {
          String date = sdf.format(m.getDate());
          String text = m.getMessage().replaceAll("&", "&amp;")
                  .replaceAll("\"", "&quot;")
                  .replaceAll("<", "&lt;")
                  .replaceAll(">", "&gt;");
            text = text.replaceAll("\\[b\\]", "<b>").replaceAll("\\[/b\\]", "</b>")
                    .replaceAll("\\[i\\]", "<i>").replaceAll("\\[/i\\]", "</i>")
                    .replaceAll("\\[img\\]", "<img src='").replaceAll("\\[/img\\]", "' style='max-width: 200px; max-height: 200px;' />");
          String name = m.getName().replaceAll("&", "&amp;")
                  .replaceAll("\"", "&quot;")
                  .replaceAll("<", "&lt;")
                  .replaceAll(">", "&gt;");

            out.println("<pre style='font-family: sans-serif'>");

          out.println("<div class='message'>" + "<span class='date'>" + date +
                  "</span>" + "<br />" + "<span class='name'>" + name
                  + "</span>" + "<br />" + text + "</div>" + "<br /><br /><br />");

            out.println("</pre>");
        }
      } else {
          pageContext.forward("./gb");
      }

    %>
</body>

</html>


