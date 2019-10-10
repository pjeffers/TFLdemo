<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
<title>Tube Arrivals</title>

<link type="text/css"
      rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<% response.setIntHeader("Refresh", 10); %>
   	<div id="wrapper">
   		<div id="header">
   			<h1 align="center">Great Portland Street Tube Arrivals  </h1>
   		</div>
   	</div>
   	<div id="container">
   		<div id="content">
   		
               		     
   			<!-- add our table here -->
   			<table>
   				<tr>
   				    
   					<th>Error</th>

   				</tr>

   				  				
   				<tr>
   				    <td  align="left"> An Error has occurred retrieving arrival details. Please check your internet connection and try again later.</td>

   				</tr>
   			
   			</table>
   		
   		
   		
   		</div>
   	
   	</div>
</body>
</html>