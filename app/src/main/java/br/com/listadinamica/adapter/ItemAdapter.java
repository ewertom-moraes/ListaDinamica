/**
 * Created by Ewertom on 24/01/2015.
 */

package br.com.listadinamica.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import br.com.listadinamica.util.Util;

/**
 * Created by thiagoporto on 21/12/14.
 */
public class ItemAdapter extends BaseAdapter implements TextWatcher {

    private Context context;
    private List<Item> itens;
    private Lista lista;
    int listPosititon;


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
        listPosititon = position;
        Item item = itens.get(position);

        //item.setIdLista(lista.get_id());
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_itens , null);
            //INFLATE É O CONTEUDO DO LISTVIEW, OS COMPONENTES...
        }

        CheckBox ok = (CheckBox) view.findViewById(R.id.item_ok);

        if(this.lista.getIsTexto()==1) {
            EditText texto = (EditText) view.findViewById(R.id.item_texto);
            texto.setText(item.getTexto());
        }
        if(this.lista.getIsNumero()==1) {
            TextView numero = (TextView) view.findViewById(R.id.item_numero);
            numero.setText(item.getNumero());
        }


        return view;
    }


    @Override
    public void afterTextChanged(Editable s) {
        Log.i("AQUI O LOG DO AFTER CHANGED!!!!!!!!!!!!!!", s.toString());
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        Log.i("AQUI O LOG DO AFTER BEFORE!!!!!!!!!!!!!!", s.toString());

    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        Log.i("AQUI O LOG DO ON CHANGE CHANGED!!!!!!!!!!!!!!", s.toString());

    }


}
