<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Особистий кабінет</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Електронна пошта: <b>${user.email}</b>
        <br>
        Номер телефону: <b>${user.phoneNumber}</b>
        <br>
        <br>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель адміністратора
            </a><br>
        </#if>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/my/products">
            Мої товари
        </a>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Вийти з аккаунта
            </button>
        </form>
    </div>
</@t.template>
