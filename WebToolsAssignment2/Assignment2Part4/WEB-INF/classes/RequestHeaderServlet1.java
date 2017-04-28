import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.io.IOException;

public class RequestHeaderServlet1 extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		
		String personName=request.getParameter("personName");
		String personAddress=request.getParameter("personAddress");
		String cityState=request.getParameter("cityState");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String accontNo=request.getParameter("accontNo");
		String petName=request.getParameter("petName");
		String breed=request.getParameter("breed");
		String gender=request.getParameter("gender");
		String age=request.getParameter("age");
		
		String comments=request.getParameter("comments");
		String claim=request.getParameter("claim");
		String veterinian=request.getParameter("veterinian");
		String datepick=request.getParameter("datepick");
		String phoneNo=request.getParameter("phoneNo");
		
		personName=personName.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
        personAddress=personAddress.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
		cityState=cityState.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
        phone=phone.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
		email=email.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
        accontNo=accontNo.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
		petName=petName.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
        breed=breed.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
		PrintWriter out=response.getWriter();
		
		if(personName==null || personAddress==null ||cityState==null || phone==null||email==null || accontNo==null||accontNo==""||
		petName==null || breed==null || gender==null ||age==null || comments==null||claim==null || veterinian==null||
		datepick==null || phoneNo==null){
			out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error PAge</title>");            
            out.println("</head>");
			out.println("<body>");
			out.println("<p> Enter all the fields Some fields are null and not valid");
			out.println("</body>");
		   out.println("</html>"); 
		}
		else{
		gender=gender.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
        age=age.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
		comments=comments.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
        claim=claim.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
		
        veterinian=veterinian.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
		datepick=datepick.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
        phoneNo=phoneNo.replaceAll("[^\\dA-za-z ]", "").replaceAll("\\s+", "+").trim();
			
		
		
		
				
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Displaying Response Headers</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<b>Person Details</b></br>");
		out.println("PersonName:" + personName + "</br>");
		out.println("Address:" + personAddress + "</br>");
		out.println("City:" + cityState + "</br>");
		out.println("Phone:" + phone + "</br>");
	    out.println("Email:" + email + "</br>");
		out.println("<b>Pet Details</b></br>");
		out.println("AcctNo:" + accontNo + "</br>");
		out.println("PetName:" + petName + "</br>");
		out.println("Breed:" + breed + "</br>");
		out.println("Gender:" + gender + "</br>");
	    out.println("Age:" + age + "</br>");
		out.println("<b>Diagnosis</b></br>");
		out.println("Symptoms:" + comments + "</br>");
		out.println("<b>Claim Details</b></br>");
		out.println("Claim Record:" + claim + "</br>");
		out.println("Veterinian:" + veterinian + "</br>");
		out.println("Illnes:" + datepick + "</br>");
	    out.println("Phone:" + phoneNo + "</br>");
		out.println("</body>");
		out.println("</html>"); 
		}		
	}	
}