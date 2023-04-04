<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="dungeonRaiders" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


            <body style="font-family:fantasy; letter-spacing: 1px;">
                <jspTagStart />
                <div class="col d-flex justify-content-center">
                    <c:forEach items="${floorDungeonRooms}" var="dungeonRoom">
                        <div class="card text-center"
                            style="background-color:#96aecf ; border-color: #415d8a; border-width: 0.15em;width: 18rem;">

                            <c:choose>
                                <c:when test="${game.getActualRoomInFloor() > dungeonRoom.position}">
                                    <h3>
                                        HABITACIÓN <br> EXPLORADA
                                    </h3>
                                    <img class="card-img" src="/img/rooms/ClosedDungeonDoor.png" />
                                </c:when>
                                <c:when
                                    test="${dungeonRoom.isHidden && game.getActualRoomInFloor() < dungeonRoom.position}">
                                    <h3>
                                        HABITACIÓN <br> OCULTA
                                    </h3>
                                    <img class="card-img" src="/img/rooms/OpenedDungeonDoor.png" />

                                    <c:if test="${activePlayer.hasATorch()}">
                                        <a href="room/${dungeonRoom.id}/reveal">
                                            <button type="button" class="btn btn-lg btn-primary btn-block"
                                                style="background-color: #e86e02; border-color: #9b5c26;">
                                                <img src="/img/icons/Torch.png" width="35px" />
                                                Revelar
                                                <img src="/img/icons/Torch.png" width="35px" />
                                            </button>
                                        </a>
                                    </c:if>
                                </c:when>
                                <c:when
                                    test="${!dungeonRoom.isHidden || game.getActualRoomInFloor() == dungeonRoom.position}">
                                    <h3>
                                        <c:out value="${dungeonRoom.room.name}" />
                                    </h3>
                                    <c:choose>
                                        <c:when test="${dungeonRoom.room.getType() == 'ENEMY'}">
                                            <img class="card-img-top" src="/img/rooms/Enemy.png" />
                                            <div>
                                                <img src="/img/icons/EnemyHealth.png" width="40px" />
                                                <c:out value="${dungeonRoom.room.getHealth(game.players.size())}" />

                                                <img src="/img/icons/Wound.png" width="40px" />
                                                <c:out value="${dungeonRoom.room.damage}" />
                                            </div>
                                        </c:when>
                                        <c:when test="${dungeonRoom.room.getType() == 'SHOP'}">
                                            <img class="card-img-top" src="/img/rooms/Shop.png" />
                                            <div>
                                                <div>
                                                    <img src="/img/icons/1.png" width="35px" />
                                                    <img src="${dungeonRoom.room.firstItem.image}" width="40px" />
                                                    <img src="/img/icons/2.png" width="35px" width="35px" />
                                                    <img src="${dungeonRoom.room.secondItem.image}" width="40px" />
                                                </div>
                                                <div>
                                                    <img src="/img/icons/3.png" width="35px" width="35px" />
                                                    <img src="${dungeonRoom.room.thirdItem.image}" width="40px" />
                                                    <img src="/img/icons/4.png" width="35px" width="35px" />
                                                    <img src="${dungeonRoom.room.fourthItem.image}" width="40px" />
                                                    <img src="/img/icons/5.png" width="35px" />
                                                    <img src="${dungeonRoom.room.fifthItem.image}" width="40px" />
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${dungeonRoom.room.getType() == 'TREASURE'}">

                                            <img class="card-img-top" src="/img/rooms/Chest.png" />
                                            <div class="mx-auto my-auto">
                                                <h5>Cofre grande
                                                    <img src="/img/icons/Coin.png" width="35px" />
                                                    <c:out value="${dungeonRoom.room.firstChestCoins}" />
                                                </h5>
                                            </div>
                                            <c:if test="${dungeonRoom.room.secondChestCoins != 0}">
                                                <div class="mx-auto my-auto">
                                                    <h5>Cofre pequeño
                                                        <img src="/img/icons/Coin.png" width="35px" />
                                                        <c:out value="${dungeonRoom.room.secondChestCoins}" />
                                                    </h5>
                                                </div>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${dungeonRoom.room.getType() == 'TRAP'}">
                                            <img class="card-img-top" src="/img/rooms/Trap.png" />
                                            <div>
                                                <img src="${dungeonRoom.room.target.imageOrder}" width="50px" />
                                                <img src="${dungeonRoom.room.target.imageCharacteristic}"
                                                    width="50px" />
                                            </div>
                                            <div>
                                                <img src="/img/icons/5.png" width="40px" />
                                                <c:out value="${dungeonRoom.room.valueFive}" />
                                                <img src="/img/icons/4.png" width="40px" />
                                                <c:out value="${dungeonRoom.room.valueFour}" />
                                                <img src="/img/icons/3.png" width="40px" />
                                                <c:out value="${dungeonRoom.room.valueThree}" />
                                                <c:if test="${dungeonRoom.room.valueTwo != 0}">
                                                    <img src="/img/icons/2.png" width="40px" />
                                                    <c:out value="${dungeonRoom.room.valueTwo}" />
                                                </c:if>
                                            </div>
                                        </c:when>
                                        <c:when test="${dungeonRoom.room.getType() == 'FINAL_BOSS'}">
                                            <img class="card-img-top" src="/img/rooms/FinalBoss.png" />
                                            <div>
                                                <img src="/img/icons/EnemyHealth.png" width="40px" />
                                                <c:out value="${dungeonRoom.room.getHealth(game.players.size())}" />

                                                <img src="${dungeonRoom.room.damageType.image}" width="40px" />
                                                <c:out value="${dungeonRoom.room.damageAmount}" />
                                            </div>
                                            <div>
                                                <c:if test="${dungeonRoom.room.escapeCard!=null}">
                                                    <img src="/img/icons/Exit.png" width="40px" />
                                                    <img src="${dungeonRoom.room.escapeCard.image}" width="40px" />
                                                </c:if>
                                                <c:if test="${dungeonRoom.room.topCardCoinReward > 0}">
                                                    <img src="/img/icons/topCardReward.png" width="40px" />
                                                    <c:out value="${dungeonRoom.room.topCardCoinReward}" />
                                                </c:if>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </div>
                    </c:forEach>
                </div>
                <jspTagEnd />
            </body>