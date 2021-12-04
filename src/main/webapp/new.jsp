<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>New ad</title>

</head>
<body>

<li class="nav-item">
    <% if (request.getSession().getAttribute("user") == null) { %>
    <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Login</a>
    <% } else { %>
    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do"> <c:out value="${user.name}"/> | Logout</a>
    <% } %>
</li>

<h2>ENTER NEW AD DATA</h2>
<br>
<br>

<form action="<%=request.getContextPath()%>/newAd.do" method="post">
    <div>
        <p><b>Type in brand</b><br>
        <input type="text" name="brand" size="40" placeholder="brand" required>
        <br>
        <br>

        <p><b>Type in model</b><br>
            <input type="text" name="model" size="40" placeholder="model" required>
            <br>
            <br>

        <p><b>Choose transmission type</b></p>
        <input name="transmission" type="radio" id="mechanical" value="mechanical" checked>
        <label for="mechanical">mechanical</label><br>
        <input name="transmission" type="radio" id="automatic" value="automatic">
        <label for="automatic">automatic</label><br>

        <p><b>Choose body type</b></p>
        <input name="body" type="radio" id="sedan" value="sedan" checked>
        <label for="sedan">sedan</label><br>
        <input name="body" type="radio" id="station_wagon" value="station_wagon">
        <label for="station_wagon">station_wagon</label><br>
        <input name="body" type="radio" id="convertible" value="convertible">
        <label for="convertible">convertible</label><br>
        <input name="body" type="radio" id="coupe" value="coupe">
        <label for="coupe">coupe</label><br>
        <input name="body" type="radio" id="suv" value="suv">
        <label for="suv">suv</label><br>
        <input name="body" type="radio" id="van" value="van">
        <label for="van">van</label><br>
        <input name="body" type="radio" id="minivan" value="minivan">
        <label for="minivan">minivan</label><br>
        <input name="body" type="radio" id="pickup" value="pickup">
        <label for="pickup">pickup</label><br>
        <input name="body" type="radio" id="minibus" value="minibus">
        <label for="minibus">minibus</label><br>

        <p><b>Choose engine type</b></p>
        <input name="engine" type="radio" id="gas" value="gas" checked>
        <label for="gas">gas</label><br>
        <input name="engine" type="radio" id="diesel" value="diesel">
        <label for="diesel">diesel</label><br>

        <p><b>Type in year</b><br>
            <input type="text" name="year" size="30" placeholder="year" required>
            <br>
            <br>

        <p><b>Type in price (in rub)</b><br>
            <input type="text" name="price" size="30" placeholder="price" required>
            <br>
            <br>

        <button type="submit" class="btn btn-primary">Save new ad</button>
    </div>
</form>

</body>
</html>