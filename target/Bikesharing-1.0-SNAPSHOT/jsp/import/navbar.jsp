<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<style>
    .language-icon {
        height: 24px;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="navbar-brand">
        <img style="width:36px;" src="${request.contextPath}/logo-white.svg" alt="Bikesharing" />
    </div>
    <div class="navbar-nav-scroll">
        <ul class="navbar-nav bd-navbar-nav flex-row">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=main_page">${stringManager.get("rent")}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">${stringManager.get("spots")}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">${stringManager.get("about")}</a>
            </li>
            <c:if test = "${sessionScope.user.role == 'admin'}">
                <li class="nav-item">
                    <a class="nav-link disabled">|</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=users_page">${stringManager.get("users")}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=bikes_page">${stringManager.get("bikes")}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=models_page">${stringManager.get("models")}</a>
                </li>
            </c:if>
        </ul>
    </div>
    <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
        <li class="nav-item">
            <a class="nav-link" href="${request.contextPath}/controller?command=rents_page&id=${sessionScope.user.id}">${stringManager.get("rents")}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${request.contextPath}/controller?command=profile">${stringManager.get("profile")}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${request.contextPath}/controller?command=logout">${stringManager.get("logout")}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${request.contextPath}/controller?command=localization&language=us">
                <img class="language-icon" src="${request.contextPath}/united-kingdom.svg" alt="English">
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link language-icon" href="${request.contextPath}/controller?command=localization&language=ru">
                <img class="language-icon" src="${request.contextPath}/russia.svg" alt="Русский">
            </a>
        </li>
    </ul>
</nav>