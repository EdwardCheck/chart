var basedata;
var option;
var chart = document.getElementById('chart');
var myChart = echarts.init(chart);
var typevalue = sessionStorage.getItem('type');
var INDEX = [ '通话量', '业务完整数量', '座席未按流程操作数量', '客户出现投诉数量' ];
var DEPARTMENT = [ '部门1', '部门2', '部门3', '部门4' ];
var USER = [ '用户1', '用户2', '用户3', '用户4' ];
var DATASOURCE;

var ONE_DAY = [ '1:00', '2:00', '3:00', '4:00', '5:00', '6:00', '7:00', '8:00',
		'9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00',
		'17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00', '24:00' ];
var ONE_HOUR = [ '6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00',
		'13:00', '14:00', '15:00', '16:00', '17:00' ];

var ONE_WEEK = [ 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',
		'Saturday', 'Sunday' ];

var data = [ [ 3, 5, 7, 8, 10, 10, 12, 5, 7, 4, 6, 3 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1 ],
		[ 1, 0, 1, 1, 0, 0, 2, 1, 0, 1, 1, 1 ],
		[ 1, 5, 1, 1, 0, 0, 2, 1, 0, 2, 3, 1 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1 ] ];

var data_hour = [];

var data_day = [
		[ 3, 5, 7, 8, 10, 10, 12, 5, 7, 4, 6, 3, 3, 5, 7, 8, 10, 10, 12, 5, 7,
				4, 6, 3 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1, 3, 5, 7, 8, 10, 10, 12, 5, 7, 4,
				6, 3 ],
		[ 1, 0, 1, 1, 0, 0, 2, 1, 0, 1, 1, 1, 3, 5, 7, 8, 10, 10, 12, 5, 7, 4,
				6, 3 ],
		[ 1, 5, 1, 1, 0, 0, 2, 1, 0, 2, 3, 1, 3, 5, 7, 8, 10, 10, 12, 5, 7, 4,
				6, 3 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1, 3, 5, 7, 8, 10, 10, 12, 5, 7, 4,
				6, 3 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1, 3, 5, 7, 8, 10, 10, 12, 5, 7, 4,
				6, 3 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1, 3, 5, 7, 8, 10, 10, 12, 5, 7, 4,
				6, 3 ],
		[ 1, 0, 5, 6, 10, 6, 8, 4, 5, 0, 3, 1, 3, 5, 7, 8, 10, 10, 12, 5, 7, 4,
				6, 3 ] ];

var data_week = [ [ 3, 5, 7, 8, 10, 10, 12 ], [ 1, 0, 5, 6, 10, 6, 8 ],
		[ 1, 0, 1, 1, 0, 0, 2 ], [ 1, 5, 1, 1, 0, 0, 2 ],
		[ 1, 0, 5, 6, 10, 6, 8 ], [ 1, 0, 5, 6, 10, 6, 8 ],
		[ 1, 0, 5, 6, 10, 6, 8 ], [ 1, 0, 5, 6, 10, 6, 8 ] ];

