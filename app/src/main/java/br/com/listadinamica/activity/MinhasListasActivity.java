package br.com.listadinamica.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.listadinamica.adapter.ListaAdapter;
import br.com.listadinamica.dao.ListaDAO;
import br.com.listadinamica.model.Item;
import br.com.listadinamica.model.Lista;


public class MinhasListasActivity extends ActionBarActivity {

    // variaveis para criacao
    private AlertDialog alerta;
    boolean [] checks = {false, false};
    String [] tiposItens = {"Texto", "Numero"};
    Lista composicaoLista = new Lista();
    ListaDAO listaDao;

    //variaveis para listagem
    private ListView lista;
    private List<Lista> listaList;
    private ListaAdapter listaAdapter;
    private ListaDAO listaDAO;



    TextView texto;
    TextView numero;

    // comit pelo logan. 22/01/2015 as 13;17 ... alalalal
    /**
     * abre um modal com opções de widgets para
     * composição dos itens da nova lista.
     * */
    public void adicionarLista(){

        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Composicao dos Itens");
        builder.setPositiveButton(R.string.criarLista,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                criaLista();
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });

        builder.setMultiChoiceItems(tiposItens,
                checks, new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked) {
                            switch (which) {
                                case 0: //cria EditText para os iten
                                    composicaoLista.setIsTexto(1);
                                    checks[0] = true;
                                    break;
                                case 1: // cria double para os itens
                                    composicaoLista.setIsNumero(1);
                                    checks[1] = true;
                                    break;
                            }
                        }
                    }
                });
         builder.show();
    }

    private void criaLista() {

        composicaoLista.setNome("nova lista");

        long resultado = listaDao.salvarLista(composicaoLista);

        if(resultado != -1){
            System.out.print("salvo lista com sucesso");
        }
    }

    private void montaListaTeste() {
        //implementacao teste: criar os widgets nesta mesma actiity
        if(composicaoLista.getIsNumero()==1){
            texto = new TextView(this);
            texto.setText("texto");
            setContentView(texto);
        }
        if(composicaoLista.getIsNumero()==1){
            numero = new TextView(this);
            numero.setText("numero");
            setContentView(numero);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);

        listaDao = new ListaDAO(this);
        if(listaDAO.listarListas()==null){
            return;
        }
        listaList = listaDAO.listarListas();

        listaAdapter = new ListaAdapter(this, listaList);

        lista = (ListView) findViewById(R.id.lvListas);
        lista.setAdapter(listaAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_minhas_listas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.adicionar_lista)
            adicionarLista();

        return super.onOptionsItemSelected(item);
    }
}
