
<%@ page import="kafkaDemo.RecordTime" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recordTime.label', default: 'RecordTime')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-recordTime" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-recordTime" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list recordTime">
			
				<g:if test="${recordTimeInstance?.currentUrl}">
				<li class="fieldcontain">
					<span id="currentUrl-label" class="property-label"><g:message code="recordTime.currentUrl.label" default="Current Url" /></span>
					
						<span class="property-value" aria-labelledby="currentUrl-label"><g:fieldValue bean="${recordTimeInstance}" field="currentUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordTimeInstance?.pageLeft}">
				<li class="fieldcontain">
					<span id="pageLeft-label" class="property-label"><g:message code="recordTime.pageLeft.label" default="Page Left" /></span>
					
						<span class="property-value" aria-labelledby="pageLeft-label"><g:formatDate date="${recordTimeInstance?.pageLeft}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordTimeInstance?.pageVisited}">
				<li class="fieldcontain">
					<span id="pageVisited-label" class="property-label"><g:message code="recordTime.pageVisited.label" default="Page Visited" /></span>
					
						<span class="property-value" aria-labelledby="pageVisited-label"><g:formatDate date="${recordTimeInstance?.pageVisited}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordTimeInstance?.referrerUrl}">
				<li class="fieldcontain">
					<span id="referrerUrl-label" class="property-label"><g:message code="recordTime.referrerUrl.label" default="Referrer Url" /></span>
					
						<span class="property-value" aria-labelledby="referrerUrl-label"><g:fieldValue bean="${recordTimeInstance}" field="referrerUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordTimeInstance?.timeSpentOnPage}">
				<li class="fieldcontain">
					<span id="timeSpentOnPage-label" class="property-label"><g:message code="recordTime.timeSpentOnPage.label" default="Time Spent On Page" /></span>
					
						<span class="property-value" aria-labelledby="timeSpentOnPage-label"><g:fieldValue bean="${recordTimeInstance}" field="timeSpentOnPage"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${recordTimeInstance?.id}" />
					<g:link class="edit" action="edit" id="${recordTimeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
