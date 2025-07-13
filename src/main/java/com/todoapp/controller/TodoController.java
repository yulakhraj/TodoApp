package com.todoapp.controller;

import com.todoapp.model.Todo;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class TodoController extends HttpServlet {
    private static final ArrayList<Todo> todos = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("todos", todos);
        req.getRequestDispatcher("/WEB-INF/todo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String task = req.getParameter("task");
        if (task != null && !task.trim().isEmpty()) {
            todos.add(new Todo(task));
        }
        doGet(req, resp);
    }
}