



<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="company.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${companyInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="company.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${companyInstance?.name}"/>
</div>

