

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-company" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-company" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="name" title="${message(code: 'company.name.label', default: 'Name')}" />

            <g:sortableColumn property="location" title="${message(code: 'company.location.label', default: 'Location')}" />


        </tr>
        </thead>
        <tbody>
        <g:each in="${companies}" status="i" var="company">
            <tr>
            <g:each in="${company}"  var="comp">
                <td>${comp}</td>

                %{--<td></td>--}%
            </g:each>
            <tr>
        </g:each>
        </tbody>
    </table>
    %{--<div class="pagination">--}%
        %{--<g:paginate total="${companyInstanceTotal}" />--}%
    %{--</div>--}%
</div>
</body>
</html>
