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
    <#--JavaScript link-->
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <title>Store.kg</title>
</head>
<body>
<#--Navbar-->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Online Store</a>
        <nav class="profile">
            <a class="nav-link active" aria-current="page" href="/api/profile"><i class="bi bi-person-square"></i></a>
        </nav>
        <nav class="orders">
            <a class="navbar " aria-current="page" href="/api/showOrders">Orders</a>
        </nav>

        <nav class="search-nav">
            <form action="/search" class="d-flex search_products">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <input class="form-control me-2" type="text" name="keyword" id="keyword" minlength="2"
                       placeholder="Search" aria-label="Search">
                <button class="btn btn btn-info search-button" type="submit" value="Search">Search</button>
            </form>
        </nav>
        <nav class="main-nav">
            <div class="user_info" style="color: black"></div>
            <nav class="basket">
                <button type="button" class="basket_button"><a href="/api/basket"><i
                                class="bi bi-cart2 h3 mx-2"></i></a>
                </button>
            </nav>
            <nav class="sign_button">
                <button type="button" class="btn btn-outline-warning"><a href="/register">Sign UP</a>
                </button>
            </nav>
            <nav>
                <button type="button" class="btn btn-outline-warning"><a href="/login">Sign In</a>
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
    <#if reviewsList??>

    <#--    List reviews-->
    <div class="reviews_list">
        <#list reviewsList as review>
            <div class="card">
                <div class="card-header">
                   ${review.user.login}
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p>${review.review}</p>
                        <footer class="blockquote-footer">${review.date}</footer>
                    </blockquote>
                </div>
            </div>
        </#list>
    </div>
    <#else>
        <h5 style="text-align: center"><p>There are no reviews yet,</p>
            <p>but you can place an order and share a review</p></h5>
    </#if>
</div>

<#--pageable-->
<footer>
    <div class="container">
        <#if pages??>
            <#if pages.hasPrevious()>
                <a href="/review?page=${pages.pageNumber-1}&size=10" style="float: left" tabindex="0">Previous page</a>
            <#else>
                <span id="loadPrev" hidden>First page</span>
            </#if>

            <#if lastPages>
                <a href="/review?page=${pages.pageNumber+1}&size=10" style="float: right" tabindex="0">Next page</a>
            <#else>
                <span id="loadPrev" hidden style="float: right">Last page</span>
            </#if>
        </#if>
    </div>
</footer>
</body>
</html>
