<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<title>Home</title>
<style>
	.success-message {
		margin-top:-4.8%;
	    background-color: #ccffcc; 
	    color: #006600; 
	    padding: 10px; 
	    border: 1px solid #006600; 
	    margin-bottom: 10px; 
	    
	}
	
	body{
		background-image: url('${pageContext.request.contextPath}/resources/immagini/sfondoIntesa.png');
		background-repeat: no-repeat; /* Prevent the image from repeating */
		border: none; 
		margin: 0; 
        padding: 0;
    	background-position: 0 60px;
    	background-size: cover;
	}
</style>
	<c:if test="${not empty successMessage}">
	    <div class="success-message">${successMessage}</div>
	</c:if>
	