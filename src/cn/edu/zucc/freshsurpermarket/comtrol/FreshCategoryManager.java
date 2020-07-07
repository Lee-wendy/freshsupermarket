package cn.edu.zucc.freshsurpermarket.comtrol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.freshsurpermarket.model.BeanFreshCategory;
import cn.edu.zucc.freshsurpermarket.util.BaseException;
import cn.edu.zucc.freshsurpermarket.util.BusinessException;
import cn.edu.zucc.freshsurpermarket.util.DBUtil;
import cn.edu.zucc.freshsurpermarket.util.DbException;

public class FreshCategoryManager {
//	类别插入
	public  void createfreshcategory(BeanFreshCategory fc) throws BaseException{
		if(fc.getCategoryname()==null || "".equals(fc.getCategoryname())){
			throw new BusinessException("生鲜类别名称不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select * from freshcategory where categoryname=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, fc.getCategoryname());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("生鲜类别名已存在");
			rs.close();
			pst.close();
			sql="select max(categorycode) from freshcategory";
			int i = 1;
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into freshcategory(categorycode,categoryname,categorydescribe) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i);
			pst.setString(2, fc.getCategoryname());
			pst.setString(3,fc.getCategorydescribe());
			pst.execute();
			pst.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
//	类别删除
	public  void deletefreshcategory(BeanFreshCategory bfc) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select categorycode from freshcategory where categorycode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, bfc.getCategorycode());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("生鲜类别不存在");
			rs.close();
			pst.close();
			sql="select count(*) from goodsinf where categorycode = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, bfc.getCategorycode());
			rs=pst.executeQuery();
			rs.next();
			int n=rs.getInt(1);
			pst.close();
			if(n>0) throw new BusinessException("已经存在"+n+"个该类别的商品，不能删除");
			pst=conn.prepareStatement("delete from freshcategory where categorycode=?");
			pst.setInt(1, bfc.getCategorycode());
			pst.execute();
			pst.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
//	类别修改
	public  void modifyfreshcategory(BeanFreshCategory bfc) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="update  freshcategory set categoryname=?,categorydescribe=? where categorycode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, bfc.getCategoryname());
			pst.setString(2, bfc.getCategorydescribe());
			pst.setInt(3, bfc.getCategorycode());
			pst.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
//	类别加载
	public List<BeanFreshCategory> loadfreshcategory() throws DbException {
		List<BeanFreshCategory> result=new ArrayList<BeanFreshCategory>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select categorycode,categoryname,categorydescribe from freshcategory ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanFreshCategory bfc=new BeanFreshCategory();
				bfc.setCategorycode(rs.getInt(1));
				bfc.setCategoryname(rs.getString(2));
				bfc.setCategorydescribe(rs.getString(3));
				result.add(bfc);
			}
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
}
