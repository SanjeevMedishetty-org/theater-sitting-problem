package sample.theater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import sample.theater.model.BookingRequest;

public class SeatingAlgorithim {
	
	private static final String SPACE = "\\s+";
	static private List<List<String>> rows = new ArrayList<List<String>>();
	static private List<String> sections;
	static private List<BookingRequest> booking = new ArrayList<BookingRequest>();
	static private int totalCapacity = 0;

	public static void main(String[] args) throws FileNotFoundException,IOException{

		try {
			
			SeatingAlgorithim.captureLayoutAndBooking();
			SeatingAlgorithim.calculateTheaterCapacity(rows);
			SeatingAlgorithim.allotSeating();
			
		} catch (FileNotFoundException fx) {
			System.out.println("Unable to locate the file");
		} catch (IOException io) {
			System.out.println("Unable to read the file");

		}
	}
	
	private static void allotSeating() {
		
		
		for(BookingRequest bkng : booking)
		{
			if (bkng.getNumTicketsReq() > totalCapacity) {

				System.out.println(bkng.getName() + " Sorry, we cannot handle your party");

			} else {

				int rowTemp = 0;
				int section = 0;
				boolean match = false;
				for (List<String> row : rows) {
					section = 0;
					for (String sec : row) {
                        // try to avoid single seat after booking which will be wasted otherwise we can allocate (but I assume that is requirement looking at output)
						if ((Integer.parseInt(sec) - bkng.getNumTicketsReq()) > 1 || (Integer.parseInt(sec) - bkng.getNumTicketsReq()) == 0  ) {
							rows.get(rowTemp).set(section,
									(Integer.parseInt(rows.get(rowTemp).get(section)) - bkng.getNumTicketsReq()) + "");
							System.out.println(bkng.getName() + " Row " + (rowTemp + 1) + " Section " + (section + 1));
							totalCapacity -= bkng.getNumTicketsReq();
							match = true;
							break;
						}

						section++;

					}
					if (match)
						break;
					rowTemp++;
				}

				if (!match) {
					System.out.println(bkng.getName() + " Call to split party.");
				}
			}
			
			
			
		}
		
		
		
		
	}

	private static void calculateTheaterCapacity(List<List<String>> rows) {

		for (List<String> row : rows) {
			for (String sec : row) {
				totalCapacity += Integer.parseInt(sec.trim());
			}
		}

	}

	public static void captureLayoutAndBooking()  throws FileNotFoundException, IOException 
	{
		File fileInput = new File("input.txt");
		FileReader reader = new FileReader(fileInput);
		BufferedReader bReader = new BufferedReader(reader);
		String content = null;
		
		// capture layout
		while (!((content = bReader.readLine()).trim()).isEmpty()) {
			sections = new ArrayList<String>();
			String section[] = content.split(SPACE);
			sections.addAll(Arrays.asList(section));
			rows.add(sections);

		}
		
		BookingRequest bookingRequest = null;
		//capture booking
		while ((content = bReader.readLine()) != null) {
			String bking[] = content.split(SPACE);
			bookingRequest = new BookingRequest(bking[0], Integer.parseInt(bking[1]));
			booking.add(bookingRequest);
		}
		
		bReader.close();
		reader.close();
		
		
	}

}
