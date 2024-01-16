let radioSelected = '';
let nomeGlobal = '';
let cognomeGlobal='';
let codCCGlobal='';
let incrementBarra=0;

window.onload = function () {
	loadCognomi();
    loadCodCCOptions();
    populateCitta();
};

	document.getElementById("ricercaBtn").addEventListener('click', controllaTipoRicerca);
	document.getElementById('btnSave').addEventListener('click', function(event) {
		console.log("***********************btnSaved clicked");
        event.preventDefault();
		console.log("codCCGlobal: "+codCCGlobal)
		if(validateForm()){
			const dataNascitaValue = document.getElementById('dataNascita').value;
			document.getElementById('dataNascita').value = dataNascitaValue;
			console.log(dataNascitaValue);
			document.getElementById("method").value = "modifica";			
			document.getElementById("viewCorrentistaForm").submit();
			disabilitaModifica();
		}
	});
	document.getElementById("btnDelete").addEventListener("click", function(event) {
        event.preventDefault();
        if(confirm("Confermi l'eliminazione?")){
        	abilitaModifica();
        	document.getElementById("method").value = "elimina";
        	console.log(document.getElementById("method").value);
        	document.getElementById("viewCorrentistaForm").submit();
        	disabilitaModifica();           	
        }
	});

    document.getElementById('cognomi').addEventListener('change', function () {
    	disabilitaModifica();
        const selectedCognome = this.value;
        const formCorrentista = document.getElementById('formCorrentista');
        formCorrentista.style.display = 'none';
        loadNomi(selectedCognome);
    });
    document.getElementById('nomi').addEventListener('change', function () {
        
        const formCorrentista = document.getElementById('formCorrentista');
        formCorrentista.style.display = 'none';
        disabilitaModifica();
    });
    
    document.getElementById("btnCancel").addEventListener('click', function(event) {
        event.preventDefault();
        annullaModifiche();
    });
    
    document.getElementById("btnMod").addEventListener('click', function(event) {
        event.preventDefault();
        abilitaModifica();
    });

    document.getElementById('cognomiRadio').addEventListener('click', function () {
        enableSelect('cognomi');
        enableSelect('nomi');
        const formCorrentista = document.getElementById('formCorrentista');
        formCorrentista.style.display = 'none';

        disabilitaModifica();
    });

    document.getElementById('codCCRadio').addEventListener('click', function () {
    	loadCodCCOptions();
        enableSelect('codCCSearch');
        const formCorrentista = document.getElementById('formCorrentista');
        formCorrentista.style.display = 'none';

        disabilitaModifica();
    });
    document.getElementById('codCCSearch').addEventListener('change', function () {
        // Remove the first blank option
        const formCorrentista = document.getElementById('formCorrentista');
        formCorrentista.style.display = 'none';
        if (document.getElementById('codCCSearch')[0].value === "") {
        	document.getElementById('codCCSearch').remove(0);
        }
    });
    
    function controllaTipoRicerca() {

    	disabilitaModifica();
        
        let radios = document.getElementsByName('selectType');
        let selectedRadio;

        for (let i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                selectedRadio = radios[i].id;
                break;
            }
        }

        if (selectedRadio === 'cognomiRadio') {
        	const cognomeValue=document.getElementById('cognomi').value;
        	const nomeValue=document.getElementById('nomi').value;
        	
        	console.log(document.getElementById('cognomi').value);
        	console.log(document.getElementById('nomi').value);

        	if(cognomeValue!=="" && nomeValue!==""){
        		console.log('Performing AJAX call for cognomiRadio');        
            	getCorrentistaByNomeCognome(nomeValue, cognomeValue);
        	}
        } else if (selectedRadio === 'codCCRadio') {
        	const codCCValue = document.getElementById('codCCSearch').value;
        	console.log(document.getElementById('codCCSearch').value);
            
            if(codCCValue!==""){
            	console.log('Performing AJAX call for codCCRadio');
            	getCorrentistaByCodCC(codCCValue)
            }
        }
        
    }
    async function getCorrentistaByNomeCognome(nome, cognome) {
        $.ajax({
            type: "GET",
            url: `/BancaIntesa/viewCorrentista.do?nome=${encodeURIComponent(nome)}&cognome=${encodeURIComponent(cognome)}`,
            dataType: "json",
            beforeSend: function () {
                console.log("beforeSend in azione!");
                $('#formCorrentista').hide();
                $('#ajax-panel').css('display', 'block');
                $('#ajax-panel').html('<img src="/BancaIntesa/resources/immagini/loading.gif" alt="Loading...">');
                // image src '${pageContext.request.contextPath}/resources/immagini/loading.gif'
            },
            complete: function () {
                $('#ajax-panel').hide();
                $('#formCorrentista').css('display', 'block');
            },
            success: function (response, stato) {
                console.log("response.nome: " + response.nome);
                popolaCampiByNomeCognome(response);
            },
            error: function (richiesta, stato, errori) {
                alert("E' avvenuto un errore. Il stato della chiamata:" + richiesta.statusText);
            }
        });
    }
    function popolaCampiByNomeCognome(data){
        //popolamento campi con valori tramite ajax
        nomeGlobal=data.nome;
        cognomeGlobal=data.cognome;
        document.getElementById('codAnag').value = data.codAnag;
        document.getElementById('cognome').value = data.cognome;
        document.getElementById('nome').value = data.nome;
        document.getElementById('telefono1').value = data.telefono1;
        document.getElementById('ragSoc').value = data.ragSoc;
        document.getElementById('sessoM').checked = data.sesso === 'M';
        document.getElementById('sessoF').checked = data.sesso === 'F';
        document.getElementById('telefono2').value = data.telefono2;
        document.getElementById('indirizzo').value = data.indirizzo;
        document.getElementById('citta').value = data.citta;
        document.getElementById('codCC').value = data.codCC;             	
        codCCGlobal=data.codCC;
        document.getElementById('luogoNascita').value = data.luogoNascita;
        document.getElementById('codFiscale').value = data.codFiscale;
        document.getElementById('partitaIva').value = data.partitaIva;
        document.getElementById('email').value = data.email;

        // Convert the date string to a JavaScript Date object
        const dateNascita = parseDateString(data.dataNascita);

        if (dateNascita instanceof Date && !isNaN(dateNascita.getTime())) {
            // Set the date input field with the Date object
            document.getElementById('dataNascita').value = dateNascita.toISOString().split('T')[0];

        } else {
            console.error('Invalid date format:', data.dataNascita);
        }
    }
    async function getCorrentistaByCodCC(codCC) {
        $.ajax({
            type: "GET",
            url: `/BancaIntesa/viewCorrentista.do?codCC=${codCC}`,
            dataType: "json",
            beforeSend: function () {
                console.log("beforeSend in azione!");
                $('#formCorrentista').hide();
                $('#ajax-panel').css('display', 'block');
                $('#ajax-panel').html('<img src="/BancaIntesa/resources/immagini/loading.gif" alt="Loading...">');
                // image src '${pageContext.request.contextPath}/resources/immagini/loading.gif'
            },
            complete: function () {
                $('#ajax-panel').hide();
                $('#formCorrentista').css('display', 'block');
            },
            success: function (response, stato) {
                console.log("response.nome: " + response.nome);
                popolaCampiByCodCC(response);
            },
            error: function (richiesta, stato, errori) {
                alert("E' avvenuto un errore. Il stato della chiamata:" + richiesta.statusText);
            }
        });
    }
    async function popolaCampiByCodCC(data) {
        // Populate form inputs with data
        document.getElementById('codAnag').value = data.codAnag;
        document.getElementById('cognome').value = data.cognome;
        document.getElementById('nome').value = data.nome;
        document.getElementById('telefono1').value = data.telefono1;
        document.getElementById('ragSoc').value = data.ragSoc;
        document.getElementById('sessoM').checked = data.sesso === 'M';
        document.getElementById('sessoF').checked = data.sesso === 'F';
        document.getElementById('telefono2').value = data.telefono2;
        document.getElementById('indirizzo').value = data.indirizzo;
        document.getElementById('citta').value = data.citta;
        document.getElementById('codCC').value = data.codCC;
        document.getElementById('luogoNascita').value = data.luogoNascita;
        document.getElementById('codFiscale').value = data.codFiscale;
        document.getElementById('partitaIva').value = data.partitaIva;
        document.getElementById('email').value = data.email;
        codCCGlobal=data.codCC;
        // Convert the date string to a JavaScript Date object
        const dateNascita = parseDateString(data.dataNascita);
        if (dateNascita instanceof Date && !isNaN(dateNascita.getTime())) {
            // Set the date input field with the Date object
            document.getElementById('dataNascita').value = dateNascita.toISOString().split('T')[0];
        } else {
            console.error('Invalid date format:', data.dataNascita);
        }
    }
    
    function enableSelect(selectId) {
        let selectElement = document.getElementById(selectId);
        selectElement.disabled = false;

        // Disable the other select element
        let otherSelectId = (selectId === 'cognomi') ? 'codCCSearch' : 'cognomi';
        document.getElementById(otherSelectId).disabled = true;

        // If codCCSearch is clicked, also disable the 'nome' select
        if (selectId === 'codCCSearch') {
            document.getElementById('cognomi').disabled = true;
            document.getElementById('nomi').disabled = true;
            document.getElementById('cognomi').value="";
            document.getElementById('nomi').value="";
            radioSelected="codCC";
            console.log("seleelelelc: "+radioSelected);
        } else {
            document.getElementById('cognomi').disabled = false;
            document.getElementById('nomi').disabled = false;
            document.getElementById('codCCSearch').value = "";
            radioSelected="cognomi";
        }
    }
    
    async function loadCodCCOptions() {
        try {
            console.log('Fetching CodCC data...');
            const response = await fetch('/BancaIntesa/getCodCC.do?method=getAllCodCC');
            
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            let select = $('#codCCSearch');
            select.empty(); 
    
            select.append('<option value="" selected></option>');
    
            data.forEach(codCC => {
                if (codCC != 0) {
                    let option = $('<option>').val(codCC).text(codCC);
                    select.append(option);
                }
            });
        } catch (error) {
            console.error('Error fetching CodCC data:', error);
        }
    }
    
    async function loadCognomi() {
        const response = await fetch('/BancaIntesa/getCognomi.do')
            .then(response => response.json())
            .then(data => {
                let select = document.getElementById('cognomi');

                data.forEach(cognome => {
                    let option = document.createElement('option');
                    option.value = cognome;
                    option.textContent = cognome;
                    select.appendChild(option);
                });
                
            })
            .catch(error => console.error('Error:', error));
    }
	
    
    async function loadNomi(cognome) {
        const nomiSelect = document.getElementById('nomi');

        if (cognome) {
            nomiSelect.innerHTML = '';
            fetch('/BancaIntesa/getNomi.do?cognome=' + cognome)
                .then(response => response.json())
                .then(data => {
                    let select = document.getElementById('nomi');
                    data.forEach(nome => {
                        let option = document.createElement('option');
                        option.value = nome;
                        option.textContent = nome;
                        select.appendChild(option);
                    });
                })
                .catch(error => console.error('Error:', error));
            if (document.getElementById('cognomi')[0].value === "") {
            	document.getElementById('cognomi').remove(0);
            }
        }
    }
    function annullaModifiche(){
    	switch(radioSelected){
    	case "cognomi":
    		getCorrentistaByNomeCognome(nomeGlobal, cognomeGlobal);
    		break;
    	case "codCC":
    		console.log("annull codCC")
    		console.log("cc global: "+codCCGlobal);
    		getCorrentistaByCodCC(codCCGlobal);
    		break;
    	}
    	disabilitaModifica();
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
	async function populateCitta() {
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
	    })
	    .catch(error => console.error('Error:', error));
	}
	function disabilitaModifica(){
		document.getElementById('cognome').setAttribute('readonly', 'readonly');
        document.getElementById('nome').setAttribute('readonly', 'readonly');
        document.getElementById('telefono1').setAttribute('readonly', 'readonly');
        document.getElementById('ragSoc').setAttribute('readonly', 'readonly');
        document.getElementById('sessoM').setAttribute('disabled', 'disabled');
        document.getElementById('sessoF').setAttribute('disabled', 'disabled');
        document.getElementById('telefono2').setAttribute('readonly', 'readonly');
        document.getElementById('indirizzo').setAttribute('readonly', 'readonly');
        document.getElementById('citta').setAttribute('disabled', 'disabled');
        document.getElementById('dataNascita').setAttribute('disabled', 'disabled');
        document.getElementById('luogoNascita').setAttribute('readonly', 'readonly');
        document.getElementById('codFiscale').setAttribute('readonly', 'readonly');
        document.getElementById('partitaIva').setAttribute('readonly', 'readonly');
        document.getElementById('email').setAttribute('readonly', 'readonly');


        document.getElementById('btnMod').removeAttribute('disabled');
        document.getElementById('btnDelete').removeAttribute('disabled');
        // Disable the Save button
        document.getElementById('btnSave').setAttribute('disabled', 'disabled');
        document.getElementById('btnCancel').setAttribute('disabled', 'disabled');
	}
	function abilitaModifica(){
		document.getElementById('cognome').removeAttribute('readonly');
        document.getElementById('nome').removeAttribute('readonly');
        document.getElementById('telefono1').removeAttribute('readonly');
        document.getElementById('ragSoc').removeAttribute('readonly');
        document.getElementById('sessoM').removeAttribute('disabled');
        document.getElementById('sessoF').removeAttribute('disabled');
        document.getElementById('telefono2').removeAttribute('readonly');
        document.getElementById('indirizzo').removeAttribute('readonly');
        document.getElementById('citta').removeAttribute('disabled');
        document.getElementById('dataNascita').removeAttribute('disabled');
        document.getElementById('luogoNascita').removeAttribute('readonly');
        document.getElementById('codFiscale').removeAttribute('readonly');
        document.getElementById('partitaIva').removeAttribute('readonly');
        document.getElementById('email').removeAttribute('readonly');

        // Enable the Save button
        // Disable the Modifica and Elimina buttons
        document.getElementById('btnMod').setAttribute('disabled', 'disabled');
        document.getElementById('btnDelete').setAttribute('disabled', 'disabled');
        document.getElementById('btnSave').removeAttribute('disabled');
        document.getElementById('btnCancel').removeAttribute('disabled');
	}
	
	function validateForm() {
		console.log("******************************************validate form function");
	    // Get input values
	    let errore = false;
	    let cognome = document.getElementById("cognome").value.trim();
	    let nome = document.getElementById("nome").value.trim();
	    let telefono1 = document.getElementById("telefono1").value.trim();
	    let telefono2 = document.getElementById("telefono2").value.trim();
	    let ragSoc = document.getElementById("ragSoc").value.trim();
	    let sessoM = document.getElementById("sessoM");
	    let sessoF = document.getElementById("sessoF");
	    let sesso="";

	    let indirizzo = primaLetteraMaiuscola(document.getElementById("indirizzo").value.trim());
	    let citta = document.getElementById("citta").value.trim();
	    let luogoNascita = document.getElementById("luogoNascita").value.trim();
	    let codFiscaleInput = document.getElementById("codFiscale");
	    let codFiscale= codFiscaleInput.value.trim().toUpperCase();
	    codFiscaleInput.value = codFiscale;
	    
	    let partitaIva = document.getElementById("partitaIva").value.trim();
	    let email = document.getElementById("email").value.trim();
	    let codCC = document.getElementById("codCC").value.trim();
	    // Validation regex patterns
	    let codFiscaleRegex = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
	    let partitaIvaRegex = /^\d{11}$/;
	    let telefonoRegex = /^\+\d{1,} \d{8,10}$/;
	    let indirizzoRegex = /^(Corso|Via|Viale|Piazza|Largo|Strada)\s[\w\sÀ-ú]+\d*[A-z]?$/;
	    let codCCRegex = /^\d{12}$/;
	    let emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.(com|it|net)$/;

	    // Perform validations
	    if (!/^[A-zÀ-ú\s]+$/.test(cognome)) {
	        alert("Cognome non valido. Sono ammessi solo lettere e lettere accentate.");
	        errore = true;
	    }

	    if (!/^[A-zÀ-ú\s]+$/.test(nome)) {
	        alert("Nome non valido. Sono ammessi solo lettere e lettere accentate.");
	        errore = true;
	    }
	    if (!telefonoRegex.test(telefono1)) {
	        //alert("Telefono 1 non valido. Utilizzare il formato: +<codice paese> <numero di telefono>");
	        if(!(/^\d{10}$/).test(telefono1)){
	        	alert("Telefono 1 non valido");
	        	errore = true;
	        }
	    }

	    if (telefono2 !== "" && !telefonoRegex.test(telefono2)) {
	        //alert("Telefono 2 non valido. Utilizzare il formato: +<codice paese> <numero di telefono>");
	    	if(!(/^\d{10}$/).test(telefono1)){
		    	alert("Telefono 2 non valido");
		        errore = true;
	    	}
	    }
	    if (sessoM.checked) {
	        sesso = sessoM.value;
	        // rest of your code
	    }else if(sessoF.checked){
	    	sesso = sessoF.value;
	    } 
	    else {
	        alert("Sesso non valido. Selezionare 'M' o 'F'.");
	        errore = true;
	    }
	    if (!/^[A-zÀ-ú\s]+$/.test(ragSoc)) {
	        alert("Ragione Sociale non valida. Sono ammessi solo lettere e lettere accentate.");
	        errore = true;
	    }

	    if (sesso !== "M" && sesso !== "F") {
	        alert("Sesso non valido. Selezionare 'M' o 'F'.");
	        errore = true;
	    }

	    if (!indirizzoRegex.test(indirizzo)) {
	        alert("Indirizzo non valido. Utilizzare il formato: <Tipo> NomeVia");
	        errore = true;
	    }

	    if (!/^[A-zÀ-ú\s]+$/.test(citta)) {
	        alert("Città non valida. Sono ammesse solo lettere e lettere accentate.");
	        errore = true;
	    }

	    if (!/^[A-zÀ-ú\s]+$/.test(luogoNascita)) {
	        alert("Luogo di Nascita non valido. Sono ammesse solo lettere e lettere accentate.");
	        errore = true;
	    }

	    if (!codFiscaleRegex.test(codFiscale) && codFiscale!="") {
	        alert("Codice Fiscale non valido.");
	        errore = true;
	    }
	    
	    if (!partitaIvaRegex.test(partitaIva) && partitaIva!="") {
	        alert("Partita IVA non valida.");
	        errore = true;
	    }
	    if (codCC!=codCCGlobal && codCC!="") {
	    	console.log("codCCGlobal: "+codCCGlobal+"   codCC: "+codCC);
	        alert("Num. conto non valido.");
	        errore = true;
	    }
	    if (!emailRegex.test(email) && email!="") {
	        alert("Email non valida. Inserire un indirizzo email valido.");
	        errore = true;
	    }

	    // If all validations pass, you can proceed with further logic or submit the form
	    if (!errore) {
	    	console.log("Il modulo è valido. Invio in corso...");
	        if (confirm("Confermi l'inserimento?")) {
	        	return true;
	        }
	    }else{
	    	return false;
	    	alert("riprova")
	    }
	}
	function primaLetteraMaiuscola(str) {
	    return str.charAt(0).toUpperCase() + str.slice(1);
	}
