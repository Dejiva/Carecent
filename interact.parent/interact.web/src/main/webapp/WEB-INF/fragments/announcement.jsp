<div id="AnnouncementRevialModal" class="reveal-modal raleway"
	data-reveal style="top: 10px !important;">
	<a class="close-reveal-modal">&#215;</a>
	<p class="lead" style="text-align: center;">Announcement</p>
	<label id="errormsg"  style="color: green;"></label>
	<div class="row">
		<div class="small-6 columns">
			<textarea cols="8" rows="6" id="announcementTextId"></textarea>
		</div>
		<div class="small-4 columns">
			<input type="button" id="saveOrAddNotesId" placeholder="Enter announcement text"
			value="Add" class="button"/>
		</div>
	</div>
	
	<table id="announcementDataGrid" class="display" width="100%" cellspacing="0" style="height: 100px;max-height:100px;overflow-y: auto;">
			<thead>
				<tr>
					<th style='text-align: center; '>Announcement</th>
					<th style='text-align: center;'>User Created</th>
					<th style='text-align: center;'>Modified Date</th>
					<th style='text-align: center;'>Active</th>
					<th style='text-align: center;'>Delete</th>
					<th style='text-align: center;'>Edit</th>
				</tr>
			</thead>
			<tbody id="announcementDataGridBody"/>
	</table>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/announcement.js"></script>
</div>