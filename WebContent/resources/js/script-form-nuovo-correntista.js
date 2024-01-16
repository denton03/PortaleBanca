let codCCGlobal='';
window.onload = function(){
	populateNumConto();
	populateCitta();
}
			async function populateNumConto(){
				console.log("sus");
				const response = await fetch('/BancaIntesa/getCodCC.do?method=getLastCodCC');
				
				if (!response.ok) {
					throw new Error(`HTTP error! Status: ${response.status}`);
				}

				const data = await response.json();
				let numConto = document.getElementById('codCC');
				numConto.value=data;
				codCCGlobal=data;
			}
			function populateCitta() {
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
			


function validateForm() {
    // Get input values
	console.log("codCCGlobal: "+codCCGlobal);
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
    let codFiscale = document.getElementById("codFiscale").value.trim().toUpperCase();
    let partitaIva = document.getElementById("partitaIva").value.trim();
    let email = document.getElementById("email").value.trim();
    let codCC = document.getElementById("codCC").value.trim();
	console.log("codCCGlobal!=codCC: "+codCCGlobal!=codCC);
	console.log("codCCGlobal!=codCC || codCC=='': "+codCCGlobal!=codCC || codCC=="");
    // Validation regex patterns
    let codFiscaleRegex = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
    let partitaIvaRegex = /^\d{11}$/;
    let telefonoRegex = /^\+\d{1,} \d{8,10}$/;
    let indirizzoRegex = /^(Corso|Via|Viale|Piazza|Largo|Strada)\s[\w\sÀ-ú]+\d*[A-z]?$/;
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
        alert("Indirizzo non valido. Utilizzare il formato: <Via|Viale|Corso...> nome numero(eventuale lettera)\nNO punti o caratteri speciali");
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
    if (codCCGlobal!=codCC || codCC=="") {
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
            document.getElementById("registrationForm").submit();
        }
    }else{
    	alert("riprova")
    }
}
function resetForm(){
	document.getElementById("cognome").value='';
    document.getElementById("nome").value='';
    document.getElementById("telefono1").value='';
    document.getElementById("telefono2").value='';
    document.getElementById("ragSoc").value='';
    document.getElementById("sessoM").checked = false;;
    document.getElementById("sessoF").checked = false;;
    document.getElementById("indirizzo").value='';
    document.getElementById("citta").value='';
    document.getElementById("luogoNascita").value='';
    document.getElementById("codFiscale").value='';
    document.getElementById("partitaIva").value='';
    document.getElementById("email").value='';
    document.getElementById("codCC").value=codCCGlobal;
}

function primaLetteraMaiuscola(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}
