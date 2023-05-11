package ph29875.fpoly.congnkph29875_ontep9_5;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Dao {
    private SQLiteDatabase db;

    public Dao(Context mContext){
        DbHelper dbHelper = new DbHelper(mContext);
        db = dbHelper.getWritableDatabase();//cho phép đọc và ghi dữ liệu vào database
    }

    public long insertFood(DanhBa obj) {
        if (isDuplicate(obj)) {
            return -1; // duplicate data
        }
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("dienThoai", obj.getSoDt());
        values.put("email", obj.geteMail());
        values.put("ghiChu", obj.getGhiChu());
        return db.insert("thongtin", null, values);
    }


    public int deleteFoodById(int ID){
        return db.delete("thongtin","id=?",
                new String[]{String.valueOf(ID)});
    }

    public boolean updateFood(DanhBa obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("dienThoai",obj.getSoDt());
        values.put("email",obj.geteMail());
        values.put("ghiChu",obj.getGhiChu());
        if(db.update("thongtin",values,"id=?",
                new String[]{String.valueOf(obj.getIb())})>0)
            return true;
        return false;
    }

    public boolean isDuplicate(DanhBa obj) {
        Cursor cursor = db.rawQuery("SELECT * FROM thongtin WHERE dienThoai=? OR email=?",
                new String[]{obj.getSoDt(), obj.geteMail()});
        boolean isDuplicate = cursor.moveToFirst();
        cursor.close();
        return isDuplicate;
    }

    //lấy dữ liệu
    public ArrayList<DanhBa> getAllData(){
        String sqlgetAll="SELECT * FROM thongtin";
        return getData(sqlgetAll);
    }

    @SuppressLint("Range")
    public ArrayList<DanhBa> getData(String sql, String ...Agrments){
        ArrayList<DanhBa> arrayList= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,Agrments);
        while (cursor.moveToNext()){
            DanhBa danhBa = new DanhBa();
            danhBa.setIb(cursor.getInt(cursor.getColumnIndex("id")));
            danhBa.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            danhBa.setSoDt(cursor.getString(cursor.getColumnIndex("dienThoai")));
            danhBa.seteMail(cursor.getString(cursor.getColumnIndex("email")));
            danhBa.setGhiChu(cursor.getString(cursor.getColumnIndex("ghiChu")));
            arrayList.add(danhBa);
        }
        return arrayList;
    }
}
