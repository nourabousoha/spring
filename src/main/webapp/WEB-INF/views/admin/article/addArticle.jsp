<%@include file="/WEB-INF/views/_header.jsp" %>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   


<c:choose>
<c:when test="${empty articles}">
</c:when>
<c:otherwise>
Articles
<pre>
<c:forEach items="${articles}" var="article" >
<c:out value="${article.name }"></c:out>
</c:forEach>
</c:otherwise>
</c:choose>
</pre>
<h3>Add/Edit Articles</h3>
        <form method="POST" action="add.html">
             <table>
                <tr>
                    <td><label >Categorie</label></td>
                    <td><select name="scategories">
                     <c:forEach items="${categories}" var="category" >
                     <option value="${category.name }">${category.name }</option>
                     </c:forEach>
                    </select>
                    </td>
                    
                </tr>
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
        