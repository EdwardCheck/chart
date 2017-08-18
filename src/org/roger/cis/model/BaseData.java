package org.roger.cis.model;

import java.util.ArrayList;
import java.util.List;

public class BaseData {
	private List<String> datasourceChartNames;
	private List<String> standards;
	private List<String> departmentNames;
	private List<String> agentNames;
	public List<String> getDatasourceChartNames() {
		return datasourceChartNames;
	}
	public void setDatasourceChartNames(List<String> datasourceChartNames) {
		this.datasourceChartNames = datasourceChartNames;
	}
	public List<String> getStandards() {
		return standards;
	}
	public void setStandards(List<String> standards) {
		this.standards = standards;
	}
	public List<String> getDepartmentNames() {
		return departmentNames;
	}
	public void setDepartmentNames(List<String> departmentNames) {
		this.departmentNames = departmentNames;
	}
	public List<String> getAgentNames() {
		return agentNames;
	}
	public void setAgentNames(List<String> agentNames) {
		this.agentNames = agentNames;
	}
	public BaseData(List<String> datasourceChartNames, List<String> standards, List<String> departmentNames,
			List<String> agentNames) {
		super();
		this.datasourceChartNames = datasourceChartNames;
		this.standards = standards;
		this.departmentNames = departmentNames;
		this.agentNames = agentNames;
	}
	public BaseData() {
		// TODO Auto-generated constructor stub
		datasourceChartNames = new ArrayList<>();
		datasourceChartNames.add("Original_statistics_for_one_hour");
		datasourceChartNames.add("Original_statistics_for_one_day");
		datasourceChartNames.add("Original_statistics_for_one_week");
		standards = new ArrayList<>();
		standards.add("Call_number");
		standards.add("Average_score");
		standards.add("Average_call_duration");
		standards.add("Business_completed_number");
		standards.add("Speakspeed");
		standards.add("Complaint_number");
		standards.add("Illegal_operation_number");
		departmentNames = new ArrayList<>();
		departmentNames.add("Department_one");
		departmentNames.add("Department_two");
		departmentNames.add("Department_three");
		departmentNames.add("Department_four");
		departmentNames.add("Department_five");
		agentNames = new ArrayList<>();
		agentNames.add("Agent_1");
		agentNames.add("Agent_2");
		agentNames.add("Agent_3");
		agentNames.add("Agent_4");
		agentNames.add("Agent_5");
		agentNames.add("Agent_6");
		agentNames.add("Agent_7");
		agentNames.add("Agent_8");
		agentNames.add("Agent_9");
		agentNames.add("Agent_10");
		agentNames.add("Agent_11");
		agentNames.add("Agent_12");
		agentNames.add("Agent_13");
		agentNames.add("Agent_14");
		agentNames.add("Agent_15");
		agentNames.add("Agent_16");
		agentNames.add("Agent_17");
		agentNames.add("Agent_18");
		agentNames.add("Agent_19");
		agentNames.add("Agent_20");
		agentNames.add("Agent_21");
		agentNames.add("Agent_22");
		agentNames.add("Agent_23");
		agentNames.add("Agent_24");
		agentNames.add("Agent_25");
		agentNames.add("Agent_26");
		agentNames.add("Agent_27");
		agentNames.add("Agent_28");
		agentNames.add("Agent_29");
		agentNames.add("Agent_30");
		agentNames.add("Agent_31");
		agentNames.add("Agent_32");
		agentNames.add("Agent_33");
		agentNames.add("Agent_34");
		agentNames.add("Agent_35");
		agentNames.add("Agent_36");
		agentNames.add("Agent_37");
		agentNames.add("Agent_38");
		agentNames.add("Agent_39");
		agentNames.add("Agent_40");
		agentNames.add("Agent_41");
		agentNames.add("Agent_42");
		agentNames.add("Agent_43");
		agentNames.add("Agent_44");
		agentNames.add("Agent_45");
		agentNames.add("Agent_46");
		agentNames.add("Agent_47");
		agentNames.add("Agent_48");
		agentNames.add("Agent_49");
		agentNames.add("Agent_50");
	}
}
