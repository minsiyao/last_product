<%@page pageEncoding="UTF-8" contentType="text/html;UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/css/form-elements.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/css/">--%>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/login/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="${pageContext.request.contextPath}/login/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="${pageContext.request.contextPath}/login/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="${pageContext.request.contextPath}/login/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed"
          href="${pageContext.request.contextPath}/login/assets/ico/apple-touch-icon-57-precomposed.png">
    <%--引入jqGrid主题样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/css/trirand/ui.jqgrid-bootstrap-ui.css">
    <%--引入jqGrid核心css--%>
    <link rel="strlesheet" href="${pageContext.request.contextPath}/jqgrid/css/ui.multiselect.css">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/login/assets/js/jquery.backstretch.min.js"></script>
    <%--<script src="${pageContext.request.contextPath}/login/assets/js/scripts.js"></script>--%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--<link rel="stylesheet" href="${app}/jqgrid/css/jquery-ui.css">--%>
    <%--引入js文件--%>
    <%--<script src="${app}/boot/js/jquery-2.2.1.min.js"></script>--%>
    <%--<script src="${app}/boot/js/bootstrap.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/china.js"></script>
    <%--引入goeasy--%>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    var goEasy = new GoEasy({
        host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-999c3af17d1643b5a60a78fd1c46825d", //替换为您的应用appkey
    });
    goEasy.subscribe({
        channel: "test3", //替换为您自己的channel
        onMessage: function (message) {
            alert("进来了")
            console.log(message.content)
            myChart.setOption({
                series: [{
                    data: JSON.parse(message.content)
                }]
            })
        }
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'cmfz用户地理分布图',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['人数']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '人数',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                }
            }
        ]
    };
    $.post(
        "${pageContext.request.contextPath}/User/queryCountByAddress",
        function (result) {
            console.log(result)
            myChart.setOption({
                series: [{
                    data: result
                }]
            })
        }, "json"
    )

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>