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
			//����������ַ���
		request.setCharacterEncoding("utf-8");
			//������Ӧ���ı�����
		response.setContentType("text/html;charset=utf-8");
   		SystemUserManager sm = new SystemUserManager();
   		UserManager m = new UserManager();
   		String staffname=request.getParameter("staffname");
   		String password=request.getParameter("password");
   		try {
			int result = sm.login(staffname, password);
			if (result==1) {
//				���õ�½״̬
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", true);
				session.setAttribute("staffname", staffname);
//				��ʾ��½�ɹ���Ϣ
				request.setAttribute("httpUrl", "/index");
				request.setAttribute("title", "��¼�ɹ�");
				request.setAttribute("info","������ת����̨��ҳ��");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
			else {
				request.setAttribute("httpUrl", "/login");
				request.setAttribute("title", "��½ʧ��");
				request.setAttribute("info","�˺Ż����벻��ȷ���������ص�½ҳ��");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
