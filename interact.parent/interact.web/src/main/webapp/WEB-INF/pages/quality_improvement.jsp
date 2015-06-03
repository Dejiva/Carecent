<html>
<head>
<title>CARECENT</title>
<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/x-icon">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/device.css" />
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">	
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/foundation-icons.css"/>
<link type="stylesheet" href="${pageContext.request.contextPath}/css/jqplot/jquery.jqplot.min.css" />
<link href="https://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" />
<%-- <script src="${pageContext.request.contextPath}/js/vendor/jquery.js"></script> --%>
</head>
<body>
	<div class="row">
		<div class="large-12 columns padding-lr">
			<div class="app_logo large-2 columns">
				<img src="${pageContext.request.contextPath}/img/mail_logo.jpg" title="Carecent" />
			</div>
			<div class="large-7 columns text-center" >
			  <label style="font-size:21px; padding-top:13px;">Facility Name: <b>${sessionScope.loggedUser.facilityName}</b></label>
			</div>
			<div class="large-3 columns padding-lr">
			     <a class="add_patient button tiny right" href="${pageContext.request.contextPath}/welcome">Dashboard</a>
			</div>


<!-- 			<div class="large-5 columns user_info"> -->
<!-- 				<nav class="top-bar" data-topbar data-options="is_hover:true"> -->
<!-- 									<ul class="title-area"> -->
<!-- 										<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li> -->
<!-- 									</ul> -->
<!-- 					<section class="top-bar-section"> -->
<!-- 						<ul class="right"> -->
<!-- 							<li class="has-dropdown"><a href="#" id="li_color_change"> -->
<!-- 									<i class="fi-torso-business user_icon"></i> <c:if -->
<%-- 										test="${pageContext.request.userPrincipal.name != null}"> --%>
<%-- 								   ${pageContext.request.userPrincipal.name == null? "Hello" : pageContext.request.userPrincipal.name}(${sessionScope.role}) --%>
<!-- 							    </c:if> -->
<!-- 							</a> -->
<!-- 								<ul class="dropdown right"> -->
<!-- 									<li id='li_hover'><a -->
<%-- 										href="${pageContext.request.contextPath}/welcome" id="DASH">Dashboard</a> --%>
<!-- 									</li> -->
<!-- 								</ul></li> -->
<!-- 						</ul> -->
<!-- 					</section> -->
<!-- 				</nav> -->
<!-- 			</div> -->
		</div>
	</div>
<div class="row">
	<div id="graphsstabs" >
		<div class='row graphsMenuTopBar' style="margin:0px;">
			<div class="large-12 columns padding-lr" style="margin-bottom:-1em">
			 <div class="large-6 columns">
				<span style="color: orange; font-size:20px; margin-left:18px;">Quality Improvement Tool</span>
			 </div>
			 <div class="large-5 columns">
			  <div class="small-6 columns">
				<div class="small-3 columns"><label style="color:white;">From:</label></div>
				<div class="small-8 columns end"><input id="fromdate" type="text" placeholder="mm/dd/yyyy" class="date-picker"/></div>
			  </div>
			  <div class="small-6 columns end">
				<div class="small-2 columns"><label style="color:white;">To:</label></div>
				<div class="small-9 columns end"><input id="todate" type="text" placeholder="mm/dd/yyyy" class="date-picker"/></div>
			  </div>
			 </div>
			 <div class="large-1 columns right">
			  <span><img class="datesearch" src="${pageContext.request.contextPath}/img/searrch.png"/></span></div>
			</div>
		</div>
		<dl class="tabs vertical" data-tab>
			<dd class="active">
				<a href="#chartl1">Symptoms/Transfers</a>
			</dd>
			<dd>
				<a href="#chartl2">Hospital Transfers</a>
			</dd>
			<dd>
				<a href="#chartl4">Manage on CIC</a>
			</dd>
			<dd class="30daysretransmission">
				<a href="#chartl3">30days of readmission</a>
			</dd>
		</dl>

		<div class="tabs-content vertical" style="max-height: 500px; overflow: auto; width: 1116px;">
			<div class="content active" id="chartl1">
			<div class="large-12 columns" style="background-color:white; margin-bottom:10px;">
			
			<div class="small-8 columns graphheading">
			  <span style="color: Red;">Changes In Condition and Other
					Factors that Contribute the Transfer</span>			  
			</div>
