<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ page import="etsii.tfg.DungeonRaiders.card.CardState" %>

                <head>
                    <title>Aventura en curso</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
                        rel="stylesheet"
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


                    <span id="allReloadInfo">
                    </span>


                    <div class="text-center" style="margin-top: 8px;">
                        <a href="exit" style="color: #961212;">
                            <u>Abandonar incursión</u>
                        </a>
                    </div>

                    <br>




                    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>

                    <script type="text/javascript">

                        function checkGameReloadNeeded() {
                            var url = window.location.pathname + "/reloadNeeded";
                            $.get(url, function (data, status) {
                                if (data == true)
                                    location.reload();
                            });
                        }
                        function checkPlayersChanged() {
                            var url = window.location.pathname + "/playerAmount";
                            $.get(url, function (data, status) {
                                if (players == "0") {
                                    players = data;
                                } else if (players != data) {
                                    changeRoomsPlayers();
                                    players = data;
                                }
                            });
                        }

                        function changeRoomsPlayers() {
                            var content = $('#allReloadInfo');
                            var url = window.location.pathname + "/reload";
                            var pageTextBetweenDelimiters;
                            content.load(url, function (response, status, xhr) {
                                var fullPageTextAsString = response;
                                pageTextBetweenDelimiters = fullPageTextAsString.substring(fullPageTextAsString.indexOf("<jspTagStart />"), fullPageTextAsString.indexOf("<jspTagEnd />"));
                                content.html(pageTextBetweenDelimiters);
                            });
                        }

                        function checkTurnChanged() {
                            var url = window.location.pathname + "/turn";
                            $.get(url, function (data, status) {
                                if (turn == "-2") {
                                    turn = data;
                                } else if (turn != data) {
                                    changeRoomsPlayers();
                                    turn = data;
                                }
                            });
                        }

                        function checkRevealedCardsChanged() {
                            var url = window.location.pathname + "/revealed";
                            $.get(url, function (data, status) {
                                if (!data && !revealedCards) {
                                    revealedCards = data;
                                } else if (revealedCards != data) {
                                    changeRoomsPlayers();
                                    revealedCards = data;
                                }
                            });
                        }



                        var turn = "-2";
                        var players = "0";
                        var revealedCards = false;
                        checkPlayersChanged();
                        changeRoomsPlayers();
                        checkTurnChanged();
                        setInterval(checkGameReloadNeeded, 3000);
                        setInterval(checkPlayersChanged, 6000);
                        setInterval(checkTurnChanged, 3000);
                        setInterval(checkRevealedCardsChanged, 3000);




                    </script>



                </body>