package com.the.dionisio.apk.client.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.the.dionisio.apk.client.model.dto.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 16/05/2017.
 */

public class GenreDAO
{
    private SQLiteDatabase db;
    private Context contextView;

    public GenreDAO(Context context)
    {
        contextView = context;
    }

    public void createGenre(Person person)
    {
        db = getDataBase();

        try
        {
            for(String genre : person.genres)
            {
                ContentValues values = new ContentValues();

                values.put("_idPerson", person._id);
                values.put("genre", genre);

                db.insert("genre", null, values);
            }
        }
        catch (Exception error)
        {

        }

        db.close();
    }

    public void updateGenre(Person person)
    {
        db = getDataBase();

        try
        {
            for(String genre : person.genres)
            {
                ContentValues values = new ContentValues();

                values.put("genre", String.valueOf(person.genres));

                db.update("genre", values, "_idPerson = ?", getParameters(person._id));
            }
        }
        catch (Exception error)
        {

        }

        db.close();
    }

    public void deleteGenre(Person person)
    {
        db = getDataBase();

        try
        {
            db.delete("genre", "_idPerson = " + person._id, null);
        }
        catch (Exception error)
        {

        }

        db.close();
    }

    public List<String> searchGenre(Person person)
    {
        db = getDataBase();
        List<String> genre = new ArrayList<>();

        try
        {
            Cursor findGenre = db.rawQuery("SELECT * FROM genre WHERE _idPerson = ?", getParameters(person._id));

            while(findGenre.moveToNext())
            {
                genre.add(findGenre.getString(findGenre.getColumnIndex("genre")));
            }
        }
        catch (Exception error)
        {

        }

        db.close();
        return genre;
    }

    private String[] getParameters(String field)
    {
        String[] parameters = {field};
        return parameters;
    }

    /***
     * Abre o banco com o contexto da classe [contextView]
     * @return
     */
    public SQLiteDatabase getDataBase()
    {
        DataBase dataBase = new DataBase(contextView);
        return dataBase.getWritableDatabase();
    }
}
