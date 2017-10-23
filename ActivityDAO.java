package com.iii.eeit9703.activity.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActivityDAO implements ActivityDAO_interface {

	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext(); //初始化
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB"); //連結資料庫
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//新增活動
		private static final String INSERT_STMT =
				"INSERT INTO activity (act_name,act_groups,act_current,BDate,EDate,activity_state,collectID) VALUES (?,?,?,?,?,?,?) ";
		//修改活動
		private static final String UPDATE_STMT =
				"UPDATE activity set act_name=?, act_groups=?, act_current=?, BDate=?, EDate=?, activity_state=?, collectID=?  where actID = ? ";
		//刪除活動
		private static final String DELETE_STMT =
				"DELETE FROM activity actID = ?";
		//查詢活動
		private static final String GET_ALL_STMT =
			      "SELECT act_name,act_groups,act_current,BDate,EDate,activity_state,collectID FROM activity order by actID";
		private static final String GET_ONE_STMT =
			      "SELECT act_name,act_groups,act_current,BDate,EDate,activity_state,collectID FROM activity where actID = ?";
	
	//新增活動
	@Override
	public void insert(ActivityVO activityVO) {
        
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, activityVO.getAct_name());    //活動名稱
			pstmt.setInt(2, activityVO.getAct_groups());    //成團人數
			pstmt.setInt(3, activityVO.getAct_current());  //當前人數
			pstmt.setDate(4, activityVO.getBDate());      //開始日期
			pstmt.setDate(5, activityVO.getEDate());     //結束日期
			pstmt.setInt(6, activityVO.getActivity_state());  //活動上下架
			pstmt.setInt(7, activityVO.getCollectID());
		
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫新增錯誤" + e.getMessage());
			
		}finally{
			if(pstmt != null){	
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace(System.err);
				}
			}
		}
		if(con !=null){
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace(System.err);
			}
		}
		
	}

	
	//修改
	@Override
	public void update(ActivityVO activityVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, activityVO.getAct_name());    //活動名稱
			pstmt.setInt(2, activityVO.getAct_groups());    //成團人數
			pstmt.setInt(3, activityVO.getAct_current());  //當前人數
			pstmt.setDate(4, activityVO.getBDate());      //開始日期
			pstmt.setDate(5, activityVO.getEDate());     //結束日期
			pstmt.setInt(6, activityVO.getActivity_state());  //活動上下架
			pstmt.setInt(7, activityVO.getCollectID());     //收藏表

			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫更新錯誤" + e.getMessage());
			
		}finally{
			if(pstmt != null){	
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace(System.err);
				}
			}
		}
		if(con !=null){
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace(System.err);
			}
		}
		
	}

	
	//刪除
	@Override
	public void delete(Integer actID) {
		
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, actID);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("資料庫刪除錯誤"
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	
	//
	@Override
	public ActivityVO findByPrimaryKey(Integer actID) {

		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, actID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				
				activityVO = new ActivityVO();
				
				activityVO.setAct_name(rs.getString("act_name"));                //活動名稱
				activityVO.setAct_groups(rs.getInt("act_groups"));              //成團人數
				activityVO.setAct_current(rs.getInt("act_current"));           //當前人數
				activityVO.setBDate(rs.getDate("BDate"));                     //開始日期
				activityVO.setEDate(rs.getDate("EDate"));                    //結束日期
				activityVO.setActivity_state(rs.getInt("activity_state"));  //活動型態
				
				
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return activityVO;
	}

	@Override
	public List<ActivityVO> getAll() {
		
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;

		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
                activityVO = new ActivityVO();
				
                activityVO.setAct_name(rs.getString("act_name"));                //活動名稱
				activityVO.setAct_groups(rs.getInt("act_groups"));              //成團人數
				activityVO.setAct_current(rs.getInt("act_current"));           //當前人數
				activityVO.setBDate(rs.getDate("BDate"));                     //開始日期
				activityVO.setEDate(rs.getDate("EDate"));                    //結束日期
				activityVO.setActivity_state(rs.getInt("activity_state"));  //活動型態
				
				list.add(activityVO);
				
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}




}
