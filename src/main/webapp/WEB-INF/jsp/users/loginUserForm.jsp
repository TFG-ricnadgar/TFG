<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                <head>
                    <title>Iniciar sesión</title>
                </head>

                <body>
                    <dungeonRaiders:header />
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-sm-8 text-center" style="font-family:fantasy; letter-spacing: 1px;">
                                <h1 class="display-3" style="color: #e86e02;">INICIAR SESIÓN</h1>

                                <form:form modelAttribute="user" class="form-vertical">
                                    <dungeonRaiders:inputField name="username" label="Nombre de usuario" />
                                    <dungeonRaiders:inputFieldPassword name="password" label="Contraseña" />

                                    <div class="row">
                                        <div class="col">
                                            <img src="/img/icons/Torch.png" width="50px">
                                        </div>
                                        <div class="col-8">
                                            <button type="submit" class="btn btn-lg btn-primary btn-block"
                                                style="background-color: #e86e02; border-color: #9b5c26;">
                                                <u>Adéntrate en la mazmorra</u>
                                            </button>
                                        </div>
                                        <div class="col">
                                            <img src="/img/icons/Torch.png" width="50px">
                                        </div>
                                    </div>
                                    <div>
                                        ¿No eres todavía un aventurero?
                                    </div>
                                    <div>
                                        <a href="/user/register" style="color: #e86e02;">Únete ahora</a>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </body>