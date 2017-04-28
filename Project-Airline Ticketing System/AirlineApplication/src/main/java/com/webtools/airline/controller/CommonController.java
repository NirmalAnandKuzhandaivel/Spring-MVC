package com.webtools.airline.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.webtools.airline.Utility.Constants;
import com.webtools.airline.Utility.Utility;
import com.webtools.airline.dao.MessageDAO;
import com.webtools.airline.model.ErrorBean;
import com.webtools.airline.model.ResponseWrapper;
import com.webtools.airline.model.SuccessBean;
import com.webtools.airline.model.User;



@Controller
public class CommonController {
	
	
	
	@Autowired
	private Utility utility;
	
	@Autowired
	private MessageDAO messageDAO;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/contactSupportMail.do",method=RequestMethod.GET)
	public @ResponseBody List<ResponseWrapper> contactSupportMail(@ModelAttribute("model") ModelMap model, 
			@RequestParam(required=false,value="currentUrl") String currentUrl,
			@RequestParam(required=true,value="mailSubject") String mailSubject,
			@RequestParam(required=true,value="mailBody") String mailBody,
			HttpServletRequest request, HttpServletResponse response){
		try{	
					
			utility.sendEmail(currentUrl, mailSubject, mailBody,request);
			User user=(User)request.getSession().getAttribute("user");
			messageDAO.saveMessagetoDB(user.getEmailID(),mailSubject,mailBody);
			
			SuccessBean successBean = new SuccessBean(Constants.MAILSENDING_SUCCESS_CODE, Constants.MAILSENDING_SUCCESS_MSG_TO_DISPLAY);
			List<SuccessBean> successBeanList = new ArrayList<SuccessBean>();
			successBeanList.add(successBean);
			return (List<ResponseWrapper>)(List<?>) successBeanList;		
			
		}
		
		catch(RuntimeException e){
			logger.error("Exception:", e);
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(Constants.MAILSENDING_FAILURE_CODE, Constants.MAILSENDING_FAILURE_MSG_TO_DISPLAY));
			return (List<ResponseWrapper>)(List<?>) error;
		}
		catch(Exception e){//General Exception handled by Spring global, for general exception, for user it will show the message set at 'handleAllException()' in GlobalExceptionController
			//Log the exception
			logger.error("Exception:", e);
			List<ErrorBean> error = new ArrayList<ErrorBean>();
			error.add(new ErrorBean(Constants.MAILSENDING_FAILURE_CODE, Constants.MAILSENDING_FAILURE_MSG_TO_DISPLAY));
			return (List<ResponseWrapper>)(List<?>) error;
		}
	}
	
	
	
	
	

}
