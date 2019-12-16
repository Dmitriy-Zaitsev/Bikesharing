<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Replenish balance - Bikesharing</title>
    <c:import url="/jsp/import/navbar.jsp"/>
</head>
<body>
<div class="card" style="margin: auto; padding: 10px; width: 400px">
    <form name="cardForm" method="POST" action="${request.contextPath}/controller">
        <input type="hidden" name="command" value="replenish" />
        <input type="hidden" name="card_id" value="${requestScope.card_id}" />
        <div class="form-group">
            <label for="inputAmount">${stringManager.get("amount")}</label>
            <input name="amount" type="text" class="form-control" id="inputAmount" placeholder="Enter amount of money">
        </div>
        <div class="form-group">
            <label for="inputCvv">CVV</label>
            <input name="cvv" type="password" class="form-control" id="inputCvv" placeholder="Enter CVV code">
        </div>
        <button type="submit" class="btn btn-primary">${stringManager.get("submit")}</button>
    </form>
</div>
</body>
</html>
