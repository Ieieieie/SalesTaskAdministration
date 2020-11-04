package controller;

import java.io.IOException;

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
@WebServlet("/TaskInputExecute")
public class TaskInputExecute extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public TaskInputExecute() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if(userInfoOnSession == null) {
			response.sendRedirect("./views/Login.jsp");
		}
		int employeeNumber = userInfoOnSession.getEmployeeNumber();
		String address = request.getParameter("zip11")+request.getParameter("addr11");
		String visitedName = request.getParameter("visited_name");
		String interviewer = request.getParameter("interviewer");
		int result = Integer.parseInt(request.getParameter("result"));
		String memo = request.getParameter("memo").replaceAll("\n", "<br/>");

		TaskInputDto taskDto = new TaskInputDto();
		taskDto.setEmployeeNumber(employeeNumber);
		taskDto.setAddress(address);
		taskDto.setVisitedName(visitedName);
		taskDto.setInterviewer(interviewer);
		taskDto.setResult(result);
		taskDto.setMemo(memo);

		BusinessLogic logic = new BusinessLogic();
		boolean inputSuccess = logic.doTaskInput(taskDto);

		if(inputSuccess) {
			response.sendRedirect(request.getContextPath()+"/htmls/success.html");
		}else {
			response.sendRedirect(request.getContextPath()+"/htmls/errot.html");
		}
	}

}
