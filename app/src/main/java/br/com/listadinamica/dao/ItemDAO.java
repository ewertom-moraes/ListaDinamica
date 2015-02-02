package br.com.listadinamica.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.listadinamica.model.Item;
import br.com.listadinamica.model.Lista;

/**
 * Created by Ewertom on 01/02/2015.
 */
public class ItemDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ItemDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if(database==null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public long salvarItem(Item item) {
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Item.IDLISTA, item.getIdLista());
        valores.put(DatabaseHelper.Item.TEXTO, item.getTexto());
        valores.put(DatabaseHelper.Item.NUMERO, item.getNumero());
        valores.put(DatabaseHelper.Item.DATA, item.getData());

        if(item.get_id() != null){
            return getDatabase().update(DatabaseHelper.Item.TABELA, valores,
                    "_id = ?", new String[]{ item.get_id().toString() });
        }

        return getDatabase().insert(DatabaseHelper.Item.TABELA, null, valores);
    }


    public List<Item> listarItensLista(Integer idLista){

        //select itens  where idLista= idLista
        return null;
    }



    public boolean removerItem(Integer id){

        //delete itens where _id= id

        return true;
    }

    public void fechar(){

    }


}
