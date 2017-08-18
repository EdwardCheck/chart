
setParam = function(key, value) {
	return '"' + key + '":"' + value + '"';
};

function callAction(PostAction, sParam, func, p1, p2, p3, p4, p5, p6, p7,
		p8) {

	var sPost = '{';

	if (sParam) {
		sPost += sParam;
	}
	sPost += '}';

	try {
		$.ajax({
			url : PostAction,
			dataType:'json',
			data : {
				a : sPost
			},
			success : function(data) {
				var a = data;
				if (func) {
					func(a, p1, p2, p3, p4, p5, p6, p7, p8);
				}
			},
			error : function() {
				alert("ajax error");
			}
		});
	} catch (e) {
		alert('Front-end error');
	}
};
