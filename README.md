# Spring Cloud Stream Reactive Demo

This repo contains a demo of [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) using reactive programming style.  Reactive is supported intrinsically by Spring Cloud Stream via [Project Reactor](https://projectreactor.io/).

Reactive support in SCSt can be used with [Spring Webflux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html) to create fully reactive applications.  This technique is reflected in this demo.

Project Reactor encapsulates a stream via a [`Flux`](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html).  Nearly anything that can be done with a `Flux` is possible with SCSt.  Besides the normal filtering, mapping, and reducing, there are other very interesting things that are useful in a messaging context:

- *Buffering*.  In a high volume application writes to databases and other backends are often much faster and more efficient if the writes are batched.  Buffering collects messages into a list, which are then used in batch writes.
- *Windowing*.  For temporal systems where it is advantageous to collect the messages into time windows.
- *Grouping*.  Sometimes it is useful to group messages based on some criteria.  This is easily supported.

The demo consists of the following components:

- `consumer` that receives messages and processes them via a `Flux`, which does simple filtering and mapping.
- `producer` that is a WebFlux endpoint that receives the messages in a POST call and processes using a `Flux`.  The data is published to the message broker.

A script is used to send the data to the `producer`.

## Running the Demo

JDK 11+ must be installed to run the demo.

This demo uses Kafka, so the first step is to get it running locally.  On Mac use Brew: `brew install kafka`.  This installs the Apache version.  
Kafka uses Zookeeper for cluster management, which is included with the Brew install.  Kafka requires JDK 1.8+.

To get the startup information use `brew info kafka`.  Note that the Brew service (`brew services start kafka`) is sometimes flaky, and doesn't
show what Kafka and Zookeeper are doing, so generally it's best to use the non-background services.  However, the recommended non-background
method shown in the info isn't always reliable either.  The reliable way to run Kafka is to run Zookeeper in one terminal:

`zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties`

Once that is up and running start the Kafka broker:

`kafka-server-start /usr/local/etc/kafka/server.properties`

Topics are automatically created by the SCSt applications when they are started.

Once Zookeeper and Kafka are running, each of the applications can be started.  Either start them directly from the IDE or use the Gradle
`bootRun` task:

Terminal 1:
`./gradlew :consumer:bootRun`

Terminal 2:
`./gradlew :producer:bootRun`

Send some data to the producer:

`./post.sh`

In the logs for the `consumer` you should see the results of the filtering and mapping logic.