<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Реєстрація</h4>
<form action="/registration" method="post">
    Ім'я користувача: <input type="text" name="name"><br><br>
    Пошта: <input type="email" name="email"><br><br>
    Номер телефону: <input type="text" name="numberPhone"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вхід"/>
</form>

</body>
</html>