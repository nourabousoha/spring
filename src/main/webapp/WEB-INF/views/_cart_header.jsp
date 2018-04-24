<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<pre>
Panier
<c:choose>
<c:when test="${empty cart}">
<p>aucun article</p>

</c:when>

<c:otherwise>
<c:forEach items="${cart.articles}" var="article" >
<c:out value="${article.name }"></c:out>
<fmt:formatNumber type="currency" value="${article.price/100}"></fmt:formatNumber>

</c:forEach>
</c:otherwise>
</c:choose>
</pre>
<a class="btn btn-primary" href="cart/1/validate.html">Commander</a>