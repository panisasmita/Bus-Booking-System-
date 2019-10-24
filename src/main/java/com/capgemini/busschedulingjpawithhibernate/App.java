package com.capgemini.busschedulingjpawithhibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capgemini.busschedulingjpawithhibernate.dto.Bus;
import com.capgemini.busschedulingjpawithhibernate.dto.Feedback;
import com.capgemini.busschedulingjpawithhibernate.dto.Ticket;
import com.capgemini.busschedulingjpawithhibernate.dto.User;
import com.capgemini.busschedulingjpawithhibernate.exceptions.BusNotFoundException;
import com.capgemini.busschedulingjpawithhibernate.exceptions.DeletionFailedException;
import com.capgemini.busschedulingjpawithhibernate.exceptions.LoginFailedException;
import com.capgemini.busschedulingjpawithhibernate.exceptions.RegistrationFailedException;
import com.capgemini.busschedulingjpawithhibernate.exceptions.UpdationFailedException;
import com.capgemini.busschedulingjpawithhibernate.service.AdminServices;
import com.capgemini.busschedulingjpawithhibernate.service.AdminServicesImplementation;
import com.capgemini.busschedulingjpawithhibernate.service.CommonServices;
import com.capgemini.busschedulingjpawithhibernate.service.CommonServicesImplementation;
import com.capgemini.busschedulingjpawithhibernate.service.CustomerServices;
import com.capgemini.busschedulingjpawithhibernate.service.CustomerServicesImplementation;
import com.capgemini.busschedulingjpawithhibernate.service.OwnerServices;
import com.capgemini.busschedulingjpawithhibernate.service.OwnerServicesImplementation;
import com.capgemini.busschedulingjpawithhibernate.util.Validation;



public class App {
	static Scanner scanner = new Scanner(System.in);
	static AdminServices adminServices = new AdminServicesImplementation();
	static CommonServices commonServices = new CommonServicesImplementation();
	static CustomerServices customerServices = new CustomerServicesImplementation();
	static OwnerServices ownerServices = new OwnerServicesImplementation();
	static Validation validation = new Validation();
	static String type = null;
	static User user = new User();
	static Bus bus = new Bus();
	static Feedback feedback = new Feedback();

	public static void main(String[] args) {
		int a = 1;
		System.out.println("*****************************************");
		System.out.println("Welcome to online bus booking portal");

		while (true) {
			System.out.println("*****************************************");
			System.out.println("Press 1 to register");
			System.out.println("Press 2 to login");
			System.out.println("Press 3 to exit");
			System.out.println("*****************************************");
			System.out.print("Enter your choice :");

			int input = Integer.parseInt(scanner.next());
			if (input == 1) {
				try {
					registrationUser();

				} catch (RegistrationFailedException e) {
					e.printStackTrace();
				}
			} else if (input == 2) {
				try {
					loginUser();
					type = user.getType();
					switch (type) {

					case ("A"):
						int adminInput;
					do {
						System.out.println("Press 1 to Register User");
						System.out.println("Press 2 to Delete User");
						System.out.println("Press 3 to Update User");
						System.out.println("Press 0 to LogOut");
						System.out.print("Enter your choice :");
						adminInput = Integer.parseInt(scanner.next());
						if (adminInput == 1) {
							registrationUser();
						} else if (adminInput == 2) {
							deleteUser();
						} else if (adminInput == 3) {
							updateUser();
						}
					}while(adminInput>0);
					break;
					case ("O"):
						int ownerInput;
					do {
						System.out.println("Press 1 to Add Bus");
						System.out.println("Press 2 to Delete Bus");
						System.out.println("Press 3 to Update Bus");
						System.out.println("Press 4 to Get All Tickits");
						System.out.println("Press 0 to go back");
						System.out.print("Enter your choice :");
						ownerInput = Integer.parseInt(scanner.next());
						if (ownerInput == 1) {
							addBus();
						} else if (ownerInput == 2) {
							deleteBus();
						} else if (ownerInput == 3) {
							updateBus();
						} else if (ownerInput == 4) {
							getAllTickets();
						}
					}while(ownerInput>0);
					break;
					case ("U"):
						int userInput;
					do {
						System.out.println("Press 1 to Search & Book Bus");
						System.out.println("Press 2 to Cancel Ticket");
						System.out.println("Press 3 to Get Ticket Info");
						System.out.println("Press 4 to Give Feedback");
						System.out.print("Enter your choice :");
						userInput = Integer.parseInt(scanner.next());
						if (userInput == 1) {
							searchBus();
							bookTicket();
						} else if (userInput == 2) {
							cancelTicket();
						} else if (userInput == 3) {
							getTicketInfo();
						} else if (userInput == 4) {
							feedback();
						}
					}while(userInput>0);
					break;
					}
				} catch (LoginFailedException e) {
					e.printStackTrace();
				} catch (RegistrationFailedException e) {
					e.printStackTrace();
				} catch (DeletionFailedException e) {
					e.printStackTrace();
				}
			} else if (input == 3) {
				break;
			}
		}

	}

