<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<style>
	
	#codCC{
	    padding-top:10%;
	    padding-bottom: 10%;
	     display:block;
	    box-sizing: border-box;
	}
	#indirizzo{
		width:100%;
	}
</style>
<%
   System.out.println("tabCorrentisti value: " + request.getAttribute("tabCorrentisti"));
%>

<tiles:insert attribute="tabCorrentisti"/>

<div id="content">
    <form id="viewCorrentistaForm" accept-charset="UTF-8">
        <table>
            <tr>
                <td><label for="cognome">Cognome*:</label></td>
                <td><input type="text" id="cognome" name="cognome" value="${beanCorrentista.cognome }" readonly></td>
                <td><label for="nome">Nome*:</label></td>
                <td><input type="text" id="nome" name="nome" value="${beanCorrentista.nome }" readonly></td>
                <td><label for="telefono1">Telefono 1*:</label></td>
                <td><input type="text" id="telefono1" name="telefono1" value="${beanCorrentista.telefono1 }" readonly></td>
            </tr>
            <tr>
                <td><label for="ragSoc">Ragione Sociale*:</label></td>
                <td><input type="text" id="ragSoc" name="ragSoc" readonly></td>
				<td colspan="2">
				    <label for="sesso">Sesso*:</label>
				    <input type="radio" id="sessoM" name="sesso" value="M" ${beanCorrentista.sesso == 'M' ? 'checked' : ''} disabled> Maschio
				    <input type="radio" id="sessoF" name="sesso" value="F" ${beanCorrentista.sesso == 'F' ? 'checked' : ''} disabled> Femmina
				</td>

                <td><label for="telefono2">Telefono 2:</label></td>
                <td><input type="text" id="telefono2" name="telefono2" value="${beanCorrentista.telefono2 }" readonly></td>
            </tr>
            <tr>
                <td><label for="indirizzo">Indirizzo*:</label></td>
                <td colspan="3"><input type="text" id="indirizzo" name="indirizzo"  value="${beanCorrentista.indirizzo }" readonly></td>
                <td><label for="citta">Città*:</label></td>
                <td><select id="citta" name="citta"  value="${beanCorrentista.citta }" disabled></select></td>
                
            </tr>
            <tr>
                <td><label for="dataNascita">Data di Nascita*:</label></td>
                <td><input type="date" id="dataNascita" name="dataNascita"  value="${beanCorrentista.dataNascita }" readonly></td>
                <td><label for="luogoNascita">Luogo di Nascita*:</label></td>
                <td><input type="text" id="luogoNascita" name="luogoNascita"  value="${beanCorrentista.luogoNascita }" readonly></td>
                <td ><label for="codCC">Num. Conto</label></td>
                <td rowspan="2"><input type="number" id="codCC" name="codCC"  value="${beanCorrentista.codCC }" readonly></td>
            </tr>
            <tr>
                <td><label for="codFiscale">Codice Fiscale:</label></td>
                <td><input type="text" id="codFiscale" name="codFiscale" value="${beanCorrentista.codFiscale }" readonly></td>
                <td><label for="partitaIva">Partita IVA:</label></td>
                <td><input type="text" id="partitaIva" name="partitaIva" value="${beanCorrentista.partitaIva}" readonly></td>
                <!-- Add your new fields here -->
            </tr>
            <tr>
            </tr>
			<tr>
			    <td><label for="email">Email:</label></td>
			    <td><input type="text" id="email" name="email" value="${beanCorrentista.email }" readonly></td>
			</tr>

        </table>

        <button type="button" onclick="window.location.href='/BancaIntesa/correntisti.do'">Cancel</button>
    </form>
</div>

<script type="text/javascript">
	console.log();
</script>