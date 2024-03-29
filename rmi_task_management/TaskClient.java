import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class TaskClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1100);
            Task task = (Task) registry.lookup("TaskService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nOptions:");
                System.out.println("1. Add Task");
                System.out.println("2. Remove Task");
                System.out.println("3. View All Tasks");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter task description to add: ");
                        String newTask = scanner.nextLine();
                        task.addTask(newTask);
                        break;
                    case 2:
                        System.out.print("Enter task ID to remove: ");
                        int taskIdToRemove = scanner.nextInt();
                        task.removeTask(taskIdToRemove);
                        break;
                    case 3:
                        List<String> tasks = task.getAllTasks();
                        System.out.println("All tasks:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + ". " + tasks.get(i));
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
