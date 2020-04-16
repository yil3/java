<%--
  Created by IntelliJ IDEA.
  User: x
  Date: 2020/3/28
  Time: 7:30 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
  Created by IntelliJ IDEA.
  User: x
  Date: 2020/3/27
  Time: 5:16 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<div style="width:80%; margin:40px auto 0px auto">
    <form action="${pageContext.request.contextPath}/book/update" method="post">
        <input type="text" name="bookName" class="form-control" value="${Qbook.bookName}">
        <input type="text" name="bookCounts" class="form-control" value="${Qbook.bookCounts}">
        <input type="text" name="detail" class="form-control" value="${Qbook.detail}">
        <input type="hidden" name="bookID" value="${Qbook.bookID}">
        <input type="submit" class="btn btn-primary" value="修改">
    </form>
</div>
</body>
</html>

</body>
</html>
