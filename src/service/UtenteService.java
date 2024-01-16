package service;

import repository.UtenteDAO;

public class UtenteService {
	private UtenteDAO dao;
	
	public UtenteService() {
		this.dao=new UtenteDAO();
	}
	
	public int getUtenteByNomeUserPassword(String nomeUser,String password) {
		return dao.getUtenteByNomeUserPassword(nomeUser, password);
	}
}
