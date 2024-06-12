<!DOCTYPE html>
<html>
    <head>
        <title>Цифровая кафедра</title>
    </head>
    <body>
        <h1>Цифровая кафедра | </h1><hr>

        <h4>Панель администратора</h4>
        <table border="1">
            <tr>
                <th>Email</th>
                <th>Номер телефона</th>
                <th>Активность</th>
                <th>Роль</th>
                <th>Бан</th>
                <th>Редактирование</th>
                <th>Посмотреть подробную информацию</th>
                <th>Выбрать курс</th>
            </tr>
            <#list users as user>
            <tr>
                <th>${user.email}</th>
                <th>${user.phoneNumber}</th>
                <th><#if user.active>true<#else>false</#if></th>
                <td><#list user.roles as role>${role} </#list></td>
            <th><form action="admin/user/ban/${user.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <#if user.active><input type="submit" value="Бан"><#else><input type="submit" value="Разбан"></#if>
            </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Редактирование</a></th>
            <th><a href="/user/${user.id}">Посмотреть подробную информацию</a></th>
            <th>
            <#if user.isUser()>
                    <form action="/admin/user/edit/courses/${user.id}" method="post">
                        <select id="course" name="courseId">
                            <#list courses as course>
                            <option value="${course.id}"<#if user.course?exists && user.course.id == course.id>selected</#if>>${course.title}</option>
                            </#list>
                        </select>
                    <input type="submit" value = "Выбрать"></input>
                    <input type = "hidden" value="${_csrf.token}" name = "_csrf">
                 </form>
                </th>
            </#if>
            </tr>
        <#else>
        <h3>Пользователей нет</h3>
        </#list>
    </table>

    </body>
</html>