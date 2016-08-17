package kafkaDemo

class ProductService {

    def getAllProducts() {


        def products = Product.createCriteria().list {
            and {
//            eq('name', 'PC')
                lt('salesPrice', 23.00)
            }


        }
        return products

    }
}
