package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChristmasCountdown")
public class ChristmasCountdownServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LocalDate today = LocalDate.now();
		LocalDate christmas = LocalDate.of(today.getYear(), 12, 25);
		long toChristmas = ChronoUnit.DAYS.between(today, christmas);
		req.setAttribute("daysTillChristmas", toChristmas);

		// forward the request to the index.jsp page
		req.getRequestDispatcher("/WEB-INF/ChristmasCountdown.jsp").forward(req, resp);
	}
}
