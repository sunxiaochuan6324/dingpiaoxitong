package dp.svlt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dp.utils.DbConn;

import net.sf.json.JSONObject;

public class HandlePiaoSvlt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HandlePiaoSvlt() {
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
		String id=request.getParameter("idi");
		String flgs=request.getParameter("flgs");
		JSONObject json=new JSONObject();
		DbConn db=new DbConn();
		if("fk".equals(flgs)){
			int bls=db.deleteOrUpdate("update dpiao set lyflag='已付款' where id="+id);
			if(bls>0){
				json.put("dt", 1);
				json.put("msg", "付款成功。");
			}else{
				json.put("dt", 0);
				json.put("msg", "fkerror:01");
			}
		}else if("gq".equals(flgs)){//改签
			int bls=db.deleteOrUpdate("delete from dpiao where id="+id);
			if(bls>0){
				json.put("dt", 2);
				json.put("msg", "改签成功，请重新选票订票。");
			}else{
				json.put("dt", 0);
				json.put("msg", "fkerror:01");
			}
		}else if("lp".equals(flgs)){
			int bls=db.deleteOrUpdate("update dpiao set lyflag='已领票' where id="+id);
			if(bls>0){
				json.put("dt", 1);
				json.put("msg", "领票成功。");
			}else{
				json.put("dt", 0);
				json.put("msg", "lperror:01");
			}
		}else if("tp".equals(flgs)){
			int bls=db.deleteOrUpdate("delete from dpiao where id="+id);
			if(bls>0){
				json.put("dt", 1);
				json.put("msg", "退票成功。");
			}else{
				json.put("dt", 0);
				json.put("msg", "tperror:01");
			}
		}else if("del".equals(flgs)){
			int bls=db.deleteOrUpdate("delete from dpiao where id="+id);
			if(bls>0){
				json.put("dt", 1);
				json.put("msg", "删除成功。");
			}else{
				json.put("dt", 0);
				json.put("msg", "tperror:01");
			}
		}else{
			json.put("dt", 0);
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
