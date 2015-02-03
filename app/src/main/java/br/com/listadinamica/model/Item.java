package br.com.listadinamica.model;

/**
 * Created by Ewertom on 22/01/2015.
 */
public class Item {

    private Integer _id;
    private Integer idLista;
    private String texto;
    private String numero;
    private String data;

    public Item(){}

    public Item(Integer _id, Integer idLista, String texto
                , String numero, String data){
        this.set_id(_id);
        this.setIdLista(idLista);
        this.setTexto(texto);
        this.setNumero(numero);
        this.setData(data);
    }


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
