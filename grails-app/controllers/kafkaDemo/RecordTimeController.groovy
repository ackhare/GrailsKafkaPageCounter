package kafkaDemo

import KafkaConsumer.ConsumerDemo
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
//@Secured('role_user')
class RecordTimeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {

       redirect(action: "list", params: params)
    }


    def doConsume()
    {
        ExecutorService executor = Executors.newSingleThreadExecutor()


        executor.execute {


            ConsumerDemo consumerDemo=new ConsumerDemo()

            Thread thread=new Thread(consumerDemo)
            thread.start()
        }
        render 'success'
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        [recordTimeInstanceList: RecordTime.list(params), recordTimeInstanceTotal: RecordTime.count()]
    }

    def create() {
        [recordTimeInstance: new RecordTime(params)]
    }

    def save() {
        def recordTimeInstance = new RecordTime(params)
        if (!recordTimeInstance.save(flush: true)) {
            render(view: "create", model: [recordTimeInstance: recordTimeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'recordTime.label', default: 'RecordTime'), recordTimeInstance.id])
        redirect(action: "show", id: recordTimeInstance.id)
    }

    def show(Long id) {
        def recordTimeInstance = RecordTime.get(id)
        if (!recordTimeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recordTime.label', default: 'RecordTime'), id])
            redirect(action: "list")
            return
        }

        [recordTimeInstance: recordTimeInstance]
    }

    def edit(Long id) {
        def recordTimeInstance = RecordTime.get(id)
        if (!recordTimeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recordTime.label', default: 'RecordTime'), id])
            redirect(action: "list")
            return
        }

        [recordTimeInstance: recordTimeInstance]
    }

    def update(Long id, Long version) {
        def recordTimeInstance = RecordTime.get(id)
        if (!recordTimeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recordTime.label', default: 'RecordTime'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (recordTimeInstance.version > version) {
                recordTimeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'recordTime.label', default: 'RecordTime')] as Object[],
                          "Another user has updated this RecordTime while you were editing")
                render(view: "edit", model: [recordTimeInstance: recordTimeInstance])
                return
            }
        }

        recordTimeInstance.properties = params

        if (!recordTimeInstance.save(flush: true)) {
            render(view: "edit", model: [recordTimeInstance: recordTimeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'recordTime.label', default: 'RecordTime'), recordTimeInstance.id])
        redirect(action: "show", id: recordTimeInstance.id)
    }

    def delete(Long id) {
        def recordTimeInstance = RecordTime.get(id)
        if (!recordTimeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recordTime.label', default: 'RecordTime'), id])
            redirect(action: "list")
            return
        }

        try {
            recordTimeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'recordTime.label', default: 'RecordTime'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'recordTime.label', default: 'RecordTime'), id])
            redirect(action: "show", id: id)
        }
    }
}
