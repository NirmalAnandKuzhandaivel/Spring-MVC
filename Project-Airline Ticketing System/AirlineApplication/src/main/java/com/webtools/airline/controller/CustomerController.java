package com.webtools.airline.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.webtools.airline.Utility.Constants;
import com.webtools.airline.Utility.Utility;
import com.webtools.airline.dao.CustomerDAO;
import com.webtools.airline.model.ErrorBean;
import com.webtools.airline.model.FlightDetails;
import com.webtools.airline.model.Order;
import com.webtools.airline.model.Passenger;
import com.webtools.airline.model.ResponseWrapper;
import com.webtools.airline.model.Ticket;
import com.webtools.airline.model.User;
import com.webtools.airline.pdfView.PdfReportView;



@Controller
public class CustomerController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private Utility utility;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@RequestMapping(value="/TICKET.do")
	public String login(HttpServletRequest request) throws Exception{
		
		try{
			HttpSession session = request.getSession();
	        if(session.getAttribute("user")==null){
	            return "redirect:login.do";
	        }
			logger.info("Welcome! You're heading to the Ticket page");
			return "customer";
		}
		catch(Exception e){//General Exception handled by Spring global, for general exception, for user it will show the message set at 'handleAllException()' in GlobalExceptionController
			//Log the exception
			logger.error("Exception:", e);
			throw e;
		}
	}
	
	@RequestMapping(value="/CustomerDetails.do")
	public String customer(HttpServletRequest request) throws Exception{
		try{
			HttpSession session = request.getSession();
	        if(session.getAttribute("user")==null){
	            return "redirect:login.do";
	        }
			logger.info("Welcome! You're heading to the Ticket page");
			return "customerDetails";
		}
		catch(Exception e){//General Exception handled by Spring global, for general exception, for user it will show the message set at 'handleAllException()' in GlobalExceptionController
			//Log the exception
			logger.error("Exception:", e);
			throw e;
		}
	}
	
	@RequestMapping(value="/PROFILE_EXT.do")
	public String userProfile(HttpServletRequest request) throws Exception{
		try{
			HttpSession session = request.getSession();
	        if(session.getAttribute("user")==null){
	            return "redirect:login.do";
	        }
			logger.info("Welcome! You're heading to the Ticket page");
			return "userProfile";
		}
		catch(Exception e){//General Exception handled by Spring global, for general exception, for user it will show the message set at 'handleAllException()' in GlobalExceptionController
			//Log the exception
			logger.error("Exception:", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/ViewSchedules.do")
	public String history(@RequestParam(required=true,value="fly_from") String fromPlace,
						@RequestParam(required=true,value="fly_to") String toPlace,
						@RequestParam(required=true,value="departDate") String departDate,
						@RequestParam(required=false,value="returnDate") String returnDate,
						@RequestParam(required=true,value="noofPeople") String noofPeople,
						@RequestParam(required=true,value="trip") String trip, 
						@ModelAttribute("user") User user,
						HttpServletRequest request) {
		
		HttpSession session1 = request.getSession();
        if(session1.getAttribute("user")==null){
            return "redirect:login.do";
        }
		
		System.out.println(noofPeople);
		int peopleCount=Integer.parseInt(noofPeople);
		HttpSession session=request.getSession();
		session.setAttribute("fromPlace", fromPlace);
		session.setAttribute("toPlace", toPlace);
		session.setAttribute("departDate", departDate);
		session.setAttribute("returnDate", returnDate);
		session.setAttribute("noofPeople", peopleCount);
		session.setAttribute("trip", trip);
		
		return "departure";
	}
	
	@RequestMapping(value = "/ReturnSchedules.do")
	public String returnSchedule(
						@ModelAttribute("user") User user,
						HttpServletRequest request) {
		
		HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            return "redirect:login.do";
        }
		
		return "returnBooking";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/FinalPage.do")
	public String finalPage(
						Model model,
						HttpServletRequest request) {
		try {
			
			HttpSession session = request.getSession();
	        if(session.getAttribute("user")==null){
	            return "redirect:login.do";
	        }
		Order order=new Order();
		Ticket ticket1=new Ticket();
		Ticket ticket2=new Ticket();
		ticket1=(Ticket)request.getSession().getAttribute("departureTicket");
		ticket2=(Ticket)request.getSession().getAttribute("returnTicket");
		Set<Ticket> tickets = new HashSet<Ticket>();
		tickets.add(ticket1);
		if(ticket2!=null){
		tickets.add(ticket2);
		}
		
		List<Passenger> passengerList=new ArrayList<Passenger>();
		passengerList=(List<Passenger>)request.getSession().getAttribute("passengerList");
		int passengerCount=(Integer)request.getSession().getAttribute("noofPeople");
		if(ticket2!=null){
		order.setPrice(ticket1.getPrice()+ticket2.getPrice());
		}else{
			order.setPrice(ticket1.getPrice());
		}
		order.setNoOfPassengers(passengerCount);
		order.setUser((User)request.getSession().getAttribute("user"));
		
			customerDAO.saveOrder(ticket1,ticket2,passengerList,order);
			customerDAO.updateFlights(ticket1,ticket2);
			
			model.addAttribute("finalOrder", order);
			
			return "finalPage";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errorPage";
		}
		
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pdfTicketView.do")
	public ModelAndView pdfView(Model model,
			@RequestParam(required=true,value="orderid") String orderid,
						HttpServletRequest request) {
		int ordID = Integer.parseInt(orderid);
		Order o=customerDAO.getOrder(ordID);
		if(o!=null){
		View view = new PdfReportView();
        request.getSession().setAttribute("customerOrder", o);
        return new ModelAndView("pdfView", "customerOrder", o);
		}
		else{
			 return new ModelAndView("pdfViewError", "order", "No display");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/departureFlightsJson.do")
	public @ResponseBody List<ResponseWrapper> getDepartureFlights(@RequestParam(required=true,value="from_place") String fromPlace,
						@RequestParam(required=true,value="to_place") String toPlace,
						@RequestParam(required=true,value="depart_date") String departDate,
						HttpServletRequest request) {
		
		
		List<ResponseWrapper> departureflightList;
		try{
			
			logger.info("Request for Flight data");
			
			departureflightList = (List<ResponseWrapper>)(List<?>) customerDAO.getFlightTrips(fromPlace,toPlace,departDate);
			System.out.println(departureflightList.size());
			
			if(departureflightList.size() == 0){
				List<ErrorBean> error = new ArrayList<ErrorBean>();
				error.add(new ErrorBean(Constants.NODATA_ERROR_CODE, Constants.NODATA_ERROR_MSG_TO_DISPLAY));
				return (List<ResponseWrapper>)(List<?>) error;
			}else{
				return departureflightList;
			}
			} catch(Exception e){
				logger.error("Exception:", e);
				List<ErrorBean> error = new ArrayList<ErrorBean>();
				error.add(new ErrorBean("100", "No data available, contact to admin"));
				departureflightList = (List<ResponseWrapper>)(List<?>) error;
				return departureflightList;
			}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/TICKET_HISTORY.do")
	public  String getUserOrderHistory(Model model,
												HttpServletRequest request) {
		
		
		try{
			HttpSession session = request.getSession();
	        if(session.getAttribute("user")==null){
	            return "redirect:login.do";
	        }
			List<ResponseWrapper> orderList;
			logger.info("Request for Flight data");
			User user=(User)request.getSession().getAttribute("user");
			if(user!=null){
			orderList = (List<ResponseWrapper>)(List<?>) customerDAO.getOrderHistory(user);
			System.out.println(orderList.size());
			model.addAttribute("orderList",orderList);
			
			return "orderhistory";
			}else{
				return "orderError";
			}
		}
		catch(Exception ex){
			logger.error("Exception:", ex);
			ex.printStackTrace();
			return "orderError";
			
		}
		
		
		
	}
	
	@RequestMapping(value = "/saveDepartureSession.do",method=RequestMethod.GET)
	public @ResponseBody void saveDeparture(@RequestParam(required=true,value="flightID") String flightID,
  				
						HttpServletRequest request) {
		try{
			
		long travelhours=0;
		Ticket departureTicket=new Ticket();
		FlightDetails fd=customerDAO.getDepartureDetails(flightID);
		
		departureTicket.setFlightID(flightID);
		departureTicket.setEndDate(fd.getEndDate());
		departureTicket.setFromPlace(fd.getFromPlace());
		departureTicket.setToPlace(fd.getToPlace());
		departureTicket.setStartTime(fd.getStartTime());
		departureTicket.setEndTime(fd.getEndTime());
		departureTicket.setStartDate(fd.getStartDate());
		departureTicket.setFlightCompany(fd.getFlightCompany());
		departureTicket.setPrice(fd.getPrice() * (Integer)request.getSession().getAttribute("noofPeople"));
		departureTicket.setTicketType("DEPARTURE");
		travelhours=utility.getTravelHours(fd.getStartDate(),fd.getEndDate(),fd.getStartTime(),fd.getEndTime());
		System.out.println(fd.getStartTime());
		System.out.println(fd.getEndTime());		
		request.getSession().setAttribute("departureTicket", departureTicket);
		System.out.println(departureTicket);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/returnFlightsJson.do")
	public @ResponseBody List<ResponseWrapper> getreturnFlights(@RequestParam(required=true,value="from_place") String fromPlace,
						@RequestParam(required=true,value="to_place") String toPlace,
						@RequestParam(required=true,value="return_date") String returndate,
						HttpServletRequest request) {
		
		
		try{
			List<ResponseWrapper> returnflightList;
			logger.info("Request for Flight data");
			
			returnflightList = (List<ResponseWrapper>)(List<?>) customerDAO.getFlightTrips(fromPlace,toPlace,returndate);
			
			System.out.println(returnflightList.size());
			return returnflightList;
		}
		catch(Exception ex){
			logger.error("Exception:", ex);
			ex.printStackTrace();
		}
		return null;
		
		
	}
	
	@RequestMapping(value = "/saveReturnSession.do",method=RequestMethod.GET)
	public @ResponseBody void saveReturn(@RequestParam(required=true,value="flightID") String flightID,
  				
						HttpServletRequest request) {
		
		Ticket returnTicket=new Ticket();
		FlightDetails fd=customerDAO.getDepartureDetails(flightID);
		returnTicket.setFlightID(flightID);
		returnTicket.setFlightCompany(fd.getFlightCompany());
		returnTicket.setEndDate(fd.getEndDate());
		returnTicket.setFromPlace(fd.getFromPlace());
		returnTicket.setToPlace(fd.getToPlace());
		returnTicket.setStartTime(fd.getStartTime());
		returnTicket.setEndTime(fd.getEndTime());
		returnTicket.setStartDate(fd.getStartDate());
		returnTicket.setPrice(fd.getPrice() * (Integer)request.getSession().getAttribute("noofPeople"));
		returnTicket.setTicketType("RETURN");
		request.getSession().setAttribute("returnTicket", returnTicket);
		System.out.println(returnTicket);
		
	
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getTicketsForOrder.do", method = RequestMethod.GET)
	public @ResponseBody List<ResponseWrapper> getTicketforOrder(
			@RequestParam(required = true, value = "orderID") String orderID, HttpServletRequest request) {

		int ordID = Integer.parseInt(orderID);
		List<ResponseWrapper> ticketList;
		try {
			
			logger.info("Request for Order data");
			System.out.println(ordID);

			ticketList = (List<ResponseWrapper>) (List<?>) customerDAO.getTickets(ordID);

			if(ticketList.size() == 0){
				List<ErrorBean> error = new ArrayList<ErrorBean>();
				error.add(new ErrorBean(Constants.NODATA_ERROR_CODE, Constants.NODATA_ERROR_MSG_TO_DISPLAY));
				return (List<ResponseWrapper>)(List<?>) error;
			}else{
				return ticketList;
			}
			} catch(Exception e){
				logger.error("Exception:", e);
				List<ErrorBean> error = new ArrayList<ErrorBean>();
				error.add(new ErrorBean("100", "No data available, contact to admin"));
				ticketList = (List<ResponseWrapper>)(List<?>) error;
				return ticketList;
			}

	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getPassengerForOrder.do", method = RequestMethod.GET)
	public  @ResponseBody List<ResponseWrapper> getPassengerforOrder(
			@RequestParam(required = true, value = "orderID") String orderID, HttpServletRequest request) {

		int ordID = Integer.parseInt(orderID);
		List<ResponseWrapper> passengerList;

		try {
			
			logger.info("Request for Order data");

			passengerList = (List<ResponseWrapper>) (List<?>) customerDAO.getPassengers(ordID);

			if(passengerList.size() == 0){
				List<ErrorBean> error = new ArrayList<ErrorBean>();
				error.add(new ErrorBean(Constants.NODATA_ERROR_CODE, Constants.NODATA_ERROR_MSG_TO_DISPLAY));
				return (List<ResponseWrapper>)(List<?>) error;
			}else{
				return passengerList;
			}
			} catch(Exception e){
				logger.error("Exception:", e);
				List<ErrorBean> error = new ArrayList<ErrorBean>();
				error.add(new ErrorBean("100", "No data available, contact to admin"));
				passengerList = (List<ResponseWrapper>)(List<?>) error;
				return passengerList;
			}

	}
	
	@RequestMapping(value = "/PaymentDetails.do",method=RequestMethod.POST)
	public String paymentDetails( 				
						HttpServletRequest request) {
		HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            return "redirect:login.do";
        }
		int count=(Integer) request.getSession().getAttribute("noofPeople");
		
		ArrayList<Passenger> passengerList=new ArrayList<Passenger>();
        for(int i=1;i<=count;i++){
        	String firstName=null,lastName=null,proofDetails=null;
        	int age=0;
            
            float price=0;
            Enumeration<String> passengerDetails=request.getParameterNames();
            while(passengerDetails.hasMoreElements()){
                String paramName=passengerDetails.nextElement();
                if(paramName.contains(String.valueOf(i))){
                    if(paramName.contains("firstName"))
                    	firstName=request.getParameter(paramName);
                    if(paramName.contains("lastName"))
                    	lastName=request.getParameter(paramName);
                    if(paramName.contains("proofDetails"))
                    	proofDetails=request.getParameter(paramName);
                    if(paramName.contains("age"))
                        age=Integer.parseInt(request.getParameter(paramName));   
                }
                
            }                
            Passenger  p=new Passenger();
            p.setAge(age);
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setIdProof(proofDetails);
            passengerList.add(p);
            
          } 
        
        request.getSession().setAttribute("passengerList", passengerList);
		return "confirmation";
	
	}
	
		
}
