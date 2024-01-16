package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import model.Anagrafica;

public class CodiceFiscaleGenerator{
	private static Anagrafica anagrafica;
    private static String cognome; 
    private static String consonantiCognome="";
    private static String vocaliCognome="";
    private static String nome;
    private static String consonantiNome="";
    private static String vocaliNome="";
    private static String dataDiNascita;
    private static LocalDate dataDiNascitaParsed;
    private static String luogoDiNascita;
    private static String codiceCitta;
    private static String sesso;

    private static String codiceFiscale;

    
    public static String getCodiceFiscale(Anagrafica a) {
    	anagrafica = a;
    	ottenimentoCampi();
        consonantiVocali();
    	codiceFiscale=codiceCognome()+codiceNome()+codiceData()+codiceCitta;
        codiceFiscale+=codiceControllo();
        return codiceFiscale;
        
    }

    public static void ottenimentoCampi(){
        
    	switch (luogoDiNascita) {
                case "MILANO":
                    codiceCitta="F205";
                    break;
                case "BOLOGNA":
                    codiceCitta="A944";
                    
                break;
                case "TORINO":
                    codiceCitta="L219";
                
                break;
                case "ROMA":
                    codiceCitta="H501";
                
                break;
                case "FIRENZE":
                    codiceCitta="D612";
                
                break;
                case "RHO":
                    codiceCitta="H264";
                
                break;
                default:
                break;
        }
    }
    /*//Ottenimento vocali e consonanti dal cognome
SE lunghezza cognome maggiore uguale di 2
FOR carattere IN cognome
	SE carattere è una vocale
		aggiungere carattere in vocali-cognome
	SE carattere diverso da spazio e carattere diverso da lettere accentate
		aggiungere carattere in consonanti-cognome
*/
public static void consonantiVocali(){
    //cognome
    for(char c : cognome.toCharArray()){
        if(isVocal(c))
            vocaliCognome+=c;
        else if((c>='A' && c<='Z') && c!=' '){
           consonantiCognome+=c;
        }
    }
    //nome
    for(char c : nome.toCharArray()){
        if(isVocal(c))
            vocaliNome+=c;
            else if((c>='A' && c<='Z') && c!=' '){
            consonantiNome+=c;
        }
    }
}

public static boolean isVocal(char c){
    if(c=='A' || c=='E' || c=='I' || c=='O' || c=='U')
        return true;
    return false;
}
/* 
OTTENIMENTO CODICE Cognome
 */
public static String codiceCognome(){
    String codice ="";
    if (consonantiCognome.length()>= 3) {
        for (int i = 0; i < 3; i++) {
            codice+=consonantiCognome.charAt(i);
        }
    }
    else if(consonantiCognome.length()==2){
        if(vocaliCognome.length()>0){
            codice+=consonantiCognome.charAt(0)+""+consonantiCognome.charAt(1)+""+vocaliCognome.charAt(0);
            }else{
            codice+=consonantiCognome.charAt(0)+""+consonantiCognome.charAt(1)+"X";
        }
    }else if(consonantiCognome.length() == 1 && vocaliCognome.length()>1){
        codice+=consonantiCognome.charAt(0)+""+vocaliCognome.charAt(0)+""+vocaliCognome.charAt(1);
    }else if(consonantiCognome.length() == 1 && vocaliCognome.length()==1){
        codice+=consonantiCognome.charAt(0)+""+vocaliCognome.charAt(0)+"X";
    }else if(consonantiCognome.length() == 1 && vocaliCognome.length()==0){
        codice+=consonantiCognome.charAt(0)+"X"+"X";
    }
    else{
        switch (vocaliCognome.length()) {
            case 1:
                codice+=vocaliCognome.charAt(0)+"X"+"X";
            case 2:
                codice+=vocaliCognome.charAt(0)+""+vocaliCognome.charAt(1)+"X";
            break;
            case 3:
                codice+=vocaliCognome.substring(0, vocaliCognome.length());
            break;
        }
    }
    return codice;
}
 
public static String codiceNome(){
    String codice ="";
    if(consonantiNome.length()>3){
        codice+=consonantiNome.charAt(0)+""+consonantiNome.charAt(2)+""+consonantiNome.charAt(3);
    }
    else if (consonantiNome.length()== 3) {
        for (int i = 0; i < 3; i++) {
            codice+=consonantiNome.charAt(i);
        }
    }
    else if(consonantiNome.length()==2){
        if(vocaliNome.length()>0){
            codice+=consonantiNome.charAt(0)+""+consonantiNome.charAt(1)+""+vocaliNome.charAt(0);
        }else{
            codice+=consonantiNome.charAt(0)+""+consonantiNome.charAt(1)+"X";
        }
    }else if(consonantiNome.length() == 1 && vocaliCognome.length()>1){
        codice+=consonantiNome.charAt(0)+""+vocaliNome.charAt(0)+""+vocaliNome.charAt(1);
    }else if(consonantiNome.length() == 1 && vocaliNome.length()==1){
        codice+=consonantiNome.charAt(0)+""+vocaliNome.charAt(0)+"X";
    }else if(consonantiNome.length() == 1 && vocaliNome.length()==0){
        codice+=consonantiNome.charAt(0)+"X"+"X";
    }
    else{
        switch (vocaliNome.length()) {
            case 1:
                codice+=vocaliNome.charAt(0)+"X"+"X";
                break;
            case 2:
                codice+=vocaliNome.charAt(0)+""+vocaliNome.charAt(1)+"X";
            break;
            case 3:
                codice+=vocaliNome.substring(0, vocaliNome.length());
            break;
        }
    }
    return codice;
}
    

public static String codiceControllo(){
        /*//CARATTERE ALFABETICO DI CONTROLLO
String caratteri-dispari
String caratteri-pari
int somma
FOR(int i = 0; i<codice-fiscale.length; i++){
	SE i%2==0
		caratteri-dispari += codice-fiscale.charAt(i)
	ALTRIMENTI
		caratteri-pari = codice-fiscale.charAt(i)
}

FOREACH(carattere in caratteri-dispari)
	SWITCH(carattere)
		in base al carattere, aggiungere il valore indicato dalla tabella dei caratteri in posizione dispari alla variabile somma

FOREACH(carattere in caratteri-pari)
	SWITCH(carattere)
		in base al carattere, aggiungere il valore indicato dalla tabella dei caratteri in posizione pari alla variabile somma

SWITCH(somma%26)
	Dal resto ottenuto convertire il valore in carattere secondo la tabella del check digit
 */
        String codice="";
        String caratteriDispari="";
        String caratteriPari="";
        int somma=0;
        for(int i = 0; i<codiceFiscale.length();i++){
            if(i%2==0){
                caratteriDispari += codiceFiscale.charAt(i);
            }else{
                caratteriPari+=codiceFiscale.charAt(i);
            }
        }
        for(char c : caratteriDispari.toCharArray()){
            switch (c) {
                case '0': somma+=1;break;
                case '1': somma+=0;break;
                case '2': somma+=5;break;
                case '3': somma+=7;break;
                case '4': somma+=9;break;
                case '5': somma+=13;break;
                case '6': somma+=15;break;
                case '7': somma+=17;break;
                case '8': somma+=19;break;
                case '9': somma+=21;break;
                case 'A': somma+=1;break;
                case 'B': somma+=0;break;
                case 'C': somma+=5;break;
                case 'D': somma+=7;break;
                case 'E': somma+=9;break;
                case 'F': somma+=13;break;
                case 'G': somma+=15;break;
                case 'H': somma+=17;break;
                case 'I': somma+=19;break;
                case 'J': somma+=21;break;
                case 'K': somma+=2;break;
                case 'L': somma+=4;break;
                case 'M': somma+=18;break;
                case 'N': somma+=20;break;
                case 'O': somma+=11;break;
                case 'P': somma+=3;break;
                case 'Q': somma+=6;break;
                case 'R': somma+=8;break;
                case 'S': somma+=12;break;
                case 'T': somma+=14;break;
                case 'U': somma+=16;break;
                case 'V': somma+=10;break;
                case 'W': somma+=22;break;
                case 'X': somma+=25;break;
                case 'Y': somma+=24;break;
                case 'Z': somma+=23;break;
            }
        }
        for(char c : caratteriPari.toCharArray()){
            switch (c) {
                case '0': somma+=0;break;
                case '1': somma+=1;break;
                case '2': somma+=2;break;
                case '3': somma+=3;break;
                case '4': somma+=4;break;
                case '5': somma+=5;break;
                case '6': somma+=6;break;
                case '7': somma+=7;break;
                case '8': somma+=8;break;
                case '9': somma+=9;break;
                case 'A': somma+=0;break;
                case 'B': somma+=1;break;
                case 'C': somma+=2;break;
                case 'D': somma+=3;break;
                case 'E': somma+=4;break;
                case 'F': somma+=5;break;
                case 'G': somma+=6;break;
                case 'H': somma+=7;break;
                case 'I': somma+=8;break;
                case 'J': somma+=9;break;
                case 'K': somma+=10;break;
                case 'L': somma+=11;break;
                case 'M': somma+=12;break;
                case 'N': somma+=13;break;
                case 'O': somma+=14;break;
                case 'P': somma+=15;break;
                case 'Q': somma+=16;break;
                case 'R': somma+=17;break;
                case 'S': somma+=18;break;
                case 'T': somma+=19;break;
                case 'U': somma+=20;break;
                case 'V': somma+=21;break;
                case 'W': somma+=22;break;
                case 'X': somma+=23;break;
                case 'Y': somma+=24;break;
                case 'Z': somma+=25;break;
            }
        }
        switch (somma%26) {
            case 0: codice="A";break;
            case 1: codice="B";break;
            case 2: codice="C";break;
            case 3: codice="D";break;
            case 4: codice="E";break;
            case 5: codice="F";break;
            case 6: codice="G";break;
            case 7: codice="H";break;
            case 8: codice="I";break;
            case 9: codice="J";break;
            case 10: codice="K";break;
            case 11: codice="L";break;
            case 12: codice="M";break;
            case 13: codice="N";break;
            case 14: codice="O";break;
            case 15: codice="P";break;
            case 16: codice="Q";break;
            case 17: codice="R";break;
            case 18: codice="S";break;
            case 19: codice="T";break;
            case 20: codice="U";break;
            case 21: codice="V";break;
            case 22: codice="W";break;
            case 23: codice="X";break;
            case 24: codice="Y";break;
            case 25: codice="Z";break;
        }

        return codice;
    }

