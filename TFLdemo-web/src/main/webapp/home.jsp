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
   			<h1>Great Portland Street Tube Arrivals  </h1>
   		</div>
   	</div>
   	<div id="container">
   		<div id="content">
   		
               		     
   			<!-- add our table here -->
   			<table>
   				<tr>
   				    
   					<th>Destination</th>
    				<th>Platform</th>
   					<th>Arrives In</th>
   				</tr>
   			<!-- loop over and print customers -->
   				<c:forEach var="arrivalRecord" items="${arrivalSchedule.arrivalRecords}">
   				  				
   				<tr>
   				    <td  align="left">${arrivalRecord.destinationName}</td>
   				    <td  align="left">${arrivalRecord.platformName}</td>
   				    <td>${arrivalRecord.timeToStationInMinutes}</td>

   				</tr>
   				
   				
   				
   				</c:forEach>
   			
   			</table>
   		
   		
   		
   		</div>
   	
   	</div>
</body>
</html>