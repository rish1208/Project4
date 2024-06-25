<%@page import="com.rays.pro4.controller.RouteCtl"%>
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
<title>Lead Page</title>

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
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.RouteBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>

		<form action="<%=ORSView.ROUTE_CTL%>" method="post">

			<div align="center">
				<h1>
					<%
						List rlist = (List) request.getAttribute("rlist");
					%>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Route Lead </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add Route </font></th>
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
					<th align="left">number<span style="color: red">*</span>
						:
					</th>
					<td><input type="number" name="number"
						placeholder="Enter number" size="25" 
						value="<%=DataUtility.getString(bean.getNumber())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("number", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>
				
				<tr>
					<th align="left">purchasedate<span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="purchasedate"
						placeholder="Enter purchasedate" size="25" id="udatee" readonly="readonly"
						value="<%=DataUtility.getDateString(bean.getPurchasedate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("purchasedate", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>
				<tr>
					<th align="left">Mobile<span style="color: red">*</span> :
					</th>
					<td><input type="number" name="Mobile" maxlength="10" style="width: 195px"
						placeholder="Enter  Mobile" 
						value="<%=DataUtility.getStringData(bean.getMobile())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Mobile", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>
				
				<tr>
					<th align="left">insuranceAmount<span style="color: red">*</span>
						:
					</th>
					<td><input type="number" name="insuranceAmount"
						placeholder="Enter insuranceAmount" size="25" 
						value="<%=DataUtility.getStringData(bean.getInsuranceAmount())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("insuranceAmount", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>
				
				
<tr>
					<th align="left">colour<span style="color: red">*</span> :
					</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Red", "Red");
							map.put("yellow", "Yellow");
							map.put("Pink", "Pink");

							String hlist = HTMLUtility.getList("colour", String.valueOf(bean.getClass()), map);
						%> <%=hlist%>
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("colour", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 3px"></th>
				</tr>

				
				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=RouteCtl.OP_UPDATE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=RouteCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>

					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=RouteCtl.OP_SAVE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=RouteCtl.OP_RESET%>"></td>

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
