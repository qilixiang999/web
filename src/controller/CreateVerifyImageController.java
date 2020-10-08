package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CreateImage;

//@WebServlet(urlPatterns="/servlet/CreateVerifyImageController")
public class CreateVerifyImageController extends HttpServlet {

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
		
		//把验证码图片生成的过程封装在CreateImage类中
		CreateImage createVCodeImage=new CreateImage();
		//产生四位随机字符
		String vCode=createVCodeImage.createCode();
		//获取HttpSession对象
		HttpSession session=request.getSession();
		//将产生的四位随机字符串存放于session中(存放在session中的数据在一个会话范围内，多个程序全局共享)，以便验证
		session.setAttribute("verify", vCode);
		//设置返回的内容
		response.setContentType("img/jpeg");
		//浏览器不缓存响应内容--验证码图片，点一次就要刷新一次，所以不能有缓存出现
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		//设置验证码失效时间
		response.setDateHeader("Expires", 0);
		//以字节流发过去，交给img的src属性去解析即可
		BufferedImage image=createVCodeImage.CreateImage(vCode);
		//获取字节流输出对象
		ServletOutputStream out=response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.flush();
		out.close();
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
/*
		//把验证码图片生成的过程封装在CreateImage类中
		CreateImage createVCodeImage=new CreateImage();
		//产生四位随机字符
		String vCode=createVCodeImage.createCode();
		//获取HttpSession对象
		HttpSession session=request.getSession();
		//将产生的四位随机字符串存放于session中(存放在session中的数据在一个会话范围内，多个程序全局共享)，以便验证
		session.setAttribute("verify", vCode);
		//设置返回的内容
		response.setContentType("img/jpeg");
		//浏览器不缓存响应内容--验证码图片，点一次就要刷新一次，所以不能有缓存出现
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		//设置验证码失效时间
		response.setDateHeader("Expires", 0);
		//以字节流发过去，交给img的src属性去解析即可
		BufferedImage image=createVCodeImage.CreateImage(vCode);
		//获取字节流输出对象
		ServletOutputStream out=response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.flush();
		out.close();*/
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
