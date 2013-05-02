package com.statistics.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author David Mekala
 * 
 * Servlet to calculate median and average.
 * Servlet implementation class StatisticsCalculatorServlet
 */
public class StatisticsCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * get method to calculate average and median
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String inputText = request.getParameter("inputText");
		String[] inputArray = null;
		
		try {
			//check if the input is empty
			if (inputText != null && !"".equals(inputText.trim())) {
				inputArray = inputText.split(",");
			} else {
				forwardToViewWithError("Input Cannot be empty", request,
						response);
			}
			
			//converts to list of Integers
			List<Integer> inputList = convertStringArrayToList(inputArray,
					request, response);
			Double average = calculateAverage(inputList); //calculate average
			Double median = calculateMedian(inputList);		//calculate median

			request.setAttribute("average", average);
			request.setAttribute("median", median);

			RequestDispatcher rd = request.getRequestDispatcher("input.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			// do nothing as it is already passing the error message back.
		}

	}
	/**
	 * Calculates median
	 * if the number of items in the list is odd then the median
	 * is the middle one else it is the average of the middle 2 numbers
	 * @param inputList
	 * @return median
	 */
	private Double calculateMedian(List<Integer> inputList) {
		Double median = 0.0;
		Collections.sort(inputList); 					//sorting the input
		
		if (inputList.size() % 2 == 0) {
			int midindex = inputList.size() / 2;
			median = (double) (((double)inputList.get(midindex) + (double)inputList
					.get(midindex - 1)) / 2);
		} else {
			median = (double) inputList.get((inputList.size() - 1) / 2);
		}

		return median;
	}
	/**
	 * Calculates Average.
	 * 
	 * it is the sum of all the numbers divided by the number of inputs.
	 * 
	 * @param inputList
	 * @return average
	 */
	private Double calculateAverage(List<Integer> inputList) {
		Double average = 0.0;

		for (Iterator<Integer> itr = inputList.iterator(); itr.hasNext();) {
			average += itr.next();
		}

		return (average == 0) ? 0 : (average / inputList.size());
	}
	/**
	 * Converts Input array of strings to list of integers
	 * It also does the validation of input. If it is not 5 digit integers
	 * sends an error message to the view page
	 * 
	 * @param inputArray
	 * @param request
	 * @param response
	 * @return list of Integrs
	 * @throws Exception
	 */
	private List<Integer> convertStringArrayToList(String[] inputArray,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Integer> intList = new ArrayList<Integer>();
		try {
			for (String input : inputArray) {
				if (input != null && input.length() == 5) {
					intList.add(new Integer(input));
				} else {
					throw new Exception("Please enter 5 digit numbers");
				}
			}
		} catch (NumberFormatException e) {
			forwardToViewWithError("Only Integers are allowed as input",
					request, response);
		} catch (Exception e) {
			forwardToViewWithError(e.getMessage(), request, response);
			e.printStackTrace();
		}
		return intList;
	}

	private void forwardToViewWithError(String error,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("error", error);
		RequestDispatcher rd = request.getRequestDispatcher("input.jsp");
		rd.forward(request, response);
		throw new Exception();
	}

	/**
	 * Forwarding the request and response to doGet method
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