$(document)
		.ready(
				function() {
					callAction('chart/basedata.action', '', getDataSuccess);

					var _createtime_val = $("#select_date1").val();
					var week_option = "<option>Monday</option>"
							+ "<option>Tuesday</option>"
							+ "<option>Wednesday</option>"
							+ "<option>Thursday</option>"
							+ "<option>Friday</option>"
							+ "<option>Saturday</option>"
							+ "<option>Sunday</option>";
					var month_option = "<option>1</option>"
							+ "<option>2</option>" + "<option>3</option>"
							+ "<option>4</option>" + "<option>5</option>"
							+ "<option>6</option>" + "<option>7</option>"
							+ "<option>8</option>" + "<option>9</option>"
							+ "<option>10</option>" + "<option>11</option>"
							+ "<option>12</option>" + "<option>13</option>"
							+ "<option>14</option>" + "<option>15</option>"
							+ "<option>16</option>" + "<option>17</option>"
							+ "<option>18</option>" + "<option>19</option>"
							+ "<option>20</option>" + "<option>21</option>"
							+ "<option>22</option>" + "<option>23</option>"
							+ "<option>24</option>" + "<option>25</option>"
							+ "<option>26</option>" + "<option>27</option>"
							+ "<option>28</option>" + "<option>29</option>"
							+ "<option>30</option>" + "<option>31</option>";
					if (_createtime_val == "Daily") {
						$("#select_date2").hide();
					} else if (_createtime_val == "Weekly") {
						$("#select_date2").empty();
						$("#select_date2").append(week_option);
						// $("#select_date2").show();
					} else if (_createtime_val == "Monthly") {
						$("#select_date2").empty();
						$("#select_date2").append(month_option);
					}
					$("#select_date1").change(function() {
						_createtime_val = $("#select_date1").val();
						if (_createtime_val == "Daily") {
							$("#select_date2").hide();
						} else if (_createtime_val == "Weekly") {
							$("#select_date2").empty();
							$("#select_date2").append(week_option);
							$("#select_date2").show();
						} else if (_createtime_val == "Monthly") {
							$("#select_date2").empty();
							$("#select_date2").append(month_option);
							$("#select_date2").show();
						}
					});

					// /////////////////////////////////////

					// tab 切换
					$(".tab li").click(function() {
						var index = $(this).index();
						$(this).siblings().find("a").removeClass("active");
						$(this).find("a").addClass("active");
						$("#box-" + index).siblings().hide();
						$("#box-" + index).show();
					});

					// 权限设置人员选择
					$("input[name='departmentAll']").click(
							function() {
								$(this).parents(".group-each").find(
										"input[name='sub']").prop("checked",
										this.checked);
								$(this).parents(".group-each").find(
										"input[name='teamAll']").prop(
										"checked", this.checked);
							});
					$("input[name='teamAll']")
							.click(
									function() {
										var $teamAlls = $(this).parents("ul")
												.find("input[name='teamAll']");
										$(this).parents("li").find(
												"input[name='sub']").prop(
												"checked", this.checked);
										$(this)
												.parents(".group-each")
												.find(
														"input[name='departmentAll']")
												.prop(
														"checked",
														$teamAlls.length == $teamAlls
																.filter(":checked").length ? true
																: false);
									});
					$("input[name='sub']")
							.click(
									function() {
										var $subs = $(this).parents("li").find(
												"input[name='sub']");
										var $teamAlls = $(this).parents("ul")
												.find("input[name='teamAll']");
										$(this)
												.parents("li")
												.find("input[name='teamAll']")
												.prop(
														"checked",
														$subs.length == $subs
																.filter(":checked").length ? true
																: false);
										$(this)
												.parents(".group-each")
												.find(
														"input[name='departmentAll']")
												.prop(
														"checked",
														$teamAlls.length == $teamAlls
																.filter(":checked").length ? true
																: false);
									});
					$("#chooseAllBtn").click(function() {
						$("#for-choose input").prop("checked", true);
					});
					$("#deleteAllBtn").click(function() {
						$("#for-choose input").prop("checked", false);
					});

					// 数据范围
					$("input[name='rule']").click(
							function() {
								if ($(this).parents(".group").find(
										"input:first").is(':checked')) {
									$("#creatTime").show();
								} else {
									$("#creatTime").hide();
								}
							});
					// 颜色选择器插件
					$("#bgColor, #fontColor").colorpicker({
						showOn : "focus"
					});
					$("#bgColor").on("change.color", function(event, color) {
						$('#bgColor').val(color);
						var option = myChart.getOption();
						option.backgroundColor = color;
						myChart.setOption(option, true);
					});
					$("#fontColor").on("change.color", function(event, color) {
						$('#fontColor').val(color);
						var option = myChart.getOption();
						option.title[0].textStyle.color = color;
						myChart.setOption(option, true);
					});

				});

