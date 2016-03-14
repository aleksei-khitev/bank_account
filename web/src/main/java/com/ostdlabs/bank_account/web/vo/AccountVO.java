package com.ostdlabs.bank_account.web.vo;

/**
 * Calue object for Account entity
 */
public class AccountVO {
    private Integer id;
    private String iban;
    private String bic;

    public AccountVO() {
    }

    public AccountVO(Integer id, String iban, String bic) {
        this.id = id;
        this.iban = iban;
        this.bic = bic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
