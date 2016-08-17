package kafkaDemo



import org.junit.*
import grails.test.mixin.*

@TestFor(RecordTimeController)
@Mock(RecordTime)
class RecordTimeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/recordTime/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.recordTimeInstanceList.size() == 0
        assert model.recordTimeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.recordTimeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.recordTimeInstance != null
        assert view == '/recordTime/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/recordTime/show/1'
        assert controller.flash.message != null
        assert RecordTime.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/recordTime/list'

        populateValidParams(params)
        def recordTime = new RecordTime(params)

        assert recordTime.save() != null

        params.id = recordTime.id

        def model = controller.show()

        assert model.recordTimeInstance == recordTime
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/recordTime/list'

        populateValidParams(params)
        def recordTime = new RecordTime(params)

        assert recordTime.save() != null

        params.id = recordTime.id

        def model = controller.edit()

        assert model.recordTimeInstance == recordTime
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/recordTime/list'

        response.reset()

        populateValidParams(params)
        def recordTime = new RecordTime(params)

        assert recordTime.save() != null

        // test invalid parameters in update
        params.id = recordTime.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/recordTime/edit"
        assert model.recordTimeInstance != null

        recordTime.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/recordTime/show/$recordTime.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        recordTime.clearErrors()

        populateValidParams(params)
        params.id = recordTime.id
        params.version = -1
        controller.update()

        assert view == "/recordTime/edit"
        assert model.recordTimeInstance != null
        assert model.recordTimeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/recordTime/list'

        response.reset()

        populateValidParams(params)
        def recordTime = new RecordTime(params)

        assert recordTime.save() != null
        assert RecordTime.count() == 1

        params.id = recordTime.id

        controller.delete()

        assert RecordTime.count() == 0
        assert RecordTime.get(recordTime.id) == null
        assert response.redirectedUrl == '/recordTime/list'
    }
}