// 获取数据源
getDataSuccess = function(data) {
	basedata = data;
	INDEX = basedata.standards;
	DEPARTMENT = basedata.departmentNames;
	USER = basedata.agentNames;
	DATASOURCE = basedata.datasourceChartNames;

	var basedataselect = document.getElementById('selectdata');
	basedataselect.innerHTML = '';
	var html = '';
	for ( var i in DATASOURCE) {
		html += '<option value=' + DATASOURCE[i] + '>' + DATASOURCE[i]
				+ '</option>';
	}
	basedataselect.innerHTML = html;

	switch (typevalue) {
	case 'bar':
		option = option_bar_or_line;
		option.legend.data = INDEX;
		option.series = getSeries(INDEX);
		break;
	case 'line':
		option = option_bar_or_line;
		option.legend.data = INDEX;
		option.series = getSeries(INDEX);
		break;
	case 'pie':
		option = option_pie;
		option.legend.data = INDEX;
		option.series[0].data = get_Pie_Series(INDEX);
		break;
	case 'treemap':
		option = option_treemap;
		option.legend.data = INDEX;
		option.series[0].data = get_Pie_Series(INDEX);
		break;
	}
	myChart.setOption(option, true);
	document.getElementById('object_group').innerHTML = '';
	var legend_html = '';
	for (var i = 0; i < INDEX.length; i++) {
		legend_html += '<li><label><input type="checkbox"  checked="checked"  onclick="selectlegend()"/><span>'
				+ INDEX[i] + '</span></label></li>';
	}
	document.getElementById('legend_list').innerHTML = legend_html;
	document.getElementById('object_group').innerHTML = "<label><input name='object' type='radio' onclick='selectObject()' checked='checked' value='department'/><span>Department</span></label>"
			+ "<label><input name='object' type='radio'  onclick='selectObject()' value='user'/><span>Agent</span></label>";
	get_Object_list_item();
}

window.onresize = function() {
	myChart.resize();
}

previousstep = function() {
	window.location.href = 'index.html';
}

// ***************************绘 制 柱 状
// 图**********************************************************

function update() {
	clearInterval(timer);
	var option = myChart.getOption();
	console.log(option);
	option.legend[0].data = [ '通话量' ];
	myChart.setOption(option);
}

getIndexLegend = function() {
	basedata.standards = INDEX;
	var option = myChart.getOption();
	if (typevalue == 'pie' || typevalue == 'treemap') {
		option.series[0].data = get_Pie_Series(INDEX);
	} else if (typevalue == 'bar' || typevalue == 'line') {
		option.series = getSeries(INDEX);
	}
	option.legend[0].data = INDEX;

	myChart.setOption(option, true);
}

getDepartmentLegend = function() {
	basedata.standards = DEPARTMENT;
	var option = myChart.getOption();
	option.legend[0].data = DEPARTMENT;
	if (typevalue == 'pie' || typevalue == 'treemap') {
		option.series[0].data = get_Pie_Series(DEPARTMENT);
	} else if (typevalue == 'bar' || typevalue == 'line') {
		option.series = getSeries(DEPARTMENT);
	}
	myChart.setOption(option, true);
}

getUserLegend = function() {
	basedata.standards = USER;
	var option = myChart.getOption();
	option.legend[0].data = USER;
	if (typevalue == 'pie' || typevalue == 'treemap') {
		option.series[0].data = get_Pie_Series(USER);
	} else if (typevalue == 'bar' || typevalue == 'line') {
		option.series = getSeries(USER);
	}
	myChart.setOption(option, true);
}

// 各种图形级数据
function getSeries(d) {
	var type = sessionStorage.getItem('type');
	var serie = [];
	var objS = document.getElementById("selectdata");
	var value = objS.options[objS.selectedIndex].value;
	if (type == 'bar') {
		if (value == 'Original_statistics_for_one_hour') {
			data = data_hour;
		}
		if (value == 'Original_statistics_for_one_day') {
			data = data_day;
		}
		if (value == 'Original_statistics_for_one_week') {
			data = data_week;
		}
		for (var i = 0; i < d.length; i++) {
			var item = {
				name : d[i],
				type : 'bar',
				data : data[i]
			}
			serie.push(item);
		}
	}
	if (type == 'line') {
		if (value == 'Original_statistics_for_one_hour') {
			data = data_hour;
		}
		if (value == 'Original_statistics_for_one_day') {
			data = data_day;
		}
		if (value == 'Original_statistics_for_one_week') {
			data = data_week;
		}
		for (var i = 0; i < d.length; i++) {
			var item = {
				name : d[i],
				type : 'line',
				data : data[i]
			}
			serie.push(item);
		}

	}
	;
	console.log(serie);
	return serie;
}

function get_Pie_Series(d) {
	var type = sessionStorage.getItem('type');
	var serie = [];
	var objS = document.getElementById("selectdata");
	var value = objS.options[objS.selectedIndex].value;

	if (value == 'Original_statistics_for_one_hour') {
		data = data_hour;
	}
	if (value == 'Original_statistics_for_one_day') {
		data = data_day;
	}
	if (value == 'Original_statistics_for_one_week') {
		data = data_week;
	}
	for (var i = 0; i < d.length; i++) {
		var item = {
			value : 1,
			name : d[i]
		};
		serie.push(item);
	}

	console.log(serie);
	return serie;
}

