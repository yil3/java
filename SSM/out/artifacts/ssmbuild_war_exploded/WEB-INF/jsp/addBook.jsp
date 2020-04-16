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
    <form action="${pageContext.request.contextPath}/book/addBook" method="post">
        <input type="text" name="bookName" class="form-control" placeholder="书籍名称">
        <input type="text" name="bookCounts" class="form-control" placeholder="书籍数量">
        <input type="text" name="detail" class="form-control" placeholder="书籍详情">
        <input type="submit" class="btn btn-primary" value="添加">
    </form>
</div>
</body>
</html>
