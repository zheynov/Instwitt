<%@ page import="by.redlaw.entity.Post" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/taglib/exittag.tld" prefix="ext" %>
<%@ taglib uri="/WEB-INF/taglib/postimagetag.tld" prefix="pit" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>INSTWITT</title>
    <link href="<c:url value="/resources/css/styles.css"/>" type="text/css" rel="stylesheet">
</head>

<body class="bodyPostLogin">

<span style="float: left">
    <a href="?lang=en"><font color="white">en</font></a>
    <a href="?lang=ru"><font color="white">ru</font></a>
    </span>

<%-- Exit from sessiom --%>
<spring:message code="exit.button" var="exitbuttonLabel"/>
<spring:message code="logged.as" var="loggedasLabel"/>
<spring:message code="profile.form" var="pfLabel"/>


<div align="right" class="postlogin-div">
    <ext:exitTag userDTO="${userDTO.login}" loggedAs="${loggedasLabel}">
        <a href="/profile"> <b> ${pfLabel} </b> </a> <a href="/exit"><b> ${exitbuttonLabel}</b> </a>
    </ext:exitTag>
</div>

<h1 align="center"> I N S T W I T T </h1>

<div align="center" class="postlogin-div">
    <form:form action="/makepost" method="post" commandName="newPost" enctype="multipart/form-data">

        <spring:message code="write.any.words.here" var="wawhLabel"/>
        <p><form:textarea path="text" rows="6" cols="100" name="text" placeholder="${wawhLabel}"/></p>
        <form:input path="photo" type="file" name="photo" accept="image/*" data-buttonText="Your label here"/>

        <spring:message code="make.post" var="mpostLabel"/>
        <spring:message code="back.button" var="backLabel"/>

        <input type="submit" value="${mpostLabel}" class="buttonPost">
    </form:form>
</div>

<br/><br/>

<c:forEach items="${postService.getAllThePosts()}" var="post">
    <div align="center" class="postlogin-div"><font color="white">
        <spring:message code="posted.by" var="postedbyLabel"/>
        <spring:message code="post.time" var="posttimeLabel"/>
            <%--tag postImageTag shows image using Base64 encoding --%>
        <a id="${post.postID}"></a>

        <c:if test="${post.photo ne null}">
            <img src="<pit:postImageTag imageByte="${post.photo}"/>" height="300">
        </c:if>

        <p>
        <b> ${post.text} </b>
        <p>
            ${postedbyLabel} <b>
            ${post.userID.login}</b>,
            ${posttimeLabel}: <b> ${post.postDate} </b> </font>

        <c:if test="${post.userID.getLogin()==userDTO.login}">
            <spring:message code="edit.post" var="edityLabel"/>
            <a href="/post/${post.postID}"> ${edityLabel} </a>
            <spring:message code="delete.post" var="deleteLabel"/>
            <a href="/delete/${post.postID}"> ${deleteLabel} </a>
        </c:if>

    </div>
</c:forEach>


</body>
</html>
