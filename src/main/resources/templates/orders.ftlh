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
        <a class="navbar-brand" href="/">Online Store</a>
        <nav class="profile">
            <a class="nav-link active" aria-current="page" href="/api/profile"><i class="bi bi-person-square"></i></a>
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
<div class="container">
    <h5 style="text-align: center;margin-top: 5rem">Your orders</h5>
    <#if ordersList??>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">Products</th>
            <th scope="col">Description</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Date</th>
            <th scope="col">Status</th>
        </tr>
        </thead>

        <tbody>

        <#list ordersList as order>
            <tr>
                <td>${order.product_id.name}</td>
                <td>${order.product_id.description}</td>
                <td>${order.quantity}</td>
                <td>${order.price}</td>
                <td>${order.date}</td>
                <td>${order.status}</td>
            </tr>
        </#list>
        </tbody>
    </table>


    <div class="form-group">
        <form action="/api/addReview" method="post" id="review_form">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                       value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <input name="username" value="${(user.email)!''}" hidden>
            <label for="review_leave_area">You can share your feedback with us.</label>
            <textarea form="review_form" name="review" class="form-control" id="review_leave_area" rows="3">

            </textarea>
            <button class="btn btn-outline-info mb-5">Save review</button>
        </form>
    </div>

    <#else>
    <h1 style="text-align: center">Orders is empty!!!</h1>
    </#if>
</div>

<#--footer-->
<footer>
    <div class="container">
        <#if pages??>
            <#if pages.hasPrevious()>
                <a href="/api/showOrders?page=${pages.pageNumber-1}&size=10" style="float: left" tabindex="0">Previous
                    page</a>
            <#else>
                <span id="loadPrev" hidden>First page</span>
            </#if>

            <#if lastPages>
                <a href="/api/showOrders?page=${pages.pageNumber+1}&size=10" style="float: right" tabindex="0">Next
                    page</a>
            <#else>
                <span id="loadPrev" hidden style="float: right">Last page</span>
            </#if>
        </#if>
    </div>
</footer>
</body>
</html>
