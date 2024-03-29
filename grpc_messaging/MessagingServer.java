import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import messaging.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessagingServer {
    private final int port;
    private final Server server;

    public MessagingServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new MessagingServiceImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server");
            MessagingServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);;
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MessagingServer server = new MessagingServer(50051);
        server.start();
        server.blockUntilShutdown();
    }

    static class MessagingServiceImpl extends MessagingServiceGrpc.MessagingServiceImplBase {
        private List<Message> messages = new ArrayList<>();

        @Override
        public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
            messages.add(Message.newBuilder()
                    .setSender(request.getSender())
                    .setText(request.getText())
                    .build());
            responseObserver.onNext(MessageResponse.newBuilder().setStatus("Message sent").build());
            responseObserver.onCompleted();
        }

        @Override
        public void getMessagesForUser(UserRequest request, StreamObserver<MessageList> responseObserver) {
            MessageList.Builder messageListBuilder = MessageList.newBuilder();
            for (Message message : messages) {
                if (message.getSender().equals(request.getUser())) {
                    messageListBuilder.addMessages(message);
                }
            }
            responseObserver.onNext(messageListBuilder.build());
            responseObserver.onCompleted();
        }
    }
}
