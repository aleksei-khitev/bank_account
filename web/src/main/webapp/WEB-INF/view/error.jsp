<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/header.jsp" />
<h3>ERROR</h3>
<h3>${title}</h3>
<b>Подробнее:</b><br/>
${exception}
<jsp:include page="templates/footer.jsp" />