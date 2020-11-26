 $(function() {
	//각 버튼이 눌렸을 때 DB에서 해당 별자리에 관한 텍스트를 출력
	
	$(".starbtn").on("click",function(){
		var title = $(this).val();
		
		var ti =$("#rTitle").val(title);		
		console.log(title);
		console.log(ti);
		$.ajax({
			type:"post",
			url:"starAjax",
			dataType: "json",
			data: ti,
			success:function(data){
				
				$("#readContent").empty();
				$("#readContent").append("<p>" + data.title + "</p>");
				$("#readContent").append("<p>" + data.content + "</p>");
				$("#readContent").append("<p> " + data.item + "</p>");
				
			},
			error:function(request, status, error){
				
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
	
	
});