package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BusinessLogic;
import model.TaskInputDto;
import model.UserInfoDto;

/**
 * Servlet implementation class TaskInputExcute
 */
@WebServlet("/SearchName")
public class SearchName extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SearchName() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		int employeeNumber = userInfoOnSession.getEmployeeNumber();
		String searchName = request.getParameter("search_name");

		BusinessLogic logic = new BusinessLogic();
		List<TaskInputDto> list = new ArrayList<>();
		list =logic.doSearchName(employeeNumber, searchName);

		request.setAttribute("ShowTasks", list);

		RequestDispatcher dispatch = request.getRequestDispatcher("/views/TaskInput.jsp");

		dispatch.forward(request, response);
	}

}
