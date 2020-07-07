package cn.edu.zucc.freshsurpermarket.comtrol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.freshsurpermarket.model.BeanCoupon;
import cn.edu.zucc.freshsurpermarket.model.BeanFullDiscount;
import cn.edu.zucc.freshsurpermarket.model.BeanFullDiscountGoods;
import cn.edu.zucc.freshsurpermarket.model.BeanLimitedTimePromotion;
import cn.edu.zucc.freshsurpermarket.util.BaseException;
import cn.edu.zucc.freshsurpermarket.util.BusinessException;
import cn.edu.zucc.freshsurpermarket.util.DBUtil;
import cn.edu.zucc.freshsurpermarket.util.DbException;

public class DiscountManager {
//	优惠券设置
	
//	插入
	public void insertcoupon(BeanCoupon bc) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select max(couponcode) from coupon";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int i = 1;
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into coupon(couponcode,content,apamount,deamount,begindate,enddate,isdelete) values(?,?,?,?,?,?,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i);
			pst.setString(2, bc.getContent());
			pst.setDouble(3,bc.getApamount());
			pst.setDouble(4, bc.getDeamount());
			pst.setTimestamp(5, new java.sql.Timestamp(bc.getBegindate().getTime()));
			pst.setTimestamp(6, new java.sql.Timestamp(bc.getEnddate().getTime()));
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
//	删除——软删除，意味着不再发布，但领到券的可以使用，但不再发布
	public  void deletecoupon(BeanCoupon bc) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select couponcode from coupon where couponcode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, bc.getCouponcode());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("优惠券不存在");
			rs.close();
			pst.close();
			sql="update coupon set isdelete=1 where couponcode=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, bc.getCouponcode());
			pst.executeUpdate();
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
//	优惠券不能修改，因为会被用户骂死  一旦发布不能修改
	
