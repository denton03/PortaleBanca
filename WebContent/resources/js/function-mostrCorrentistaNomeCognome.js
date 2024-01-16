async function mostraCorrentistaNomeCognome(nome, cognome) {
    try {
        const url = `/BancaIntesa/viewCorrentista.do?nome=${encodeURIComponent(nome)}&cognome=${encodeURIComponent(cognome)}`;
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        
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

            const formCorrentista = document.getElementById('formCorrentista');
            formCorrentista.style.display = 'block';
        } else {
            console.error('Invalid date format:', data.dataNascita);
        }
    } catch (error) {
        console.error('Error fetching correntista data:', error);
    }
}