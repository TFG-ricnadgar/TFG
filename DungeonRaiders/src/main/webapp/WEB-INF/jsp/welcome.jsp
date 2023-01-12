<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>


    <head>

                <!-- Bootstrap import-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
                    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                    crossorigin="anonymous">
    </head>


    <body>
        <dungeonRaiders:menu>
            <div class="row">
                <div class="col">
                    <img src="../static/img/ExploradoraBarbaro.png" th:src="@{img/ExploradoraBarbaro.png}"
                        style="max-width: 330px;" />
                    <img src="../static/img/Guerrero.png" th:src="@{img/Guerrero.png}"
                        style="max-width: 200px;" />
                </div>

                <div class="col"> </div>
                
                <div class="col">
                    <img src="../static/img/Enemigos.png" th:src="@{img/Enemigos.png}"
                        style="max-width: 530px;" />
                </div>
            </div>

            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-sm-8 text-center" style="font-family:fantasy; letter-spacing: 1px;">
                        <h1 class="display-1" style="color: #e86e02;">DUNGEON RAIDERS</h1>
                        <h4 style="color: #9a2f0e ">Explora una mazmorra llena de monstruos, trampas y tesoros. </h4>
                            <p>
                                En Dungeon Raiders, cada jugador toma el papel de un aventurero diferente. Tendréis
                                que trabajar juntos para sobrevivir en la mazmorra, pero sólo uno de vosotros 
                                conseguirá salir con la mayor cantidad de tesoro posible y ganar el juego. 
                                La mazmorra es diferente cada vez que juegas, ofreciendo nuevas sorpresas
                                a medida que avanzas por ella.
                            </p>
                    </div>
                </div>
            </div>
        </dungeonRaiders:menu>
    </body>
