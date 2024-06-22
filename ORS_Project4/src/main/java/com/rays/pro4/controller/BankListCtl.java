package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Model.BankModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "BankListCtl", urlPatterns = { "/ctl/BankListCtl" })
public class BankListCtl extends BaseCtl{
	

	protected void preload(HttpServletRequest request) {

		BankModel model = new BankModel();
		
		
		try {
			List 	clist = model.list();
			request.setAttribute("jlist",clist);
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#populateBean(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		BankBean bean = new BankBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setBankName(DataUtility.getString(request.getParameter("bankName")));

		bean.setAccountNo(DataUtility.getString(request.getParameter("accountNo")));
		
		bean.setAmount(DataUtility.getString(request.getParameter("amount")));

		bean.setAccountHolder(DataUtility.getString(request.getParameter("accountHolder")));

		//bean.setId(DataUtility.getLong(request.getParameter("accountNo")));

		return bean;
	}

 	/**
	 * Contains Display logics.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		List list = null;
		List nextList = null;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		BankBean bean = (BankBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));

//	        get the selected checkbox ids array for delete list

		// String[] ids = request.getParameterValues("ids");

	BankModel model = new BankModel();

		
		
				try {
					list = model.search(bean, pageNo, pageSize);
				
			
			System.out.println("list" + list);

			nextList = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlist", nextList.size());

			ServletUtility.setList(list, request);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			// ServletUtility.setBean(bean, request);
			ServletUtility.forward(getView(), request, response);
		
	} catch (Exception e) {
		ServletUtility.handleException(e, request, response);
		return;
	}
	}
	
	
	

		
		
	

	/**
	 * Contains Submit logics.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List list;
		List nextList = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));

		BankBean bean = (BankBean) populateBean(request);

		// get the selected checkbox ids array for delete list

		String[] ids = request.getParameterValues("ids");

		BankModel model = new BankModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.BANK_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.BANK_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				BankBean deletebean = new BankBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (Exception e) {
						
						ServletUtility.handleException(e, request, response);
						return;
					}
					ServletUtility.setSuccessMessage("bank is Deleted Successfully", request);
				}
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		}
	

			try {
				list = model.search(bean, pageNo, pageSize);
				System.out.println("list" + list);
				nextList = model.search(bean, pageNo + 1, pageSize);
				request.setAttribute("nextlist", nextList.size());

				ServletUtility.setList(list, request);

				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found", request);
				}

				ServletUtility.setList(list, request);
				ServletUtility.setPageNo(pageNo, request);
				ServletUtility.setPageSize(pageSize, request);
				// ServletUtility.setBean(bean, request);

			} catch (Exception e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
			
			request.setAttribute("nextlist", nextList.size());

		
		if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
			ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setBean(bean, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.BANK_LIST_VIEW;
	}

}