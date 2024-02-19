<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Детальна інформація про товар</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Назва товару: </b>${product.title!}<br>
<b>Опис товару: </b>${product.description!}<br>
<b>Ціна: </b>${product.price!}<br>
<b>Місто: </b>${product.city!}<br>
<b>Автор: </b>${product.user.name}<br>
<b>Номер телефону: </b>${product.user.phoneNumber}<br>
</body>
</html>
