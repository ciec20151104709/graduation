package filter.manager;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		req.getSession();

		String path = req.getContextPath();
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path;
		HttpSession session = req.getSession(true);

		String userid = (String) session.getAttribute("m_userid");
		// 判断是否为登请求，如是，则直接访问
		String url = req.getRequestURL().toString();
		if (url.indexOf("manager/login_page") > -1 || url.indexOf("manager/login_check") > -1) {
			arg2.doFilter(req, resp);
		} else {
			// 非登录请求，需要判断是否已登录
			if (userid == null || "".equals(userid.trim())) {
				resp.setHeader("Cache-Control", "no-store");
				resp.setDateHeader("Expires", 0);
				resp.setHeader("Prama", "no-cache");
				resp.sendRedirect(basePath + "/manager/login_page");
			} else {
				arg2.doFilter(req, resp);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
