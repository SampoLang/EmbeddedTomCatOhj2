package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/daysUntil")
public class daysUntil extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PAIVALASKURI SERVLETISSÄ");
		LocalDate today = LocalDate.now();
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"));
		int day = Integer.parseInt(req.getParameter("day"));
		LocalDate compareDate = LocalDate.of(year, month, day);
		long daysBetweenDates = ChronoUnit.DAYS.between(today, compareDate);
		System.out.println(daysBetweenDates);
		if (daysBetweenDates < 0) {
			System.out.println("isompi");
			req.setAttribute("arrow", "&#9194");
			req.setAttribute("image",
					"https://historyofliverpool.com/wp-content/uploads/2021/02/Stone-Age-Liverpool-70.jpg");
		} else {
			req.setAttribute("arrow", "&#9193");
			req.setAttribute("image", "https://i.pinimg.com/474x/bc/c3/29/bcc3297acbd6f25d750a6c8f42e2679f.jpg");
		}
		req.setAttribute("daysBetweenDates", daysBetweenDates);
		req.setAttribute("futureDate", compareDate);
		req.getRequestDispatcher("/WEB-INF/ShowDaysBetween.jsp").forward(req, resp);
	}
}
