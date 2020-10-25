package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import vo.User;
import dao.UserDao;

@WebServlet(urlPatterns="/ajaxLoginCheck.do")
public class AjaxLoginCheck extends HttpServlet {

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
		//设置请求参数编码格式为utf-8,防止中文参数乱码
		request.setCharacterEncoding("utf-8");
		//1.按照表单的各元素的name属性值获取各请求参数值
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String vcode=request.getParameter("vcode");
		String autologin=request.getParameter("autologin");
		//2.获取HttpSession对象
		HttpSession session=request.getSession();
		//取出CreateVerifyImageController中存放的验证码字符串
		String saveVcode=(String) session.getAttribute("verify");
		//存放返回信息的Map
		Map<String,Object> map=new HashMap<String,Object>();
		//比较输入的验证码和随机生成的验证码是否相同
		if(!vcode.equalsIgnoreCase(saveVcode)){//不同
			//在map中存放返回数据
			map.put("code",1);
			map.put("info","验证码不正确！");
		}else{//验证码正确
			UserDao userDao=new UserDao();
			User user=userDao.get(userName);
			if(user==null){//用户名不存在
				map.put("code",2);
				map.put("info","用户名不存在");
			}else{//用户名存在
				if(!user.getPassword().equals(password)){//密码不正确
					map.put("code",3);
					map.put("info","密码不正确");
				}else{//用户名密码正确
					//将需要传递的数据存放在session域范围中，一个会话阶段的所有程序都可以从中获取
					session.setAttribute("currentUser", user);
					if("true".equals(request.getParameter("autologin"))){//免登录选中
						Cookie cookie1=new Cookie("userName",userName);
						Cookie cookie2=new Cookie("password",password);
						cookie1.setPath(request.getContextPath());
						cookie2.setPath(request.getContextPath());
						cookie1.setMaxAge(60*60*24*7);
						cookie2.setMaxAge(60*60*24*7);
						response.addCookie(cookie1);
						response.addCookie(cookie2);
					}
					map.put("code",0);
					map.put("info","登录成功");
				}
			}
		}
		//调用谷歌的Gson库将map类型数据转换为json字符串
		String jsonStr=new Gson().toJson(map);
		//字符流输出字符串
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonStr);
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
