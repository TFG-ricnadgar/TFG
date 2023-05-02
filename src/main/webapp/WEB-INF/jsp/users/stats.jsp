<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <title>Estadísticas</title>
            </head>

            <body>
                <dungeonRaiders:header />
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="text-center" style="font-family:fantasy; letter-spacing: 1px;">
                            <h1 class="display-3"><u>Estadísticas de partidas</u></h1>
                            <h2>
                                <dungeonRaiders:stat name="Partidas jugadas" value="${gamesPlayed}" />
                                &ensp; | &ensp;
                                <dungeonRaiders:stat name="Partidas ganadas" value="${gamesWon}" />
                            </h2>
                            <br>
                            <h2>
                                <dungeonRaiders:stat name="Suma total de " img="/img/icons/Coin.png"
                                    value="${totalCoins}" />
                                &ensp; | &ensp;
                                <dungeonRaiders:stat name="Suma total de " img="/img/icons/Wound.png"
                                    value="${totalWounds}" />
                            </h2>
                            <br>
                            <h2>
                                <dungeonRaiders:stat name="Mayor cantidad de " img="/img/icons/Coin.png"
                                    namePost=" en una partida" value="${gamesMaxCoins}" />
                            </h2>
                            <br>
                            <h2>
                                <dungeonRaiders:stat name="Mayor cantidad de " img="/img/icons/Wound.png"
                                    namePost=" en una partida" value="${gamesMaxWounds}" />
                            </h2>

                            <br>
                            <h2>
                                <dungeonRaiders:stat name="Partidas jugadas como" img="/img/characters/ThiefLogo.png"
                                    value="${gamesLadrona}" />
                            </h2>
                            <br>
                            <h2>
                                <dungeonRaiders:stat name="Partidas jugadas como " img="/img/characters/KnightLogo.png"
                                    value="${gamesCaballero}" />
                            </h2>
                            <br>
                            <h2>
                                <dungeonRaiders:stat name="Partidas jugadas como " value="${gamesGuerrero}"
                                    img="/img/characters/WarriorLogo.png" />
                            </h2>
                            <br>
                            <h2>
                                <dungeonRaiders:stat name="Partidas jugadas como "
                                    img="/img/characters/SorcererLogo.png" value="${gamesHechicero}" />
                            </h2>
                            <br>
                            <h2>
                                <dungeonRaiders:stat name="Partidas jugadas como "
                                    img="/img/characters/ExplorerLogo.png" value="${gamesExploradora}" />
                            </h2>

                        </div>
                    </div>
                </div>
            </body>