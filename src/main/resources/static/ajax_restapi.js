'use strict';
$(document).ready(function(){

	$("#getZipCd").click(function(){
		var zipcd = document.getElementById("zipcd").value ;
		jQuery.ajax({
			type: 'GET',
			url: 'http://zipcloud.ibsnet.co.jp/api/search',
			data:{
				zipcode:zipcd
			},
			dataType: 'jsonp',
			jsonp: 'callback',            //コールバックパラメータ名の指定
	 		jsonpCallback: 'testCallback',//callback関数名を自分で指名した場合
			cache: false
		})
		.done(function(data){
			$("#result").empty();
			if (data.status == "200"){
				$.each(data,function(i, item){
				    console.log(item);
				});
				$("#result").append(data.results[0].address2+data.results[0].address3);
			}else{
				$("#result").append(data.message);
			}
			switch  (data.status ){
				case "200":
					break;
				case "400":
					break;
				default:
			}
		})
		.fail(function(){
			 $("#result").append("エラーです");
		});
	});
});
