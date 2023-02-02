<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of user" %>
<%@ attribute name="img" required="true" rtexprvalue="true"
              description="Image of character" %>
<%@ attribute name="coins" required="true" rtexprvalue="true"
              description="Amount of coins" %>
<%@ attribute name="wounds" required="true" rtexprvalue="true"
              description="Amount of wounds" %>

    <c:if test="${not empty name }">
        <div class="card" style="background-color:#a7917b;margin:10px;width:25rem;font-size:25px" >
            <div class="row">
                <div class="col-lg-2 mx-auto  my-auto ">
                    <img src="${img}" width="70px"/>
                </div>
                <div class="col-lg-8 my-auto mx-auto">
                        <c:out value="${name}" />
                </div>
            </div>
            <div class="row text-center">
                <div class="col-lg-6 my-auto ">
                    Mon: <c:out value="${coins}" />
                </div>
                <div class="col-lg-5 my-auto">
                    Her: <c:out value="${wounds}" />
                </div>
            </div>
        </div>
        </c:if>
    