<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Elenco Correntisti</title>
<style>
    .tabCorrentisti {
	    margin-left: 25%;
	    display: block;
	}
	.tabCorrentisti table {
	}
	/*.tabCorrentisti th {
	    border:1px solid white;
	    color: white;
	    color: white;
	    border-bottom: none; 
	    width: 2%;
	    text-align: center;
	}*/
	.tabHead tr {
        border: none;
    }
    .tabHead td {
        color: white;
    }

	.tabCorrentisti tbody{
	    /*display: block; 
	    height: 100px; 
	    overflow-y: auto;
	    border:1px solid green;*/
	
	}
	
	.tabCorrentisti tbody td {
	    width: 20.15%;
	    padding-top: 0.5%;
	    padding-left: 10px;
	    padding-bottom: 0.5%;
	}
	
	.tabCorrentistiBody tr {
	   display: table-row;
	}
	
	.tabCorrentistiBody td {
	   display: table-cell;
	}
	.tabCorrentistiBody tr.even {
    	background-color: white;
	}
	
	.tabCorrentistiBody tr.odd {
	    background-color: #ffdd57;
	}
	.tableRow td {
	    text-align: left;
	}
	/*form dettagli correntista*/
	.formCorrentista {
		margin-top:2%;
	    margin-left: 16%;
	    padding-left: 7%;
	    background-color: #84b71f;
	    width: 65%;
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
	

	
	/*#tabCorrentisti{
		border-collapse:collapse;
		display: block; 
	    height: 250px; 
	    overflow-y: auto;
	    border:1px solid green;
	}*/
	#tabCorrentisti {
	    border-collapse: collapse;
	    width: 43%;           
	    border: 1px solid green;
	}
	#tabCorrentisti thead td {
	    border: 1px solid white;
	    border-bottom: none;
	    width: 19.88%;      
	    text-align: center;
	    padding: 10px; 
	}
	#tabCorrentisti thead,
	#tabCorrentisti tbody {
	   display: block;
	   width: 100%; 
	}
	#tabCorrentisti td:nth-child(5) {
	    width: 30%; 
	}
	#tabCorrentistiBody {
		
	    max-height: 250px; /* Adjust the max-height as needed */
	    overflow-y: auto;
	}
	#tabCorrentistiBody tr {
	    display: table-row;
	}
	#tabCorrentistiBody td {
	    display: table-cell;
	    border: 1px solid green;
	    padding: 10px; 
	    color:black;
	}
	#viewCorrentistaForm td,
	#viewCorrentistaForm tr {
	    border: 0;
	}
	
	#codCC {
	    padding-top: 10%;
	    padding-bottom: 10%;
	    display: block;
	    box-sizing: border-box;
	}
	
	#indirizzo {
	    width: 100%;
	}
	
	#btnVisualizza {
	    cursor: pointer;
	    background-image: url('${pageContext.request.contextPath}/resources/immagini/lente.jpg');
	    background-size: cover;
	    background-repeat: no-repeat;
	    background-position: center;
	    border: none;
	    color: transparent;
	    margin-left: 36%;
	    width: 30px;
	    height: 30px;
	}
	
	#h2Form {
	    margin-left: 34%;
	    margin-bottom: 30px;
	    color: #007025;
	}
	
	#btnCancel {
	    border-radius: 3%;
	    margin-left: 81%;
	    margin-bottom: 15px;
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
	body {
	    background-color: #007025;
	}
	tbody td{
		color:black;
	}
	#btnCancel{
	    cursor: pointer;
	}
	#btnCancel:hover{
		background-color:#a6a6a6;
	}
</style>
<body>
<table class="tabCorrentisti" id="tabCorrentisti" >
	<thead class="tabHead">
		<tr >
			<td>Seleziona</td>
			<td>Cognome</td>
			<td>Nome</td>
			<td>Ragione Sociale</td>
			<td>Telefono</td>
		</tr>
	</thead>
		<tbody id="tabCorrentistiBody" class="tabCorrentistiBody">
            <c:forEach items="${beanCorrentisti.elencoCorrentisti}" var="correntista" varStatus="loop">
                <form method="post" id="form${loop.index}">
                    <tr class="tableRow ${loop.index % 2 == 0 ? 'even' : 'odd'}">
                        <td><input type="button" id="btnVisualizza" name="codAnag" value="${correntista.codAnag}"></input></td>
                        <td><label>${correntista.cognome}</label></td>
                        <td><label>${correntista.nome}</label></td>
                        <td><label>${correntista.ragSoc}</label></td>
                        <td ><label>${correntista.telefono1}</label></td>
                    </tr>
                </form>
            </c:forEach>
		</tbody>
</table>

