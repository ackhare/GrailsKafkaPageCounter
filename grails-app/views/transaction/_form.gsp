<%@ page import="kafkaDemo.Store; kafkaDemo.Product; kafkaDemo.Transaction" %>



<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="transaction.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${kafkaDemo.Product.list()}" optionKey="id" required="" value="${transactionInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="transaction.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="quantity" type="number" value="${transactionInstance.quantity}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'salesDate', 'error')} required">
	<label for="salesDate">
		<g:message code="transaction.salesDate.label" default="Sales Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="salesDate" precision="day"  value="${transactionInstance?.salesDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'store', 'error')} required">
	<label for="store">
		<g:message code="transaction.store.label" default="Store" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="store" name="store.id" from="${kafkaDemo.Store.list()}" optionKey="id" required="" value="${transactionInstance?.store?.id}" class="many-to-one"/>
</div>

