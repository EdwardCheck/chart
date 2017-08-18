package org.roger.cis.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.roger.cis.model.CisAgent;
import org.roger.cis.model.CisDepartment;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 解析json格式的数据--> Object
 * @author Hiram
 *
 */
public class PraseFactory {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<CisDepartment> prase (String jsonUrl) {
		List<CisDepartment> departments = new ArrayList<>();
		File file = new File(jsonUrl);
		
		if (!file.exists()) {
			System.out.println("找不到该路径");
			return null;
		}
		
		String tempString = null;
		StringBuffer buffer = new StringBuffer();
		FileReader fr = null;
		BufferedReader reader = null;
		try {
			fr = new FileReader(file);
			reader = new BufferedReader(fr);
			
			while ((tempString = reader.readLine()) != null) {
				buffer.append(tempString);
			}
			
			new ArrayList<>();
			System.out.println(buffer.toString());
			JSONArray jsonArray = JSONArray.fromObject(buffer.toString());
			Iterator<Object> it = jsonArray.iterator();
			while (it.hasNext()) {
				JSONObject ob = (JSONObject) it.next();
				Map<String, Class> classMap = new HashMap<String, Class>();
				classMap.put("cisAgents", CisAgent.class);  
				CisDepartment cd = (CisDepartment) JSONObject.toBean(ob, CisDepartment.class, classMap);
				departments.add(cd);	
			}
			System.out.println("****");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departments;
	}
	
	
	
	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		PraseFactory.prase(path + "\\data\\2017-8-1\\hour-1.json");
	}
}
