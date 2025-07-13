<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo App</title>
    <style>
        body {
            background: #f0f4f8;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .container {
            background: #fff;
            padding: 2rem 3rem;
            border-radius: 12px;
            box-shadow: 0 4px 24px rgba(0,0,0,0.08);
            text-align: center;
        }
        h1 {
            color: #2d6cdf;
            margin-bottom: 1.5rem;
        }
        form {
            margin-bottom: 2rem;
        }
        input[type="text"] {
            padding: 0.5rem 1rem;
            border: 1px solid #b0b8c1;
            border-radius: 6px;
            width: 220px;
            font-size: 1rem;
        }
        button {
            padding: 0.5rem 1.2rem;
            background: #2d6cdf;
            color: #fff;
            border: none;
            border-radius: 6px;
            margin-left: 0.5rem;
            font-size: 1rem;
            cursor: pointer;
            transition: background 0.2s;
        }
        button:hover {
            background: #174ea6;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            background: #eaf1fb;
            margin: 0.5rem 0;
            padding: 0.7rem 1rem;
            border-radius: 6px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            font-size: 1.1rem;
        }
        .view-btn {
            background: #fff;
            color: #2d6cdf;
            border: 1px solid #2d6cdf;
            padding: 0.3rem 0.9rem;
            border-radius: 5px;
            font-size: 0.95rem;
            cursor: pointer;
            transition: background 0.2s, color 0.2s;
        }
        .view-btn:hover {
            background: #2d6cdf;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Todo List</h1>
        <form method="post">
            <input type="text" name="task" required placeholder="Enter a new todo..." />
            <button type="submit">Add</button>
        </form>
        <ul>
            <c:forEach var="todo" items="${todos}" varStatus="status">
                <li>
                    <span>${todo.task}</span>
                    <button class="view-btn" type="button" data-task="${fn:escapeXml(todo.task)}" onclick="showTodoDetail(this.getAttribute('data-task'))">View</button>
                </li>
            </c:forEach>
        </ul>
        <div id="todo-detail" style="display:none; margin-top:2rem; padding:1rem; background:#eaf1fb; border-radius:8px; text-align:left;"></div>
    </div>
    <script>
        function showTodoDetail(task) {
            var detailDiv = document.getElementById('todo-detail');
            detailDiv.style.display = 'block';
            detailDiv.innerHTML = '<strong>Todo Details:</strong><br><span>' + task + '</span>';
        }
    </script>
</body>
</html>