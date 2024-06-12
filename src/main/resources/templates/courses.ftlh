<!DOCTYPE html>
<html>
    <head>
        <title>Цифровая кафедра</title>
    </head>
    <body>
        <h1>Цифровая кафедра  </h1>
<#if user.isAdmin()>
<h4>Курсы</h4>
<form action="/courses" method="get">
Поиск по названию: <input type="text" name="title"><br>
<input type="submit" value="Поиск"/>
</form>
<#list courses as course>
<div>
<p><b>${course.title}</b>  | <a href="/courses/${course.id}">подробнее</a></p>
</div>
<#else>
<h3>Курсов нет</h3>
</#list>
<h3>Создать новый курс</h3>
<form action="/courses/create" method="post" enctype="multipart/form-data">
Название курса: <input type="text" name="title"><br><br>
Описание курса: <input type="text" name="description"><br><br>
<input type="hidden" name="_csrf" value = "${_csrf.token}">
<input type="submit" value="Добавить курс">
</form>
<#else>
    <h4>Ваш курс</h4>
    <#if user.course??>
        <b>Название курса: </b>${user.course.title}<br>
        <b>Название курса: </b>${user.course.description}<br>
        <#list user.course.lectures as lecture>
        <div>
            <p><b>Лекция №</b>${lecture.numberOfLectures} ${lecture.name}</p>
            <p><b>Комментарий: </b> ${lecture.comment}</p>
        </div>
        </#list>
    <#else>
        У вас нет курсов, обратитесь к администратору.
    </#if>
</#if>
</body>
</html>