package br.com.listadinamica.model;

/**
 * Created by Ewertom on 22/01/2015.
 */
public class Lista {

    private Integer _id;
    private String nome;
    private Integer isTexto;
    private Integer isNumero;
    private Integer isData;

    public Lista(){}

    public Lista(Integer _id, String nome, Integer isTexto,
                 Integer isNumero, Integer isData){
        this.set_id(_id);
        this.setNome(nome);
        this.setIsTexto(isTexto);
        this.setIsNumero(isNumero);
        this.setIsData(isData);
    }


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIsTexto() {
        return isTexto;
    }

    public void setIsTexto(Integer isTexto) {
        this.isTexto = isTexto;
    }

    public Integer getIsNumero() {
        return isNumero;
    }

    public void setIsNumero(Integer isNumero) {
        this.isNumero = isNumero;
    }

    public Integer getIsData() {
        return isData;
    }

    public void setIsData(Integer isData) {
        this.isData = isData;
    }
}
