<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                <a class="nav-link active" aria-current="page" href="/profile"><i class="bi bi-person-square"></i></a>
            </nav>
            <nav class="search-nav">
                <form action="/search" class="d-flex search_products">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input class="form-control me-2" type="text" name="keyword" id="keyword"  minlength="2" placeholder="Search" aria-label="Search">
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
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <button class="log_out"><i class="bi bi-door-closed h3 mx-2"></i></button>
                </form>

            </nav>
    </div>
</nav>

<#--Content-->
<div class="container" style="text-align: center">
    <#if user??>
    <div class="card" style="width: 18rem;">
        <img src="/images/anonim.jpg" class="card-img-top" alt="#">
        <div class="card-body card_profile">
            <p>${user.name}</p>
            <p>${user.login}</p>
            <p>${user.email}</p>
            <p>${user.role}</p>
        </div>
    </div>
    <#else>
        <h1>Пользователь не найден!</h1>
    </#if>
</div>

<script src="/js/script.js"></script>
</body>
</html>