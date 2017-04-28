/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultipleForms;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nimi
 */
public class MultipleForm extends HttpServlet {

    String answer1;
   String answer2;
   String answer3;
   String answer4;
   String answer5;
  
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        sendPage1(response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");
       
        
       // answer1 = request.getParameter("question1");
      //  answer2 = request.getParameter("question2");
       System.out.println(page);
       //System.out.println(answer1);
        // System.out.println(answer2);
        
        if (page == null) {
            sendPage1(response);
            return;
        }
        if (page.equals("1")) {
            if (request.getParameter("question1") == null ) {
                sendPage1(response);
            } else {
                answer1=request.getParameter("question1");
                request.getSession().setAttribute("ans1", answer1);
                sendPage2(response);
            }
        } else if (page.equals("2")) {
           
            if (answer1 == null || request.getParameter("question2") == null) {
                sendPage1(response);
            } else {
                 answer2=request.getParameter("question2");
                request.getSession().setAttribute("ans2", answer2);
                sendPage3(response);
            }
        }else if (page.equals("3")) {
           
            if (answer1 == null || answer2 ==null || request.getParameter("question3") == null) {
                sendPage1(response);
            } else {
                 answer3=request.getParameter("question3");
                request.getSession().setAttribute("ans3", answer3);
                 sendPage4(response);
            }
        }else if (page.equals("4")) {
           
            if (answer1 == null || answer2 ==null ||answer3 == null || request.getParameter("question4") == null) {
                sendPage1(response);
            } else {
                 answer4=request.getParameter("question4");
                request.getSession().setAttribute("ans4", answer4);
                 sendPage5(response);
            }
        }else if (page.equals("5")) {
           
            if (answer1 == null || answer2 ==null ||answer3 == null ||answer4 == null|| 
                    request.getParameter("question5") == null) {
                sendPage1(response);
            } else {
                 answer5=request.getParameter("question5");
                request.getSession().setAttribute("ans5", answer5);
                 displayValues(response,request);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendPage1(HttpServletResponse response) throws IOException {
   response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MultipleForm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='POST'>");
            out.println("<INPUT TYPE=HIDDEN NAME=page VALUE=1>");
             out.println("Indias First President is Narasimha rao");
            out.println("<input type='radio' name='question1' value='True' checked> True<br>");
            out.println("<input type='radio' name='question1' value='False'> False<br>");
            out.println("<TD><INPUT TYPE=SUBMIT VALUE=Submit></TD>");
            out.println("</form> ");
            out.println("</body>");
            out.println("</html>");
        } }

    private void sendPage2(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MultipleForm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='POST'>");
            out.println("<INPUT TYPE=HIDDEN NAME=page VALUE=2>");
            out.println("America's First President is Bill Clinton");
            out.println("<input type='radio' name='question2' value='True' checked> True<br>");
            out.println("<input type='radio' name='question2' value='False'> False<br>");
            out.println("<TD><INPUT TYPE=SUBMIT VALUE=Submit></TD>");
            out.println("</form> ");
            out.println("</body>");
            out.println("</html>");
        } }
    
     private void sendPage3(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MultipleForm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='POST'>");
            out.println("<INPUT TYPE=HIDDEN NAME=page VALUE=3>");
            out.println("What is the captial of India");
            out.println("<input type='radio' name='question3' value='Delhi'> Delhi<br>");
            out.println("<input type='radio' name='question3' value='Mumbai'> Mumbai<br>");
            out.println("<input type='radio' name='question3' value='Calcutta' checked> Calcutta<br>");
            out.println("<input type='radio' name='question3' value='Chennai'> Chennai<br>");
            out.println("<TD><INPUT TYPE=SUBMIT VALUE=Submit></TD>");
            out.println("</form> ");
            out.println("</body>");
            out.println("</html>");
        } }
     
     private void sendPage4(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MultipleForm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='POST'>");
            out.println("<INPUT TYPE=HIDDEN NAME=page VALUE=4");
            out.println("Who Invented Flight");
            out.println("<input type='radio' name='question4' value='LehmanBrothers'> LehmanBrothers<br>");
            out.println("<input type='radio' name='question4' value='Wright Brothers'> Wright Brothers<br>");
            out.println("<input type='radio' name='question4' value='Bryan Brothers' > Bryan Brothers<br>");
            out.println("<input type='radio' name='question4' value='Diana Brothers'> Diana Brothers<br>");
            out.println("<TD><INPUT TYPE=SUBMIT VALUE=Submit></TD>");
            out.println("</form> ");
            out.println("</body>");
            out.println("</html>");
        } }
       private void sendPage5(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MultipleForm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='POST'>");
            out.println("<INPUT TYPE=HIDDEN NAME=page VALUE=5>");
            out.println("National Sport of England");
            out.println("<input type='radio' name='question5' value='Football'> Football<br>");
            out.println("<input type='radio' name='question5' value='Basketball'> Basketball <br>");
            out.println("<input type='radio' name='question5' value='Tennis' >Tennis<br>");
            out.println("<input type='radio' name='question5' value='Cricket'> Cricket<br>");
            out.println("<TD><INPUT TYPE=SUBMIT VALUE=Submit></TD>");
            out.println("</form> ");
            out.println("</body>");
            out.println("</html>");
        } }
    
    

    private void displayValues(HttpServletResponse response,HttpServletRequest request) throws IOException {
       PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Page 3</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<CENTER>");
        out.println("<H2>Finish Quiz)</H2>");
        out.println("<BR>");
        out.println("<BR>");
        out.println("Here are the values you have entered.");
        out.println("<BR>");
        out.println("<BR>");
        out.println("<TABLE>");
        out.println("<TR>");
        out.println("<TD>Question 1: &nbsp;</TD>");
        out.println("<TD>" + request.getSession().getAttribute("ans1")+ "</TD>");
        out.println("</TR>");
        out.println("<TR>");
        out.println("<TD>Question 2: &nbsp;</TD>");
        out.println("<TD>" + request.getSession().getAttribute("ans2") + "</TD>");
        out.println("</TR>");
        
        out.println("<TR>");
        out.println("<TD>Question 3: &nbsp;</TD>");
        out.println("<TD>" + request.getSession().getAttribute("ans3") + "</TD>");
        out.println("</TR>");
        
        out.println("<TR>");
        out.println("<TD>Question 4: &nbsp;</TD>");
        out.println("<TD>" + request.getSession().getAttribute("ans4") + "</TD>");
        out.println("</TR>");
        out.println("<TR>");
        out.println("<TD>Question 5: &nbsp;</TD>");
        out.println("<TD>" + request.getSession().getAttribute("ans5") + "</TD>");
        out.println("</TR>");
        
        out.println("</TABLE>");
        out.println("</CENTER>");
        out.println("</BODY>");
        out.println("</HTML>");
        
    
    }

}
