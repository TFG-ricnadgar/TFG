<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                <dungeonRaiders:header />
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-sm-8 text-center" style="font-family:fantasy; letter-spacing: 1px;">
                            <h1 class="display-3" style="color: #e86e02;">INICIAR SESIÓN</h1>

                            <form:form modelAttribute="user" class="form-vertical">
                                <div class="form-group">
                                    <h4><label for="username">Nombre de usuario</label></h4>
                                    <input name="username" id="username" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <h4><label for="password">Contraseña</label></h4>
                                    <input type="password" name="password" id="password" class="form-control" />
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <img src="/img/Antorcha.png" width="50px">
                                    </div>
                                    <div class="col-8">
                                        <button type="submit" class="btn btn-lg btn-primary btn-block"
                                            style="background-color: #e86e02; border-color: #9b5c26;">
                                            <u>Adentrate en la mazmorra</u>
                                        </button>
                                    </div>
                                    <div class="col">
                                        <img src="/img/Antorcha.png" width="50px">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>