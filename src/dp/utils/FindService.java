package dp.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dp.been.Dpiao;
import dp.been.Piao;
import dp.been.Cusinfo;
import dp.been.Traininfo;
import dp.been.Userinfo;
import dp.been.Xianlu;
import dp.been.Zhandian;
//该类的作用就是将结果集转化成链表
public class FindService {
	//将结果集转化成链表
	public static List<Userinfo> getUserinfo(ResultSet rs){
		List<Userinfo> alist=new ArrayList();
		if(rs!=null){
			try {
				rs.beforeFirst();
				while(rs.next()){
					Userinfo a=new Userinfo();
					a.setId(rs.getInt("id"));
					a.setUname(rs.getString("uname"));
					a.setUpassword(rs.getString("upassword"));
					a.setUtype(rs.getString("utype"));
					alist.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alist;
	}
	//将结果集转化成链表
	public static List<Dpiao> getDpiao(ResultSet rs){
		List<Dpiao> alist=new ArrayList();
		if(rs!=null){
			try {
				rs.beforeFirst();
				while(rs.next()){
					Dpiao a=new Dpiao();
					a.setId(rs.getInt("id"));
					a.setCxzh(rs.getString("cxzh"));
					a.setSzdid(rs.getInt("szdid"));
					a.setSzdname(rs.getString("szdname"));
					a.setLyflag(rs.getString("lyflag"));
					a.setPtype(rs.getString("ptype"));
					a.setPval(rs.getString("pval"));
					a.setEzdid(rs.getInt("ezdid"));
					a.setEzdname(rs.getString("ezdname"));
					a.setStime(rs.getString("stime"));
					a.setTid(rs.getInt("tid"));
					a.setTname(rs.getString("tname"));
					a.setTraintype(rs.getString("traintype"));
					String yx=rs.getString("yxsj");
					if(yx.length()>10){
						yx=yx.substring(0,10);
					}
					a.setYxsj(yx);
					alist.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alist;
	}
	//将结果集转化成链表
	public static List<Piao> getPiao(ResultSet rs){
		List<Piao> alist=new ArrayList();
		if(rs!=null){
			try {
				rs.beforeFirst();
				while(rs.next()){
					Piao a=new Piao();
					a.setId(rs.getInt("id"));
					a.setCxzh(rs.getString("cxzh"));
					a.setSzdid(rs.getInt("szdid"));
					a.setSzdname(rs.getString("saddr"));
					a.setPtype(rs.getString("ptype"));
					a.setPval(rs.getString("pval"));
					a.setEzdid(rs.getInt("ezdid"));
					a.setEzdname(rs.getString("eaddr"));
					a.setStime(rs.getString("stime"));
					a.setTid(rs.getInt("tid"));
					a.setTname(rs.getString("tname"));
					a.setTraintype(rs.getString("traintype"));
					String yx=rs.getString("yxsj");
					if(yx.length()>10){
						yx=yx.substring(0,10);
					}
					a.setYxsj(yx);
					alist.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alist;
	}
	//将结果集转化成链表
	public static List<Cusinfo> getCusinfo(ResultSet rs){
		List<Cusinfo> alist=new ArrayList();
		if(rs!=null){
			try {
				rs.beforeFirst();
				while(rs.next()){
					Cusinfo a=new Cusinfo();
					a.setId(rs.getInt("id"));
					a.setCidcard(rs.getString("cidcard"));
					a.setCname(rs.getString("cname"));
					a.setCpassword(rs.getString("cpassword"));
					a.setIntime(rs.getString("intime"));
					alist.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alist;
	}
	//将结果集转化成链表
	public static List<Traininfo> getTraininfo(ResultSet rs){
		List<Traininfo> alist=new ArrayList();
		if(rs!=null){
			try {
				rs.beforeFirst();
				while(rs.next()){
					Traininfo a=new Traininfo();
					a.setId(rs.getInt("id"));
					a.setCz(rs.getString("cz"));
					a.setTname(rs.getString("tname"));
					a.setTraintype(rs.getString("traintype"));
					a.setXcnums(rs.getString("xcnums"));
					a.setZnums(rs.getString("znums"));
					alist.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alist;
	}
	
	//将结果集转化成链表
	public static List<Zhandian> getZhandian(ResultSet rs){
		List<Zhandian> alist=new ArrayList();
		if(rs!=null){
			try {
				rs.beforeFirst();
				while(rs.next()){
					Zhandian a=new Zhandian();
					a.setId(rs.getInt("id"));
					a.setZdname(rs.getString("zdname"));
					alist.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alist;
	}
	//将结果集转化成链表
	public static List<Zhandian> getZhandians(ResultSet rs){
		List<Zhandian> alist=new ArrayList();
		if(rs!=null){
			try {
				rs.beforeFirst();
				while(rs.next()){
					Zhandian a=new Zhandian();
					a.setId(rs.getInt("id"));
					a.setXh(rs.getInt("xh"));
					a.setZdname(rs.getString("zdname"));
					alist.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alist;
	}
}
