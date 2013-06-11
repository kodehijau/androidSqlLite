package com.knoqdown.android.trial9.db;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TodoDataSource {
	SQLiteDatabase db;
	DbHelper helper;
	String[] all_cols = {DbHelper.COL_ID,DbHelper.COL_TODO_TASK,DbHelper.COL_TODO_ISDONE,DbHelper.COL_TODO_TSDONE};
	
	public TodoDataSource(Context context) {
		helper = new DbHelper(context);
	}
	
	public void open() throws SQLException {
		db = helper.getWritableDatabase();
	}
	
	public void close() {
		db.close();
	}
	
	public long addTodo(String task) {
		ContentValues values =  new ContentValues();
		values.put(DbHelper.COL_TODO_TASK, task);
		values.put(DbHelper.COL_TODO_ISDONE, 0);
		return db.insert(DbHelper.TB_TODO, null, values);
	}
	
	public long editTodo(int intTodoId, String task) {
		ContentValues values =  new ContentValues();
		values.put(DbHelper.COL_TODO_TASK, task);
		return db.update(DbHelper.TB_TODO, values, DbHelper.COL_ID+"="+intTodoId, null);
	}
	
	public long cekTodo(int intTodoId) {
		ContentValues values = new ContentValues();
		values.put(DbHelper.COL_TODO_ISDONE, 1);
		values.put(DbHelper.COL_TODO_TSDONE, System.currentTimeMillis()/1000);
		return db.update(DbHelper.TB_TODO, values, DbHelper.COL_ID+"="+intTodoId +" and "+DbHelper.COL_TODO_ISDONE+"=0", null);
	}
	
	public Todo getTodo(int id){
		Cursor c = db.query(DbHelper.TB_TODO, all_cols, DbHelper.COL_ID+"="+id, null, null, null, null);
		c.moveToFirst();
		Todo todo = new Todo(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3));
		c.close();
		return todo;
	}
	
	public void delTodo(int id) {
		db.delete(DbHelper.TB_TODO, DbHelper.COL_ID+"="+id, null);
	}
	
	public List<Todo> getAllTodo() {
		List<Todo> todos = new ArrayList<Todo>();
		Cursor c = db.query(DbHelper.TB_TODO, all_cols, null, null, null, null,DbHelper.COL_TODO_ISDONE);
		c.moveToFirst();
		while(!c.isAfterLast()) {
			Todo todo = new Todo(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3));
			todos.add(todo);
			c.moveToNext();
		}
		c.close();
		return todos;
	}
}
