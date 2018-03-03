package cl.gerardomascayano.appfiestas.util;

import android.content.Context;

/**
 * Created by Gerardo on 02-03-2018.
 */

public class Funciones {


    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

}
