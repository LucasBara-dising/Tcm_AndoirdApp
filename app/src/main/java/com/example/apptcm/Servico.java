package com.example.apptcm;

public class Servico {
    int codeServ;
    String nomeEmpresa;
    String tituloServ;
    String Prazo;
    String descServ;
    String areaServ;
    String nivelConclusao;

    public Servico(){

    }

    //update
    public Servico(int VcodeServ, String VnomeEmpresa, String VtituloServ, String VdescServ, String VPrazo, String VareaServ,String VnivelConclusao){
        this.codeServ=VcodeServ;
        this.nomeEmpresa=VnomeEmpresa;
        this.tituloServ=VtituloServ;
        this.descServ=VdescServ;
        this.Prazo=VPrazo;
        this.areaServ=VareaServ;
        this.nivelConclusao=VnivelConclusao;
    }



    //insert
    public Servico(String VnomeEmpresa, String VtituloServ, String VPrazo, String VdescServ, String VareaServ,String VnivelConclusao){
        this.nomeEmpresa=VnomeEmpresa;
        this.tituloServ=VtituloServ;
        this.descServ=VdescServ;
        this.Prazo=VPrazo;
        this.areaServ=VareaServ;
        this.nivelConclusao=VnivelConclusao;
    }




    //--------------------------------------------------
    public int getCodeServ() {
        return codeServ;
    }
    public void setCodeServ(int codeServ) {
        this.codeServ = codeServ;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getTituloServ() {
        return tituloServ;
    }
    public void setTituloServ(String tituloServ) {
        this.tituloServ = tituloServ;
    }

    public String getDescServ() {
        return descServ;
    }
    public void setDescServ(String descServ) {
        this.descServ = descServ;
    }

    public String getPrazo() {
        return Prazo;
    }
    public void setPrazo(String prazo) {
        Prazo = prazo;
    }

    public String getAreaServ() {
        return areaServ;
    }
    public void setAreaServ(String areaServ) {
        this.areaServ = areaServ;
    }

    public String getNivelConclusao() {
        return nivelConclusao;
    }
    public void setNivelConclusao(String nivelConclusao) {
        this.nivelConclusao = nivelConclusao;
    }
}
