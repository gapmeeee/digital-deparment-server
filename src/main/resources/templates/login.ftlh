<!DOCTYPE html>
<html>
    <head>
        <title>Цифровая кафедра</title>>
    </head>
    <body>
        <h1>Цифровая кафедра </h1>
        <h4>Авторизация</h4>
        ${messafe?ifExists}
        <form action="/login" method="post">
            ЭЛ. почта: <input type="email" name = "username"><br></br>
            Пароль: <input type="password" name = "password"><br></br>
            <input type="hidden" name="_csrf" value ="${_csrf.token}">
            <input type="submit" value="Войти">
        </form>
        <a href="/registration">Зарегистрироваться</a>
    </body>
</html>