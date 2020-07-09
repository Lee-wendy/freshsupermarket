package cn.edu.zucc.freshsurpermarket.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.freshsurpermarket.comtrol.FreshCategoryManager;
import cn.edu.zucc.freshsurpermarket.comtrol.UserManager;
import cn.edu.zucc.freshsurpermarket.model.BeanFreshCategory;
import cn.edu.zucc.freshsurpermarket.util.BaseException;

/**
 * Servlet implementation class categorydelete
 */
@WebServlet("/admin/categorydelete")
public class categorydelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public categorydelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int categorycode =Integer.parseInt(request.getParameter("categorycode"));
		FreshCategoryManager fcm = new FreshCategoryManager();
		try {
			fcm.deletefreshcategory(categorycode);
			request.getRequestDispatcher("/admin/categorylist").forward(request, response);
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
