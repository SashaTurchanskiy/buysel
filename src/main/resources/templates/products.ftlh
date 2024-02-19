<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1> BUYSELL</h1><hr>
<#if user.email??>
    <h3>Ім'я користувача: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Вийти"/>
    </form>
<#else>
    <a href="/login">Увійти</a></h1><hr>
</#if>
<hr>
<h4>Товари зі всієї України</h4>
<form action="/" method="get">
    Пошук по назві оголошення: <input type="text" name="title"><br>
    <input type="submit" value="Пошук"/>
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} грн. | <a href="/product/${product.id}">Детальніше...</a></p>
    </div>
<#else>
    <h3>Товарів немає</h3>
</#list>
<if user.email??>
<hr>
<h3>Створити новий товар</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Ім'я оголошення: <label>
        <input type="text" name="title"/>
    </label><br><br>
    Опис оголошення: <label>
        <input type="text" name="description"/>
    </label><br><br>
    Ціна: <label>
        <input type="number" name="price"/>
    </label><br><br>
    Місто: <label>
        <input type="text" name="city"/>
    </label><br><br>
    Фото 1: <input type="file" name="file1"/><br><br>
    Фото 2: <input type="file" name="file2"/><br><br>
    Фото 3: <input type="file" name="file3"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавити товар"/>
</form>
</if>
</body>
</html>