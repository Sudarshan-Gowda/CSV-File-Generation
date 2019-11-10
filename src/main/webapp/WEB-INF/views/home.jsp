<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CSV File Generation</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="icon"
	href="<c:url value="/resources/images/sudarshan-logo.png"/>" />
<link rel="shortcut icon"
	href="<c:url value="/resources/images/ico/favicon.ico"/>"
	type="image/x-icon">

<style type="text/css">
.page-title {
	font-family: 'Open Sans', sans-serif;
	font-style: normal;
	font-weight: 600;
	font-size: 24px;
	color: #4C4C4C;
}

.mt-1 {
	margin-top: 1rem !important;
}

.mb-2, .my-2 {
	margin-bottom: 0.5rem !important;
}
</style>

</head>

<body>
	<div id="fullpage">
		<div class="header">

			<%@include file="../views/common/welcome-header.jspf"%>

		</div>

		<div>
			<%@include file="../views/common/message.jspf"%>
		</div>


		<div class="container">
			<div class="row">
				<div class="col-6 mt-4">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">

							<li class="breadcrumb-item" style="color: blue;">Basic CSV
								File Generation</li>
						</ol>
					</nav>
				</div>

				<div class="col-1"></div>
			</div>
		</div>


		<div class="container">
			<div class="row">

				<div class="col-7">
					<div class="page-title">Enter the details below</div>
				</div>

			</div>
		</div>

		<div class="container">
			<div class="row">

				<form:form
					action="${pageContext.request.contextPath}/generateXlFile"
					method="post" modelAttribute="empDetails">

					<div id="fileDnDiv" class="col-12 row" style="margin-top: 25px;">

						<div class="col-12">
							<div class="border-bottom mt-1 mb-2"></div>
						</div>


						<div class="col-sm-6">


							<label for="content">Employee Id</label> <input id="empId"
								name="empId" class="form-control required" required="required"></input> <label
								for="empName">Employee Name</label> <input id="empName"
								name="empName" class="form-control required" required="required"></input> <label
								for="empDesign">Employee Designation</label> <input
								id="empDesign" name="empDesign" class="form-control required" required="required"></input>

							<label for="empDept">Employee Department</label> <input
								id="empDept" name="empDept" class="form-control required" required="required"></input>

							<label for="empAddress">Employee Address</label>
							<textarea id="empAddress" name="empAddress"
								class="form-control required" required="required"></textarea>

						</div>


						<div class="col-sm-12">
							<div class="border-bottom mt-1 mb-2"></div>
						</div>

						<div class="col-sm-12">
							<button type="submit" class="btn btn-info">Submit</button>
							<button type="reset" class="btn btn-danger">Cancel</button>
							<c:if test="${isDisabled !=null && isDisabled}">
								<a href="${pageContext.request.contextPath}/"
									class="btn btn-info">Go Back</a>
							</c:if>
						</div>

					</div>

				</form:form>


			</div>

		</div>

		<c:if test="${isDisabled !=null && isDisabled}">

			<div class="container" style="margin-top: 40px;">
				<div class="row">

					<div class="col-7">
						<div class="page-title">Generated CSV File</div>
					</div>

					<form:form action="${pageContext.request.contextPath}/download"
						method="post" modelAttribute="empDetails">
						<div>

							<input type="hidden" name="filePath" id="filePath"
								value="${filePath}">
							<div class="col-sm-12">
								<button type="submit" class="btn btn-success">Click
									Here to Download</button>
							</div>
						</div>
					</form:form>

				</div>
			</div>
		</c:if>

	</div>


	<div>
		<div class="col-lg-12 col-sm-12 col-xs-12 copyrights">
			<%@include file="../views/login-footer.jsp"%>
		</div>
	</div>
</body>
</html>