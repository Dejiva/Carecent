<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-draggable.ui-resizable {
	    left: 15% !important;
	    width: 70% !important;
	}

	.ui-dialog-title{
		color: orange;
		font-size: 25px;
	}
	
	.ui-button-text-only > .ui-button-text {
		background-color: #008cba;
	    border-color: #007095;
	    border-style: solid;
	    border-width: 0;
	    color: #fff;
	    cursor: pointer;
	    display: inline-block;
	    font-family: Raleway;
	    font-size: 15px;
	    font-weight: normal;
	    line-height: normal;
	    margin: 0 0 0rem 1rem;
	    padding: 7px 10px;
	    position: relative;
	    text-align: center;
	    text-decoration: none;
	    transition: background-color 300ms ease-out 0s;
	}
	
	.ui-button-text-only > .ui-button-text:hover {
		color: #fff;
	}
	
	.ui-dialog .ui-dialog-buttonpane {
	    border: medium none;
	    padding: 0.5em 0rem;
	    position: relative;
	    top: 5px;
	}
	
	div[aria-describedby=carePathModalMessage], div[aria-describedby=conditionPopup] {
		top: 120px !important;
	}
</style>

<div id="carePathModal" style="display: none;">
	<div id="content" class="large-12 columns move_form_left"
		style="margin-bottom: 20px"></div>
	
</div>
<!-- <div id="carePathViewModal" class="carePathViewModal" style="display: none; "  > -->
<!-- <div id="viewContent" class="large-12 columns move_form_left"></div> -->
<!-- </div> -->
<div id="carePathModalMessage" style="display: none;">
<!-- 	<p class="s_header" style="text-align: center;">Care Path Concluded</p> -->
	<div class="row">
		<div class="lead" style="text-align: center;">
			<b><label id="cpMessage" style="font-size: 40px; color: red"></label></b>
		</div>
	</div>
</div>
