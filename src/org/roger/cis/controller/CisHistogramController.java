package org.roger.cis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.roger.cis.factory.StatisticFactory;
import org.roger.cis.model.BaseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

/**
 * 这个类调用与柱状图相关的一些接口
 * @author Hiram
 */
@Controller
@RequestMapping("/chart")
public class CisHistogramController {
	@RequestMapping("histogram.action")
	public void getHistogramData (HttpServletRequest request, HttpServletResponse response) {
		String a= request.getParameter("a");
		System.out.println(a);
		JSONObject jsonObject = JSONObject.fromObject(a);
		String chartType = (String) jsonObject.get("chartType");
		String legendType = (String) jsonObject.get("legendype");
		String[] legends = jsonObject.get("legends").toString().split(",");
		String statisticalObject = (String) jsonObject.get("statisticalObject");
		String isHistory = (String) jsonObject.get("isHistory");
		String isNatural = (String) jsonObject.get("isNatural");
		String beforeTime = (String) jsonObject.get("beforeTime");
		String produceTime = (String) jsonObject.get("produceTime");
		StatisticFactory sf = new StatisticFactory();
		String mes = sf.getData(request, chartType, legends, legendType, statisticalObject, isHistory, isNatural,
				beforeTime, produceTime);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(mes);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("piechart.action")
	public void getPieChartData(HttpServletRequest request, HttpServletResponse response) {
		String a = request.getParameter("a");
		System.out.println(a);
		JSONObject jsonObject = JSONObject.fromObject(a);
		String chartType = (String) jsonObject.get("chartType");
		String legendType = (String) jsonObject.get("legendype");
		String[] legends = jsonObject.get("legends").toString().split(",");
		String statisticalObject = (String) jsonObject.get("statisticalObject");
		String isHistory = (String) jsonObject.get("isHistory");
		String isNatural = (String) jsonObject.get("isNatural");
		String beforeTime = (String) jsonObject.get("beforeTime");
		String produceTime = (String) jsonObject.get("produceTime");
		StatisticFactory sf = new StatisticFactory();
		String mes = sf.getPieData(request, chartType, legends, legendType, statisticalObject, isHistory, isNatural,
				beforeTime, produceTime);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(mes);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	@RequestMapping("linechart.action")
	public void getLineChartData(HttpServletRequest request, HttpServletResponse response) {
		String a = request.getParameter("a");
		System.out.println(a);
		JSONObject jsonObject = JSONObject.fromObject(a);
		String chartType = (String) jsonObject.get("chartType");
		String legendType = (String) jsonObject.get("legendype");
		String[] legends = jsonObject.get("legends").toString().split(",");
		String statisticalObject = (String) jsonObject.get("statisticalObject");
		String isHistory = (String) jsonObject.get("isHistory");
		String isNatural = (String) jsonObject.get("isNatural");
		String beforeTime = (String) jsonObject.get("beforeTime");
		String produceTime = (String) jsonObject.get("produceTime");
		StatisticFactory sf = new StatisticFactory();
		String mes = sf.getData(request, chartType, legends, legendType, statisticalObject, isHistory, isNatural,
				beforeTime, produceTime);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(mes);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@RequestMapping("rectangularChart.action")
	public void getRectangularChartData(HttpServletRequest request, HttpServletResponse response) {

	}
	
	@RequestMapping("basedata.action")
	public void getBaseData (HttpServletRequest request, HttpServletResponse rsponse) {
		BaseData baseData = new BaseData();
		JSONObject jsonObject = JSONObject.fromObject(baseData);
		String str = jsonObject.toString();
		rsponse.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		
		try {
			out = rsponse.getWriter();
			out.write(str);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			out.close();
		}
		
	}
	public static void main(String[] args) {
		BaseData baseData = new BaseData();
		JSONObject jsonObject = JSONObject.fromObject(baseData);
		String str = jsonObject.toString();
		System.out.println(str);
	}
}
