package cn.edu.zucc.freshsurpermarket.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.freshsurpermarket.comtrol.SystemUserManager;
import cn.edu.zucc.freshsurpermarket.util.BaseException;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
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
		String staffname=request.getParameter("staffname");
		String password=request.getParameter("password");
		String ackpassword=request.getParameter("ackpassword");
//		System.out.println(staffname+" "+password+" "+ackpassword);
//		request.getRequestDispatcher("/staff/info.jsp").forward(request, response);
		SystemUserManager sum = new SystemUserManager();
		try {
			int result=sum.reg(staffname, password, ackpassword);
			if(result==0) {
//				System.out.print("注册成功！");
				request.setAttribute("title", "注册成功");
				request.setAttribute("info", "注册成功即将跳转至登录页面");
				request.setAttribute("httpUrl", "/login");
			}
			else if(result==1) {
				request.setAttribute("title", "注册失败");
				request.setAttribute("info", "密码必须大于等于6位，即将回到注册页面");
				request.setAttribute("httpUrl", "/register");
			}
			else if(result==2) {
				request.setAttribute("title", "注册失败");
				request.setAttribute("info", "两次输入密码不一致，即将回到注册页面");
				request.setAttribute("httpUrl", "/register");
			}
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
//			response.sendRedirect("index.html");
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
