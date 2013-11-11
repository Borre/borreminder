package com.borre

class MongoConnectionParams {
    public String server, collection, user, password
    public Integer port

    public String toString() {
        return "Server: $server Collection: $collection Port: $port User: $user Password: $password"
    }
}
