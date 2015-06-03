$(function(){
	$(document).on('click',function(){
		$.ajax({
			url:contextPath+'/checkSession',
			type:'get',
			global : false,
			success:function(data){
				if(data == "valid"){
				}else{
					var status = confirm("Your session has expired please login again...");
					if(status){
					location.reload();
					}
				}
			} 
		});
	});
	
	$("#fcontactType").change(function(){
		var value= $("#fcontactType").val();
		$("#fcontactInput").html(value+": <input type='text' name='fcontact' id='fcontact'/>");
	});
	
	$("#createFacility").click(function(){
		$("#standingOrders").attr("checked",true);
		$("#facilityModel").foundation('reveal', 'open');	
	});
	$("#facilitySubmit").click(function(){
		
		var  facilityName = $("#facilityName").val();
		var  address = $("#faddress").val();
		var  street = $("#fstreet").val();
		var  city = $("#fcity").val();
		var  state = $("#fstate").val();
		var  zipcode = $("#fzipcode").val();
		var  contactType = $("#fcontactType").val();
		var  contact = $("#fcontact").val();
		var  status = $("#fstatus").val();
		var standingOrders = $("#standingOrders").is(':checked') ? "true": "false";
		var facilityObj = { "facilityName" : facilityName,
							"faddress" : address,
							"fstreet" : street,
							"fcity" : city,
							"fstate" : state,
							"fzipcode" : zipcode,
							"fcontactType" : contactType,
							"fcontact" : contact,
							"fstatus" : status,
							"standingOrders":standingOrders,
							};
		var error=false;
		var msg="";
		  if(jQuery.isEmptyObject(facilityName)){
			  error=true;
			  msg+="Please provide Facility Name\n"
			  
		  }
		if(jQuery.isEmptyObject(contact)){
			error=true;
			msg+="Please provide contact number\n"
			   
		  }
       if(error){
    	   $("#facilitySubmit").hide();
			$("#facilityStatus").text(msg).css("color","red");
			$("#facilitySubmit").attr("disabled","disabled");
       }else{
			  $("#facilitySubmit").removeAttr("disabled");
			  $("#facilitySubmit").show();
			  $("#facilityStatus").text("");
		  
		$.ajax({
			url:contextPath+'/facility/create',
			data:  facilityObj,
			type:'get',
			success:function(data){
				var status = data.fstatus;
				$("#facilityStatus").text(status).css("color","green");
				setTimeout(function() {
					$("#facilityModel").foundation('reveal', 'close');	
				}, 3000);
				location.reload();
			} 
		});
		}
	});
	
	$(".close-reveal-modal,#facilityCancel").click(function(){
		$("#facilityForm").trigger('reset');
		$("#facilityStatus").empty();
		$("#facilityModel").foundation('reveal', 'close');	
	});

  
});
function checkContact(){
	   console.log("hell");
	  var  contact = $("#fcontact").val();
	  if(contact==''||contact==null){
		    $("#facilitySubmit").attr("disabled","true");
			$("#facilityStatus").text("Please provide contact number").css("color","red");
	  }else{
		  $("#facilitySubmit").removeAttr("disabled");
		  $("#facilitySubmit").show();
		  $("#facilityStatus").text("");
	  }
}