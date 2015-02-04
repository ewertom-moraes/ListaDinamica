/**
 * Created by Ewertom on 24/01/2015.
 */

package br.com.listadinamica.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.listadinamica.activity.ItemActivity;
import br.com.listadinamica.activity.R;
import br.com.listadinamica.model.Item;
import br.com.listadinamica.model.Lista;

/**
 * Created by thiagoporto on 21/12/14.
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> itens;
    private Lista lista;

    public ItemAdapter(Context ctx, List<Item> item){
        this.context = ctx;
        this.itens = item;
    }


    public ItemAdapter(Context ctx,  List<Item> itens, Lista lista){
        this.context = ctx;
        this.lista = lista;
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Item item = itens.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_itens , null);
            //INFLATE É O CONTEUDO DO LISTVIEW, OS COMPONENTES...
        }

        CheckBox ok = (CheckBox) view.findViewById(R.id.item_ok);
        ok.setChecked(false);

        if(this.lista.getIsTexto()==1) {
            EditText texto = (EditText) view.findViewById(R.id.item_texto);
            texto.setText("Texto do item");
        }
        if(this.lista.getIsNumero()==1) {
            TextView numero = (TextView) view.findViewById(R.id.item_numero);
            numero.setText("256,00");
        }
        if(this.lista.getIsTexto()==1) {
            Button data = (Button) view.findViewById(R.id.dataItem);
        }

        return view;
    }
}
