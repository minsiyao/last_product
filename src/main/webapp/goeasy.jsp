<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script>
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-999c3af17d1643b5a60a78fd1c46825d", //替换为您的应用appkey
        });
        goEasy.subscribe({
            channel: "test1", //替换为您自己的channel
            onMessage: function (message) {
                alert("Channel:" + message.channel + " content:" + message.content);
            }
        });
        // goEasy.publish({
        //     channel: "test1", //替换为您自己的channel
        //     message: "Hello, GoEasy!" //替换为您想要发送的消息内容
        // });


    </script>

</head>
<body>

</body>
</html>