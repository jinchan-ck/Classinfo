package tk.sweetvvck.web.controller;

import com.opensymphony.xwork2.ActionSupport;

public class AddAlbumAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String albumname = null;
	private String studentNum = null;
	private int albumId = 0;
	
	@Override
	public String execute() throws Exception {
		System.out.println("studentNum:" + studentNum);
		System.out.println("albumId = " + albumId);
		return SUCCESS;
	}


	public String getAlbumname() {
		return albumname;
	}


	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}


	public String getStudentNum() {
		return studentNum;
	}


	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}


	public int getAlbumId() {
		return albumId;
	}


	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

}
