<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <title>书籍展示</title>
</head>
<body>

<div style="width:80%; margin:40px auto 0px auto;">
    <div style="font-size: 20px">显示书籍</div>
    <br>
    <div style="display: flex; margin: 10px 0;">
        <div><button class="btn btn-default addbook" type="submit">添加书籍</button></div>
    </div>
    <table class="table table-striped table-condensed">
        <tr>
            <th>编号</th>
            <th>书名</th>
            <th>数量</th>
            <th>详情</th>
            <th>修改/删除</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.bookID}</td>
                <td>${book.bookName}</td>
                <td>${book.bookCounts}</td>
                <td>${book.detail}</td>
                <td>
                    <input type="submit" class="btn btn-primary update" id="${book.bookID}" value="修改">
                    <input type="submit" class="btn btn-primary delete" id="${book.bookID}" value="删除">
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script>
    $(function () {
        $(".update").click((e) =>{
            let id = e.target.id;
            window.location.href = "${pageContext.request.contextPath}/book/toUpdate/" + id;
        });
        $(".delete").click((e) =>{
            let id = e.target.id;
            if (confirm("您确定要删除?")){
                window.location.href = "${pageContext.request.contextPath}/book/deleteBook?id=" + id;
            }
        });
        $(".addbook").click(() => {
            window.location.href="${pageContext.request.contextPath}/book/toAddBook"
        });
    });



</script>
</html>
