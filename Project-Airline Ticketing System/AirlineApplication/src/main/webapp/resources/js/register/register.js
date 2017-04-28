$(document).ready(function() {
	$('.form').find('input, textarea').on('keyup blur focus', function(e) {

		var $this = $(this), label = $this.prev('label');

		if (e.type === 'keyup') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.addClass('active highlight');
			}
		} else if (e.type === 'blur') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.removeClass('highlight');
			}
		} else if (e.type === 'focus') {

			if ($this.val() === '') {
				label.removeClass('highlight');
			} else if ($this.val() !== '') {
				label.addClass('highlight');
			}
		}

	});

	$('.tab a').on('click', function(e) {

		e.preventDefault();

		$(this).parent().addClass('active');
		$(this).parent().siblings().removeClass('active');

		target = $(this).attr('href');

		$('.tab-content > div').not(target).hide();

		$(target).fadeIn(600);

	});
	
	function alertHandling(alert, msg) {
		alert.text(msg);
		alert.css("display", "inherit").fadeOut(2000);
	}
/*	
	$("#signUpform").submit(function(e){
		 e.preventDefault();
		alert("nirmal");		
		$.ajax({
            url: "signUp.do",
            type: "POST",  
            data: $(this).serialize(),            
            success: function(data){
            	alert(data);
	    		if(data == 0){
            		$('#errorSection').hide();
            		alertHandling($("#successSection"), "User registered Successfully");                	
                  } else if(data == -1){
                	$('#successSection').hide();
                	alertHandling($("#errorSection"), "User registration Failed"); 
                  } else if(data == -2){
                		$('#successSection').hide();
                    	alertHandling($("#errorSection"), "User already Exists");	
                  }                
            },
            error: function(xhr,status){
            	
            	$('#successSection').hide();
            	console.log(xhr);
            	alertHandling($("#errorSection"), status);
            }
        })
	});*/
	
});