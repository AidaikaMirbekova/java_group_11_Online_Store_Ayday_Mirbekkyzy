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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous">
    </script>
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
        <nav class="reviews">
            <a class="navbar " aria-current="page" href="/reviews">Reviews</a>
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
<div class="container">
    <h5 style="text-align: center;margin-top: 5rem">Your busket</h5>
    <#if userBasket??>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">Products</th>
                <th scope="col">Description</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>

            <tbody>

            <#list userBasket as basket>
                <tr>

                    <td>${basket.products_id.name}</td>
                    <td>${basket.products_id.description}</td>
                    <#--CHANGE QUANTITY IN BASKET-->
                    <form action="/api/basket/changeQuantity" method="post" class="form_change_quantity">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <input name="username" value="${(user.email)!''}" hidden>
                        <input name="id" value="${basket.id}" hidden>
                        <td>${basket.quantity} <input type="number" style="width: 50px" name="quantity"
                                                      value="${quantity!''}">
                            <button class="change" hidden></button>
                        </td>
                    </form>

                    <td>${basket.price}</td>
<#--                Delete one basket-->
                <form action="/api/basket/delete" method="post" id="basket_form_delete_one">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                        <input name="id" value="${basket.id}" hidden>
                        <input name="username" value="${(user.email)!''}" hidden>
                    <td><button class="btn btn-light" id="delete_one_basket" type="submit"><i class="bi bi-x-lg"></i></button></td>
                </form>

                </tr>
            </#list>
            </tbody>
        </table>
        <form action="/api/basket/empty" method="post" class="form_basket">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <input name="username" value="${(user.email)!''}" hidden>
            <button class="btn btn-outline-info delete_baskets_all">Clear basket</button>
        </form>
    <#--Order -->
        <form action="/api/addOrder" method="post" class="form_order">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <input name="username" value="${(user.email)!''}" hidden>
            <button class="btn btn-outline-info order_button">Order</button>
        </form>

    <#else>
        <h1 style="text-align: center">Basket is empty!!!</h1>
    </#if>
</div>

<#--footer-->
<footer>
    <div class="container">
        <#if pages??>
            <#if pages.hasPrevious()>
                <a href="/api/basket?page=${pages.pageNumber-1}&size=10" style="float: left" tabindex="0">Previous
                    page</a>
            <#else>
                <span id="loadPrev" hidden>First page</span>
            </#if>

            <#if lastPages>
                <a href="/api/basket?page=${pages.pageNumber+1}&size=10" style="float: right" tabindex="0">Next page</a>
            <#else>
                <span id="loadPrev" hidden style="float: right">Last page</span>
            </#if>
        </#if>
    </div>
</footer>
<script src="/js/script.js"></script>
</body>
</html>
