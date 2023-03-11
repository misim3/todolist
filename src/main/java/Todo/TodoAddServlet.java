package Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/TodoAddServlet")
public class TodoAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String getCurrentTime() {
		LocalDateTime now = LocalDateTime.now(); // 객체 생성&저장
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(now);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			request.setCharacterEncoding("utf-8");
			String work_input = request.getParameter("work_input");
			String who_input = request.getParameter("who_input");
			int priority = Integer.parseInt(request.getParameter("priority").substring(0, 1));
			TodoDao tododao = new TodoDao();
			tododao.addTodo(new TodoDto.Builder().title_(work_input).name_(who_input).sqeuence_(priority).type_("TODO")
					.regDate_(getCurrentTime()).build());
			response.sendRedirect("/mavenweb/MainServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}