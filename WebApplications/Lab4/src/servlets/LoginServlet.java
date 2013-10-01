package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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

		if (username.equals("username") && password.equals("passw0rd")) {
			
			 HttpSession session = request.getSession(true);
			 session.setAttribute("authenticated", new Boolean(true));
			 session.setMaxInactiveInterval(60);

			// define strings for the type of the document
			String docType = "<!DOCTYPE html>";
			String html = "<html>\n";

			// get the output stream to send response back to the client
			PrintWriter output = response.getWriter();

			// set the response type to be HTML
			response.setContentType("text/html");

			// write the response document to the user
			output.println(docType);
			output.println(html);
			output.println("<head><title>");
			output.println("A Response From a Servlet");
			output.println("</title></head>");
			output.println("<body>");
			output.println("<h1>Congratulations!</h1>");
			output.println("</body>");
			output.println("</html>\n");

			// close the connection to the client
			output.close();
		}

		else {
			response.sendError(403,
					"The username or password you entered is incorrect. Please Try Again.");
		}
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
