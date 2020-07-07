package cn.edu.zucc.freshsurpermarket.comtrol;

import java.sql.Connection;
import java.sql.SQLException;

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
