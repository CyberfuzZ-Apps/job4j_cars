<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <script>
        function validate() {
            let email = $('#email');
            let password = $('#password');
            let array = [email, password];
            for (let i = 0; i < array.length; i++) {
                if (array[i].val() === '') {
                    alert(array[i].attr('title') + ' не заполнено!');
                    return false;
                }
            }
            return true;
        }
    </script>
    <title>Авторизация</title>
</head>
<body>
<div class="container pt-3">

    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="/cars/index.html">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cars/reg.do">Регистрация</a>
            </li>
        </ul>
        <div class="card" style="width: 100%">
            <div class="card-header">
                Авторизация
            </div>
            <div class="card-body">
                <form action="/cars/auth.do" method="post">
                    <div class="form-group">
                        <label for="email">Почта</label>
                        <input type="email" class="form-control" name="email" id="email" title="Поле ПОЧТА"
                               placeholder="Введите email">
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input type="password" class="form-control" name="password" id="password" title="Поле ПАРОЛЬ"
                               placeholder="Введите пароль">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Войти</button>
                    <div style="color:red; font-weight: bold; margin: 30px 0;">
                        <% String error = (String) request.getAttribute("error");
                            if (error != null) { %>
                        <%=error%>
                        <% } %>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
