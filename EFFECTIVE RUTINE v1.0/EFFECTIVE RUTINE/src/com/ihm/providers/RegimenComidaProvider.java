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
public class RegimenComidaProvider extends ContentProvider{
    public static final String PROVIDER_NAME = "effectiveRutine.proveedor.RegimenComida";
    public static final String ENTIDAD = "regimen_comida";
    private static final String uriString = "content://"+PROVIDER_NAME+"/"+ENTIDAD;
    public static final Uri CONTENT_URI = Uri.parse(uriString);
    
    public static final String _ID = "id_regimen";
    public static final String NOMBRE = "nombre";
    public static final String RECOMENDACION = "recomendacion";
    
    public static final int REGIMEN_COMIDA = 1;
    public static final int REGIMEN_COMIDA_ID = 2;
    
    public static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, ENTIDAD, REGIMEN_COMIDA);
        uriMatcher.addURI(PROVIDER_NAME, ENTIDAD+"/#", REGIMEN_COMIDA_ID);
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
        
        if(uriMatcher.match(uri) == REGIMEN_COMIDA_ID)
            sqlBuilder.appendWhere(
                    _ID + "=" + uri.getLastPathSegment());
        
        if(sortOrder == null || "".equals(sortOrder))
            sortOrder = NOMBRE;
        
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
            case REGIMEN_COMIDA:
                return "vnd.android.cursor.dir/vnd.effectiveRutine."+ENTIDAD;
            case REGIMEN_COMIDA_ID:
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
            case REGIMEN_COMIDA:
                count = this.db.delete(ENTIDAD, selection, selectionArgs);
            break;
            case REGIMEN_COMIDA_ID:
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
            case REGIMEN_COMIDA:
                count = this.db.update(
                        ENTIDAD,
                        values,
                        selection,
                        selectionArgs);
            break;
            case REGIMEN_COMIDA_ID:
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
