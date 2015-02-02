package br.com.listadinamica.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //Util.alerta(this, String.valueOf(bundle.getInt("idLista")));

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void adicionarItemNaListView() {

        Bundle bundle = getIntent().getExtras();
        lista = new Lista(bundle.getInt("idLista"),bundle.getString("nome"),
                bundle.getInt("isTexto"), bundle.getInt("isNumero"), bundle.getInt("isData"));

        itemDAO = new ItemDAO(this);
        item.setIdLista(lista.get_id());
        itemDAO.salvarItem(item);


        //itemAdapter = new ItemAdapter(this, lista);
        //lvItens = (ListView) findViewById(R.id.lvItens);
        //lvItens.setAdapter(itemAdapter);
    }

    @Override
        protected void onPause() {
        Intent intent = new Intent(ItemActivity.this, MinhasListasActivity.class);
        startActivity(intent);
        super.onPause();
        finish();
    }
}
