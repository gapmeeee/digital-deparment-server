<!DOCTYPE html>
<html>
    <head>
        <title>Цифровая кафедра </title>
    </head>
    <body>
        <h1>Цифровая кафедра </h1><hr>
        <h3>Подробности инофрмации о пользователе  ${user.name}</h3>
        <b>Email</b> ${user.email}<br>
        <b>Номер телефона</b> ${user.phoneNumber}<br>
        <form action="/logout" method="post">
            <input type = "hidden" name="_csrf" value="${_csrf.token}">
            <input type = "submit" value="Выйти">
        </form>
    </body>
</html>