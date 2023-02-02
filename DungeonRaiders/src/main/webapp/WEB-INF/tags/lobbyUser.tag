<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of user" %>
<%@ attribute name="img" required="true" rtexprvalue="true"
              description="Image of character" %>


    <c:if test="${not empty name }">
        <div class="card" style="background-color:#a7917b;margin:10px;width:15rem">
            <div class="row">
                <div class="col-md-3 my-auto mx-auto ">
                    <img src="${img}" width="50px"/>
                </div>
                <div class="col-md-7 my-auto  mx-auto ">
                        <c:out value="${name}" />
                </div>
            </div>
        </div>
        </c:if>
    