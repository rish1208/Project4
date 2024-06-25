package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.VehicleTrackingBean;
import com.rays.pro4.Model.VehicleTrackingModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "VehicleCtl", urlPatterns = { "/ctl/VehicleCtl" })
public class VehicleCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("Date"))) {
			request.setAttribute("Date", PropertyReader.getValue("error.require", "Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Date"))) {
			request.setAttribute("Date", PropertyReader.getValue("error.date", "Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("VehicleId"))) {
			request.setAttribute("VehicleId", PropertyReader.getValue("error.require", "VehicleId"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("VehicleId"))) {
			request.setAttribute("VehicleId", "LongId  must contains alphabet only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Lat"))) {
			request.setAttribute("Lat", PropertyReader.getValue("error.require", "Lat"));
			pass = false;
		} else if (!DataValidator.isDouble(request.getParameter("Lat"))) {
			request.setAttribute("Lat", "Lat  must be contain No");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("LongId"))) {
			request.setAttribute("LongId", PropertyReader.getValue("error.require", "LongId"));
			pass = false;
		}else if (!DataValidator.isDouble(request.getParameter("LongId"))) {
			request.setAttribute("LongId", "LongId  must be contain No");
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		VehicleTrackingBean bean = new VehicleTrackingBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDate(DataUtility.getDate(request.getParameter("Date")));
		bean.setVehicleId(DataUtility.getString(request.getParameter("VehicleId")));
		bean.setLat(DataUtility.getDouble(request.getParameter("Lat")));
		bean.setLongId(DataUtility.getDouble(request.getParameter("LongId")));
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		VehicleTrackingModel model = new VehicleTrackingModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("vehicle Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			VehicleTrackingBean bean;

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

		VehicleTrackingModel model = new VehicleTrackingModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.VEHICLE_LIST_CTL, request, response);

		}
		if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.VEHICLE_CTL, request, response);

		}

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			VehicleTrackingBean bean = (VehicleTrackingBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("vehicle is successfully Updated", request);
					ServletUtility.forward(getView(), request, response);
				} catch (Exception e) {
					System.out.println("vehicle not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setSuccessMessage("vehicle is successfully Added", request);

					bean.setId(pk);
					ServletUtility.forward(getView(), request, response);
				} catch (Exception e) {
					System.out.println("vehicle not added");
					e.printStackTrace();
				}

			}

		}
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.VECHILE_VIEW;
	}

}
