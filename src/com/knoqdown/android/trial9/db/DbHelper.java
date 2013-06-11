package com.knoqdown.android.trial9.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final String DBNAME="todo.db";
	private static final int DBVERSION=1;
	
	public static final String TB_TODO = "todo";
	public static final String COL_ID = "id";
	public static final String COL_TODO_TASK = "task";
	public static final String COL_TODO_ISDONE = "is_done";
	public static final String COL_TODO_TSDONE = "ts_done";

	public DbHelper(Context context) {
		super(context, DBNAME, null, DBVERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE " +TB_TODO+ " ("
				+ COL_ID + " integer primary key autoincrement,"
				+ COL_TODO_TASK + " varchar(100),"
				+ COL_TODO_ISDONE + " int(1),"
				+ COL_TODO_TSDONE + " int(11)"
				+ ");";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TB_TODO);
		onCreate(db);
	}

}
