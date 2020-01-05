<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script>
        $.post(
            "${pageContext.request.contextPath}/Poi/showAllBanner",
            function (result) {
                $.each(result, function (index, val) {
                    $("#myDiv").append(val.id + "--" + val.title + "--" + val.text + "<br/>")
                })
            }, "json"
        )
    </script>


</head>
<body>
<div id="myDiv"></div>
<a href="${pageContext.request.contextPath}/Poi/load">
    <button>点击导出</button>
</a>
<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/Poi/upload" method="post">
    <div style="border:1px solid rebeccapurple"></div>
    <input type="file" name="myFile">
    <input type="submit" value="确定">
</form>
</body>
</html>