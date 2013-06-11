package com.knoqdown.android.trial9;

import com.knoqdown.android.trial9.db.Todo;
import com.knoqdown.android.trial9.db.TodoDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class FormActivity extends Activity implements OnClickListener {
	Todo todo;
	EditText txtTask;
	TodoDataSource ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
		
		TextView tvJudul = (TextView) findViewById(R.id.tvJudul);
		findViewById(R.id.btnSave).setOnClickListener(this);
		txtTask = (EditText) findViewById(R.id.txtTask);
		ds = new TodoDataSource(this);
		ds.open();
		int id = getIntent().getIntExtra("intTodoId", 0);
		if(id>0){
			todo = ds.getTodo(id);
			tvJudul.setText("Ubah Task");
			txtTask.setText(todo.getStrTask());
		} else {
			tvJudul.setText("Tambah Task");
		}
		ds.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		ds.open();
		String task = txtTask.getText().toString();
		if(todo == null) {
			ds.addTodo(task);
		} else {
			ds.editTodo(todo.getIntId(), task);
		}
		ds.close();
		finish();
	}

}
