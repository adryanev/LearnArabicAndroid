package com.inkubator.adryan.learnarabic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.inkubator.adryan.learnarabic.model.Kategori;
import com.inkubator.adryan.learnarabic.model.Materi;
import com.inkubator.adryan.learnarabic.model.MateriDetail;
import com.inkubator.adryan.learnarabic.model.Soal;
import com.inkubator.adryan.learnarabic.model.SubMateri;
import com.inkubator.adryan.learnarabic.model.Ujian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adryanev on 17/10/17.
 */

public class DbHelper extends SQLiteOpenHelper {


    //log
    private static final String LOG = "DbHelper";

    //versi database
    private static final Integer DATABASE_VERSION = 4;

    //nama database
    private static final String DATABASE_NAME = "app-learning";

    //nama-nama tabel
    private static final String TABLE_KATEGORI = "kategori";
    private static final String TABLE_MATERI = "materi";
    private static final String TABLE_MATERI_DETAIL = "materi_detail";
    private static final String TABLE_SOAL = "soal";
    private static final String TABLE_SUB_MATERI = "sub_materi";
    private static final String TABLE_UJIAN = "ujian";

    //common field name
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_IDKATEGORI = "idKategori";
    private static final String KEY_IDMATERI = "idMateri";
    private static final String KEY_IDSUBMATERI = "idSubMateri";
    private static final String KEY_GAMBAR = "gambar";

    //field tabel kategori
    private static final String KEY_NAMAKATEGORI = "namaKategori";

    //field tabel materi
    private static final String KEY_NAMAMATERI = "namaMateri";


    //field tabel materi_detail
    private static final String KEY_IDMATERI_DETAIL = "idMateriDetail";
    private static final String KEY_ISI = "isi";
    private static final String KEY_TERJEMAHAN = "terjemahan";

    //field tabel soal
    private static final String KEY_IDSOAL = "idSoal";
    private static final String KEY_SOAL = "soal";
    private static final String KEY_A = "a";
    private static final String KEY_B = "b";
    private static final String KEY_C = "c";
    private static final String KEY_D = "d";
    private static final String KEY_JAWABAN = "jawaban";

    //field tabel ujian
    private static final String KEY_ID_UJIAN = "idUjian";
    private static final String KEY_ID_USER = "idUser";
    private static final String KEY_TOTAL_SKOR = "totalSkor";
    private static final String KEY_SYNC = "sync";

    //Create table Statement
    //tabel kategori
    private static final String CREATE_TABLE_KATEGORI = "CREATE TABLE  "+TABLE_KATEGORI+" ( " +
            KEY_IDKATEGORI+" INTEGER PRIMARY KEY," +
            KEY_NAMAKATEGORI+" TEXT, "+
            KEY_TIMESTAMP+" DATETIME)";

    //tabel materi
    private static final String CREATE_TABLE_MATERI = "CREATE TABLE  "+TABLE_MATERI+" ( "+
            KEY_IDMATERI+" INTEGER PRIMARY KEY," +
            KEY_NAMAMATERI+" TEXT, "+
            KEY_TIMESTAMP+" DATETIME)";

    //tabel sub-materi
    private static final String CREATE_TABLE_SUB_MATERI = "CREATE TABLE "+TABLE_SUB_MATERI+" ("+
            KEY_IDSUBMATERI+" INTEGER PRIMARY KEY,"+
            KEY_IDMATERI+" INTEGER,"+
            KEY_IDKATEGORI+" INTEGER,"+
            KEY_TIMESTAMP+" DATETIME,"+
            "FOREIGN KEY ("+KEY_IDMATERI+")"+
            "REFERENCES "+TABLE_MATERI+"("+KEY_IDMATERI+")"+
            "ON UPDATE CASCADE "+
            "ON DELETE CASCADE,"+
            "FOREIGN KEY ("+KEY_IDKATEGORI+")"+
            "REFERENCES "+TABLE_KATEGORI+"("+KEY_IDKATEGORI+")"+
            "ON UPDATE CASCADE "+
            "ON DELETE CASCADE"+
            ")";

