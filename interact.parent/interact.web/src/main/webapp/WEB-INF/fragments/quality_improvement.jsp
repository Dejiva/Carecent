<%@page import="com.codecoop.interact.core.util.Constants"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="graphSlider" class="reveal-modal large raleway" data-reveal style="top: 15px !important;overflow-y: auto; width:990px!important;height:555px !important;">
<p class="lead" style="text-align: center; color:orangered;font-size:20px">Quality Improvement Tool</p>
	<a class="close-reveal-modal">&#215;</a>
<dl class="tabs" data-tab style=" bottom: 10px; position: relative;">
	  <dd class="active" id="HospMeasuresTracking"><a href="#panel1" style="font-family: Raleway;">HospitalizationMeasuresTracking</a></dd>
	  <dd><a href="#panel2" style="font-family: Raleway;">ItemSummaries</a></dd>
	 <dd><a href="#panel3" style="font-family: Raleway;"> CustomizedTracking</a></dd>
	  <dd><a href="#panel4" style="font-family: Raleway;">CustomizedItemSummaries</a></dd>
</dl>
<div class="tabs-content"  >
 <div class="content active" id='panel1'>
 <ul class='graph-orbit' data-orbit >
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/HospitalizationMeasuresTracking/image1.png" alt="slide 1" />
    </li>
    <li class="active">
      <img src="${pageContext.request.contextPath}/img/Graphs/HospitalizationMeasuresTracking/image2.png" alt="slide 2" />
    </li>
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/HospitalizationMeasuresTracking/image3.png" alt="slide 3" />
    </li>
  </ul>
  </div>
  <div class="content" id='panel2'>
   <ul class='graph-orbit' data-orbit>
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/ItemSummaries/image1.png" alt="slide 1" />
    </li>
    <li class="active">
      <img src="${pageContext.request.contextPath}/img/Graphs/ItemSummaries/image2.png" alt="slide 2" />
    </li>
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/ItemSummaries/image3.png" alt="slide 3" />
    </li>
     <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/ItemSummaries/image4.png" alt="slide 4" />
    </li>
       <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/ItemSummaries/image5.png" alt="slide 5" />
    </li>
    <li >
      <img src="${pageContext.request.contextPath}/img/Graphs/ItemSummaries/image6.png" alt="slide 6" />
    </li>
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/ItemSummaries/image7.png" alt="slide 7" />
    </li>
     <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/ItemSummaries/image8.png" alt="slide 8" />
    </li>
  </ul>
  </div>
   <div class="content" id='panel3'>
   <ul class='graph-orbit' data-orbit>
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/CustomizedTracking/image1.png" alt="slide 1" />
    </li>
    <li class="active">
      <img src="${pageContext.request.contextPath}/img/Graphs/CustomizedTracking/image2.png" alt="slide 2" />
    </li>
      </ul>
  </div>
  <div class="content" id='panel4'>
   <ul class='graph-orbit' data-orbit>
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/CustomizedItemSummaries/image1.png" alt="slide 1" />
    </li>
    <li class="active">
      <img src="${pageContext.request.contextPath}/img/Graphs/CustomizedItemSummaries/image2.png" alt="slide 2" />
    </li>
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/CustomizedItemSummaries/image3.png" alt="slide 3" />
    </li>
     <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/CustomizedItemSummaries/image4.png" alt="slide 4" />
    </li>
    <li>
      <img src="${pageContext.request.contextPath}/img/Graphs/CustomizedItemSummaries/image5.png" alt="slide 5" />
    </li>
  </ul>
  </div>
  </div>
  </div>
