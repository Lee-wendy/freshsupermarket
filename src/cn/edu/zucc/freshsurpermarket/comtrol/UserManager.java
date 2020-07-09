package cn.edu.zucc.freshsurpermarket.comtrol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.freshsurpermarket.model.BeanFullDiscount;
import cn.edu.zucc.freshsurpermarket.model.BeanShippingAddress;
import cn.edu.zucc.freshsurpermarket.model.BeanUser;
import cn.edu.zucc.freshsurpermarket.util.BaseException;
import cn.edu.zucc.freshsurpermarket.util.BusinessException;
import cn.edu.zucc.freshsurpermarket.util.DBUtil;
import cn.edu.zucc.freshsurpermarket.util.DBUtil2;
import cn.edu.zucc.freshsurpermarket.util.DbException;

public class UserManager {
	public static BeanUser currentUser=null;
//	�û�ע��
	public void reg(BeanUser user,String ackpassword) throws BaseException {
		// TODO Auto-generated method stub
		if(user.getPassword()==null || "".equals(user.getPassword()) || user.getPassword().length()<6){
			throw new BusinessException("���������ڵ���6λ");
		}
		if(!(ackpassword.equals(user.getPassword()))){
			throw new BusinessException("�����������벻һ��");
		}
	
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select max(usercode) from userinf order by usercode";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			int i = 1;
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into userinf(usercode,name,sex,password,phone,email,city,registerdate,isvip,logoff) values(?,?,?,?,?,?,?,now(),0,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setString(2, user.getName());
			pst.setString(3, user.getSex());
			pst.setString(4, user.getPassword());
			pst.setString(5, user.getPhone());
			pst.setString(6, user.getEmail());
			pst.setString(7, user.getCity());
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
	
//	�û���¼
	public BeanUser login(int usercode, String password) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select name,password,phone,isvip,logoff from userinf where usercode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,usercode);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�û�������");
			if(!rs.getString(2).equals(password)) throw new BusinessException("�������");
			if(rs.getInt(5)==1) throw new BusinessException("�û�����ע��");
			BeanUser u=new BeanUser();
			u.setUsercode(usercode);
			u.setName(rs.getString(1));
			u.setPassword(rs.getString(2));
			u.setPhone(rs.getString(3));
			u.setIsvip(rs.getInt(4));
			rs.close();
			pst.close();
			conn.commit();
			return u;
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
	
//	�û��޸�����
	public void changePwd(BeanUser User, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null) throw new BusinessException("ԭʼ���벻��Ϊ��");
		if(newPwd.length()<6) throw new BusinessException("���������ڵ���6λ");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			if(!oldPwd.equals(User.getPassword())) throw new BusinessException("ԭʼ�������");
			if(!newPwd.equals(newPwd2)) throw new BusinessException("�����������벻һ��");
			String sql="update userinf set password=? where usercode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, User.getUsercode());
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
	
//	�û�ע��
	public void deleteUser(int usercode)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select usercode,logoff from userinf where usercode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,usercode);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�û��˺Ų�����");
			if(rs.getInt(2)==1) throw new BusinessException("�û���ע��");
			rs.close();
			pst.close();
			sql="update userinf set logoff=1 where usercode=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, usercode);
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
//	�û����ع���
	public List<BeanUser> loaduserinf(int num) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		List<BeanUser> result=new ArrayList<BeanUser>();
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select usercode,name,sex,password,phone,email,city,registerdate,isvip,vipdeadline from userinf where logoff=0 order by usercode limit ?,5";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, num);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanUser bu = new BeanUser();
				bu.setUsercode(rs.getInt(1));
				bu.setName(rs.getString(2));
				bu.setSex(rs.getString(3));
				bu.setPassword(rs.getString(4));
				bu.setPhone(rs.getString(5));
				bu.setEmail(rs.getString(6));
				bu.setCity(rs.getString(7));
				bu.setRegisterdate(rs.getTimestamp(8));
				bu.setIsvip(rs.getInt(9));
				bu.setVipdeadline(rs.getTimestamp(10));
				result.add(bu);
			}
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
		return result;
	}
//	���û���
	public int userinfnum() throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		int allnum=0;
		List<BeanUser> result=new ArrayList<BeanUser>();
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select * from userinf where logoff=0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				allnum=allnum+1;
			}
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
		return allnum;
	}
	
//	�����Ա
	public void setvip(BeanUser User,int month) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="";
			if(User.getIsvip()==0) {
				sql="update userinf set isvip= 1,vipdeadline = date_add(NOW(),INTERVAL ? MONTH) where usercode=?";
			}
			else {
				sql="update userinf set vipdeadline = date_add(vipdeadline,INTERVAL ? MONTH) where usercode=?";
			}
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,month);
			pst.setInt(2, User.getUsercode());
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
	
//	������͵�ַ
	public void setshippingaddress(BeanShippingAddress bsa,int usercode) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select max(addresscode) from shippingadress";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int i = 1;
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into shippingadress(addresscode,usercode,province,city,area,address,contacts,phone) values(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i);
			pst.setInt(2, usercode);
			pst.setString(3, bsa.getProvince());
			pst.setString(4,bsa.getCity());
			pst.setString(5, bsa.getArea());
			pst.setString(6,bsa.getAddress());
			pst.setString(7,bsa.getContacts());
			pst.setString(8,bsa.getPhone());
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

//	ɾ�����͵�ַ
	public void deleteshippingaddress(int shippingadress) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select addresscode from shippingadress where addresscode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,shippingadress);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("���͵�ַ������");
			rs.close();
			pst.close();
			pst=conn.prepareStatement("delete from shippingadress where addresscode=?");
			pst.setInt(1,shippingadress);
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
	
//	�޸����͵�ַ
	public void modifyshippingaddress(BeanShippingAddress bsa,int usercode) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="update shippingadress set province=?,city=?,area=?,address=?,contacts=?,phone=? where addresscode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, bsa.getProvince());
			pst.setString(2,bsa.getCity());
			pst.setString(3, bsa.getArea());
			pst.setString(4,bsa.getAddress());
			pst.setString(5,bsa.getContacts());
			pst.setString(6,bsa.getPhone());
			pst.setInt(7, bsa.getAddresscode());
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
	
//	�������͵�ַ
	public List<BeanShippingAddress> loadshippingaddress() throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		List<BeanShippingAddress> result=new ArrayList<BeanShippingAddress>();
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select addresscode,usercode,province,city,area,address,contacts,phone from shippingadress order by addresscode";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanShippingAddress bsa = new BeanShippingAddress();
				bsa.setAddresscode(rs.getInt(1));
				bsa.setUsercode(rs.getInt(2));
				bsa.setProvince(rs.getString(3));
				bsa.setCity(rs.getString(4));
				bsa.setArea(rs.getString(5));
				bsa.setAddress(rs.getString(6));
				bsa.setContacts(rs.getString(7));
				bsa.setPhone(rs.getString(8));
				result.add(bsa);
			}
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
		return result;
	}
	
	public static void main(String[] args){
		UserManager user=new UserManager(); 
		try {
			BeanUser u = new BeanUser(); 
			u.setUsercode(1);
			u.setName("����");
			u.setIsvip(1);
			u.setPassword("1234567");
			u.setCity("����");
			user.setvip(u,1);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
