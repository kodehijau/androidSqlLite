package com.knoqdown.android.trial9;

import java.util.List;

import com.knoqdown.android.trial9.db.Todo;
import com.knoqdown.android.trial9.db.TodoDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {
	TodoDataSource ds;
	ListView lvTodo;
	List<Todo> todos;
	MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btnTambahTodo).setOnClickListener(this);
		lvTodo = (ListView) findViewById(R.id.lvTodo);
		registerForContextMenu(lvTodo);
		lvTodo.setOnItemClickListener(this);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		ds = new TodoDataSource(this);
		ds.open();
		todos = ds.getAllTodo();
		ds.close();
		adapter = new MyAdapter();
		lvTodo.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, FormActivity.class);
		startActivity(intent);
	}
	

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return todos.size();
		}

		@Override
		public Todo getItem(int position) {
			// TODO Auto-generated method stub
			return todos.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return todos.get(position).getIntId();
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(view == null) {
				view = getLayoutInflater().inflate(R.layout.list_item, null);
			}
			
			TextView tv = (TextView) view.findViewById(R.id.tvTask);
			ImageView iv = (ImageView) view.findViewById(R.id.ivIsDone);
			Todo todo = todos.get(position);
			tv.setText(todo.toString());
			
			if(todo.getIntIsDone() == 1) {
				iv.setImageResource(R.drawable.tick);
			} else {
				iv.setImageResource(R.drawable.cross);
			}
			return view;
		}
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Todo todo = todos.get(position);
		ds.open();
		ds.cekTodo(todo.getIntId());
		ds.close();
		onResume();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		getMenuInflater().inflate(R.menu.ctx_task, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		Todo todo = todos.get(info.position);
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.ctx_delete:
			ds = new TodoDataSource(this);
			ds.open();
			ds.delTodo(todo.getIntId());
			ds.close();
			onResume();
			return true;

		case R.id.ctx_edit:
			Intent intent = new Intent(this, FormActivity.class);
			intent.putExtra("intTodoId", todo.getIntId());
			startActivity(intent);
			return true;
			
		default:
			return super.onContextItemSelected(item);
		}
		
	}
}
