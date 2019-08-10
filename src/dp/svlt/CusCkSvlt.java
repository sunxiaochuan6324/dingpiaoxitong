package dp.svlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.been.Piao;
import dp.utils.DbConn;
import dp.utils.FindService;

public class CusCkSvlt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CusCkSvlt() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置传输数据编码方式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		HttpSession session =request.getSession();
		String flgs=request.getParameter("flgs");
		String sqls="";
		if("1".equals(flgs)){
			sqls=request.getParameter("sql");
			session.setAttribute("sql", sqls);
		}else{
			Object sql=session.getAttribute("sql");
			if(sql==null){
				
			}else{
				sqls=sql.toString();
			}
		}
		if(!"".equals(sqls)){
			DbConn db=new DbConn();
			ResultSet rs=db.query(sqls);
			List<Piao> alist=FindService.getPiao(rs);
			request.setAttribute("alist", alist);
		}
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
