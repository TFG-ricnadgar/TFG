<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <title>Esperando en la taberna...</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
                    crossorigin="anonymous">
            </head>

            <body style="font-family:fantasy; letter-spacing: 1px;">
                <dungeonRaiders:header />



                <div class="col-lg text-center" style="color: #e86e02;">
                    <h1 class="display-3">
                        <u>MISIÓN:</u>
                        <span style="color: black;">
                            <c:out value="${game.name}" />
                        </span>
                    </h1>
                    <h2 class="display-6">Esperando en la taberna...</h2>
                </div>


                <div class="col d-flex justify-content-center">
                    <dungeonRaiders:lobbyUser name="${players[0].user.username}" img="${players[0].character.image}" />
                    <dungeonRaiders:lobbyUser name="${players[1].user.username}" img="${players[1].character.image}" />
                    <dungeonRaiders:lobbyUser name="${players[2].user.username}" img="${players[2].character.image}" />
                </div>
                <div class="col d-flex justify-content-center">
                    <img src="/img/Mesa.png">
                </div>
                <div class="col d-flex justify-content-center">
                    <dungeonRaiders:lobbyUser name="${players[3].user.username}" img="${players[3].character.image}" />
                    <dungeonRaiders:lobbyUser name="${players[4].user.username}" img="${players[4].character.image}" />
                </div>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == game.creatorUsername}">
                        <div class="row justify-content-center" style="margin-top: 8px;">
                            <div class="col-4">
                                <c:choose>
                                    <c:when test="${game.hasEnoughPlayersToStart()}">
                                        <a href="start">
                                            <button type="button" class="btn btn-lg btn-primary btn-block"
                                                style="background-color: #e86e02; border-color: #9b5c26;">
                                                <u>Comienza la incursión</u>
                                            </button>
                                        </a>
                                    </c:when>
                                    <c:when test="${!game.hasEnoughPlayersToStart()}">
                                        <button type="button" class="btn btn-lg btn-primary btn-block"
                                            style="background-color: #e86e02; border-color: #ac0000;" disabled>
                                            <u>Esperando a mas aventureros</u>
                                        </button>
                                    </c:when>
                                </c:choose>
                            </div>

                        </div>
                        <div class="text-center" style="margin-top: 8px;">
                            <a href="delete" style="color: #961212;">
                                <u>Cancelar incursión</u>
                            </a>
                        </div>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != game.creatorUsername}">
                        <div class="row justify-content-center" style="margin-top: 8px;">
                            <div class="col-4">
                                <a href="exit">
                                    <button type="button" class="btn btn-lg btn-primary btn-block"
                                        style="background-color: #961212;; border-color: #5c1515;">
                                        <u>Abandonar la incursión</u>
                                    </button>
                                </a>
                            </div>

                        </div>
                    </c:when>
                </c:choose>

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