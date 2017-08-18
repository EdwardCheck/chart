var type;
var basedata;
function selectchart(value){
	
	if(value == 0){
		type = 'bar';
		sessionStorage.setItem('type',type);
	}
	if(value == 1){
		type = 'line';
		sessionStorage.setItem('type',type);
	}
	if(value == 2){
		type = 'pie';
		sessionStorage.setItem('type',type);
	}
	if(value == 3){
		type = 'treemap';
		sessionStorage.setItem('type',type);
	}
};

nextstep = function(){
	
	window.location.href='index2.html';
	
}

//$().ready(function(){
//	$("#ul").on("click","li",function(){   
//		//只需要找到你点击的是哪个ul里面的就行
//		 $(this).find(.title).css({'background':'#92cddc'});
//		
//	});
//});

function select(num){
	 
    $('#box'+this.boxnum).css('border-color','#e7e7e7');
	for(var i=0;i<4;i++){
       if(num == i){
            $('#box'+num).css('border-color','#2196f3');
            this.boxnum = num;
            break;
       }
}
}


