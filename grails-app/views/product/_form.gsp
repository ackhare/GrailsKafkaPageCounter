<%@ page import="kafkaDemo.Company;" %>



<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'manufacturer', 'error')} required">
	<label for="manufacturer">
		<g:message code="product.manufacturer.label" default="Manufacturer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="manufacturer" name="manufacturer.id" from="${kafkaDemo.Company.list()}" optionKey="id" required="" value="${productInstance?.manufacturer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="product.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${productInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'salesPrice', 'error')} required">
	<label for="salesPrice">
		<g:message code="product.salesPrice.label" default="Sales Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="salesPrice" value="${fieldValue(bean: productInstance, field: 'salesPrice')}" required=""/>
</div>

