package br.com.listadinamica.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.listadinamica.adapter.ItemAdapter;
import br.com.listadinamica.adapter.ListaAdapter;
import br.com.listadinamica.dao.ItemDAO;
import br.com.listadinamica.model.Item;
import br.com.listadinamica.model.Lista;
import br.com.listadinamica.util.Util;
import br.com.listadinamica.activity.R;

public class ItemActivity extends ActionBarActivity {

    private Lista lista;
    private Item item;
    private ItemDAO itemDAO;
    private ItemAdapter itemAdapter;
    private ListView lvItens;
    private List<Item> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        listarItensNaActivity();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.adicionar_item){
            adicionarItemNaListView();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.salvar_itens) {
            salvarItensDaListView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void listarItensNaActivity(){

        Bundle bundle = getIntent().getExtras();
        lista = new Lista(bundle.getInt("idLista"),bundle.getString("nome"),
                bundle.getInt("isTexto"), bundle.getInt("isNumero"), bundle.getInt("isData"));

        itemDAO = new ItemDAO(this);
        itens = itemDAO.listarItensLista(lista.get_id());
        if(itens==null){
            return;
        }
        itemAdapter = new ItemAdapter(this, itens ,lista);
        lvItens = (ListView) findViewById(R.id.lvItens);
        lvItens.setAdapter(itemAdapter);
    }

    private void adicionarItemNaListView() {
        insereCoponenteListView(lista);
    }

    private void insereCoponenteListView(Lista lista){
        Item itemNovo = new Item();
        itens.add(itemNovo);
        itemAdapter.notifyDataSetChanged();

    }


    public void salvarItensDaListView(){


        ListAdapter listAdapter = lvItens.getAdapter();
        //nao ta pegando os valores digitados naquele instante na lv. só pega quando ja ta gravado e vem do bd
        int contagem = listAdapter.getCount();
        int i = 0;
        long resultado = 0;
        while(i<contagem){
            Item itemASalvar = (Item) listAdapter.getItem(i);
            itemASalvar.setIdLista(lista.get_id());
            resultado = itemDAO.salvarItem(itemASalvar);
            i++;
        }
        //Util.alerta(this, "contagem=" + contagem+" ultimo id salvo="+ resultado);


        Item itemRecuperado  = (Item) listAdapter.getItem(1);
        Util.alerta(this, "contagem=" + contagem+" itemtoString="+itemRecuperado.getTexto());
        listarItensNaActivity();
    }

    public void selecionarData(View view){
        showDialog(view.getId());
    }

    @Override
        protected void onPause() {
        Intent intent = new Intent(ItemActivity.this, MinhasListasActivity.class);
        startActivity(intent);
        super.onPause();
        finish();
    }
}
