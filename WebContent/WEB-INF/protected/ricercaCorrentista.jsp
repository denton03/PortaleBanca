<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<title>Modifica correntista</title>
<style>
	#cognomi{
		margin-left:6%;
	}
	#codCCSearch{
		margin-left:2%;
	}
	.divRicerca{
		margin-left:30%;
		border: 1px solid white;
		padding: 1.5%;
		padding-top:3%;
		padding-bottom:3%;
		padding-left:2%;
		width:38%;
	}
	.divRicerca select{
		width: 23%;
	}
	.divRicerca #nomi{
		margin-left:2%;
	}
	.tabBtn{
		margin-left:59%;
	}
	.tabBtn button{
		width:70px;
		border-radius:0%;
	}
	.formCorrentista{
		margin-top:2%;
		margin-left:18%;
		padding-left:3%;
		padding-bottom:1%;
		background-color:#84b71f;
		width:65%;
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
	body{
		background-color:#007025;
	}
	#btnChiudi{
		width:70px;
		border-radius:0%;
		margin-left:86.5%;
	}
	#h2Form{
		margin-left:30%;
		margin-bottom:30px;
		color:#007025;
	}
	#viewCorrentistaForm td, #viewCorrentistaForm th {
    	border: 0;
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
	#ajax-panel .progress-bar {
		background-color:yellow;
		height:20%;
	}
	#ajax-panel {
		margin-top: 5%;
		margin-left: 40%;
		height:20%;
	}
	#ajax-panel div{
		height:20%;
	}
	table, th,td{
   		padding: 7px;
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
<div class="divRicerca">
	<div>
	    <input type="radio" name="selectType" id="cognomiRadio"> 
		<label for="selectType">Cognome:</label>
	    <select name="cognome" id="cognomi" disabled>
	        <option value="" selected></option>
	    </select>
 		<label for="nome" style="margin-left:20px">Nome:</label>
	    <select name="nome" id="nomi" disabled></select>
		<button style="margin-left:30px;" id="ricercaBtn">Cerca</button>
	</div>
	
	<div style="margin-top:20px;">
	    <input type="radio" name="selectType" id="codCCRadio"> 
		<label for="selectType" >Codice conto:</label>
	    <select name="codCC" id="codCCSearch" disabled>
	        <option value="" selected></option>
	    </select>
	</div>
	
	<div>
	</div>
</div>
<span id="ajax-panel"></span>
<div class="formCorrentista" id="formCorrentista" style="display:none;">
	<h2 id="h2Form">Modifica anagrafica correntista</h2>
    <form id="viewCorrentistaForm" action="modifica.do" accept-charset="UTF-8" method="post">
        <table>
        	<input type="hidden" id="method" name="method" />
            <tr>
            	<input type="hidden" name="codAnag" id="codAnag"/>
                <td><label for="cognome">Cognome*:</label></td>
                <td><input type="text" id="cognome" name="cognome" readonly ></td>
                <td><label for="nome">Nome*:</label></td>
                <td><input type="text" id="nome" name="nome" readonly></td>
                <td><label for="telefono1">Telefono 1*:</label></td>
                <td><input type="text" id="telefono1" name="telefono1" readonly></td>
            </tr>
            <tr>
                <td><label for="ragSoc">Ragione Sociale*:</label></td>
                <td><input type="text" id="ragSoc" name="ragSoc"></td>
				<td colspan="2">
				    <label for="sesso">Sesso*:</label>
				    <input type="radio" id="sessoM" name="sesso" value="M"  disabbled > Maschio
				    <input type="radio" id="sessoF" name="sesso" value="F"  disabbled > Femmina
				</td>

                <td><label for="telefono2">Telefono 2:</label></td>
                <td><input type="text" id="telefono2" name="telefono2" readonly ></td>
            </tr>
            <tr>
                <td><label for="indirizzo">Indirizzo*:</label></td>
                <td colspan="3"><input type="text" id="indirizzo" name="indirizzo"  readonly ></td>
                <td><label for="citta">Città*:</label></td>
                <td><select id="citta" name="citta" disabled></select></td>
                
            </tr>
            <tr>
                <td><label for="dataNascita">Data di Nascita*:</label></td>
                <td><input type="date" id="dataNascita" name="dataNascita"  disabled ></td>
                <td><label for="luogoNascita">Luogo di Nascita*:</label></td>
                <td><input type="text" id="luogoNascita" name="luogoNascita"  readonly></td>
                <td ><label for="codCC">Num. Conto</label></td>
                <td rowspan="2"><input type="number" id="codCC" name="codCC" readonly></td>
            </tr>
            <tr>
                <td><label for="codFiscale">Codice Fiscale:</label></td>
                <td><input type="text" id="codFiscale" name="codFiscale" readonly></td>
                <td><label for="partitaIva">Partita IVA:</label></td>
                <td><input type="text" id="partitaIva" name="partitaIva" readonly></td>
                <!-- Add your new fields here -->
            </tr>
            <tr>
            </tr>
			<tr>
			    <td><label for="email">Email:</label></td>
			    <td><input type="text" id="email" name="email" readonly></td>
			</tr>
        </table>
		<table style="border:0px;" class="tabBtn">
			<tr>
				<td>
				<button type="button" id="btnMod">Modifica</button></td>
				<td><button type="button" id="btnDelete">Elimina</button></td><td><button type="button" id="btnSave" >Salva</button></td><td><button type="button" id="btnCancel" disabled>Annulla</button></td>
			</tr>
		</table>
        <button id="btnChiudi" type="button" onclick="nascondiCorrentista()">Chiudi</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    
<script src="${pageContext.request.contextPath}/resources/js/scriptModificaRicercaCorrentista.js">
    
</script>