<!-- 			<div class="small-2 columns graphheading"> -->
<!-- 			  <span class="Startdate"></span> -->
<!-- 			</div> -->
<!-- 			<div class="small-2 columns graphheading"> -->
<!-- 			  <span class="Enddate"></span> -->
<!-- 			</div> -->
			</div>
				<div class="small-12 columns">
					<div class="small-5 columns padding-lr">
						<table id="symtomDataTable" class="QIsymtomDataTable"
							style="float: left; border-collapse:">
							<thead>
								<tr>
									<th>New or Worsning Symptoms and Signs</th>
									<th>N</th>
									<th>%</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="small-7 columns padding-lr qigraphss">
						<div id="QiChart" class="QIqualityImprovementChart"></div>
					</div>
				</div>
			</div>
			<div class="content" id="chartl2">
			<div class="large-12 columns" style="background-color:white; margin-bottom:10px;">
			<div class="small-8 columns graphheading">
			  <span style="color: Red;">Hospital Transfer Information</span>			  
			</div>
<!-- 			<div class="small-2 columns graphheading"> -->
<!-- 			  <span class="Startdate"></span> -->
<!-- 			</div> -->
<!-- 			<div class="small-2 columns graphheading"> -->
<!-- 			  <span class="Enddate"></span> -->
<!-- 			</div> -->
			</div>
				<div class="row">
					<div class="small-12 columns padding-lr">
						<div class="small-5 columns padding-lr">
							<table id="symtomDataTable1" class="QIsymtomDataTable"
								style="float: left; border-collapse:">
								<thead>
									<tr>
										<th>Time of Day</th>
										<th>N</th>
										<th>%</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="small-7 columns  padding-lr qigraphss">
							<div id="QIChart1" class="QIqualityImprovementChart"></div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="small-12 columns padding-lr">
						<div class="small-5 columns padding-lr">
							<table id="symtomDataTable2" class="QIsymtomDataTable"
								style="float: left; border-collapse:">
								<thead>
									<tr>
										<th>Day of Week</th>
										<th>N</th>
										<th>%</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="small-7 columns  padding-lr qigraphss">
							<div id="QIChart2" class="QIqualityImprovementChart"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="content" id="chartl4">
			<div class="large-12 columns" style="background-color:white; margin-bottom:10px;">
			<div class="small-8 columns graphheading">
			  <span style="color: Red;">Action Takes to Evaluate and Manage the Change in Condition</span>			  
			</div>
<!-- 			<div class="small-2 columns graphheading"> -->
<!-- 			  <span class="Startdate"></span> -->
<!-- 			</div> -->
<!-- 			<div class="small-2 columns graphheading"> -->
<!-- 			  <span class="Enddate"></span> -->
<!-- 			</div> -->
			</div>
				<div class="row">
					<div class="small-12 columns padding-lr">
						<div class="small-5 columns padding-lr">
							<table id="symtomDataTable4" class="QIsymtomDataTable"
								style="float: left; border-collapse:">
								<thead>
									<tr>
										<th>Medical Evalution</th>
										<th>N</th>
										<th>%</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="small-7 columns  padding-lr qigraphss">
							<div id="QIChart4" class="QIqualityImprovementChart"></div>
						</div>
					</div>
				</div>
				
			</div>
			<div class="content" id="chartl3">
			<div class="large-12 columns" style="background-color:white; margin-bottom:10px;">
			<div class="small-8 columns graphheading">
			  <span style="color: Red;">30 day Readmission for Current Nursing Home vs all Other Nusring Homes</span>			  
			</div>
<!-- 			<div class="small-2 columns graphheading"> -->
<!-- 			  <span class="Startdate"></span> -->
<!-- 			</div> -->
<!-- 			<div class="small-2 columns graphheading"> -->
<!-- 			  <span class="Enddate"></span> -->
<!-- 			</div> -->
			</div>
			<div class="small-12 columns padding-lr"><span><b>(GRAPH WITH DUMMY DATA-service under progess)</b></span></div>
				<div class="small-12 columns">
					<div class="small-9 columns padding-lr qigraphss">
						<div id="QiChart3" class="QIqualityImprovementlineChart"></div>
					</div>
					<div class="small-3 columns padding-lr"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqplot/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqplot/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqplot/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqplot/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqplot/jqplot.canvasAxisLabelRenderer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqplot/jqplot.canvasTextRenderer.min.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqplot/jqplot.pointLabels.min.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation/foundation.js"></script>
