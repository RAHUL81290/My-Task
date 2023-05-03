<%@page import="com.Mytask.dao.TodoDao"%>
<%@page import="com.Mytask.model.TodoModel"%>
<%@page import="com.Mytask.dao.UserDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Mytask.model.CollectionModel"%>
<%@page import="java.util.List"%>
<%@page import="com.Mytask.dao.CollectionDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<CollectionModel> mycollection = new ArrayList<>();
List<TodoModel>myTask=new ArrayList<>();
String userName = "";
int collectionId=0;
String collectionName="";
boolean isValidCid=false;
response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
if (session.getAttribute("USER") == null) {
	response.sendRedirect("login.jsp");
} else {
	int userId = (int) session.getAttribute("USER");
	UserDao user = new UserDao();
	userName = user.getUserNameById(userId);
	CollectionDao collection = new CollectionDao();
	mycollection = collection.getAllCollectionById(userId);
	if(request.getParameter("id")!=null&&request.getParameter("name")!=null)
	{
		collectionId=Integer.parseInt(request.getParameter("id"));
		collectionName=request.getParameter("name");
		for(int i=0;i<mycollection.size();i++)
		{
			if(collectionId==mycollection.get(i).getCid()&&collectionName.equals(mycollection.get(i).getTitle())){
				isValidCid=true;
			}
		}
		if(isValidCid)
		{
			TodoDao todoDao=new TodoDao();
			myTask=todoDao.getTodoById(collectionId);
		}
		else{
			response.sendRedirect("dashboard.jsp");
		}
	}
	else{
		response.sendRedirect("dashboard.jsp");
	}
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
*{
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
</style>

</head>
<body>
	<div class="d-flex">
		<div style="height: 100vh; width: 300px;"
			class="bg-black bg-gradient ps-4 pe-4 pt-2">
			<h2 class="text-center text-white">My Task</h2>
			<div class="d-grid gap-2 mt-3">
				<a href="dashboard.jsp" class="btn btn-outline-primary" type="button">Dashboard</a>
			</div>
			<div>
				<h6 class="text-white mt-4">Collections:</h6>
				<div style="height: 400px; width: 100%"
					class="bg-black bg-gradient mt-2 rounded p-3">
					<button type="button" class="btn btn-outline-primary mb-2"
						data-bs-toggle="modal" data-bs-target="#exampleModal1">
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
								<%
								if (mycollection.get(i).getCid() == collectionId) {
								%>
								<button type="submit"
									class="list-group-item list-group-item-action list-group-item-light mt-2 active"><%=mycollection.get(i).getTitle()%></button>
								<%
								} else {
								%>
								<button type="submit"
									class="list-group-item list-group-item-action list-group-item-light mt-2"><%=mycollection.get(i).getTitle()%></button>
								<%
								}
								%>
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





		<div style="height: 100vh; width: 980px;"
			class="bg-white ps-4 pe-4 pt-3">
			<div class="d-flex justify-content-between mb-2">
				<h2><%=request.getParameter("name")%></h2>
				<div style="height: 50%;"
					class="d-flex justify-content-center align-items-center">
					<button type="button" class="btn btn-primary me-2"
						data-bs-toggle="modal" data-bs-target="#exampleModal">Add
						Task</button>

					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal2">
						Delete
						<%=collectionName%></button>
				</div>
			</div>
			<div style="height: 40px; width: 500px;" class="d-flex">
				<input style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;"
					class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-dark" type="submit">Search</button>
			</div>

			<div class="mt-4 d-flex justify-content-between">
			
			<!-- tasks start -->
				<div
					style="height: 450px; width: 300px; box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;"
					class="rounded p-2 bg-body-secondary">
					<h5>Tasks</h5>
					<div style="max-height: 400px;"
						class="overflow-auto ps-2 pe-2 pb-2">
						<%
						for (int i = 0; i < myTask.size(); i++) {
						%>
						<div
							class="card 
						<%if (myTask.get(i).isCompleted()) {%>
						text-bg-light
						<%} else {%>
						text-bg-dark
						<%}%>
						mb-3"
							style="max-width: 18rem;">
							<div class="d-flex justify-content-between">
								<h5 class="card-header"><%=myTask.get(i).getTitle()%></h5>
								<div class="dropdown">
									<button class="btn btn-secondary btn-sm dropdown-toggle"
										type="button" data-bs-toggle="dropdown" aria-expanded="false">
										⚙️</button>
									<ul class="dropdown-menu">
										<%
										if (myTask.get(i).isCompleted() == false) {
										%>
										<li>
											<form action="CompleteTask" method="post">
												<input type="hidden" value="<%=myTask.get(i).getTid()%>"
													name="tId"> <input type="hidden"
													value="<%=collectionId%>" name="cId"> <input
													type="hidden" value="<%=collectionName%>" name="cName">
												<button type="submit" class="dropdown-item">Completed</button>
											</form>
										</li>
										<%
										}
										%>
										<li>
											<form action="UpdateTask" method="post">
												<input type="hidden" value="<%=myTask.get(i).getTid()%>"
													name="tId"> <input type="hidden"
													value="<%=collectionId%>" name="cId"> <input
													type="hidden" value="<%=collectionName%>" name="cName">
												<input type="hidden" value="<%=myTask.get(i).getTitle()%>"
													name="tTitle"> <input type="hidden"
													value="<%=myTask.get(i).getContent()%>" name="tContent">
												<button type="submit" class="dropdown-item">Edit</button>
											</form>
										</li>
										<li>
											<form action="DeleteTask" method="post">
												<input type="hidden" value="<%=myTask.get(i).getTid()%>"
													name="tId"> <input type="hidden"
													value="<%=collectionId%>" name="cId"> <input
													type="hidden" value="<%=collectionName%>" name="cName">
												<button type="submit" class="dropdown-item">Delete</button>
											</form>
										</li>
									</ul>
								</div>
							</div>
							<div class="card-body">
								<p class="card-text"><%=myTask.get(i).getContent() %></p>
							</div>
							<%
							if (myTask.get(i).isImportant()) {
							%>
							<div class="d-flex justify-content-start mb-1 ms-1">
								<span class="badge text-bg-primary">Important</span>
							</div>
							<%
							}
							%>
							<%
							if (myTask.get(i).isCompleted()) {
							%>
							<div class="d-flex justify-content-start mb-1 ms-1">
								<span class="badge text-bg-success">Completed</span>
							</div>
							<%
							}
							%>
						</div>
						<%
						}
						%>
					</div>
				</div>
				<!-- task end -->
				
				<!-- In progress start -->
				<div
					style="height: 450px; width: 300px; box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;"
					class="rounded p-2 bg-body-secondary">
					<h5>In Progress</h5>
					<div style="max-height: 400px;"
						class="overflow-auto ps-2 pe-2 pb-2">
						<%
						for (int i = 0; i < myTask.size(); i++) {
						%>
						<%if(myTask.get(i).isCompleted()==false){%>
						<div
							class="card 
						<%if (myTask.get(i).isCompleted()) {%>
						text-bg-light
						<%} else {%>
						text-bg-dark
						<%}%>
						mb-3"
							style="max-width: 18rem;">
							<div class="d-flex justify-content-between">
								<h5 class="card-header"><%=myTask.get(i).getTitle()%></h5>
								<div class="dropdown">
									<button class="btn btn-secondary btn-sm dropdown-toggle"
										type="button" data-bs-toggle="dropdown" aria-expanded="false">
										⚙️</button>
									<ul class="dropdown-menu">
										<li>
											<form action="CompleteTask" method="post">
												<input type="hidden" value="<%=myTask.get(i).getTid()%>"
													name="tId"> <input type="hidden"
													value="<%=collectionId%>" name="cId"> <input
													type="hidden" value="<%=collectionName%>" name="cName">
												<button type="submit" class="dropdown-item">Completed</button>
											</form>
										</li>
										<li>
											<form action="UpdateTask" method="post">
												<input type="hidden" value="<%=myTask.get(i).getTid()%>"
													name="tId"> <input type="hidden"
													value="<%=collectionId%>" name="cId"> <input
													type="hidden" value="<%=collectionName%>" name="cName">
												<input type="hidden" value="<%=myTask.get(i).getTitle()%>"
													name="tTitle"> <input type="hidden"
													value="<%=myTask.get(i).getContent()%>" name="tContent">
												<button type="submit" class="dropdown-item">Edit</button>
											</form>
										</li>
										<li>
											<form action="DeleteTask" method="post">
												<input type="hidden" value="<%=myTask.get(i).getTid()%>"
													name="tId"> <input type="hidden"
													value="<%=collectionId%>" name="cId"> <input
													type="hidden" value="<%=collectionName%>" name="cName">
												<button type="submit" class="dropdown-item">Delete</button>
											</form>
										</li>
									</ul>
								</div>
							</div>
							<div class="card-body">
								<p class="card-text"><%=myTask.get(i).getContent() %></p>
							</div>
							<%
							if (myTask.get(i).isImportant()) {
							%>
							<div class="d-flex justify-content-start mb-1 ms-1">
								<span class="badge text-bg-primary">Important</span>
							</div>
							<%
							}
							%>
							<%
							if (myTask.get(i).isCompleted()) {
							%>
							<div class="d-flex justify-content-start mb-1 ms-1">
								<span class="badge text-bg-success">Completed</span>
							</div>
							<%
							}
							%>
						</div>
						<%} %>
						<%
						}
						%>
					</div>
				</div>
				<!-- In progress end -->
				
				<!-- completed start -->
				<div
					style="height: 450px; width: 300px; box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;"
					class="rounded p-2 bg-body-secondary">
					<h5>Completed</h5>
					<div style="max-height: 400px;"
						class="overflow-auto ps-2 pe-2 pb-2">
						<%
						for (int i = 0; i < myTask.size(); i++) {
						%>
						<%if(myTask.get(i).isCompleted()){ %>
						<div
							class="card 
						<%if (myTask.get(i).isCompleted()) {%>
						text-bg-light
						<%} else {%>
						text-bg-dark
						<%}%>
						mb-3"
							style="max-width: 18rem;">
							<div class="d-flex justify-content-between">
								<h5 class="card-header"><%=myTask.get(i).getTitle()%></h5>
								<div class="dropdown">
									<button class="btn btn-secondary btn-sm dropdown-toggle"
										type="button" data-bs-toggle="dropdown" aria-expanded="false">
										⚙️</button>
									<ul class="dropdown-menu">
										<li>
											<form action="UpdateTask" method="post">
												<input type="hidden" value="<%=myTask.get(i).getTid()%>"
													name="tId"> <input type="hidden"
													value="<%=collectionId%>" name="cId"> <input
													type="hidden" value="<%=collectionName%>" name="cName">
												<input type="hidden" value="<%=myTask.get(i).getTitle()%>"
													name="tTitle"> <input type="hidden"
													value="<%=myTask.get(i).getContent()%>" name="tContent">
												<button type="submit" class="dropdown-item">Edit</button>
											</form>
										</li>
										<li>
											<form action="DeleteTask" method="post">
												<input type="hidden" value="<%=myTask.get(i).getTid()%>"
													name="tId"> <input type="hidden"
													value="<%=collectionId%>" name="cId"> <input
													type="hidden" value="<%=collectionName%>" name="cName">
												<button type="submit" class="dropdown-item">Delete</button>
											</form>
										</li>
									</ul>
								</div>
							</div>
							<div class="card-body">
								<p class="card-text"><%=myTask.get(i).getContent() %></p>
							</div>
							<%
							if (myTask.get(i).isImportant()) {
							%>
							<div class="d-flex justify-content-start mb-1 ms-1">
								<span class="badge text-bg-primary">Important</span>
							</div>
							<%
							}
							%>
							<%
							if (myTask.get(i).isCompleted()) {
							%>
							<div class="d-flex justify-content-start mb-1 ms-1">
								<span class="badge text-bg-success">Completed</span>
							</div>
							<%
							}
							%>
						</div>
						<%} %>
						<%
						}
						%>
					</div>
				</div>
				<!-- completed end -->
			</div>
			<!-- task, in progress,completed  flex end -->
		</div>


	</div>



























		
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
  <form action="AddTask" method="post">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Add Task</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
				<div class="modal-body">
					<div class="form-floating mb-3">
						<input type="text" class="form-control" name="title" id="floatingInput"
							placeholder="name@example.com" required> <label
							for="floatingInput">Title</label>
					</div>
					<div class="form-floating mb-3">
						<textarea class="form-control" name="content" placeholder="Leave a comment here"
							id="floatingTextarea2" style="height: 100px" required></textarea>
						<label for="floatingTextarea2">Discription</label>
					</div>
					<input type="checkbox" class="btn-check" name="toggle"
						id="btn-check-outlined" autocomplete="off"> <label
						class="btn btn-outline-primary" for="btn-check-outlined">Important</label><br>
					<input type="hidden" value="<%=collectionId %>" name="collectionId">
					<input type="hidden" value="<%=collectionName %>" name="cName">						

				</div>
				<div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save</button>
      </div>
    </div>
    </form>
  </div>
</div>

<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
  <form action="AddCollection" method="post">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Add Collection</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="form-floating mb-3">
						
						<div class="form-floating mb-3">
							<input type="text" class="form-control" name="collectionTitle"
								id="floatingInput" placeholder="name@example.com" required>
							<label for="floatingInput">Title</label>
						</div>
					
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save</button>
      </div>
    </div>
    </form>
  </div>
</div>
<!-- Button trigger modal -->

<!-- Modal -->
<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
  <form action="DeleteCollection" method="post">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Delete</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        are you sure you want to delete <span class=text-danger><%=collectionName %></span>?
        <input type="hidden" value="<%=collectionId %>" name="cId">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-danger">ok</button>
      </div>
    </div>
    </form>
  </div>
</div>
<%if(request.getAttribute("UPDATE")=="YES"){ %>
<div style=display:block; class="modal" tabindex="-1">
  <div class="modal-dialog ">
  <form action="UpdateTaskMain" method="post">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Update</h5>
        <a href="todo.jsp?id=<%=collectionId%>&name=<%=collectionName%>" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></a>
      </div>
				<div class="modal-body">
					<div class="form-floating mb-3">
						<input type="text" class="form-control" name="title"
							value="<%=request.getAttribute("TASKTITLE") %>" id="floatingInput" placeholder="name@example.com" required>
						<label for="floatingInput">Title</label>
						<input type="hidden" value="<%=request.getAttribute("TASKID")%>" name="tId">
						<input type="hidden" value="<%=collectionId%>" name="cId">
						<input type="hidden" value="<%=collectionName%>" name="cName">
					</div>
					<div class="form-floating mb-3">
						<textarea class="form-control" name="content"
							placeholder="Leave a comment here" id="floatingTextarea2"
							style="height: 100px" required><%=request.getAttribute("TASKCONTENT") %></textarea>
						<label for="floatingTextarea2">Discription</label>
					</div>
				</div>
				<div class="modal-footer">
        <a href="todo.jsp?id=<%=collectionId%>&name=<%=collectionName%>" class="btn btn-secondary" data-mdb-dismiss="modal">Close</a>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
    </div>
    </form>
  </div>
</div>
<%} %>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
</body>
</html>