	private static void searchBus() throws BusNotFoundException{
		String start;
		String end;
		while (true) {
			System.out.println("Enter bus Source");
			String busSource = scanner.next();
			if (busSource != null) {
				start = busSource;
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Destination");
			String busDestination = scanner.next();
			if (busDestination != null) {
				end = busDestination;
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		List<Bus> buses = commonServices.exploreBus(start, end);
		if(!buses.isEmpty()) {
			for (Bus bus : buses) {
				System.out.println(bus);
			}
		} else {
			throw new BusNotFoundException("No Buses available!!!");
		}
	}

	private static void bookTicket() {
		int id;
		int seats;
		while (true) {
			System.out.println("Enter Bus Id for book the ticket");
			Integer busId = validation.validateId(scanner.next());
			if (busId != null) {
				id = busId;
				break;
			} else {
				System.out.print("Enter a valid ticket  :");
			}
		}
		while (true) {
			System.out.println("Enter No of Seats");
			Integer seat = validation.validateId(scanner.next());
			if (seat != null) {
				seats = seat;
				break;
			} else {
				System.out.print("Enter a valid ticket  :");
			}
		}

		Ticket ticket = customerServices.bookTicket(id, user.getUserId(), seats);
		if(ticket != null) {
			System.out.println(ticket);
		} else {
			System.out.println("Failed");
		}
	}

	private static void feedback() {
		while (true) {
			System.out.println("Enter Bus Id");
			Integer busId = validation.validateId(scanner.next());
			if (busId != null) {
				feedback.setBusId(busId);
				break;
			} else {
				System.out.print("Enter a valid ticket  :");
			}
		}
		while (true) {
			System.out.println("Enter User Id");
			Integer userId = validation.validateId(scanner.next());
			if (userId != null) {
				feedback.setUserId(userId);
				break;
			} else {
				System.out.print("Enter a valid ticket  :");
			}
		}
		while (true) {
			System.out.println("Enter Feedback");
			String feedbackText = scanner.next();
			if (feedbackText != null) {
				feedback.setFeedback(feedbackText);;
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		if(customerServices.giveFeedback(feedback)) {
			System.out.println("Feedback Added");
		} else {
			System.out.println("Failed");
		}
	}

	private static void getTicketInfo() {
		while (true) {
			System.out.println("Enter Ticket id");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				Ticket ticket = customerServices.getTicketInfo(id);
				if(ticket != null) {
					System.out.println(ticket);
				}
				break;
			} else {
				System.out.print("Enter a valid ticket  :");
			}
		}
	}

	private static void cancelTicket() {
		while (true) {
			System.out.println("Enter Ticket Id");
			Integer ticketId = validation.validateId(scanner.next());
			System.out.println("Enter No of Setas");
			Integer seats = validation.validateId(scanner.next());
			if (ticketId != null && seats != null) {
				if (customerServices.cancelTicket(ticketId, user.getUserId(), seats)) {
					System.out.println("Canceled");
					break;
				}
			} else {
				System.out.print("Enter a valid number :");
			}
		}
	}

	private static void getAllTickets() {
		int busId;
		Date date = null;
		while (true) {
			System.out.println("Enter bus Id");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				busId = id;
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter bus Date");
			String busDate = scanner.next();
			if (busDate != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = format.parse(busDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		List<Ticket> tickets = ownerServices.getTicketByBusId(busId, date);
		for (Ticket ticket : tickets) {
			System.out.println(ticket);
		}

	}

	private static void updateBus() {
		while (true) {
			System.out.println("Enter bus Id");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				bus.setBusId(id);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter bus Name");
			String busName = scanner.next();
			if (busName != null) {
				bus.setBusName(busName);
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Type");
			String busType = scanner.next();
			if (busType != null) {
				bus.setBusType(busType);
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Source");
			String busSource = scanner.next();
			if (busSource != null) {
				bus.setSource(busSource);
				;
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Destination");
			String busDestination = scanner.next();
			if (busDestination != null) {
				bus.setDestination(busDestination);
				;
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Fare");
			Integer fare = validation.validateId(scanner.next());
			if (fare != null) {
				bus.setFare(fare);
				;
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter bus Capacity");
			Integer capacity = validation.validateId(scanner.next());
			if (capacity != null) {
				bus.setCapacity(capacity);
				;
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter available seats in bus");
			Integer availableSeats = validation.validateId(scanner.next());
			if (availableSeats != null) {
				bus.setAvailableSeats(availableSeats);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		if (ownerServices.updateBus(bus)) {
			System.out.println("Bus Updated");
		} else {
			System.out.println("Failed");
		}
	}

	private static void deleteBus() {
		while (true) {
			System.out.println("Enter bus Id");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				if (ownerServices.deleteBus(id)) {
					System.out.println("Deleted");
					break;
				}
			} else {
				System.out.print("Enter a valid number :");
			}
		}
	}

	private static void addBus() {
		while (true) {
			System.out.println("Enter bus Id");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				bus.setBusId(id);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter bus Name");
			String busName = scanner.next();
			if (busName != null) {
				bus.setBusName(busName);
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Type");
			String busType = scanner.next();
			if (busType != null) {
				bus.setBusType(busType);
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Source");
			String busSource = scanner.next();
			if (busSource != null) {
				bus.setSource(busSource);
				;
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Destination");
			String busDestination = scanner.next();
			if (busDestination != null) {
				bus.setDestination(busDestination);
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		while (true) {
			System.out.println("Enter bus Fare");
			Integer fare = validation.validateId(scanner.next());
			if (fare != null) {
				bus.setFare(fare);
				;
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter bus Capacity");
			Integer capacity = validation.validateId(scanner.next());
			if (capacity != null) {
				bus.setCapacity(capacity);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter available seats in bus");
			Integer availableSeats = validation.validateId(scanner.next());
			if (availableSeats != null) {
				bus.setAvailableSeats(availableSeats);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter Journey Date");
			String busDate = scanner.next();
			if (busDate != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					java.util.Date date = format.parse(busDate);
					java.sql.Date busDates = new java.sql.Date(date.getTime());
					bus.setJourneyData(busDates);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			} else {
				System.out.println("This field cannot be empty");
			}
		}
		if (ownerServices.addBus(bus) != null) {
			System.out.println("Bus Added");
		} else {
			System.out.println("Failed");
		}
	}

	private static void deleteUser() throws DeletionFailedException {
		while (true) {
			System.out.print("Enter your id : ");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				user.setUserId(id);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		if (adminServices.deleteUser(user.getUserId())) {
			System.out.println("Success");
		} else {
			throw new DeletionFailedException("this id does not exist!!!!!");
		}
	}

	private static void loginUser() throws LoginFailedException {
		while (true) {
			System.out.print("Enter your id : ");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				user.setUserId(id);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}
		while (true) {
			System.out.println("Enter your password");
			String password = scanner.next();
			if (password != null) {
				user.setPassword(password);
				break;
			} else {
				System.out.println("Password field cannot be empty");
			}
		}
		if (commonServices.login(user.getUserId(), user.getPassword()) != null) {
			user = commonServices.login(user.getUserId(), user.getPassword());
			System.out.println("Success");
		} else {
			System.out.println("Failed!!!");
			throw new LoginFailedException();
		}
	}
	
	public static void updateUser() throws UpdationFailedException {
		while (true) {
			System.out.print("Enter your id : ");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				user.setUserId(id);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}

		while (true) {
			System.out.println("Enter your name");
			String name = scanner.next();
			if (name != null) {
				user.setUserName(name);
				break;
			} else {
				System.out.println("Name field cannot be empty");
			}
		}

		while (true) {
			System.out.println("Enter your Email Id");
			String email = validation.validateEmail(scanner.next());
			if (email != null) {
				user.setEmailId(email);
				break;
			} else {
				System.out.println("Enter a valid email id (eg:example12@gmail.com)");
			}
		}

		while (true) {
			System.out.println("Enter your contact number");
			Long number = validation.validateContact(scanner.next());
			if (number != null) {
				user.setContactNumber(number);
				break;
			} else {
				System.out.println("Number should be a valid 10 digits");
			}
		}

		while (true) {
			System.out.println("Enter your password");
			String password = scanner.next();
			if (password != null) {
				user.setPassword(password);
				break;
			} else {
				System.out.println("Password field cannot be empty");
			}
		}

		while (true) {
			System.out.println("Enter U user\nEnter O for owner");
			String type = scanner.next();
			if (type.equals("U") || type.equals("O")) {
				user.setType(type);
				break;
			} else {
				System.out.println("Enter Valid input");
			}
		}
		if (commonServices.updateUser(user)) {
			System.out.println("Success");
		} else {
			throw new UpdationFailedException("Failed to update check the id");
		}
	}

	public static void registrationUser() throws RegistrationFailedException {
		while (true) {
			System.out.print("Enter your id : ");
			Integer id = validation.validateId(scanner.next());
			if (id != null) {
				user.setUserId(id);
				break;
			} else {
				System.out.print("Enter a valid number :");
			}
		}

		while (true) {
			System.out.println("Enter your name");
			String name = scanner.next();
			if (name != null) {
				user.setUserName(name);
				break;
			} else {
				System.out.println("Name field cannot be empty");
			}
		}

		while (true) {
			System.out.println("Enter your Email Id");
			String email = validation.validateEmail(scanner.next());
			if (email != null) {
				user.setEmailId(email);
				break;
			} else {
				System.out.println("Enter a valid email id (eg:example12@gmail.com)");
			}
		}

		while (true) {
			System.out.println("Enter your contact number");
			Long number = validation.validateContact(scanner.next());
			if (number != null) {
				user.setContactNumber(number);
				break;
			} else {
				System.out.println("Number should be a valid 10 digits");
			}
		}

		while (true) {
			System.out.println("Enter your password");
			String password = scanner.next();
			if (password != null) {
				user.setPassword(password);
				break;
			} else {
				System.out.println("Password field cannot be empty");
			}
		}

		while (true) {
			System.out.println("Enter U user\nEnter O for owner");
			String type = scanner.next();
			if (type.equals("U") || type.equals("O")) {
				user.setType(type);
				break;
			} else {
				System.out.println("Enter Valid input");
			}
		}
		if (adminServices.registerUser(user) != null) {
			System.out.println("Success");
		} else {
			throw new RegistrationFailedException();
		}
	}

}
