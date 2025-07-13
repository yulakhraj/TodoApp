<!DOCTYPE html>
<html>
<head>
    <title>Todo App</title>
</head>
<body>
    <h1>Todo List</h1>
    <form method="post">
        <input type="text" name="task" required />
        <button type="submit">Add</button>
    </form>
    <ul>
        <c:forEach var="todo" items="${todos}">
            <li>${todo.task}</li>
        </c:forEach>
    </ul>
</body>
</html>