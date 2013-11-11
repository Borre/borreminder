package com.borre

import com.mongodb.DB
import com.mongodb.Mongo
import com.mongodb.MongoException
import com.mongodb.ServerAddress

class MongoConnector {

    MongoConnector(MongoConnectionParams params) {
        println("disco!")
    }

    public static Boolean validate(MongoConnectionParams params) {
        try {
            DB db = getDb(params)
            return db.authenticate(params.user, params.password.toCharArray())
        } catch (UnknownHostException e) {
            e.printStackTrace()
            return false
        } catch (MongoException e) {
            e.printStackTrace()
            return false
        }
    }
    private static DB getDb(MongoConnectionParams connectionParams) {
        Mongo mongo = new Mongo(new ServerAddress(connectionParams.server, connectionParams.port))
        return mongo.getDB(connectionParams.collection)
    }

}
