package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("pwd");
		String username = request.getParameter("unm");
		String message = null;

		if (password.equals("") || password.equals(null)) {
			message = "Must enter a password.";
		}

		else if (username.equals("") || username.equals(null)) {
			message = "Must enter a username.";
		}

		else {

			if (username.equals("username") && password.equals("passw0rd")) {

				HttpSession session = request.getSession(true);
				session.setAttribute("authenticated", new Boolean(true));
				session.setMaxInactiveInterval(60);

				message = "Congratulations!";
			}

			else {
				message = "Username or password isn't correct.";
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
