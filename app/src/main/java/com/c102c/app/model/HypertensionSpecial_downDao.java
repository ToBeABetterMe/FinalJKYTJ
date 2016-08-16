package com.c102c.app.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.c102c.app.model.HypertensionSpecial_down;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HYPERTENSION_SPECIAL_DOWN".
*/
public class HypertensionSpecial_downDao extends AbstractDao<HypertensionSpecial_down, String> {

    public static final String TABLENAME = "HYPERTENSION_SPECIAL_DOWN";

    /**
     * Properties of entity HypertensionSpecial_down.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property PersonId = new Property(0, String.class, "personId", true, "PERSON_ID");
        public final static Property SpecialNo = new Property(1, String.class, "specialNo", false, "SPECIAL_NO");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property RegisterDate = new Property(3, String.class, "registerDate", false, "REGISTER_DATE");
        public final static Property RegisterOrgCode = new Property(4, String.class, "registerOrgCode", false, "REGISTER_ORG_CODE");
        public final static Property RegisterDoctorCode = new Property(5, String.class, "registerDoctorCode", false, "REGISTER_DOCTOR_CODE");
        public final static Property RegisterDoctorName = new Property(6, String.class, "registerDoctorName", false, "REGISTER_DOCTOR_NAME");
        public final static Property DiagnoseDate = new Property(7, String.class, "diagnoseDate", false, "DIAGNOSE_DATE");
        public final static Property DiagnoseOrgCode = new Property(8, String.class, "diagnoseOrgCode", false, "DIAGNOSE_ORG_CODE");
        public final static Property DiagnoseDoctorCode = new Property(9, String.class, "diagnoseDoctorCode", false, "DIAGNOSE_DOCTOR_CODE");
        public final static Property DiagnoseDoctorName = new Property(10, String.class, "diagnoseDoctorName", false, "DIAGNOSE_DOCTOR_NAME");
        public final static Property SBP = new Property(11, String.class, "SBP", false, "SBP");
        public final static Property DBP = new Property(12, String.class, "DBP", false, "DBP");
        public final static Property BloodPressureLevel = new Property(13, String.class, "bloodPressureLevel", false, "BLOOD_PRESSURE_LEVEL");
        public final static Property NextFlupDate = new Property(14, String.class, "nextFlupDate", false, "NEXT_FLUP_DATE");
    };


    public HypertensionSpecial_downDao(DaoConfig config) {
        super(config);
    }
    
    public HypertensionSpecial_downDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HYPERTENSION_SPECIAL_DOWN\" (" + //
                "\"PERSON_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: personId
                "\"SPECIAL_NO\" TEXT," + // 1: specialNo
                "\"NAME\" TEXT," + // 2: name
                "\"REGISTER_DATE\" TEXT," + // 3: registerDate
                "\"REGISTER_ORG_CODE\" TEXT," + // 4: registerOrgCode
                "\"REGISTER_DOCTOR_CODE\" TEXT," + // 5: registerDoctorCode
                "\"REGISTER_DOCTOR_NAME\" TEXT," + // 6: registerDoctorName
                "\"DIAGNOSE_DATE\" TEXT," + // 7: diagnoseDate
                "\"DIAGNOSE_ORG_CODE\" TEXT," + // 8: diagnoseOrgCode
                "\"DIAGNOSE_DOCTOR_CODE\" TEXT," + // 9: diagnoseDoctorCode
                "\"DIAGNOSE_DOCTOR_NAME\" TEXT," + // 10: diagnoseDoctorName
                "\"SBP\" TEXT," + // 11: SBP
                "\"DBP\" TEXT," + // 12: DBP
                "\"BLOOD_PRESSURE_LEVEL\" TEXT," + // 13: bloodPressureLevel
                "\"NEXT_FLUP_DATE\" TEXT);"); // 14: nextFlupDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HYPERTENSION_SPECIAL_DOWN\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, HypertensionSpecial_down entity) {
        stmt.clearBindings();
 
        String personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindString(1, personId);
        }
 
        String specialNo = entity.getSpecialNo();
        if (specialNo != null) {
            stmt.bindString(2, specialNo);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String registerDate = entity.getRegisterDate();
        if (registerDate != null) {
            stmt.bindString(4, registerDate);
        }
 
        String registerOrgCode = entity.getRegisterOrgCode();
        if (registerOrgCode != null) {
            stmt.bindString(5, registerOrgCode);
        }
 
        String registerDoctorCode = entity.getRegisterDoctorCode();
        if (registerDoctorCode != null) {
            stmt.bindString(6, registerDoctorCode);
        }
 
        String registerDoctorName = entity.getRegisterDoctorName();
        if (registerDoctorName != null) {
            stmt.bindString(7, registerDoctorName);
        }
 
        String diagnoseDate = entity.getDiagnoseDate();
        if (diagnoseDate != null) {
            stmt.bindString(8, diagnoseDate);
        }
 
        String diagnoseOrgCode = entity.getDiagnoseOrgCode();
        if (diagnoseOrgCode != null) {
            stmt.bindString(9, diagnoseOrgCode);
        }
 
        String diagnoseDoctorCode = entity.getDiagnoseDoctorCode();
        if (diagnoseDoctorCode != null) {
            stmt.bindString(10, diagnoseDoctorCode);
        }
 
        String diagnoseDoctorName = entity.getDiagnoseDoctorName();
        if (diagnoseDoctorName != null) {
            stmt.bindString(11, diagnoseDoctorName);
        }
 
        String SBP = entity.getSBP();
        if (SBP != null) {
            stmt.bindString(12, SBP);
        }
 
        String DBP = entity.getDBP();
        if (DBP != null) {
            stmt.bindString(13, DBP);
        }
 
        String bloodPressureLevel = entity.getBloodPressureLevel();
        if (bloodPressureLevel != null) {
            stmt.bindString(14, bloodPressureLevel);
        }
 
        String nextFlupDate = entity.getNextFlupDate();
        if (nextFlupDate != null) {
            stmt.bindString(15, nextFlupDate);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public HypertensionSpecial_down readEntity(Cursor cursor, int offset) {
        HypertensionSpecial_down entity = new HypertensionSpecial_down( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // personId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // specialNo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // registerDate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // registerOrgCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // registerDoctorCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // registerDoctorName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // diagnoseDate
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // diagnoseOrgCode
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // diagnoseDoctorCode
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // diagnoseDoctorName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // SBP
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // DBP
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // bloodPressureLevel
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14) // nextFlupDate
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, HypertensionSpecial_down entity, int offset) {
        entity.setPersonId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setSpecialNo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRegisterDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRegisterOrgCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRegisterDoctorCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setRegisterDoctorName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDiagnoseDate(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDiagnoseOrgCode(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setDiagnoseDoctorCode(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDiagnoseDoctorName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSBP(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setDBP(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setBloodPressureLevel(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setNextFlupDate(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(HypertensionSpecial_down entity, long rowId) {
        return entity.getPersonId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(HypertensionSpecial_down entity) {
        if(entity != null) {
            return entity.getPersonId();
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