<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <title>Busqueda de partida</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
                    crossorigin="anonymous">
            </head>

            <body>
                <dungeonRaiders:header />

                <div class="container" style="font-family:fantasy; letter-spacing: 1px;">
                    <div class="row justify-content-center">
                        <div class="col-lg text-center">
                            <h1 class="display-3" style="color: #e86e02;">ÚNETE A UNA INCURSIÓN</h1>
                        </div>
                    </div>

                    <c:forEach items="${gamesList}" var="game">

                        <div class="card"
                            style="margin-bottom: 10px; background-color:#96aecf ; border-color: #415d8a; border-width: 0.15em;">
                            <div class="row">

                                <div class="col my-auto ">
                                    <c:choose>
                                        <c:when test="${game.maxPlayers == game.players.size()}">
                                            <img src="/img/PuertaMazmorraCerrada.png" width="100px">
                                        </c:when>
                                        <c:when test="${game.maxPlayers != game.players.size()}">
                                            <img src="/img/PuertaMazmorraAbierta.png" width="100px">
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div class="col-6 my-auto">
                                    <h2>
                                        <c:out value="${game.name}" /> -
                                        <c:out value="${game.creatorUsername}" />
                                    </h2>
                                </div>
                                <div class="col-2 my-auto">
                                    <c:if test="${game.maxPlayers != game.players.size()}">
                                        <a href="${game.id}/join">
                                            <button type="button" class="btn btn-primary  btn-lg btn-block"
                                                style="background-color: #e86e02; border-color: #9b5c26;">
                                                <h2><u>Únete</u></h2>
                                            </button>
                                        </a>
                                    </c:if>
                                </div>


                                <div class="col my-auto mx-auto">
                                    <h3 class="display-6">
                                        <c:out value="${game.players.size()}" /> /
                                        <c:out value="${game.maxPlayers}" />
                                    </h3>
                                </div>
                            </div>
                        </div>


                    </c:forEach>

                </div>

            </body>