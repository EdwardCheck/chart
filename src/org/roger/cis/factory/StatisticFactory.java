package org.roger.cis.factory;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.roger.cis.model.CisAgent;
import org.roger.cis.model.CisDepartment;

import net.sf.json.JSONObject;

public class StatisticFactory {
	
	private String filePath;
	
	
	public StatisticFactory() {
		super();
		try {
			filePath = Paths.get(this.getClass().getResource("").toURI()).toString();
			if (filePath.contains("\\")) {
				filePath += "\\";
			} else {
				filePath += "/";
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	/**
	 * @param timeType
	 * @param legends
	 * @param legendType
	 * @param statisticalObject
	 * @param isCurrentChart
	 * @param isNaturalDay
	 * @param brforetimes
	 * @param produceTime
	 */
	public String getData(HttpServletRequest request, String timeType, String[] legends, String legendType,
			String statisticalObject, String isCurrentChart, String isNaturalDay, String brforetimes,
			String produceTime) {
		Map<String, List<String>> datas = new TreeMap<>();
		String[] btimes = brforetimes.split(":");

		if (timeType.contains("hour")) {
			int brforeHour = Integer.parseInt(btimes[1]);
			int produce_hour = Integer.parseInt(produceTime.split(",")[1].split(":")[0]);
			int beginTime = produce_hour - brforeHour;
			List<String> xaxis = new ArrayList<>();
			for (int i = 0; i < brforeHour; i++) {
				if (beginTime < 0) {
					beginTime = 24 + beginTime;
					if (beginTime < 10) {
						xaxis.add("0" + beginTime + ":00");
					} else {
						xaxis.add(beginTime + ":00");
					}
					beginTime++;
				} else if (beginTime >= 24) {
					beginTime = beginTime - 24;
					if (beginTime < 10) {
						xaxis.add("0" + beginTime + ":00");
					} else {
						xaxis.add(beginTime + ":00");
					}
					beginTime++;
				} else {
					if (beginTime < 10) {
						xaxis.add("0" + beginTime + ":00");
					} else {
						xaxis.add(beginTime + ":00");
					}
					beginTime++;
				}
			}
			datas.put("xaxis", xaxis);

			int legendsSize = legends.length;
			String path = this.getClass().getClassLoader().getResource("").toString();
			List<List<CisDepartment>> departments_list = new ArrayList<>();
			for (int h = 1; h <= brforeHour; h++) {
				List<CisDepartment> departments = PraseFactory.prase(filePath + "data/hour/2017-8-1/hour-" + h + ".json");
				departments_list.add(departments);
			}

			for (int i = 0; i < legendsSize; i++) {
				String legendName = legends[i];
				List<String> li = new ArrayList<>();

				switch (legendType) {
				case "index":
					switch (legendName) {
					case "Call_number":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Average_score":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getAverageScore();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageScore();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageScore();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageScore();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageScore();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}

						System.out.println("******************");
						break;
					case "Average_call_duration":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getAverageCallDuration();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageCallDuration();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageCallDuration();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageCallDuration();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageCallDuration();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Business_completed_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getBusinessCompletedNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getBusinessCompletedNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getBusinessCompletedNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getBusinessCompletedNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getBusinessCompletedNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Speakspeed":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getSpeakSpeed();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getSpeakSpeed();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getSpeakSpeed();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getSpeakSpeed();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getSpeakSpeed();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Complaint_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getComplaintNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getComplaintNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getComplaintNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getComplaintNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getComplaintNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Illegal_operation_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getIllegalOperationNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getIllegalOperationNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getIllegalOperationNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getIllegalOperationNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getIllegalOperationNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					default:
						break;
					}

					break;
				case "department":
					switch (legendName) {
					case "Department_one":

						switch (statisticalObject) {
						case "Call_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

								li.add(call_num + "");
							}
							break;
						case "Average_score":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageScore();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Average_call_duration":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageCallDuration();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Business_completed_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getBusinessCompletedNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Speakspeed":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getSpeakSpeed();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Complaint_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getComplaintNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Illegal_operation_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getIllegalOperationNum();
								}

								li.add(call_num + "");
							}
							break;
						default:
							break;
						}
						break;
					case "Department_two":
						switch (statisticalObject) {
						case "Call_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

								li.add(call_num + "");
							}
							break;
						case "Average_score":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageScore();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Average_call_duration":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageCallDuration();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Business_completed_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getBusinessCompletedNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Speakspeed":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getSpeakSpeed();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Complaint_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getComplaintNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Illegal_operation_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getIllegalOperationNum();
								}

								li.add(call_num + "");
							}
							break;
						default:
							break;
						}
						break;
					case "Department_three":
						switch (statisticalObject) {
						case "Call_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

								li.add(call_num + "");
							}
							break;
						case "Average_score":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageScore();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Average_call_duration":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageCallDuration();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Business_completed_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getBusinessCompletedNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Speakspeed":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getSpeakSpeed();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Complaint_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getComplaintNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Illegal_operation_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getIllegalOperationNum();
								}

								li.add(call_num + "");
							}
							break;
						default:
							break;
						}
						break;
					case "Department_four":
						switch (statisticalObject) {
						case "Call_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

								li.add(call_num + "");
							}
							break;
						case "Average_score":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageScore();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Average_call_duration":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageCallDuration();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Business_completed_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getBusinessCompletedNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Speakspeed":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getSpeakSpeed();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Complaint_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getComplaintNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Illegal_operation_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getIllegalOperationNum();
								}

								li.add(call_num + "");
							}
							break;
						default:
							break;
						}
						break;
					case "Department_five":
						switch (statisticalObject) {
						case "Call_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

								li.add(call_num + "");
							}
							break;
						case "Average_score":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageScore();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Average_call_duration":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getAverageCallDuration();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Business_completed_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getBusinessCompletedNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Speakspeed":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getSpeakSpeed();
								}

								li.add(call_num / 10 + "");
							}
							break;
						case "Complaint_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getComplaintNum();
								}

								li.add(call_num + "");
							}
							break;
						case "Illegal_operation_number":
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();
								int call_num = 0;

								for (CisAgent agent : agents) {
									call_num += agent.getIllegalOperationNum();
								}

								li.add(call_num + "");
							}
							break;
						default:
							break;
						}
						break;
					default:
						break;
					}
					break;
				case "Agent":

					break;

				default:
					break;
				}
				datas.put(legendName, li);
			}
		} else if (timeType.contains("day")) {
			int brforeDays = Integer.parseInt(btimes[0]);
			// int produce_hour =
			// Integer.parseInt(produceTime.split(",")[1].split(":")[0]);

			List<String> xaxis = new ArrayList<>();
			for (int i = 0; i < brforeDays; i++) {
				xaxis.add((i + 1) + "Day");
			}
			datas.put("xaxis", xaxis);

			int legendsSize = legends.length;
			String path = this.getClass().getClassLoader().getResource("").toString();
			List<List<CisDepartment>> departments_list = new ArrayList<>();
			for (int h = 1; h <= brforeDays; h++) {
				List<CisDepartment> departments = PraseFactory
						.prase(filePath + "data/day/2017-8-" + h + "/2017-8-" + h + ".json");
				departments_list.add(departments);
			}

			for (int i = 0; i < legendsSize; i++) {
				String legendName = legends[i];
				List<String> li = new ArrayList<>();

				switch (legendType) {
				case "index":
					switch (legendName) {
					case "Call_number":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Average_score":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getAverageScore();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageScore();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageScore();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageScore();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageScore();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}

						System.out.println("******************");
						break;
					case "Average_call_duration":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getAverageCallDuration();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageCallDuration();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageCallDuration();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageCallDuration();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageCallDuration();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Business_completed_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getBusinessCompletedNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getBusinessCompletedNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getBusinessCompletedNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getBusinessCompletedNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getBusinessCompletedNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Speakspeed":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getSpeakSpeed();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getSpeakSpeed();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getSpeakSpeed();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getSpeakSpeed();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getSpeakSpeed();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Complaint_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getComplaintNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getComplaintNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getComplaintNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getComplaintNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getComplaintNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Illegal_operation_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getIllegalOperationNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getIllegalOperationNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getIllegalOperationNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getIllegalOperationNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getIllegalOperationNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					default:
						break;
					}

					break;
				case "Department":

					break;
				case "Agent":

					break;

				default:
					break;
				}
				datas.put(legendName, li);
			}
		} else {
			int brforeWeek = Integer.parseInt(btimes[0]) / 7;

			List<String> xaxis = new ArrayList<>();
			for (int i = 1; i <= brforeWeek; i++) {

				xaxis.add(i + "Week");

			}
			datas.put("xaxis", xaxis);

			int legendsSize = legends.length;
			String path = this.getClass().getClassLoader().getResource("").toString();
			List<List<CisDepartment>> departments_list = new ArrayList<>();
			for (int h = 1; h <= brforeWeek; h++) {
				List<CisDepartment> departments = PraseFactory
						.prase(filePath + "data/week/2017-8第" + h + "周/第" + h + "周.json");
				departments_list.add(departments);
			}

			for (int i = 0; i < legendsSize; i++) {
				String legendName = legends[i];
				List<String> li = new ArrayList<>();

				switch (legendType) {
				case "index":
					switch (legendName) {
					case "Call_number":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Average_score":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getAverageScore();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageScore();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageScore();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageScore();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageScore();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}

						System.out.println("******************");
						break;
					case "Average_call_duration":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getAverageCallDuration();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageCallDuration();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageCallDuration();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageCallDuration();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageCallDuration();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Business_completed_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getBusinessCompletedNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getBusinessCompletedNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getBusinessCompletedNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getBusinessCompletedNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getBusinessCompletedNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Speakspeed":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getSpeakSpeed();
									}
									li.add(call_num / 10 + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getSpeakSpeed();
									}
									li.add(call_num2 / 10 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getSpeakSpeed();
									}
									li.add(call_num3 / 10 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getSpeakSpeed();
									}
									li.add(call_num4 / 10 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getSpeakSpeed();
									}
									li.add(call_num5 / 10 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Complaint_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getComplaintNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getComplaintNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getComplaintNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getComplaintNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getComplaintNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					case "Illegal_operation_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":

								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();
									int call_num = 0;
									for (CisAgent agent : agents) {
										call_num += agent.getIllegalOperationNum();
									}
									li.add(call_num + "");
								}

								break;
							case "Department_two":
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();
									int call_num2 = 0;
									for (CisAgent agent : agents2) {
										call_num2 += agent.getIllegalOperationNum();
									}
									li.add(call_num2 + "");
								}
								break;
							case "Department_three":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();
									int call_num3 = 0;
									for (CisAgent agent : agents3) {
										call_num3 += agent.getIllegalOperationNum();
									}
									li.add(call_num3 + "");
								}
								break;
							case "Department_four":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();
									int call_num4 = 0;
									for (CisAgent agent : agents4) {
										call_num4 += agent.getIllegalOperationNum();
									}
									li.add(call_num4 + "");
								}
								break;
							case "Department_five":
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();
									int call_num5 = 0;
									for (CisAgent agent : agents5) {
										call_num5 += agent.getIllegalOperationNum();
									}
									li.add(call_num5 + "");
								}
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();
								int call_num = 0;

								if (remain == 0) {
									call_num += agents.get(9).getErLang();
									li.add(call_num + "");
								} else {
									call_num += agents.get(remain - 1).getErLang();
									li.add(call_num + "");
								}

							}
						}
						System.out.println("******************");
						break;
					default:
						break;
					}

					break;
				case "Department":

					break;
				case "Agent":

					break;

				default:
					break;
				}
				datas.put(legendName, li);
			}
		}

		JSONObject jo = JSONObject.fromObject(datas);
		System.out.println(jo.toString());
		return jo.toString();

	}

	public String getPieData(HttpServletRequest request, String timeType, String[] legends, String legendType,
			String statisticalObject, String isCurrentChart, String isNaturalDay, String brforetimes,
			String produceTime) {
		Map<String, List<String>> datas = new TreeMap<>();
		String[] btimes = brforetimes.split(":");

		if (timeType.contains("hour")) {
			int brforeHour = Integer.parseInt(btimes[1]);
			int produce_hour = Integer.parseInt(produceTime.split(",")[1].split(":")[0]);
			int beginTime = produce_hour - brforeHour;
			List<String> xaxis = new ArrayList<>();
			xaxis = Arrays.asList(legends);
			datas.put("xaxis", xaxis);

			int legendsSize = legends.length;
			String path = this.getClass().getClassLoader().getResource("").toString();
			List<List<CisDepartment>> departments_list = new ArrayList<>();
			for (int h = 1; h <= brforeHour; h++) {
				List<CisDepartment> departments = PraseFactory.prase(filePath + "data/hour/2017-8-1/hour-" + h + ".json");
				departments_list.add(departments);
			}

			for (int i = 0; i < legendsSize; i++) {
				String legendName = legends[i];
				List<String> li = new ArrayList<>();

				switch (legendType) {
				case "index":
					switch (legendName) {
					case "Call_number":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}
								}

								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Average_score":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getAverageScore();
									}

								}
								li.add(call_num / 10 + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageScore();
									}

								}
								li.add(call_num2 / 10 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageScore();
									}

								}
								li.add(call_num3 / 10 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageScore();
									}

								}
								li.add(call_num4 / 10 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageScore();
									}

								}
								li.add(call_num5 / 10 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}

						System.out.println("******************");
						break;
					case "Average_call_duration":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getAverageCallDuration();
									}

								}
								li.add(call_num / 10 + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageCallDuration();
									}

								}
								li.add(call_num2 / 10 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageCallDuration();
									}

								}
								li.add(call_num3 / 10 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageCallDuration();
									}

								}
								li.add(call_num4 / 10 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageCallDuration();
									}

								}
								li.add(call_num5 / 10 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Business_completed_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Speakspeed":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getSpeakSpeed();
									}

								}
								li.add(call_num / 10 + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getSpeakSpeed();
									}

								}
								li.add(call_num2 / 10 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getSpeakSpeed();
									}

								}
								li.add(call_num3 / 10 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getSpeakSpeed();
									}

								}
								li.add(call_num4 / 10 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getSpeakSpeed();
									}

								}
								li.add(call_num5 / 10 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Complaint_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getComplaintNum();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getComplaintNum();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getComplaintNum();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getComplaintNum();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getComplaintNum();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Illegal_operation_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getIllegalOperationNum();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getIllegalOperationNum();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getIllegalOperationNum();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getIllegalOperationNum();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getIllegalOperationNum();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					default:
						break;
					}

					break;
				case "department":
					switch (legendName) {
					case "Department_one":

						switch (statisticalObject) {
						case "Call_number":
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

							}
							li.add(call_num + "");
							break;
						case "Average_score":
							int call_num2 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();

								for (CisAgent agent : agents) {
									call_num2 += agent.getAverageScore();
								}

							}
							li.add(call_num2 / 10 + "");
							break;
						case "Average_call_duration":
							int call_num3 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();

								for (CisAgent agent : agents) {
									call_num3 += agent.getAverageCallDuration();
								}

							}
							li.add(call_num3 / 10 + "");
							break;
						case "Business_completed_number":
							int call_num4 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();

								for (CisAgent agent : agents) {
									call_num4 += agent.getBusinessCompletedNum();
								}
							}
							li.add(call_num4 + "");
							break;
						case "Speakspeed":
							int call_num5 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();

								for (CisAgent agent : agents) {
									call_num5 += agent.getSpeakSpeed();
								}

							}
							li.add(call_num5 / 10 + "");
							break;
						case "Complaint_number":
							int call_num6 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();

								for (CisAgent agent : agents) {
									call_num6 += agent.getComplaintNum();
								}
							}
							li.add(call_num6 + "");
							break;
						case "Illegal_operation_number":
							int call_num7 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();

								for (CisAgent agent : agents) {
									call_num7 += agent.getIllegalOperationNum();
								}

							}
							li.add(call_num7 + "");
							break;
						default:
							break;
						}
						break;
					case "Department_two":
						switch (statisticalObject) {
						case "Call_number":
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(0).getCisAgents();

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

							}
							li.add(call_num + "");
							break;
						case "Average_score":
							int call_num2 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();

								for (CisAgent agent : agents) {
									call_num2 += agent.getAverageScore();
								}

							}
							li.add(call_num2 / 10 + "");
							break;
						case "Average_call_duration":
							int call_num3 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();

								for (CisAgent agent : agents) {
									call_num3 += agent.getAverageCallDuration();
								}

							}
							li.add(call_num3 / 10 + "");
							break;
						case "Business_completed_number":
							int call_num4 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();

								for (CisAgent agent : agents) {
									call_num4 += agent.getBusinessCompletedNum();
								}
							}
							li.add(call_num4 + "");
							break;
						case "Speakspeed":
							int call_num5 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();

								for (CisAgent agent : agents) {
									call_num5 += agent.getSpeakSpeed();
								}

							}
							li.add(call_num5 / 10 + "");
							break;
						case "Complaint_number":
							int call_num6 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();

								for (CisAgent agent : agents) {
									call_num6 += agent.getComplaintNum();
								}
							}
							li.add(call_num6 + "");
							break;
						case "Illegal_operation_number":
							int call_num7 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(1).getCisAgents();

								for (CisAgent agent : agents) {
									call_num7 += agent.getIllegalOperationNum();
								}

							}
							li.add(call_num7 + "");
							break;
						default:
							break;
						}
						break;
					case "Department_three":
						switch (statisticalObject) {
						case "Call_number":
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

							}
							li.add(call_num + "");
							break;
						case "Average_score":
							int call_num2 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();

								for (CisAgent agent : agents) {
									call_num2 += agent.getAverageScore();
								}

							}
							li.add(call_num2 / 10 + "");
							break;
						case "Average_call_duration":
							int call_num3 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();

								for (CisAgent agent : agents) {
									call_num3 += agent.getAverageCallDuration();
								}

							}
							li.add(call_num3 / 10 + "");
							break;
						case "Business_completed_number":
							int call_num4 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();

								for (CisAgent agent : agents) {
									call_num4 += agent.getBusinessCompletedNum();
								}
							}
							li.add(call_num4 + "");
							break;
						case "Speakspeed":
							int call_num5 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();

								for (CisAgent agent : agents) {
									call_num5 += agent.getSpeakSpeed();
								}

							}
							li.add(call_num5 / 10 + "");
							break;
						case "Complaint_number":
							int call_num6 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();

								for (CisAgent agent : agents) {
									call_num6 += agent.getComplaintNum();
								}
							}
							li.add(call_num6 + "");
							break;
						case "Illegal_operation_number":
							int call_num7 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(2).getCisAgents();

								for (CisAgent agent : agents) {
									call_num7 += agent.getIllegalOperationNum();
								}

							}
							li.add(call_num7 + "");
							break;
						default:
							break;
						}
						break;
					case "Department_four":
						switch (statisticalObject) {
						case "Call_number":
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

							}
							li.add(call_num + "");
							break;
						case "Average_score":
							int call_num2 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();

								for (CisAgent agent : agents) {
									call_num2 += agent.getAverageScore();
								}

							}
							li.add(call_num2 / 10 + "");
							break;
						case "Average_call_duration":
							int call_num3 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();

								for (CisAgent agent : agents) {
									call_num3 += agent.getAverageCallDuration();
								}

							}
							li.add(call_num3 / 10 + "");
							break;
						case "Business_completed_number":
							int call_num4 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();

								for (CisAgent agent : agents) {
									call_num4 += agent.getBusinessCompletedNum();
								}
							}
							li.add(call_num4 + "");
							break;
						case "Speakspeed":
							int call_num5 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();

								for (CisAgent agent : agents) {
									call_num5 += agent.getSpeakSpeed();
								}

							}
							li.add(call_num5 / 10 + "");
							break;
						case "Complaint_number":
							int call_num6 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();

								for (CisAgent agent : agents) {
									call_num6 += agent.getComplaintNum();
								}
							}
							li.add(call_num6 + "");
							break;
						case "Illegal_operation_number":
							int call_num7 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(3).getCisAgents();

								for (CisAgent agent : agents) {
									call_num7 += agent.getIllegalOperationNum();
								}

							}
							li.add(call_num7 + "");
							break;
						default:
							break;
						}
						break;
					case "Department_five":
						switch (statisticalObject) {
						case "Call_number":
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();

								for (CisAgent agent : agents) {
									call_num += agent.getErLang();
								}

							}
							li.add(call_num + "");
							break;
						case "Average_score":
							int call_num2 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();

								for (CisAgent agent : agents) {
									call_num2 += agent.getAverageScore();
								}

							}
							li.add(call_num2 / 10 + "");
							break;
						case "Average_call_duration":
							int call_num3 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();

								for (CisAgent agent : agents) {
									call_num3 += agent.getAverageCallDuration();
								}

							}
							li.add(call_num3 / 10 + "");
							break;
						case "Business_completed_number":
							int call_num4 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();

								for (CisAgent agent : agents) {
									call_num4 += agent.getBusinessCompletedNum();
								}
							}
							li.add(call_num4 + "");
							break;
						case "Speakspeed":
							int call_num5 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();

								for (CisAgent agent : agents) {
									call_num5 += agent.getSpeakSpeed();
								}

							}
							li.add(call_num5 / 10 + "");
							break;
						case "Complaint_number":
							int call_num6 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();

								for (CisAgent agent : agents) {
									call_num6 += agent.getComplaintNum();
								}
							}
							li.add(call_num6 + "");
							break;
						case "Illegal_operation_number":
							int call_num7 = 0;
							for (List<CisDepartment> departments : departments_list) {
								List<CisAgent> agents = departments.get(4).getCisAgents();

								for (CisAgent agent : agents) {
									call_num7 += agent.getIllegalOperationNum();
								}

							}
							li.add(call_num7 + "");
							break;
						default:
							break;
						}
						break;
					default:
						break;
					}
					break;
				case "Agent":

					break;

				default:
					break;
				}
				datas.put(legendName, li);
			}
		} else if (timeType.contains("day")) {
			int brforeDays = Integer.parseInt(btimes[0]);
			// int produce_hour =
			// Integer.parseInt(produceTime.split(",")[1].split(":")[0]);

			List<String> xaxis = new ArrayList<>();
			for (int i = 0; i < brforeDays; i++) {
				xaxis.add((i + 1) + "Day");
			}
			datas.put("xaxis", xaxis);

			int legendsSize = legends.length;
			String path = this.getClass().getClassLoader().getResource("").toString();
			List<List<CisDepartment>> departments_list = new ArrayList<>();
			for (int h = 1; h <= brforeDays; h++) {
				List<CisDepartment> departments = PraseFactory
						.prase(filePath + "data/day/2017-8-" + h + "/2017-8-" + h + ".json");
				departments_list.add(departments);
			}

			for (int i = 0; i < legendsSize; i++) {
				String legendName = legends[i];
				List<String> li = new ArrayList<>();

				switch (legendType) {
				case "index":
					switch (legendName) {
					case "Call_number":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Average_score":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getAverageScore();
									}

								}
								li.add(call_num / 10 + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageScore();
									}

								}
								li.add(call_num2 / 10 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageScore();
									}

								}
								li.add(call_num3 / 10 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageScore();
									}

								}
								li.add(call_num4 / 10 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageScore();
									}

								}
								li.add(call_num5 / 10 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}

						System.out.println("******************");
						break;
					case "Average_call_duration":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getAverageCallDuration();
									}

								}
								li.add(call_num / 10 + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getAverageCallDuration();
									}

								}
								li.add(call_num2 / 10 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getAverageCallDuration();
									}

								}
								li.add(call_num3 / 10 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getAverageCallDuration();
									}

								}
								li.add(call_num4 / 10 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getAverageCallDuration();
									}

								}
								li.add(call_num5 / 10 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Business_completed_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getBusinessCompletedNum();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Speakspeed":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getSpeakSpeed();
									}

								}
								li.add(call_num / 10 + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getSpeakSpeed();
									}

								}
								li.add(call_num2 / 10 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getSpeakSpeed();
									}

								}
								li.add(call_num3 / 10 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getSpeakSpeed();
									}

								}
								li.add(call_num4 / 10 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getSpeakSpeed();
									}

								}
								li.add(call_num5 / 10 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Complaint_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getComplaintNum();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getComplaintNum();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getComplaintNum();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getComplaintNum();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getComplaintNum();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Illegal_operation_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getComplaintNum();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getComplaintNum();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getComplaintNum();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getComplaintNum();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getComplaintNum();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					default:
						break;
					}

					break;
				case "Department":

					break;
				case "Agent":

					break;

				default:
					break;
				}
				datas.put(legendName, li);
			}
		} else {
			int brforeWeek = Integer.parseInt(btimes[0]) / 7;

			List<String> xaxis = new ArrayList<>();
			for (int i = 1; i <= brforeWeek; i++) {

				xaxis.add(i + "Week");

			}
			datas.put("xaxis", xaxis);

			int legendsSize = legends.length;
			String path = this.getClass().getClassLoader().getResource("").toString();
			List<List<CisDepartment>> departments_list = new ArrayList<>();
			for (int h = 1; h <= brforeWeek; h++) {
				List<CisDepartment> departments = PraseFactory
						.prase(filePath + "data/week/2017-8第" + h + "周/第" + h + "周.json");
				departments_list.add(departments);
			}

			for (int i = 0; i < legendsSize; i++) {
				String legendName = legends[i];
				List<String> li = new ArrayList<>();

				switch (legendType) {
				case "index":
					switch (legendName) {
					case "Call_number":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Average_score":
						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Average_call_duration":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Business_completed_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Speakspeed":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Complaint_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					case "Illegal_operation_number":

						if (statisticalObject.contains("Department")) {
							switch (statisticalObject) {
							case "Department_one":
								int call_num = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents = departments.get(0).getCisAgents();

									for (CisAgent agent : agents) {
										call_num += agent.getErLang();
									}

								}
								li.add(call_num + "");
								break;
							case "Department_two":
								int call_num2 = 0;
								for (List<CisDepartment> departments : departments_list) {
									List<CisAgent> agents2 = departments.get(1).getCisAgents();

									for (CisAgent agent : agents2) {
										call_num2 += agent.getErLang();
									}

								}
								li.add(call_num2 + "");
								break;
							case "Department_three":
								int call_num3 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents3 = departments.get(2).getCisAgents();

									for (CisAgent agent : agents3) {
										call_num3 += agent.getErLang();
									}

								}
								li.add(call_num3 + "");
								break;
							case "Department_four":
								int call_num4 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents4 = departments.get(3).getCisAgents();

									for (CisAgent agent : agents4) {
										call_num4 += agent.getErLang();
									}

								}
								li.add(call_num4 + "");
								break;
							case "Department_five":
								int call_num5 = 0;
								for (List<CisDepartment> departments : departments_list) {

									List<CisAgent> agents5 = departments.get(4).getCisAgents();

									for (CisAgent agent : agents5) {
										call_num5 += agent.getErLang();
									}

								}
								li.add(call_num5 + "");
								break;

							default:
								break;
							}
						} else if (statisticalObject.contains("Agent")) {
							int num = Integer.parseInt(statisticalObject.split("_")[1]);
							int group = (num % 10 == 0) ? num / 10 : (num / 10 + 1);
							int remain = num % 10;
							int call_num = 0;
							for (List<CisDepartment> departments : departments_list) {

								List<CisAgent> agents = departments.get(group - 1).getCisAgents();

								if (remain == 0) {
									call_num += agents.get(9).getErLang();

								} else {
									call_num += agents.get(remain - 1).getErLang();

								}

							}
							li.add(call_num + "");
						}
						System.out.println("******************");
						break;
					default:
						break;
					}

					break;
				case "Department":

					break;
				case "Agent":

					break;

				default:
					break;
				}
				datas.put(legendName, li);
			}
		}

		JSONObject jo = JSONObject.fromObject(datas);
		System.out.println(jo.toString());
		return jo.toString();

	}
}
