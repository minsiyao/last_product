<%@page pageEncoding="UTF-8" contentType="text/html;UTF-8" isELIgnored="false" %>

<script>
    var editor;
    var editor1;

    //数据回显
    function echo(id) {
        $.post(
            "${pageContext.request.contextPath}/Article/queryById?id=" + id,
            function (result) {
                $("#update_id").val(result[0].id)
                $("#input3").attr("value", result[0].title)
                if (result[0].status == "展示") {
                    $("#input4").val("展示")
                } else {
                    $("#input4").val("不展示")
                }
                $("#input5").val(result[0].guru_id)
                editor.html(result[0].content)
                $("#modal_title").html("修改文章信息")
                $("#modalShow_1").hide()
                $("#modalShow_2").hide()
                $("#modalShow_3").hide()
                $("#modalShow_4").hide()
                $("#modalStatus").show()
                // $("#button_update").show()
                $("#input5").removeAttr("readOnly")
                $("#input3").removeAttr("readOnly")
                editor.readonly(false);

            }, "json"
        )
        //显示修改模态框
        $('#myModal').modal({
            show: true,
            keyboard: false,
            backdrop: false
        })
    }

    //修改数据
    function update() {
        editor.sync();
        $.post(
            "${pageContext.request.contextPath}/Article/updateById",
            $("#update").serialize(),
            function (result) {

            }, "json"
        )
    }

    //展示显示数据模态框
    function showArticle(id) {
        $.post(
            "${pageContext.request.contextPath}/Article/queryById?id=" + id,
            function (result) {
                $("#update_id").val(result[0].id)
                $("#input3").attr("value", result[0].title)
                if (result[0].status == "展示") {
                    $("#input4").val("展示")
                } else {
                    $("#input4").val("不展示")
                }
                editor.html(result[0].content)
                $("#modal_title").html("展示文章信息")
                $("#modalShow_1").show()
                $("#modalShow_2").show()
                $("#modalShow_3").show()
                $("#modalShow_4").show()
                $("#modalStatus").hide()
                // $("#button_update").hide()
                $("#input5").attr("readonly", "readonly")
                $("#input3").attr("readonly", "readonly")
                $("#modalShow_guru_id").val(result[0].guru_id)
                $("#modalShow_author").val(result[0].author)
                $("#modalShow_create_date").val(result[0].create_date)
                $("#modalShow_publish_date").val(result[0].publish_date)
                $("#modalShow_status").val(result[0].status)
                editor.readonly();
            }, "json"
        )
        //显示展示数据模态框
        $('#myModal').modal({
            show: true,
            keyboard: false,
            backdrop: false
        })

    }

    //展示添加模态框
    function showModal2() {
        $("#myModal2").modal({
            show: true,
            keyboard: false,
            backdrop: false
        })
    }

    //添加数据
    function add() {
        editor1.sync();
        $.post(
            "${pageContext.request.contextPath}/Article/insert",
            $("#insert").serialize(),
            function (result) {

            }, "json"
        )
        $('#insert')[0].reset();
        editor1.html("");
    }

    //初始化加载函数
    $(function () {
        //创建KindEditor
        editor1 = KindEditor.create("textarea[id='textarea_id2']", {
            // width:'900px',
            // height:'500px',
            // minHeight:400,
            resizeType: 0,
            fileManagerJson: '${pageContext.request.contextPath}/test/getAll',
            uploadJson: '${pageContext.request.contextPath}/test/test',
            allowFileManager: true
            // filePostName:'img'
        });
        editor = KindEditor.create("textarea[id='textarea_id']", {
            // width:'900px',
            // height:'500px',
            // minHeight:400,
            resizeType: 0,
            fileManagerJson: '${pageContext.request.contextPath}/test/getAll',
            uploadJson: '${pageContext.request.contextPath}/test/test',
            allowFileManager: true
            // filePostName:'img'
        });
        //------------jqGrid----------------
        $("#articleTable").jqGrid({
            url: "${pageContext.request.contextPath}/Article/queryByPaging",
            editurl: "${pageContext.request.contextPath}/Article/editArticle",
            datatype: "json",
            styleUI: "Bootstrap",
            autowidth: true,
            records: true,
            rowNum: 3,
            rowList: [3, 6, 9],
            height: "45%",
            caption: "文章",
            multiselect: true,
            pager: "#articlePaging",
            viewrecords: true,
            rownumbers: true,
            colNames: [
                "上师id", "标题", "状态", "上传时间", "发布时间", "操作"
            ],
            colModel: [
                {name: "guru_id"},
                {name: "title"},
                {name: "status"},
                {name: "create_date"},
                {name: "publish_date"},
                {
                    name: "tup", formatter: function (cellvalue, options, rowObject) {
                        return "<span onclick='showArticle(\"" + rowObject.id + "\")' class='glyphicon glyphicon-th-list ' style='color: #3baae3' aria-hidden='true'></span>" + "        " + "<span class='glyphicon glyphicon-pencil' style='color: #3baae3' aria-hidden='true' onclick='echo(\"" + rowObject.id + "\")'></span>";
                    }
                }
            ]


        }).jqGrid("navGrid", "#articlePaging", {})
    })
