package com.the.dionisio.apk.client.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daniel on 05/05/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final String NOME_BD = "TheDionisio";
    private static final int VERSAO_BD = 1;

    public DataBase(Context ctx){
        super(ctx, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            StringBuilder sql = new StringBuilder();
                sql
                .append("CREATE TABLE person (")
                .append("_idPerson TEXT PRIMARY KEY, ")
                .append("name TEXT NOT NULL, ")
                .append("email TEXT NOT NULL, ")
                .append("password TEXT NOT NULL, ")
                .append("birth TEXT, ")
                .append("cpf TEXT, ")
                .append("sex TEXT, ")
                .append("picture TEXT, ")
                .append("isActive INTEGER);");

            db.execSQL(sql.toString());
        }
        catch (Exception e)
        {
            e.toString();
        }

        try
        {
            StringBuilder sql = new StringBuilder();
                sql
                .append("CREATE TABLE genre (")
                .append("_idGenre INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("_idPerson TEXT NOT NULL, ")
                .append("genre TEXT NOT NULL, ")
                .append("FOREIGN KEY (_idPerson) REFERENCES person (_idPerson));");

            db.execSQL(sql.toString());
        }
        catch (Exception e)
        {
            e.toString();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try
        {
            StringBuilder sql = new StringBuilder();
                sql
                .append("DROP TABLE IF EXISTS person");

            db.execSQL(sql.toString());

            StringBuilder sql2 = new StringBuilder();
                sql2
                .append("DROP TABLE IF EXISTS genre");

            db.execSQL(sql2.toString());

            onCreate(db);
        }
        catch (Exception e)
        {
            e.toString();
        }
    }
}
