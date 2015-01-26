package br.com.listadinamica.dao;

/**
 * Created by Ewertom on 21/01/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "listadinamica";
    private static final int VERSAO = 1;

    public DatabaseHelper(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de Lista
        db.execSQL("create table lista(_id integer primary key autoincrement, "
                +"nome text , isTexto integer, isNumero integer , isData integer)");

        //Tabela de tarefas
        db.execSQL("create table item(_id integer primary key autoincrement, " +
                "idLista integer not null,"
                +" texto text, numero real, data datetime, FOREIGN KEY(idLista) REFERENCES lista(_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Lista{
        public static final String TABELA = "lista";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String ISTEXTO = "isTexto";
        public static final String ISNUMERO = "isNumero";
        public static final String ISDATA = "isData";

        public static final String[] COLUNAS = new String[]{
                _ID, NOME, ISTEXTO, ISNUMERO, ISDATA
        };
    }

    public static class Item{
        public static final String TABELA = "item";
        public static final String _ID = "_id";
        public static final String IDLISTA = "idLista";
        public static final String TEXTO = "texto";
        public static final String NUMERO = "numero";
        public static final String DATA = "data";

        public static final String[] COLUNAS = new String[]{
                _ID, IDLISTA, TEXTO, NUMERO, DATA
        };
    }
}