<script src="${pageContext.request.contextPath}/js/foundation/foundation.tab.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/modernizr.js"></script>
<script src="https://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script>
	var firstTimeUserFlag = '${firstTimeUser}';
	$(document).foundation('tab', 'reflow');
</script>

<script>
var contextPath = '<%=request.getContextPath()%>';
$(document).ready(function() {
	$(".date-picker").datepicker(
			{
				showOn : "both",
				buttonImage : "img/calendar_icon.jpg",
				buttonImageOnly : true,
				timeFormat : 'hh:mm:ss tt',
				defaultDate : new Date(),
				changeMonth : true,
				changeYear : true,
				maxDate : new Date(),
				yearRange : "-100:+0",
				dateFormat : "mm/dd/yy",
				onSelect : function(datetext) {
					var d = new Date(); // for now
					datetext = datetext + " " + d.getHours() + ": "
							+ d.getMinutes() + ": " + d.getSeconds();
					$('.snwrepoteddatepicker').val(datetext);
				}
			});
	var chartFlag = true;
	var chartFlag1 = true;
	var chartFlag2 = true;
	$(document).foundation({
	    tab: {
	      callback : function (tab) {
	        if(tab.find('a').attr('href') == "#chartl2"){
	        	if(chartFlag){
	        		transferByTimeOfDayAndDayOfWeek();
	        		chartFlag = false;
	        	}
	         }
	        else if(tab.find('a').attr('href') == "#chartl3"){
// 	        	if(chartFlag1){
	        		linechart();
// 	        		chartFlag1 = false;
// 	        	}
	         }
	        else if(tab.find('a').attr('href') == "#chartl4"){
	        	console.log("After tab click ");
	        	if(chartFlag2){
	        		actionsTakenToEvalNdMngCIC();
	        		chartFlag2 = false;
	        	}
	         }	       
	      }
	    }
    });
	
	var perArray = [], jQplotChart;
	var timeOfDayArray = [];
	var dayOfWeekArray = [];
	var attEvalNdMngCIC = [];
	symptomsAgainstUnplanned();
	//transferByTimeOfDayAndDayOfWeek();
	function symptomsAgainstUnplanned() {

		$.ajax({
			url : contextPath + '/qiGraphs/symptomsAgainstUnplanned',
			type : 'get',
			async : false,
			success : function(json) {
				drawTable(json, 'symtomDataTable');
				addRowforTotal("Total Transfers",json.totalTransfers,'symtomDataTable');
				drawChart(perArray, 'QiChart', Math.round(perArray.length * 21.8));
				
			},
			error : function() {
				alert("Please try again.");
			}
		});
		//   }
	}
	
	function drawTable(json, dataTableId) {
		perArray = [];
		$("#" + dataTableId + " tbody").html('');
		var data = json.symptomsAgainstUnplanned;
		for (var i = 0; i < data.length; i++) {
			drawRow(data[i], dataTableId);
			perArray.push(data[i].transferPercent);
		}
	}

	function drawRow(rowData, dataTableId) {
		var row = $("<tr />");
		$("#" + dataTableId).append(row);
		row.append($("<td>" + rowData.symtomName + "</td>"));
		row.append($("<td>" + rowData.numberOfTransfers + "</td>"));
		row.append($("<td>" + rowData.transferPercent + "%</td>"));
	}
	// 	********************************************

	function transferByTimeOfDayAndDayOfWeek() {
		$.ajax({
			url : contextPath + '/qiGraphs/hospitalTransferInformation',
			type : 'get',
			async : false,
			success : function(json) {
				drawTableTOD(json.timeOfDay, 'symtomDataTable1');
				addRowforTotal("Total Transfers",json.timeOfDay.total,'symtomDataTable1');
				drawChart(timeOfDayArray, 'QIChart1', Math.round([50, 20, 10, 10].length * 30));
				
				drawTableDOW(json.dayOfWeek, 'symtomDataTable2');
				addRowforTotal("Total Transfers",json.dayOfWeek.total,'symtomDataTable2');
				drawChart(dayOfWeekArray, 'QIChart2', Math.round(dayOfWeekArray.length * 26.4));
			},
			error : function() {
				alert("Please try again.");
			}
		});
	}
	
	function drawTableTOD(json, tableid) {
		timeOfDayArray = [];
		$("#" + tableid + " tbody").html('');
		var data = json.info;
		for (var i = 0; i < data.length; i++) {
			drawRowTODAndDOW(data[i], tableid);
			timeOfDayArray.push(data[i].percent);
		}
	}
	
	function drawTableDOW(json, tableid) {
		dayOfWeekArray = [];
		$("#" + tableid + " tbody").html('');
		var data = json.info;
		for (var i = 0; i < data.length; i++) {
			drawRowTODAndDOW(data[i], tableid);
			dayOfWeekArray.push(data[i].percent);
		}
	}
	
	function drawRowTODAndDOW(rowData, tableid) {
		var row = $("<tr />");
		$("#" + tableid).append(row);
		row.append($("<td>" + rowData.name + "</td>"));
		row.append($("<td>" + rowData.cout + "</td>"));
		row.append($("<td>" + rowData.percent + "%</td>"));
	}

	function actionsTakenToEvalNdMngCIC(){
		console.log("ghjk");
		$.ajax({
			url : contextPath + '/qiGraphs/actionsTakenToEvalNdMngCIC',
			type : 'get',
			async : false,
			success : function(json) {
				drawTableATToEvalNdMngCIC(json.medicalEvaluation, 'symtomDataTable4');
				addRowforTotal("Number of Transfers(denominator)",json.totalTransfers,'symtomDataTable4');
				drawChart(attEvalNdMngCIC, 'QIChart4', Math.round(attEvalNdMngCIC.length * 33));
				
			},
			error : function() {
				alert("Please try again.");
			}
		});
	}
	function addRowforTotal(name,value, tableid){
		var row = $("<tr />");
		$("#" + tableid).append(row);
		row.append($("<td><B>" + name + "</B></td>"));
		row.append($("<td><B>" + value + "</B></td>"));
		
	}
	

	function drawTableATToEvalNdMngCIC(json, tableid) {
		attEvalNdMngCIC = [];
		$("#" + tableid + " tbody").html('');
// 		var data1 = json.medicalEvaluation;
		for (var i = 0; i < json.length; i++) {
			drawRowATToEvalNdMngCIC(json[i], tableid);
			attEvalNdMngCIC.push(json[i].percent);
		}
		
	}

	function drawRowATToEvalNdMngCIC(rowData, tableid) {
		var row = $("<tr />");
		$("#" + tableid).append(row);
		row.append($("<td>" + rowData.name + "</td>"));
		row.append($("<td>" + rowData.cout + "</td>"));
		row.append($("<td>" + rowData.percent + "%</td>"));
		
	}
	
	function drawChart(dataAry, chartId, chartHeight) {
		//jQplotChart.destroy();
		//$(".QIqualityImprovementChart").show();
		//$.jqplot.config.enablePlugins = true;
		$.jqplot(chartId, [ dataAry.reverse() ], {
			height : chartHeight,
			width : 600,
			//stackSeries: true,
			seriesDefaults : {
				renderer : $.jqplot.BarRenderer,
				xaxis : 'x2axis',
				rendererOptions : {
					fillToZero : true,
					barDirection : 'horizontal',
					barMargin : 10,
					barWidth : 14,
					barPadding: 10,
					highlightMouseDown : true,
					shadowOffset : 0
				},
				pointLabels : {
					show : false,
					stackedValue : false,
					location : 'e'
				}
			},
			axes : {
				x2axis : {
					//renderer: $.jqplot.CategoryAxisRenderer,
					min : 0,
					max: 100,
					tickInterval : 10,
					numberTicks : 11,// Math.round((Math.max.apply(Math,dataAry) / 10)) + 1,
					tickOptions : {
						formatString : "%d%",
						fontSize : '10pt',
						labelPosition : 'middle',
					}
				},
				yaxis : {
					renderer : $.jqplot.CategoryAxisRenderer,
					tickOptions : {
						showGridline : false,
						mark : false,
						show : false
					}
				}
			},
			grid : {
				drawBorder : false,
				shadow : false,
				background : '#FFFFFF',
			},
			legend : {
				show : false
			},
			title : ''
		});
		$('.jqplot-axis.jqplot-yaxis').hide();
		$('.jqplot-axis.jqplot-x2axis').css('top', '-3px');
	}
	
	
	
	function linechart()
	{
	 var Firstarray = [];
	 var Secondarray = [];
	 var a  ={
	  "Unplanned": [
	                {
	                    "month": "Jan",
	                    "cfTransfersPer": 80,
	                    "OfTransfersPer": 18
	                },
	                {
	                    "month": "Feb",
	                    "cfTransfersPer": 10,
	                    "OfTransfersPer": 84
	                },
	                {
	                    "month": "Mar",
	                    "cfTransfersPer": 60,
	                    "OfTransfersPer": 90
	                },
	                {
	                    "month": "Apr",
	                    "cfTransfersPer": 10,
	                    "OfTransfersPer": 38
	                },
	                {
	                    "month": "May",
	                    "cfTransfersPer": 40,
	                    "OfTransfersPer": 60
	                },
	                {
	                    "month": "June",
	                    "cfTransfersPer": 10,
	                    "OfTransfersPer": 40
	                },
	                {
	                    "month": "July",
	                    "cfTransfersPer": 98,
	                    "OfTransfersPer": 90
	                },
	                {
	                    "month": "Aug",
	                    "cfTransfersPer": 32,
	                    "OfTransfersPer": 70
	                },
	               {
	                    "month": "Sep",
	                    "cfTransfersPer": 100,
	                    "OfTransfersPer": 80
	                },
	               {
	                    "month": "Oct",
	                    "cfTransfersPer": 80,
	                    "OfTransfersPer": 50
	                },
	               {
	                    "month": "Nov",
	                    "cfTransfersPer": 15,
	                    "OfTransfersPer": 10
	                },
	              {
	                    "month": "Dec",
	                    "cfTransfersPer": 35,
	                    "OfTransfersPer": 100
	                }
	            ],
	            "startDate": "2015-04-27 ",
	            "endDate": "2016-04-27 "
	  }
		for(i=0;i<a.Unplanned.length;i++)
		{
		 var b = a.Unplanned[i].cfTransfersPer;
		 Firstarray.push(b);
		 var y = a.Unplanned[i].OfTransfersPer;
		 Secondarray.push(y);
		 var startdate=a.startDate;
		 var enddate=a.endDate;
		 $('.Startdate').html("<b style='padding-right:10px;'>From:</b>"+startdate);
		 $('.Enddate').html("<b style='padding-right:10px;'>To:</b>"+enddate);
		};  
		
	  var plot3 = $.jqplot('QiChart3', [Firstarray,Secondarray], 
	    { 
	      title:'', 
	      seriesDefaults: {
	          rendererOptions: {
	              smooth: true
	          }
	      },
	      series:[ 
	              {
	                lineWidth:3, 
	                markerOptions: { style:'dimaond'}
	              },
	              { 
	                markerOptions: { style:"circle" }
	              }
	          ],
	        axes : {
				xaxis : {
					renderer : $.jqplot.CategoryAxisRenderer,
					tickOptions : {
						showGridline : true
					},
	                ticks:['Jan','Feb','Mar','Apr','May','June','July','Aug','Sep','Oct','Nov','Dec'],
				    numberTicks : 12,
				    lineWidth:3,
		            markerOptions: { style:'dimaond'}
				},
				yaxis : {
					min : 0,
					max: 100,
					tickInterval : 10,
					numberTicks : 11,// Math.round((Math.max.apply(Math,dataAry) / 10)) + 1,
					tickOptions : {
//	 					showGridline : false,
						mark : false,
						show : true,
						formatString : "%d%",
						fontSize : '10pt',
						labelPosition : 'middle',
					}
				}
			}
	    }
	  );
	}
});
</script>
</body>
</html>