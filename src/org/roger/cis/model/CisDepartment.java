package org.roger.cis.model;

import java.util.List;

/**
 * ²¿ÃÅ±í
 * 
 * @author Hiram
 *
 */
public class CisDepartment {
	private Integer departmentId;
	private String departmentName;
	private List<CisAgent> cisAgents;

	public CisDepartment(Integer departmentId, String departmentName, List<CisAgent> cisAgents) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.cisAgents = cisAgents;
	}


	public Integer getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public List<CisAgent> getCisAgents() {
		return cisAgents;
	}


	public void setCisAgents(List<CisAgent> cisAgents) {
		this.cisAgents = cisAgents;
	}


	public CisDepartment() {
		// TODO Auto-generated constructor stub
	}
}
