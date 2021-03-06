package com.c102c.app.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.c102c.app.model.LocalMeasurementNiaoYe;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOCAL_MEASUREMENT_NIAO_YE".
*/
public class LocalMeasurementNiaoYeDao extends AbstractDao<LocalMeasurementNiaoYe, Long> {

    public static final String TABLENAME = "LOCAL_MEASUREMENT_NIAO_YE";

    /**
     * Properties of entity LocalMeasurementNiaoYe.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PersonId = new Property(1, String.class, "personId", false, "PERSON_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property IdCard = new Property(3, String.class, "idCard", false, "ID_CARD");
        public final static Property Pro = new Property(4, String.class, "pro", false, "PRO");
        public final static Property Bld = new Property(5, String.class, "bld", false, "BLD");
        public final static Property Uglu = new Property(6, String.class, "uglu", false, "UGLU");
        public final static Property Ket = new Property(7, String.class, "ket", false, "KET");
        public final static Property Nqt = new Property(8, String.class, "nqt", false, "NQT");
        public final static Property Time = new Property(9, String.class, "time", false, "TIME");
    };


    public LocalMeasurementNiaoYeDao(DaoConfig config) {
        super(config);
    }
    
    public LocalMeasurementNiaoYeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOCAL_MEASUREMENT_NIAO_YE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PERSON_ID\" TEXT," + // 1: personId
                "\"NAME\" TEXT," + // 2: name
                "\"ID_CARD\" TEXT," + // 3: idCard
                "\"PRO\" TEXT," + // 4: pro
                "\"BLD\" TEXT," + // 5: bld
                "\"UGLU\" TEXT," + // 6: uglu
                "\"KET\" TEXT," + // 7: ket
                "\"NQT\" TEXT," + // 8: nqt
                "\"TIME\" TEXT);"); // 9: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOCAL_MEASUREMENT_NIAO_YE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, LocalMeasurementNiaoYe entity) {
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
 
        String pro = entity.getPro();
        if (pro != null) {
            stmt.bindString(5, pro);
        }
 
        String bld = entity.getBld();
        if (bld != null) {
            stmt.bindString(6, bld);
        }
 
        String uglu = entity.getUglu();
        if (uglu != null) {
            stmt.bindString(7, uglu);
        }
 
        String ket = entity.getKet();
        if (ket != null) {
            stmt.bindString(8, ket);
        }
 
        String nqt = entity.getNqt();
        if (nqt != null) {
            stmt.bindString(9, nqt);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(10, time);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public LocalMeasurementNiaoYe readEntity(Cursor cursor, int offset) {
        LocalMeasurementNiaoYe entity = new LocalMeasurementNiaoYe( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // personId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // idCard
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // pro
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // bld
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // uglu
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ket
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // nqt
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, LocalMeasurementNiaoYe entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPersonId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIdCard(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPro(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setBld(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUglu(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setKet(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setNqt(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(LocalMeasurementNiaoYe entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(LocalMeasurementNiaoYe entity) {
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
