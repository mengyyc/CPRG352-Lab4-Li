/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Note;

/**
 *
 * @author lixia
 */
@WebServlet(name = "NoteServlet", urlPatterns = {"/note"})
public class NoteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		boolean paramContainEdit = false;
		// Get all parameters
		Enumeration paramList = request.getParameterNames();
		while (paramList.hasMoreElements()) {
			if (paramList.nextElement().equals("edit")) {
				paramContainEdit = true;
			}
		}

		String path = getServletContext().getRealPath("/WEB-INF/note.txt");
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));

		String title = br.readLine();
		String content = br.readLine();
		br.close();

		Note note = new Note(title, content);

		request.setAttribute("note", note);
		// If parameters contain edit send it to Edit Page
		if (paramContainEdit) {
			getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
			return;
		}

		getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		for (char l : content.toCharArray()) {
			System.out.print(l);
		}
		content = content.replace("\n", "<br>");
		Note note = new Note(title, content);
		request.setAttribute("note", note);
		
		String path = getServletContext().getRealPath("/WEB-INF/note.txt");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path,false)));
		pw.write(title);
		pw.println();
		pw.write(content);
		pw.flush();
		pw.close();

		getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
	}

}
