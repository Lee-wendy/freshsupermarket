package cn.edu.zucc.freshsurpermarket.comtrol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.freshsurpermarket.model.BeanMenu;
import cn.edu.zucc.freshsurpermarket.util.BaseException;
import cn.edu.zucc.freshsurpermarket.util.BusinessException;
import cn.edu.zucc.freshsurpermarket.util.DBUtil;
import cn.edu.zucc.freshsurpermarket.util.DbException;

public class GoodsMenuManager {
//	菜谱插入
	public  void creatememu(BeanMenu bm) throws BaseException{
		if(bm.getMenuname()==null || "".equals(bm.getMenuname())){
			throw new BusinessException("菜谱名称不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select * from menuinf where menuname = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, bm.getMenuname());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("菜谱已存在");
			rs.close();
			pst.close();
			sql="select max(menucode) from menuinf";
			int i = 1;
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into menuinf(menucode,menuname,menumaterials,step) values(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i);
			pst.setString(2,bm.getMenuname());
			pst.setString(3,bm.getMenumaterials());
			pst.setString(4, bm.getStep());
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
	
//	菜谱删除
	public  void deletememu(BeanMenu bm) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select menucode from menuinf where menucode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, bm.getMenucode());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("菜谱不存在");
			rs.close();
			pst.close();
//			sql="select count(*) from goodsmenurecommend where menucode = ?";
//			pst=conn.prepareStatement(sql);
//			pst.setInt(1, bm.getMenucode());
//			rs=pst.executeQuery();
//			rs.next();
//			int n=rs.getInt(1);
//			pst.close();
//			if(n>0) throw new BusinessException("已经存在"+n+"个该菜谱商品推荐，不能删除");
			pst=conn.prepareStatement("delete from menuinf where menucode=?");
			pst.setInt(1, bm.getMenucode());
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
	
	
//	菜谱修改
	public  void modifymemu(BeanMenu bm) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
//			如何执行，单独名称修改，用料修改，步骤修改，更改图片？
			String sql="update menuinf set menuname=?,menumaterials=?,step=? where menucode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,bm.getMenuname());
			pst.setString(2, bm.getMenumaterials());
			pst.setInt(3, bm.getMenucode());
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
	
//	菜谱加载
	public List<BeanMenu> loadMenu() throws DbException {
		List<BeanMenu> result=new ArrayList<BeanMenu>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select menucode,menuname,menumaterials,step from menuinf ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanMenu bm=new BeanMenu();
				bm.setMenucode(rs.getInt(1));
				bm.setMenuname(rs.getString(2));
				bm.setMenumaterials(rs.getString(3));
				bm.setStep(rs.getString(4));
				result.add(bm);
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
	
//	插入商品菜谱推荐
//	删除商品菜谱推荐
//	修改商品菜谱推荐
//	加载商品菜谱推荐
}
