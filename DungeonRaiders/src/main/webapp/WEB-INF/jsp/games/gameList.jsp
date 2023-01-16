<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>

        <head>
            <title>Busqueda de partida</title>
        </head>

        <body>
            <dungeonRaiders:header />
            <c:if test="${gamesList != null}">
                <c:forEach items="${gamesList}" var="game">
                    <div class="card">
                        <div class="card-body">
                            ${game.name}
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </body>