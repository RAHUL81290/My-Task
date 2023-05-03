<%@page import="com.Mytask.model.TodoModel"%>
<%@page import="com.Mytask.dao.TodoDao"%>
<%@page import="com.Mytask.dao.UserDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Mytask.model.CollectionModel"%>
<%@page import="java.util.List"%>
<%@page import="com.Mytask.dao.CollectionDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<CollectionModel> mycollection = new ArrayList<>();
List<TodoModel> myTasks = new ArrayList<>();
String userName = "";
response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
if (session.getAttribute("USER") == null) {
	response.sendRedirect("login.jsp");
} else {
	int userId = (int) session.getAttribute("USER");
	UserDao user = new UserDao();
	userName = user.getUserNameById(userId);
	CollectionDao collection = new CollectionDao();
	mycollection = collection.getAllCollectionById(userId);
	TodoDao todoDao = new TodoDao();
	myTasks = todoDao.getAllTodoByUid(userId);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<title>dashboard</title>
<style>
*::-webkit-scrollbar {
	display: none;
}

* {
	-ms-overflow-style: none; /* IE and Edge */
	scrollbar-width: none; /* Firefox */
}
</style>
</head>
<body>
	<div class="d-flex">
		<div style="height: 100vh; width: 300px;"
			class="bg-black bg-gradient ps-4 pe-4 pt-2">
			<h2 class="text-center text-white">My Task</h2>
			<div class="d-grid gap-2 mt-3">
				<a href="dashboard.jsp" class="btn btn-outline-primary active"
					type="button">Dashboard</a>
			</div>
			<div>
				<h6 class="text-white mt-4">Collections:</h6>
				<div style="height: 400px; width: 100%"
					class="bg-black bg-gradient mt-2 rounded p-3">
					<button type="button" class="btn btn-outline-primary mb-2"
						data-bs-toggle="modal" data-bs-target="#exampleModal">
						Add</button>
					<div style="height: 300px; width: 100%">
						<div style="max-height: 300px; border-radius: 0;"
							class="list-group overflow-auto">
							<%
							for (int i = 0; i < mycollection.size(); i++) {
							%>
							<form action="DynamicLink" method="post">
								<input type="hidden" value="<%=mycollection.get(i).getCid()%>"
									name=id> <input type="hidden"
									value="<%=mycollection.get(i).getTitle()%>" name=name>
								<button type="submit"
									class="list-group-item list-group-item-action list-group-item-light mt-2"><%=mycollection.get(i).getTitle()%></button>
							</form>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>
			<form action="Logout" method="get">
				<button class="btn btn-outline-light" type="submit">Logout</button>
			</form>
		</div>

		<div style="height: 100vh; width: 980px;" class="bg-white ps-2 pe-2">
			<h2>
				Welcome,
				<%=userName%>
			</h2>
			<div style="height: 550px; width: 960px;" class="ps-2 pe-2">
				<div class="row">
					<%
					double completed = 0;
					double total = 0;
					int per = 0;
					for (int i = 0; i < mycollection.size(); i++) {
						String cName = "";
						int cId = 0;
						completed = 0;
						total = 0;
						per = 0;
					%>
					<div class="col-sm-4 mb-3 mb-sm-0 pt-2">
						<div style="box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;"
							class="card">
							<div class="card-body">
								<h5 class="card-title"><%=mycollection.get(i).getTitle()%></h5>
								<%
								for (int j = 0; j < myTasks.size(); j++) {
								%>
								<%
								if (myTasks.get(j).getCid() == mycollection.get(i).getCid()) {
									cName = mycollection.get(i).getTitle();
									cId = mycollection.get(i).getCid();
									total++;
								%>
								<%
								if (myTasks.get(j).isCompleted()) {
									completed++;
								%>
								<span class="badge text-bg-success"><%=myTasks.get(j).getTitle()%></span>
								<%
								} else {
								%>
								<span class="badge text-bg-secondary"><%=myTasks.get(j).getTitle()%></span>
								<%
								}
								%>
								<%
								}
								%>
								<%
								}
								per = (int) ((completed / total) * 100);
								%>
								<p>
									<a href="todo.jsp?id=<%=cId%>&name=<%=cName%>"
										class="btn btn-primary mt-4">Go to <%=cName%></a>
								</p>
								<div class="progress" role="progressbar"
									aria-label="Animated striped example" aria-valuenow="75"
									aria-valuemin="0" aria-valuemax="100">
									<div
										class="progress-bar progress-bar-striped
  <%if (per < 35) {%>
  bg-danger
  <%} else if (per >= 35 && per < 80) {%>
  bg-warning
  <%} else {%>
  bg-success
  <%}%> 
  progress-bar-animated"
										style="width: <%=per%>%"></div>
								</div>

							</div>

						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>

	<!-- Button trigger modal -->

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="AddCollection" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Add
							Collection</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">

						<div class="form-floating mb-3">
							<input type="text" class="form-control" name="collectionTitle"
								id="floatingInput" placeholder="name@example.com" required>
							<label for="floatingInput">Title</label>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
</body>
</html>