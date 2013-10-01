package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implements a survey of favorite pets
 */
@WebServlet(urlPatterns = "/SurveyServlet", initParams = { @WebInitParam(name = "surveyFile", value = "survey.dat") })

public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String animalNames[] = { "dog", "cat", "bird", "snake", "fish",
			"other", "none" };

	private int votesForAnimals[];

	private int totalVotes = 0;

	private File surveyData;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SurveyServlet() {
		super();
	}

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		// read current response (that caused this to run)
		String user_value = request.getParameter("animal");
		totalVotes++;

		// determine which was selected and update the total
		for (int x = 0; x < animalNames.length; x++) {
			if (user_value.equals(animalNames[x])) {
				votesForAnimals[x]++;
				break;
			}
		}

		// determine percentages
		double percentages[] = new double[votesForAnimals.length];
		for (int x = 0; x < percentages.length; x++)
			percentages[x] = 100.0 * votesForAnimals[x] / totalVotes;

		// now send a thanks to the client and the results
		response.setContentType("text/html");
		String docType = "<!DOCTYPE html>";
		String html = "<html>\n";

		// get the output stream for the client
		PrintWriter clientOutput = response.getWriter();

		// generate the response document
		clientOutput.println(docType);
		clientOutput.println(html);
		clientOutput.println("<head><title>Thank You!</title></head>");
		clientOutput.println("<body>");
		clientOutput.println("<p>Thank you for your input!</p>");
		clientOutput.println("<h3>Results:</h3>");
		DecimalFormat twoDigits = new DecimalFormat("#0.00");
		clientOutput.println("<table>");
		clientOutput.println("<tr><th>");
		clientOutput.println("Animal Name" + "</th><td>");
		clientOutput.println("Number of Responses" + "</td><td>"
				+ "% of Responses " + "</td>");
		for (int x = 0; x < percentages.length; x++) {
			clientOutput.println("<tr><th>");
			clientOutput.println(animalNames[x] + "</th><td>");
			clientOutput.println(twoDigits.format(percentages[x]));
			clientOutput.println("</td><td>" + votesForAnimals[x]
					+ "</td></tr>");
		}
		clientOutput.println("</table>");
		clientOutput.println("<p><strong>Total Responses:</strong> ");
		clientOutput.println(totalVotes + "</p>");
		clientOutput.println("</body>\n</html>");

		clientOutput.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
		super.destroy();
		// write updated total to the file
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(surveyData));

			out.writeObject(votesForAnimals);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();
		surveyData = new File(getInitParameter("surveyFile"));
		if (surveyData.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(surveyData));
				votesForAnimals = (int[]) in.readObject();
				in.close();
				for (int x = 0; x < votesForAnimals.length; x++){
					totalVotes += votesForAnimals[x];
				}
			} catch (ClassNotFoundException exp) {
				System.err.println("Error reading in file" + surveyData);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			votesForAnimals = new int[animalNames.length];
		}
	}

}
