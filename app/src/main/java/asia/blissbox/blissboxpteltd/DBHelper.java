package asia.blissbox.blissboxpteltd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public final class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "Projects_blissbox";
    private static final int DBVERSION = 1;
    private static final String DBTABLECART = "cart";
    private static final String CARTID = "_id";
    private static final String CARTGIFTBOXID = "giftbox_id";
    private static final String CARTUNIVERSEID = "universe_id";
    private static final String CARTINITIAL = "inital";
    private static final String CARTTHUMBNAIL = "thumbnail";
    private static final String CARTNAME = "name";
    private static final String CARTPRICE = "price";
    private static final String CARTDESCRIPTION = "description";
    private static final String CARTPDFURL = "pdf_url";
    private static final String CARTREVIEW = "review";
    private static final String CARTDELETEDAT = "deleted_at";
    private static final String CARTCREATEDAT = "created_at";
    private static final String CARTUPDATEDAT = "updated_at";


    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String createTableCart = "CREATE TABLE " + DBTABLECART + "  (" + CARTID + " INTEGER PRIMARY KEY, " + CARTGIFTBOXID + " INTEGER, " + CARTUNIVERSEID + " INTEGER, " + CARTINITIAL + " TEXT, " + CARTTHUMBNAIL + " TEXT, " + CARTNAME + " TEXT, " + CARTPRICE + " REAL, " + CARTDESCRIPTION + " TEXT, " + CARTPDFURL + " TEXT, " + CARTREVIEW + " INTEGER, " + CARTDELETEDAT + " TEXT, " + CARTCREATEDAT + " TEXT, " + CARTUPDATEDAT + " TEXT); ";
        db.execSQL(createTableCart);
        Log.e("DBHelper", "Table Created: " + DBTABLECART + "\nQuery: " + createTableCart);
    }

    public void onUpgrade(SQLiteDatabase db, int i, int j) {

        db.execSQL("DROP TABLE IF EXISTS " + DBTABLECART);
        this.onCreate(db);
    }

    public final long insertIntoCart(ObjectBoxes box) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CARTGIFTBOXID, Integer.valueOf(box.getGiftboxId()));
        values.put(CARTUNIVERSEID, Integer.valueOf(box.getUniverseId()));
        values.put(CARTINITIAL, box.getInitial());
        values.put(CARTTHUMBNAIL, box.getThumbnail());
        values.put(CARTNAME, box.getName());
        values.put(CARTPRICE, Double.valueOf(box.getPrice()));
        values.put(CARTDESCRIPTION, box.getDescription());
        values.put(CARTPDFURL, box.getPdf_url());
        values.put(CARTREVIEW, box.getPdf_url());
        values.put(CARTDELETEDAT, box.getDeleted_at());
        values.put(CARTCREATEDAT, box.getCreated_at());
        values.put(CARTUPDATEDAT, box.getUpdated_at());
        long result = db.insert(DBTABLECART, null, values);
        Log.d("DBHelper", "SQL INSERT: " + result);
        db.close();
        return result;
    }

    public final int updateCartData(ObjectBoxes box) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CARTGIFTBOXID, Integer.valueOf(box.getGiftboxId()));
        values.put(CARTUNIVERSEID, Integer.valueOf(box.getUniverseId()));
        values.put(CARTINITIAL, box.getInitial());
        values.put(CARTTHUMBNAIL, box.getThumbnail());
        values.put(CARTNAME, box.getName());
        values.put(CARTPRICE, Double.valueOf(box.getPrice()));
        values.put(CARTDESCRIPTION, box.getDescription());
        values.put(CARTPDFURL, box.getPdf_url());
        values.put(CARTREVIEW, box.getPdf_url());
        values.put(CARTDELETEDAT, box.getDeleted_at());
        values.put(CARTCREATEDAT, box.getCreated_at());
        values.put(CARTUPDATEDAT, box.getUpdated_at());
        String condition = CARTID + "= ?";
        String[] args = {String.valueOf(box.getGiftboxId())};
        int result = db.update(DBTABLECART, values, condition, args);
        db.close();
        return result;
    }

    public final int deleteCartData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = CARTID + " = ?";
        Object[] elements$iv = new String[]{String.valueOf(id)};
        String[] args = (String[]) elements$iv;
        int result = db.delete(DBTABLECART, condition, args);
        db.close();
        return result;
    }

    public final ArrayList getCartData() {
        ArrayList boxes = new ArrayList();
        String SelectQuery = "SELECT * FROM " + DBTABLECART;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ObjectBoxes box = new ObjectBoxes(cursor.getInt(1)
                        , cursor.getInt(2)
                        , cursor.getString(3)
                        , cursor.getString(4)
                        , cursor.getString(5)
                        , (float) cursor.getDouble(6)
                        , cursor.getString(7)
                        , cursor.getString(8)
                        , cursor.getInt(9)
                        , cursor.getString(10)
                        , cursor.getString(11)
                        , cursor.getString(12));
                boxes.add(box);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return boxes;
    }
}
