package com.iii.eeit9703.activity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActivityJDBCDAO implements ActivityDAO_interface {
	
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=CMDB";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	//新增活動
	private static final String INSERT_STMT =
			"INSERT INTO activity (act_name,act_groups,act_current,BDate,EDate,activity_state,collectID) VALUES (?,?,?,?,?,?,?)";
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

	
	//新增行程
	@Override
	public void insert(ActivityVO activityVO) {
        
		
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, activityVO.getAct_name());    //活動名稱
			pstmt.setInt(2, activityVO.getAct_groups());    //成團人數
			pstmt.setInt(3, activityVO.getAct_current());  //當前人數
			pstmt.setDate(4, activityVO.getBDate());      //開始日期
			pstmt.setDate(5, activityVO.getEDate());     //結束日期
			pstmt.setInt(6, activityVO.getActivity_state()); //活動上下架
			pstmt.setInt(7, activityVO.getCollectID());     //收藏表
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	
	//修改活動
	@Override
	public void update(ActivityVO activityVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, activityVO.getAct_name());    //活動名稱
			pstmt.setInt(2, activityVO.getAct_groups());    //成團人數
			pstmt.setInt(3, activityVO.getAct_current());  //當前人數
			pstmt.setDate(4, activityVO.getBDate());      //開始日期
			pstmt.setDate(5, activityVO.getEDate());     //結束日期
			pstmt.setInt(6, activityVO.getActivity_state());  //活動上下架
			pstmt.setInt(7, activityVO.getCollectID());     //收藏表
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	
	//刪除活動
	@Override
	public void delete(Integer actID) {
		
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, actID);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	
	//查詢一筆活動
	@Override
	public ActivityVO findByPrimaryKey(Integer actID) {

		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	
	//查詢全部活動
	@Override
	public List<ActivityVO> getAll() {
		
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;

		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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


	public static void main(String[] args) {

		ActivityJDBCDAO dao = new ActivityJDBCDAO();
		
		//新增活動  
		ActivityVO actVO1 = new ActivityVO();
		
		actVO1.setAct_name("九份一日遊");
		actVO1.setAct_groups(15);
		actVO1.setAct_current(5);
		actVO1.setBDate(java.sql.Date.valueOf("2017-10-10"));
		actVO1.setEDate(java.sql.Date.valueOf("2017-10-11"));
		actVO1.setActivity_state(0);
		actVO1.setCollectID(0);
		dao.insert(actVO1);
		
		//修改
//        ActivityVO actVO2 = new ActivityVO();
//		
//		actVO2.setActID(1);
//		actVO2.setName("八份");
//		actVO2.setCounty("中部");
//		actVO2.setDay(2);
//		actVO2.setPeriod(java.sql.Date.valueOf("2017-10-10"));
//		actVO2.setDesc("無");
//		dao.insert1(actVO2);
		
		//刪除
		//dao.delete(1);
		
		// 查詢
		ActivityVO actVO3 = dao.findByPrimaryKey(1);
		System.out.print(actVO3.getAct_name() + ",");
		System.out.print(actVO3.getAct_groups() + ",");
		System.out.print(actVO3.getAct_current() + ",");
		System.out.print(actVO3.getBDate() + ",");
		System.out.print(actVO3.getEDate() + ",");
		System.out.print(actVO3.getActivity_state() + ",");
		System.out.println("---------------------");
		
		// 查詢
		List<ActivityVO> list = dao.getAll();
		for (ActivityVO aAct : list) {
			System.out.print(aAct.getAct_name() + ",");
			System.out.print(aAct.getAct_groups() + ",");
			System.out.print(aAct.getAct_current() + ",");
			System.out.print(aAct.getEDate() + ",");
			System.out.print(aAct.getEDate() + ",");
			System.out.print(aAct.getActivity_state() + ",");
			System.out.println();
		}
		

	}


	

}
