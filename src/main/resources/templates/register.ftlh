<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="_csrf_token" content="${_csrf.token}"/>

    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/style.css">
    <title>Store.kg</title>
</head>
<body>
<#--Navbar-->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"><@spring.message "logo" /></a>
        <nav class="profile">
            <a class="nav-link active" aria-current="page" href="/api/profile"><i class="bi bi-person-square"></i></a>
        </nav>
        <nav class="orders">
            <a class="navbar " aria-current="page" href="/api/showOrders"><@spring.message "orders" /></a>
        </nav>
        <nav class="reviews">
            <a class="navbar " aria-current="page" href="/reviews"><@spring.message "reviews" /></a>
        </nav>
        <nav class="search-nav">
            <form action="/search" class="d-flex search_products">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <input class="form-control me-2" type="text" name="keyword" id="keyword" minlength="2"
                       placeholder="<@spring.message "search" />" aria-label="Search">
                <button class="btn btn btn-info search-button" type="submit" value="Search"><@spring.message "search" /></button>
            </form>
        </nav>
        <nav class="main-nav">
            <div class="user_info" style="color: black"></div>
            <nav class="basket">
                <button type="button" class="basket_button"><a href="/api/basket"><i
                                class="bi bi-cart2 h3 mx-2"></i></a>
                </button>
            </nav>
            <nav>
                <button type="button" class="btn btn-outline-warning"><a href="/login"><@spring.message "sign.in" /></a>
                </button>
            </nav>
            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="log_out"><i class="bi bi-door-closed h3 mx-2"></i></button>
            </form>
        </nav>
    </div>
</nav>

<#--Content-->
<div class="container content_container">
    <form action="/register" id="sign_up" class="sign-up-htm" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="mb-3 row">
            <div class="col-sm-10">
                <input type="email" name="email" class="form-control" id="inputEmail" placeholder="<@spring.message "email" />"
                       value="${(form.email)!''}" required>
            </div>
        </div>
        <div class="mb-3 row">
            <div class="col-sm-10">
                <input type="text" name="name" class="form-control" id="inputName" placeholder="<@spring.message "name" />"
                       minlength="2" maxlength="24" value="${(form.name)!''}" required>
            </div>
        </div>
        <div class="mb-3 row">
            <div class="col-sm-10">
                <input type="text" name="login" class="form-control" id="inputLogin" placeholder="<@spring.message "login" />"
                       minlength="5" maxlength="15" value="${(form.login)!''}" required>
            </div>
        </div>
        <div class="mb-3 row">
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control" id="inputPassword"
                       placeholder="<@spring.message "password" />" minlength="5" value="${(form.password)!''}" required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary sign"><@spring.message "sign.up" /></button>

        <div>
            <#if errors?? >
                <p><@spring.message "register.error" /></p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
        </div>
    </form>
</div>

</body>
</html>
