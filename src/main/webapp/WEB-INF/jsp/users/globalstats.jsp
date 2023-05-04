<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <title>Estadísticas globales</title>
            </head>

            <body>
                <dungeonRaiders:header />
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="text-center" style="font-family:fantasy; letter-spacing: 1px;">
                            <h1 class="display-3"><u>Estadísticas globales</u></h1>
                            <h2>
                                <dungeonRaiders:stat name="Partidas totales jugadas" value="${gamesPlayed}" />
                            </h2>

                            <br>
                            <h2>
                                <span style="color: #9a2f0e ">Duración media de partida: </span>
                                <span id="avgDuration" style="color: #030303 "></span>
                            </h2>
                            <br>
                            <h2>
                                <span style="color: #9a2f0e ">Tiempo total de juego: </span>
                                <span id="totalDuration" style="color: #030303 "></span>
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
                            <br>

                            <h2>
                                Partidas ganadas con:
                            </h2>
                            <canvas id="charactersChartWon"></canvas>
                            <br>


                        </div>
                    </div>
                </div>

                <script>
                    function setTimeCounter(distance, content) {
                        var minutes = Math.floor((distance / 60));
                        var seconds = Math.floor((distance % (60)));
                        var valueHtml = minutes + "m " + seconds + "s";
                        content.html(valueHtml);
                    }
                    setTimeCounter('${avgGameDuration}', $('#avgDuration'));
                    setTimeCounter('${totalGameDuration}', $('#totalDuration'));
                </script>


                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

                <script type="text/javascript">
                    function generateChart(xValues, yValues, images, chartId) {
                        var barColors = ["red", "green", "blue", "orange", "brown"];

                        new Chart(chartId, {
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
                    }
                    var xValues = ['Ladrona', 'Caballero', 'Guerrero', 'Hechicero', 'Exploradora'];
                    var images = ["/img/characters/ThiefLogo.png", "/img/characters/KnightLogo.png", "/img/characters/WarriorLogo.png", "/img/characters/SorcererLogo.png", "/img/characters/ExplorerLogo.png"]
                        .map(png => {
                            const image = new Image();
                            image.src = png;
                            return image;
                        });
                    var yValues = ['${gamesLadrona}', '${gamesCaballero}', '${gamesGuerrero}', '${gamesHechicero}', '${gamesExploradora}'];

                    var yValuesWon = ['${gamesWonLadrona}', '${gamesWonCaballero}', '${gamesWonGuerrero}', '${gamesWonHechicero}', '${gamesWonExploradora}']
                    generateChart(xValues, yValues, images, "charactersChart");
                    generateChart(xValues, yValuesWon, images, "charactersChartWon");
                </script>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                    crossorigin="anonymous"></script>
            </body>