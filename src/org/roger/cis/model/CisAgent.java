package org.roger.cis.model;

public class CisAgent {
	private Integer id;
	private String agentName;

	private Integer erLang;// ͨ����
	private Double averageScore;// ƽ����
	private Double averageCallDuration;// ƽ��ͨ��ʱ��
	private Integer businessCompletedNum;// ҵ���������
	private Double speakSpeed;// ����
	private Integer complaintNum;// Ͷ����
	private Integer illegalOperationNum;// Υ���������

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Integer getErLang() {
		return erLang;
	}

	public void setErLang(Integer erLang) {
		this.erLang = erLang;
	}

	public Double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	public Double getAverageCallDuration() {
		return averageCallDuration;
	}

	public void setAverageCallDuration(Double averageCallDuration) {
		this.averageCallDuration = averageCallDuration;
	}

	public Integer getBusinessCompletedNum() {
		return businessCompletedNum;
	}

	public void setBusinessCompletedNum(Integer businessCompletedNum) {
		this.businessCompletedNum = businessCompletedNum;
	}

	public Double getSpeakSpeed() {
		return speakSpeed;
	}

	public void setSpeakSpeed(Double speakSpeed) {
		this.speakSpeed = speakSpeed;
	}

	public Integer getComplaintNum() {
		return complaintNum;
	}

	public void setComplaintNum(Integer complaintNum) {
		this.complaintNum = complaintNum;
	}

	public Integer getIllegalOperationNum() {
		return illegalOperationNum;
	}

	public void setIllegalOperationNum(Integer illegalOperationNum) {
		this.illegalOperationNum = illegalOperationNum;
	}

	public CisAgent(Integer id, String agentName, Integer erLang, Double averageScore, Double averageCallDuration,
			Integer businessCompletedNum, Double speakSpeed, Integer complaintNum, Integer illegalOperationNum) {
		super();
		this.id = id;
		this.agentName = agentName;
		this.erLang = erLang;
		this.averageScore = averageScore;
		this.averageCallDuration = averageCallDuration;
		this.businessCompletedNum = businessCompletedNum;
		this.speakSpeed = speakSpeed;
		this.complaintNum = complaintNum;
		this.illegalOperationNum = illegalOperationNum;
	}

	public CisAgent() {
		// TODO Auto-generated constructor stub
	}
}
