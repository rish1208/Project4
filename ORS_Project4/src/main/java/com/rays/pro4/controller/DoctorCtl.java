package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.DoctorBean;

import com.rays.pro4.Model.DoctorModel;

import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "orderCtl", urlPatterns = { "/ctl/orderCtl" })
public class DoctorCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {
		// TODO Auto-generated method stub
		DoctorModel model = new DoctorModel();

		try {

			List dList = model.list();
			request.setAttribute("dList", dList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("dctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", " name must contains alphabet only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "dob"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			System.out.println("else perform ");
			request.setAttribute("dob", PropertyReader.getValue("error.date", "dob"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "mobile"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("experties"))) {
			request.setAttribute("experties", PropertyReader.getValue("error.require", "experties"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("experties"))) {
			request.setAttribute("experties", "experties name must contains alphabet only");
			pass = false;
		}
		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		DoctorBean bean = new DoctorBean();
		System.out.println("populateBean iiiddd>>>>> " + request.getParameter("id"));

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		System.out.println("populateBean jjjj" + bean.getId());

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setMobile(DataUtility.getString(request.getParameter("mobile")));

		bean.setExperties(DataUtility.getString(request.getParameter("experties")));

		bean.setId(DataUtility.getLong(request.getParameter("dList")));
		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("d ctl do get 1111111");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));
		DoctorModel model = new DoctorModel();
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			DoctorBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		System.out.println("Doc  Ctl do get");
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);
		DoctorModel model = new DoctorModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			DoctorBean bean = (DoctorBean) populateBean(request);
			bean.setId(id);
			System.out.println("operation = " + op);

			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("order is successfully Updated", request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setSuccessMessage("order is successfully Added", request);

					bean.setId(pk);

				}
			} catch (Exception e) {
				System.out.println("order not added");

				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {

		return ORSView.DOCTOR_VIEW;
	}

}
