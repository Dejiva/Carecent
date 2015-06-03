/* PDFMAKER DOC OBJECT */
var API = API || {};

/* AJAX loader show for PDFMAKER DOC */
API.show = function(){
	$('body').append('<div class="pdfLoading"><img src="'+contextPath+'/img/loader.gif" class="ajax-loader"/></div>');
	return "";
};

/* AJAX loader hide for PDFMAKER DOC */
API.hide = function(){
	$('.pdfLoading').hide();
	return "";
};

/* AJAX call method for PDFMAKER DOC */
API.ajaxCall = function(url) {
	//console.log("AJAX URL:  "+url);
	var result="";
	$.ajax({
	    url: url,
	    async: false,  
	    type:'get',
	    global: true,
	    success: function(data) {
	    	result = data; 
	    }
	});
    return result;
};

/* String Sentence Case for PDFMAKER DOC */
API.toSentenceCase = function(str) {
	if(str != undefined){
        return str.replace(/\w\S*/g, function(txt){
    		return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    	});
	} else{
		return "";
	}
};
 
/* User ID Label object for PDFMAKER DOC */
API.line = function(){
    var line = "";
    for(var i=0; i<=102; i++){
        line +="_";
    }
    return line;
};
API.tickline = function(){
    var line = "";
    for(var i=0; i<=102; i++){
        line +="=";
    }
    line.style = ['subheader'];
    return line;
};

/* Item Header Label object for PDFMAKER DOC */
API.itemHeader = function(head){
    var ih = {};
        ih.text = head;
        ih.style = ['subheader'];
    return ih;
};
API.encounterHeader = function(encounter){
//	var sDate=encounter.startDate.parse("MM/dd/yyyy");
//     console.log("kk"+sDate);
    var ih = {};
        ih.text = "Encounter: "+encounter.encounterNum;
        ih.style = ['colorheader'];
    return ih;
};
/* Info Label object for PDFMAKER DOC */
API.patientInfo = function(pid){
	var api = this;
	var result = this.ajaxCall(contextPath+'/sbar/getpdfPatientInfo?patientId='+pid);
    var head={}, dob = {}, fn={}, doj={};
    
	    head.text = api.toSentenceCase(result.patientName) +' Information\n\n';
	    head.alignment = 'center';
	    head.style = ['header'];
    
	    dob.fontSize = 10;
	    dob.text = 'Date of Birth: '+result.birthDate;
	    
	    fn.fontSize = 10;
	    fn.style = ['right', 'marginTopNegative'];
	    fn.text = 'Facility Name: '+api.toSentenceCase(result.facilityName);
	    
	    doj.fontSize = 10;
	    doj.margin = [0, 5, 0, 0];
	    doj.text = 'Date of Join: '+(result.joiningDate != null ? result.joiningDate : "");
    return [head, dob, fn, doj];
};

/* Create a style object for PDFMAKER DOC */
API.style = function(){
    var s = {}, h = {}, sh = {}, r = {}, mtl = {}, mtn = {}, table = {}, b = {},ch={};
        h.fontSize = 12;
        h.margin = [0, -10, 0, 0];
        sh.fontSize = 10;
        sh.bold = true;
        r.alignment = 'right';
        r.fontSize = 10;
        mtl.margin = [10, 0, 0, 0];
        mtl.fontSize = 10;
        mtn.margin = [0, -14, 0, 0];
        mtn.fontSize = 10;
        table.margin = [0, 5, 0, 5];
        table.alignment = 'center';
        table.fontSize = 10;

        b.fontSize = 10;
        b.bold = true;
        b.margin = [0, 5, 0, 0];
        ch.fontSize = 12;
        ch.color="orange";
        ch.alignment = 'center';
        ch.margin=[0, 5, 0, 0]
        s.header = h;
        s.subheader = sh;
        s.colorheader=ch;
        s.right = r;
        s.marginTopLeft = mtl;
        s.marginTopNegative = mtn;
        s.tableStyle = table;
        s.bold = b;
    //console.log(s);
    return s;
};

