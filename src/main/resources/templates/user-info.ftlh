<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h3>Детальна інформація про користувача ${user.name}</h3>
<b>Номер телефону: </b>
<#if user.phoneNumber??>
    ${user.phoneNumber}
<#else>
    Номер телефону не вказано
</#if>

<b>Email: </b> ${user.email}<br>
<h4>Товари користувача ${user.name}</h4>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} грн. | <a href="/product/${product.id}">Детальніше...</a></p>
    </div>
<#else>
    <h3>Товарів не знайдено</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>