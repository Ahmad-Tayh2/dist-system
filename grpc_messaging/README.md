# gRPC Messaging Service

This application implements a simple messaging service using gRPC. It consists of a server and a client written in Java.

## Usage

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- Protocol Buffers compiler (`protoc`) and gRPC Java plugin installed.

## Server Implementation

The server (`MessagingServer`) provides the following functionalities:

- Accepts incoming messages from clients and stores them.
- Retrieves messages for a specific user.

### Running the Server

To run the server, follow these steps:

1. Compile the Protocol Buffers file (`messaging.proto`) to generate Java classes:

```bash
protoc --java_out=<output_directory> --grpc-java_out=<output_directory> messaging.proto
```
Replace <output_directory> with the directory where you want the generated Java files to be placed.

2. Make sure you have Java installed on your machine.
3. Compile the Java files.
4. Run the `MessagingServer` class.

Example:

```bash
javac *.java
java MessagingServer
```

The server will start listening on port 50051.

## Client Implementation

The client (MessagingClient) allows users to send messages to the server and retrieve messages for a specific user.

### Running the Client

To run the client, follow these steps:

1. Make sure you have Java installed on your machine.
2. Compile the Java files.
3. Run the MessagingClient class.

Example:

```bash
javac *.java
java MessagingClient
```
