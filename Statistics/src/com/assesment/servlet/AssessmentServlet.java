package com.assesment.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sridevi Solletty
 * 
 * THis Servlet take the input from the jsp and calculates average and median.
 * 
 */
public class AssessmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * get method to calculate average and median
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String numbers = request.getParameter("numbers");
		
		List<String> numbersList = Arrays.asList(numbers.split("\n"));
		List<Integer> listOfNumbers = new ArrayList<Integer>();
		for(Iterator<String> itr = numbersList.iterator();itr.hasNext();){
			listOfNumbers.add(new Integer(itr.next().trim()));
		}
		
		Collections.sort(listOfNumbers);
		int sum = 0;
		for(int i=0;i<listOfNumbers.size();i++){
			sum += listOfNumbers.get(i);
		}
		
		double median = 0;
		
		if(listOfNumbers.size() %2 ==0){
			median = listOfNumbers.get(listOfNumbers.size()/2);
			median += listOfNumbers.get((listOfNumbers.size()/2)-1);
			median = median/2;
		}else{
			median = listOfNumbers.get((listOfNumbers.size()-1)/2);
		}
		
		request.setAttribute("median", new Double(sum/listOfNumbers.size()));
		request.setAttribute("average", new Double(sum/listOfNumbers.size()));
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
			
	}

	/**
	 * Post to get
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
