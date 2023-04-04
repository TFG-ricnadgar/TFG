<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <body>
                <jspTagStart />
                <div class="col d-flex justify-content-center">

                    <div class="card" style="background-color:#c2ac98;margin:10px;width:27rem;font-size:25px">
                        <div class="row text-center">
                            <div class="col-md-2 mx-auto  my-auto ">
                                <img src="${activePlayer.character.image}" width="70px" />
                            </div>
                            <div class="col-md-8 my-auto mx-auto">
                                <c:out value="${activePlayer.user.username}" />
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-sm-5 my-auto mx-auto ">
                                <img src="/img/icons/Coin.png" width="50px" />
                                <c:out value="${activePlayer.coins}" />
                            </div>
                            <div class="col-sm-5 my-auto mx-auto">
                                <img src="/img/icons/Wound.png" width="50px" />
                                <c:out value="${activePlayer.wounds}" />
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-sm-7" style="padding-right: 0px;">
                                Cartas jugadas :
                            </div>
                            <div class="col-sm-5" style="padding-left: 0px;">
                                <c:forEach items="${activePlayer.cards}" var="card">
                                    <c:if test="${card.isUsed && !card.isRecentlyUsed}">
                                        <img src="${card.type.image}" width="30px" />
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <jspTagEnd />
            </body>