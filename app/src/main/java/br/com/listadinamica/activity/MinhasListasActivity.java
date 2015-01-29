package br.com.listadinamica.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
    private EditText input;
    boolean [] checks = {false, false, false};
    String [] tiposItens = {"Texto", "Numero", "Data"};
    Lista composicaoLista = new Lista();
    ListaDAO listaDaoCriar;


    //variaveis para listagem
    private ListView lvLista;
    private List<Lista> listaList;
    private ListaAdapter listaAdapter;
    private ListaDAO listaDAOLista;



    TextView texto;
    TextView numero;

    // comit pelo logan. 22/01/2015 as 13;17 ... alalalal
    /**
     * abre um modal com opções de widgets para
     * composição dos itens da nova lista.
     * */


    public void adicionarLista(){

        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Nome da Lista");
        input = new EditText(this);
        builder.setView(input);


        builder.setPositiveButton(R.string.criarLista,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                adicionarComposicaoLista();
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        builder.show();
    }

     public void adicionarComposicaoLista(){

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
                checks, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            switch (which) {
                                case 0:
                                    composicaoLista.setIsTexto(1);
                                    checks[0] = true;
                                    break;
                                case 1:
                                    composicaoLista.setIsNumero(1);
                                    checks[1] = true;
                                    break;
                                case 2:
                                    composicaoLista.setIsData(1);
                                    checks[2] = true;
                                    break;
                            }
                        }
                    }
                });
         builder.show();
    }

    private void criaLista() {

        listaDaoCriar = new ListaDAO(this);
        composicaoLista.setNome(input.getText().toString());
        long resultado = listaDaoCriar.salvarLista(composicaoLista);
        listarListasListView();
    }

    public void listarListasListView(){
        listaDAOLista = new ListaDAO(this);
        listaList = listaDAOLista.listarListas();
        listaAdapter = new ListaAdapter(this, listaList);
        lvLista = (ListView) findViewById(R.id.lvListas);
        lvLista.setAdapter(listaAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);

        listarListasListView();
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

    @Override
    protected void onDestroy() {
        listaDAOLista.fechar();
        listaDaoCriar.fechar();
        super.onDestroy();
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

}
