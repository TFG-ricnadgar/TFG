<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                <head>
                    <title>Modificar usuario</title>
                </head>

                <body>
                    <dungeonRaiders:header />
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-sm-8 text-center" style="font-family:fantasy; letter-spacing: 1px;">
                                <h1 class="display-3" style="color: #e86e02;">EDITA TU AVENTURERO</h1>

                                <c:if test="${messages != null}">
                                    <c:forEach items="${messages}" var="message">
                                        <div class="alert alert-danger" role="alert">
                                            ${message}
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <form:form modelAttribute="user" class="form-vertical">
                                    <dungeonRaiders:inputField name="username" label="Nombre de usuario" />
                                    <input type="hidden" name="password" value="${user.password}" />

                                    <div class="form-group">
                                        <h4><label for="oldPassword">Contraseña antigua</label></h4>
                                        <input type="password" name="oldPassword" id="repeatedPassword"
                                            class="form-control" />
                                    </div>

                                    <div class="form-group">
                                        <h4><label for="newPassword">Contraseña nueva</label></h4>
                                        <input type="password" name="newPassword" id="newPassword"
                                            class="form-control" />
                                    </div>

                                    <div class="row">
                                        <div class="col">
                                            <img src="/img/icons/CrystalBall.png" width="50px">
                                        </div>
                                        <div class="col-8">
                                            <button type="submit" class="btn btn-lg btn-primary btn-block"
                                                style="background-color: #e86e02; border-color: #9b5c26;">
                                                <u>Guardar cambios</u>
                                            </button>
                                        </div>
                                        <div class="col">
                                            <img src="/img/icons/CrystalBall.png" width="50px">
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>

                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                        crossorigin="anonymous"></script>
                </body>