/* Create a arrow image object for PDFMAKER DOC */
API.img = function(){
	var img = {};
	img.image = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAECSURBVFhHY2AYBaMhMIRDwA/o9ntAfBeIfQbCHy+Alv6H4mfkOoCRXI1Qi5G1k2UWEwUOoIrWUQeMhsBoCAyZEOAlI9OTowfDGlGgyHloqbcHSAsiqYAVwzAaJgVSsxeq5xyQFiHD8XAttUjlPcgikGPEoLLYHACSu4CmpwafAwilgadomg2A/INALI3FUBmonD6a3H1KQgDkwLloPgL5HFQNo4cANjGQXrIqKXRHN2KxEN0B6PwmSnyOTW85CY4AqaUJyAaa+g+PQ0ByIDU0BalA0/9icQRILI2mNiMZHgFk/0JyxG8gO4pelsPsCQAyHgPxEyAOobflo/aNhgDVQgAAIKhep14SuocAAAAASUVORK5CYII=';
	img.alignment = 'left';
	img.width= 15;
	img.margin= [210, 0, 0, 0];
	return img;
};

/* Create a default style object for PDFMAKER DOC */
API.defaultStyles = function(){
    var ds = {};
        ds.fontSize = 12;
        ds.bold = false;
    return ds;
};

/* items Label object for PDFMAKER DOC */ 
/*obj: actual list object for order list, flag: used for margin top space*/
API.ul = function(obj, flag){
    var ul = {}, li = [], api = this;
    $.each(obj, function(index, item){
    	/*if(obj.length -1 == index){
    		li.push(api.toSentenceCase(item)+"\n");
    	}else{*/
		li.push(api.toSentenceCase(item));
    	//}
    });
    ul.ol = li;
    if(flag){
		ul.style = ['marginTopLeft'];
	}
    return ul;
};

/* items Label object for PDFMAKER DOC */
API.items = function(obj){
    var i = {};
		i.style = ['marginTopLeft'];
        i.ol = obj;
    return i;
};

/* Get Stop And Watch Data*/
API.getStopAndWatch = function(id,eId){
	var api = this;
	var result= this.ajaxCall(contextPath+"/stopAndWatch/getPatientHistoryForSbar?currPatientId="+id+"&encounterId="+eId), output = [];
	if(result.length != 0){
		$.each(result, function(index, item){
			var ul = [], table = {};
			//table.style = 'tableStyle';
			table.fontSize = 10;
			table.margin = [0, 10, 0, 0];
			table.table = {
				body:[
					['Reported by', 'Reported to', 'Date and Time', 'Nurse Name', 'Response', 'Response Date'],
					[api.toSentenceCase(item.repotedBy), api.toSentenceCase(item.repotedTo), api.toSentenceCase(item.repotedDateString), api.toSentenceCase(item.nurseName), api.toSentenceCase(item.response), api.toSentenceCase(item.responseDateString)]
		        ]
			};
			table.layout = {
				hLineWidth: function(i, node) {
					return 0.5;
				},
				vLineWidth: function(i, node) {
					return 0.5;
				}
			};
			ul.push({ text: 'Symptoms:', fontSize: 10, margin: [0, 5, 0, 0] });
			ul.push(api.ul(item.symtomsList, true));
			output.push(table);
			output.push(ul);
		});
		return output;
	}
	return "";
};

/* Get Symptoms ChangeInConditions Data*/
API.getChangeInConditions = function(id,eId){
	var api = this;
	var result = this.ajaxCall(contextPath+"/sbar/getSymptomsChangeInConditionsByPatientEpisodeId?patientEpisodeId="+id+"&encounterId="+eId), ol = {};
	if(result.length != 0){
		var ol = [];
		$.each(result, function(index, item){
			var ul = [], child=[];
			ol.margin = [5, 0, 0, 0];
			ul.push({ text: api.toSentenceCase(item.symptomName)+":", style: 'bold'});
			$.each(item.attributes, function(idx, val){
				if(val.attributeValue == 'true'){
					child.push(api.toSentenceCase(val.attributeName));
				}
			});
			ul.push(api.ul(child, true));
			ol.ul = ul;
			ol.push(ol.ul);
		});
		return [/*{ text: 'Symptoms:', fontSize: 10, style: {bold: true}}, */ol, '\n'];
	}
	return "";
};

