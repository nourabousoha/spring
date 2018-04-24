<%@include file="/WEB-INF/views/_header.jsp" %>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   


<c:choose>
<c:when test="${empty categories}">
</c:when>
<c:otherwise>
categories
<pre>
<c:forEach items="${categories}" var="category" >
<c:out value="${category.name }"></c:out>
</c:forEach>
</c:otherwise>
</c:choose>
</pre>
<h3>Add/Edit Categories</h3>
        <form method="POST" action="add.html">
             <table>
                <tr>
                    <td><label >Name</label></td>
                    <td><input name="name"/></td>
                </tr>
                
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
<%@include file="/WEB-INF/views/_footer.jsp" %>
        