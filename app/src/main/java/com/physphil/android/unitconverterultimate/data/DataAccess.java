/*
 * Copyright 2015 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.ConversionState;

/**
 * Extension of SQLiteOpenHelper to access SQLite database
 * Created by Phizz on 15-08-02.
 */
public class DataAccess extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "UNIT_CONVERTER";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONVERSION_STATE = "ConversionState";

    private static final String COLUMN_CONVERSION_STATE_ID = "conversionId";
    private static final String COLUMN_CONVERSION_STATE_FROM_ID = "fromId";
    private static final String COLUMN_CONVERSION_STATE_TO_ID = "toId";

    private static final String CREATE_CONVERSION_STATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CONVERSION_STATE + "(" +
                    COLUMN_CONVERSION_STATE_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_CONVERSION_STATE_FROM_ID + " INTEGER, " +
                    COLUMN_CONVERSION_STATE_TO_ID + " INTEGER);";

    private static DataAccess mInstance;
    private SQLiteDatabase mDb;

    private DataAccess(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mDb = getWritableDatabase();
    }

    /**
     * Get instance of DataAccess object
     * @param context context
     * @return DataAccess instance
     */
    public static DataAccess getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new DataAccess(context.getApplicationContext());
        }

        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONVERSION_STATE);
        createTables(db);
    }

    private void createTables(SQLiteDatabase db)
    {
        db.execSQL(CREATE_CONVERSION_STATE_TABLE);
    }

    /**
     * Save current state of given conversion
     * @param cs ConversionState of current conversion
     */
    public void saveConversionState(final ConversionState cs)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_CONVERSION_STATE_ID, cs.getConversionId());
                cv.put(COLUMN_CONVERSION_STATE_FROM_ID, cs.getFromId());
                cv.put(COLUMN_CONVERSION_STATE_TO_ID, cs.getToId());
                mDb.replace(TABLE_CONVERSION_STATE, null, cv);
            }
        }).run();
    }

    /**
     * Get saved ConversionState from database
     * @param conversionId id of conversion
     * @return saved ConversionState
     */
    @SuppressWarnings("ResourceType")
    public ConversionState getConversionState(@Conversion.id int conversionId)
    {
        String sql =
                "SELECT " +
                        COLUMN_CONVERSION_STATE_FROM_ID + ", " +
                        COLUMN_CONVERSION_STATE_TO_ID + " " +
                "FROM " +
                        TABLE_CONVERSION_STATE + " " +
                "WHERE " +
                        COLUMN_CONVERSION_STATE_ID + " = ?";

        Cursor c = mDb.rawQuery(sql, new String[]{Integer.toString(conversionId)});
        if(c.moveToFirst())
        {
            ConversionState cs = new ConversionState(conversionId,
                    c.getInt(c.getColumnIndex(COLUMN_CONVERSION_STATE_FROM_ID)),
                    c.getInt(c.getColumnIndex(COLUMN_CONVERSION_STATE_TO_ID)));

            c.close();
            return cs;
        }
        else
        {
            c.close();
            // No saved state, create new one with first two units selected
            return new ConversionState(conversionId);
        }
    }
}
