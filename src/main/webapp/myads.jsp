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
    <title>My ads</title>
</head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
    $(document).ready(function () {
        myAds()
    })

    function myAds() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cars/myAds.do',
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
            $('#myAds').html(items);
        }).fail(function (err) {
            console.log(err);
        });
    }
</script>

<body>

<br>
<br>
<br>

<br>
<br>
<br>

<br>
<br>
<br>

<a href="<%=request.getContextPath()%>/ads.jsp">Return to list of all ads</a>
<br>
<br>
<br>

<form action="<%=request.getContextPath()%>/myAds.do" method="post">
    <div>
        <h5>Type in the ID of an ad to close if sold (one shot -> one task)</h5>
        <br>
        <label>
            <input type="number" name="closedAd" required>
        </label>
        <button type="submit" class="btn btn-primary">Close the ad</button>
    </div>
</form>
<br>
<br>

<div class="container">
    <div class="row pt-3">
        <h4>
            My ads
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
            <tbody id="myAds">
            </tbody>
        </table>
    </div>
</div>


</body>
</html>