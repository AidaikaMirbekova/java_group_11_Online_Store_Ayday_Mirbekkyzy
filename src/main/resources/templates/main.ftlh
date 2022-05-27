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
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="log_out"><i class="bi bi-door-closed h3 mx-2"></i></button>
            </form>
            <div>
                <form action="" method="get">
                    <select name="lang" id="lang_select">
                        <option value="en">English</option>
                        <option value="ru">Русский</option>
                    </select>
                    <button type="submit">
                        <@spring.message "button.language-change.text"></@spring.message>
                    </button>
                </form>
            </div>
        </nav>
    </div>
</nav>

<#--Content-->
<div class="container content_container">
    <#--    Search products by price-->
    <aside class="select_products col-ms-2">
        <h5 style="text-align: left"><@spring.message "price.range" />:</h5>
        <form action="/priceSearch" class="d-flex">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="form-row">
                <div class="col-5">
                    <input type="text" name="more" id="more" class="form-control" placeholder="0" value="0" required>
                </div>
                <div class="col-5">
                    <input type="text" name="less" id="less" class="form-control" placeholder="0" required>
                </div>
                <button class="btn btn btn-info btn-search_price" type="submit" value="Search"><@spring.message "search" /></button>
            </div>
        </form>
    </aside>

    <#--    List products-->
    <section class="products_list col-ms-4">
        <#list products as product>
            <div class="card card-deck mb-3" style="max-width: 540px;">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="/images/${product.image}" class="img-fluid rounded-start" alt="">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title"><@spring.message "brand" />: ${product.name}</h5>
                            <p class="card-text"><@spring.message "model" />: ${product.description}</p>
                            <p class="card-text"><small><@spring.message "price" />: ${product.price?string.currency}<@spring.message "som" /></small></p>
                            <p class="card-text"><small class="text-muted"><@spring.message "in.stock" />: ${product.quantity} <@spring.message "pieces" /></small>
                            </p>


                            <#--                        Add to basket form-->
                            <form action="/api/basket/add" method="post" id="basket-form">
                                <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                           value="${(_csrf.token)!'--no-token--'}"/>
                                </#if>
                                <div>
                                    <input name="id" value="${product.id}" hidden>
                                    <input name="quantity" value="1" hidden>
                                    <input type="number" inputmode="decimal" name="price" value="${product.price}"
                                           hidden>
                                    <input name="username" value="${(user.email)!''}" hidden>
                                    <button class="btn btn-light" id="add_basket" type="submit"><@spring.message "add.to.basket" />
                                    </button>
                                </div>
                            </form>
                            <a href="/oneProduct?id=${product.id}" class="col"><@spring.message "more" /></a>

                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </section>
</div>

<#--pageable-->
<footer>
    <div class="container">
        <#if pages??>
            <#if pages.hasPrevious()>
                <a href="/?page=${pages.pageNumber-1}&size=4" style="float: left" tabindex="0"><@spring.message "previous.page" /></a>
            <#else>
                <span id="loadPrev" hidden>First page</span>
            </#if>

            <#if lastPages>
                <a href="/?page=${pages.pageNumber+1}&size=4" style="float: right" tabindex="0"><@spring.message "next.page" /></a>
            <#else>
                <span id="loadPrev" hidden style="float: right">Last page</span>
            </#if>
        </#if>
    </div>
</footer>
</body>
</html>