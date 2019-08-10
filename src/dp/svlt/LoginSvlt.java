package dp.svlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import dp.utils.DbConn;

public class LoginSvlt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginSvlt() {
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
		response.setContentType("text/html");
		//设置传输数据编码方式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取前台传送的变量，账号，密码和用户类型
		String uname=request.getParameter("uname");
		String upassword=request.getParameter("upassword");
		String utype=request.getParameter("utype");
		//创建数据库操作对象
		DbConn db=new DbConn();
		//sql语句变量定义
		String sql="";
		int id=-1;
		String usname="";
		if("gly".equals(utype)){
			sql="select id from userinfo where uname='"+uname+"' and upassword='"+upassword+"'";
		}else if("cus".equals(utype)){
			sql="select id,cname from cusinfo where cidcard='"+uname+"' and cpassword='"+upassword+"'";
		}
		JSONObject json=new JSONObject();
		if("".equals(sql)){
			json.put("msg", "系统错误01.");
		}else{
			ResultSet rs=db.query(sql);
			if(rs==null){
				json.put("msg", "系统错误02");
			}else{
				try {
					while(rs.next()){
						id=rs.getInt("id");
						if("cus".equals(utype)){
							usname=rs.getString("cname");
						}else if("gly".equals(utype)){
							usname=uname;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(id!=-1){
				HttpSession session=request.getSession();
				json.put("msg", 1);
				session.setAttribute("username", usname);
				session.setAttribute("userid", id);
				session.setAttribute("utype", utype);
			}else{
				json.put("msg", "账号或密码错误。");
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
