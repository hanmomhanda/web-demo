package servlet;

import helper.JSONMapHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.BookSearchAPI;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		Map<String, String> jsonMap = JSONMapHelper.generateJSONMap(request
				.getInputStream());

		String keyword = jsonMap.get("keyword");
		
		BookSearchAPI daumAPI = new BookSearchAPI(
				"http://apis.daum.net/search/book", "DAUM_SEARCH_DEMO_APIKEY",
				"json", "q");
		String result = daumAPI.getResult(keyword);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();				
		out.print(result);
	}
}
