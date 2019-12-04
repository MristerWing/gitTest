<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link rel="stylesheet" href="${root}/css/common.css" />
<link rel="stylesheet" href="${root}/css/guest/write.css" />
<script type="text/javascript" src="${root}/javascript/guest/guest.js"></script>
</head>
<body>
	<div align="center">
		<c:if test="${count == 0 || currentPage == 1}">
			<form class="form" action="${root}/guest/writeOk.do" method="get">
				<div class="title" style="text-align: left;">
					<label>이름</label> <input type="text" name="name" size="12" /> <label>비밀번호</label>
					<input type="password" name="password" size="12" />
				</div>
				<div class="content">
					<textarea cols="53" rows="5" name="message"></textarea>
				</div>

				<div class="title" style="text-align: right;">
					<input type="submit" value="확인"> <input type="reset"
						value="취소">
				</div>
			</form>
		</c:if>

		<c:if test="${count > 0}">
			<c:forEach var="guestDto" items="${guestList}">
				<div class="form">
					<div class="title">
						<c:out value="${guestDto.num}"></c:out>
						<label>이름: </label>
						<c:out value="${guestDto.name}" />
						<label>작성일: </label>
						<fmt:formatDate value="${guestDto.writeDate}"
							pattern="yyyy/MM/dd HH:mm:ss" />
					</div>
					<div class="index">
						<span class="message"><c:out value="${guestDto.message}"></c:out></span>
					</div>
					<button type="button" onclick="updateCheck('${root}', '${guestDto.num}', '${currentPage}')">수정</button>
					<button type="button" onclick="deleteCheck('${root}', '${guestDto.num}', '${currentPage}')">삭제</button>
				</div>
			</c:forEach>
		</c:if>

		<%-- 
		페이지 번호 
		1. 총 페이지수 : 전체레코드수(count)와 페이지당 게시물 수(boardSize)
		2. 페이지 블럭 : 시작번호, 끝 번호 => 예) 10 = [1]..[10]
		--%>

		<div align="center">
			<c:if test="${count > 0}">
				<%-- 1. 총페이지 수 : count/boardSize count/boardSize+(count%boardSize==0?0:1) --%>
				<fmt:parseNumber integerOnly="true" var="pageCount"
					value="${count%boardSize!=0?count/boardSize+1:count/boardSize}" />
				<%-- 페이지 블럭 --%>
				<c:set var="pageBlock" value="${2}" />
				
				<%-- jstl의 정수형 계산은 계산을 하고 난 뒤 정수형으로 반환된다. --%>
				
				<%--페이지 블럭 / 시작, 끝번호--%>
				<fmt:parseNumber integerOnly="true" var="rn" value="${(currentPage-1)/pageBlock}"></fmt:parseNumber>
				
				<fmt:parseNumber integerOnly="true" var="startPage"
					value="${rn*pageBlock+1}"></fmt:parseNumber>
				<fmt:parseNumber integerOnly="true" var="endPage"
					value="${startPage+pageBlock-1}"></fmt:parseNumber>
				<%-- 3. 총 레코드 수 : 270 / 10 = 27 요청페이지: 25 --%>
				<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}"/>
				</c:if>
				<%--이전 --%>
				<c:if test="${startPage > pageBlock}">
					<a href="${root}/guest/write.do?pageNumber=${startPage-pageBlock}">[이전]</a>
				</c:if>
				
				<c:forEach var="loop" begin="${startPage}" end="${endPage}" step="1">
					<a href="${root}/guest/write.do?pageNumber=${loop}">[${loop}]</a>
				</c:forEach>
				
				<%--다음 --%>
				<c:if test="${endPage < pageCount}">
					<a href="${root}/guest/write.do?pageNumber=${startPage+pageBlock}">[다음]</a>
				</c:if>
				
			</c:if>
		</div>

	</div>
</body>
</html>