import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TaskServer {
    public static void main(String[] args) {
        try {
            Task task = new TaskImpl();
            Registry registry = LocateRegistry.createRegistry(1100);
            registry.rebind("TaskService", task);
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
