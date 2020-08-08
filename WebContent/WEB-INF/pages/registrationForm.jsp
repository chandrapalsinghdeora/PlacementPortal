<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">


<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>


</head>
<body>
   
      <div align="center">
        <h1>User Registration</h1>
        <form action="saveRegistration" method="post" >
       <table>
            <tr>
                <td>Enrollment no:</td>
                <td><input type="text" name="userEnrollmentNumber" required="" pattern="[a-zA-Z0-9]+" title="enter alphabets only" maxlength="10"/></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="userName" required=""  title="enter alphabets only"/></td>
            </tr>
            <tr>
                <td>User Nick Name:</td>
                <td><input type="text" name="userNickName" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="loginPassword" required/></td>
            </tr>
            
            <tr>
                <td>Date Of Birth:</td>
                <td><input type="text" name="dateOfBirth" id="datepicker" required/></td>
            </tr>
            <tr>
                <td>Guardian Name:</td>
                <td><input type="text" name="guardianName" /></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="userAddress" /></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="userCity" /></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="userState" /></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="userCountry" /></td>
            </tr>
            <tr>
                <td>ZIP Code:</td>
                <td><input type="text" name="userZIPCode" required pattern="[0-9]{6}" title="enter only numeric" maxlength="6"/></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="userPhone" /></td>
            </tr>
            <tr>
                <td>Email Id:</td>
                <td><input  name="userEmailId"  type="email" required  title="enter valid email address only"/></td>
            </tr>
            <tr>
                <td>Mobile Number:</td>
                <td><input type="text" name="userMobileNumber" required pattern="[0-9]{10}" title="please enter numeric only" maxlength="10"/></td>
            </tr>
            <tr>
				<td><label>Gender</label></td>
           
				<td><input type="radio" name="userGender" value="male" >Male 
				<input type="radio" name="userGender" value="female">  Female
				</td>
			</tr>
            <!-- <tr>
                <td>Photo:</td>
                <td><input type="text" name="userPhoto" /></td>
            </tr> -->
           
            <tr>
                <td>Role Type:</td>
                <td><input type='text' name="userRoleType"/></td>
            </tr>            
		
            
			<tr>
                <td colspan="2" align="center" style="padding-top:30px;">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table> 
       
        </form>
    </div>

</body>
</html>