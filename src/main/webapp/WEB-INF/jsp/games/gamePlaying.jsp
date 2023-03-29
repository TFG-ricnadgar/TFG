<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <title>Aventura en curso</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
                    crossorigin="anonymous">
            </head>

            <body style="font-family:fantasy; letter-spacing: 1px;">
                <dungeonRaiders:header />
                <div class="col-lg text-center" style="color: #e86e02;">
                    <h1 class="display-5">
                        <u>MISIÓN:</u>
                        <span style="color: black;">
                            <c:out value="${game.name}" />
                        </span>
                    </h1>
                </div>

                <div class="col d-flex justify-content-center">
                    <c:forEach items="${otherPlayers}" var="player">
                        <div class="card" style="background-color:#c4b3a2;margin:10px;width:17rem">
                            <div class="row text-center">
                                <div class="col-md-3 mx-auto my-auto ">
                                    <img src="${player.character.image}" width="50px" />
                                </div>
                                <div class="col-md-7 mx-auto my-auto">
                                    <c:out value="${player.user.username}" />
                                </div>
                            </div>
                            <div class="row text-center">
                                <div class="col-sm-5 mx-auto my-auto ">
                                    <img src="/img/icons/Coin.png" width="35px" />
                                    <c:out value="${player.coins}" />
                                </div>
                                <div class="col-sm-5 mx-auto my-auto">
                                    <img src="/img/icons/Wound.png" width="35px" />
                                    <c:out value="${player.wounds}" />
                                </div>
                            </div>
                            <div class="row text-center ">
                                <div class="col-sm-6" style="padding-right: 0px;">
                                    Cartas jugadas :
                                </div>
                                <div class="col-sm-6" style="padding-left: 0px;">
                                    <c:forEach items="${player.cards}" var="card">
                                        <c:if test="${card.isUsed && !card.isRecentlyUsed}">
                                            <img src="${card.type.image}" width="25px" />
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <c:if test="${!empty revealedCards}">
                    <div class="col d-flex justify-content-center">
                        <div class="card text-center"
                            style="background-color:#dfb8f1 ; border-color: #5a0075; border-width: 0.15em;width: 28rem; margin-bottom: 10px;">
                            <h3>
                                <img src="/img/icons/CrystalBall.png" width="40px" />
                                Cartas reveladas
                                <img src="/img/icons/CrystalBall.png" width="40px" />
                            </h3>
                            <div>
                                <c:forEach items="${revealedCards}" var="cardType">
                                    <img src="${cardType.image}" width="65px" />
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>

                <span id="allDungeonRooms">
                </span>

                <div class="col d-flex justify-content-center">
                    <c:forEach items="${activePlayer.cards}" var="card">
                        <c:if test="${!card.isUsed}">
                            <div class="card text-center" style="background-color:#f3e1c0;margin:5px;">
                                <img src="${card.type.image}" width="70px" />
                                <a href="card/${card.id}/play">
                                    <button type="button" class="btn btn-lg btn-primary btn-block"
                                        style="background-color: #e86e02; border-color: #9b5c26;">
                                        Jugar
                                    </button>
                                </a>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

                <div class="col d-flex justify-content-center">

                    <div class="card" style="background-color:#c2ac98;margin:10px;width:27rem;font-size:25px">
                        <div class="row text-center">
                            <div class="col-md-2 mx-auto  my-auto ">
                                <img src="${activePlayer.character.image}" width="70px" />
                            </div>
                            <div class="col-md-8 my-auto mx-auto">
                                <c:out value="${activePlayer.user.username}" />
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-sm-5 my-auto mx-auto ">
                                <img src="/img/icons/Coin.png" width="50px" />
                                <c:out value="${activePlayer.coins}" />
                            </div>
                            <div class="col-sm-5 my-auto mx-auto">
                                <img src="/img/icons/Wound.png" width="50px" />
                                <c:out value="${activePlayer.wounds}" />
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-sm-7" style="padding-right: 0px;">
                                Cartas jugadas :
                            </div>
                            <div class="col-sm-5" style="padding-left: 0px;">
                                <c:forEach items="${activePlayer.cards}" var="card">
                                    <c:if test="${card.isUsed && !card.isRecentlyUsed}">
                                        <img src="${card.type.image}" width="30px" />
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="text-center" style="margin-top: 8px;">
                    <a href="exit" style="color: #961212;">
                        <u>Abandonar incursión</u>
                    </a>
                </div>


                <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                    crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                    crossorigin="anonymous"></script>

                <script type="text/javascript">

                    function changeRooms() {
                        var content = $('#allDungeonRooms');
                        var url = "/game/1/playing/rooms";

                        content.load(url, function (response, status, xhr) {

                            var fullPageTextAsString = response;
                            var pageTextBetweenDelimiters = fullPageTextAsString.substring(fullPageTextAsString.indexOf("<jspTagStart/>"), fullPageTextAsString.indexOf("<jspTagEnd />"));
                            if (pageTextBetweenDelimiters != oldContentHtml) {
                                content.html(response);
                            }
                        });
                    }

                    changeRooms()
                    setInterval(changeRooms, 3000);
                </script>


            </body>