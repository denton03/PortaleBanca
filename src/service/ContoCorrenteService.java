package service;

import model.ContoCorrente;
import repository.ContoCorrenteDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ContoCorrenteService {
    private ContoCorrenteDAO dao;

    public ContoCorrenteService() {
        dao = new ContoCorrenteDAO();
    }

    public long insertContoCorrente(String iso, int numControllo, String cin, int abi, int cab, long codCC,
            int codAnagCC, BigDecimal importoSaldo, BigDecimal importoFido) {
        ContoCorrente contoCorrente = new ContoCorrente(iso, numControllo, cin, abi, cab,codCC,codAnagCC, importoSaldo, importoFido);
        return dao.insertContoCorrente(contoCorrente);
    }
    public long getLastIdPlus1() {
    	return dao.getLastIdPlus1();
    }
    public ContoCorrente getContoCorrenteByCodCC(long codCC) {
        return dao.getContoCorrenteByCodCC(codCC);
    }
    public ArrayList<Long> getAllCodCC(){
    	return dao.getAllCodCC();
    }
    public ArrayList<ContoCorrente> getAllContiCorrenti() {
        return dao.getAllContiCorrenti();
    }

    public ArrayList<ContoCorrente> getContiCorrentiByCodAnagCC(int codAnagCC) {
        return dao.getContiCorrentiByCodAnagCC(codAnagCC);
    }
}
