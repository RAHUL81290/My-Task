<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/LoginPage.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&family=Open+Sans:wght@500;600;700&family=Poppins:wght@400;500;600&family=Roboto+Condensed:wght@400;700&display=swap"
	rel="stylesheet">
<title>MyTask</title>
</head>
<body>
	<div class="registerContainer">
		<div class="registerContainerLeft">
			<img alt="non.jpg" src="images/Checklist.jpg">
		</div>
		<div class="registerContainerRight">
			<div class="registerRightInnerCotainer">
				<h1 class="mytasktext">My Task</h1>
				<form action="Login" method="post">
					<input type="email" placeholder="Email" name="email"> <input
						type="password" placeholder="Password" name="password">
						<%if(request.getAttribute("NOTIFICATION")=="wrong credentials"){%>
							<p class="errorNotification">${NOTIFICATION}</p>
						<%}%>
					<button type="submit">Login</button>
				</form>
				<div class="loginLink">
					<p>new user?</p>
					<a href="register.jsp">register</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>