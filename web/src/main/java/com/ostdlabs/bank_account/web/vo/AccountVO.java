package com.ostdlabs.bank_account.web.vo;

public class AccountVO {
    private long id;
    private String iban;
    private String bic;

    public AccountVO() {
    }

    public AccountVO(long id, String iban, String bic) {
        this.id = id;
        this.iban = iban;
        this.bic = bic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    @Override
    public String toString() {
        return "AccountVO{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", bic='" + bic + '\'' +
                '}';
    }
}
