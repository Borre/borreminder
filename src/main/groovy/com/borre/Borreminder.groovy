package com.borre

class Borreminder {

    private File confFile = new File("conf.txt")
    public MongoConnectionParams credentials

    public static void main(String[] args) {
        new Borreminder(args)
    }

    public Borreminder(String[] args) {
        if (confFile.canRead() && confFile.size()) {

            List<String> conf = confFile.readLines()

            credentials = new MongoConnectionParams(
                    server: conf.get(0),
                    collection: conf.get(1),
                    port: conf.get(2)?.toInteger(),
                    user: conf.get(3),
                    password: conf.get(4)
            )
            new ExecuteOrders(args, credentials)
        } else {
            setConfiguration()
        }
    }

    private Boolean setConfiguration() {
        println("""
            Asking for configuration!
                """)
        Console console = System.console()

        MongoConnectionParams connectionParams = new MongoConnectionParams(
                server: console.readLine("Mongo server address: "),
                collection: console.readLine("Mongo collection: "),
                port: console.readLine("Mongo port (default:27017 ): ").toInteger(),
                user: console.readLine("Mongo username: "),
                password: console.readLine("Mongo password: ")
        )

        if (MongoConnector.validate(connectionParams)) {
            return createConfigFile(connectionParams)
        } else {
            return false
        }
    }

    private Boolean createConfigFile(MongoConnectionParams params) {
        confFile.append(params.server)
        confFile.append(params.collection)
        confFile.append(params.port)
        confFile.append(params.user)
        confFile.append(params.password)
        return confFile.size() > 1
    }
}