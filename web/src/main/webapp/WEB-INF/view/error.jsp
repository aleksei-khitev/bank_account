<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/header.jsp" />
<h3>ERROR</h3>
<h3>${title}</h3>
<b>Подробнее:</b><br/>

<div class="widgets">
    <div>
        <input type="text" id="inputName" maxlength="15">
    </div>
</div>
<div class="badge">
    <div class="greeting">
        Arrr! Me name is
    </div>
    <div class="name">
        <span id="badgeName"> </span>
    </div>
</div>

${exception}
<jsp:include page="templates/footer.jsp" />