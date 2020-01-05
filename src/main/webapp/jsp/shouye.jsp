<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
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

    <script>

    </script>
</head>

<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">持明法洲管理系统 </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎：${sessionScope.loginUser.username}</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">退出登入<span class="glyphicon glyphicon-log-out"
                                                       aria-hidden="true"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!--分行-->
<div class="row">
    <!--左栏开始-->
    <div class="col-sm-2 text-center">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a href="#collapseOne" role="button" data-toggle="collapse" data-parent="#accordion"
                           aria-expanded="true" aria-controls="collapseOne">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <button type="button" role="presentation" class="btn btn-default navbar-btn"
                                style="background-color: red;width: 100%;"><a href="javascript:void(0)"
                                                                              onclick="$('#home').load('${pageContext.request.contextPath}/jsp/banner.jsp')"
                                                                              aria-controls="home" role="tab"
                                                                              data-toggle="tab"><span
                                style="color: white">用户列表</span></a></button>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            上师管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid.
                        3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt
                        laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin
                        coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes
                        anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings
                        occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard
                        of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <button type="button" role="presentation" class="btn btn-default navbar-btn"
                                style="background-color: red;width: 100%;"><a href="javascript:void(0)"
                                                                              onclick="$('#banner').load('${pageContext.request.contextPath}/jsp/article.jsp')"
                                                                              aria-controls="home" role="tab"
                                                                              data-toggle="tab"><span
                                style="color: white">文章列表</span></a></button>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFour">
                    <h4 class="panel-title">
                        <a href="#collapseFour" role="button" data-toggle="collapse" data-parent="#accordion"
                           aria-expanded="true" aria-controls="collapseFour">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <div class="panel-body">
                        <button type="button" role="presentation" class="btn btn-default navbar-btn"
                                style="background-color: red;width: 100%;"><a href="javascript:void(0)"
                                                                              onclick="$('#banner').load('${pageContext.request.contextPath}/jsp/album.jsp')"
                                                                              aria-controls="home" role="tab"
                                                                              data-toggle="tab"><span
                                style="color: white">专辑列表</span></a></button>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFive">
                    <h4 class="panel-title">
                        <a href="#collapseFive" role="button" data-toggle="collapse" data-parent="#accordion"
                           aria-expanded="true" aria-controls="collapseFive">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                    <div class="panel-body">
                        <button type="button" role="presentation" class="btn btn-default navbar-btn"
                                style="background-color: red;width: 100%;"><a href="javascript:void(0)"
                                                                              onclick="$('#banner').load('${pageContext.request.contextPath}/jsp/banner.jsp')"
                                                                              aria-controls="home" role="tab"
                                                                              data-toggle="tab"><span
                                style="color: white">轮播图列表</span></a></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--左栏结束--%>
    <!--中心-->
    <div class="col-sm-9 container" style="margin-top: -40px">
        <div id="banner">
            <br/><br/>
            <!--111111111111-->
            <div class="jumbotron">
                <h2>欢迎来到持明法洲后台管理系统</h2>
            </div>
            <!--22222222222222-->
            <!--轮播图-->
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/img/shouye.jpg" style='width:100%; height:515px'
                             alt="...">
                        <div class="carousel-caption">
                            ...
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/A2.jpg" style='width:100%; height:515px'
                             alt="...">
                        <div class="carousel-caption">
                            ...
                        </div>
                    </div>
                </div>
                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <!--33333333333333-->
            <nav class="navbar navbar-default navbar-fixed-bottom">
                <div class="container text-center">
                    <h4><b>@百知教育 baizhi@zparkhr.com.cn</b></h4>
                </div>
            </nav>
        </div>
    </div>
</div>


</body>

</html>