package cn.edu.zucc.freshsurpermarket.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.freshsurpermarket.comtrol.FreshCategoryManager;
import cn.edu.zucc.freshsurpermarket.model.BeanFreshCategory;
import cn.edu.zucc.freshsurpermarket.util.BaseException;

/**
 * Servlet implementation class categorylist
 */
@WebServlet("/admin/categorylist")
public class categorylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public categorylist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String page = request.getParameter("page");
			if(page==null) {
				page="1";
			}
			int num = (Integer.parseInt(page)-1)*5;
			FreshCategoryManager fcm = new FreshCategoryManager();
			List<BeanFreshCategory> categorylist=new ArrayList<BeanFreshCategory>();
			int allpage = 0;
			int allnum = 0;
//			获取模糊查找生鲜类别
			String likecategory = request.getParameter("likecategory");
			if(likecategory==null) {
//				没有模糊查找任何生鲜类别
				categorylist = fcm.loadfreshcategory(num);
//				获取类别数
				allnum = fcm.freshcategorynum();
				allpage =(int) Math.ceil((double)allnum /5);
			}
			else {
//				模糊查询
				categorylist = fcm.searchfreshcategory(likecategory,num);
//				获取类别数
				allnum = fcm.freshcategorysearchnum(likecategory);
				allpage =(int) Math.ceil((double)allnum /5);
			}
			request.setAttribute("categorylist", categorylist);
			request.setAttribute("page",page);
			request.setAttribute("allnum",allnum);
			request.setAttribute("allpage",allpage);
			request.getRequestDispatcher("/admin/categorylist.jsp").forward(request, response);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
