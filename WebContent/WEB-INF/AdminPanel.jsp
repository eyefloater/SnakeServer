<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Snake Admin Panel</title>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="./res/js/AdminPanel.js"></script>
</head>
<body>
	<p>You made it to the admin panel!</p>
	<br />
	<p>Connected to DataBase: ${db}</p>
<script type="text/javascript">
if (typeof jQuery != 'undefined') {
	 
    console.log("jQuery library is loaded!");
 
}else{
 
    alert("jQuery library is not found!");
 
};
doPoll();
</script>
	<table>
		<tr>
			<td>There are ${players_logged_in} users currently logged into the game.</td>
		</tr>
		<%
		String path = request.getContextPath();
		System.out.println("the admin panel says: " + path);
			Integer numberOfPlayers = Integer.parseInt((String)request.getAttribute("numberOfPlayers"));
			
			for (int i = 0; i < numberOfPlayers; i++) {
		%>

		<tr>
			<td id="test">Snake ${SnakePlayer}</td>
			<td>X Location: ${XLoc}</td>
			<td>Y Location: ${YLoc}</td>
		</tr>


		<%
			}
		%>
	</table>
</body>
</html>