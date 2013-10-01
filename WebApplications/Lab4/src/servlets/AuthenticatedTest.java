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
 * Servlet implementation class AuthenticatedTest
 */
@WebServlet("/AuthenticatedTest")
public class AuthenticatedTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticatedTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get session
		HttpSession session = request.getSession();
		// get session attribute for authentication
		Boolean authenticated = (Boolean) session.getAttribute("authenticated");

		response.setContentType("text/html");
		String docType = "<!DOCTYPE html>";
		String html = "<html\">\n";
		PrintWriter clientOutput = response.getWriter();

		// generate the response document
		clientOutput.println(docType);
		clientOutput.println(html);

		if (authenticated == null ) {
			clientOutput.println("<head><title>Not Authenticated</title></head>");
			clientOutput.println("<body style=\"font-family:sans-serif\">");
			clientOutput.println("<h1>Not Authenticated</h1>");
			clientOutput.println("<p>You are not supposed to be here.  You must login first.</p>");
			clientOutput.println("</body>");
			clientOutput.println("</html>");
		}
		else {
			clientOutput.println("<head><title>Authenticated!</title></head>");
			clientOutput.println("<body style=\"font-family:sans-serif\">");
			clientOutput.println("<h1>Authenticated!</h1>");
			clientOutput.println("<p>Welcome to the promised land!: <br/><img src=\"beach.jpg\"/></p>");
			clientOutput.println("</body>");
			clientOutput.println("</html>");

		}

		clientOutput.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
