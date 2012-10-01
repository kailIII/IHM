package com.ihm.providers;

import com.ihm.bd.DatabaseHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 *
 * @author Edward J. Holguín Holguín
 */
public class DetalleRutinaAlimentoProvider extends ContentProvider{
    public static final String PROVIDER_NAME = "effectiveRutine.proveedor.DetalleRutinaAlimento";
    public static final String ENTIDAD = "detalle_rutina_alimento";
    private static final String uriString = "content://"+PROVIDER_NAME+"/"+ENTIDAD;
    public static final Uri CONTENT_URI = Uri.parse(uriString);
    
    public static final String _ID = "id_detalle";
    public static final String ID_RUTINA_ALIMENTO = "id_rutina_alimento";
    public static final String ID_ALIMENTO = "id_alimento";
    public static final String CANTIDAD_INGERIDA_G = "cantidad_ingerida_g";
    public static final String CALORIAS_KCAL = "calorias_kcal";
    public static final String GRASAS_G = "grasas_g";
    public static final String PROTEINAS_G = "proteinas_g";
    public static final String CARBOHIDRATOS_G = "carbohidratos_g";
    
    public static final int DETALLES_RUTINA_ALIMENTO = 1;
    public static final int DETALLES_RUTINA_ALIMENTO_ID = 2;
    
    public static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, ENTIDAD, DETALLES_RUTINA_ALIMENTO);
        uriMatcher.addURI(PROVIDER_NAME, ENTIDAD+"/#", DETALLES_RUTINA_ALIMENTO_ID);
    }
    
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        this.db = new DatabaseHelper(getContext()).getWritableDatabase();
        
        return (this.db == null) ? false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(ENTIDAD);
        
        if(uriMatcher.match(uri) == DETALLES_RUTINA_ALIMENTO_ID)
            sqlBuilder.appendWhere(
                    _ID + "=" + uri.getLastPathSegment());
        
        //if(sortOrder == null || "".equals(sortOrder))
            //sortOrder = NOMBRE;
        
        Cursor c = sqlBuilder.query(
                this.db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        
        c.setNotificationUri(getContext().getContentResolver(), uri);
        
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case DETALLES_RUTINA_ALIMENTO:
                return "vnd.android.cursor.dir/vnd.effectiveRutine."+ENTIDAD;
            case DETALLES_RUTINA_ALIMENTO_ID:
                return "vnd.android.cursor.item/vnd.effectiveRutine."+ENTIDAD;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = this.db.insert(ENTIDAD, null, values);
        
        if(id > 0){//Registro insertado exitosamente
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        
        throw new SQLException("Error al insertar el registro en " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        
        switch (uriMatcher.match(uri)){
            case DETALLES_RUTINA_ALIMENTO:
                count = this.db.delete(ENTIDAD, selection, selectionArgs);
            break;
            case DETALLES_RUTINA_ALIMENTO_ID:
                count = this.db.delete(
                        ENTIDAD,
                        _ID + " = " + uri.getLastPathSegment() +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
            break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        
        getContext().getContentResolver().notifyChange(uri, null);
        
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        
        switch (uriMatcher.match(uri)) {
            case DETALLES_RUTINA_ALIMENTO:
                count = this.db.update(
                        ENTIDAD,
                        values,
                        selection,
                        selectionArgs);
            break;
            case DETALLES_RUTINA_ALIMENTO_ID:
                count = this.db.update(
                        ENTIDAD,
                        values,
                        _ID + " = " + uri.getLastPathSegment() +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
            break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        
        getContext().getContentResolver().notifyChange(uri, null);
        
        return count;
    }
    
}
