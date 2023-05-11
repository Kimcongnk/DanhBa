package ph29875.fpoly.congnkph29875_ontep9_5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "DanhBa", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable="CREATE TABLE thongtin(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                "dienThoai TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "ghiChu TEXT NOT NULL)";
        db.execSQL(creatTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
String dropTable="DROP TABLE IF EXISTS thongtin";
        db.execSQL(dropTable);
        onCreate(db);
    }
}
