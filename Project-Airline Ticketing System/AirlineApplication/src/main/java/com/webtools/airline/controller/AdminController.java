package com.webtools.airline.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webtools.airline.model.ResponseWrapper;
import com.webtools.airline.model.SuccessBean;

import com.webtools.airline.validator.LoginValidator;

import com.webtools.airline.Exception.AirlineGenericException;
import com.webtools.airline.Utility.Constants;
import com.webtools.airline.dao.AdminDAO;
import com.webtools.airline.model.ErrorBean;
import com.webtools.airline.model.FlightDetails;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	protected static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminDAO adminDAO;
	
	/*@Autowired
	@Qualifier("flightDetailsValidator")
	FlightDetailsValidator flightDetailsValidator;
	
	@InitBinder("flightDetails")
	private void initUserBinder(WebDataBinder binder) {
		binder.setValidator(flightDetailsValidator);
		
	}*/
	
	@RequestMapping(value="/addFlightDetails.do")
	public  String  addFlight(@ModelAttribute("flightDetails") FlightDetails flight,
			HttpServletRequest request, @ModelAttribute("model") ModelMap model2,BindingResult result) throws Exception{
	
		HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            return "redirect:../login.do";
        }
		
		return "FlightDetails";
	}
	
	
	@RequestMapping(value="/CUSTOMER.do")
	public String customer(HttpServletRequest request) throws Exception{
		try{
			HttpSession session = request.getSession();
	        if(session.getAttribute("user")==null){
	            return "redirect:../login.do";
	        }
			logger.info("Welcome! You're heading to the Ticket page");
			return "viewCustomers";
		}
		catch(Exception e){//General Exception handled by Spring global, for general exception, for user it will show the message set at 'handleAllException()' in GlobalExceptionController
			//Log the exception
			logger.error("Exception:", e);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/saveFlightDetails.do")
	public  @ResponseBody List<ResponseWrapper> saveFlight(@ModelAttribute("flightDetails") FlightDetails flight,
			HttpServletRequest request,@ModelAttribute("model") ModelMap model2) throws Exception{
		
		
		
		try{
			System.out.println(flight.getFlightCompany());
		Integer status=adminDAO.saveUser(flight);
		model2.addAttribute("view", "flightDetails");
		if (status != 0) {
			
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(Constants.INVALID_FIELDNAME_ERROR_CODE,	Constants.INVALID_FIELDNAME_ERROR_MSG_TO_DISPLAY));
			return (List<ResponseWrapper>) (List<?>) error;
		} else {
			List<SuccessBean> success = new ArrayList<SuccessBean>();
			success.add(new SuccessBean(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG));
			return (List<ResponseWrapper>) (List<?>) success;
		}
		}
		catch(AirlineGenericException ex){
			logger.error("Exception:", ex);
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(ex.getErrCode(), ex.getErrMsg()));
			return (List<ResponseWrapper>)(List<?>) error;
		}
		catch(Exception ex){
			logger.error("Exception:", ex);
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(Constants.SYSTEM_ERROR_CODE, Constants.SYSTEM_ERROR_MSG_TO_DISPLAY));
			return (List<ResponseWrapper>)(List<?>) error;
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/FlightsJson.do")
	public @ResponseBody List<ResponseWrapper> getFTPJson(@ModelAttribute("model") ModelMap model, 
			HttpServletRequest request){
		try{
			List<ResponseWrapper> flightList;
			logger.info("Request for Flight data");
			
			flightList = (List<ResponseWrapper>)(List<?>) adminDAO.getFlightTrips();
			System.out.println(flightList.size());
			
			return flightList;
		}
		catch(Exception ex){
			logger.error("Exception:", ex);
			ex.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/viewUsersJson.do")
	public @ResponseBody List<ResponseWrapper> getUSERJson(@ModelAttribute("model") ModelMap model, 
			HttpServletRequest request){
		try{
			List<ResponseWrapper> userList;
			logger.info("Request for Flight data");
			
			userList = (List<ResponseWrapper>)(List<?>) adminDAO.getUserList();
			System.out.println(userList.size());
			
			return userList;
		}
		catch(Exception ex){
			logger.error("Exception:", ex);
			ex.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteRecord.do", method = RequestMethod.GET)
	public  @ResponseBody List<ResponseWrapper> deleteRecord(
			@RequestParam(required = true, value = "id") String id, HttpServletRequest request) {

		int ordID = Integer.parseInt(id);
		try {
			
			logger.info("Request to Delete Data");
			int status = adminDAO.deleteRecord(ordID);
			System.out.println(status);
			if (status != 0) {
				
				List<ErrorBean> error = new ArrayList<ErrorBean>();
				error.add(new ErrorBean(Constants.INVALID_FIELDNAME_ERROR_CODE,	Constants.INVALID_FIELDNAME_ERROR_MSG_TO_DISPLAY_INS));
				return (List<ResponseWrapper>) (List<?>) error;
			} else {
				List<SuccessBean> success = new ArrayList<SuccessBean>();
				success.add(new SuccessBean(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG));
				return (List<ResponseWrapper>) (List<?>) success;
			}
			
		} catch(AirlineGenericException ex){
			logger.error("Exception:", ex);
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(ex.getErrCode(), ex.getErrMsg()));
			return (List<ResponseWrapper>)(List<?>) error;
		}
		catch(Exception ex){
			logger.error("Exception:", ex);
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(Constants.SYSTEM_ERROR_CODE, Constants.SYSTEM_ERROR_MSG_TO_DISPLAY));
			return (List<ResponseWrapper>)(List<?>) error;
		}

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteFlightRecord.do", method = RequestMethod.GET)
	public  @ResponseBody List<ResponseWrapper> deleteFlightRecord(
			@RequestParam(required = true, value = "flightID") String id, HttpServletRequest request) {

		int flightID = Integer.parseInt(id);
		try {
			
			logger.info("Request to Delete Data");
			int status = adminDAO.deleteFlightRecord(flightID);
			System.out.println(status);
			System.out.println(status);
			if (status != 0) {
				
				List<ErrorBean> error = new ArrayList<ErrorBean>();
				error.add(new ErrorBean(Constants.INVALID_FIELDNAME_ERROR_CODE,	Constants.INVALID_FIELDNAME_ERROR_MSG_TO_DISPLAY_INS));
				return (List<ResponseWrapper>) (List<?>) error;
			} else {
				List<SuccessBean> success = new ArrayList<SuccessBean>();
				success.add(new SuccessBean(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG));
				return (List<ResponseWrapper>) (List<?>) success;
			}
			
		} catch(AirlineGenericException ex){
			logger.error("Exception:", ex);
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(ex.getErrCode(), ex.getErrMsg()));
			return (List<ResponseWrapper>)(List<?>) error;
		}
		catch(Exception ex){
			logger.error("Exception:", ex);
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(Constants.SYSTEM_ERROR_CODE, Constants.SYSTEM_ERROR_MSG_TO_DISPLAY));
			return (List<ResponseWrapper>)(List<?>) error;
		}

	}

}
