<%--
  Created by IntelliJ IDEA.
  User: x
  Date: 2020/4/1
  Time: 9:52 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script type="text/javascript" src="/static/js/jquery-3.4.1.js"></script>
  <script>
    $(function (
    ) {
      let user = {
        "id":10,
        "name":"张三",
        "address":{"name":"中国"}};

      $.ajax({
        url:"/json",
        data:JSON.stringify(user),
        type:"POST",
        contentType:"application/json;charset=UTF-8",
        dataType:"JSON",
        success:function(data){
          alert(data.id+"---"+data.name); }
      })



    });
  </script>
</head>
<body>

</body>
</html>
