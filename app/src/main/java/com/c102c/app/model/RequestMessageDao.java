package com.c102c.app.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.c102c.app.model.RequestMessage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "REQUEST_MESSAGE".
*/
public class RequestMessageDao extends AbstractDao<RequestMessage, String> {

    public static final String TABLENAME = "REQUEST_MESSAGE";

    /**
     * Properties of entity RequestMessage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Uuid = new Property(0, String.class, "uuid", true, "UUID");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Xml = new Property(2, String.class, "xml", false, "XML");
        public final static Property State = new Property(3, String.class, "state", false, "STATE");
    };


    public RequestMessageDao(DaoConfig config) {
        super(config);
    }
    
    public RequestMessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"REQUEST_MESSAGE\" (" + //
                "\"UUID\" TEXT PRIMARY KEY NOT NULL ," + // 0: uuid
                "\"NAME\" TEXT," + // 1: name
                "\"XML\" TEXT," + // 2: xml
                "\"STATE\" TEXT);"); // 3: state
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"REQUEST_MESSAGE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RequestMessage entity) {
        stmt.clearBindings();
 
        String uuid = entity.getUuid();
        if (uuid != null) {
            stmt.bindString(1, uuid);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String xml = entity.getXml();
        if (xml != null) {
            stmt.bindString(3, xml);
        }
 
        String state = entity.getState();
        if (state != null) {
            stmt.bindString(4, state);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RequestMessage readEntity(Cursor cursor, int offset) {
        RequestMessage entity = new RequestMessage( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // uuid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // xml
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // state
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RequestMessage entity, int offset) {
        entity.setUuid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setXml(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setState(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(RequestMessage entity, long rowId) {
        return entity.getUuid();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(RequestMessage entity) {
        if(entity != null) {
            return entity.getUuid();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
