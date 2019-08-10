package dp.been;

import java.util.List;

public class Xianlu {
	private int id;
	private int xh;
	private List<Zhandian> zdlist;
	private int tid;
	private String tname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getXh() {
		return xh;
	}
	public void setXh(int xh) {
		this.xh = xh;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public List<Zhandian> getZdlist() {
		return zdlist;
	}
	public void setZdlist(List<Zhandian> zdlist) {
		this.zdlist = zdlist;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
}