</script>


<div style="margin-top: 40px">
    <div class="page-header">
        <h1>文章管理</h1>
    </div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">文章信息</a></li>
        <li role="presentation"><a onclick="showModal2()" aria-controls="home" role="tab" data-toggle="tab">添加信息</a>
        </li>
    </ul>
    <table id="articleTable"></table>
    <div id="articlePaging" style='height:50px'></div>
</div>
<%-------------------------模态框------修改----------------------------%>
<div class="modal fade" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="modal_title">修改文章信息</h4>
            </div>
            <div class="modal-body">

                <%--表单--%>
                <form id="update" class="form-horizontal">
                    <div class="form-group">
                        <input id="update_id" name="id" type="text" hidden>
                    </div>


                    <div class="form-group" id="modalShow_1">
                        <label for="input3" class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-6">
                            <input id="modalShow_author" class="form-control" type="text"
                                   placeholder="Readonly input here…" readonly>
                        </div>
                    </div>
                    <div class="form-group" id="modalShow_2">
                        <label for="input3" class="col-sm-2 control-label">创建时间</label>
                        <div class="col-sm-6">
                            <input id="modalShow_create_date" class="form-control" type="text"
                                   placeholder="Readonly input here…" readonly>
                        </div>
                    </div>
                    <div class="form-group" id="modalShow_3">
                        <label for="input3" class="col-sm-2 control-label">出版时间</label>
                        <div class="col-sm-6">
                            <input id="modalShow_publish_date" class="form-control" type="text"
                                   placeholder="Readonly input here…" readonly>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="input5" class="col-sm-2 control-label">上师id</label>
                        <div class="col-sm-6">
                            <input id="input5" name="guru_id" class="form-control" type="text"
                                   placeholder="Readonly input here…" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="input3" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-6">
                            <input name="title" class="form-control" id="input3" type="text"
                                   placeholder="Readonly input here…" readonly>
                        </div>
                    </div>
                    <div class="form-group" id="modalShow_4">
                        <label for="input4" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-6">
                            <input id="modalShow_status" class="form-control" type="text"
                                   placeholder="Readonly input here…" readonly>

                        </div>
                    </div>
                    <div class="form-group" id="modalStatus">
                        <label for="input4" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-6">
                            <select name="status" class="form-control input-lg" id="input4"
                                    placeholder="Readonly input here…" readonly>
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>
                    </div>
                    <%--文本域--%>
                    <div class="controls">

                        <textarea id="textarea_id" name="content" style="width: 90%; height: 300px;">  </textarea>

                    </div>
                    <%--文本域结束--%>
                </form>
                <%--form表单结束--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="button_update" type="button" class="btn btn-primary" data-dismiss="modal"
                        onclick="update()">保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<%-------------------------模态框------添加----------------------------%>
<div class="modal fade" role="dialog" id="myModal2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加文章</h4>
            </div>
            <div class="modal-body">

                <%--表单--%>
                <form id="insert" class="form-horizontal">
                    <div class="form-group">
                        <input id="update_id2" name="id" type="text" hidden>
                    </div>
                    <div class="form-group">
                        <label for="input32" class="col-sm-2 control-label">上师id</label>
                        <div class="col-sm-6">
                            <input name="guru_id" class="form-control" type="text" placeholder="请输入上师id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="input32" class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-6">
                            <input name="author" class="form-control" type="text" placeholder="请输入作者名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="input32" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-6">
                            <input name="title" class="form-control" id="input32" type="text" placeholder="请输入标题名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="input42" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-6">
                            <select name="status" class="form-control input-lg" id="input42"
                                    placeholder="Readonly input here…" readonly>
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>
                    </div>
                    <%--文本域--%>
                    <div class="controls">

                        <textarea id="textarea_id2" name="content" style="width: 90%; height: 300px;">  </textarea>

                    </div>
                    <%--文本域结束--%>
                </form>
                <%--form表单结束--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="add()">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->