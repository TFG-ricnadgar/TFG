<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of user" %>
<%@ attribute name="img" required="true" rtexprvalue="true"
              description="Image of character" %>


    <c:if test="${not empty name }">
        <div class="card" style="background-color:#a7917b;margin:10px;width:15rem">
            <div class="row g-0">
                <div class="col-md-3 my-auto ">
                    <img src="${img}" width="50px"/>
                </div>
                <div class="col-md-7 my-auto">
                    <div class="card-body">
                        <c:out value="${name}" />
                    </div>
                </div>
            </div>
        </div>
        </c:if>
    