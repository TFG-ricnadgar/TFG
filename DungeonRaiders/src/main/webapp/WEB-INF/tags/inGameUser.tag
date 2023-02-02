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
        <div class="card" style="background-color:#a7917b;margin:10px;width:14rem">
            <div class="row">
                <div class="col-md-3 my-auto ">
                    <img src="${img}" width="50px"/>
                </div>
                <div class="col-md-7 my-auto">
          
                        <c:out value="${name}" />
                  
                </div>
            </div>
            <div class="row text-center">
                <div class="col-md-6 my-auto ">
                    Mon: <c:out value="${coins}" />
                </div>
                <div class="col-md-5 my-auto">
                    Her: <c:out value="${wounds}" />
                </div>
            </div>
        </div>
        </c:if>
    