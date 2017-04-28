package com.webtools.airline.Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import static java.lang.Math.abs;


import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.springframework.stereotype.Component;



@Component
public class Utility {

	public String convertDate(String source) throws Exception{
		 String date=source;
	        String converted_date=""; 

	            converted_date+=date.substring(date.lastIndexOf('/')+1,date.length()); 
	            converted_date+="-"+date.substring(0,date.indexOf('/')); 
	            converted_date+="-"+date.substring(date.indexOf('/')+1,date.lastIndexOf('/')); 
	            System.out.println(converted_date);
	            SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
	            Date dat=sf.parse(converted_date);
	            return sf.format(dat);
	}
	
	public long getTravelHours(Date startDate,Date endDate,String startTime,String endTime) throws Exception{
		
		System.out.println(startDate);
		System.out.println(endDate);
		DateTime dt1 = new DateTime(startDate);
		DateTime dt2 = new DateTime(endDate);
		long diff1 = endDate.getTime() - startDate.getTime();
		System.out.println("Hour Diff2"+diff1);
		int hour1diff=(Hours.hoursBetween(dt1,dt2).getHours()/24)*24;
		System.out.println("Hour Diff"+hour1diff);
		System.out.println("Joda hours" + (Hours.hoursBetween(dt1,dt2).getHours()/24)*24);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mma");
        Date date1=sdf.parse(startTime);
        Date date2=sdf.parse(endTime);
        long diff2=date2.getTime()-date1.getTime();
        long hour2diff = abs(diff2 / (60 * 60 * 1000) % 24);
        return  hour1diff+hour2diff;
		
		
		
		
	}
	
	public  void sendEmail(String currentUrl, String mailSubject, String mailBody, HttpServletRequest request) throws Exception{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		System.out.println("funticon called");
		
		  final String USERNAME = "nimi.hbk@gmail.com"; 
		  final String PASSWORD = "Summer@123";
		  String toEmailAddress = "nirmalanand.k@gmail.com";	
		  
		  
		  Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		 properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.port", "587");

		properties.put("mail.smtp.socketFactory.port", "465");
		 properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 properties.put("mail.smtp.port", "465");
		  
		boolean isSSL=true;
          /******* For SSL *******************/
         
		 if(isSSL)
       	  properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 Session session = Session.getInstance(properties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USERNAME, PASSWORD);
					}
				  });
		  
		  try {
			  
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(USERNAME));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmailAddress));
				message.setSubject(mailSubject);
				
				/*MimeBodyPart attachmentPart = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource(logFile) {
					@Override
					    public String getContentType() {
							return "application/octet-stream";
					    }
				};
				attachmentPart.setDataHandler(new DataHandler(fileDataSource));
				attachmentPart.setFileName(fileDataSource.getName());*/
				
			    
			    String footer = "";
			    footer = "URL : " + currentUrl;
			    footer += "<br/>Time : " + dateFormat.format(date);
			    
			    BodyPart messageBodyFooter1 = new MimeBodyPart();  
			    messageBodyFooter1.setContent(footer, "text/html");

			    String userString = "";
			    String userid = (String)request.getSession().getAttribute("name");
			    String username = (String)request.getSession().getAttribute("name");
			    if(null!= userid && !userid.equals("")){
			    	userString = "UserID:"+userid;
			    	userString += "<br>Username:"+username;
			    } else {
			    	userString = "UserID: Anonymous";
			    	userString += "UserID: Anonymous";
			    }
			    
			    mailBody += "<br>"+userString+"<br>"+footer;
			  //create MimeBodyPart object and set your message text     
			    BodyPart messageBodyPart = new MimeBodyPart();  
			    messageBodyPart.setContent(mailBody, "text/html");		
			    
				Multipart multipart = new MimeMultipart(); 
				multipart.addBodyPart(messageBodyPart);
//				multipart.addBodyPart(messageBodyFooter1);
			    //multipart.addBodyPart(attachmentPart); 
			    
			    message.setContent(multipart);
	 
				Transport.send(message);
	 
			} catch (MessagingException e) {
				//e.printStackTrace();
				throw new RuntimeException(e);				
			}catch (Exception e) {
				//e.printStackTrace();
				throw new Exception(e);				
			}
	}
	
	public  void sendReplyMail(String fromEmail,String toEmail,String subject,String messageBody) throws Exception{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		System.out.println("funticon called");
		
		  final String USERNAME = fromEmail;
		  final String PASSWORD = "Summer@123";
		  String toEmailAddress = toEmail;
		  
		  
		  Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		 properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.port", "587");

		properties.put("mail.smtp.socketFactory.port", "465");
		 properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 properties.put("mail.smtp.port", "465");
		  
		boolean isSSL=true;
          /******* For SSL *******************/
         
		 if(isSSL)
       	  properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 Session session = Session.getInstance(properties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USERNAME, PASSWORD);
					}
				  });
		  
		  try {
			  
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(USERNAME));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmailAddress));
				message.setSubject(subject);
				
				/*MimeBodyPart attachmentPart = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource(logFile) {
					@Override
					    public String getContentType() {
							return "application/octet-stream";
					    }
				};
				attachmentPart.setDataHandler(new DataHandler(fileDataSource));
				attachmentPart.setFileName(fileDataSource.getName());*/
				
			    
			  
			    
			  
			  //create MimeBodyPart object and set your message text     
			    BodyPart messageBodyPart = new MimeBodyPart();  
			    messageBodyPart.setContent(messageBody, "text/html");		
			    
				Multipart multipart = new MimeMultipart(); 
				multipart.addBodyPart(messageBodyPart);
//				multipart.addBodyPart(messageBodyFooter1);
			    //multipart.addBodyPart(attachmentPart); 
			    
			    message.setContent(multipart);
	 
				Transport.send(message);
	 
			} catch (MessagingException e) {
				//e.printStackTrace();
				throw new RuntimeException(e);				
			}catch (Exception e) {
				//e.printStackTrace();
				throw new Exception(e);				
			}
	}
	
}
