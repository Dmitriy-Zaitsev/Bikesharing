<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Add card - Bikesharing</title>
    <c:import url="/jsp/import/navbar.jsp"/>
</head>
<body>
<div class="card" style="margin: auto; padding: 10px; width: 400px">
    <form name="cardForm" method="POST" action="${request.contextPath}/controller">
        <input type="hidden" name="command" value="add_card" />
        <div class="form-group">
            <label for="inputNumber">Number</label>
            <input name="number" type="text" class="form-control" id="inputNumber" placeholder="Enter card number">
        </div>
        <div class="form-group">
            <label for="inputFirstName">First name</label>
            <input name="firstName" type="text" class="form-control" id="inputFirstName" placeholder="Enter name">
            <label for="inputLastName">Last name</label>
            <input name="lastName" type="text" class="form-control" id="inputLastName" placeholder="Enter surname">
        </div>
        <label>Expiration date</label>
        <div class="form-inline">
            <label for="inputMonth">Month</label>
            <input name="month" style="width: 100px; margin: 10px" type="text" class="form-control" id="inputMonth" placeholder="Enter month">
            <label for="inputYear">Year</label>
            <input name="year" style="width: 100px; margin: 10px" type="text" class="form-control" id="inputYear" placeholder="Enter year">
        </div>
        <div class="form-group">
            <label for="inputCvv">CVV code</label>
            <input name="cvv" type="password" class="form-control" id="inputCvv" placeholder="Enter CVV code">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>