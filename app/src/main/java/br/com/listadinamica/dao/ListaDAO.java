package br.com.listadinamica.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public long salvarUsuario(Lista lista){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Lista.NOME, lista.getNome());
        valores.put(DatabaseHelper.Lista.ISTEXTO, lista.getIsTexto());
        valores.put(DatabaseHelper.Lista.ISNUMERO, lista.getIsNumero());
        //valores.put(DatabaseHelper.Lista.ISDATA, lista.getIsData());

        if(lista.get_id() != null){
            return getDatabase().update(DatabaseHelper.Lista.TABELA, valores,
                    "_id = ?", new String[]{ lista.get_id().toString() });
        }

        return getDatabase().insert(DatabaseHelper.Lista.TABELA, null, valores);
    }

}
