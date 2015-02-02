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
import android.widget.TextView;
import java.util.List;

import br.com.listadinamica.activity.R;
import br.com.listadinamica.model.Lista;

/**
 * Created by thiagoporto on 21/12/14.
 */
public class ListaAdapter extends BaseAdapter {

    private Context context;
    private List<Lista> lista;

    public ListaAdapter(Context ctx, List<Lista> listas){
        this.context = ctx;
        this.lista = listas;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Lista listas = lista.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_minhas_listas , null);
        }

        TextView txtNome = (TextView) view.findViewById(R.id.lista_lista_nome);
        txtNome.setText(listas.getNome());

        TextView parametros = (TextView) view.findViewById(R.id.lista_par);
        parametros.setText(String.valueOf(listas.getIsTexto()));


        return view;
    }
}
