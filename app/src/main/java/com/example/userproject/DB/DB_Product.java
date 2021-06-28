package com.example.userproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;


import com.example.userproject.models.FProduct;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DB_Product  extends SQLiteAssetHelper {


 public DB_Product(Context context) {
  super(context, DB_NAME, null, DB_VERSION);
 }


 public static final  String DB_NAME = "prodact_db.db";
 public static final  int DB_VERSION = 1;
 public static final  String C_TB_NAME = "prodact";
 public static final  String C_CLM_ID = "id";
 public static final  String C_CLM_NAME = "name_pr";
 public static final  String C_CLM_PRICE = "price_pr";
 public static final  String C_CLM_Image = "imaga";
 public static final  String C_CLM_DESC = "desc_pr";



 public boolean insertProdcut (FProduct product) {
  SQLiteDatabase db = getWritableDatabase();
  ContentValues cv = new ContentValues();
  cv.put(C_CLM_NAME, product.getName());
  cv.put(C_CLM_PRICE, product.getPrice());
  cv.put(C_CLM_DESC, product.getDesc());
  cv.put(C_CLM_Image, product.getImag());
  long res =  db.insert(C_TB_NAME,null,cv);
  return res>0;
 }


 public boolean updateProdcut(FProduct product) {
  SQLiteDatabase db = getWritableDatabase();

  ContentValues cv =new ContentValues();
  cv.put(C_CLM_NAME, product.getName());
  cv.put(C_CLM_PRICE, product.getPrice());
  cv.put(C_CLM_DESC, product.getDesc());
  cv.put(C_CLM_Image, product.getImag());

  long res =  db.update(C_TB_NAME, cv,"id=?", new String[]{String.valueOf(product.getId())});
  return res>0;
 }
 public boolean deleteProduct(int  productId) {
  SQLiteDatabase db = getWritableDatabase();

  long res =  db.delete(C_TB_NAME, "id=?", new String[]{String.valueOf(productId)}
  );
  return res>0;
 }

 public ArrayList<FProduct> getAllProduct() {
  SQLiteDatabase db = getWritableDatabase();
  Cursor cursor = db.query(C_TB_NAME,null,null,null,null,null,null);
  ArrayList<FProduct> products = new ArrayList<>();
  if(cursor.moveToFirst()){
   do{
    int id = cursor.getInt(cursor.getColumnIndex(C_CLM_ID));
    String name = cursor.getString(cursor.getColumnIndex(C_CLM_NAME));
    String price = cursor.getString(cursor.getColumnIndex(C_CLM_PRICE));
    String desc = cursor.getString(cursor.getColumnIndex(C_CLM_DESC));
    String image = cursor.getString(cursor.getColumnIndex(C_CLM_Image));

    products.add(new FProduct(id,name,price,desc,image));
   }
   while (cursor.moveToNext());
  }

  return products;
 }


 public FProduct getProuct(int productId) {
  SQLiteDatabase db = getWritableDatabase();
  Cursor cursor = db.query(C_TB_NAME, null, C_CLM_ID+"=?", new String[]{String.valueOf(productId)}, null, null, null);

  FProduct p;
  if (cursor.moveToFirst()) {
   int id = cursor.getInt(cursor.getColumnIndex(C_CLM_ID));

   String name = cursor.getString(cursor.getColumnIndex(C_CLM_NAME));
   String price = cursor.getString(cursor.getColumnIndex(C_CLM_PRICE));
   String desc = cursor.getString(cursor.getColumnIndex(C_CLM_DESC));
   String image = cursor.getString(cursor.getColumnIndex(C_CLM_Image));

   p =new FProduct(id, name,price, desc, image);
   return p;
  }

  return null;

 }

 public ArrayList<FProduct> get_search_Product(String k){

  SQLiteDatabase db = getWritableDatabase();
  Cursor cursor =  db.query(C_TB_NAME,null,C_CLM_NAME +" LIKE ?",
          new String[]{"%"+k+"%"},null,null,null);
  ArrayList<FProduct> products = new ArrayList<>();
  if(cursor.moveToFirst()){
   do{
    int id = cursor.getInt(cursor.getColumnIndex(C_CLM_ID));
    String name = cursor.getString(cursor.getColumnIndex(C_CLM_NAME));
    String price = cursor.getString(cursor.getColumnIndex(C_CLM_PRICE));
    String desc = cursor.getString(cursor.getColumnIndex(C_CLM_DESC));
    String image = cursor.getString(cursor.getColumnIndex(C_CLM_Image));


    products.add(new FProduct(id,name,price,desc,image));
   }
   while (cursor.moveToNext());
  }


  return products;
 }

 public long getProductCount(){
  SQLiteDatabase db = getWritableDatabase();
  long count = DatabaseUtils.queryNumEntries(db,C_TB_NAME);
  return count;
 }

}