//	加载
	public List<BeanCoupon> loadcoupon() throws BaseException{
		Connection conn=null;
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select couponcode,content,apamount,deamount,begindate,enddate from coupon order by couponcode where isdelete=0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanCoupon bc=new BeanCoupon();
				bc.setCouponcode(rs.getInt(1));
				bc.setContent(rs.getString(2));
				bc.setApamount(rs.getDouble(3));
				bc.setDeamount(rs.getDouble(4));
				bc.setBegindate(rs.getTimestamp(5));
				bc.setEnddate(rs.getTimestamp(6));
				bc.setIsdelete(0);
				result.add(bc);
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
	
	
	
	
	
//	限时促销设置
	
	
//	插入
	public void insertlimitedtimepromotion(BeanLimitedTimePromotion bltp) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select max(promotioncode) from limitedtimepromotioninf";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int i = 1;
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into limitedtimepromotioninf(promotioncode,goodscode,promotionprice,promotionquantity,begindate,enddate) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i);
			pst.setInt(2, bltp.getGoodscode());
			pst.setDouble(3,bltp.getPromotionprice());
			pst.setInt(4, bltp.getPromotionquantity());
			pst.setTimestamp(5, new java.sql.Timestamp(bltp.getBegindate().getTime()));
			pst.setTimestamp(6, new java.sql.Timestamp(bltp.getEnddate().getTime()));
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
//	删除
	public  void deletelimitedtimepromotion(BeanLimitedTimePromotion bltp) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select promotioncode from limitedtimepromotioninf where promotioncode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, bltp.getPromotioncode());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("促销信息不存在");
			pst=conn.prepareStatement("delete from limitedtimepromotioninf where promotioncode=?");
			pst.setInt(1,bltp.getPromotioncode());
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
//	修改
	public  void modifylimitedtimepromotion(BeanLimitedTimePromotion bltp) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="update limitedtimepromotioninf set goodscode=?,promotionprice=?,promotionquantity=?,begindate=?,enddate=? where promotioncode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,bltp.getGoodscode());
			pst.setDouble(2, bltp.getPromotionprice());
			pst.setInt(3, bltp.getPromotionquantity());
			pst.setTimestamp(4, new java.sql.Timestamp(bltp.getBegindate().getTime()));
			pst.setTimestamp(5, new java.sql.Timestamp(bltp.getEnddate().getTime()));
			pst.setInt(6, bltp.getPromotioncode());
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
//	加载
	public List<BeanLimitedTimePromotion> loadlimitedtimepromotion() throws BaseException{
		Connection conn=null;
		List<BeanLimitedTimePromotion> result=new ArrayList<BeanLimitedTimePromotion>();
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select promotioncode,goodscode,promotionprice,promotionquantity,begindate,enddate from limitedtimepromotioninf order by promotioncode";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanLimitedTimePromotion bc=new BeanLimitedTimePromotion();
				bc.setPromotioncode(rs.getInt(1));
				bc.setGoodscode(rs.getInt(2));
				bc.setPromotionprice(rs.getDouble(3));
				bc.setPromotionquantity(rs.getInt(4));
				bc.setBegindate(rs.getTimestamp(5));
				bc.setEnddate(rs.getTimestamp(6));
				result.add(bc);
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
	
	
	
	
	
//	满折设置
	
	
//	插入
	public void insertfulldiscount(BeanFullDiscount bfd) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select max(fulldiscountcode) from fulldiscountinf";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int i = 1;
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into fulldiscountinf(fulldiscountcode,content,apgoodsquantity,discount,begindate,enddate) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i);
			pst.setString(2, bfd.getContent());
			pst.setDouble(3,bfd.getApgoodsquantity());
			pst.setDouble(4, bfd.getDiscount());
			pst.setTimestamp(5, new java.sql.Timestamp(bfd.getBegindate().getTime()));
			pst.setTimestamp(6, new java.sql.Timestamp(bfd.getEnddate().getTime()));
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
//	删除
	public  void deletefulldiscount (BeanFullDiscount bfd) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select fulldiscountcode from fulldiscountinf where fulldiscountcode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, bfd.getFulldiscountcode());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("满折信息不存在");
			rs.close();
			pst.close();
			sql="select count(*) from goodsdetails where fulldiscountcode = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,bfd.getFulldiscountcode());
			rs=pst.executeQuery();
			rs.next();
			int n=rs.getInt(1);
			pst.close();
			if(n>0) throw new BusinessException("已经存在"+n+"个商品订单使用该满折优惠，不能删除");
			pst=conn.prepareStatement("delete from fulldiscountinf where fulldiscountcode=?");
			pst.setInt(1,bfd.getFulldiscountcode());
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
//	修改
	public void modifyfulldiscount(BeanFullDiscount bfd) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="update fulldiscountinf set content=?,apgoodsquantity=?,discount=?,begindate=?,enddate=? where fulldiscountcode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, bfd.getContent());
			pst.setInt(2, bfd.getApgoodsquantity());
			pst.setDouble(3, bfd.getDiscount());
			pst.setTimestamp(4, new java.sql.Timestamp(bfd.getBegindate().getTime()));
			pst.setTimestamp(5, new java.sql.Timestamp(bfd.getEnddate().getTime()));
			pst.setInt(6, bfd.getFulldiscountcode());
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
//	加载
	public List<BeanFullDiscount> loadfulldiscount() throws BaseException{
		Connection conn=null;
		List<BeanFullDiscount> result=new ArrayList<BeanFullDiscount>();
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select fulldiscountcode,content,apgoodsquantity,discount,begindate,enddate from fulldiscountinf order by fulldiscountcode";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanFullDiscount bfd=new BeanFullDiscount();
				bfd.setFulldiscountcode(rs.getInt(1));
				bfd.setContent(rs.getString(2));
				bfd.setApgoodsquantity(rs.getInt(3));
				bfd.setDiscount(rs.getDouble(4));
				bfd.setBegindate(rs.getTimestamp(5));
				bfd.setEnddate(rs.getTimestamp(6));
				result.add(bfd);
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
	
	
	
	
	
	
//	满折商品关联
	
//	插入
	public void insertfulldiscountgoods(int promotioncode,int goodscode) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="insert into fulldiscountassociation(promotioncode,goodscode) values(?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,promotioncode);
			pst.setInt(2,goodscode);
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
//	删除
	public  void deletefulldiscountgoods (int promotioncode,int goodscode) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select * from fulldiscountassociation where promotioncode= ? and goodscode= ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,promotioncode);
			pst.setInt(2, goodscode);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("满折商品不存在");
			rs.close();
			pst.close();
			pst=conn.prepareStatement("delete from fulldiscountassociation where promotioncode= ? and goodscode= ?");
			pst.setInt(1,promotioncode);
			pst.setInt(2, goodscode);
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
//	修改
	public  void modifyfulldiscountgoods (BeanFullDiscountGoods bfdg,int promotioncode,int goodscode) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="update fulldiscountassociation set promotioncode=?,goodscode=? where promotioncode=? and goodscode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,promotioncode);
			pst.setInt(2, goodscode);
			pst.setInt(3,bfdg.getPromotioncode());
			pst.setInt(4,bfdg.getGoodscode());
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
//	加载
	public List<BeanFullDiscountGoods> loadfulldiscountgoods() throws BaseException{
		Connection conn=null;
		List<BeanFullDiscountGoods> result=new ArrayList<BeanFullDiscountGoods>();
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select promotioncode,goodscode from fulldiscountassociation group by promotioncode order by goodscode";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				BeanFullDiscountGoods bc=new BeanFullDiscountGoods();
				bc.setPromotioncode(rs.getInt(1));
				bc.setGoodscode(rs.getInt(2));
				result.add(bc);
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
