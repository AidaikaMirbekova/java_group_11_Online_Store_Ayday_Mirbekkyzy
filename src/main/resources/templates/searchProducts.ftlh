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
                <input class="form-control me-2" type="text" name="keyword" id="keyword"  minlength="5" placeholder="Search" aria-label="Search">
                <button class="btn btn btn-info search-button" type="submit" value="Search">Search</button>
            </form>
        </nav>
        <nav class="main-nav">
            <div class="user_info" style="color: black"></div>
            <nav class="sign_button">
                <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal">
                    Sign UP
                </button>
            </nav>
            <nav>
                <button type="button" class="btn btn-outline-warning" data-toggle="modal"
                        data-target="#staticBackdrop">Sign IN
                </button>
            </nav>
            <nav><span class="h3 mx-2 text-dark" id="log_out"><i class="bi bi-door-closed"></i></span></nav>
        </nav>
    </div>
</nav>

<#--Content-->
<div class="container">
    <#list products as product>
        <div class="card card-deck mb-3" style="max-width: 540px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="/images/${product.image}" class="img-fluid rounded-start" alt="">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Brand: ${product.name}</h5>
                        <p class="card-text">Model: ${product.description}</p>
                        <p class="card-text"><small class="text-muted">Price: ${product.price} som.</small></p>
                    </div>
                </div>
            </div>

        </div>
    </#list>

</div>
    <div>
        <#if pages??>
            <#if pages.hasPrevious()>
                <a href="/?page=${pages.pageNumber-1}&size=4" style="float: left" tabindex="0">Previous page</a>
            <#else>
                <span id="loadPrev">Previous page</span>
            </#if>

            <#if pages.pageNumber=lastPage>
                <span id="loadPrev" style="float: right">Last page</span>
            <#else>
                <a href="/?page=${pages.pageNumber+1}&size=4" style="float: right" tabindex="0">Next page</a>
            </#if>
        </#if>
    </div>


<#--Modal register-->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Register</h5>
            </div>
            <div class="modal-body">
                <form action="/register" id="sign_up" class="sign-up-htm">
                    <div class="mb-3 row">
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email"
                                   required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-10">
                            <input type="text" name="name" class="form-control" id="inputName" placeholder="Name"
                                   minlength="2" maxlength="24" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-10">
                            <input type="text" name="login" class="form-control" id="inputLogin" placeholder="Login"
                                   minlength="5" maxlength="15" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-10">
                            <input type="password" name="password" class="form-control" id="inputPassword"
                                   placeholder="Password" minlength="5" required>
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <div class="col-sm-10">
                            <input type="password" name="password" class="form-control" id="repeatPassword"
                                   placeholder="Repeat Password" minlength="5" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Sign up</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<#--Modal Login-->
<div class="modal fade" id="staticBackdrop" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Login</h5>
            </div>
            <div class="modal-body">
                <form action="/login" method="post" id="sign_in" class="sign-in-htm">
                    <div class="mb-3 row">
                        <div class="col-sm-10">
                            <input type="email" name="username" class="form-control" placeholder="Email" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-10">
                            <input type="password" name="password" class="form-control" placeholder="Password"  required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/js/script.js"></script>
</body>
</html>