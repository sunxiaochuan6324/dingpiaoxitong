package dp.svlt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.utils.DbConn;

import net.sf.json.JSONObject;

public class ChangePwd extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangePwd() {
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
		HttpSession session=request.getSession();
		Object utype=session.getAttribute("utype");
		Object userid=session.getAttribute("userid");
		JSONObject json=new JSONObject();
		if(utype==null||userid==null){
			json.put("msg", "请重新登陆");
		}else{
			String opwd=request.getParameter("opwd");
			String npwd=request.getParameter("npwd");
			DbConn db=new DbConn();
			String sql="";
			if("gly".equals(utype.toString())){
				sql="select id from userinfo where id="+userid+" and upassword='"+opwd+"'";
			}else if("cus".equals(utype.toString())){
				sql="select id from cusinfo where id="+userid+" and cpassword='"+opwd+"'";
			}
			boolean bl=false;
			if("".equals(sql)){
				json.put("msg", "系统错误01");
			}else{
				bl=db.checkTrue(sql);
			}
			if(bl){
				if("gly".equals(utype.toString())){
					sql="update userinfo set upassword='"+npwd+"' where id="+userid;
				}else if("cus".equals(utype.toString())){
					sql="update cusinfo set cpassword='"+npwd+"' where id="+userid;
				}
				int bls=db.deleteOrUpdate(sql);
				if(bls>0){
					json.put("msg", "修改成功。");
				}else{
					json.put("msg", "修改失败。");
				}
			}else{
				json.put("msg", "旧密码输入有误");
			}
		}
		PrintWriter out = response.getWriter();
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