// 选择数据源
function selectData() {
	var objS = document.getElementById("selectdata");
	var value = objS.options[objS.selectedIndex].value;
	switch (value) {
	case 'Original_statistics_for_one_hour':
		var option = myChart.getOption();
		var legendtype = $('input[name=type]:checked').val();
		if (legendtype == 'index') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_HOUR;
				option.series = getSeries(INDEX);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(INDEX);
			}

		}
		if (legendtype == 'department') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_HOUR;
				option.series = getSeries(DAPARTMENT);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(DAPARTMENT);
			}

		}
		if (legendtype == 'user') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_HOUR;
				option.series = getSeries(USER);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(USER);
			}
		}

		myChart.setOption(option, true);
		break;
	case 'Original_statistics_for_one_day':
		var option = myChart.getOption();
		var legendtype = $('input[name=type]:checked').val();
		if (legendtype == 'index') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_DAY;
				option.series = getSeries(INDEX);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(INDEX);
			}
		}
		if (legendtype == 'department') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_DAY;
				option.series = getSeries(DAPARTMENT);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(DEPARTMENT);
			}
		}
		if (legendtype == 'user') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_DAY;
				option.series = getSeries(USER);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(USER);
			}
		}

		myChart.setOption(option, true);
		break;
	case 'Original_statistics_for_one_week':
		var option = myChart.getOption();
		var legendtype = $('input[name=type]:checked').val();
		if (legendtype == 'index') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_WEEK;
				option.series = getSeries(INDEX);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(INDEX);
			}
		}
		if (legendtype == 'department') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_WEEK;
				option.series = getSeries(DEPARTMENT);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(DEPARTMENT);
			}
		}
		if (legendtype == 'user') {
			if (typevalue == 'bar' || typevalue == 'line') {
				option.xAxis[0].data = ONE_WEEK;
				option.series = getSeries(USER);
			} else if (typevalue == 'pie' || typevalue == 'treemap') {
				option.series.data = get_Pie_Series(USER);
			}
		}

		myChart.setOption(option, true);
		break;
	}
}

// 选择图例
$("input[name='type']")
		.change(
				function() {
					var legend = $("input[name='type']:checked").val();
					switch (legend) {
					case 'index':// 图例类型： 指标
						document.getElementById('object_group').innerHTML = '';
						var legend_html = '';
						for (var i = 0; i < INDEX.length; i++) {
							legend_html += '<li><label><input type="checkbox"  checked="checked"  onclick="selectlegend()"/><span>'
									+ INDEX[i] + '</span></label></li>';
						}
						document.getElementById('legend_list').innerHTML = legend_html;
						document.getElementById('object_group').innerHTML = "<label><input name='object' type='radio' onclick='selectObject()' checked='checked' value='department'/><span>Department</span></label>"
								+ "<label><input name='object' type='radio'  onclick='selectObject()' value='user'/><span>Agent</span></label>";
						get_Object_list_item();
						getIndexLegend();
						break;
					case 'department':// 图例类型： 部门
						document.getElementById('object_group').innerHTML = '';
						document.getElementById('object_group').innerHTML = "<label><input name='object' type='radio' checked='true' value='index'/><span>Index</span></label>";
						document.getElementById('legend_list').innerHTML = '';
						var legend_html = '';
						var object_html = '';
						for (var i = 0; i < DEPARTMENT.length; i++) {
							legend_html += '<li><label><input type="checkbox" checked="checked"  onclick="selectlegend()"/><span>'
									+ DEPARTMENT[i] + '</span></label></li>';
						}
						for (var j = 0; j < INDEX.length; j++) {
							object_html += '<li><label><input name="object_list_item" type="radio"/><span>'
									+ INDEX[j] + '</span></label></li>';
						}

						document.getElementById('legend_list').innerHTML = legend_html;
						document.getElementById('object_list').innerHTML = object_html;
						getDepartmentLegend();
						break;
					case 'user':// 图例类型： 用户
						document.getElementById('object_group').innerHTML = '';
						document.getElementById('object_group').innerHTML = "<label><input name='object' type='radio' checked='true' value='index'/><span>Index</span></label>";
						document.getElementById('legend_list').innerHTML = '';
						var legend_html = '';
						var object_html = '';
						for (var i = 0; i < USER.length; i++) {
							legend_html += '<li><label><input type="checkbox"  checked="checked"  onclick="selectlegend()"/><span>'
									+ USER[i] + '</span></label></li>';
						}
						for (var j = 0; j < INDEX.length; j++) {
							object_html += '<li><label><input name="object_list_item" type="radio"/><span>'
									+ INDEX[j] + '</span></label></li>';
						}

						document.getElementById('legend_list').innerHTML = legend_html;
						document.getElementById('object_list').innerHTML = object_html;
						getUserLegend();
						break;
					}
				});

