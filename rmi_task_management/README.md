# Task List RMI Application

This is a simple RMI (Remote Method Invocation) application for managing a task list. The application consists of a server component that exposes methods to add, delete, and retrieve tasks, and a client component that interacts with the server remotely.

## Deployment Instructions

### Prerequisites
- Java Development Kit (JDK) installed on your machine.
- Basic understanding of Java programming and RMI concepts.

### Steps

1. **Clone the Repository:**
```console
git clone <repository_url>
```

2. **Compile the Java Files:**
```console
cd task-list-rmi
javac *.java
```

3. **Start the RMI Registry:**
Open a terminal and run the following command:
```console
rmiregistry
```

4. **Start the Server:**
Open another terminal and navigate to the project directory:
```console
java TaskServer
```

5. **Start the Client:**
Open another terminal and navigate to the project directory:
```console
java TaskClient
```

