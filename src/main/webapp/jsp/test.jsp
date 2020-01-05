<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>
<script charset="utf-8" src="${app}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${app}/kindeditor/lang/zh-CN.js"></script>

<script>
    KindEditor.ready(function (K) {
        K.create('#textarea_id', {
            width: '900px',
            height: '500px',
            minHeight: 400,
            resizeType: 0,
            fileManagerJson: '${pageContext.request.contextPath}/test/getAll',
            uploadJson: '${pageContext.request.contextPath}/test/test',
            allowFileManager: true
            // filePostName:'img'
        });
    });
</script>

<textarea id="textarea_id" name="content" style="width:700px;height:300px;">
HTML内容&lt;
</textarea>