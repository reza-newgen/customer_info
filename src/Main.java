import com.task.database.service.DataRetrieve;
import com.task.read.ReadFile;

public class Main {
    public static void main(String[] args) {

        ReadFile rf = new ReadFile();
          rf.readFileData();
        long startTime = System.nanoTime();
        DataRetrieve drt = new DataRetrieve();
        drt.getDataFromValidCustomer();
        drt.getDataFromInvalidCustomer();


        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

    }
}