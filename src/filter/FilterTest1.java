package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.User;
import dao.UserDao;

public class FilterTest1 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 将ServletRequest类型参数转换为HttpServletRequest类型
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
/*		String URL = "/exercise1/servlet/DownloadController;/exercise1/download.jsp;"
				+ "/exercise1/servlet/GetDownloadListController";*/
		// 1.用户未登录
		if (session.getAttribute("currentUser") == null) {
			request.setAttribute("info", "请先登录后再进行访问！");
			String forwardPath = "/error.jsp";
			request.getRequestDispatcher(forwardPath).forward(request, resp);
			;
		}
		// 2.用户已登录
		else {
			// 普通用户只允许访问普通用户的界面
			// 管理员允许访问普通用户和管理员的界面
			String url = request.getRequestURI();
			User user=(User) session.getAttribute("currentUser");
			UserDao dao=new UserDao();
			String URL=dao.Check(user.getUserName());
			if (!URL.contains(url)) {
				request.setAttribute("info", "您好，您是普通用户，不具备访问管理员界面的权限！");
				String forwardPath = "/error.jsp";
				request.getRequestDispatcher(forwardPath)
						.forward(request, resp);
				;
			} else {
				chain.doFilter(req, resp);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
