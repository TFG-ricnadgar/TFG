<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                <head>
                    <title>Crear partida</title>

                    <!-- Bootstrap import-->
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
                        rel="stylesheet"
                        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
                        crossorigin="anonymous">
                </head>

                <body>
                    <dungeonRaiders:header />
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-sm-8 text-center" style="font-family:fantasy; letter-spacing: 1px;">
                                <h1 class="display-3" style="color: #e86e02;">CREAR PARTIDA</h1>

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
                                <form:form modelAttribute="game" class="form-vertical">
                                    <dungeonRaiders:inputField name="name" label="Nombre de la partida" />
                                    <h4>Maximo de jugadores en partida</h4>

                                    <div class="btn-group btn-group-lg d-grid d-md-flex " role="group"
                                        aria-label="Cantidad maxima de jugadores en partida">
                                        <input type="radio" class="btn-check" name="maxPlayers" id="3" value="3"
                                            autocomplete="off" checked>
                                        <label class="btn btn-outline-dark" for="3">3</label>

                                        <input type="radio" class="btn-check" name="maxPlayers" id="4" value="4"
                                            autocomplete="off">
                                        <label class="btn btn-outline-dark" for="4">4</label>

                                        <input type="radio" class="btn-check" name="maxPlayers" id="5" value="5"
                                            autocomplete="off">
                                        <label class="btn btn-outline-dark" for="5">5</label>
                                    </div>




                                    <div class="row" style="margin-top: 8px;">
                                        <div class="col">
                                            <img src="/img/rooms/Chest.png" width="60px">
                                        </div>
                                        <div class="col-8">
                                            <button type="submit" class="btn btn-lg btn-primary btn-block"
                                                style="background-color: #e86e02; border-color: #9b5c26;">
                                                <u>Empieza la aventura</u>
                                            </button>
                                        </div>
                                        <div class="col">
                                            <img src="/img/rooms/Chest.png" width="60px">
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>


                    <!-- Bootstrap scripts-->
                    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                        crossorigin="anonymous"></script>
                    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                        crossorigin="anonymous"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                        crossorigin="anonymous"></script>
                </body>