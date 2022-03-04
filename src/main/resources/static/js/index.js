$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");

	// 获取标题和内容
	var title = $("#recipient-name").val();
	var content = $("#message-text").val();
	// jQuery发送异步请求(POST)
	// $(selector).post(URL,data,function(data,status,xhr),dataType)
	// url (String) : 发送请求的URL地址.
	// data (Map) : (可选) 要发送给服务器的数据，以 Key/value 的键值对形式表示。
	// function(data,status,xhr) : 可选。规定当请求成功时运行的函数 data - 包含来自请求的结果数据
	// type (String) : (可选) 规定预期的服务器响应的数据类型。默认地，jQuery 会智能判断。
	$.post(
		CONTEXT_PATH + "/discuss/add",
		{"title":title,"content":content},
		function(data) {
			// 将符合标准格式的的JSON字符串转为与之对应的JavaScript对象。
			data = $.parseJSON(data);
			// 在提示框中显示返回消息
			$("#hintBody").text(data.msg);
			// 显示提示框
			$("#hintModal").modal("show");
			// 2秒后,自动隐藏提示框
			setTimeout(function(){
				$("#hintModal").modal("hide");
				// 刷新页面
				if(data.code === 0) {
					window.location.reload();
				}
			}, 2000);
		}
	);

}