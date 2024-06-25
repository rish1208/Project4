<%@page import="com.rays.pro4.Bean.VehicleTrackingBean"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.controller.VehicleListCtl"%>
<%@page import="com.rays.pro4.Bean.LeadBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
	<script src="<%=ORSView.APP_CONTEXT%>/js/ValidateToInput.js"></script>

<title>Lead List</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2002',
		//  mindefaultDate : "01-01-1962"
		});
	});
</script>

</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.VehicleTrackingBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>


	<form action="<%=ORSView.VEHICLE_LIST_CTL%>" method="post">

		<center>

			<div align="center">
				<h1>Vehicle List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>

			<%
				int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
				List prolist = (List) request.getAttribute("prolist");
				List vlist = (List) request.getAttribute("vlist");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<VehicleTrackingBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<table width="100%" align="center">
				<tr>
					<th></th>
					<td align="center"><label>Date</font> :
					</label> <input type="text" name="Date" id="udate"
						placeholder="Enter Date" readonly="readonly"
						value="<%=ServletUtility.getParameter("Date", request)%>">
						
					<td align="center"><label>Lat</font> :
					</label> <input type="No" name="Lat" placeholder="Lat Name"
						value="<%=ServletUtility.getParameter("Lat", request)%>">
						
						
					<%-- <td align="center"><label>LongId</font> :
					</label> <input type="number" name="LongId" placeholder="Enter Long"
						value="<%=ServletUtility.getParameter("LongId", request)%>">
 --%>
 
						<%-- <td align="center"><label>Experties</font> :
					</label> <input type="text" name="Experties" placeholder="Enter Experties"
						value="<%=ServletUtility.getParameter("Experties", request)%>"> --%>
						
				 	<%-- <td><select style='width: 203px; height: 23px;'
						class='form-control' name="Experties">
							<option style='width: 203px; height: 30px;' selected value=''>--------------Select---------------------</option>
							
				 		
							
							<% 
							Iterator<DoctarBean> it1 = proList.iterator();
								while (it1.hasNext()) {
										bean = it1.next();
							%>
							<option selected value='" + <%=bean.getExperties()%> + "'>
								<%=bean.getExperties()%>
							</option>
							<option value="<%=bean.getExperties()%>">
								<%=bean.getExperties()%>
							</option>
							<%
								}
							%> 
							
							 


					</select></td>--%>
					&emsp; <label>Vehicle Id</font> :
					</label> <%=HTMLUtility.getList("VehicleId", String.valueOf(bean.getVehicleId()), prolist)%>
					
					&emsp; <label>Long Id</font> :
					</label> <%=HTMLUtility.getList("LongId", String.valueOf(bean.getLongId()), vlist)%>
					
					
					<input type="submit" name="operation"
						value="<%=VehicleListCtl.OP_SEARCH%>"> &nbsp;
					<input type="submit" name="operation"
						value="<%=VehicleListCtl.OP_RESET%>">
					</td>

				</tr>
			</table>
			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: Pink">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>Date</th>
					<th>Vehicle Id</th>
					<th>Lat</th>
					<th>Long</th>
					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = it.next();
				%>


				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getDate()%></td>
					<td><%=bean.getVehicleId()%></td>
					<td><%=bean.getLat()%></td>
					<td><%=bean.getLongId()%></td>
					<td><a href="VehicleCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table width="100%">
				<tr>
					<th></th>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=VehicleListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=VehicleListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=VehicleListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=VehicleListCtl.OP_NEW%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=VehicleListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>



				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=VehicleListCtl.OP_BACK%>"></td>
			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>

	</center>

	<%@include file="Footer.jsp"%>
</body>
</html>
