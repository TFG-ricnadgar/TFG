<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of stat" %>
<%@ attribute name="namePost" required="false" rtexprvalue="true"
              description="Name of stat post" %>

<%@ attribute name="value" required="true" rtexprvalue="true"
              description="Value of stat" %>
<%@ attribute name="img" required="false" rtexprvalue="true"
              description="Value of optional image" %>

                            <span style="color: #9a2f0e "> <c:out value="${name}" /> 
                            <c:if test="${!empty img}"> <img src="${img}" width="50px"/> </c:if>
                            <c:if test="${!empty namePost}"> <c:out value="${namePost}" /> </c:if> :
                                <span style="color: #050505 ">
                                    <c:out value="${value}" />        
                                </span>
                            </span>