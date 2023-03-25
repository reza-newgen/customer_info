import com.task.database.service.DataRetrieve;
import com.task.read.ReadFile;

public class Main {
    public static void main(String[] args) {

      //  System.out.println("Hello world!");
        ReadFile rf = new ReadFile();
         // rf.readFileData();
        DataRetrieve drt = new DataRetrieve();
        drt.getDataFromValidCustomer();
        drt.getDataFromInvalidCustomer();

    }
}