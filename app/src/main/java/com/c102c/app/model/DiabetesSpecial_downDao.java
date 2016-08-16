package com.c102c.app.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.c102c.app.model.DiabetesSpecial_down;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DIABETES_SPECIAL_DOWN".
*/
public class DiabetesSpecial_downDao extends AbstractDao<DiabetesSpecial_down, String> {

    public static final String TABLENAME = "DIABETES_SPECIAL_DOWN";

    /**
     * Properties of entity DiabetesSpecial_down.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property PersonId = new Property(0, String.class, "personId", true, "PERSON_ID");
        public final static Property SpecialNo = new Property(1, String.class, "specialNo", false, "SPECIAL_NO");
        public final static Property RegisterDate = new Property(2, String.class, "registerDate", false, "REGISTER_DATE");
        public final static Property RegisterOrgCode = new Property(3, String.class, "registerOrgCode", false, "REGISTER_ORG_CODE");
        public final static Property RegisterDoctorCode = new Property(4, String.class, "registerDoctorCode", false, "REGISTER_DOCTOR_CODE");
        public final static Property RegisterDoctorName = new Property(5, String.class, "registerDoctorName", false, "REGISTER_DOCTOR_NAME");
        public final static Property DiagnoseDate = new Property(6, String.class, "diagnoseDate", false, "DIAGNOSE_DATE");
        public final static Property DiagnoseOrgCode = new Property(7, String.class, "diagnoseOrgCode", false, "DIAGNOSE_ORG_CODE");
        public final static Property DiagnoseDoctorCode = new Property(8, String.class, "diagnoseDoctorCode", false, "DIAGNOSE_DOCTOR_CODE");
        public final static Property DiagnoseDoctorName = new Property(9, String.class, "diagnoseDoctorName", false, "DIAGNOSE_DOCTOR_NAME");
        public final static Property SBP = new Property(10, String.class, "SBP", false, "SBP");
        public final static Property DBP = new Property(11, String.class, "DBP", false, "DBP");
        public final static Property BloodPressureLevel = new Property(12, String.class, "bloodPressureLevel", false, "BLOOD_PRESSURE_LEVEL");
        public final static Property NextFlupDate = new Property(13, String.class, "nextFlupDate", false, "NEXT_FLUP_DATE");
        public final static Property DiabetesLevelCode = new Property(14, String.class, "diabetesLevelCode", false, "DIABETES_LEVEL_CODE");
        public final static Property CaseType = new Property(15, String.class, "caseType", false, "CASE_TYPE");
        public final static Property ICDCode = new Property(16, String.class, "ICDCode", false, "ICDCODE");
        public final static Property DoseCode = new Property(17, String.class, "doseCode", false, "DOSE_CODE");
        public final static Property NoMedicationCode = new Property(18, String.class, "noMedicationCode", false, "NO_MEDICATION_CODE");
        public final static Property DrugCost = new Property(19, String.class, "drugCost", false, "DRUG_COST");
        public final static Property FamilyHistoryCode = new Property(20, String.class, "familyHistoryCode", false, "FAMILY_HISTORY_CODE");
        public final static Property Height = new Property(21, String.class, "height", false, "HEIGHT");
        public final static Property RandomBloodGlucose = new Property(22, String.class, "randomBloodGlucose", false, "RANDOM_BLOOD_GLUCOSE");
        public final static Property KidneyDiseaseYears = new Property(23, String.class, "kidneyDiseaseYears", false, "KIDNEY_DISEASE_YEARS");
        public final static Property RetinalDiseaseYears = new Property(24, String.class, "retinalDiseaseYears", false, "RETINAL_DISEASE_YEARS");
        public final static Property NeuropathyYears = new Property(25, String.class, "neuropathyYears", false, "NEUROPATHY_YEARS");
        public final static Property SkinInfectionYears = new Property(26, String.class, "skinInfectionYears", false, "SKIN_INFECTION_YEARS");
        public final static Property VascularDiseaseYears = new Property(27, String.class, "vascularDiseaseYears", false, "VASCULAR_DISEASE_YEARS");
        public final static Property NoComplYears = new Property(28, String.class, "noComplYears", false, "NO_COMPL_YEARS");
        public final static Property ComplicationDate = new Property(29, String.class, "complicationDate", false, "COMPLICATION_DATE");
        public final static Property InitialDisease = new Property(30, String.class, "initialDisease", false, "INITIAL_DISEASE");
        public final static Property CurrentDisease = new Property(31, String.class, "currentDisease", false, "CURRENT_DISEASE");
        public final static Property CaseSourceCode = new Property(32, String.class, "caseSourceCode", false, "CASE_SOURCE_CODE");
        public final static Property CaseSourceOther = new Property(33, String.class, "caseSourceOther", false, "CASE_SOURCE_OTHER");
        public final static Property Name = new Property(34, String.class, "name", false, "NAME");
    };


    public DiabetesSpecial_downDao(DaoConfig config) {
        super(config);
    }
    
    public DiabetesSpecial_downDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DIABETES_SPECIAL_DOWN\" (" + //
                "\"PERSON_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: personId
                "\"SPECIAL_NO\" TEXT," + // 1: specialNo
                "\"REGISTER_DATE\" TEXT," + // 2: registerDate
                "\"REGISTER_ORG_CODE\" TEXT," + // 3: registerOrgCode
                "\"REGISTER_DOCTOR_CODE\" TEXT," + // 4: registerDoctorCode
                "\"REGISTER_DOCTOR_NAME\" TEXT," + // 5: registerDoctorName
                "\"DIAGNOSE_DATE\" TEXT," + // 6: diagnoseDate
                "\"DIAGNOSE_ORG_CODE\" TEXT," + // 7: diagnoseOrgCode
                "\"DIAGNOSE_DOCTOR_CODE\" TEXT," + // 8: diagnoseDoctorCode
                "\"DIAGNOSE_DOCTOR_NAME\" TEXT," + // 9: diagnoseDoctorName
                "\"SBP\" TEXT," + // 10: SBP
                "\"DBP\" TEXT," + // 11: DBP
                "\"BLOOD_PRESSURE_LEVEL\" TEXT," + // 12: bloodPressureLevel
                "\"NEXT_FLUP_DATE\" TEXT," + // 13: nextFlupDate
                "\"DIABETES_LEVEL_CODE\" TEXT," + // 14: diabetesLevelCode
                "\"CASE_TYPE\" TEXT," + // 15: caseType
                "\"ICDCODE\" TEXT," + // 16: ICDCode
                "\"DOSE_CODE\" TEXT," + // 17: doseCode
                "\"NO_MEDICATION_CODE\" TEXT," + // 18: noMedicationCode
                "\"DRUG_COST\" TEXT," + // 19: drugCost
                "\"FAMILY_HISTORY_CODE\" TEXT," + // 20: familyHistoryCode
                "\"HEIGHT\" TEXT," + // 21: height
                "\"RANDOM_BLOOD_GLUCOSE\" TEXT," + // 22: randomBloodGlucose
                "\"KIDNEY_DISEASE_YEARS\" TEXT," + // 23: kidneyDiseaseYears
                "\"RETINAL_DISEASE_YEARS\" TEXT," + // 24: retinalDiseaseYears
                "\"NEUROPATHY_YEARS\" TEXT," + // 25: neuropathyYears
                "\"SKIN_INFECTION_YEARS\" TEXT," + // 26: skinInfectionYears
                "\"VASCULAR_DISEASE_YEARS\" TEXT," + // 27: vascularDiseaseYears
                "\"NO_COMPL_YEARS\" TEXT," + // 28: noComplYears
                "\"COMPLICATION_DATE\" TEXT," + // 29: complicationDate
                "\"INITIAL_DISEASE\" TEXT," + // 30: initialDisease
                "\"CURRENT_DISEASE\" TEXT," + // 31: currentDisease
                "\"CASE_SOURCE_CODE\" TEXT," + // 32: caseSourceCode
                "\"CASE_SOURCE_OTHER\" TEXT," + // 33: caseSourceOther
                "\"NAME\" TEXT);"); // 34: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DIABETES_SPECIAL_DOWN\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DiabetesSpecial_down entity) {
        stmt.clearBindings();
 
        String personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindString(1, personId);
        }
 
        String specialNo = entity.getSpecialNo();
        if (specialNo != null) {
            stmt.bindString(2, specialNo);
        }
 
        String registerDate = entity.getRegisterDate();
        if (registerDate != null) {
            stmt.bindString(3, registerDate);
        }
 
        String registerOrgCode = entity.getRegisterOrgCode();
        if (registerOrgCode != null) {
            stmt.bindString(4, registerOrgCode);
        }
 
        String registerDoctorCode = entity.getRegisterDoctorCode();
        if (registerDoctorCode != null) {
            stmt.bindString(5, registerDoctorCode);
        }
 
        String registerDoctorName = entity.getRegisterDoctorName();
        if (registerDoctorName != null) {
            stmt.bindString(6, registerDoctorName);
        }
 
        String diagnoseDate = entity.getDiagnoseDate();
        if (diagnoseDate != null) {
            stmt.bindString(7, diagnoseDate);
        }
 
        String diagnoseOrgCode = entity.getDiagnoseOrgCode();
        if (diagnoseOrgCode != null) {
            stmt.bindString(8, diagnoseOrgCode);
        }
 
        String diagnoseDoctorCode = entity.getDiagnoseDoctorCode();
        if (diagnoseDoctorCode != null) {
            stmt.bindString(9, diagnoseDoctorCode);
        }
 
        String diagnoseDoctorName = entity.getDiagnoseDoctorName();
        if (diagnoseDoctorName != null) {
            stmt.bindString(10, diagnoseDoctorName);
        }
 
        String SBP = entity.getSBP();
        if (SBP != null) {
            stmt.bindString(11, SBP);
        }
 
        String DBP = entity.getDBP();
        if (DBP != null) {
            stmt.bindString(12, DBP);
        }
 
        String bloodPressureLevel = entity.getBloodPressureLevel();
        if (bloodPressureLevel != null) {
            stmt.bindString(13, bloodPressureLevel);
        }
 
        String nextFlupDate = entity.getNextFlupDate();
        if (nextFlupDate != null) {
            stmt.bindString(14, nextFlupDate);
        }
 
        String diabetesLevelCode = entity.getDiabetesLevelCode();
        if (diabetesLevelCode != null) {
            stmt.bindString(15, diabetesLevelCode);
        }
 
        String caseType = entity.getCaseType();
        if (caseType != null) {
            stmt.bindString(16, caseType);
        }
 
        String ICDCode = entity.getICDCode();
        if (ICDCode != null) {
            stmt.bindString(17, ICDCode);
        }
 
        String doseCode = entity.getDoseCode();
        if (doseCode != null) {
            stmt.bindString(18, doseCode);
        }
 
        String noMedicationCode = entity.getNoMedicationCode();
        if (noMedicationCode != null) {
            stmt.bindString(19, noMedicationCode);
        }
 
        String drugCost = entity.getDrugCost();
        if (drugCost != null) {
            stmt.bindString(20, drugCost);
        }
 
        String familyHistoryCode = entity.getFamilyHistoryCode();
        if (familyHistoryCode != null) {
            stmt.bindString(21, familyHistoryCode);
        }
 
        String height = entity.getHeight();
        if (height != null) {
            stmt.bindString(22, height);
        }
 
        String randomBloodGlucose = entity.getRandomBloodGlucose();
        if (randomBloodGlucose != null) {
            stmt.bindString(23, randomBloodGlucose);
        }
 
        String kidneyDiseaseYears = entity.getKidneyDiseaseYears();
        if (kidneyDiseaseYears != null) {
            stmt.bindString(24, kidneyDiseaseYears);
        }
 
        String retinalDiseaseYears = entity.getRetinalDiseaseYears();
        if (retinalDiseaseYears != null) {
            stmt.bindString(25, retinalDiseaseYears);
        }
 
        String neuropathyYears = entity.getNeuropathyYears();
        if (neuropathyYears != null) {
            stmt.bindString(26, neuropathyYears);
        }
 
        String skinInfectionYears = entity.getSkinInfectionYears();
        if (skinInfectionYears != null) {
            stmt.bindString(27, skinInfectionYears);
        }
 
        String vascularDiseaseYears = entity.getVascularDiseaseYears();
        if (vascularDiseaseYears != null) {
            stmt.bindString(28, vascularDiseaseYears);
        }
 
        String noComplYears = entity.getNoComplYears();
        if (noComplYears != null) {
            stmt.bindString(29, noComplYears);
        }
 
        String complicationDate = entity.getComplicationDate();
        if (complicationDate != null) {
            stmt.bindString(30, complicationDate);
        }
 
        String initialDisease = entity.getInitialDisease();
        if (initialDisease != null) {
            stmt.bindString(31, initialDisease);
        }
 
        String currentDisease = entity.getCurrentDisease();
        if (currentDisease != null) {
            stmt.bindString(32, currentDisease);
        }
 
        String caseSourceCode = entity.getCaseSourceCode();
        if (caseSourceCode != null) {
            stmt.bindString(33, caseSourceCode);
        }
 
        String caseSourceOther = entity.getCaseSourceOther();
        if (caseSourceOther != null) {
            stmt.bindString(34, caseSourceOther);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(35, name);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DiabetesSpecial_down readEntity(Cursor cursor, int offset) {
        DiabetesSpecial_down entity = new DiabetesSpecial_down( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // personId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // specialNo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // registerDate
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // registerOrgCode
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // registerDoctorCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // registerDoctorName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // diagnoseDate
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // diagnoseOrgCode
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // diagnoseDoctorCode
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // diagnoseDoctorName
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // SBP
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // DBP
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // bloodPressureLevel
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // nextFlupDate
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // diabetesLevelCode
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // caseType
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // ICDCode
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // doseCode
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // noMedicationCode
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // drugCost
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // familyHistoryCode
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // height
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // randomBloodGlucose
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // kidneyDiseaseYears
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // retinalDiseaseYears
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // neuropathyYears
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // skinInfectionYears
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // vascularDiseaseYears
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // noComplYears
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // complicationDate
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // initialDisease
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // currentDisease
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // caseSourceCode
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // caseSourceOther
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34) // name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DiabetesSpecial_down entity, int offset) {
        entity.setPersonId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setSpecialNo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRegisterDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRegisterOrgCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRegisterDoctorCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRegisterDoctorName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDiagnoseDate(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDiagnoseOrgCode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDiagnoseDoctorCode(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setDiagnoseDoctorName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSBP(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setDBP(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setBloodPressureLevel(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setNextFlupDate(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setDiabetesLevelCode(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setCaseType(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setICDCode(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setDoseCode(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setNoMedicationCode(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setDrugCost(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setFamilyHistoryCode(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setHeight(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setRandomBloodGlucose(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setKidneyDiseaseYears(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setRetinalDiseaseYears(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setNeuropathyYears(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setSkinInfectionYears(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setVascularDiseaseYears(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setNoComplYears(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setComplicationDate(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setInitialDisease(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setCurrentDisease(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setCaseSourceCode(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setCaseSourceOther(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setName(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(DiabetesSpecial_down entity, long rowId) {
        return entity.getPersonId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(DiabetesSpecial_down entity) {
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