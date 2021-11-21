<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Ads</title>
</head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
    $(document).ready(function () {
        loadAllAds()
    })

    function loadAllAds() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cars/findAll.do',
            dataType: 'json'
        }).done(function (data) {
            let items = ""
            for (let i = 0; i < data.length; i++) {
                items += "<tr>" + "<td>" + "photo tbd" + "</td>"
                items += "<td>" + data[i]["id"] + "</td>"
                items += "<td>" + data[i]["created"] + "</td>"
                items += "<td>" + data[i]["user"]["name"] + "</td>"
                if (data[i]["status"] === true) {
                    items += "<td>" + "for sale" + "</td>"
                } else {
                    items += "<td>" + "sold" + "</td>"
                }
                items += "<td>" + data[i]["brand"] + "</td>"
                items += "<td>" + data[i]["model"] + "</td>"
                items += "<td>" + data[i]["transmission"] + "</td>"
                items += "<td>" + data[i]["body"] + "</td>"
                items += "<td>" + data[i]["engine"] + "</td>"
                items += "<td>" + data[i]["year"] + "</td>"
                items += "<td>" + data[i]["price"] + "</td>"
                items += "</tr>"
            }
            $('#ads').html(items);
        }).fail(function (err) {
            console.log(err);
        });
    }

    function loadCurAds() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cars/curAds.do',
            dataType: 'json'
        }).done(function (data) {
            let items = ""
            for (let i = 0; i < data.length; i++) {
                items += "<tr>" + "<td>" + "photo tbd" + "</td>"
                items += "<td>" + data[i]["id"] + "</td>"
                items += "<td>" + data[i]["created"] + "</td>"
                items += "<td>" + data[i]["user"]["name"] + "</td>"
                if (data[i]["status"] === true) {
                    items += "<td>" + "for sale" + "</td>"
                } else {
                    items += "<td>" + "sold" + "</td>"
                }
                items += "<td>" + data[i]["brand"] + "</td>"
                items += "<td>" + data[i]["model"] + "</td>"
                items += "<td>" + data[i]["transmission"] + "</td>"
                items += "<td>" + data[i]["body"] + "</td>"
                items += "<td>" + data[i]["engine"] + "</td>"
                items += "<td>" + data[i]["year"] + "</td>"
                items += "<td>" + data[i]["price"] + "</td>"
                items += "</tr>"
            }
            $('#ads').html(items);
        }).fail(function (err) {
            console.log(err);
        });
    }

    function photoAds() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cars/photoAds.do',
            dataType: 'json'
        }).done(function (data) {
            let items = ""
            for (let i = 0; i < data.length; i++) {
                items += "<tr>" + "<td>" + "photo tbd" + "</td>"
                items += "<td>" + data[i]["id"] + "</td>"
                items += "<td>" + data[i]["created"] + "</td>"
                items += "<td>" + data[i]["user"]["name"] + "</td>"
                if (data[i]["status"] === true) {
                    items += "<td>" + "for sale" + "</td>"
                } else {
                    items += "<td>" + "sold" + "</td>"
                }
                items += "<td>" + data[i]["brand"] + "</td>"
                items += "<td>" + data[i]["model"] + "</td>"
                items += "<td>" + data[i]["transmission"] + "</td>"
                items += "<td>" + data[i]["body"] + "</td>"
                items += "<td>" + data[i]["engine"] + "</td>"
                items += "<td>" + data[i]["year"] + "</td>"
                items += "<td>" + data[i]["price"] + "</td>"
                items += "</tr>"
            }
            $('#ads').html(items);
        }).fail(function (err) {
            console.log(err);
        });
    }
</script>

<body>

<a href="<%=request.getContextPath()%>/new.jsp">Fill in a new ad</a>
<br>
<br>

<button onclick="loadCurAds();">Filter today's ads</button>
<br>
<br>

<button onclick="photoAds();">Filter ads with photo</button>
<br>
<br>

<a href="<%=request.getContextPath()%>/myads.jsp">Show and edit my own ads</a>
<br>
<br>

<div class="container">
    <div class="row pt-3">
        <li class="nav-item">
            <% if (request.getSession().getAttribute("user") == null) { %>
            <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Login</a>
            <% } else { %>
            <a class="nav-link" href="<%=request.getContextPath()%>/logout.do"> <c:out value="${user.name}"/> | Logout</a>
            <% } %>
        </li>
        <h4>
            Car ads list (all users)
        </h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Photo</th>
                <th>ID</th>
                <th>Date</th>
                <th>User</th>
                <th>Status</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Transmission</th>
                <th>Body</th>
                <th>Engine</th>
                <th>Year</th>
                <th>Price (rub)</th>
            </tr>
            </thead>
            <tbody id="ads">
            </tbody>
        </table>
    </div>
</div>

</body>
</html>