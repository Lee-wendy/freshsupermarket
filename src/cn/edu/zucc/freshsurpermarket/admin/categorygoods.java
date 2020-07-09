package cn.edu.zucc.freshsurpermarket.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.freshsurpermarket.comtrol.GoodsManager;
import cn.edu.zucc.freshsurpermarket.model.BeanGoods;
import cn.edu.zucc.freshsurpermarket.util.BaseException;

/**
 * Servlet implementation class categorygoods
 */
@WebServlet("/admin/categorygoods")
public class categorygoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public categorygoods() {
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
		int categorycode = Integer.valueOf(request.getParameter("categorycode"));
		request.setAttribute("categorycode", categorycode);
		try {
			String page = request.getParameter("page");
			if(page==null) {
				page="1";
			}
			int num = (Integer.parseInt(page)-1)*5;
			GoodsManager gm = new GoodsManager();
			List<BeanGoods> categorygoods=new ArrayList<BeanGoods>();
			int allpage = 0;
			int allnum = 0;
//			获取模糊查找生鲜类别
			String likecategorygoods = request.getParameter("likecategorygoods");
			if(likecategorygoods==null) {
//				没有模糊查找任何类别
				categorygoods = gm.loadCategoryGoods(categorycode,num);
//				获取类别数
				allnum = gm.CategoryGoodsnum(categorycode);
				allpage =(int) Math.ceil((double)allnum /5);
				System.out.print(allnum);
			}
			else {
//				模糊查询
				categorygoods = gm.SearchCategoryGoods(categorycode, likecategorygoods,num);
//				获取类别数
				allnum = gm.SearchCategoryGoodsnum(categorycode, likecategorygoods);
				allpage =(int) Math.ceil((double)allnum /5);
			}
			request.setAttribute("categorygoods", categorygoods);
			request.setAttribute("page",page);
			request.setAttribute("allnum",allnum);
			request.setAttribute("allpage",allpage);
			request.getRequestDispatcher("/admin/categorygoods.jsp").forward(request, response);
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
