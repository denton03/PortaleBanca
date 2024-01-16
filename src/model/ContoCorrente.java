package model;

import java.math.BigDecimal;

public class ContoCorrente {
    private String iso;
    private int numControllo;
    private String cin;
    private int abi;
    private int cab;
    private long codCC;
    private int codAnagCC;
    private BigDecimal importoSaldo;
    private BigDecimal importoFido;

    // Constructors
    public ContoCorrente() {
    }
    public ContoCorrente(String iso, int numControllo, String cin, int abi, int cab,
            int codAnagCC, BigDecimal importoSaldo, BigDecimal importoFido) {
		this.iso = iso;
		this.numControllo = numControllo;
		this.cin = cin;
		this.abi = abi;
		this.cab = cab;
		this.codAnagCC = codAnagCC;
		this.importoSaldo = importoSaldo;
		this.importoFido = importoFido;
		}
    public ContoCorrente(String iso, int numControllo, String cin, int abi, int cab, long codCC,
                         int codAnagCC, BigDecimal importoSaldo, BigDecimal importoFido) {
        this.iso = iso;
        this.numControllo = numControllo;
        this.cin = cin;
        this.abi = abi;
        this.cab = cab;
        this.codCC = codCC;
        this.codAnagCC = codAnagCC;
        this.importoSaldo = importoSaldo;
        this.importoFido = importoFido;
    }

    // Getters and Setters
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public int getNumControllo() {
        return numControllo;
    }

    public void setNumControllo(int numControllo) {
        this.numControllo = numControllo;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getAbi() {
        return abi;
    }

    public void setAbi(int abi) {
        this.abi = abi;
    }

    public int getCab() {
        return cab;
    }

    public void setCab(int cab) {
        this.cab = cab;
    }

    public long getCodCC() {
        return codCC;
    }

    public void setCodCC(long codCC) {
        this.codCC = codCC;
    }

    public int getCodAnagCC() {
        return codAnagCC;
    }

    public void setCodAnagCC(int codAnagCC) {
        this.codAnagCC = codAnagCC;
    }

    public BigDecimal getImportoSaldo() {
        return importoSaldo;
    }

    public void setImportoSaldo(BigDecimal importoSaldo) {
        this.importoSaldo = importoSaldo;
    }

    public BigDecimal getImportoFido() {
        return importoFido;
    }

    public void setImportoFido(BigDecimal importoFido) {
        this.importoFido = importoFido;
    }
}
