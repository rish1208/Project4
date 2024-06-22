<%@page import="com.rays.pro4.controller.BankCtl"%>
<%@page import="com.rays.pro4.controller.JobRequirementCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Bank Page</title>

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
			yearRange : '1900:2024',
		//dateFormat:'yy-mm-dd'
		});
	});
</script>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.BankBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>

		<form action="<%=ORSView.BANK_CTL%>" method="post">

			<%-- <%
				List list = (List) request.getAttribute("bList");
			%> --%>


			<div align="center">
				<h1>

					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update bank </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add bank</font></th>
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
					<th align="left">AccountNo: <span style="color: red">*</span>

					</th>
					<td><input type="text" name="accountNo" placeholder="Enter accountNo"
						size="25" value="<%=DataUtility.getStringData(bean.getAccountNo())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("accountNo", request)%></font></td>
                      
                      
                      
                      
				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>


                <tr>
                    <th align="left" > Bank Name <span style="color: red">*</span> :</th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("SBI", "SBI");
                            map.put("UBI", "UBI");
                            map.put("PNB", "PNB");


                            String hlist = HTMLUtility.getList("bankName", String.valueOf(bean.getBankName()), map);
    						%> <%=hlist%>
                   </td>
                   <td style="position: fixed" ><font color="red"> <%=ServletUtility.getErrorMessage("bankName", request)%></font></td>
                 </tr>
    <tr><th style="padding: 3px"></th></tr>          
                 
				<tr>
					<th align="left">Amount<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="amount" placeholder="Enter amount"
						
						value="<%=DataUtility.getStringData(bean.getAmount())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("amount", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>
				<tr>

					<th align="left">AccountHolder: <span style="color: red">*</span>

					</th>
					<td><textarea rows="3" cols="24" name="accountHolder"
							placeholder="Enter accountholder" size="25"><%=DataUtility.getStringData(bean.getAccountHolder())%></textarea>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("accountHolder", request)%></font></td>

				</tr>

				<tr>
					<%
						if (bean.getId() > 0) {
					%>

					&nbsp;
					<td align="right" colspan="2">&nbsp; &emsp; <input
						type="submit" name="operation"
						value="<%=BankCtl.OP_UPDATE%>"> &nbsp; &nbsp; <input
						type="submit" name="operation"
						value="<%=BankCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>

					&nbsp;&nbsp;
					<td align="right" scolspan="2">&nbsp; &emsp; <input
						type="submit" name="operation"
						value="<%=BankCtl.OP_SAVE%>"> &nbsp; &nbsp; <input
						type="submit" name="operation"
						value="<%=BankCtl.OP_RESET%>"></td>

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