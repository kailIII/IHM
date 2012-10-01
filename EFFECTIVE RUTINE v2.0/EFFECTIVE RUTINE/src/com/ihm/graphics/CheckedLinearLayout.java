package com.ihm.graphics;

/**
*
* @author Ricardo X. Campozano 
*/

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * LinearLayout que implementa la interfaz Checkable para
 * gestionar estados de selecci�n/deselecci�n
 */
public class CheckedLinearLayout extends LinearLayout implements Checkable{
 
    /**
     * Esta variable es la que nos sirve para almacenar el estado de este widget
     */
    private boolean mChecked=false;
 
    /**
     * Este array se usa para que los drawables que se usen
     * reaccionen al cambio de estado especificado
     * En nuestro caso al "state_checked"
     * que es el que utilizamos en nuestro selector
     */
    private final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };
 
    public CheckedLinearLayout(Context context) {
        super(context);
    }
 
    public CheckedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    /**
     * Este m�todo es el que cambia el estado de nuestro widget
     * @param checked true para activarlo y false para desactivarlo
     */
    public void setChecked(boolean checked) {
        mChecked = checked;
        //Cuando cambiamos el estado, debemos informar a los drawables
        //que este widget tenga vinculados
        refreshDrawableState();
        invalidate();
    }
 
    /**
     * Este m�todo devuelve el estado de nuestro widget <img src="http://androcode.es/wp-includes/images/smilies/icon_smile.gif" alt=":)" class="wp-smiley"> 
     * @return true o false, no?
     */
    public boolean isChecked() {
        return mChecked;
    }
 
    /**
     * Este m�todo cambia el estado de nuestro widget
     * Si estaba activo se desactiva y viceversa
     */
    public void toggle() {
        setChecked(!mChecked);
    }
 
    /**
     * Este m�todo es un poco m�s complejo
     * Se encarga de combinar los diferentes "estados" de un widget
     * para informar a los drawables.
     *
     * Si nuestro widget est� "checked" le a�adimos el estado CHECKED_STATE_SET
     * que definimos al principio
     *
     * @return el array de estados de nuestro widget
     */
    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}