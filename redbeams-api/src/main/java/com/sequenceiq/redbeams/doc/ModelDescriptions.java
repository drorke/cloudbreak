package com.sequenceiq.redbeams.doc;

public final class ModelDescriptions {

    public static final String ALLOCATE_DATABASE_SERVER_REQUEST = "Request for allocating a new database server in a provider";
    public static final String CREATE_DATABASE_REQUEST = "Request for creating a new database on a registered database server";
    public static final String CREATE_DATABASE_RESPONSE = "Response for creating a new database on a registered database server";
    public static final String DATABASE_IDENTIFIERS = "Identifiers that together identify a database in an environment";
    public static final String DATABASE_REQUEST = "Request containing information about a database to be registered";
    public static final String DATABASE_RESPONSE = "Response containing information about a database that was acted upon, e.g., retrieved, deleted, listed";
    public static final String DATABASE_RESPONSES = "A set of multiple database responses";
    public static final String DATABASE_SERVER_REQUEST = "Request containing information about a database server to be registered";
    public static final String DATABASE_SERVER_RESPONSE = "Response containing information about a database server that was acted upon, e.g., retrieved, "
        + "deleted, listed";
    public static final String DATABASE_SERVER_RESPONSES = "A set of multiple database server responses";
    public static final String DATABASE_SERVER_STATUS_RESPONSE = "Response containing status information about a database server";
    public static final String DATABASE_SERVER_TEST_REQUEST = "Request for testing connectivity to a database server";
    public static final String DATABASE_SERVER_TEST_RESPONSE = "Response for testing connectivity to a database server";
    public static final String DATABASE_TEST_REQUEST = "Request for testing connectivity to a database";
    public static final String DATABASE_TEST_RESPONSE = "Response for testing connectivity to a database";
    public static final String TERMINATE_DATABASE_SERVER_REQUEST = "Request for terminating an existing database server in a provider";

    public static class Database {
        public static final String CRN = "CRN of the database";
        public static final String DESCRIPTION = "Description of the database";
        public static final String CONNECTION_URL = "JDBC connection URL in the form of jdbc:<db-type>:<driver-specific-part>";
        public static final String CONNECTION_DRIVER = "Name of the JDBC connection driver (for example: 'org.postgresql.Driver')";
        public static final String CONNECTOR_JAR_URL = "URL that points to the JAR of the connection driver";
        public static final String DB_ENGINE = "Name of the database vendor (MYSQL, POSTGRES...)";
        public static final String DB_ENGINE_DISPLAYNAME = "Display name of the database vendor (MySQL, PostgreSQL, ...)";
        public static final String VERSION = "Version of the Database";
        public static final String USERNAME = "Username to use for authentication";
        public static final String PASSWORD = "Password to use for authentication";
        public static final String NAME = "Name of the database";
        public static final String TYPE = "Type of database, i.e., the service name that will use the database (HIVE, DRUID, SUPERSET, RANGER, ...)";
        public static final String CREATE_RESULT = "Result of database creation";
        public static final String ENVIRONMENT_CRN = "CRN of the environment of the database";
        public static final String CREATION_DATE = "Creation date / time of the database, in epoch milliseconds";
    }

    public static class DatabaseTest {
        public static final String EXISTING_REQUEST = "Identifiers of registered database to be tested for connectivity";
        public static final String NEW_REQUEST = "Information about a unregistered database to be tested for connectivity";
        public static final String RESULT = "Result of database connection test";
    }

    public static class DatabaseServer {
        public static final String ID = "Internal ID of the database server";
        public static final String NAME = "Name of the database server";
        public static final String CRN = "CRN of the database server";
        public static final String DESCRIPTION = "Description of the database server";
        public static final String HOST = "Host of the database server";
        public static final String PORT = "Port of the database server";
        public static final String CONNECTION_DRIVER = "Name of the JDBC connection driver (for example: 'org.postgresql.Driver')";
        public static final String CONNECTOR_JAR_URL = "URL that points to the JAR of the connection driver";
        public static final String DATABASE_VENDOR = "Name of the database vendor (MYSQL, POSTGRES, ...)";
        public static final String DATABASE_VENDOR_DISPLAY_NAME = "Display name of the database vendor (MySQL, PostgreSQL, ...)";
        public static final String USERNAME = "Username of the administrative user of the database server";
        public static final String PASSWORD = "Password of the administrative user of the database server";
        public static final String ENVIRONMENT_CRN = "CRN of the environment of the database server";
        public static final String CREATION_DATE = "Creation date / time of the database server, in epoch milliseconds";
        public static final String RESOURCE_STATUS = "Ownership status of the database server";
        public static final String STATUS = "Status of the database server stack";
        public static final String STATUS_REASON = "Additional status information about the database server stack";
    }

    public static class DatabaseServerTest {
        public static final String EXISTING_CRN = "CRN of registered database server to be tested for connectivity";
        public static final String NEW_REQUEST = "Information about an unregistered database server to be tested for connectivity";
        public static final String RESULT = "Result of database server connection test";
    }

    public static class DBStack {
        public static final String STACK_NAME = "Name of the database stack";
        public static final String REGION = "Region for the database stack";
        public static final String NETWORK = "Network information for the database stack";
        public static final String DATABASE_SERVER = "Database server information for the database stack";
        public static final String AWS_PARAMETERS = "AWS-specific parameters for the database stack";
        public static final String AZURE_PARAMETERS = "Azure-specific parameters for the database stack";
    }

    public static class NetworkModelDescription {
        public static final String AWS_PARAMETERS = "AWS-specific parameters of the specified network";
        public static final String GCP_PARAMETERS = "GCP-specific parameters of the specified network";
        public static final String AZURE_PARAMETERS = "Azure-specific parameters of the specified network";
        public static final String OPEN_STACK_PARAMETERS = "OpenStack-specific parameters of the specified network";
    }

    public static class AwsNetworkModelDescription {
        public static final String SUBNET_ID = "Subnet ID(s) of the specified AWS network";
    }

    public static class AzureNetworkModelDescription {
        public static final String VIRTUAL_NETWORK = "Fully qualified IDs for an Azure network and subnet";
    }

    public static class DatabaseServerModelDescription {
        public static final String INSTANCE_TYPE = "Instance type of the database server";
        public static final String DATABASE_VENDOR = "Database vendor of the database server";
        public static final String CONNECTION_DRIVER = "Name of the JDBC connection driver (for example: 'org.postgresql.Driver')";
        public static final String CONNECTOR_JAR_URL = "URL that points to the JAR of the connection driver (JDBC connector)";
        public static final String STORAGE_SIZE = "Storage size of the database server, in GB";
        public static final String ROOT_USER_NAME = "Root user name of the database server";
        public static final String ROOT_USER_PASSWORD = "Root user password of the database server";
        public static final String PORT = "Port for the database";
        public static final String AWS_PARAMETERS = "AWS-specific parameters of the specified database server";
        public static final String GCP_PARAMETERS = "GCP-specific parameters of the specified database server";
        public static final String AZURE_PARAMETERS = "Azure-specific parameters of the specified database server";
        public static final String OPEN_STACK_PARAMETERS = "OpenStack-specific parameters of the specified database server";
        public static final String SECURITY_GROUP = "Security group of the database server";
    }

    public static class SecurityGroupModelDescription {
        public static final String SECURITY_GROUP_IDS = "Exisiting security group ID(s) for the database server";
    }

    private ModelDescriptions() {
    }
}
