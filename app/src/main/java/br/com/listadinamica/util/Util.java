package br.com.listadinamica.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Ewertom on 31/01/2015.
 */
public class Util {

    public static void alerta(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }


//    Util.alerta(this, listaEditar.get_id() +", " + listaEditar.getNome()
//            + ", "+listaEditar.getIsTexto() + ", "+listaEditar.getIsNumero() + ", "+listaEditar.getIsData());
//    Intent intent = getIntent().setClass(MinhasListasActivity.this, ItemActivity.class);

}
