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
 * Servlet implementation class ShowAllTasks
 */
@WebServlet("/ShowAllTasks")
public class ShowAllTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllTasks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		int employeeNumber = userInfoOnSession.getEmployeeNumber();

		BusinessLogic logic = new BusinessLogic();
		List<TaskInputDto> list = new ArrayList<>();
		list =logic.doShowAllTasks(employeeNumber);

		request.setAttribute("ShowTasks", list);

		RequestDispatcher dispatch = request.getRequestDispatcher("/views/TaskInput.jsp");

		dispatch.forward(request, response);
	}

}