function get_Object_list_item() {
	var item = $('input[name=object]:checked').val();
	switch (item) {
	case 'department':
		var object_html = '';
		for (var j = 0; j < DEPARTMENT.length; j++) {
			object_html += '<li><label><input name="object_list_item" type="radio"/><span>'
					+ DEPARTMENT[j] + '</span></label></li>';
		}
		document.getElementById('object_list').innerHTML = object_html;
		break;
	case 'user':
		var object_html = '';
		for (var k = 0; k < USER.length; k++) {
			object_html += '<li><label><input name="object_list_item" type="radio"/><span>'
					+ USER[k] + '</span></label></li>';
		}
		document.getElementById('object_list').innerHTML = object_html;
		break;
	}
}

// 选择统计对象
function selectObject() {
	$("input[name='object_list_item']").checked = true;
	$('input[name=object]').change(function() {
		get_Object_list_item();
	});
}

// 选择具体图例
selectlegend = function() {
	var lbox = document.querySelectorAll("#legend_list [type=checkbox]");
	var option = myChart.getOption();
	for (var i = 0; i < lbox.length; i++) {
		var t = $("#legend_list input[type=checkbox]")[i];
		if (t.checked) {
			var b = $("#legend_list span")[i].innerText;
			option.legend[0].selected[b] = true;
			myChart.setOption(option, true);
		} else {
			var c = $("#legend_list span")[i].innerText;
			option.legend[0].selected[c] = false;
			myChart.setOption(option, true);
		}
	}
}

// 选择title size
selectTitleSize = function() {
	var title = document.getElementById("selecttitlesize");
	var size = title.options[title.selectedIndex].value;
	var option = myChart.getOption();
	option.title[0].textStyle.fontSize = size;
	myChart.setOption(option, true);
}

addTitle = function() {
	var title = document.getElementById("title").value;
	var option = myChart.getOption();
	option.title[0].text = title;
	myChart.setOption(option, true);
}

setBackgroundColor = function() {
	var bgColor = document.getElementById("bgColor").value;
	var option = myChart.getOption();
	option.backgroundColor = bgColor;
	myChart.setOption(option, true);

}

