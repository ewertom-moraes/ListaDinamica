package br.com.listadinamica.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.listadinamica.adapter.ListaAdapter;
import br.com.listadinamica.dao.ListaDAO;
import br.com.listadinamica.model.Item;
import br.com.listadinamica.model.Lista;
import br.com.listadinamica.util.Util;


public class MinhasListasActivity extends ActionBarActivity {




    // variaveis para criacao
    private AlertDialog alerta;
    private EditText input;
    boolean [] checks = {false, false, false};
    String [] tiposItens = {"Texto", "Numero", "Data"};
    Lista composicaoLista = new Lista();
    private ListaDAO listaDAO;


    //variaveis para listagem
    private ListView lvLista;
    private List<Lista> listaList;
    private ListaAdapter listaAdapter;


    TextView texto;
    TextView numero;

    // comit pelo logan. 22/01/2015 as 13;17 ... alalalal
    /**
     * abre um modal com opções de widgets para
     * composição dos itens da nova lista.
     * */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Opcoes da Lista");
        menu.add(0, v.getId(), 0, "Ver Lista");
        menu.add(0, v.getId(), 1, "Renomear");//groupId, itemId, order, title
        menu.add(0, v.getId(), 2, "Excluir");



    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();

        listaDAO = new ListaDAO(this);
        listaList = listaDAO.listarListas();
        Lista listaEditar = listaDAO.buscarUsuarioPorId(listaList.get(info.position).get_id());
       // Util.alerta(this, listaEditar.get_id() +", " + listaEditar.getNome()
       //   + ", "+listaEditar.getIsTexto() + ", "+listaEditar.getIsNumero() + ", "+listaEditar.getIsData());
        switch (item.getOrder()){
            case 0 :
                Intent intent = getIntent().setClass(MinhasListasActivity.this, ItemActivity.class);
                intent.putExtra("idLista", listaEditar.get_id());
                intent.putExtra("nome", listaEditar.getNome());
                intent.putExtra("isTexto", listaEditar.getIsTexto());
                intent.putExtra("isNumero", listaEditar.getIsNumero());
                intent.putExtra("isData", listaEditar.getIsData());
                startActivity(intent);
                finish();
                break;
            case 1 :
                renomearLista(listaEditar);
                break;
            case 2 :
                listaDAO.removerLista(listaEditar.get_id());
                listarListasListView();
                break;
            default:
                return false;
        }

        return true;
    }

    private void renomearLista(final Lista lista) {
        AlertDialog.Builder builderRenomear  = new AlertDialog.Builder(this);
        builderRenomear.setTitle("Editar Nome da Lista");
        input = new EditText(this);
        input.setText(lista.getNome());
        builderRenomear.setView(input);

        builderRenomear.setPositiveButton(R.string.renomearLista,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                lista.setNome(input.getText().toString());
                listaDAO.salvarLista(lista);
                listarListasListView();
            }
        });
        builderRenomear.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        builderRenomear.show();

    }


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
                                    break;
                                case 1:
                                    composicaoLista.setIsNumero(1);
                                    break;
                                case 2:
                                    composicaoLista.setIsData(1);
                                    break;
                            }
                        }
                    }
                });
        builder.show();
    }

    private void criaLista() {

        listaDAO = new ListaDAO(this);
        composicaoLista.setNome(input.getText().toString());
        long resultado = listaDAO.salvarLista(composicaoLista);
        listarListasListView();
    }

    public void listarListasListView(){
        listaDAO = new ListaDAO(this);
        listaList = listaDAO.listarListas();
        listaAdapter = new ListaAdapter(this, listaList);
        lvLista = (ListView) findViewById(R.id.lvListas);
        lvLista.setAdapter(listaAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);

        lvLista=(ListView)findViewById(R.id.lvListas);
        // Register the ListView  for Context menu
        registerForContextMenu(lvLista);
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
        listaDAO.fechar();
        super.onDestroy();
    }
}
