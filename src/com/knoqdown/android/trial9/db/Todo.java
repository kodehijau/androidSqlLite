package com.knoqdown.android.trial9.db;

import java.security.Timestamp;

public class Todo {
	int intId,intIsDone,intTsDone;
	String strTask;
	/**
	 * @param intId
	 * @param intIsDone
	 * @param intTsDone
	 * @param strTask
	 */
	public Todo(int intId, String strTask, int intIsDone, int intTsDone) {
		this.intId = intId;
		this.intIsDone = intIsDone;
		this.intTsDone = intTsDone;
		this.strTask = strTask;
	}
	/**
	 * @param strTask
	 */
	public Todo(String strTask) {
		this.strTask = strTask;
	}
	/**
	 * @return the intId
	 */
	public int getIntId() {
		return intId;
	}
	/**
	 * @param intId the intId to set
	 */
	public void setIntId(int intId) {
		this.intId = intId;
	}
	/**
	 * @return the intIsDone
	 */
	public int getIntIsDone() {
		return intIsDone;
	}
	/**
	 * @param intIsDone the intIsDone to set
	 */
	public void setIntIsDone(int intIsDone) {
		this.intIsDone = intIsDone;
	}
	/**
	 * @return the intTsDone
	 */
	public int getIntTsDone() {
		return intTsDone;
	}
	/**
	 * @param intTsDone the intTsDone to set
	 */
	public void setIntTsDone(int intTsDone) {
		this.intTsDone = intTsDone;
	}
	/**
	 * @return the strTask
	 */
	public String getStrTask() {
		return strTask;
	}
	/**
	 * @param strTask the strTask to set
	 */
	public void setStrTask(String strTask) {
		this.strTask = strTask;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return strTask;
	}
	
	
}