    public static String codiceData(){
        String codice = "";
        String anno = codice+dataDiNascita.charAt(8) + dataDiNascita.charAt(9);
        codice+=anno;
        int mese = dataDiNascitaParsed.getMonthValue();
        int giorni =dataDiNascitaParsed.getDayOfMonth();
        switch (mese) {
            case 1: codice+="A";break;
		    case 2: codice+="B";break;
		    case 3: codice+="C";break;
		    case 4: codice+="D";break;
		    case 5: codice+="E";break;
		    case 6: codice+="H";break;
		    case 7: codice+="L";break;
		    case 8: codice+="M";break;
		    case 9: codice+="P";break;
		    case 10: codice+="R";break;
		    case 11: codice+="S";break;
		    case 12: codice+="T";break;
        }
        if(sesso.equals("M")){
            if(giorni<10){
                codice+='0'+giorni;
            }else{
                codice+=giorni;
            }
        }else if(sesso.equals("F")){
            giorni+=40;
            codice+=giorni;
        }

        return codice;
    }


    public static boolean dateIsValid(String data){
        LocalDate oggi = LocalDate.now();
        int giorniOggi = oggi.getDayOfMonth();
        int meseOggi = oggi.getMonthValue();
        int anniOggi = oggi.getYear();
        boolean annoBisestile = false;
        int giorni, mesi, anni;
        if(data.length() == 10){
            //controllo data parsing 
            /*try{
                dataParsed = LocalDate.parse(data, formatter);
            }catch(DateTimeParseException e){
                System.out.println("Data invalida non parsabile");
                return false;
            } */
            if(Character.isDigit(data.charAt(0)) && Character.isDigit(data.charAt(1)) && Character.isDigit(data.charAt(3)) && Character.isDigit(data.charAt(4)) && Character.isDigit(data.charAt(6)) && Character.isDigit(data.charAt(7)) && Character.isDigit(data.charAt(8)) && Character.isDigit(data.charAt(9))  ){
                if(data.charAt(2)=='/' && data.charAt(5) == '/'){
                    giorni = Integer.parseInt(data.substring(0,2));
                    mesi = Integer.parseInt(data.substring(3,5));
                    anni = Integer.parseInt(data.substring(6, 10));
                    if(anni<anniOggi && anni!=0000){
                            if(anni % 4 == 0){
                                annoBisestile = true;
                            }
                            switch (mesi) {
                                case 1:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 2:
                                    if(annoBisestile && !(giorni>=1 && giorni<=29))
                                        return false;
                                    else if(!annoBisestile && !(giorni>=1 && giorni<=28))
                                        return false;
                                    
                                    break;
                                case 3:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 4:
                                    if(!(giorni>=1 && giorni<=30))
                                        return false;
                                    break;
                                case 5:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 6:
                                    if(!(giorni>=1 && giorni<=30))
                                        return false;
                                    break;
                                case 7:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 8: 
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 9:
                                    if(!(giorni>=1 && giorni<=30))
                                        return false;
                                break;
                                case 10:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 11:
                                    if(!(giorni>=1 && giorni<=30))
                                        return false;
                                    break;
                                case 12:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                default:
                                    return false;
                            }
                        
                    }
                    else if(anni == anniOggi){
                        if (mesi<meseOggi) {
                            if(anni % 4 == 0){
                                annoBisestile = true;
                            }
                            switch (mesi) {
                                case 1:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 2:
                                    if(annoBisestile && !(giorni>=1 && giorni<=29))
                                        return false;
                                    else if(!annoBisestile && !(giorni>=1 && giorni<=28))
                                        return false;
                                    
                                    break;
                                case 3:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 4:
                                    if(!(giorni>=1 && giorni<=30))
                                        return false;
                                    break;
                                case 5:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 6:
                                    if(!(giorni>=1 && giorni<=30))
                                        return false;
                                    break;
                                case 7:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 8: 
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 9:
                                    if(!(giorni>=1 && giorni<=30))
                                        return false;
                                break;
                                case 10:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                case 11:
                                    if(!(giorni>=1 && giorni<=30))
                                        return false;
                                    break;
                                case 12:
                                    if(!(giorni>=1 && giorni<=31))
                                        return false;
                                    break;
                                default:
                                    return false;
                            }
                        }
                        else if(mesi==meseOggi){
                            if(giorni<=giorniOggi){
                                if(giorni>=1){
                                    return true;
                                }
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
                else{
                    System.out.println("Formato non valido con caratteri : "+data.charAt(2));
                    return false;
                }
            }else{
                System.out.println("non valida");
                return false;
            }
            
        }
        else{
            return false;
        }
        return true;
    }
}