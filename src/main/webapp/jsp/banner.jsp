<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/1 0001
  Time: 下午 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>


<%--<link rel="stylesheet" href="${app}/boot/css/bootstrap.min.css">--%>
<%--&lt;%&ndash;引入jqgrid中主题css&ndash;%&gt;--%>
<%--<link rel="stylesheet" href="${app}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">--%>
<%--&lt;%&ndash;<link rel="stylesheet" href="${app}/jqgrid/css/jquery-ui.css">&ndash;%&gt;--%>
<%--&lt;%&ndash;引入js文件&ndash;%&gt;--%>
<%--&lt;%&ndash;<script src="${app}/boot/js/jquery-2.2.1.min.js"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script src="${app}/boot/js/bootstrap.min.js"></script>&ndash;%&gt;--%>
<%--<script src="${app}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>--%>
<%--<script src="${app}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>--%>

<%--引入jquery.js--%>
<%--<script src="${app}/boot/js/jquery-2.2.1.min.js"></script>--%>
<%--引入bootstrap核心js--%>
<%--<script src="${app}/boot/js/bootstrap.min.js"></script>--%>


<%--&lt;%&ndash;引入jq主题样式&ndash;%&gt;--%>
<%--<link rel="stylesheet" href="${path}/newJq/jqgrid/css/css/cupertino/jquery-ui-1.8.16.custom.css">--%>
<%--&lt;%&ndash;引入jq核心css&ndash;%&gt;--%>
<%--<link rel="stylesheet" href="${path}/newJq/jqgrid/css/ui.jqgrid.css" >--%>


<script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
<script>
    $(function () {
        $("#t").jqGrid({
            url: "${app}/banner/queryByPaging",
            editurl: "${app}/banner/editBanner",
            datatype: "json",
            styleUI: "Bootstrap",
            autowidth: true,
            height: 380,
            pager: "#pager",
            rowNum: 3,
            rowList: [3, 10, 15],
            // toolbar: [true, 'both'],
            multiselect: true,
            // caption: "我的表格",
            viewrecords: true,
            // rowNum:5,
            colNames: ["id", "title", "img", "create_date", "status"],
            colModel: [
                {name: "id", align: "center"},
                {name: "title", editable: true},
                {
                    name: "img", editable: true, edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='height:100px;width:100px' src='${pageContext.request.contextPath}/img/" + cellvalue + "'/>"
                    }
                },
                {name: "create_date"},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {value: "展示:展示;不展示:不展示"}
                },

            ]
        }).jqGrid("navGrid", "#pager",
            {search: false},
            {//修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    alert("修改")

                    // var bannerId = response.responseJSON.bannerId;
                    // var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "img",
                        type: "post",
                        // data:{bannerId:bannerId},
                        success: function (data) {
                            $("#span").empty();
                            $("#span").html("修改成功").show();
                            $("#t").trigger('reloadGrid');
                            setTimeout(function () {
                                $("#span").hide();
                            }, 4000)
                        }
                    })

                    return response;
                }
            },
            {//添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    alert("添加")
                    var bannerId = response.responseJSON.bannerId;
                    // var bannerId = response.responseText;
                    alert(bannerId)
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "img",
                        type: "post",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#span").html("添加成功").show();
                            $("#t").trigger('reloadGrid');
                            setTimeout(function () {
                                $("#span").hide();
                            }, 4000)

                        }
                    })
                    return response;
                }
            },
            {//删除

            }
        );
    })
</script>
<body>
<%--<div class="page-header">--%>
<%--<h3>用户管理</h3>--%>
<%--</div>--%>
<%--<!--标签页头部-->--%>
<%--<ul class="nav nav-tabs" role="tablist">--%>
<%--<li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">用户列表</a></li>--%>
<%--<li id="regist"><a href="#">用户添加</a></li>--%>
<%--</ul>--%>
<div style="margin-top: 40px">
    <div class="page-header">
        <h1>轮播图管理</h1>
    </div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">轮播图信息</a></li>
    </ul>
    <table id="t"></table>
    <span id="span" style="color: red" hidden></span>
    <div id="pager" style='height:50px'></div>
</div>
</body>
