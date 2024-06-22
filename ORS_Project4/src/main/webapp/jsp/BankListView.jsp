

<%@page import="com.rays.pro4.controller.BankListCtl"%>
<%@page import="com.rays.pro4.Bean.BankBean"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />

<title>bank List</title>
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
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.BankBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>


	<form action="<%=ORSView.BANK_LIST_CTL%>" method="post">

		<center>

			<div align="center">
				<h1>Bank List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>

			<%
				int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
				List jlist = (List) request.getAttribute("jlist");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<BankBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<table width="100%" align="center">
				 <tr>
					<th></th>
					<%-- <td align="center"><label>Bank Name</font> :
					</label> <input type="text" name="bankName"
						placeholder="Enter bank name"
						value="<%=ServletUtility.getParameter("bankName", request)%>"> --%>
					
					<td align="center"><label>AccountNo</font> :
					</label> <input type="text" name="accountNo"
						placeholder="Enter account no"
						value="<%=ServletUtility.getParameter("accountNo", request)%>">
						
					 <td align="center"><label>Amount</font> :
					</label> <input type="text" name="amount"
						placeholder="Enter Amount"
						value="<%=ServletUtility.getParameter("amount", request)%>">

						 <td align="center"><label>Account Holder</font> :
					</label> <input type="text" name="accountHolder"
						placeholder="Enter accountHolder"
						value="<%=ServletUtility.getParameter("accountHolder", request)%>">
						
						
						 &emsp; <label>Bank Name</font> :
					</label> <%=HTMLUtility.getList("bankName", String.valueOf(bean.getBankName()), jlist)%>
						&nbsp; 
						
						 <input type="submit" name="operation"
						value="<%=BankListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation"
						value="<%=BankListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>s

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>Bank Name</th>
					<th>Account Number</th>
					<th>Amount</th>
					<th>Account Holder</th>
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
					<td><%=bean.getBankName()%></td>
					<td><%=bean.getAccountNo()%></td>
					<td><%=bean.getAmount()%></td>
					<td><%=bean.getAccountHolder()%></td>
					<td><a href="BankCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						value="<%=BankListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=BankListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=BankListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=BankListCtl.OP_NEW%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=BankListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>



				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=BankListCtl.OP_BACK%>"></td>
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