package com;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ProductsAPI")
public class ProductsAPI extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Products userObj;   
    
    public ProductsAPI() 
    {
        super();
        userObj = new Products();
        
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String output = userObj.insertProducts(
				request.getParameter("ProductName"),
				request.getParameter("ResearcherName"),
				request.getParameter("ProductDescription"),
				request.getParameter("ProductType"));
		response.getWriter().write(output);
	}

	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map<String, String> paras = getParasMap(request);
		
		String output = userObj.updateProducts(paras.get("hidProductIdSave").toString(),
				paras.get("ProductName").toString(),
				paras.get("ResearcherName").toString(),
				paras.get("ProductDescription").toString(), 
				paras.get("ProductType").toString());
		response.getWriter().write(output);
		
	}

	
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map<String, String> paras = getParasMap(request);
		String output = userObj.deleteProducts(paras.get("ProductId").toString());
		response.getWriter().write(output);
		/*Map paras = getParasMap(request);
		String output = userObj.deleteProducts(paras.get("ProductId").toString());
		response.getWriter().write(output);*/
		
	}
	
	
	// Convert request parameters to a Map
		private static Map<String,String> getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
			
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
									scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				
				String[] params = queryString.split("&");
				for (String param : params)
				{
					String[] p = param.split("=");
					map.put(p[0], java.net.URLDecoder.decode(p[1], StandardCharsets.UTF_8.name()));
				}
			}
			catch (Exception e)
			{
			}
			return map;
		}

}
