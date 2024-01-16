package service;

import java.util.ArrayList;

import repository.ComuneDAO;

public class ComuneService {
	private ComuneDAO dao;
	
	public ComuneService() {
		dao = new ComuneDAO();
	}
	
	public ArrayList<String> getDescrizioneComuni(){
		return dao.getDescrizioneComuni();
	}
}
