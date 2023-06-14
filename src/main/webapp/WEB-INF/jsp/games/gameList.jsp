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

                    <span id="reloadGameListInfo">

                    </span>

                </div>

                <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                    crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                    crossorigin="anonymous"></script>

                <script type="text/javascript">

                    function changeGames() {
                        var content = $('#reloadGameListInfo');
                        var url = window.location.pathname + "/reload";
                        var pageTextBetweenDelimiters;
                        content.load(url, function (response, status, xhr) {
                            var fullPageTextAsString = response;
                            pageTextBetweenDelimiters = fullPageTextAsString.substring(fullPageTextAsString.indexOf("<jspTagStart />"), fullPageTextAsString.indexOf("<jspTagEnd />"));
                            content.html(pageTextBetweenDelimiters);
                        });
                    }


                    function checkGamesChanged() {
                        var url = window.location.pathname + "/amount";
                        $.get(url, function (data, status) {
                            if (games == "-1") {
                                games = data;
                            } else if (games != data) {
                                changeGames();
                                games = data;
                            }
                        });
                    }

                    var games = "-1";
                    changeGames();
                    setInterval(checkGamesChanged, 3000);      
                </script>
            </body>