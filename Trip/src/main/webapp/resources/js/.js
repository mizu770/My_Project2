 $(function() {
	//각 버튼이 눌렸을 때 DB에서 해당 별자리에 관한 텍스트를 출력
	
	$(".btn1").on("click",function(){
		var title = $(this).val();
		
		var ti =$("#rTitle").val(title);
		$.ajax({
			type:"post",
			url:"starAjax.star",
			data: ti,
			dataType : "json",
			success:function(readStar){
				$("#readContent").empty();
				$("#readContent").append("<p>"+readStar.title+"</p>");
				$("#readContent").append("<p>"+readStar.content+"</p>");
				$("#readContent").append("<p>"+readStar.item+"</p>");
				
				
			},
			error:function(e){
				alert("다시 해");
				}
		});
	});
	
	
});