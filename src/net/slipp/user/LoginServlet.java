package net.slipp.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;;

@WebServlet("/users/logn")
public class LoginServlet {
	private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		try{
			User.login(userId, password);
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			
			response.sendRedirect("/");
		} catch (UserNotFoundException e){
			forwardJSP(request, response,"존재하지 않는 사용자입니다");
		} catch(PasswordMismatchException e){
			forwardJSP(request, response, "비밀번호가 틀립니다");
		}
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}
}
