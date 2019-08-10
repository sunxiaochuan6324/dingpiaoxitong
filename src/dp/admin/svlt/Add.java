package dp.admin.svlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.utils.DbConn;
import net.sf.json.JSONObject;

public class Add extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Add() {
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
		//管理员信息添加
		if("admin".equals(tname)){
			String un=request.getParameter("uname");
			String up=request.getParameter("upassword");
			boolean bl=db.checkTrue("select id from userinfo where uname='"+un+"'");
			if(bl){
				json.put("msg", "账号已存在");
			}else{
				bl=db.insort("insert into userinfo(uname,upassword) values('"+un+"','"+up+"')");
				if(!bl){
					json.put("msg", "添加成功。");
				}else{
					json.put("msg", "添加失败");
				}
			}
		}
		//顾客信息添加
		if("cusinfo".equals(tname)){
			String un=request.getParameter("cidcard");
			String up=request.getParameter("cpassword");
			String um=request.getParameter("cname");
			boolean bl=db.checkTrue("select id from cusinfo where cidcard='"+un+"'");
			if(bl){
				json.put("msg", "身份证已注册");
			}else{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				bl=db.insort("insert into cusinfo(cidcard,cpassword,cname,intime) values('"+un+"','"+up+"','"+um+"','"+sdf.format(new Date())+"')");
				if(!bl){
					json.put("msg", "添加成功。");
				}else{
					json.put("msg", "添加失败");
				}
			}
		}
		//火车信息添加
		if("traininfo".equals(tname)){
			String tnames=request.getParameter("tnames");
			String cz=request.getParameter("cz");
			String znums=request.getParameter("znums");
			String xcnums=request.getParameter("xcnums");
			String traintype=request.getParameter("traintype");
			boolean bl=db.checkTrue("select id from traininfo where tname='"+tnames+"'");
			if(bl){
				json.put("msg", "车次已存在");
			}else{
				bl=db.insort("insert into traininfo(tname,cz,znums,xcnums,traintype) values('"+tnames+"','"+cz+"',"+znums+","+xcnums+",'"+traintype+"')");
				if(!bl){
					json.put("msg", "添加成功。");
				}else{
					json.put("msg", "添加失败");
				}
			}
		}
		//站点信息添加
		if("zhandian".equals(tname)){
			String zdname=request.getParameter("zdname");
			boolean bl=db.checkTrue("select id from zhandian where zdname='"+zdname+"'");
			if(bl){
				json.put("msg", "站点名称已存在");
			}else{
				bl=db.insort("insert into zhandian(zdname) values('"+zdname+"')");
				if(!bl){
					json.put("msg", "添加成功。");
				}else{
					json.put("msg", "添加失败");
				}
			}
		}
		//线路信息添加
		if("xianlu".equals(tname)){
			String tid=request.getParameter("tid");
			String zdid=request.getParameter("zdid");
			String xh=request.getParameter("xh");
			DbConn db2=new DbConn();
			if(db.checkTrue("select id from xianlu where tid="+tid+"  and zdid="+zdid)){
				json.put("msg", "该线路已有该站点");
			}else if(db2.checkTrue("select id from xianlu where tid="+tid+" and xh="+xh)){
				json.put("msg", "站点序号已存在");
			}else{
				boolean bl=db.insort("insert into xianlu(xh,tid,zdid) values("+xh+","+tid+","+zdid+")");
				if(!bl){
					json.put("msg", "添加成功。");
				}else{
					json.put("msg", "添加失败");
				}
			}
		}
		//车票信息添加
		//data:{tname:'piao',"szdid":szdid,"stime":stime,"ezdid":ezdid,"pval":pval,"ptype":ptype,
		//"cxzh":cxzh,"yxsj":yxsj,"tid":tid,idi:ids},
		if("piao".equals(tname)){
			String tid=request.getParameter("tid");
			String szdid=request.getParameter("szdid");
			String ezdid=request.getParameter("ezdid");
			String stime=request.getParameter("stime");
			String pval=request.getParameter("pval");
			String ptype=request.getParameter("ptype");
			String cxzh=request.getParameter("cxzh");
			String yxsj=request.getParameter("yxsj");
			boolean bl=db.insort("insert into piao(szdid,ezdid,tid,stime,pval,ptype,cxzh,yxsj) values("+szdid+","+ezdid+","+tid+",'"+stime+"',"+pval+",'"+ptype+"','"+cxzh+"','"+yxsj+"')");
			if(!bl){
				json.put("msg", "添加成功。");
			}else{
				json.put("msg", "添加失败");
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
