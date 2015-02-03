package br.com.listadinamica.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
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

    private Item criaItem(Cursor cursor){

        Item model = new Item(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Item._ID)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Item.IDLISTA)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.TEXTO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.NUMERO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.DATA))
        );

        return model;
    }

    public List<Item> listarItensLista(Integer idLista){

        //select itens  where idLista= idLista
        Cursor cursor = getDatabase().query(DatabaseHelper.Item.TABELA,
                DatabaseHelper.Item.COLUNAS, "idLista = ?", new String[]{idLista.toString()},
                null, null, null);

        List<Item> itens = new ArrayList<Item>();

        //trecho comentado pois nao tava mostrando
        // quando query retornava apenas um registro
        /*if(!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }*/
        while(cursor.moveToNext()){
            Item model = criaItem(cursor);
            itens.add(model);
        }
        cursor.close();
        return itens;
    }



    public boolean removerItem(Integer id){

        return getDatabase().delete(DatabaseHelper.Item.TABELA,
                "_id = ?", new String[]{ Integer.toString(id) }) > 0;

    }

    public boolean removerItensLista(Integer idLista){

        return getDatabase().delete(DatabaseHelper.Item.TABELA,
                "idLista = ?", new String[]{ Integer.toString(idLista) }) > 0;


    }


    public void fechar(){

    }


}
