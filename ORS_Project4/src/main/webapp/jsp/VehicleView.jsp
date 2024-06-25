
<%@page import="com.rays.pro4.controller.VehicleCtl"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<script src="<%=ORSView.APP_CONTEXT%>/js/ValidateToInput.js"></script>
<title>Vehicle Page</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udatee").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2020',
		});
	});
</script>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.VehicleTrackingBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>

		<form action="<%=ORSView.VEHICLE_CTL%>" method="post">

			<div align="center">
				<h1>
					<%
						List rlist = (List) request.getAttribute("rlist");
					%>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update Vehicle </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add Vehicle </font></th>
					</tr>
					<%
						}
					%>
				</h1>

				<h3>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>



			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>

				<tr>
					<th align="left">Date<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="Date" placeholder="Enter Date "
						size="25" id="udatee" readonly="readonly"
						value="<%=DataUtility.getDateString(bean.getDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("Date", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>

				<tr>
					<th align="left">Long Id<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="LongId"
						placeholder="Enter loginid" size="25"
						value="<%=DataUtility.getStringFromDouble(bean.getLongId())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("LongId", request)%></font></td>

				</tr>

				<tr>
					<th style="padding: 1px"></th>
				</tr>



				<tr>
					<th align="left">Lat<span style="color: red">*</span> :
					</th>
					<td><input type="text" name=Lat style="width: 195px"
						placeholder="Enter  Lat"
						value="<%=DataUtility.getStringFromDouble(bean.getLat())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Lat", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Vehicle Id<span style="color: red">*</span> :
					</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Car", "Car");
							map.put("bike", "bike");
							map.put("train", "train");

							String hlist = HTMLUtility.getList("VehicleId", String.valueOf(bean.getVehicleId()), map);
						%> <%=hlist%>
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("VehicleId", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 3px"></th>
				</tr>


				<%-- <tr>
					<th style="padding: 1px"></th>
				</tr>
				<tr>
					<th align="left">Experties<span style="color: red">*</span>
						:
					</th>
					<td><%=HTMLUtility.getList("Experties", String.valueOf(bean.getExperties()), rlist)%></td>
					<td style="position: fixed"><font style="position: fixed"
						color="red"> <%=ServletUtility.getErrorMessage("Experties", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 3px"></th>
				</tr>  --%>
				<%-- <tr>
					<th align="left">Experties<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="Experties"
						placeholder="Enter  Experties" size="25"
						value="<%=DataUtility.getStringData(bean.getExperties())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Experties", request)%></font></td>
				</tr> --%>




				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=VehicleCtl.OP_UPDATE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=VehicleCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>

					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=VehicleCtl.OP_SAVE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=VehicleCtl.OP_RESET%>"></td>

					<%
						}
					%>
				</tr>
			</table>
		</form>
	</center>

	<%@ include file="Footer.jsp"%>
</body>
</html>
