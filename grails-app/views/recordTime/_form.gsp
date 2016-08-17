<%@ page import="kafkaDemo.RecordTime" %>



<div class="fieldcontain ${hasErrors(bean: recordTimeInstance, field: 'currentUrl', 'error')} ">
	<label for="currentUrl">
		<g:message code="recordTime.currentUrl.label" default="Current Url" />
		
	</label>
	<g:textField name="currentUrl" value="${recordTimeInstance?.currentUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordTimeInstance, field: 'pageLeft', 'error')} required">
	<label for="pageLeft">
		<g:message code="recordTime.pageLeft.label" default="Page Left" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="pageLeft" precision="day"  value="${recordTimeInstance?.pageLeft}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: recordTimeInstance, field: 'pageVisited', 'error')} required">
	<label for="pageVisited">
		<g:message code="recordTime.pageVisited.label" default="Page Visited" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="pageVisited" precision="day"  value="${recordTimeInstance?.pageVisited}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: recordTimeInstance, field: 'referrerUrl', 'error')} ">
	<label for="referrerUrl">
		<g:message code="recordTime.referrerUrl.label" default="Referrer Url" />
		
	</label>
	<g:textField name="referrerUrl" value="${recordTimeInstance?.referrerUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordTimeInstance, field: 'timeSpentOnPage', 'error')} ">
	<label for="timeSpentOnPage">
		<g:message code="recordTime.timeSpentOnPage.label" default="Time Spent On Page" />
		
	</label>
	<g:textField name="timeSpentOnPage" value="${recordTimeInstance?.timeSpentOnPage}"/>
</div>

