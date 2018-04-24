<%@include file="_header.jsp" %>

<h1>Liste des commandes</h1>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:choose>
<c:when test="${empty orders}">
<p>aucun ordre</p>
</c:when>
<c:otherwise>
Les commandes
<ul>
<c:forEach items="${orders}" var="order" >
<li>
<c:out value="${order.id }"></c:out>
<ul>
<c:forEach items="${order.articles}" var="article" >
<li><c:out value="${article }"></c:out></li>
</c:forEach>
</ul>
</c:forEach>
</c:otherwise>

</c:choose>
<%@include file="_footer.jsp" %>