save = function() {
	// 数据源 basedata:
	var objS = document.getElementById("selectdata");
	var basedata = objS.options[objS.selectedIndex].value;
	console.log(basedata);
	// 图例类型
	var legend = [];
	var ul = $('#legend_list li');
	for (var i = 0; i < ul.length; i++) {
		var c = $("#legend_list input[type=checkbox]")[i];
		if (c.checked) {
			var b = $("#legend_list span")[i].innerText;
			legend.push(b);
		}
	}
	var legendtype = $('input[name=type]:checked').val();
	console.log(legendtype);
	// 统计对象
	var objectItem = $('input[name=object]:checked').val();
	console.log(objectItem);
	var object;
	switch (objectItem) {
	case 'index':
		var ul = $('#object_list li');
		for (var i = 0; i < ul.length; i++) {
			var c = $("#object_list input[type=radio]")[i];
			if (c.checked) {
				object = $("#object_list span")[i].innerText;
				break;
			}
		}
		break;
	case 'department':
		var ul = $('#object_list li');
		for (var i = 0; i < ul.length; i++) {
			var c = $("#object_list input[type=radio]")[i];
			if (c.checked) {
				object = $("#object_list span")[i].innerText;
				break;
			}
		}
		break;
	case 'user':
		var ul = $('#object_list li');
		for (var i = 0; i < ul.length; i++) {
			var c = $("#object_list input[type=radio]")[i];
			if (c.checked) {
				object = $("#object_list span")[i].innerText;
				break;
			}
		}
		break;
	}

	console.log(object);
	// 报表生成规则
	var createrule = $('input[name=rule]:checked').val();
	console.log(createrule);

	// 数据范围
	var range = $('input[name=range]:checked').val();
	console.log(range);
	var time = '';
	var day = $('input[name=time]')[0].value;
	var hour = $('input[name=time]')[1].value;
	var min = $('input[name=time]')[2].value;
	if (day == '') {
		day = 0;
	}
	if (hour == '') {
		hour = 0;
	}
	if (min == '') {
		min = 0;
	}
	time = day + ':' + hour + ':' + min;
	console.log(time);
	// 生成时刻
	var chartdate = '';
	if (createrule == 'history') {
		var chartdate = '';
		var select_date1 = document.getElementById("select_date1");
		var date1 = select_date1.options[select_date1.selectedIndex].value;
		console.log(date1);
		chartdate += date1;
		if (date1 != 'Daily') {
			var select_date2 = document.getElementById("select_date2");
			var date2 = select_date2.options[select_date2.selectedIndex].value;
			console.log(date2);
			chartdate += ',' + date2;
		}
		var select_date3 = document.getElementById("select_date3");
		var date3 = select_date3.options[select_date3.selectedIndex].value;
		console.log(date3);
		chartdate += ',' + date3;
		console.log(chartdate);
	}

	if (typeof object == 'undefined') {
		alert('Please Choose Statistical Object');
		return;
	}
	var param = setParam('chartType', basedata) + ','
			+ setParam('legendype', legendtype) + ','
			+ setParam('legends', legend) + ','
			+ setParam('objectType', objectItem) + ','
			+ setParam('statisticalObject', object) + ','
			+ setParam('isHistory', createrule) + ','
			+ setParam('isNatural', range) + ',' + setParam('beforeTime', time)
			+ ',' + setParam('produceTime', chartdate);

	if (typevalue == 'pie' || typevalue == 'treemap') {
		callAction('chart/piechart.action', param, saveSuccess);
	} else if (typevalue == 'bar' || typevalue == 'line') {
		callAction('chart/histogram.action', param, saveSuccess);
	}

}

saveSuccess = function(data) {
	var realdata = data;
	var arr_data = [];
	data_hour = [];
	data_day = [];
	data_week = [];
	var objS = document.getElementById("selectdata");
	var value = objS.options[objS.selectedIndex].value;
	for ( var i in basedata.standards) {
		switch (typevalue) {
		case 'bar':
			var a = basedata.standards[i];
			arr_data.push(realdata[a]);
			break;
		case 'line':
			var a = basedata.standards[i];
			arr_data.push(realdata[a]);
			break;
		case 'pie':
			var a = basedata.standards[i];
			var value = {
				value : realdata[a],
				name : a
			};
			arr_data.push(value);
			break;
		case 'treemap':
			var a = basedata.standards[i];
			var value = {
				value : realdata[a],
				name : a
			};
			arr_data.push(value);
			break;
		}
	}
	console.log(arr_data);
	if (value == 'Original_statistics_for_one_hour') {
		data_hour = arr_data;
	}
	if (value == 'Original_statistics_for_one_day') {
		data_day = arr_data;
	}
	if (value == 'Original_statistics_for_one_week') {
		data_week = arr_data;
	}

	var option = myChart.getOption();
	var legendtype = $('input[name=type]:checked').val();

	switch (typevalue) {
	case 'bar':
		option.xAxis[0].data = realdata.xaxis;
		if (legendtype == 'index') {
			option.series = getSeries(INDEX);
		}
		if (legendtype == 'department') {
			option.series = getSeries(DEPARTMENT);
		}
		if (legendtype == 'user') {
			option.series = getSeries(USER);
		}
		break;
	case 'line':
		option.xAxis[0].data = realdata.xaxis;
		if (legendtype == 'index') {
			option.series = getSeries(INDEX);
		}
		if (legendtype == 'department') {
			option.series = getSeries(DEPARTMENT);
		}
		if (legendtype == 'user') {
			option.series = getSeries(USER);
		}
		break;
	case 'pie':
		option.series[0].data = arr_data;
		break;
	case 'treemap':
		option.series[0].data = arr_data;
		break;
	}

	sessionStorage.setItem('option', JSON.stringify(option));
	myChart.setOption(option, true);

}
