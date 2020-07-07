package cn.edu.zucc.freshsurpermarket.comtrol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import cn.edu.zucc.freshsurpermarket.model.BeanSystemUser;
import cn.edu.zucc.freshsurpermarket.model.BeanFreshCategory;
import cn.edu.zucc.freshsurpermarket.model.BeanGoods;
import cn.edu.zucc.freshsurpermarket.model.BeanMenu;
import cn.edu.zucc.freshsurpermarket.util.BaseException;
import cn.edu.zucc.freshsurpermarket.util.BusinessException;
import cn.edu.zucc.freshsurpermarket.util.DBUtil;
import cn.edu.zucc.freshsurpermarket.util.DbException;
import cn.edu.zucc.freshsurpermarket.util.DBUtil2;

public class SystemUserManager {
	public static BeanSystemUser currentUser=null;
//	ע��Ա��
	public void reg(String staffname, String password1,String password2) throws BaseException {
		// TODO Auto-generated method stub
		if(password1==null || "".equals(password1) || password1.length()<6){
			throw new BusinessException("���������ڵ���6λ");
		}
		if(!(password1.equals(password2))){
			throw new BusinessException("�����������벻һ��");
		}
	
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select max(staffcode) from administratorsinf order by staffcode";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			int i = 1;
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				i=1;
			}
			else {
				i+=rs.getInt(1);
			}
			sql="insert into administratorsinf(staffcode,name,password,offjob) values(?,?,?,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setString(2, staffname);
			pst.setString(3, password1);
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
	
//	Ա����¼
	public BeanSystemUser login(int staffcode, String password) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select staffcode,name,password,offjob from administratorsinf where staffcode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,staffcode);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("Ա���˺Ų�����");
			if(!rs.getString(3).equals(password)) throw new BusinessException("�������");
			if(rs.getInt(4)==1) throw new BusinessException("Ա������ְ");
			BeanSystemUser su=new BeanSystemUser();
			su.setStaffcode(rs.getInt(1));
			su.setName(rs.getString(2));
			su.setPassword(rs.getString(3));
			su.setOffjob(rs.getInt(4));
			rs.close();
			pst.close();
			conn.commit();
			return su;
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
	
//	Ա���޸�����
	public void changePwd(BeanSystemUser SystemUser, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null) throw new BusinessException("ԭʼ���벻��Ϊ��");
		if(newPwd.length()<6) throw new BusinessException("���������ڵ���6λ");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			if(!oldPwd.equals(SystemUser.getPassword())) throw new BusinessException("ԭʼ�������");
			if(!newPwd.equals(newPwd2)) throw new BusinessException("�����������벻һ��");
			String sql="update administratorsinf set password=? where staffcode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, SystemUser.getStaffcode());
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
//	����Ա��ְ
	public void deleteSystemUser(int staffcode)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select staffcode,offjob from administratorsinf where staffcode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,staffcode);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("Ա���˺Ų�����");
			if(rs.getInt(2)==1) throw new BusinessException("Ա������ְ");
			rs.close();
			pst.close();
			sql="update administratorsinf set offjob=1 where staffcode=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, staffcode);
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
//	Ա�����ع���
	
	
//	�µ����ɹ���    quantity����������Ʒ��������
	public void purchase(int staffcode,int quantity) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
//			conn.setAutoCommit(false);
			String sql="select max(purchasecode) from goodspurchase order by purchasecode";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			int x = 1;
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				x=1;
			}
			else {
				x+=rs.getInt(1);
			}
			sql="insert into goodspurchase(purchasecode,staffcode,state,quantity,purchasedate) values(?,?,'�µ�',?,now())";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, x);
			pst.setInt(2, staffcode);
			pst.setInt(3, quantity);
			pst.execute();
			pst.close();
			for(int i=1;i<=quantity;i++) {
//				Scanner input = new Scanner(System.in); 
//				System.out.println("��������Ʒ���ƣ�"); 
//			    String goodsname = input.nextLine();
//				System.out.println("��������Ʒ������"); 
//			    int goodsquantity = input.nextInt();
//				purchasedetails(x,goodsname,goodsquantity);
			}
//			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
//					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
//	���ɲɹ������
	public void purchasedetails(int purchasecode,String goodsname,int goodsquantity) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select goodscode from goodsinf where goodsname = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,goodsname);
			java.sql.ResultSet rs=pst.executeQuery();
			int x;
			if(!rs.next()) {
				sql="select max(goodscode) from goodsinf order by goodscode";
				pst=conn.prepareStatement(sql);
				x = 1;
				rs=pst.executeQuery();
				if(!rs.next()) {
					x=1;
				}
				else {
					x+=rs.getInt(1);
				}
				sql="insert into goodsinf(goodscode,goodsname) values(?,?)";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,x);
				pst.setString(2, goodsname);
				pst.execute();
			}
			else {
				x=rs.getInt(1);
			}
			sql="insert into purchasedetails(goodscode,purchasecode,quantity) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, x);
			pst.setInt(2, purchasecode);
			pst.setInt(3, goodsquantity);
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
	
//	��;
	public void onway(int purchasecode) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select state from goodspurchase where purchasecode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, purchasecode);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�ɹ���������");
			if(rs.getString(1).equals("��;")) throw new BusinessException("�ɹ���Ʒ��;");
			if(rs.getString(1).equals("���")) throw new BusinessException("�ɹ���Ʒ�����");
			sql="update goodspurchase set state = '��;' where purchasecode=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, purchasecode);
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
//	���
	public void warehousing(int purchasecode) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			conn=DBUtil2.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql="select state from goodspurchase where purchasecode=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, purchasecode);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�ɹ���������");
			if(rs.getString(1).equals("�µ�")) throw new BusinessException("�ɹ���Ʒδ����");
			if(rs.getString(1).equals("���")) throw new BusinessException("�ɹ���Ʒ�����");
			sql="update goodspurchase set state = '���' where purchasecode=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, purchasecode);
			pst.execute();
			
			sql="select goodscode,quantity from purchasedetails where purchasecode = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, purchasecode);
			rs=pst.executeQuery();
			while(rs.next()) {
				sql="update goodsinf set quantity = ? where goodscode = ?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, rs.getInt(2));
				pst.setInt(2, rs.getInt(1));
				pst.execute();
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
	}
//	��Ʒ�ɹ�����
//	�ɹ���������
	
	public static void main(String[] args){
		SystemUserManager user=new SystemUserManager(); 
		try {
			user.warehousing(5);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
