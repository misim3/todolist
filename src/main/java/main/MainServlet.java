package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import Todo.TodoDao;
import Todo.TodoDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		TodoDao dao = new TodoDao();
		List<TodoDto> list = dao.getTodos();
		List<TodoDto> todo_list = new ArrayList<>();
		List<TodoDto> doing_list = new ArrayList<>();
		List<TodoDto> done_list = new ArrayList<>();

		for (TodoDto todo : list) {
			switch (todo.getType()) {
			case "TODO": {
				todo_list.add(new TodoDto.Builder().id_(todo.getId()).name_(todo.getName())
						.regDate_(todo.getRegDate().split(" ")[0]).sqeuence_(todo.getSequence()).title_(todo.getTitle())
						.type_(todo.getType()).build());
				break;
			}
			case "DOING": {
				doing_list.add(new TodoDto.Builder().id_(todo.getId()).name_(todo.getName())
						.regDate_(todo.getRegDate().split(" ")[0]).sqeuence_(todo.getSequence()).title_(todo.getTitle())
						.type_(todo.getType()).build());
				break;
			}
			case "DONE": {
				done_list.add(new TodoDto.Builder().id_(todo.getId()).name_(todo.getName())
						.regDate_(todo.getRegDate().split(" ")[0]).sqeuence_(todo.getSequence()).title_(todo.getTitle())
						.type_(todo.getType()).build());
				break;
			}
			}
		}
		request.setAttribute("todo_list", todo_list);
		request.setAttribute("doing_list", doing_list);
		request.setAttribute("done_list", done_list);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
	}
}