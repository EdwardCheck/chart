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
 * ��ٹ���
 * 
 * @author Hiram
 *
 */
public class ForgeFactory {
	/**
	 * ��һ��һ����ϯһ��Сʱ�ļ�����
	 * 
	 * @param agentId
	 */
	public static CisAgent produceAgentData(Integer agentId) {

		String agentName = "Worker-" + agentId;

		Random random = new Random();
		DecimalFormat df = new DecimalFormat("0.00");

		Integer erLang = random.nextInt(20) + 1;// ͨ����
		Double averageScore = Double.valueOf(df.format(random.nextDouble() * 100));
		Double averageCallDuration = Double.valueOf(df.format(random.nextDouble() * 100 + 60));
		;// ƽ��ͨ��ʱ��
		Integer businessCompletedNum = random.nextInt(10) + 1;// ҵ���������
		Double speakSpeed = Double.valueOf(df.format(random.nextDouble() * 2)) + 5;// ����
		Integer complaintNum = random.nextInt(3);// Ͷ����
		Integer illegalOperationNum = random.nextInt(2);// Υ���������
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
	 * ������������ţ�50��Ա����ÿ��������ʮ��Ա��
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
	 * ��14��ļ�����
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
