package com.example.userproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;


import com.example.userproject.models.FOrder;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public  class DB_Order  extends SQLiteAssetHelper {

   public DB_Order(Context context) {
       super(context, DB_NAME, null, DB_VERSION);
   }

   public static final  String DB_NAME = "Order_db.db";
   public static final  int DB_VERSION = 1;
   public static final  String C_TB_NAME = "orders";
   public static final  String C_CLM_ID = "id";
   public static final  String C_CLM_NAME = "name";
   public static final  String C_CLM_Addres = "adress";
   public static final  String C_CLM_Image = "image";
   public static final  String C_CLM_Phone= "phone";



   public boolean insertOrder (FOrder order) {
       SQLiteDatabase db = getWritableDatabase();
       ContentValues cv = new ContentValues();
       cv.put(C_CLM_NAME, order.getName());
       cv.put(C_CLM_Addres, order.getAddress());
       cv.put(C_CLM_Phone, order.getPhone());
       cv.put(C_CLM_Image, order.getImag());
       long res =  db.insert(C_TB_NAME,null,cv);
       return res>0;
   }


   public boolean updateOrder(FOrder order) {
       SQLiteDatabase db = getWritableDatabase();

       ContentValues cv =new ContentValues();
       cv.put(C_CLM_NAME, order.getName());
       cv.put(C_CLM_Addres, order.getAddress());
       cv.put(C_CLM_Phone, order.getPhone());
       cv.put(C_CLM_Image, order.getImag());

       long res =  db.update(C_TB_NAME, cv,"id=?", new String[]{String.valueOf(order.getId())});
       return res>0;
   }
   public boolean deleteOrder(int  orderId) {
       SQLiteDatabase db = getWritableDatabase();

       long res =  db.delete(C_TB_NAME, "id=?", new String[]{String.valueOf(orderId)}
       );
       return res>0;
   }

   public ArrayList<FOrder> getAllOrder() {
       SQLiteDatabase db = getWritableDatabase();
       Cursor cursor = db.query(C_TB_NAME,null,null,null,null,null,null,null);
       ArrayList<FOrder> orders = new ArrayList<>();
       if(cursor.moveToFirst()){
           do{
               int id = cursor.getInt(cursor.getColumnIndex(C_CLM_ID));
               String name = cursor.getString(cursor.getColumnIndex(C_CLM_NAME));
               int address = cursor.getInt(cursor.getColumnIndex(C_CLM_Addres));
               int phone = cursor.getInt(cursor.getColumnIndex(C_CLM_Phone));
               String image = cursor.getString(cursor.getColumnIndex(C_CLM_Image));

               orders.add(new FOrder(id,name,image,address,phone));
           }
           while (cursor.moveToNext());
       }

       return orders;
   }



   public long getOrderCount(){
       SQLiteDatabase db = getWritableDatabase();
       long count = DatabaseUtils.queryNumEntries(db,C_TB_NAME);
       return count;
   }

}


