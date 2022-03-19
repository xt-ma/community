$(function () {
    $("#uploadForm").submit(upload);
});

function upload() {
    $.ajax({
        url: "http://upload.qiniup.com",
        method: "post",
        processData: false,// 不要转为字符串
        contentType: false,// 不设置上传的类型
        data: new FormData($("#uploadForm")[0]),
        success: function (data) {
            if (data && data.code == 0) {
                // 更新头像访问路径
                $.post(
                    CONTEXT_PATH + "/user/header/url",
                    {"fileName": $("input[name='key']").val()},
                    function (data) {
                        data = $.parseJSON(data);
                        if (data.code == 0) {
                            window.location.reload();
                        } else {
                            alert(data.msg);
                        }
                    }
                );
            } else {
                alert("上传失败!");
            }
        }
    });
    return false;
}