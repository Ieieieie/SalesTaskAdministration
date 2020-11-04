package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BusinessLogic;
import model.UserInfoDto;


@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public DoLogin() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			response.sendRedirect("ShowAllMessage");
		} else {
			String username   = request.getParameter("username");
			String password = request.getParameter("password");
			String sha256 = "";
//			try {
//	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	            byte[] result = digest.digest(password.getBytes());
//	            sha256 = String.format("%040x", new BigInteger(1, result));
//	        } catch (Exception e){
//	            e.printStackTrace();
//	        }

			BusinessLogic logic = new BusinessLogic();
			UserInfoDto   dto   = logic.checkUserInfo(username, password);

			if (dto.getUsername() != null) {
				session.setAttribute("LOGIN_INFO", dto);
				response.sendRedirect("./views/TaskInput.jsp");

			} else {
				System.out.println("失敗");
				response.sendRedirect(request.getContextPath()+"/htmls/errot.html");
			}
		}


	}

}
