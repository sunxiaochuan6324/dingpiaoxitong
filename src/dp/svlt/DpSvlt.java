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

public class DpSvlt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DpSvlt() {
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
		HttpSession session=request.getSession();
		Object userid =session.getAttribute("userid");
		JSONObject json=new JSONObject();
		if(userid==null){
			json.put("msg", "请登录.");
		}else{
			String pid=request.getParameter("pid");
			if("".equals(pid)||pid==null){
				json.put("msg", "error:01");
			}else {
				DbConn db=new DbConn();
				Object cidcard=db.getOnlyOne("select cidcard from cusinfo where id="+userid);
				boolean bl=db.insort("insert into dpiao(cidcard,pid,lyflag) values('"+cidcard+"',"+pid+",'待付款')");
				if(!bl){
					json.put("msg", 1);
				}else{
					json.put("msg", "error:02");
				}
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
