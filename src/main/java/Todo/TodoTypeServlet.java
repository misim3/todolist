package Todo;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/TodoTypeServlet")
public class TodoTypeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String getType(String param) {
		if (param.equals("TODO"))
			return "DOING";
		else
			return "DONE";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Long id = Long.parseLong(request.getParameter("id"));
			String type = getType(request.getParameter("type"));
			TodoDao tododao = new TodoDao();
			TodoDto tododto = new TodoDto.Builder().id_(id).type_(type).build();
			response.setHeader("Content-Type", "text/plain");
			PrintWriter writer = response.getWriter();

			if (tododao.updateTodo(tododto) == 1) {
				writer.print("success");
			} else {
				writer.print("fail");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}