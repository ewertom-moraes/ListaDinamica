package br.com.listadinamica.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            salvarItensDaListView();
        }

        return super.onOptionsItemSelected(item);
    }

    private void listarItensNaActivity(){

        Bundle bundle = getIntent().getExtras();
        lista = new Lista(bundle.getInt("idLista"),bundle.getString("nome"),
                bundle.getInt("isTexto"), bundle.getInt("isNumero"), bundle.getInt("isData"));

        itemDAO = new ItemDAO(this);
        item = new Item();
        item.setIdLista(lista.get_id());
        itens = itemDAO.listarItensLista(lista.get_id());
        if(itens==null){
            return;
        }
        itemAdapter = new ItemAdapter(this, itens ,lista);
        lvItens = (ListView) findViewById(R.id.lvItens);
        lvItens.setAdapter(itemAdapter);
    }

    private void adicionarItemNaListView() {
        /*/long insert = itemDAO.salvarItem(item);
        if(insert!=-1){
            Util.alerta(this, "adicionado registro com sucesso. id="+insert);
        }*/
        //listarItensNaActivity();
        insereCoponenteListView(lista);
    }

    private void insereCoponenteListView(Lista lista){
        Item itemNovo = new Item();
        item.setIdLista(lista.get_id());
        itens.add(itemNovo);
        itemAdapter.notifyDataSetChanged();

    }

    public void salvarItensDaListView(){

        Item itemPego = (Item) itemAdapter.getItem(0);
        Util.alerta(this, "Pegou item da listview texto="+itemPego.getTexto());
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
