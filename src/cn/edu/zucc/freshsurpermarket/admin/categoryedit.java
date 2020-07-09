package cn.edu.zucc.freshsurpermarket.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.freshsurpermarket.comtrol.FreshCategoryManager;
import cn.edu.zucc.freshsurpermarket.model.BeanFreshCategory;
import cn.edu.zucc.freshsurpermarket.util.BaseException;

/**
 * Servlet implementation class categoryedit
 */
@WebServlet("/admin/categoryedit")
public class categoryedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public categoryedit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		FreshCategoryManager fcm = new FreshCategoryManager();
		BeanFreshCategory bfc = new BeanFreshCategory();
		int categorycode = Integer.valueOf(request.getParameter("categorycode"));
		String categoryname = request.getParameter("categoryname");
		String categorydescribe =request.getParameter("categorydescribe");
		bfc.setCategorycode(categorycode);
		bfc.setCategoryname(categoryname);
		bfc.setCategorydescribe(categorydescribe);
		System.out.print(categorycode+" "+categoryname+" "+categorydescribe);
//		try {
//			fcm.modifyfreshcategory(bfc);
//			request.setAttribute("httpUrl", "/admin/categorylist");
//			request.setAttribute("title", "修改成功");
//			request.setAttribute("info","即将跳转至生鲜类别页面。");
//			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
//		} catch (BaseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
