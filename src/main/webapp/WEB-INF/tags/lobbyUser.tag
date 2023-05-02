<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of user" %>
<%@ attribute name="img" required="true" rtexprvalue="true"
              description="Image of character" %>
<%@ attribute name="id" required="true" rtexprvalue="true"
              description="Id of player" %>
<%@ attribute name="isGameCreator" required="true" rtexprvalue="true"
              description="Active user is game creator" type="java.lang.Boolean" %>



    <c:if test="${not empty name }">
        <div class="card" style="background-color:#a7917b;margin:10px;width:15rem">
            <div class="row">
                <div class="col-md-3 my-auto mx-auto ">
                    <img src="${img}" width="50px"/>
                </div>
                <div class="col-md-7 my-auto  mx-auto ">
                        <c:out value="${name}" />
                        <c:if test="${isGameCreator && pageContext.request.userPrincipal.name != name }">
                           <a href="lobby/player/${id}/kick"><img src="/img/icons/Wound.png" width="40px" /> <a/>
                        </c:if>
                </div>

            </div>
        </div>
       
    </c:if>
    