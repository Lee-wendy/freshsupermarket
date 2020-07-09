package cn.edu.zucc.freshsurpermarket.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.freshsurpermarket.comtrol.UserManager;
import cn.edu.zucc.freshsurpermarket.model.BeanUser;
import cn.edu.zucc.freshsurpermarket.util.BaseException;

/**
 * Servlet implementation class userlist
 */
@WebServlet("/admin/userlist")
public class userlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userlist() {
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
			UserManager um = new UserManager();
			List<BeanUser> userlist = um.loaduserinf(num);
			request.setAttribute("userlist", userlist);
			request.setAttribute("page",page);
//			获取总用户数
			int allnum =um.userinfnum();
			int allpage =(int) Math.ceil((double)allnum /5);
			request.setAttribute("allpage",allpage);
			request.getRequestDispatcher("/admin/userlist.jsp").forward(request, response);
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
