<%-- 
    Document   : index
    Created on : Nov 21, 2009, 6:14:51 PM
    Author     : leo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="yui.classes.*" %>
<jsp:include page="inc/config.jsp" />
    <%
      YUI_util_Loader  loader= new YUI_util_Loader("2.7.0",pageContext);
    //Specify YUI components to load

    loader.load("yahoo", "dom","calendar", "event", "tabview", "grids", "fonts", "reset");
    //Output the tags (this call would most likely be placed in the document head)
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<html>
<head>
	<title>YUI Java  Loader Utility Advanced Example: Loading YUI Calendar with the YUI Java Loader Utility</title>


         <%=loader.css()%>
</head>

<body class="yui-skin-sam">

<h1>YUI Java Loader Utility Advanced Example: Loading the YUI Calendar Control with the YUI Java Loader Utility</h1>

<p>In this example, we bring a YUI component onto the page using the <a href="http://developer.yahoo.com/yui/Javaloader/">YUI Java Loader Utility</a>. This example implements YUI Java Loader via a <code>YAHOO_util_Loader</code> instance.  We include the YUI Java Loader class file, then specify in configuration which component we want loaded on the page &mdash; and what we want to do once it <em>is</em> loaded.  The main difference between this advanced example and the simple one show <a href="./Javaloader-basic.html">here</a> is that this one outputs the CSS and JavaScript files separately with two different method calls.  The CSS files are included in the document head and the JavaScript files are included just before the closing body node.  This is done in accordance with the best practice performance recommendations outlined <a href="http://developer.yahoo.com/performance/rules.html">here</a>.</p>

<div id="calendar_container"></div>

  <%=loader.script()%>
<script type="text/javascript">
    YAHOO.util.Event.onAvailable("calendar_container", function() {
		var myCal = new YAHOO.widget.Calendar("mycal_id", "calendar_container");
		myCal.render();
	})
</script>
</body>
</html>
