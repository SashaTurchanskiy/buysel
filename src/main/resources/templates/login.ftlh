<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Авторизація</h1>
    <hr>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="email">Електронна пошта</label>
            <input type="email" class="form-control <#if error??>is-invalid</#if>" id="email" name="username">
            <div id="email" class="invalid-feedback">
                Невірна електронна адреса
            </div>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control <#if error??>is-invalid</#if>" id="password" name="password">
            <div id="password" class="invalid-feedback">
                Новий пароль
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Увійти</button>
    </form>
    <hr>
    <h2>Немає аккаунта BUYSELL? <a href="/registration">Зареєструватись!</a></h2>
</@t.template>
