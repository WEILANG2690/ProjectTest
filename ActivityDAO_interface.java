package com.iii.eeit9703.activity.model;

import java.util.*;

public interface ActivityDAO_interface {
	
	//新增活動
	public void insert (ActivityVO activityVO);
    //修改活動
	public void update (ActivityVO activityVO);
	//刪除
	public void delete (Integer actID);
	//主鍵
	public ActivityVO findByPrimaryKey(Integer actID);
	//查詢
	public List<ActivityVO>getAll();
	
	//新增行程(編輯)
	//public void insertAch (AcheduleVO acheduleVO);
	
	//修改行程(編輯)
	//public void updateAch (AcheduleVO acheduleVO);
	
	
}
