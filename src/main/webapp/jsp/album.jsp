<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html;UTF-8" isELIgnored="false" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

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


<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${app}/Album/queryByPaging",
            editurl: "${app}/Album/editAlbum",
            datatype: "json",
            styleUI: "Bootstrap",
            autowidth: true,
            records: true,
            rowNum: 3,
            rowList: [3, 6, 9],
            height: "45%",
            caption: "专辑",
            multiselect: true,
            pager: "#albumPager",
            viewrecords: true,
            colNames: [
                "id", "title", "score", "author",
                "broadcaster", "count", "brief", "create_date", "img"
            ],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "score"},
                {name: "author", editable: true},
                {name: "broadcaster", editable: true},
                {name: "count"},
                {name: "brief", editable: true},
                {name: "create_date"},
                {
                    name: "img", edittype: "file", editable: true, formatter: function (cellvalue, options, rowObject) {
                        return "<img style='height:100px;width:100px' src='${pageContext.request.contextPath}/" + cellvalue + "'/>"
                    }
                }
            ],
            subGrid: true,//开启子表格
            subGridRowExpanded: function (subGridId, albumId) {
                addSubGrid(subGridId, albumId);
            }
        }).jqGrid("navGrid", "#albumPager",
            {search: false},
            {//修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    alert("修改")
                    var id = response.responseJSON.id;
                    if (id != null) {
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/Album/upload",
                            fileElementId: "img",
                            type: "post",
                            data: {id: id},
                            success: function (data) {
                                $("#albumList").trigger('reloadGrid');
                                $("#span1").empty();
                                $("#span1").html("修改成功").show();
                                $("#t").trigger('reloadGrid');
                                setTimeout(function () {
                                    $("#span1").hide();
                                }, 4000)
                            }
                        })
                    }

                    return response;
                }
            },
            {//添加

                closeAfterAdd: true,
                afterSubmit: function (response) {
                    alert("添加")
                    var id = response.responseJSON.id;
                    // var bannerId = response.responseText;
                    alert(id)
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/Album/upload",
                        fileElementId: "img",
                        type: "post",
                        data: {id: id},
                        success: function (data) {
                            $("#albumList").trigger('reloadGrid');
                            $("#span1").html("添加成功").show();
                            $("#t").trigger('reloadGrid');
                            setTimeout(function () {
                                $("#span1").hide();
                            }, 4000)

                        }
                    })
                    return response;
                }
            },
            {//删除

            }
        )
    })

    //添加子表格
    function addSubGrid(subGridId, albumId) {
        //动态table  id
        var subGridTableId = subGridId + "table";
        //动态div  id
        var subGridDivId = subGridId + "div";
        //动态添加子表格
        $("#" + subGridId).html("<table id='" + subGridTableId + "'></table>" +
            "<div id='" + subGridDivId + "' style='height:50px'></div>"
        )

        $("#" + subGridTableId).jqGrid({
            url: "${app}/Chapter/queryByAlbumIdPaging?album_id=" + albumId,
            editurl: "${app}/Chapter/editChapter?album_id=" + albumId,
            datatype: "json",
            styleUI: "Bootstrap",
            autowidth: true,
            records: true,
            rowNum: 3,
            rowList: [3, 6, 9],
            caption: "章节",
            toolbar: [true, "top"],
            multiselect: true,
            pager: "#" + subGridDivId,
            viewrecords: true,
            colNames: [
                "id", "album_id", "title", "size",
                "duration", "audio"
            ],
            colModel: [
                {name: "id"},
                {name: "album_id"},
                {name: "title", editable: true},
                {name: "size"},
                {name: "duration", edittype: "date"},
                {name: "audio", align: 'center', width: 300, editable: true, edittype: 'file'}
            ]
        }).jqGrid("navGrid", "#" + subGridDivId,
            {search: false},
            {//修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    alert("修改")
                    var audio = response.responseJSON.audio;
                    alert(audio != null)
                    alert(audio != "")
                    if (audio != null && audio != "") {
                        var id = response.responseJSON.id;
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/Chapter/upload",
                            fileElementId: "audio",
                            type: "post",
                            data: {id: id},
                            success: function (data) {
                                // $("#span").empty();
                                // $("#span").html("修改成功").show();
                                $("#albumList").trigger('reloadGrid');
                                // setTimeout(function () {
                                //     $("#span").hide();
                                // }, 4000)
                            }
                        })
                    }
                    return response;
                }
            },
            {//添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var id = response.responseJSON.id;
                    // var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/Chapter/upload",
                        fileElementId: "audio",
                        type: "post",
                        data: {id: id},
                        success: function (data) {
                            // $("#span").html("添加成功").show();
                            $("#albumList").trigger('reloadGrid');
                            // setTimeout(function () {
                            //     $("#span").hide();
                            // }, 4000)

                        }
                    })
                    return response;
                }
            },
            {//删除

            }
        )

        //    添加按钮
        $("#t_" + subGridTableId).html("<button class='btn btn-danger' onclick=\"play('" + subGridTableId + "')\">播放<span class='glyphicon glyphicon-play'></span></button>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<button class='btn btn-danger' onclick=\"download('" + subGridTableId + "')\">下载<span class='glyphicon glyphicon-arrow-down'></span></button>"
        )
    }

    // 播放
    function play(subGridTableId) {
        //选中行的id   没有被选中时  为null
        var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow')
        if (gr == null) {
            alert("选中需要播放的音频")
        } else {
            //展示播放器模态框
            $('#myModal').modal({
                show: true,
                keyboard: false,
                backdrop: false
            })
            //    1.请求后台
            //    2.jqgrid的方法  根据id拿到对应的值
            //选中行的数据
            var data = $("#" + subGridTableId).jqGrid('getRowData', gr)
            var path = "${pageContext.request.contextPath}/audio/" + data["audio"];
            if ($("#audio1").attr("src") != path) {
                $("#audio1").attr("src", path)
            }

        }
    }

    //下载
    function download(subGridTableId) {
        //选中行的id    没有被选中时  为null
        var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow')
        if (gr == null) {
            alert("选中需要下载的音频")
        } else {
            //    1.请求后台
            //    2.jqgrid的方法   根据id拿到对应的值
            //选中行的数据
            alert("准备开始下载文件")

            var data = $("#" + subGridTableId).jqGrid('getRowData', gr)
            var path = "${pageContext.request.contextPath}/audio/" + data["audio"];
            window.location.href = "${app}/Chapter/download?fileName=" + data["audio"];
            <%--$.ajax({--%>
            <%--type: 'POST',--%>
            <%--url: "${app}/Chapter/download",--%>
            <%--data: {fileName:data["audio"]},--%>
            <%--success: function (result) {--%>
            <%----%>
            <%--},--%>
            <%--dataType: "html"--%>
            <%--});--%>
        }

    }

</script>
<div style="margin-top: 40px">
    <div class="page-header">
        <h1>专辑管理</h1>
    </div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">专辑信息</a></li>
    </ul>
    <table id="albumList"></table>
    <div id="albumPager" style='height:50px'></div>
    <span id="span1"></span>
</div>


<div class="modal fade" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="modal_title">音乐播放器</h4>
            </div>
            <div class="modal-body">
                <audio id="audio1" src="" controls="controls">
                </audio>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->


