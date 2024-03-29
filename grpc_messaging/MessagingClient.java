import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import messaging.*;

public class MessagingClient {
    private final ManagedChannel channel;
    private final MessagingServiceGrpc.MessagingServiceBlockingStub blockingStub;

    public MessagingClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    public MessagingClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = MessagingServiceGrpc.newBlockingStub(channel);
    }

    public void sendMessage(String sender, String recipient, String text) {
        MessageRequest request = MessageRequest.newBuilder()
                .setSender(sender)
                .setRecipient(recipient)
                .setText(text)
                .build();
        MessageResponse response = blockingStub.sendMessage(request);
        System.out.println("Message status: " + response.getStatus());
    }

    public void getMessagesForUser(String user) {
        UserRequest request = UserRequest.newBuilder().setUser(user).build();
        MessageList response = blockingStub.getMessagesForUser(request);
        for (Message message : response.getMessagesList()) {
            System.out.println("Sender: " + message.getSender() + ", Text: " + message.getText());
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        MessagingClient client = new MessagingClient("localhost", 50051);
        try {
            client.sendMessage("X", "Y", "Hello, Y!");
            client.sendMessage("Y", "X", "Hi, X!");
            client.getMessagesForUser("X");
        } finally {
            client.shutdown();
        }
    }
}
