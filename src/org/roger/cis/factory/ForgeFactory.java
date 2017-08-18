package org.roger.cis.factory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.roger.cis.model.CisAgent;
import org.roger.cis.model.CisDepartment;
import net.sf.json.JSONArray;

/**
 * 造假工厂
 * 
 * @author Hiram
 *
 */
public class ForgeFactory {
	/**
	 * 编一个一个坐席一个小时的假数据
	 * 
	 * @param agentId
	 */
	public static CisAgent produceAgentData(Integer agentId) {

		String agentName = "Worker-" + agentId;

		Random random = new Random();
		DecimalFormat df = new DecimalFormat("0.00");

		Integer erLang = random.nextInt(20) + 1;// 通话量
		Double averageScore = Double.valueOf(df.format(random.nextDouble() * 100));
		Double averageCallDuration = Double.valueOf(df.format(random.nextDouble() * 100 + 60));
		;// 平均通话时长
		Integer businessCompletedNum = random.nextInt(10) + 1;// 业务完成数量
		Double speakSpeed = Double.valueOf(df.format(random.nextDouble() * 2)) + 5;// 语速
		Integer complaintNum = random.nextInt(3);// 投诉量
		Integer illegalOperationNum = random.nextInt(2);// 违规操作数量
		CisAgent agent = new CisAgent(agentId, agentName, erLang, averageScore, averageCallDuration,
				businessCompletedNum, speakSpeed, complaintNum, illegalOperationNum);
		return agent;
	}

	public static CisDepartment produceDepartment(Integer id, List<CisAgent> li) {
		String departmentName = "Group-" + id;
		CisDepartment departMent = new CisDepartment(id, departmentName, li);
		return departMent;
	}

	/**
	 * 假设有五个部门，50个员工，每个部门有十个员工
	 * 
	 * @param hours
	 */
	public static String produceHoursData() {
		String jsonStr = "";

		List<CisDepartment> departments = new ArrayList<>();

		for (int j = 1; j < 6; j++) {
			List<CisAgent> li = new ArrayList<CisAgent>();
			for (int i = (j * 10 - 9); i < (j * 10 + 1); i++) {
				CisAgent agent = produceAgentData(i);
				li.add(agent);
			}
			CisDepartment department = produceDepartment(j, li);
			departments.add(department);
			JSONArray obj = JSONArray.fromObject(departments);
			jsonStr = obj.toString();
		}

		return jsonStr;
	}

	/**
	 * 造14天的假数据
	 */
	public static void produceAllData() {
		String rootPath = System.getProperty("user.dir");
		for (int i = 1; i <= 14; i++) {
			File f = new File(rootPath + "\\data\\" + "2017-8-" + i);

			if (!f.exists()) {
				f.mkdirs();
			}
			
			for (int h = 1; h <= 24; h++) {
				String hourdata = produceHoursData();
				File f2 = new File(f, "hour-" + h + ".json");
				FileWriter writer = null;
				try {
					writer = new FileWriter(f2);
					writer.write(hourdata);
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {

		ForgeFactory.produceAllData();
	}
}
