package kafkaDemo

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.example.SecUser
import org.springframework.dao.DataIntegrityViolationException

import java.util.concurrent.TimeUnit

@Secured(["hasRole('role_user')"])
class CompanyController {

    def companyService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def storeInfo() {

        if (params?.outDate && session.inTime) {
            session.outTime = new Date()
            Date outTime = session.outTime
            Date inTime = session.inTime
            long duration = (outTime.getTime() - inTime.getTime());

            long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
            Date timeSpent = new Date(outTime.getTime() - inTime.getTime())
            RecordTime recordTime = RecordTime.newInstance()

            recordTime.pageLeft = outTime as String


            recordTime.pageVisited = inTime as String

            recordTime.timeSpentOnPage = diffInSeconds + " seconds "
            recordTime.currentUrl = (session.cu_url as String).split('.dispatch')[0]
            recordTime.referrerUrl = session.cu_referer as String
            recordTime.user = (springSecurityService.currentUser as SecUser)
            companyService.sendToProducer(recordTime)
            session.removeAttribute('outTime')
            session.removeAttribute('inTime')
            session.removeAttribute('cu_url')
            session.removeAttribute('cu_referer')
        } else if (params?.inDate) {
            if (session.cu_referer) {
                session.inTime = new Date()
            }



            render 'success'
        } else {
            render 'failure'
        }

    }


    def list(Integer max) {
        List companies = []
        String url1 = "http://localhost:8080/KafkaDemo/"

        String url = request.getHeader(HttpHeaders.REFERER);
        session.cu_referer = "/" + (url1.split("//")[1]).split('/')[1]
        session.cu_url = request.getRequestURI()

        companies = companyService.allCompany
        [companyInstanceList: companies, companyInstanceTotal: Company.count()]
    }

    def create() {
        [companyInstance: new Company(params)]
    }

    def save() {
        def companyInstance = new Company(params)
        if (!companyInstance.save(flush: true)) {
            render(view: "create", model: [companyInstance: companyInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'company.label', default: 'Company'), companyInstance.id])
        redirect(action: "show", id: companyInstance.id)
    }

    def show(Long id) {
        def companyInstance = Company.get(id)
        if (!companyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'company.label', default: 'Company'), id])
            redirect(action: "list")
            return
        }

        [companyInstance: companyInstance]
    }

    def showSpecficColumn() {
        List companies = companyService.projectionsDemo()
        println companies

        [companies: companies]

    }

    def edit(Long id) {
        def companyInstance = Company.get(id)
        if (!companyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'company.label', default: 'Company'), id])
            redirect(action: "list")
            return
        }

        [companyInstance: companyInstance]
    }

    def update(Long id, Long version) {
        def companyInstance = Company.get(id)
        if (!companyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'company.label', default: 'Company'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (companyInstance.version > version) {
                companyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'company.label', default: 'Company')] as Object[],
                        "Another user has updated this Company while you were editing")
                render(view: "edit", model: [companyInstance: companyInstance])
                return
            }
        }

        companyInstance.properties = params

        if (!companyInstance.save(flush: true)) {
            render(view: "edit", model: [companyInstance: companyInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'company.label', default: 'Company'), companyInstance.id])
        redirect(action: "show", id: companyInstance.id)
    }

    def delete(Long id) {
        def companyInstance = Company.get(id)
        if (!companyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'company.label', default: 'Company'), id])
            redirect(action: "list")
            return
        }

        try {
            companyInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'company.label', default: 'Company'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'company.label', default: 'Company'), id])
            redirect(action: "show", id: id)
        }
    }
}
