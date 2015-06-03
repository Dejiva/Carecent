var announcementId;
var currentSelectionEdit;

$('#manageAnnouncement').click(
		function() {
			announcements();
			$('#AnnouncementRevialModal').foundation('reveal', 'open');
		});
$('#saveOrAddNotesId').click(function() {
	var saveOrUpdate=$('#saveOrAddNotesId').val();
	if(saveOrUpdate.trim()=='Add')
	{
		var announcementText = encodeURIComponent($('#announcementTextId').val());
		var contentRow="";
		if(announcementText==""||announcementText==null){
			$('#errormsg').text("Please Enter Announcement");
			$('#errormsg').css({"color":"red"});
			$('#errormsg').show();
		}
	else{
		$.ajax({
			url : contextPath+'/saveAnnouncement?announcement='+announcementText,
			type : 'get',
			success : function(data) {
				$('#errormsg').text("Announcement Added successfully");
				$('#errormsg').css({"color":"green"});
				$('#errormsg').show();
				announcements();
				$('#announcementTextId').val('');
			}
		});
		}
	}else{
		var announcementText = encodeURIComponent($('#announcementTextId').val());
		var annId=encodeURIComponent(announcementId);
		console.log(annId);
		console.log(announcementText);
		var contentRow="";
		if(announcementText==""||announcementText==null)
		{
		$('#errormsg').text("Please Enter Annoucement");
	    $('#errormsg').css({"color":"red"});
	    $('#errormsg').show();
		}else{
		$.ajax({
			url : contextPath+'/updateAnnouncement?announcement='+announcementText+"&annId="+annId,
			type : 'get',
			success : function(data) {
				$('#errormsg').text("Announcement updated successfully");
				$('#errormsg').css({"color":"green"});
				$('#errormsg').show();
				announcements();
				$('#announcementTextId').val('');
				$('#saveOrAddNotesId').val('Add');
				}
		});
		}
	}
});
$('#announcementDataGridBody').on('change', 'input.actAnn', function(e){
	var annId=encodeURIComponent($(this).attr('ann-id'));
	var activeFlag=encodeURIComponent($(this).is(":checked"));
	$.ajax({
		url : contextPath+'/activateAnnouncement?annId='+annId+"&actFlag="+activeFlag,
		type : 'get',
		success : function(data) {
		}
	});
	
});

$('#announcementDataGridBody').on('click', 'span.fi-trash.annDel', function(e){
	var annId=$(this).attr('ann-id');
	$.ajax({
		url : contextPath+'/deleteAnnouncement?annId='+annId,
		type : 'get',
		success : function(data) {
		}
	});
	
	 $(this).closest('tr').remove();
	
});
$('#announcementDataGridBody').on('click', 'span.fi-pencil.annEdi', function(e){
	console.log('editing');
	$('#errormsg').text("");
	$('#announcementTextId').val($(this).attr('ann-note'));
	announcementId=$(this).attr('ann-id');
	$('#saveOrAddNotesId').val('Update');
	currentSelectionEdit=$(this).closest('tr');
});

function announcements(){
	var content = "";
	$.ajax({
		url : contextPath+'/getAllAnnouncement',
		type : 'get',
		success : function(data) {
			$.each(data, function(index, value) {
				var str = value.announcementText;
				var result="" ;
				while (str) {
				    if (str.length < 70) {
				    	result+= str;
				        break;
				    }
				    else {
				    	result+=str.substr(0, 70)+"<br>";
				        str = str.substr(70);
				    }
				}
				console.log("result--->"+result);
				content += "<tr><td style='text-align: left;'>" + result + "</td><td style='text-align: center;'>"
						+ value.userCreated  + "</td><td style='text-align: center;'>"
						+ value.modifiedDate + "</td><td style='text-align: center;'>"
						+ (value.activeFlag==1?"<input class='actAnn' type='checkbox'  ann-id='"+value.id+"' checked/>":"<input class='actAnn' type='checkbox' ann-id='"+value.id+"' />") + "</td>"
						+ "<td style='text-align: center;'>"+"<span class='fi-trash annDel'  ann-id='"+value.id+"'></span>"+"</td>"
						+ "<td style='text-align: center;'>"+"<span class='fi-pencil annEdi' style='float: center !important;  position: static !important;'  ann-id='"+value.id+"' ann-note='"+value.announcementText+"'></span></td></tr>";
			});
			$('#announcementDataGridBody').html('');
			$('#announcementDataGridBody').append(content);

		}
	});
}


