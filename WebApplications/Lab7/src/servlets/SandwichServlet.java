package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SandwichServlet
 */
@WebServlet("/SandwichServlet")
public class SandwichServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SandwichServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name == ""){
			name = "Anonymous";
		}
		String bread = request.getParameter("bread");
		String base = request.getParameter("base");
		if(base == null){
			base = "Plain";
		}
		String cheese = request.getParameter("cheese");
		if (cheese == null){
			cheese = "no cheese";
		}
		String[] condiments = null;
		boolean empty = false;
		if (request.getParameter("condiment") != null){
			condiments = request.getParameterValues("condiment");
		}
		else{
			empty = true;
		}
		String location = request.getParameter("location");
		if(location == null){
			location = "Unspecified";
		}
		String notes = request.getParameter("notes");
		
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
		output.println("<h1>" + name + "'s Order</h1>");
		output.println("<p>" + location + "</p>");
		output.println("<p>" + base + " sandwich on " + bread + " with " + cheese + "</p>");
		output.println("<p>");
		if (!empty){
		for(int i = 0; i < condiments.length; i++){
			output.print(condiments[i] + " ");
		}
		}
		else{
			output.print("Plain");
		}
		output.println("</p>");
		output.println("<p> User Comments:" + notes + "</p>");

		output.println("</body>");
		output.println("</html>\n");

		// close the connection to the client
		output.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
