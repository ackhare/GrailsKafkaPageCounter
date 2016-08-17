
<%@ page import="kafkaDemo.RecordTime" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recordTime.label', default: 'RecordTime')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-recordTime" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-recordTime" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="currentUrl" title="${message(code: 'recordTime.currentUrl.label', default: 'Current Url')}" />
					
						<g:sortableColumn property="pageLeft" title="${message(code: 'recordTime.pageLeft.label', default: 'Page Left')}" />
					
						<g:sortableColumn property="pageVisited" title="${message(code: 'recordTime.pageVisited.label', default: 'Page Visited')}" />
					
						<g:sortableColumn property="referrerUrl" title="${message(code: 'recordTime.referrerUrl.label', default: 'Referrer Url')}" />
					
						<g:sortableColumn property="timeSpentOnPage" title="${message(code: 'recordTime.timeSpentOnPage.label', default: 'Time Spent On Page')}" />
						<th class="sortable">Current User</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${recordTimeInstanceList}" status="i" var="recordTimeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: recordTimeInstance, field: "currentUrl")}</td>

						<td>${fieldValue(bean: recordTimeInstance, field: "pageVisited")}</td>

						<td>${fieldValue(bean: recordTimeInstance, field: "pageLeft")}</td>


						<td>${fieldValue(bean: recordTimeInstance, field: "referrerUrl")}</td>

						<td>${fieldValue(bean: recordTimeInstance, field: "timeSpentOnPage")}</td>
						<td>${recordTimeInstance.user.username}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${recordTimeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
