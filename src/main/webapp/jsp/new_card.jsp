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
            <label for="input_number">Number</label>
            <input name="number" type="text" class="form-control" id="input_number" placeholder="Enter card number">
        </div>
        <div class="form-group">
            <label for="input_first_name">First name</label>
            <input name="first_name" type="text" class="form-control" id="input_first_name" placeholder="Enter name">
            <label for="input_last_name">Last name</label>
            <input name="last_name" type="text" class="form-control" id="input_last_name" placeholder="Enter surname">
        </div>
        <label>Expiration date</label>
        <div class="form-inline">
            <label for="input_month">Month</label>
            <input name="month" style="width: 100px; margin: 10px" type="text" class="form-control" id="input_month" placeholder="Enter month">
            <label for="input_year">Year</label>
            <input name="year" style="width: 100px; margin: 10px" type="text" class="form-control" id="input_year" placeholder="Enter year">
        </div>
        <div class="form-group">
            <label for="input_cvv">CVV code</label>
            <input name="cvv" type="password" class="form-control" id="input_cvv" placeholder="Enter CVV code">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        ${requestScope.message}
    </form>
</div>
</body>
</html>