/* Get VitalSigns Data*/
API.getVitalSigns = function(id,eId){
	var api = this;
	var result = this.ajaxCall(contextPath+"/sbar/getVitalSignsChangeInConditionsByPatientEpisodeId?patientEpisodeId="+id+"&encounterId="+eId), ol = {};
	if(result.length != 0){
		var ol = [];
		$.each(result, function(index, item){
			var ul = [], child1=[];
			//ul.push(api.toSentenceCase(item.symptomName));
			ol.margin = [5, 0, 0, 0];
			ul.push({ text: api.toSentenceCase(item.symptomName)+":", style: 'bold'});
			//ul.push('Results:');
			$.each(item.attributes, function(idx, val1){
				if(val1.attributeValue!=null){
					if(val1.dataType=="BOOLEAN"){
						child1.push(api.toSentenceCase(val1.attributeName));
					}else{
				        child1.push(api.toSentenceCase(val1.attributeName)+": "+api.toSentenceCase(val1.attributeValue));
					}
				}
			});
			ul.push(api.ul(child1, true));
			ol.ul = ul;
			ol.push(ol.ul);
		});
		
		return [ol, '\n'];
	}
	return "";
};

/* Get Labworks Data*/
API.getLabworks = function(id,eId){
	var api = this;
	var result = this.ajaxCall(contextPath+"/sbar/getLabworkChangeInConditionsByPatientEpisodeId?patientEpisodeId="+id+"&encounterId="+eId), ol = {};
	if(result.length != 0){
		var ol = [];
		$.each(result, function(index, item){
			var ul = [], child=[];
			ol.margin = [5, 0, 0, 0];
			ul.push({ text: api.toSentenceCase(item.symptomName)+":", bold: true});
			$.each(item.attributes, function(idx, val){
				if(val.attributeValue!=null){
					if(val.dataType=="BOOLEAN"){
						child.push(api.toSentenceCase(val.attributeName));
					}else{
						child.push(api.toSentenceCase(val.attributeName)+": "+api.toSentenceCase(val.attributeValue));
					}
				}
			});
			ul.push(api.ul(child, true));
			ol.ul = ul;
			ol.push(ol.ul);
		});
		return ol;
	}
	return "";
};

