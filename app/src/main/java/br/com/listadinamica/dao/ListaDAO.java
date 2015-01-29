package br.com.listadinamica.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.listadinamica.model.Lista;

/**
 * Created by Ewertom on 22/01/2015.
 */
public class ListaDAO {

    // inicializa classes essenciais
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ListaDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if(database==null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Lista criaLista(Cursor cursor){

        Lista model = new Lista(
            cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Lista._ID)),
            cursor.getString(cursor.getColumnIndex(DatabaseHelper.Lista.NOME)),
            cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Lista.ISTEXTO)),
            cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Lista.ISNUMERO)),
            cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Lista.ISDATA))

        );

        return model;
    }

    public long salvarLista(Lista lista){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Lista.NOME, lista.getNome());
        valores.put(DatabaseHelper.Lista.ISTEXTO, lista.getIsTexto());
        valores.put(DatabaseHelper.Lista.ISNUMERO, lista.getIsNumero());
        valores.put(DatabaseHelper.Lista.ISDATA, lista.getIsData());

        if(lista.get_id() != null){
            return getDatabase().update(DatabaseHelper.Lista.TABELA, valores,
                    "_id = ?", new String[]{ lista.get_id().toString() });
        }

        return getDatabase().insert(DatabaseHelper.Lista.TABELA, null, valores);
    }


    public List<Lista> listarListas(){
        Cursor cursor = getDatabase().query(DatabaseHelper.Lista.TABELA,
                DatabaseHelper.Lista.COLUNAS, null, null, null, null, null);

        List<Lista> listas = new ArrayList<Lista>();
        if(!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        while(cursor.moveToNext()){
            Lista model = criaLista(cursor);
            listas.add(model);
        }
        cursor.close();
        return listas;

    }

    public void fechar(){
        databaseHelper.close();
        database = null;
    }

}
