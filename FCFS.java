import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;

public class FCFS {
	public static void main(String[] args) {

		double AvgWaitTime = 0, AvgTurnaroundTime = 0;
		int WaitingTime[] = new int[100];
		int TurnAroundTime[] = new int[100];
		int PID[] = new int[100];
		int ArrivalTime[] = new int[100];
		int BurstTime[] = new int[100];
		String CSVfile = args[0];
		
		
		FileInputStream FileOpener = null;

		// To read file name
		Scanner input = new Scanner(System.in);

		// To store each element obtained from file input
		String RecordFromCSV = "";
		int i = 0;
		int j;
		try {
			//Open file
			FileOpener = new FileInputStream(CSVfile);

			// Read file
			input = new Scanner(FileOpener);

			
			while (input.hasNextLine()) {

				RecordFromCSV = input.nextLine();

				// Split each element to fields using a separator, comma. 
				String[] commaSplit = RecordFromCSV.split(",");
				PID[i] = Integer.parseInt(commaSplit[0]);
				ArrivalTime[i] = Integer.parseInt(commaSplit[1]);
				BurstTime[i] = Integer.parseInt(commaSplit[2]);
				System.out.printf("Process ID=%d\tArrival Time=%d\tBurst Time=%d\n", PID[i], ArrivalTime[i],
						BurstTime[i]);
				i++;
			}
			System.out.println(" ");
			WaitingTime[0] = 0;
			for (j = 1; j < i; j++) {
				WaitingTime[j] = WaitingTime[j - 1] + BurstTime[j - 1];
				WaitingTime[j] = WaitingTime[j] - ArrivalTime[j];
			}
			for (j = 0; j < i; j++) {
				TurnAroundTime[j] = WaitingTime[j] + BurstTime[j];
				AvgWaitTime = AvgWaitTime + WaitingTime[j];
				AvgTurnaroundTime = AvgTurnaroundTime + TurnAroundTime[j];
			}
			System.out.println(" PROCESS | BT | WT | TAT ");
			for (j = 0; j < i; j++) {
				System.out.println(" " + PID[j] + "          " + BurstTime[j] + "  " + WaitingTime[j] + "  " + TurnAroundTime[j]);
			}
			//Print out the AWT and ATAT
			System.out.println("Average Waiting Time = " + AvgWaitTime / i);
			System.out.println("Average Turn Around Time = " + AvgTurnaroundTime / i);

		} catch (FileNotFoundException e) {
			System.out.printf("%s not found! ", CSVfile);
		}

		finally {
			if (FileOpener != null) {
				try {
					FileOpener.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}