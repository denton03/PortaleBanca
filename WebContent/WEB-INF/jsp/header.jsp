<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/BancaIntesa/resources/css/styleHeader.css">

	<div class="barraMenu">
	  	<button class="home-button" onclick="window.location.href='index.do'" >Home</button>
	  	
      <div class="dropdown-button" >Anagrafica Correntisti</div>
        <div class="dropdown-options">
            <div class="dropdown-option" onclick="window.location.href='nuovoCorrentista.do'">Inserimento correntista</div>
            <div class="dropdown-option" onclick="window.location.href='modifica.do?method=view'">Modifica correntista</div>
            <div class="dropdown-option" onclick="window.location.href='correntisti.do'">Elenco correntisti</div>
        </div>
	  	<button class="logout" onclick="window.location.href='logout.do'">Logout</button>
	</div>

  <script>
    document.addEventListener("DOMContentLoaded", function() {
        var dropdownButton = document.querySelector('.dropdown-button');
        var dropdownOptions = document.querySelector('.dropdown-options');
  
        dropdownButton.addEventListener('click', function() {
            dropdownOptions.style.display = (dropdownOptions.style.display === 'none' || dropdownOptions.style.display === '') ? 'block' : 'none';
        });
  
        document.addEventListener('click', function(event) {
            if (!dropdownButton.contains(event.target) && !dropdownOptions.contains(event.target)) {
                dropdownOptions.style.display = 'none';
            }
        });
    });
  </script>