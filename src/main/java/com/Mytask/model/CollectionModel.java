package com.Mytask.model;

public class CollectionModel {
	private int cid;
	private int uid;
	private String title;
	public CollectionModel(int cid, int uid, String title) {
		super();
		this.cid = cid;
		this.uid = uid;
		this.title = title;
	}
	public CollectionModel(int uid, String title) {
		super();
		this.uid = uid;
		this.title = title;
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
	
}
