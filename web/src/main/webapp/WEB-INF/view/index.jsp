<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/header.jsp"/>
<h1>Hi</h1>
<table id="bank_accounts_table">
    <thead>
        <tr>
            <th>IBAN</th>
            <th>Business Identifier Code</th>
            <th>Edit</th>
            <th>Remove</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>${account.iban}</td>
            <td>${account.bic}</td>
            <td><a href="<c:url value='/edit-user-${account.id}' />" class="btn btn-success
custom-width">edit</a></td>
            <td><a href="<c:url value='/delete-user-${account.id}' />" class="btn btn-danger
custom-width">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="templates/footer.jsp"/>