/* Get CarePath Data as Flow Chart*/
API.getCarePath = function(id, eId,head, rows){
	var api = this;
	var result = this.ajaxCall(contextPath+"/carepath/viewAllCarePaths?patientEpisodeId="+id+"&encounterId="+eId), olObj = {}, output = [];
	if(result.length != 0){
		$.each(result, function(index, item){
			$.each(item.carePathSteps, function(idx, val){
				var stackObj = {}, ul = [], tableObj = {}, tbody = {}, body = [], length = [], notify = [];
				body.push([{ text: api.toSentenceCase(val.stepName)}]);
				length.push(val.stepName.length);
				if(val.attrValues.length == 0){
					body.push([{ text: "No Symtoms were found"}]);
					length.push(21);
				};
				$.each(val.attrValues, function(item, attr){
					if(attr.dataType != null){
						ul.push(api.toSentenceCase(attr.carePathAttrName)+": "+api.toSentenceCase(attr.value));
					}else {
						ul.push(api.toSentenceCase(attr.carePathAttrName));
					}
					length.push(attr.carePathAttrName.length);
					if(attr.notifyDoctorFlag){
						notify = [];
						notify.push([api.img()]);
						notify.push({margin: [20, 0, 0, 0], table:{widths: [400], body:[[{text:'Notify MD or NP or PA', bold: true, fontSize : 10, color: 'red'}]]}, layout: {
							hLineWidth: function(i, node) {
								return 0.5;
							},
							vLineWidth: function(i, node) {
								return 0.5;
							}
						}});
					};
				});
				stackObj.ul = ul;
				body.push([stackObj]);
				tbody.body = body;
				tbody.widths= [400];
				tableObj.table = tbody;
				tableObj.alignment = 'left';
				tableObj.margin = [20, 0, 0, 0];
				//(Math.max.apply(Math, length) < 50 ? 44 : 10) * (555 - Math.max.apply(Math, length))/100
				tableObj.fontSize = 10;
				tableObj.layout = {
					hLineWidth: function(i, node) {
						return 0.5;
					},
					vLineWidth: function(i, node) {
						return 0.5;
					}
				};
				if(idx == 0){
					output.push([{ text: api.toSentenceCase(item.carePathName), bold: true, margin: [0, 5, 0 ,5], fontSize : 10}, tableObj]);
					if(idx != item.carePathSteps.length-1){
						output.push([api.img()]);
					};
				} else{
					output.push([tableObj]);
				}
				output.push(notify);
			});
		});
		//console.log(JSON.stringify(olObj));
	    return output;
	}
    return "";
};

/* Create a skeleton object for PDFMAKER DOC */

API.skeleton = function(name, id, peid){
    var s = {};
        s.styles = this.style();
        s.defaultStyle = this.defaultStyles();
        s.pageSize = 'A4';
        s.pageMargins = [ 20, 20, 20, 20 ];
        s.content=[
            this.patientInfo(id),
            this.line(),
        ];
        var encounters = this.ajaxCall(contextPath+"/sbar/getEncounters?patientEpisodeId="+peid);
        var con=this;
        if(encounters.length != 0){
        $.each(encounters,function(i, item){
        	/*item is encounters list*/
       
        var text = [
            con.encounterHeader(item),
            con.itemHeader('STOP AND WATCH HISTORY'),
            con.getStopAndWatch(id,item.id),
            con.line(),
            con.itemHeader('CHANGE IN CONDITION DETAILS\n'),
            con.getChangeInConditions(peid,item.id),
            con.itemHeader('VITAL SIGNS'),
            con.getVitalSigns(peid,item.id),
            con.itemHeader('TESTS/RESULTS'),
            con.getLabworks(peid,item.id),
            con.line(),
            con.itemHeader('CARE PATH VIEWS'),
            con.getCarePath(peid,item.id),   
            con.tickline()
            
          
        ];
        s.content.push(text);
     });
        }
    //console.log(s);
    return s;
};

/* Helps to Download DOC */         
//pdfMake.createPdf(dd).download('optionalName.pdf');
    
function exportAsPdf(patient){
	/* Render DOC to HTML Element */
	var peid = API.ajaxCall(contextPath+"/sbar/getActivePatientEpisodeId?patientId="+patient.split('=')[0]);
	/*(name , patient id, patient episode id)*/
	pdfMake.createPdf(API.skeleton(patient.split('=')[1], patient.split('=')[0], peid.split('=')[0])).getDataUrl(function(outDoc) {
		 $('#exportPdfModal').html('<a class="close-reveal-modal pdf-close">&#215;</a><iframe src=""  width="100%" height="100%"/>').trigger('create');
		 $('iframe').attr('src', outDoc);
    });
}

//Create's a exportPdfModal while home page load
$(document.body).append('<div class="reveal-modal xlarge" style="display: none" id="exportPdfModal" data-reveal></div>');

$(document).on('click', 'span.fi-page-export-pdf', function(){
	try {
		API.show();
		exportAsPdf($(this).attr('data-patient'));
		API.hide();
		$('#exportPdfModal').foundation('reveal', 'open');
	} catch(e){
	
		//alert("sorry for the inconvenience, please try again.");
	}
});