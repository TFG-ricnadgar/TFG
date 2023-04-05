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
                    <h1 class="display-4">
                        <u>MISIÓN:</u>
                        <span style="color: black;">
                            <c:out value="${game.name}" />
                        </span>
                    </h1>
                    <h2 class="display-6">Esperando en la taberna...</h2>
                </div>

                <span id="reloadGameLobbyInfo">

                </span>


                <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                    crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                    crossorigin="anonymous"></script>

                <script type="text/javascript">
                    function checkGameReloadNeeded() {
                        var url = window.location.pathname + "/reloadNeeded";
                        $.get(url, function (data, status) {
                            if (data == true)
                                location.reload();
                        });
                    }


                    function changePlayers() {
                        var content = $('#reloadGameLobbyInfo');
                        var url = window.location.pathname + "/reload";
                        var pageTextBetweenDelimiters;
                        content.load(url, function (response, status, xhr) {
                            var fullPageTextAsString = response;
                            pageTextBetweenDelimiters = fullPageTextAsString.substring(fullPageTextAsString.indexOf("<jspTagStart />"), fullPageTextAsString.indexOf("<jspTagEnd />"));
                            content.html(pageTextBetweenDelimiters);
                        });
                    }


                    function checkPlayersChanged() {
                        var url = window.location.pathname + "/playerAmount";
                        $.get(url, function (data, status) {
                            if (players == "0") {
                                players = data;
                            } else if (players != data) {
                                changePlayers();
                                players = data;
                            }
                        });
                    }

                    var players = "0";
                    changePlayers();
                    setInterval(checkGameReloadNeeded, 3000);
                    setInterval(checkPlayersChanged, 3000);      
                </script>

            </body>