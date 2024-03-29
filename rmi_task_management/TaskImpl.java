import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskImpl extends UnicastRemoteObject implements Task {
    private List<String> taskList;

    public TaskImpl() throws RemoteException {
        taskList = new ArrayList<>();
    }

    @Override
    public void addTask(String task) throws RemoteException {
        taskList.add(task);
        System.out.println("Task added: " + task);
    }

    @Override
    public void removeTask(int taskId) throws RemoteException {
        if (taskId >= 0 && taskId < taskList.size()) {
            String removedTask = taskList.remove(taskId);
            System.out.println("Task removed: " + removedTask);
        } else {
            System.out.println("Invalid task ID");
        }
    }

    @Override
    public List<String> getAllTasks() throws RemoteException {
        return new ArrayList<>(taskList);
    }
}
