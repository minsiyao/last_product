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
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script>

        $(function () {
            //创建kindeditor
            editor = KindEditor.create("textarea[id='textarea_id2']", {
                // width:'900px',
                // height:'500px',
                // minHeight:400,
                resizeType: 0,
                fileManagerJson: '${pageContext.request.contextPath}/test/getAll',
                uploadJson: '${pageContext.request.contextPath}/test/test',
                allowFileManager: true
                // filePostName:'img'
            });
        })
        //创建goeasy对象
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-999c3af17d1643b5a60a78fd1c46825d", //替换为您的应用appkey
        });
        //接受聊天信息
        goEasy.subscribe({
            channel: "chat1", //替换为您自己的channel
            onMessage: function (message) {
                $("#1").append($("<span style='margin: 50px;color: blue'>" + message.content + "</span><br/>"))
            }
        });
        <%--发送聊天信息--%>

        function sendChatMessages() {
            alert("开始发送信息")
            //发送聊天信息
            goEasy.publish({
                channel: "chat2", //替换为您自己的channel
                message: editor.html() //替换为您想要发送的消息内容
            });
            $("#1").append($("<span style='float:right;color: red'>" + editor.html() + "</span><br/>"))
            editor.html("")
        }
    </script>
</head>
<body>
<h1>聊天框1</h1>
<div>
    <div style="border: 1px #9A9A9A solid;width: 700px;height: 300px;overflow-y:auto" id="1">
    </div>
    <textarea id="textarea_id2" name="content" style="width: 700px; height: 300px;">  </textarea>
    <input type="button" value="发送" onclick="sendChatMessages()">
</div>

</body>
</html>