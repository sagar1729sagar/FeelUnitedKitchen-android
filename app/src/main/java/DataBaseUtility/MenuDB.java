package DataBaseUtility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

import Models.MenuItem;
import Util.Util;

/**
 * Created by sagar on 30/05/17.
 */
public class MenuDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Menu";
    private static final String TABLE_MNEU = "MenuItems";

    private static final String KEY_ID = "id";
    private static final String ITEM_ID = "itemId";
    private static final String ITEM_NAME = "itemName";
    private static final String ITEM_URL = "itemUrl";
    private static final String PRICE_TODAY = "priceToday";
    private static final String PRICE_TOMORROW = "priceTomorrow";
    private static final String PRICE_LATER = "priceLater";
    private static final String AVAILABLE_MONDAY = "available_Monday";
    private static final String AVAILABLE_TUESDAY = "available_Tuesday";
    private static final String AVAILABLE_WEDNESDAY = "available_Wednesday";
    private static final String AVAILABLE_THRUSDAY = "available_Thrusday";
    private static final String AVAILABLE_FRIDAY = "available_Friday";
    private static final String AVAILABLE_SATURDAY = "available_Saturday";
    private static final String AVAILABLE_SUNDAY = "available_Sunday";
    private static final String ITEM_CATEGEORY = "item_categeory";
    private static final String FOOD_TYPE = "foodType";
    private static final String ITEM_DESCRIPTION = "itemDescription";
    private static final String DISPLAY_ORDER = "displayOrder";
    private static final String OBJECT_ID = "objectId";
    private static final String CREATED = "created";
    private static final String UPDATED = "updated";
    private Util util;

    public MenuDB(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        util = new Util();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE `"+TABLE_MNEU+"` ( `"+KEY_ID+"` INTEGER, `"+ITEM_ID+"` TEXT, `"
                +ITEM_NAME+"` TEXT, `"+ITEM_URL+"` TEXT, `"+PRICE_TODAY+"` TEXT, `"+PRICE_TOMORROW+"` TEXT, `"
                +PRICE_LATER+"` TEXT, `"+AVAILABLE_MONDAY+"` TEXT, `"+AVAILABLE_TUESDAY+"` TEXT, `"
                +AVAILABLE_WEDNESDAY+"` TEXT, `"+AVAILABLE_THRUSDAY+"` TEXT, `"
                +AVAILABLE_FRIDAY+"` TEXT, `"+AVAILABLE_SATURDAY+"` TEXT, `"+AVAILABLE_SUNDAY+"` TEXT, `"
                +ITEM_CATEGEORY+"` TEXT, `"+FOOD_TYPE+"` TEXT, `"+ITEM_DESCRIPTION+"` TEXT, `"
                +DISPLAY_ORDER+"` TEXT, `"+OBJECT_ID+"` TEXT, `"+CREATED+"` TEXT, `"
                +UPDATED+"` TEXT, PRIMARY KEY(`"+KEY_ID+"`) )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MNEU);
        onCreate(db);
    }

    public void addMenuItem(MenuItem item){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(ITEM_ID,item.getItemId());
        values.put(ITEM_NAME,item.getItemName());
        values.put(ITEM_URL,item.getItemUrl());
        values.put(PRICE_TODAY,item.getPriceToday());
        values.put(PRICE_TOMORROW,item.getPriceTomorrow());
        values.put(PRICE_LATER,item.getPriceLater());
        values.put(AVAILABLE_MONDAY,item.getAvailableMonday());
        values.put(AVAILABLE_TUESDAY,item.getAvailableTuesday());
        values.put(AVAILABLE_WEDNESDAY,item.getAvailableWedenesday());
        values.put(AVAILABLE_THRUSDAY,item.getAvailableThrusday());
        values.put(AVAILABLE_FRIDAY,item.getAvailableFriday());
        values.put(AVAILABLE_SATURDAY,item.getAvailableSaturday());
        values.put(AVAILABLE_SUNDAY,item.getAvailableSunday());
        values.put(ITEM_CATEGEORY,item.getItemCategeory());
        values.put(FOOD_TYPE,item.getFoodType());
        values.put(ITEM_DESCRIPTION,item.getItemDescription());
        values.put(DISPLAY_ORDER,item.getDisplayOrder());
        values.put(OBJECT_ID,item.getObjectId());
        values.put(CREATED, String.valueOf(item.getCreated()));
        values.put(UPDATED, String.valueOf(item.getUpdated()));

        db.insert(TABLE_MNEU, null, values);
        db.close();

    }

    public ArrayList<MenuItem> getMenuItems(){
        ArrayList<MenuItem> items = new ArrayList<>();

        String query = "SELECT * FROM "+TABLE_MNEU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                MenuItem item = new MenuItem();
                item.setItemId(cursor.getString(1));
                item.setItemName(cursor.getString(2));
                item.setItemUrl(cursor.getString(3));
                item.setPriceToday(cursor.getString(4));
                item.setPriceTomorrow(cursor.getString(5));
                item.setPriceLater(cursor.getString(6));
                item.setAvailableMonday(cursor.getString(7));
                item.setAvailableTuesday(cursor.getString(8));
                item.setAvailableWedenesday(cursor.getString(9));
                item.setAvailableThrusday(cursor.getString(10));
                item.setAvailableFriday(cursor.getString(11));
                item.setAvailableSaturday(cursor.getString(12));
                item.setAvailableSunday(cursor.getString(13));
                item.setItemCategeory(cursor.getString(14));
                item.setFoodType(cursor.getString(15));
                item.setItemDescription(cursor.getString(16));
                item.setDisplayOrder(cursor.getString(17));
                item.setObjectId(cursor.getString(18));
                item.setUpdated(util.convertStringtoDate(cursor.getString(19)));
                item.setCreated(util.convertStringtoDate(cursor.getString(20)));

                items.add(item);
            } while (cursor.moveToNext());

        }

        cursor.close();
        return items;
    }

    public void deleteItem(MenuItem item) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MNEU, ITEM_NAME + " = ?", new String[]{String.valueOf(item.getItemName())});
        db.close();
    }

    public int getCount(){
        String query = "SELECT * FROM "+TABLE_MNEU;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        int i = cursor.getCount();
        cursor.close();

        return i;
    }

    public void resetDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MNEU);
        onCreate(db);
    }
    
    public boolean checkForItem(MenuItem item){

        ArrayList<MenuItem> items = new ArrayList<>();
        items = getMenuItems();
        if (items.size() != 0) {
            
            for (int i = 0 ; i < items.size();i++){

                if (items.get(i).getItemName().equals(item.getItemName())) {

                    return true;
                }
            }
        }

        return  false;
    }

}
