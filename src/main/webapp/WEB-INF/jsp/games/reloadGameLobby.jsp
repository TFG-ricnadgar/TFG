<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <body>
                <jspTagStart />

                <div class="col d-flex justify-content-center">
                    <dungeonRaiders:lobbyUser name="${players[0].user.username}" img="${players[0].character.image}" />
                    <dungeonRaiders:lobbyUser name="${players[1].user.username}" img="${players[1].character.image}" />
                    <dungeonRaiders:lobbyUser name="${players[2].user.username}" img="${players[2].character.image}" />
                </div>
                <div class="col d-flex justify-content-center">
                    <img src="/img/DungeonTable.png">
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
                            <a href="exit" style="color: #961212;">
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
                <jspTagEnd />
            </body>