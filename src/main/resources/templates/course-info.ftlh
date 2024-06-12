<!DOCTYPE html>
<html>
    <head>
        <title>Цифровая кафедра</title>
    </head>
    <body>
    <h1>Цифровая кафедра </h1><hr></hr>
    <h4>Подробная информация о курсе</h4>

    <b>Название курса: </b>${course.title}<br>
    <b>Описание курса: </b>${course.description}<br>
    <#list lectures as lecture>
        <div>
            <p><b>Лекция № ${lecture.name}</b></p>
            <form action="/courses/${course.id}/lecture/delete/${lecture.id}" method="post">
                <input type="hidden" name="_csrf" value ="${_csrf.token}">
                <input type="submit" value="Удалить лекцию"><br><br>
            </form>
        </div>
    </#list>
    <form action="/courses/${course.id}/lecture/create" method="post">
        Название лекции: <input type="text" name="name"><br><br>
        Комментарий к лекции: <input type="text" name="comment"><br><br>
        <input type="hidden" name="_csrf" value ="${_csrf.token}">
        <input type="submit" value="Создать лекцию">
    </form>
    <form action="/courses/delete/${course.id}" method="post">
        <input type="hidden" name="_csrf" value ="${_csrf.token}">
        <input type="submit" value="Удалить курс">
    </form>

</body>
</html>