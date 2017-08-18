//柱状图,折线图模板
var option_bar_or_line = {
	backgroundColor : '',// 背景色
	title : {
		text : 'Report Demo', // 标题
		textStyle : {
			fontWeight : 'normal', // 标题颜色
			color : '#353535',
			fontSize : 18
		}
	},
	tooltip : {
		trigger : 'axis'
	},
	toolbox : {

	},
	legend : {
		data : [],
		orient : 'vertical',
		left : 'right'
	},
	xAxis : [ {
		type : 'category',
		name : 'Time',
		data : [ '6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00',
				'13:00', '14:00', '15:00', '16:00', '17:00' ],
		axisPointer : {
			type : 'shadow'
		},
		axisLabel : {
			rotate : 45
		// 刻度旋转45度角
		}
	} ],
	yAxis : [ {
		type : 'value',
		name : 'Number',
		axisLabel : {
			formatter : '{value}'
		}
	} ],
	series : []
};

//饼图模板
var option_pie = {
	backgroundColor : '',// 背景色
	title : {
		text : 'Report Demo', // 标题
		textStyle : {
			fontWeight : 'bolder', // 标题颜色'normal' 'bold' 'bolder' 'lighter'
			color : '#353535',
			fontSize : 18
		}
	},
	tooltip : {
		trigger : 'item',
		formatter : "{b} : {c} ({d}%)"
	},
	toolbox : {

	},
	legend : {
		data : [],
		orient : 'vertical',
		left : 'right'
	},
	series : [ {
		name : '',
		type : 'pie',
		radius : '55%',
		center : [ '50%', '60%' ],
		data : [],
		itemStyle : {
			emphasis : {
				shadowBlur : 10,
				shadowOffsetX : 0,
				shadowColor : 'rgba(0, 0, 0, 0.5)'
			}
		}
	} ]
};

//矩形树图模板
var option_treemap = {
	title : {
		text : 'Report Demo', // 标题
		textStyle : {
			fontWeight : 'normal', // 标题颜色
			color : '#353535',
			fontSize : 18
		}
	},
	tooltip : {
		trigger : 'item',
		formatter : "{b}: {c}"
	},
	toolbox : {

	},
	calculable : false,
	legend : {
		data : [],
		orient : 'vertical',
		left : 'right'
	},
	series : [ {
		name : 'Rectangle',
		type : 'treemap',
		itemStyle : {
			normal : {
				label : {
					show : true,
					formatter : "{b}"
				},
				borderWidth : 1
			},
			emphasis : {
				label : {
					show : true
				}
			}
		},
		data : []
	} ]
};
