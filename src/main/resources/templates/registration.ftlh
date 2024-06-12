<!DOCTYPE html>
<html>
    <head>
        <title>Цифровая кафедра </title>>
    </head>
    <body>
        <h1>Цифровая кафедра </h1>
        <h4>Регистрация</h4>
        <form action="/registration" method="post">
            Никнейм:  <input type="text" name = "name"><br></br>
            Фамилия:  <input type="text" name = "last_name"><br></br>
            Имя:  <input type="text" name = "first_name"><br></br>
            Отчество: <input type="text" name = "father_name"><br></br>
            ЭЛ. почта: <input type="email" name = "email"><br></br>
            Номер телефона <input type="text" name = "phoneNumber"><br></br>
            Пароль: <input type="password" name = "password"><br></br>
            <input type="hidden" name="_csrf" value ="${_csrf.token}">
            <input type="submit" value="Регистрация">
        </form>
<!--        <#if errorMessage??>-->
<!--            <h2> <style ="color: red">${errorMessage}</h2>-->
<!--        </#if>-->
    </body>
</html>