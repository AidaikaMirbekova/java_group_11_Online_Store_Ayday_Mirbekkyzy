<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="${.locale}" xmlns="http://www.w3.org/1999/html">
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
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <input class="form-control me-2" type="text" name="keyword" id="keyword"  minlength="2" placeholder="<@spring.message "search" />" aria-label="Search">
                <button class="btn btn btn-info search-button" type="submit" value="Search"><@spring.message "search" /></button>
            </form>
        </nav>
        <nav class="main-nav">
            <div class="user_info" style="color: black"></div>
            <nav class="sign_button">
                <button type="button" class="btn btn-outline-warning"><a href="/register"><@spring.message "sign.up" /></a>
                </button>
            </nav>
            <nav>
                <button type="button" class="btn btn-outline-warning"><a href="/login"><@spring.message "sign.in" /></a>
                </button>
            </nav>
            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="log_out"><i class="bi bi-door-closed h3 mx-2"></i></button>
            </form>
        </nav>
    </div>
</nav>

<#--Content-->
<div class="container">
    <h5 style="text-align: center;margin-top: 5rem"><@spring.message "your.basket" /></h5>
    <#if baskets??>
        <table class="table">
                 <thead class="thead-light">
            <tr>
                <th scope="col"><@spring.message "products" /></th>
                <th scope="col"><@spring.message "name" /></th>
                <th scope="col"><@spring.message "description" /></th>
                <th scope="col"><@spring.message "price" /></th>
            </tr>
            </thead>

            <tbody>
            <#list baskets as product>
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td><input type="number" value="1"></td>
                <td>${product.price?string.currency}<@spring.message "som" /></td>
            </tr>
            </#list>
            </tbody>
        </table>
            <form action="/api/basket/empty" method="post" class="form_basket">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="btn btn-outline-info"><@spring.message "clear.basket" /></button>
            </form>
        <#else>
            <h1 style="text-align: center"><@spring.message "basket.is.empty" /></h1>
            </#if>
</div>

<#--footer-->
<footer>
    <div class="container">
        <#if pages??>
            <#if pages.hasPrevious()>
                <a href="/api/basket?page=${pages.pageNumber-1}&size=10" style="float: left" tabindex="0"><@spring.message "previous.page" /></a>
            <#else>
                <span id="loadPrev" hidden>First page</span>
            </#if>

            <#if lastPages>
                <a href="/api/basket?page=${pages.pageNumber+1}&size=10" style="float: right" tabindex="0"><@spring.message "next.page" /></a>
            <#else>
                <span id="loadPrev"hidden style="float: right">Last page</span>
            </#if>
        </#if>
    </div>
</footer>
</body>
</html>
