package com.example.apptcm;

public class Funcionario {
    int idFunc;
    String EmailFunc;
    String SenhaFunc;
    String NomeFunc;
    String CargoFunc;

    public Funcionario(){

    }

    //update
    public Funcionario(int VidFunc, String VEmailFunc, String VSenhaFunc, String VNomeFunc, String VCargoFunc){
        this.idFunc=VidFunc;
        this.EmailFunc=VEmailFunc;
        this.SenhaFunc=VSenhaFunc;
        this.NomeFunc=VNomeFunc;
        this.CargoFunc=VCargoFunc;
    }

    //insert
    public Funcionario(String VEmailFunc, String VSenhaFunc, String VNomeFunc, String VCargoFunc){
        this.EmailFunc=VEmailFunc;
        this.SenhaFunc=VSenhaFunc;
        this.NomeFunc=VNomeFunc;
        this.CargoFunc=VCargoFunc;
    }


    //============================================

    public int getIdFunc() {
        return idFunc;
    }
    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public String getEmailFunc() {
        return EmailFunc;
    }
    public void setEmailFunc(String emailFunc) {
        EmailFunc = emailFunc;
    }

    public String getSenhaFunc() {
        return SenhaFunc;
    }
    public void setSenhaFunc(String senhaFunc) {
        SenhaFunc = senhaFunc;
    }

    public String getNomeFunc() {
        return NomeFunc;
    }
    public void setNomeFunc(String nomeFunc) {
        NomeFunc = nomeFunc;
    }

    public String getCargoFunc() {
        return CargoFunc;
    }
    public void setCargoFunc(String cargoFunc) {
        CargoFunc = cargoFunc;
    }

}
