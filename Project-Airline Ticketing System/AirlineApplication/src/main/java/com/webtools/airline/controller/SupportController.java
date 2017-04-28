package com.webtools.airline.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.webtools.airline.model.MessageBean;
import com.webtools.airline.model.ResponseWrapper;
import com.webtools.airline.model.SuccessBean;
import com.webtools.airline.model.User;

@Controller
@RequestMapping(value="/support")
public class SupportController {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private Utility utility;

	private static final Logger logger = LoggerFactory.getLogger(SupportController.class);
	
	@RequestMapping(value="/SupportDetails.do")
	public String contactSupportMail(@ModelAttribute("model") ModelMap model, 
			
			HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            return "redirect:../login.do";
        }
		return "support";
	}
	

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/viewMessageAsJson.do")
	public @ResponseBody List<ResponseWrapper> getMessageJson(@ModelAttribute("model") ModelMap model, 
			HttpServletRequest request){
		try{
			List<ResponseWrapper> userList;
			logger.info("Request for Message data");
			
			userList = (List<ResponseWrapper>)(List<?>) messageDAO.getMessages();
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
	@RequestMapping(value="/replyRequest.do",method=RequestMethod.GET)
	public @ResponseBody List<ResponseWrapper> contactSupportMail(@ModelAttribute("model") ModelMap model, 
			@RequestParam(required=true,value="message_id") int message_id,
			@RequestParam(required=true,value="msg_txt") String messageBody,
			HttpServletRequest request, HttpServletResponse response){
		try{	
			System.out.println("function called");	
			MessageBean msgBean=messageDAO.getMessage(message_id);
			User user=(User)request.getSession().getAttribute("user");
			String subject="Response for" + msgBean.getReferenceID();
			utility.sendReplyMail(user.getEmailID(),msgBean.getUserEmail(),subject,messageBody);
			
			messageDAO.saveReplytoDB(user.getEmailID(),msgBean.getReferenceID(),subject,messageBody);
			messageDAO.update(msgBean);

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
