package com.borre

class ExecuteOrders {
    ExecuteOrders(String[] args, MongoConnectionParams connectionParams) {
        args.each { println(it) }
        connectionParams.each { println(it) }
    }
}
