<%--
  Created by IntelliJ IDEA.
  User: x
  Date: 2020/3/31
  Time: 4:08 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}

<form action="/save" method="post"/>
    <input type="text" name="id"/><br>
    <input type="text" name="name"/><br>
    <input type="text" name="address.name"/>
    <input type="submit" value="注册"/>
</form>

${user}

${msg1}

</body>
</html>
