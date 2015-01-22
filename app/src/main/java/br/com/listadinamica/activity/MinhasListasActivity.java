package br.com.listadinamica.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class MinhasListasActivity extends ActionBarActivity {

    private AlertDialog alerta;
    boolean [] checks = {false, false};
    String [] tiposItens = {"Texto", "Monetário"};
    Item composicaoItens = new Item();
    ArrayList<Object> listaItensEscolhidos= new ArrayList<>();

    TextView texto;
    TextView monetario;


    /**
     * abre um modal com opções de widgets para
     * composição dos itens da nova lista.
     * */
    public void adicionarLista(){

        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Composicao dos Itens");
        builder.setPositiveButton(R.string.criarLista,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                montaLista();
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
                                    composicaoItens.setTexto(true);
                                    checks[0] = true;
                                    break;
                                case 1: // cria double para os itens
                                    composicaoItens.setMonetario(true);
                                    checks[1] = true;
                                    break;
                            }
                        }
                    }
                });
         builder.show();
    }

    private void montaLista() {
        //implementacao teste: criar os widgets nesta mesma actiity
        if(composicaoItens.isTexto()){
            texto = new TextView(this);
            texto.setText("texto");
            setContentView(texto);
        }
        if(composicaoItens.isMonetario()){
            monetario = new TextView(this);
            monetario.setText("monetario");
            setContentView(monetario);
        }
    }

    private void adicionarTipoLista(int i) {
        switch (i){
            case 0 :
                texto = new TextView(this);
                listaItensEscolhidos.add(texto);
                break;
            case 1 :
                monetario = new TextView(this);
                listaItensEscolhidos.add(monetario);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);

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
