<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<head>
<!-- Bootstrap import-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
                    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                    crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark"
                style="background-color: #e86e02; font-family:fantasy; letter-spacing: 1px;">

                <a class="navbar-brand" href="/">
                    <img src="/img/logo.png" style="margin-right: 10px; border-radius: 15%; " width="45px" />
                    Dungeon Riders
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                    aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        
                            <li class="nav-item">
                                <a class="nav-link" href="/">Inicio</a>
                            </li>
                            
                        <sec:authorize access="isAuthenticated()">
                            <li class="nav-item">
                                <a class="nav-link" href="/game/list">Buscar partida</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/game/new">Crear partida</a>
                            </li>
                            <li class="dropdown" >
                                <a class="dropdown-toggle nav-link" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Estad&iacutesticas
                                </a>

                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu" >
                                    <li><a class="dropdown-item" href="<c:url value="/user/stats" />">Individuales</a></li>
                                    <li><a class="dropdown-item" href="<c:url value="/user/globalstats" />">Globales</a></li>
                                </ul>
                               

                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/user/update">Modificar usuario</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/user/logout" />">Cerrar sesi&oacuten</a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/user/login">Iniciar sesi&oacuten</a>
                        </li>
                        </sec:authorize>
                    </ul>
                </div>
        </nav>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
   </body>