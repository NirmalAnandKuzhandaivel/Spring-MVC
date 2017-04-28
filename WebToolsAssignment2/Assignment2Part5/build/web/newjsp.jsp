<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script>  
 $(function() {     
 $( "#datepicker" ).datepicker();   });   
 </script>
    </head>
    <body>
        <div class="row">  
            <div class="col-md-4" id="c1">  
                <img src="Capture.PNG" alt="Responsive image" class="img-rounded" style="paddingtop:10px">  
            </div>    
            <div class="col-md-4" id="c2">  </div>  
            <div class="col-md-4" id="c3">  </div> 
        </div>  
        <div class="first"> Claim Form PLEASE INCLUDE YOUR PET'S MEDICAL RECORDS TO HELP EXPEDITE PROCESSING. </div> 
        <div class=row> 
            <div class="col-md-1" style="height:30px;border:1px solid #000;backgroundcolor:black;color:white">1</div> 
            <div class="col-md-11"  style="background-color:lightgrey">General Information Please fill out this form completely. Incomplete forms will delay processing.</div> 
        </div> 
        <div class=row style="height:30px"> 
            <div class="col-md-6" style="background:lightblue; border:1px solid #000"><b>Personal Information</b></div> 
            <div class="col-md-6" style="background:lightblue; border:1px solid #000"><b>Pet Information</b></div>
        </div>
        <form action="newjsp.jsp" method="POST"> 
            <div class=row >  
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">   
                    <div class="col-md-2"><b>Name:</b></div>   
                    <input type="text" name="personName" placeholder="Person Name">   
                </div>  
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">  
                    <div class="col-md-2"><b>Acct No :</b></div>
                    <input type="text"  id="accountNo" name="accountNo" placeholder="Account No">  
                </div> 
            </div> 
            <div class=row >  
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">   
                    <div class="col-md-2"><b>Address: </b></div>  
                    <input type="text" name="personAddress" placeholder="Person Address"> 
                </div>   
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">  
                    <div class="col-md-2"><b>Pet Name :</b></div>    
                    <input type="text"  id="petName" name="petName" placeholder="Pet Name"> 
                </div> 
            </div> 
            <div class=row >  
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">  
                    <div class="col-md-2"><b>City,State,Zip </b></div>  
                    <input type="text" name="cityState" placeholder="City,State,Zip"> 
                </div>  
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">  
                    <div class="col-md-2"><b>Breed :</b></div>   
                    <input type="text"  id="accountNo" name="breed" placeholder="Breed"> 
                </div>
            </div>
            <div class=row >   
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">   
                    <div class="col-md-6">  
                        <div class="col-md-4"><b>Phone</b></div>   
                        <input type="text" name="phone" placeholder="Phone">   
                    </div> 
                    <div class="col-md-6">   
                        <div class="col-md-4"><b>Email</b></div>   
                        <input type="text" name="email" placeholder="Email">   
                    </div>  
                </div>  
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">   
                    <div class="col-md-6"> 
                        <div class="col-md-4"><b>Age</b></div>  
                        <input type="text" name="age" placeholder="Age">  
                    </div>    
                    <div class="col-md-6">   
                        <div class="col-md-4"><b>Gender</b></div>  
                        <input type="text" name="gender" placeholder="Gender">  
                    </div> 
                </div>
            </div>
            <div class=row>  
                <div class="col-md-1" style="height:30px;border:1px solid #000;backgroundcolor:black;color:white">2</div> 
                <div class="col-md-11"style="background-color:lightgrey">Diagnosis/Symptom Information</div>
            </div> 
            <div class=row>  
                <textarea rows='3' style="margin: 0px; width: 1375px; height: 104px; border:1px solid #000" name='comments'></textarea>
            </div>  
            <div class=row >   
                <div class="col-md-6" style="background:lightblue; border:1px solid #000">   
                    <b>Claim related to<b>   
                            <input type="checkbox" name="claim" value="Accident">Accident   
                            <input type="checkbox" name="claim" value="Illness">Illness    
                            <input type="checkbox" name="claim" value="Wellness">Wellness   
                            </div>   
                            <div class="col-md-6" style="background:lightblue; border:1px solid #000">    
                                <b>Veterinian :</b>    
                                <input type="text"  id="Veterinian" name="veterinian" placeholder="Veterinian">   
                            </div> 
                            </div>  
                <div class=row >   
                                <div class="col-md-6" style="background:lightblue; border:1px solid #000"> 

                                    <div class="col-md-2"><b>Illness Date<b></div>  
                                         <input type="text" name="datepick" id="datepicker">      
                                         </div> 
                                         <div class="col-md-6" style="background:lightblue; border:1px solid #000">   
                                         <div class="col-md-2"><b>Phone :</b></div>  
                                     <input type="text"  id="phone" name="phoneNo" placeholder="Phone No">   
                                </div> 
                            </div> 
                        <input type="submit" value="Submit" /> 
                </form> 

         <jsp:scriptlet>
            if (request.getMethod().equalsIgnoreCase("post")) {
                String first = request.getParameter("personName");
                String last = request.getParameter("accountNo");
                String cityState = request.getParameter("cityState");
                String phone=request.getParameter("phone");
                String email=request.getParameter("email"); 
                String petName=request.getParameter("petName");
                String breed=request.getParameter("breed");
                String gender=request.getParameter("gender");
                String age=request.getParameter("age") ;
                String comments=request.getParameter("comments") ;
                String claim=request.getParameter("claim"); 
                String vet=request.getParameter("veterinian");
                String datepick=request.getParameter("datepick");
                String phoneNo=request.getParameter("phoneNo");        
           </jsp:scriptlet>


            Person Name <jsp:expression>first</jsp:expression> 
            <br>
            Account No <jsp:expression>last</jsp:expression>
             <br>
            City State <jsp:expression>cityState</jsp:expression> 
             <br>
            Phone <jsp:expression>phone</jsp:expression>
             <br>
            Email<jsp:expression>email</jsp:expression> 
            <br>
            Pet Name <jsp:expression>petName</jsp:expression>
            <br>
            Breed <jsp:expression>breed</jsp:expression> 
            <br>
            Gender <jsp:expression>gender</jsp:expression>
            <br>
            Age <jsp:expression>age</jsp:expression>
            <br>
            Comments<jsp:expression>comments</jsp:expression> 
            <br>
            Claim <jsp:expression>claim</jsp:expression>
            <br>
            Vetnarian <jsp:expression>vet</jsp:expression> 
            <br>
            Date Pick <jsp:expression>datepick</jsp:expression> 
            <br>
            Phone No  <jsp:expression>phoneNo</jsp:expression>
            <br>
          

         <jsp:scriptlet>
            }   //this closes if block
</jsp:scriptlet>
           </body>
      </html>
