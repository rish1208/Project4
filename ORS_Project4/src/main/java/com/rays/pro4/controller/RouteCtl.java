package com.rays.pro4.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.RouteBean;
import com.rays.pro4.Model.RouteModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "RouteCtl", urlPatterns = { "/ctl/RouteCtl" })
public class RouteCtl extends BaseCtl {
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("purchasedate"))) {
			request.setAttribute("purchasedate", PropertyReader.getValue("error.require", "purchasedate"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("purchasedate"))) {
			request.setAttribute("purchasedate", PropertyReader.getValue("error.date", "purchasedate"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("number"))) {
			request.setAttribute("number", PropertyReader.getValue("error.require", "number"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("number"))) {
			request.setAttribute("number", "number  must contains no only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Mobile"))) {
			request.setAttribute("Mobile", PropertyReader.getValue("error.require", "Mobile"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("Mobile"))) {
			request.setAttribute("Mobile", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}if (DataValidator.isNull(request.getParameter("insuranceAmount"))) {
			request.setAttribute("insuranceAmount", PropertyReader.getValue("error.require", "insuranceAmount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("colour"))) {
			request.setAttribute("colour", PropertyReader.getValue("error.require", "colour"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		RouteBean bean = new RouteBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setPurchasedate(DataUtility.getDate(request.getParameter("purchasedate")));
		bean.setMobile(DataUtility.getString(request.getParameter("Mobile")));
		bean.setInsuranceAmount(DataUtility.getInt(request.getParameter("insuranceAmount")));
		bean.setNumber(DataUtility.getString(request.getParameter("number")));
		bean.setColour(DataUtility.getString(request.getParameter("colour")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		RouteModel model = new RouteModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("product Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			RouteBean bean;

			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("uctl Do Post");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);

		RouteModel model = new RouteModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ROUTE_LIST_CTL, request, response);

		}
		if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ROUTE_CTL, request, response);

		}

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			RouteBean bean = (RouteBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Route is successfully Updated", request);
					ServletUtility.forward(getView(), request, response);
				} catch (Exception e) {
					System.out.println("Route not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setSuccessMessage("Route is successfully Added", request);

					bean.setId(pk);
					ServletUtility.forward(getView(), request, response);
				} catch (Exception e) {
					System.out.println("route not added");
					e.printStackTrace();
				}

			}

		}
	}


	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROUTE_VIEW;
	}

}
