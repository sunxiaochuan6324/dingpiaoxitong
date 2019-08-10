package dp.admin.svlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dp.been.Zhandian;
import dp.utils.DbConn;
import dp.utils.FindService;

import net.sf.json.JSONObject;

public class GetZdByTid extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetZdByTid() {
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
		String tid=request.getParameter("tid");
		JSONObject json=new JSONObject();
		if(!"".equals(tid)&&tid!=null){
			DbConn db=new DbConn();
			ResultSet rs=db.query("select zhandian.* from xianlu,zhandian where xianlu.zdid=zhandian.id and xianlu.tid="+tid);
			List<Zhandian> zdlist=FindService.getZhandian(rs);
			json.put("msg", 1);
			json.put("zdlist", zdlist);
		}else{
			json.put("msg", 0);
		}
		PrintWriter out=response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
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
