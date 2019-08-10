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

import dp.been.Dpiao;
import dp.utils.DbConn;
import dp.utils.FindService;

public class DpListSvlt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DpListSvlt() {
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
		Object userid=session.getAttribute("userid");
		if(userid==null){
			request.getRequestDispatcher("/CusCkSvlt").forward(request, response);
			return;
		}else{
			DbConn db=new DbConn();
			ResultSet rs=db.query("select dpiao.*,cxzh,ezdid,ezdname,ptype,pval,stime,szdid,szdname,tid,tname,traintype,yxsj from(select tmp.*,zdname as ezdname from(select piao.*,zdname as szdname,tname,traintype  from piao,zhandian,traininfo where piao.tid=traininfo.id and piao.szdid=zhandian.id)tmp,zhandian where tmp.ezdid=zhandian.id)tm,dpiao,cusinfo where tm.id=dpiao.pid and dpiao.cidcard=cusinfo.cidcard and cusinfo.id="+userid);
			List<Dpiao> dplist=FindService.getDpiao(rs);
			request.setAttribute("alist", dplist);
			request.getRequestDispatcher("/customer/main.jsp").forward(request, response);
		}
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
