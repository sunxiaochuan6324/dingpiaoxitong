package dp.admin.svlt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.utils.DbConn;

import net.sf.json.JSONObject;

public class Del extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Del() {
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
		HttpSession session=request.getSession();
		//获取当前登录用户信息
		Object utype=session.getAttribute("utype");
		Object userid=session.getAttribute("userid");
		//创建json对象
		JSONObject json=new JSONObject();
		if(utype==null||userid==null){
			json.put("msg", "请重新登陆。");
			return;
		}
		//获取前台数据tname
		String tname=request.getParameter("tname");
		//创建数据库操作对象
		DbConn db=new DbConn();
		//管理员信息删除
		if("admin".equals(tname)){
			String ids=request.getParameter("idi");
			int bls=db.deleteOrUpdate("delete from userinfo where id="+ids);
			if(bls>0){
				json.put("msg", 1);
			}else{
				json.put("msg", "删除失败");
			}
		}
		//顾客信息删除
		if("cusinfo".equals(tname)){
			String ids=request.getParameter("idi");
			Object cidcard=db.getOnlyOne("select cidcard from cusinfo where id="+ids);
			int bls=db.deleteOrUpdate("delete from cusinfo where id="+ids);
			if(bls>0){
				json.put("msg", 1);
				//顾客信息删除，同时情况，其购票信息
				db.deleteOrUpdate("delete from dpiao where cidcard="+cidcard);
			}else{
				json.put("msg", "删除失败");
			}
		}
		//火车信息删除
		if("traininfo".equals(tname)){
			String ids=request.getParameter("idi");
			if(db.checkTrue("select id from piao where tid="+ids)){
				json.put("msg", "该列车有票务信息存在不能删除.");
			}else if(db.checkTrue("select id from xianlu where tid="+ids)){
				json.put("msg", "该火车有线路信息存在不能删除.");
			}else{
				int bls=db.deleteOrUpdate("delete from traininfo where id="+ids);
				if(bls>0){
					json.put("msg", 1);
				}else{
					json.put("msg", "删除失败");
				}
			}
		}
		//站点信息删除
		if("zhandian".equals(tname)){
			String ids=request.getParameter("idi");
			if(db.checkTrue("select id from xianlu where zdid="+ids)){
				json.put("msg", "该站点有线路信息存在不能删除.");
			}else{
				int bls=db.deleteOrUpdate("delete from zhandian where id="+ids);
				if(bls>0){
					json.put("msg", 1);
				}else{
					json.put("msg", "删除失败");
				}
			}
		}
		//线路信息删除
		if("xianlu".equals(tname)){
			String zdid=request.getParameter("zdid");
			String tid=request.getParameter("tid");
			int bls=db.deleteOrUpdate("delete from xianlu where zdid="+zdid+" and tid="+tid);
			if(bls>0){
				json.put("msg", 1);
			}else{
				json.put("msg", "删除失败");
			}
		}
		//车票信息删除
		if("piao".equals(tname)){
			String id=request.getParameter("idi");
			int bls=db.deleteOrUpdate("delete from piao where id="+id);
			if(bls>0){
				json.put("msg", 1);
			}else{
				json.put("msg", "删除失败");
			}
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
