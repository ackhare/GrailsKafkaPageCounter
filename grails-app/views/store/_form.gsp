



<div class="fieldcontain ${hasErrors(bean: storeInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="store.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${storeInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: storeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="store.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${storeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: storeInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="store.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${storeInstance?.state}"/>
</div>

