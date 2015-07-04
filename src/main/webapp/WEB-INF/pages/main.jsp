<!DOCTYPE html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Гостевая книга</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/guestbook.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/guestbook.js"></script>
    <a href="/">На главную</a>
</head>
<body>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<h1>Гостевая книга</h1>
<p>Оставьте мне здесь какое-нибудь сообщение. Пару ласковых или неласковых слов =)</p>
<div class="form">
    <form onsubmit="send(); return false" method="post" accept-charset="UTF-8">

        <label for="name-field">Ваше имя:</label> <br />
        <input type="text" name="username" id="name-field" size="13">
        <br />

        <label for="mess-field">Ваше сообщение:</label> <br />
        <textarea name="mess" id="mess-field" rows="10" cols="50"></textarea>
        <br />
        <input type="submit" value="Отправить">
    </form>
</div>

<br />
<br />


<span id="guest"></span>


<script>
    ajax(0);
</script>



</body>
</html>
