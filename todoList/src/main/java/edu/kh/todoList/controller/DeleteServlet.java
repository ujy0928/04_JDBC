package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.service.TodoListService;
import edu.kh.todoList.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int todoNo = Integer.parseInt(req.getParameter("todoNo"));
		
		try {
			TodoListService service = new TodoListServiceImpl();
			
			int result = service.todoDelete(todoNo);
			String message = null;
			
			if(result > 0) {
				message = "할 일이 삭제 되었습니다";
			}
			else {
				message = "todo가 존재하지 않습니다";
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			resp.sendRedirect("/");
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
