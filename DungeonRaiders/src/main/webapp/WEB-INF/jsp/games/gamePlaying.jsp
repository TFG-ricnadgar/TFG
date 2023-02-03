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
                        <div class="card" style="background-color:#c4b3a2;margin:10px;width:14rem">
                            <div class="row">
                                <div class="col-md-3 mx-auto my-auto ">
                                    <img src="${player.character.image}" width="50px" />
                                </div>
                                <div class="col-md-7 mx-auto my-auto">
                                    <c:out value="${player.user.username}" />
                                </div>
                            </div>
                            <div class="row text-center">
                                <div class="col-md-6 my-auto ">
                                    <img src="/img/Moneda.png" width="30px" />
                                    <c:out value="${player.coins}" />
                                </div>
                                <div class="col-md-5 my-auto">
                                    <img src="/img/Herida.png" width="30px" />
                                    <c:out value="${player.wounds}" />
                                </div>
                            </div>
                            <div class="row text-center">
                                <div class="col-sm-8" style="padding-right: 0px;">
                                    Cartas jugadas :
                                </div>
                                <div class="col-md-4" style="padding-left: 0px;">
                                    <c:forEach items="${player.cards}" var="card">
                                        <c:if test="${card.isUsed}">
                                            <c:out value="${card.type.name}" />
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="col d-flex justify-content-center">
                    <c:forEach items="${activePlayer.cards}" var="card">
                        <c:if test="${!card.isUsed}">
                            <div class="card text-center" style="background-color:#f3e1c0;margin:10px;width:8rem">
                                <c:out value="${card.type.name}" />
                                <a href="card/${card.id}/play">
                                    <button type="button" class="btn btn-lg btn-primary btn-block"
                                        style="background-color: #e86e02; border-color: #9b5c26;">
                                        <u>Jugar carta</u>
                                    </button>
                                </a>
                            </div>

                        </c:if>
                    </c:forEach>
                </div>

                <div class="col d-flex justify-content-center">

                    <div class="card" style="background-color:#c2ac98;margin:10px;width:25rem;font-size:25px">
                        <div class="row">
                            <div class="col-md-2 mx-auto  my-auto ">
                                <img src="${activePlayer.character.image}" width="70px" />
                            </div>
                            <div class="col-md-8 my-auto mx-auto">
                                <c:out value="${activePlayer.user.username}" />
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-md-6 my-auto ">
                                <img src="/img/Moneda.png" width="50px" />
                                <c:out value="${activePlayer.coins}" />
                            </div>
                            <div class="col-md-5 my-auto">
                                <img src="/img/Herida.png" width="50px" />
                                <c:out value="${activePlayer.wounds}" />
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-sm-8" style="padding-right: 0px;">
                                Cartas jugadas :
                            </div>
                            <div class="col-sm-4" style="padding-left: 0px;">
                                <c:forEach items="${activePlayer.cards}" var="card">
                                    <c:if test="${card.isUsed}">
                                        <c:out value="${card.type.name}" />
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
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