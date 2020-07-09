package cn.edu.zucc.freshsurpermarket.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.freshsurpermarket.comtrol.SystemUserManager;
import cn.edu.zucc.freshsurpermarket.comtrol.UserManager;
import cn.edu.zucc.freshsurpermarket.util.BaseException;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			//设置请求的字符集
		request.setCharacterEncoding("utf-8");
			//设置相应的文本类型
		response.setContentType("text/html;charset=utf-8");
   		SystemUserManager sm = new SystemUserManager();
   		UserManager m = new UserManager();
   		String staffname=request.getParameter("staffname");
   		String password=request.getParameter("password");
   		try {
			int result = sm.login(staffname, password);
			if (result==1) {
//				设置登陆状态
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", true);
				session.setAttribute("staffname", staffname);
//				显示登陆成功信息
				request.setAttribute("httpUrl", "/index");
				request.setAttribute("title", "登录成功");
				request.setAttribute("info","即将跳转至后台首页。");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
			else {
				request.setAttribute("httpUrl", "/login");
				request.setAttribute("title", "登陆失败");
				request.setAttribute("info","账号或密码不正确。即将返回登陆页面");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
