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
                                Partidas jugadas con:
                            </h2>
                            <canvas id="charactersChart"></canvas>

                        </div>
                    </div>
                </div>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

                <script type="text/javascript">
                    var xValues = ['Ladrona', 'Caballero', 'Guerrero', 'Hechicero', 'Exploradora'];
                    var images = ["/img/characters/ThiefLogo.png", "/img/characters/KnightLogo.png", "/img/characters/WarriorLogo.png", "/img/characters/SorcererLogo.png", "/img/characters/ExplorerLogo.png"]
                        .map(png => {
                            const image = new Image();
                            image.src = png;
                            return image;
                        });
                    var yValues = ['${gamesLadrona}', '${gamesCaballero}', '${gamesGuerrero}', '${gamesHechicero}', '${gamesExploradora}'];

                    var barColors = ["red", "green", "blue", "orange", "brown"];

                    new Chart("charactersChart", {
                        type: "bar",
                        plugins: [{
                            afterDraw: chart => {
                                var ctx = chart.chart.ctx;
                                var xAxis = chart.scales['x-axis-0'];
                                var yAxis = chart.scales['y-axis-0'];
                                xAxis.ticks.forEach((value, index) => {
                                    var x = xAxis.getPixelForTick(index);
                                    ctx.drawImage(images[index], x - 21, yAxis.bottom + 10, 45, 50);
                                });
                            },
                        }],
                        data: {
                            labels: xValues,
                            datasets: [{
                                backgroundColor: barColors,
                                data: yValues,

                            }]
                        },
                        options: {
                            responsive: true,
                            legend: {
                                display: false
                            },
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        callback: function (value) { if (value % 1 === 0) { return value; } }
                                    }
                                }],
                                xAxes: [{
                                    ticks: {
                                        padding: 55
                                    }
                                }],

                            }
                        }
                    });
                </script>
            </body>