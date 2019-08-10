package dp.admin.svlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.been.Cusinfo;
import dp.been.Dpiao;
import dp.been.Piao;
import dp.been.Traininfo;
import dp.been.Userinfo;
import dp.been.Xianlu;
import dp.been.Zhandian;
import dp.utils.DbConn;
import dp.utils.FindService;
import dp.utils.HandleProperties;

public class Init extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Init() {
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
		
		if(utype==null||userid==null){
			return;
		}
		//获取前台数据
		String flgs=request.getParameter("flgs");
		String tname=request.getParameter("tname");
		//定义sql语句变量
		String sql="";
		//创建配置文件操作对象		
		HandleProperties hp=new HandleProperties(session,utype+""+userid);
		//创建数据库操作对象
		DbConn db=new DbConn();
		//管理员信息管理初始化
		if("admin".equals(tname)){
			if("1".equals(flgs)){
				sql="select * from userinfo";
				hp.setValue("sql", sql);
			}else{
				sql=hp.readValue("sql");
			}
			ResultSet rs=db.query(sql);
			List<Userinfo> alist=FindService.getUserinfo(rs);
			request.setAttribute("alist", alist);
			request.getRequestDispatcher("/admin/mgadmin.jsp").forward(request, response);
			return;
		}
		//顾客信息管理初始化
		if("cusinfo".equals(tname)){
			if("1".equals(flgs)){
				sql="select * from cusinfo";
				hp.setValue("sql", sql);
			}else{
				sql=hp.readValue("sql");
			}
			ResultSet rs=db.query(sql);
			List<Cusinfo> alist=FindService.getCusinfo(rs);
			request.setAttribute("alist", alist);
			request.getRequestDispatcher("/admin/mgcusinfo.jsp").forward(request, response);
			return;
		}
		//火车信息管理初始化
		if("traininfo".equals(tname)){
			if("1".equals(flgs)){
				sql="select * from traininfo";
				hp.setValue("sql", sql);
			}else{
				sql=hp.readValue("sql");
			}
			ResultSet rs=db.query(sql);
			List<Traininfo> alist=FindService.getTraininfo(rs);
			request.setAttribute("alist", alist);
			request.getRequestDispatcher("/admin/mgtraininfo.jsp").forward(request, response);
			return;
		}
		//站点信息管理初始化
		if("zhandian".equals(tname)){
			if("1".equals(flgs)){
				sql="select * from zhandian";
				hp.setValue("sql", sql);
			}else{
				sql=hp.readValue("sql");
			}
			ResultSet rs=db.query(sql);
			List<Zhandian> alist=FindService.getZhandian(rs);
			request.setAttribute("alist", alist);
			request.getRequestDispatcher("/admin/mgzhandian.jsp").forward(request, response);
			return;
		}
		//线路信息初始化
		if("initxl".equals(tname)){
			if("1".equals(flgs)){
				sql="select * from traininfo";
				hp.setValue("sql", sql);
			}else{
				sql=hp.readValue("sql");
			}
			List<Xianlu> xllist=new ArrayList();
			DbConn db2=new DbConn();
			ResultSet rs=db.query(sql);
			List<Traininfo> tlist=FindService.getTraininfo(rs);
			Iterator<Traininfo> it=tlist.iterator();
			while(it.hasNext()){
				Traininfo t=it.next();
				Xianlu xl=new Xianlu();
				xl.setTid(t.getId());
				xl.setTname(t.getTname());
				ResultSet rs2=db2.query("select zhandian.*,xh from xianlu,zhandian where xianlu.zdid=zhandian.id and xianlu.tid="+t.getId()+" order by xh");
				List<Zhandian> zdlist=FindService.getZhandian(rs2);
				xl.setZdlist(zdlist);
				xllist.add(xl);
			}
			request.setAttribute("alist", xllist);
			request.getRequestDispatcher("/admin/initxianlu.jsp").forward(request, response);
			return;
		}
		//线路信息管理初始化
		if("xianlu".equals(tname)){
			String ids=request.getParameter("tid");
			if("1".equals(flgs)){
				sql="select zhandian.*,xh from zhandian,xianlu where zhandian.id=xianlu.zdid and xianlu.tid="+ids+"  order by xh";
				hp.setValue("sql", sql);
			}else{
				sql=hp.readValue("sql");
			}
			ResultSet rs=db.query(sql);
			List<Zhandian> zlist=FindService.getZhandians(rs);
			request.setAttribute("alist", zlist);
			DbConn db2=new DbConn();
			ResultSet rs2=db2.query("select * from zhandian where id not in(select zdid from xianlu where tid="+ids+")");
			List<Zhandian> zdlist=FindService.getZhandian(rs2);
			request.setAttribute("zdlist", zdlist);
			DbConn db3=new DbConn();
			Object tn=db3.getOnlyOne("select tname from traininfo where id="+ids);
			Traininfo t=new Traininfo();
			t.setId(Integer.parseInt(ids));
			t.setTname(tn+"");
			request.setAttribute("t", t);
			request.getRequestDispatcher("/admin/mgxianlu.jsp").forward(request, response);
			return;
		}
		//车票信息管理初始化
		if("piao".equals(tname)){
			String ids=request.getParameter("tid");
			if("1".equals(flgs)){
				sql="select tmp.*,zdname as eaddr,tname,traintype from (select piao.*,zdname as saddr from piao,zhandian where piao.szdid=zhandian.id and yxsj='1975-01-01' and zdname like '%0%') tmp,zhandian,traininfo where tmp.tid=traininfo.id and tmp.ezdid=zhandian.id and zdname like '%0%' and 1=0";
				hp.setValue("sql", sql);
			}else{
				sql=hp.readValue("sql");
			}
			ResultSet rs2=db.query(sql);
			List<Piao> plist=FindService.getPiao(rs2);
			request.setAttribute("alist", plist);
			DbConn db2=new DbConn();
			ResultSet rs=db2.query("select * from traininfo");
			List<Traininfo> tlist=FindService.getTraininfo(rs);
			request.setAttribute("tlist", tlist);
			request.getRequestDispatcher("/admin/mgpiao.jsp").forward(request, response);
			return;
		}
		//订票信息管理初始化
		if("dpiao".equals(tname)){
			ResultSet rs=db.query("select dpiao.*,cxzh,ezdid,ezdname,ptype,pval,stime,szdid,szdname,tid,tname,traintype,yxsj from(select tmp.*,zdname as ezdname from(select piao.*,zdname as szdname,tname,traintype  from piao,zhandian,traininfo where piao.tid=traininfo.id and piao.szdid=zhandian.id)tmp,zhandian where tmp.ezdid=zhandian.id)tm,dpiao,cusinfo where tm.id=dpiao.pid and dpiao.cidcard=cusinfo.cidcard");
			List<Dpiao> dplist=FindService.getDpiao(rs);
			request.setAttribute("alist", dplist);
			request.getRequestDispatcher("/admin/mdpiao.jsp").forward(request, response);
			return;
		}
		//登录/注册跳转
		if("dl".equals(tname)){
			String sflag=request.getParameter("sflag");
			request.setAttribute("sflag",sflag );
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
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
