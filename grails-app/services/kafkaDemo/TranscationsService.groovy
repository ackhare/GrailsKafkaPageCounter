package kafkaDemo

import org.hibernate.FetchMode

class TranscationsService {


    def printingTransactionsWithACompanyId(Long companyId) {
        List transactions = []

        transactions = Transaction.createCriteria().list {
            fetchMode 'product', FetchMode.JOIN
            fetchMode 'store', FetchMode.JOIN

            product {
                manufacturer {
                    eq 'id', companyId
                }
            }
        }


        return transactions
    }


    def printingTransactionsWithACompanyIdviaProjections(Long companyId) {
        List transactions = []

        def atransactions = Transaction.createCriteria()

        transactions = atransactions.list {
            product {
                manufacturer {
                    eq 'id', companyId
                }
            }
        }

        println transactions
        return transactions
    }


}