<span id="ajax-panel"></span>
<div class="formCorrentista" id="formCorrentista" style="display:none;">
	<h2 id="h2Form">Dati correntista</h2>
    <form id="viewCorrentistaForm" accept-charset="UTF-8">
        <table class="tabDatiCorrentista">
            <tr>
                <td><label for="cognome">Cognome*:</label></td>
                <td><input type="text" id="cognome" name="cognome" readonly></td>
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
				    <input type="radio" id="sessoM" name="sesso" value="M"  disabled> Maschio
				    <input type="radio" id="sessoF" name="sesso" value="F"  disabled> Femmina
				</td>

                <td><label for="telefono2">Telefono 2:</label></td>
                <td><input type="text" id="telefono2" name="telefono2" readonly></td>
            </tr>
            <tr>
                <td><label for="indirizzo">Indirizzo*:</label></td>
                <td colspan="3"><input type="text" id="indirizzo" name="indirizzo"  readonly></td>
                <td><label for="citta">Città:</label></td>
                <td><select id="citta" name="citta"   disabled></select></td>
                
            </tr>
            <tr>
                <td><label for="dataNascita">Data di Nascita*:</label></td>
                <td><input type="date" id="dataNascita" name="dataNascita" readonly ></td>
                <td><label for="luogoNascita">Luogo di Nascita*:</label></td>
                <td><input type="text" id="luogoNascita" name="luogoNascita"   readonly></td>
                <td ><label for="codCC">Num. Conto</label></td>
                <td rowspan="2"><input type="number" id="codCC" name="codCC"   readonly></td>
            </tr>
            <tr>
                <td><label for="codFiscale">Codice Fiscale:</label></td>
                <td><input type="text" id="codFiscale" name="codFiscale"  readonly></td>
                <td><label for="partitaIva">Partita IVA:</label></td>
                <td><input type="text" id="partitaIva" name="partitaIva" readonly></td>
                <!-- Add your new fields here -->
            </tr>
            <tr>
            </tr>
        </table>

        <button type="button" id="btnCancel" onclick="nascondiCorrentista()">Chiudi</button>
    </form>
</div>
</body>		
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>	
<script type="text/javascript">
	
	document.addEventListener('DOMContentLoaded', function () {
		popolaCitta();
	    const btnVisualizzaList = document.querySelectorAll('#btnVisualizza');
		btnVisualizzaList.forEach(btnVisualizza => {
				btnVisualizza.addEventListener('click', function () {
					const codAnag = this.value;
					console.log("this.value: "+this.value);
					mostraCorrentista(codAnag);
				});
		});
	});
	async function mostraCorrentista(codAnag) {
		console.log("codAnag: "+codAnag);
		$.ajax({
			type: "GET",
			url: `/BancaIntesa/viewCorrentista.do?`,
    		data: { codAnag: codAnag },
			dataType: "json",
			beforeSend: function(){
				$('#formCorrentista').css('display', 'none');
				$('#ajax-panel').css('display','block');
				$('#ajax-panel').html("<img src='/BancaIntesa/resources/immagini/loading.gif' alt='Caricamento'>");
			},
			complete: function(){
				$('#ajax-panel').css('display','none');	
				$('#formCorrentista').css('display','block');
			},
			success: function(response, stato){
				populateFormCorrentista(response);
				
			},
			error: function(richiesta, stato, errori){
				alert("Errore stato: "+richiesta.statusText);
			}
		});
	}   
	function populateFormCorrentista(data) {
		// Populate form inputs with data
		document.getElementById('cognome').value = data.cognome;
		document.getElementById('nome').value = data.nome;
		document.getElementById('telefono1').value = data.telefono1;
		document.getElementById('ragSoc').value = data.ragSoc;
		document.getElementById('sessoM').checked = data.sesso === 'M';
		document.getElementById('sessoF').checked = data.sesso === 'F';
		document.getElementById('telefono2').value = data.telefono2;
		document.getElementById('luogoNascita').value = data.luogoNascita;
		document.getElementById('indirizzo').value = data.indirizzo;
		document.getElementById('citta').value = data.citta;
			document.getElementById('codCC').value = data.codCC;             	
		if(data.codCC>0){
		}
		// Convert the date string to a JavaScript Date object
		const dateNascita = parseDateString(data.dataNascita);

		if (dateNascita instanceof Date && !isNaN(dateNascita.getTime())) {
			// Set the date input field with the Date object
			document.getElementById('dataNascita').value = dateNascita.toISOString().split('T')[0];

			const formCorrentista = document.getElementById('formCorrentista');
			formCorrentista.style.display = 'block';
		} else {
			console.error('Invalid date format:', data.dataNascita);
		}
	}
	function nascondiCorrentista() {
        console.log("sus")
        const formCorrentista = document.getElementById('formCorrentista');
        formCorrentista.style.display = "none";
    }
	
	function parseDateString(dateString) {
	    const months = {
	        'gen': 0, 'feb': 1, 'mar': 2, 'apr': 3, 'mag': 4, 'giu': 5,
	        'lug': 6, 'ago': 7, 'set': 8, 'ott': 9, 'nov': 10, 'dic': 11
	    };

	    const dateParts = dateString.split(' ');
	    const month = months[dateParts[0].toLowerCase()];
	    const day = parseInt(dateParts[1].replace(',', ''), 10);
	    const year = parseInt(dateParts[2], 10);

	    if (!isNaN(month) && !isNaN(day) && !isNaN(year)) {
	        return new Date(year, month, day);
	    } else {
	        console.error('Invalid date format:', dateString);
	        return null;
	    }
	}
	async function popolaCitta() {
		console.log("populateCitta")
		console.log("populateCItta");
	    fetch('/BancaIntesa/getDescrizioneComuni.do')
	    .then(response => response.json())
	    .then(data => {
	        let select = document.getElementById('citta');
	        data.forEach(descrizione => {
	            let option = document.createElement('option');
	            option.value = descrizione;
	            option.textContent = descrizione;
	            select.appendChild(option);
	        });
            let option = document.createElement('option');
            option.value = "";
            option.textContent = "";
            select.appendChild(option);
	    })
	    .catch(error => console.error('Error:', error));
	}
</script>