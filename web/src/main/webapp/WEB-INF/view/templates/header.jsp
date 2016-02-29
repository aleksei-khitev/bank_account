<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <link href="<c:url value='/css/normalize.css' />" type="text/css" rel="stylesheet"/>
  <link href="<c:url value='/css/jquery.dataTables.min.css' />" type="text/css" rel="stylesheet"/>
  <link href="<c:url value='/css/jquery-ui.css' />" type="text/css" rel="stylesheet"/>
  <link href="<c:url value='/css/styles.css' />" type="text/css" rel="stylesheet"/>
  <script type="application/javascript">
    var ctx = "<%=request.getContextPath()%>";
  </script>
  <script src="<c:url value='/js/jquery-1.10.2.js' />" type="text/javascript"></script>
  <script src="<c:url value='/js/jquery.min.js' />" type="text/javascript"></script>
  <script src="<c:url value='/js/jquery-1.11.1.min.js' />" type="text/javascript"></script>
  <script src="<c:url value='/js/jquery.validate.min.js' />" type="text/javascript"></script>
  <script src="<c:url value='/js/additional-methods.min.js' />" type="text/javascript"></script>
  <script src="<c:url value='/js/bank_account.js' />" charset="utf-8" type="text/javascript"></script>
  <script src="<c:url value='/js/jquery-ui.js' />" type="text/javascript"></script>
  <script src="<c:url value='/js/datatables.min.js' />" type="text/javascript"></script>
  <script src="<c:url value='/js/jquery.mask.js' />" type="text/javascript"></script>
  <title>Bank Accounts</title>
</head>
<body>
<div id="operation_status" name="error">
</div>
<header>
  <h1 id="badgeName">Bank Account Management</h1>
</header>