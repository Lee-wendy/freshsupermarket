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
 * Servlet implementation class addcategory
 */
@WebServlet("/admin/addcategory")
public class addcategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addcategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/admin/addcategory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String categoryname = request.getParameter("categoryname");
		String categorydescribe =request.getParameter("categorydescribe");
		try {
			FreshCategoryManager fcm = new FreshCategoryManager();
			BeanFreshCategory fc = new BeanFreshCategory();
			fc.setCategoryname(categoryname);
			fc.setCategorydescribe(categorydescribe);
			int result = fcm.createfreshcategory(fc);
			if(result==1) {
//				告知用户添加成功
				request.setAttribute("httpUrl", "/admin/categorylist");
				request.setAttribute("title", "添加成功");
				request.setAttribute("info","生鲜类别添加成功。即将返回生鲜类别列表页面");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
			else {
//				告知用户添加失败
				request.setAttribute("httpUrl", "/admin/categorylist");
				request.setAttribute("title", "添加失败");
				request.setAttribute("info","生鲜类别名已存在。即将返回生鲜类别列表页面");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}

		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
