package dp.svlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dp.utils.DbConn;

import net.sf.json.JSONObject;

public class RegSvlt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegSvlt() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String rgidcard=request.getParameter("rgidcard");
		String rgpassword=request.getParameter("rgpassword");
		String rgsname=request.getParameter("rgsname");
		JSONObject json =new JSONObject();
		DbConn db=new DbConn();
		if(rgidcard!=null&&!"".equals(rgidcard)&&rgpassword!=null&&!"".equals(rgpassword)&&rgsname!=null&&!"".equals(rgsname)){
			if(db.checkTrue("select id from cusinfo where cidcard='"+rgidcard+"'")){
				json.put("msg", "身份证已注册");
			}else{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				boolean bl=db.insort("insert into cusinfo(cname,cidcard,cpassword,intime) values('"+rgsname+"','"+rgidcard+"','"+rgpassword+"','"+sdf.format(new Date())+"')");
				if(!bl){
					json.put("msg", "注册成功");
				}else{
					json.put("msg", "error:02");
				}
			}
		}else{
			json.put("msg", "error:01");
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