    //tabel materi-detail
    private static final String CREATE_TABLE_MATERI_DETAIL = "CREATE TABLE  "+TABLE_MATERI_DETAIL+" ( " +
            KEY_IDMATERI_DETAIL+" INTEGER PRIMARY KEY," +
            KEY_IDSUBMATERI+" INTEGER,"+
            KEY_ISI+" TEXT, "+
            KEY_GAMBAR+" TEXT,"+
            KEY_TERJEMAHAN+" TEXT,"+
            KEY_TIMESTAMP+" DATETIME,"+
            "FOREIGN KEY ("+KEY_IDSUBMATERI+")"+
            "REFERENCES "+TABLE_SUB_MATERI+"("+KEY_IDSUBMATERI+")"+
            "ON UPDATE CASCADE "+
            "ON DELETE CASCADE"+
            ")";

    //tabel soal
    private static final String CREATE_TABLE_SOAL = "CREATE TABLE "+TABLE_SOAL+" ( "+
            KEY_IDSOAL+" INTEGER PRIMARY KEY," +
            KEY_GAMBAR+" TEXT, "+
            KEY_SOAL+" TEXT, "+
            KEY_A+" TEXT, "+
            KEY_B+" TEXT, "+
            KEY_C+" TEXT, "+
            KEY_D+" TEXT, "+
            KEY_JAWABAN+" TEXT, "+
            KEY_TIMESTAMP+" DATETIME)";

