# Ideas2It-Training
Spring Boot training Files are present in this repo
Testing Jenkins


# Devero Mobile Training are present in LocalStack and SQSListener Folders
Step 1:

CHANGES MADE IN DATABASE MUST BE CAPTURED BY STREAM

In MySQL DB whenever there is an change made in the DB a binary log will be maintained .
We need to find that BinLog file.

We are using MAXWELL DAEMON for this which fetches the binlog value and posts in a stream.
Here we are using kafka Stream.


SO we need kafka and maxwell running on the same system.

Start Kafka & Zookeeper:
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

Start Maxwell:
bin/maxwell --config config.properties --user='root' --password='Test@1234' --schema_database=vitalsign --host='127.0.0.1' --kafka_version=2.7.0 --producer=kafka --port=3306 --kafka.bootstrap.servers=localhost:9092 --kafka_topic=Audit

here we have created the topic foe communication and have been mentioned while starting the Maxwell Daemon.


So this step will read the changes made in the binlog and put it into a stream.
In next step we need to consume that message and store in DynamoDB

---------------------------------------------------------------------------

STEP 2:

We have created a micro service which we will use to consume the message from Kafka Producer and store it in dynamoDB.

But DynamoDB is present in AWS...

Inorder to use DynamoDb locally, we can use it through docker which is supported by LocalStack image.

Localstack is an image available in docker which idealy helps to use all the services provided by AWS locally without any account details...its like using AWS in local.


So start the LocalStack in docker with DynamoDb service using docker-compose file.

Now dynamodb is available in docker through Localstack.

But we need to access that dynamodb so we need to configure the request and the end points to that docker.
so in config file we need to create the request with the end point configuration pointing to the docker.

once Endpoint has been configured all the other crud operations can be done.

---------------------------------------------------------------------------

STEP 3:

Now data which has been modified has been stored in DynamoDb.
We shall create and end point to fetch the changes in the DB and put it into a SQS.


So we need SQS which is also an AWS service.
SO as like before we shall create a Localstack with sqs and Dynamodb.
we need a queue to send and consume the message.

Create a queue with the following command
aws sqs create-queue --queue-name LocalStack --endpoint-url http://localhost:4566 --region us-east-1

once queue is created fetch the records from the dynamodb and send to the queue.

---------------------------------------------------------------------------

STEP 4:

As the changes has been put into the sqs queue, now we need to consume the message automatically.
So we shall create a seperate micro service.
Where we shall have SQSListener which will always listens to the messges put in queue.
