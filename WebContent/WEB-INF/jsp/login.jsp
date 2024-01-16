<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>Login</title>
<style>
 .login-form {
 		margin-top:5%;
        margin-left: 40%;
        border: 3px solid green;
        width: 320px;
        border-radius: 5px 5px 5px 5px;
        background-color:white;
    }

    .divTitolo {
    	padding-left:10px;
        margin-top: -25px;
        color: white;
        width: 310px;
        background-color: green;
        border-radius: 5px 5px 0px 0px;
    }

    .divInput {
        margin-left: 15px;
    }

    .divInput input {
        width: 110px;
    }

    .divInput #username {
        margin-left: 12%;
        position: relative;
    }

    .divInput #password {
        margin-top: 15px;
        margin-left: 1%;
    }

    .divInput #passLabel {
        margin-top: 15px;
    }

    .divInput #btnLogin {
        margin-top: 10px;
        margin-left: 75px;
        width:110px;
        color:white;
        background-color:green;
    }

    .imgLogin {
        /* float: right;
        margin-top: -120px; 
        margin-left: 15px;
        margin-right: 15px; */ 
		float: right;
        margin-top: -120px; 
        margin-left: 15px;
        margin-right: 15px;
        
    }
	.divInput button{
		cursor:pointer;
	}
	#btnLogin:hover{
		background-color:#003300;
	}

    .error-message {
    	
    	width:27%;
        background-color: #ffcccc;
        color: #cc0000;
        padding: 10px;
        border: 1px solid #cc0000;
        padding-left:17%;
    	margin-top:30px;
		margin-left:30%;        
        margin-bottom: 10px;
    }
    body{
		background-image: url('${pageContext.request.contextPath}/resources/immagini/sfondoIntesa.png');
		background-repeat: no-repeat; 
		border: none; 
		margin: 0; 
        padding: 0;
    	background-position: 0 50px;
    	background-size: cover;
	}
	
</style>

	<div class="login-form">
		<div class="divTitolo">
		    <h5>Accedi al tuo conto</h5>		
		</div>
	    <div class="divInput">
	        <form id="formLogin" action="login.do" method="post"  onkeyup="if (event.keyCode == 13) this.submit();">
	            <label for="username">User:</label>
	            <input type="text" id="username" name="username" required/><br/>
	            <label for="password" id="passLabel">Password:</label>
	            <input type="password" id="password" name="password" required/><br/>
	            <button id="btnLogin" type="button" onclick="validateForm()">Login</button>
	        </form>
	    </div>

	    <div class="imgLogin">
	        <img id="imgLogin" src="${pageContext.request.contextPath}/resources/immagini/immLoginIntesaSPaolo.png"></img>
	    </div>
    </div>
	    <%-- Display error message if it exists --%>
	    <c:if test="${not empty errorMessage}">
	<div class="error-message">${errorMessage}</div>
	    </c:if>
	    
<script>
	function validateForm() {
	    // Get input values
	    let errore = false;
	    let user = document.getElementById("username").value.trim();
	    let password = document.getElementById("password").value.trim();
	
	    // Perform validations
	    if (user=="") {
	        alert("Username vuoto non valido");
	        errore = true;
	    }else if(!/^[A-z\s]+$/.test(user)){
	    	alert("Username non valida");
	        errore = true;
	    }
	
	    if (password=="") {
	        alert("Password vuota non valida");
	        errore = true;
	    }else if(!(/^[A-Za-z0-9%$]{5,}$/).test(password)){
	    	alert("Password non valida");
	        errore = true;
	    }
	    
	    // If all validations pass, you can proceed with further logic or submit the form
	    if (!errore) {
			document.getElementById("formLogin").submit();
	    }else{
	    	alert("riprova")
	    }
	}
</script>
	
