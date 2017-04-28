package com.webtools.airline.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.webtools.airline.dao.HomeDAO;
import com.webtools.airline.model.FlightDetails;
import com.webtools.airline.model.LoginBean;
import com.webtools.airline.model.User;
import com.webtools.airline.validator.LoginValidator;
import com.webtools.airline.validator.UserValidator;



/**
 * Handles requests for the application home page.
 */
@Controller
/*@RequestMapping(value="/customer")*/
public class HomeController {
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@Autowired
	ServletContext servletContext;
	
	
	
	@Autowired
	@Qualifier("loginValidator")
	LoginValidator logvalidator;
	
	
	@Autowired
	HomeDAO homeDAO;
	
	@InitBinder("user")
	private void initUserBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		
	}
	
	@InitBinder("loginBean")
	private void initLoginBinder(WebDataBinder binder) {
		binder.setValidator(logvalidator);
		
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @ModelAttribute("user") User user) {
		
		return "register";
	}
	
	@RequestMapping(value = "/logout.do")
	public void logout(Locale locale, Model model, @ModelAttribute("user") User user,HttpServletRequest request,HttpServletResponse response) {
		
		request.getSession().invalidate();
		try {
			response.sendRedirect(request.getContextPath() + "/login.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/signUp.do",method = RequestMethod.POST)
	public  String   doSubmitAction(@ModelAttribute("user") User user,
			HttpServletRequest request,BindingResult result) throws Exception{
		
		validator.validate(user, result);
		if (result.hasErrors()) {
		return "register";			
		}
		try{
			System.out.println("nirmal");
			User u=homeDAO.getUserEmail(user.getEmailID());
			if(u!=null){
				return "registrationError";
			}
			int status=homeDAO.saveUser(user);
			System.out.println("controller" + status);
			return "successPage";
		}
		catch(Exception e){//General Exception handled by Spring global, for general exception, for user it will show the message set at 'handleAllException()' in GlobalExceptionController
			e.printStackTrace();
			logger.error("Exception:", e);
			throw e;
		}
		
	}
	
	@RequestMapping(value="/updateProfile.do",method = RequestMethod.POST)
	public String   updateUser(@RequestParam(required=true,value="uploadFile") MultipartFile photoFile,
			@RequestParam(required=true,value="firstName") String firstName,
			@RequestParam(required=true,value="lastName") String lastName,
			@RequestParam(required=true,value="emailID") String emailID,				
			HttpServletRequest request) throws Exception{
		
		System.out.println("Controlled called");
		File file;
		String check = File.separator;
		String path = null;
		if (check.equalsIgnoreCase("\\")) {
			path = servletContext.getRealPath("").replace("build\\", "");
		}
		if (check.equalsIgnoreCase("/")) {
			path = servletContext.getRealPath("").replace("build/", "");
			System.out.println("path1"+path );
			path += "/resources/images/";
			System.out.println("path2"+path );
		}
		
		path+="resources\\images\\";

		System.out.println("path"+path);
		if (photoFile != null) {
			String fileNameWithExt = System.currentTimeMillis() + photoFile.getOriginalFilename();

			file = new File(path + fileNameWithExt);
			
			String context = servletContext.getContextPath();
			System.out.println(file);
			photoFile.transferTo(file);
			User user = (User) request.getSession().getAttribute("user");
			String photoName = context + "/resources/images/" + fileNameWithExt;
			homeDAO.updateUser(user,firstName,lastName,emailID,photoName);
			
		}
		return "userProfile";
		
	}
	

	
	@RequestMapping(value="/home.do")
	public  String  loginUser(@RequestParam("username") String username,
			@RequestParam("pass") String password, @ModelAttribute("user") User user,
			@ModelAttribute("modelAttribute") LoginBean login,HttpServletRequest request,
			HttpServletResponse response,
			BindingResult result)
			
	{
		
		logvalidator.validate(login, result);
		if (result.hasErrors()) {
		return "register";			
		}	
		
		
		
		ModelAndView model= null;
			HomeDAO homeDAO=new HomeDAO();
			try
			{
					boolean isValidUser = homeDAO.isValidUser(username,password);
					System.out.println(isValidUser);
					if(isValidUser)
					{
						Cookie userCookie = new Cookie("userName", username);
			            Cookie pwdCookie = new Cookie("password", password);
			            response.addCookie(userCookie);
			            response.addCookie(pwdCookie);
						    User loggedInUser=homeDAO.getUser(username, password);
							System.out.println("User Login Successful");
							request.getSession().setAttribute("user", loggedInUser);
							request.getSession().setAttribute("name", loggedInUser.getFirstName());
							if(loggedInUser.getRoleType().equalsIgnoreCase("admin")){
								request.getSession().setAttribute("IsAdmin", true);
							}
							if(loggedInUser.getRoleType().equalsIgnoreCase("support")){
								request.getSession().setAttribute("IsSupport", true);
							}
							
							if(loggedInUser.getRoleType().equalsIgnoreCase("CUSTOMER")){
								request.getSession().setAttribute("IsCustomer", true);
							}
							System.out.println(request.getSession().getAttribute("IsCustomer"));
							return "customer";
					}
					else
					{
						return "error";	
					}

			}
			catch(Exception e)
			{
					e.printStackTrace();
					return "error";
			}

			
	}
	

	@RequestMapping(value = "/VIEW_TICKET.do", method = RequestMethod.GET)
	public String history(Locale locale, Model model, @ModelAttribute("user") User user) {
    
        
		return "history";
	}

	
	
	
}
