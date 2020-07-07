package cn.edu.zucc.freshsurpermarket.comtrol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.freshsurpermarket.model.BeanFreshCategory;
import cn.edu.zucc.freshsurpermarket.model.BeanGoods;
import cn.edu.zucc.freshsurpermarket.model.BeanMenu;
import cn.edu.zucc.freshsurpermarket.util.BaseException;
import cn.edu.zucc.freshsurpermarket.util.BusinessException;
import cn.edu.zucc.freshsurpermarket.util.DBUtil;
import cn.edu.zucc.freshsurpermarket.util.DbException;

public class GoodsManager {
//	商品初始录入
	public void goodsinitialization(BeanGoods bg) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select * from goodsinf where goodsname=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, bg.getGoodsname());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("商品已存在");
			sql="select max(goodscode) from goodsinf";
			int i = 1;
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into goodsinf(goodscode,categorycode,goodsname,price,vipprice,quantity,standards,ps) values(?,?,?,?,?,0,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setInt(2, bg.getCategorycode());
			pst.setString(3, bg.getGoodsname());
			pst.setDouble(4, bg.getPrice());
			pst.setDouble(5, bg.getVipprice());
			pst.setString(6, bg.getStandards());
			pst.setString(7, bg.getPs());
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
//	商品修改
	public  void modifyGoods(BeanGoods bg,String categoryname) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select categorycode from freshcategory where categoryname = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, categoryname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("类别不存在");
			sql="update goodsinf set categorycode=?,goodsname=? ,price=?,vipprice=?,standards=?,ps=? where menucode=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, rs.getInt(1));
			pst.setString(2, bg.getGoodsname());
			pst.setDouble(3, bg.getPrice());
			pst.setDouble(4, bg.getVipprice());
			pst.setString(5, bg.getStandards());
			pst.setString(6, bg.getPs());
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
//	商品删除（我觉得不能删除）--------如果不卖了，不要再进货就好了数量为0 没有存货 用户也不能买
	
	
//	商家商品加载管理
	public List<BeanGoods> loadGoodsByStaff(int categorycode) throws DbException {
		List<BeanGoods> result=new ArrayList<BeanGoods>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select goodsname,price,vipprice,standards,ps from goodsinf where categorycode = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, categorycode);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanGoods bg=new BeanGoods();
				bg.setGoodsname(rs.getString(1));
				bg.setPrice(rs.getDouble(2));
				bg.setVipprice(rs.getDouble(3));
				bg.setStandards(rs.getString(4));
				bg.setPs(rs.getString(5));
				result.add(bg);
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
	
//	用户商品加载购买
	public List<BeanGoods> loadGoodsByUser(int categorycode) throws DbException {
		List<BeanGoods> result=new ArrayList<BeanGoods>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select goodsname,price,vipprice,standards,ps from goodsinf where categorycode = ? and quantity > 0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, categorycode);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanGoods bg=new BeanGoods();
				bg.setGoodsname(rs.getString(1));
				bg.setPrice(rs.getDouble(2));
				bg.setVipprice(rs.getDouble(3));
				bg.setStandards(rs.getString(4));
				bg.setPs(rs.getString(5));
				result.add(bg);
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
