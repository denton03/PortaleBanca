<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<title>Inserimento Correntista</title>
<style>
	.content{
		margin-left:13%;
		padding-left:3%;
		
		background-color:#84b71f;
		width:67%;
	}
	.content tr {
		border-collapse: separate;
    	border-spacing: 0 10px;
    }

	.tabDatiCorrentista tr {
		border-collapse: separate;
    	border-spacing: 0 10px;
    }
    .tabDatiCorrentista td {
    	padding-bottom: 20px;
	}
    .tabDatiCorrentista tr:last-child td {
    	padding-bottom: 0; 
	}
	.divBtn{
		margin-left:79%;
		margin-top:15px;
		padding-bottom: 15px;
	}
	.divBtn button{
		border-radius:4%;
	}
	.divBtn #btnSave{
		margin-right:7%;
		padding-left:7%;
		padding-right:7%;
		border-radius:4%;
	}
	
		.error-message {
	    background-color: #ffcccc; 
	    color: #cc0000; 
	    padding: 10px; 
	    border: 1px solid #cc0000; 
	    margin-bottom: 10px; 
	}
	
	.success-message {
	    background-color: #ccffcc; 
	    color: #006600; 
	    padding: 10px; 
	    border: 1px solid #006600; 
	    margin-bottom: 10px; 
	}
	#content{
		background-color:#84b71f;
	}
	#h2Form{
		padding-top:15px;
		margin-bottom:30px;
		color:#007025;
	}
	#codCC{
	    padding-top:10%;
	    padding-bottom: 10%;
	     display:block;
	    box-sizing: border-box;
	}
	#indirizzo{
		width:100%;
	}
	body{
		background-color:#007025;
	}
	label{
 		margin-left: 30px;
	}
	button{
		cursor:pointer;
	}
	button:hover{
		background-color:#a6a6a6;
	}
</style>
	<!-- Display success messages -->
	<c:if test="${not empty successMessage}">
	    <div class="success-message">${successMessage}</div>
	</c:if>
	
	<!-- Display error messages -->
	    <c:if test="${not empty errorMessage}">
	        <div class="error-message">${errorMessage}</div>
	    </c:if>
	
<div class="content">
	<h2 id="h2Form">Inserimento anagrafica correntista</h2>
    <form id="registrationForm" action="inserimentoCorrentista.do" method="post" accept-charset="UTF-8">
        <table class="tabDatiCorrentista">
            <tr>
                <td><label for="cognome">Cognome*:</label></td>
                <td><input type="text" id="cognome" name="cognome" required></td>
                <td><label for="nome">Nome*:</label></td>
                <td><input type="text" id="nome" name="nome" required></td>
                <td><label for="telefono1">Telefono 1*:</label></td>
                <td><input type="text" id="telefono1" name="telefono1" placeholder="+prefix num" required></td>
            </tr>
            <tr>
                <td><label for="ragSoc">Ragione Sociale*:</label></td>
                <td><input type="text" id="ragSoc" name="ragSoc"></td>
                <td colspan="2">
                    <label for="sesso">Sesso*:</label>
                    <input type="radio" id="sessoM" name="sesso" value="M" required> Maschio
                    <input type="radio" id="sessoF" name="sesso" value="F" required> Femmina
                </td>
                
                <td><label for="telefono2">Telefono 2:</label></td>
                <td><input type="text" id="telefono2" placeholder="+prefix num"  name="telefono2"></td>
            </tr>
            <tr>
                <td><label for="indirizzo">Indirizzo*:</label></td>
                <td colspan="3"><input type="text" id="indirizzo" name="indirizzo" placeholder="Via Viale Corso Piazza Largo Strada"></td>
                <td><label for="citta">Città*:</label></td>
                <td><select id="citta" name="citta" required></select></td>
                
            </tr>
            <tr>
                <td><label for="dataNascita">Data di Nascita*:</label></td>
                <td><input type="date" id="dataNascita" name="dataNascita" required></td>
                <td><label for="luogoNascita">Luogo di Nascita*:</label></td>
                <td><input type="text" id="luogoNascita" name="luogoNascita" required></td>
                <td ><label for="codCC">Num. Conto</label></td>
                <td rowspan="2"><input type="text" id="codCC" name="codCC" readonly></td>
            </tr>
            <tr>
                <td><label for="codFiscale">Codice Fiscale:</label></td>
                <td><input type="text" id="codFiscale" name="codFiscale"></td>
                <td><label for="partitaIva">Partita IVA:</label></td>
                <td><input type="text" id="partitaIva" name="partitaIva"></td>
                <!-- Add your new fields here -->
            </tr>
            <tr>
            </tr>
			<tr>
			    <td><label for="email">Email:</label></td>
			    <td><input type="text" id="email" name="email"></td>
			</tr>

        </table>
		<div class="divBtn">
	        <button type="button" id="btnSave" onclick="validateForm()">Save</button>
    	    <button type="button" onclick="resetForm()">Cancel</button>	
		</div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/resources/js/script-form-nuovo-correntista.js" charset="UTF-8"></script>