    //tabel ujian
    private static final String CREATE_TABLE_UJIAN = "CREATE TABLE "+TABLE_UJIAN+" ( "+
            KEY_ID_UJIAN+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_ID_USER+" INTEGER,"+
            KEY_TOTAL_SKOR+" INTEGER,"+
            KEY_SYNC+" INTEGER)";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_KATEGORI);
        Log.d(LOG,"Create table "+TABLE_KATEGORI);
        db.execSQL(CREATE_TABLE_MATERI);
        Log.d(LOG,"Create table "+TABLE_MATERI);
        db.execSQL(CREATE_TABLE_SUB_MATERI);
        Log.d(LOG, "Create table "+TABLE_SUB_MATERI);
        db.execSQL(CREATE_TABLE_MATERI_DETAIL);
        Log.d(LOG,"Create table "+TABLE_MATERI_DETAIL);
        db.execSQL(CREATE_TABLE_SOAL);
        Log.d(LOG,"Create table "+TABLE_SOAL);
        db.execSQL(CREATE_TABLE_UJIAN);
        Log.d(LOG,"Create table "+TABLE_UJIAN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop older tables
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_KATEGORI);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MATERI);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SUB_MATERI);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MATERI_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SOAL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_UJIAN);


        //create new database
        onCreate(db);
    }

    //--------------------------------METHOD TABEL KATEGORI --------------------------------------//

    /**
     * Buat data Kategori
     */

    public Long createKategori(Kategori kategori){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IDKATEGORI,kategori.getIdKategori());
        values.put(KEY_NAMAKATEGORI,kategori.getNamaKategori());
        values.put(KEY_TIMESTAMP,kategori.getTimestamp());
        Long kategori_id = db.insert(TABLE_KATEGORI,null,values);
        Log.d(LOG,"INSERT KATEGORI "+kategori.getIdKategori().toString()+" to Table");
        return kategori_id;
    }

    /**
     * Ambil 1 kategori
     * @return kategori
     */
    public Kategori getKategori(long idKategori){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * from "+TABLE_KATEGORI+" WHERE "+KEY_IDKATEGORI+" = "+idKategori;
        Log.e(LOG,selectQuery);

        Cursor c = db.rawQuery(selectQuery,null);

        if(c != null){
            c.moveToFirst();
        }
        Kategori kategori = new Kategori();
        kategori.setIdKategori(c.getInt(c.getColumnIndex(KEY_IDKATEGORI)));
        kategori.setNamaKategori(c.getString(c.getColumnIndex(KEY_NAMAKATEGORI)));
        kategori.setTimestamp(c.getString(c.getColumnIndex(KEY_TIMESTAMP)));

        return kategori;
    }

    /**
     * Ambil semua kategori
     */

    public List<Kategori> kategoriList (){

        List<Kategori> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_KATEGORI;
        Log.e(LOG,selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        //loop dari row pertama
        if(c.moveToFirst()){
            do{
                Kategori kategori = new Kategori();
                kategori.setIdKategori(c.getInt(c.getColumnIndex(KEY_IDKATEGORI)));
                kategori.setNamaKategori(c.getString(c.getColumnIndex(KEY_NAMAKATEGORI)));
                kategori.setTimestamp(c.getString(c.getColumnIndex(KEY_TIMESTAMP)));

                //masukkan ke array list
                list.add(kategori);
            }while(c.moveToNext());
        }
        return list;
    }

    /**
     * Mendapatkan Kategori dari materi
     */
    public List<Kategori> kategoriByMateri(long idMateri){

        //penampung kategori
        List<Kategori> kategoriList = new ArrayList<>();

        //buat list menampung id kategori
        List<String> idkategori = new ArrayList<>();

        //tampung materi yg mau di cari kategorinya
        Materi materi = getMateri(idMateri);

        //masukkan kategori tersebut kedalam list id kategori dalam bentuk arraylist
        idkategori=Arrays.asList(materi.getIdKategori().split(","));

        for(int i =0; i<idkategori.size(); i++){
           Kategori k = this.getKategori(Long.parseLong(idkategori.get(i)));

           kategoriList.add(k);
        }
        return kategoriList;
    }

    public Integer updateKategori(Kategori kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_IDKATEGORI,kategori.getIdKategori());
        cv.put(KEY_NAMAKATEGORI,kategori.getIdKategori());
        cv.put(KEY_TIMESTAMP,kategori.getTimestamp());

        return db.update(TABLE_KATEGORI,cv, KEY_IDKATEGORI+"=?", new String[]{String.valueOf(kategori.getIdKategori())});


    }

    public String getNamaKategori(Integer idKategori){
        SQLiteDatabase db = this.getReadableDatabase();
        String namaKategori = null;
        String query = "SELECT "+KEY_NAMAKATEGORI+" FROM "+TABLE_KATEGORI+" WHERE "+KEY_IDKATEGORI+" = "+idKategori;
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()){
            namaKategori = c.getString(c.getColumnIndex(KEY_NAMAKATEGORI));
        }

        return namaKategori;
    }

    //--------------------------------METHOD TABEL MATERI --------------------------------------//

    /**
     * Membuat menambah materi
     */

    public Long createMateri(Materi materi){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IDMATERI, materi.getIdMateri());
        cv.put(KEY_NAMAMATERI,materi.getNamaMateri());
        cv.put(KEY_TIMESTAMP,materi.getTimestamp());

        Long idkat= db.insert(TABLE_MATERI,null,cv);
        Log.d(LOG,"INSERT MATERI "+materi.getIdMateri().toString()+" to Table");

        return idkat;
    }

    /**
     * Mendapatkan materi dari id materi
     * @param idMateri
     * @return materi
     */
    public Materi getMateri(long idMateri){
        Materi materi = new Materi();
        String sqlQuery = "SELECT * FROM "+TABLE_MATERI+" WHERE "+KEY_IDMATERI+" = "+idMateri;
        Log.e(LOG,sqlQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sqlQuery,null);

        if(c != null) c.moveToFirst();

        materi.setIdMateri(c.getInt(c.getColumnIndex(KEY_IDMATERI)));
        materi.setNamaMateri(c.getString(c.getColumnIndex(KEY_NAMAKATEGORI)));
        materi.setTimestamp(c.getString(c.getColumnIndex(KEY_TIMESTAMP)));

        return materi;
    }


    /**
     * Mendapatkan semua materi
     */

    public List<Materi> getSemuaMateri(){
        List<Materi> materiList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM "+TABLE_MATERI;
        Log.e(LOG,sqlQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sqlQuery,null);

        if(c.moveToFirst()){
            do{
                Materi materi = new Materi();
                materi.setIdMateri(c.getInt(c.getColumnIndex(KEY_IDMATERI)));
                materi.setNamaMateri(c.getString(c.getColumnIndex(KEY_NAMAMATERI)));
                materi.setTimestamp(c.getString(c.getColumnIndex(KEY_TIMESTAMP)));

                materiList.add(materi);
            }while (c.moveToNext());
        }
        return materiList;
    }

    public Integer updateMateri(Materi materi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IDMATERI,materi.getIdMateri());
        cv.put(KEY_NAMAMATERI,materi.getNamaMateri());
        cv.put(KEY_TIMESTAMP,materi.getTimestamp());

        return db.update(TABLE_MATERI,cv,KEY_IDMATERI+" =?",new String[]{ String.valueOf(materi.getIdMateri())
        });
    }

    public String getNamaMateri(Integer idMateri){
        SQLiteDatabase db = this.getReadableDatabase();
        String namaMateri = null;
        String query = "SELECT "+KEY_NAMAMATERI+" FROM "+TABLE_MATERI+" WHERE "+KEY_IDMATERI+" = "+idMateri;
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()){
            namaMateri = c.getString(c.getColumnIndex(KEY_NAMAMATERI));
        }

        return namaMateri;
    }

    //--------------------------------METHOD TABEL MATERI DETAIL --------------------------------------//

    public Long createMateriDetail(MateriDetail materiDetail){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IDMATERI_DETAIL,materiDetail.getIdMateriDetail());
        cv.put(KEY_IDSUBMATERI,materiDetail.getIdSubMateri());
        cv.put(KEY_ISI,materiDetail.getIsi());
        cv.put(KEY_GAMBAR,materiDetail.getGambar());
        cv.put(KEY_TERJEMAHAN,materiDetail.getTerjemahan());
        cv.put(KEY_TIMESTAMP,materiDetail.getTimestamp());

        Long idMateriDetail = db.insert(TABLE_MATERI_DETAIL,null,cv);
        Log.d(LOG,"INSERT MATERI DETAIL "+materiDetail.getIdMateriDetail().toString()+" to Table");
        return idMateriDetail;

    }

    public List<MateriDetail> getAllMateriDetailByIdKategoriAndIdMateri(Long idKategori, Long idMateri){
        List<MateriDetail> allMateriDetail = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT * FROM "+TABLE_MATERI_DETAIL+" WHERE "+KEY_IDKATEGORI+" = "+idKategori+" AND "+KEY_IDMATERI+" = "+idMateri;
        Log.e(LOG,sqlQuery);
        Cursor c = db.rawQuery(sqlQuery,null);

        if(c.moveToFirst()){
            do{
                MateriDetail materiDetail = new MateriDetail();
                materiDetail.setIdMateriDetail(c.getInt(c.getColumnIndex(KEY_IDMATERI_DETAIL)));
                materiDetail.setIdSubMateri(c.getInt(c.getColumnIndex(KEY_IDSUBMATERI)));
                materiDetail.setGambar(c.getString(c.getColumnIndex(KEY_GAMBAR)));
                materiDetail.setIsi(c.getString(c.getColumnIndex(KEY_ISI)));
                materiDetail.setTerjemahan(c.getString(c.getColumnIndex(KEY_TERJEMAHAN)));
                materiDetail.setTimestamp(c.getString(c.getColumnIndex(KEY_TIMESTAMP)));

                allMateriDetail.add(materiDetail);
            }while(c.moveToNext());
        }

        return allMateriDetail;
    }
    public List<MateriDetail> getMateriDetailBySubMateri(Integer idSubMateri){
        List<MateriDetail> allMateriDetail = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT * FROM "+TABLE_MATERI_DETAIL+" WHERE "+KEY_IDSUBMATERI+" = "+idSubMateri;
        Log.e(LOG,sqlQuery);
        Cursor c = db.rawQuery(sqlQuery,null);

        if(c.moveToFirst()){
            do{
                MateriDetail materiDetail = new MateriDetail();
                materiDetail.setIdMateriDetail(c.getInt(c.getColumnIndex(KEY_IDMATERI_DETAIL)));
                materiDetail.setIdSubMateri(c.getInt(c.getColumnIndex(KEY_IDSUBMATERI)));
                materiDetail.setGambar(c.getString(c.getColumnIndex(KEY_GAMBAR)));
                materiDetail.setIsi(c.getString(c.getColumnIndex(KEY_ISI)));
                materiDetail.setTerjemahan(c.getString(c.getColumnIndex(KEY_TERJEMAHAN)));
                materiDetail.setTimestamp(c.getString(c.getColumnIndex(KEY_TIMESTAMP)));

                allMateriDetail.add(materiDetail);
            }while(c.moveToNext());
        }

        return allMateriDetail;
    }

    public Integer updateMateriDetail(MateriDetail materiDetail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_IDMATERI_DETAIL,materiDetail.getIdMateriDetail());
        cv.put(KEY_IDSUBMATERI,materiDetail.getIdSubMateri());
        cv.put(KEY_GAMBAR,materiDetail.getGambar());
        cv.put(KEY_ISI,materiDetail.getIsi());
        cv.put(KEY_TERJEMAHAN,materiDetail.getTerjemahan());
        cv.put(KEY_TIMESTAMP, materiDetail.getTimestamp());

        return db.update(TABLE_MATERI_DETAIL,cv,KEY_IDMATERI_DETAIL+" =?", new String[]{String.valueOf(materiDetail.getIdMateriDetail())});
    }


    //--------------------------------METHOD TABEL SOAL --------------------------------------//

    public Long createSoal(Soal soal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_IDSOAL,soal.getIdSoal());
        cv.put(KEY_GAMBAR,soal.getGambar());
        cv.put(KEY_SOAL,soal.getSoal());
        cv.put(KEY_A,soal.getA());
        cv.put(KEY_B,soal.getB());
        cv.put(KEY_C,soal.getC());
        cv.put(KEY_D,soal.getD());
        cv.put(KEY_JAWABAN,soal.getJawaban());
        cv.put(KEY_TIMESTAMP,soal.getTimestamp());

        Long idsoal = db.insert(TABLE_SOAL,null,cv);
        Log.d(LOG,"INSERT KATEGORI "+soal.getIdSoal().toString()+" to Table");

        return idsoal;
    }

    public List<Soal> getAllSoal(){
        List<Soal> soals = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT * FROM "+TABLE_SOAL;
        Log.e(LOG,sqlQuery);

        Cursor c = db.rawQuery(sqlQuery,null);

        if(c.moveToFirst()){
            do{
                Soal soal = new Soal();
                soal.setIdSoal(c.getInt(c.getColumnIndex(KEY_IDSOAL)));
                soal.setGambar(c.getString(c.getColumnIndex(KEY_GAMBAR)));
                soal.setSoal(c.getString(c.getColumnIndex(KEY_SOAL)));
                soal.setA(c.getString(c.getColumnIndex(KEY_A)));
                soal.setB(c.getString(c.getColumnIndex(KEY_B)));
                soal.setC(c.getString(c.getColumnIndex(KEY_C)));
                soal.setD(c.getString(c.getColumnIndex(KEY_D)));
                soal.setJawaban(c.getString(c.getColumnIndex(KEY_JAWABAN)));
                soal.setTimestamp(c.getString(c.getColumnIndex(KEY_TIMESTAMP)));

                soals.add(soal);

            }while(c.moveToNext());
        }
        return soals;
    }
    public List<Soal> getSoalByLimit(Long limit){
        List<Soal> soals = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT * FROM "+TABLE_SOAL+" ORDER BY RANDOM() LIMIT "+limit;
        Log.e(LOG,sqlQuery);

        Cursor c = db.rawQuery(sqlQuery,null);

        if(c.moveToFirst()){
            do{
                Soal soal = new Soal();
                soal.setIdSoal(c.getInt(c.getColumnIndex(KEY_IDSOAL)));
                soal.setGambar(c.getString(c.getColumnIndex(KEY_GAMBAR)));
                soal.setSoal(c.getString(c.getColumnIndex(KEY_SOAL)));
                soal.setA(c.getString(c.getColumnIndex(KEY_A)));
                soal.setB(c.getString(c.getColumnIndex(KEY_B)));
                soal.setC(c.getString(c.getColumnIndex(KEY_C)));
                soal.setD(c.getString(c.getColumnIndex(KEY_D)));
                soal.setJawaban(c.getString(c.getColumnIndex(KEY_JAWABAN)));
                soal.setTimestamp(c.getString(c.getColumnIndex(KEY_TIMESTAMP)));

                soals.add(soal);

            }while(c.moveToNext());
        }
        return soals;
    }

    public Integer updateSoal(Soal soal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IDSOAL,soal.getIdSoal());
        cv.put(KEY_GAMBAR,soal.getGambar());
        cv.put(KEY_SOAL,soal.getSoal());
        cv.put(KEY_A,soal.getA());
        cv.put(KEY_B,soal.getB());
        cv.put(KEY_C,soal.getC());
        cv.put(KEY_D,soal.getD());
        cv.put(KEY_JAWABAN,soal.getJawaban());
        cv.put(KEY_TIMESTAMP,soal.getTimestamp());

        return db.update(TABLE_SOAL,cv,KEY_IDSOAL+" =?", new String[]{String.valueOf(soal.getIdSoal())});
    }

    //----------------------------------- TABEL SUB MATERI ---------------------------------------//

    public Long createSubMateri(SubMateri subMateri){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IDSUBMATERI,subMateri.getIdSubMateri());
        cv.put(KEY_IDMATERI,subMateri.getIdMateri());
        cv.put(KEY_IDKATEGORI,subMateri.getIdKategori());
        cv.put(KEY_TIMESTAMP,subMateri.getTimestamp());
        Long idSubMateri = db.insert(TABLE_SUB_MATERI,null,cv);

        return idSubMateri;

    }

    public Integer getSubMateriByMateriKategori(Integer idMateri, Integer idKategori){
        Integer idSubMateri = 0;
        SQLiteDatabase db = getReadableDatabase();
        String sqlQuery = "SELECT "+KEY_IDSUBMATERI+" from "+TABLE_SUB_MATERI +" WHERE "+KEY_IDMATERI+" = "+idMateri+
                " AND "+KEY_IDKATEGORI+" = "+idKategori;
        Cursor c = db.rawQuery(sqlQuery,null);
        if(c.moveToFirst()){
            do{
                idSubMateri = c.getInt(c.getColumnIndex(KEY_IDSUBMATERI));
            }while (c.moveToNext());
        }

        return idSubMateri;
    }

    public Integer getIdKategoriFromSubMateri(int idSubMateri){
        Integer idkategori = 0;
        String sql = "SELECT "+KEY_IDKATEGORI+" FROM "+TABLE_SUB_MATERI+" WHERE "+KEY_IDSUBMATERI+" = "+idSubMateri;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            idkategori = c.getInt(c.getColumnIndex(KEY_IDKATEGORI));
        }

        return idkategori;

    }
    public Integer getIdMateriFromSubMateri(int idSubMateri){
        Integer idMateri = 0;
        String sql = "SELECT "+KEY_IDMATERI+" FROM "+TABLE_SUB_MATERI+" WHERE "+KEY_IDSUBMATERI+" = "+idSubMateri;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            idMateri = c.getInt(c.getColumnIndex(KEY_IDMATERI));
        }

        return idMateri;
    }

    //-------------------------------METHOD TABLE UJIAN-------------------------------------------//
    public Long setUjian(Integer idUser, Integer totalSkor, Integer sync){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID_USER, idUser);
        cv.put(KEY_TOTAL_SKOR, totalSkor);
        cv.put(KEY_SYNC, sync);
        Long idUjian = db.insert(TABLE_UJIAN,null,cv);

        return idUjian;
    }

    public List<Ujian> getNotSyncListUjian(){
        List<Ujian> ujianList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_UJIAN+" WHERE "+KEY_SYNC+" = 0";

        Cursor c = db.rawQuery(query,null);

        if (c.moveToFirst()){
            Ujian ujian = new Ujian();
            ujian.setIdUjian(c.getInt(c.getColumnIndex(KEY_ID_USER)));
            ujian.setIdUser(c.getInt(c.getColumnIndex(KEY_ID_USER)));
            ujian.setTotalSkor(c.getInt(c.getColumnIndex(KEY_TOTAL_SKOR)));
            ujian.setSync(c.getInt(c.getColumnIndex(KEY_SYNC)));

            ujianList.add(ujian);
        }
        return ujianList;
    }

    public boolean updateSyncUjian(Integer idUjian){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_SYNC,1);
        db.update(TABLE_UJIAN,cv,KEY_ID_UJIAN+" = "+idUjian,null);
        return true;
    }

    //------------------------------- END OF TABLE METHOD ----------------------------------------//

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public void truncateTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName,null,null);

    }
}
