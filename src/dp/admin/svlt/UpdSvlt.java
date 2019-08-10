package dp.admin.svlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.utils.DbConn;

import net.sf.json.JSONObject;

public class UpdSvlt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdSvlt() {
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
		//管理员信息修改
		if("admin".equals(tname)){
			String un=request.getParameter("uname");
			String up=request.getParameter("upassword");
			String ids=request.getParameter("idi");
			ResultSet rs=db.query("select id from userinfo where uname='"+un+"'");
			if(rs!=null){
				boolean bl=false;
				int id=-1;
				try {
					while(rs.next()){
						id=rs.getInt("id");
						if(!String.valueOf(id).equals(ids)){
							bl=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bl){
					json.put("msg", "账号已存在");
				}else{
					int bls=db.deleteOrUpdate("update userinfo set uname='"+un+"' ,upassword='"+up+"' where id="+ids);
					if(bls>0){
						json.put("msg", "修改成功。");
					}else{
						json.put("msg", "修改失败");
					}
				}
			}else{
				json.put("msg", "操作错误01");
			}
		}
		//顾客信息修改
		if("cusinfo".equals(tname)){
			String un=request.getParameter("cidcard");
			String up=request.getParameter("cpassword");
			String um=request.getParameter("cname");
			String ids=request.getParameter("idi");
			ResultSet rs=db.query("select id from cusinfo where cidcard='"+un+"'");
			if(rs!=null){
				boolean bl=false;
				int id=-1;
				try {
					while(rs.next()){
						id=rs.getInt("id");
						if(!String.valueOf(id).equals(ids)){
							bl=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bl){
					json.put("msg", "身份证已注册");
				}else{
					int bls=db.deleteOrUpdate("update cusinfo set cname='"+um+"', cidcard='"+un+"' ,cpassword='"+up+"' where id="+ids);
					if(bls>0){
						json.put("msg", "修改成功。");
					}else{
						json.put("msg", "修改失败");
					}
				}
			}else{
				json.put("msg", "操作错误01");
			}
		}
		//火车信息修改
		if("traininfo".equals(tname)){
			String tnames=request.getParameter("tnames");
			String cz=request.getParameter("cz");
			String znums=request.getParameter("znums");
			String xcnums=request.getParameter("xcnums");
			String traintype=request.getParameter("traintype");
			String ids=request.getParameter("idi");
			ResultSet rs=db.query("select id from traininfo where tname='"+tnames+"'");
			if(rs!=null){
				boolean bl=false;
				int id=-1;
				try {
					while(rs.next()){
						id=rs.getInt("id");
						if(!String.valueOf(id).equals(ids)){
							bl=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bl){
					json.put("msg", "车次已注册");
				}else{
					int bls=db.deleteOrUpdate("update traininfo set tname='"+tnames+"', cz='"+cz+"' ,traintype='"+traintype+"',znums="+znums+",xcnums="+xcnums+" where id="+ids);
					if(bls>0){
						json.put("msg", "修改成功。");
					}else{
						json.put("msg", "修改失败");
					}
				}
			}else{
				json.put("msg", "操作错误01");
			}
		}
		//站点信息修改
		if("zhandian".equals(tname)){
			String zdname=request.getParameter("zdname");
			String ids=request.getParameter("idi");
			ResultSet rs=db.query("select id from zhandian where zdname='"+zdname+"'");
			if(rs!=null){
				boolean bl=false;
				int id=-1;
				try {
					while(rs.next()){
						id=rs.getInt("id");
						if(!String.valueOf(id).equals(ids)){
							bl=true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bl){
					json.put("msg", "站点名称已存在");
				}else{
					int bls=db.deleteOrUpdate("update zhandian set zdname='"+zdname+"' where id="+ids);
					if(bls>0){
						json.put("msg", "修改成功。");
					}else{
						json.put("msg", "修改失败");
					}
				}
			}else{
				json.put("msg", "操作错误01");
			}
		}
		//车票信息修改
		if("piao".equals(tname)){
			String tid=request.getParameter("tid");
			String szdid=request.getParameter("szdid");
			String ezdid=request.getParameter("ezdid");
			String stime=request.getParameter("stime");
			String pval=request.getParameter("pval");
			String ptype=request.getParameter("ptype");
			String cxzh=request.getParameter("cxzh");
			String yxsj=request.getParameter("yxsj");
			String ids=request.getParameter("idi");
			int bls=db.deleteOrUpdate("update piao set yxsj='"+yxsj+"', cxzh='"+cxzh+"' ,ptype='"+ptype+"',pval="+pval+", stime='"+stime+"', tid="+tid+",szdid="+szdid+",ezdid="+ezdid+" where id="+ids);
			if(bls>0){
				json.put("msg", "修改成功。");
			}else{
				json.put("msg", "修改失败");
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
