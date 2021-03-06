package com.c102c.app.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.c102c.app.model.LocalMeasurementEWen;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOCAL_MEASUREMENT_EWEN".
*/
public class LocalMeasurementEWenDao extends AbstractDao<LocalMeasurementEWen, Long> {

    public static final String TABLENAME = "LOCAL_MEASUREMENT_EWEN";

    /**
     * Properties of entity LocalMeasurementEWen.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PersonId = new Property(1, String.class, "personId", false, "PERSON_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property IdCard = new Property(3, String.class, "idCard", false, "ID_CARD");
        public final static Property Ew = new Property(4, String.class, "ew", false, "EW");
        public final static Property Time = new Property(5, String.class, "time", false, "TIME");
    };


    public LocalMeasurementEWenDao(DaoConfig config) {
        super(config);
    }
    
    public LocalMeasurementEWenDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOCAL_MEASUREMENT_EWEN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PERSON_ID\" TEXT," + // 1: personId
                "\"NAME\" TEXT," + // 2: name
                "\"ID_CARD\" TEXT," + // 3: idCard
                "\"EW\" TEXT," + // 4: ew
                "\"TIME\" TEXT);"); // 5: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOCAL_MEASUREMENT_EWEN\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, LocalMeasurementEWen entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindString(2, personId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String idCard = entity.getIdCard();
        if (idCard != null) {
            stmt.bindString(4, idCard);
        }
 
        String ew = entity.getEw();
        if (ew != null) {
            stmt.bindString(5, ew);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public LocalMeasurementEWen readEntity(Cursor cursor, int offset) {
        LocalMeasurementEWen entity = new LocalMeasurementEWen( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // personId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // idCard
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // ew
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, LocalMeasurementEWen entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPersonId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIdCard(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEw(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(LocalMeasurementEWen entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(LocalMeasurementEWen entity) {
        if(entity != null) {
            return entity.getId();
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
