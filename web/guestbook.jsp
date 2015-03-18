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
    boolean isNew = true;
        if(request.getParameter("new") == null) {
            isNew = false;
        }

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

        for (int i = 0; i < messages.size(); i++) {
        Message m = messages.get(i);
          String date = sdf.format(m.getDate());
          String text = m.getMessage().replaceAll("&", "&amp;")
                  .replaceAll("\"", "&quot;")
                  .replaceAll("<", "&lt;")
                  .replaceAll(">", "&gt;");

            text = text.replaceAll("\\[b\\]", "<b>").replaceAll("\\[/b\\]", "</b>");
            text = text.replaceAll("\\[i\\]", "<i>").replaceAll("\\[/i\\]", "</i>");
            text = text.replaceAll("\\[img\\]", "<img class='transition' src='").replaceAll("\\[/img\\]", "' />");


          String name = m.getName().replaceAll("&", "&amp;")
                  .replaceAll("\"", "&quot;")
                  .replaceAll("<", "&lt;")
                  .replaceAll(">", "&gt;");

            out.println("<pre style='font-family: sans-serif'>");

            if(i == 0 && isNew) {
                            out.println("<div class='message new'>" + "<span class='date'>" + date +
                  "</span>" + "<br />" + "<span class='name'>" + name
                  + "</span>" + "<br />" + "<div class='text'>" + text + "</div>" + "</div>" + "<br /><br /><br />");
            } else {

                out.println("<div class='message'>" + "<span class='date'>" + date +
                  "</span>" + "<br />" + "<span class='name'>" + name
                  + "</span>" + "<br />" + "<div class='text'>" + text + "</div>" + "</div>" + "<br /><br /><br />");
            }
            out.println("</pre>");
        }
      } else {
          pageContext.forward("./gb");
      }

    %>
</body>

</html>


