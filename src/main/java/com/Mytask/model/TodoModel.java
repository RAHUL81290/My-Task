package com.Mytask.model;

public class TodoModel {
	private int tid;
	private int cid;
	private int uid;
	private String title;
	private String content;
	private boolean important;
	private boolean completed;
	public TodoModel(int tid, int cid, int uid, String title, String content, boolean important, boolean completed) {
		super();
		this.tid = tid;
		this.cid = cid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.important = important;
		this.completed = completed;
	}
	public TodoModel(int cid, int uid, String title, String content, boolean important) {
		super();
		this.cid = cid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.important = important;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isImportant() {
		return important;
	}
	public void setImportant(boolean important) {
		this.important = important;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
	
}
