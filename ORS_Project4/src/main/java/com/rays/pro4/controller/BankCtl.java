package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Model.BankModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "BankCtl", urlPatterns = { "/ctl/BankCtl" })
public class BankCtl extends BaseCtl {
	@Override
	protected void preload(HttpServletRequest request) {
		// TODO Auto-generated method stub
		BankModel model = new BankModel();

		try {

			List bList = model.list();
			request.setAttribute("bList", bList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("bankName"))) {
			request.setAttribute("bankName", PropertyReader.getValue("error.require", "bankName"));
			pass = false;
		}
		else if (!DataValidator.isName(request.getParameter("bankName"))) {
			request.setAttribute("bankName","bankName name must contains alphabet only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("accountNo"))) {
			request.setAttribute("accountNo", PropertyReader.getValue("error.require", "accountNo"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("accountNo"))) {
			request.setAttribute("accountNo","accountNo name must contains Number only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "amount"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("amount"))) {
			request.setAttribute("amount","amount name must contains Number only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("accountHolder"))) {
			request.setAttribute("accountHolder", PropertyReader.getValue("error.require", "accountHolder"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("accountHolder"))) {
			request.setAttribute("accountHolder","accountHolder name must contains alphabet only");
			pass = false;
		}
		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		BankBean bean = new BankBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setBankName(DataUtility.getString(request.getParameter("bankName")));

		bean.setAccountNo(DataUtility.getString(request.getParameter("accountNo")));

		bean.setAmount(DataUtility.getString(request.getParameter("amount")));

		bean.setAccountHolder(DataUtility.getString(request.getParameter("accountHolder")));

		bean.setId(DataUtility.getLong(request.getParameter("bList")));
		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BankModel model = new BankModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("product Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			BankBean bean;

			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
				/*
				 * System.out.println("productCatagory name =====> " +
				 * bean.getProductCatagory()); System.out.println("product name ===== > " +
				 * bean.getProductName());
				 */

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("uctl Do Post");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);

		BankModel model = new BankModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			BankBean bean = (BankBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("bank is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("product not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("product is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("product not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.BANK_VIEW;
	}

}

