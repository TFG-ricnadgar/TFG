<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <title>Aventura finalizada</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
                    crossorigin="anonymous">
                <meta http-equiv="refresh" content="5">
            </head>

            <body style="font-family:fantasy; letter-spacing: 1px;">
                <dungeonRaiders:header />
                <div class="col-lg text-center" style="color: #e86e02;">
                    <h1 class="display-5">
                        <u>MISIÓN:</u>
                        <span style="color: black;">
                            <c:out value="${game.name}" />
                        </span>
                        <div style="color: rgb(124, 18, 18);">
                            FINALIZADA
                        </div>
                    </h1>
                </div>

                <div class="col-lg text-center">
                    <h2>
                        <img src="/img/icons/Coin.png" width="45px" />
                        Aventurero vencedor
                        <img src="/img/icons/Coin.png" width="45px" />
                    </h2>
                </div>

                <div class="col d-flex justify-content-center">
                    <div class="card" style="background-color:#c4b3a2;margin:10px;width:17rem">
                        <div class="row text-center">
                            <div class="col-md-3 mx-auto my-auto ">
                                <img src="${game.winnerPlayer.character.image}" width="50px" />
                            </div>
                            <div class="col-md-7 mx-auto my-auto">
                                <c:out value="${game.winnerPlayer.user.username}" />
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-lg text-center">
                    <h2>
                        <img src="/img/icons/Wound.png" width="45px" />
                        Otros aventureros
                        <img src="/img/icons/Wound.png" width="45px" />
                    </h2>
                </div>

                <div class="col d-flex justify-content-center">
                    <c:forEach items="${game.players}" var="player">
                        <c:if test="${player.id != game.winnerPlayer.id}">
                            <div class="card" style="background-color:#c4b3a2;margin:10px;width:17rem">
                                <div class="row text-center">
                                    <div class="col-md-3 mx-auto my-auto ">
                                        <img src="${player.character.image}" width="50px" />
                                    </div>
                                    <div class="col-md-7 mx-auto my-auto">
                                        <c:out value="${player.user.username}" />
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

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