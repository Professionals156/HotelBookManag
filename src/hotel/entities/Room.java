package hotel.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hotel.credit.CreditCard;
import hotel.utils.IOUtils;

public class Room {
	
	private enum State {READY, OCCUPIED}
	
	int id;
	RoomType roomType;
	List<Booking> bookings;
	State state;

	
	public Room(int id, RoomType roomType) {
		this.id = id;
		this.roomType = roomType;
		bookings = new ArrayList<>();
		state = State.READY;
	}
	

	public String toString() {
		return String.format("Room : %d, %s", id, roomType);
	}


	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return roomType.getDescription();
	}
	
	
	public RoomType getType() {
		return roomType;
	}
	
	public boolean isAvailable(Date arrivalDate, int stayLength) {
		IOUtils.trace("Room: isAvailable");
		for (Booking b : bookings) {
			if (b.doTimesConflict(arrivalDate, stayLength)) {
				return false;
			}
		}
		return true;
	}
	
	
	public boolean isReady() {
		return state == State.READY;
	}


	public Booking book(Guest guest, Date arrivalDate, int stayLength, int numberOfOccupants, CreditCard creditCard) {
		// TODO Auto-generated method stub
		Booking myBoking = new Booking(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
 		

		bookings.add(myBoking);

		return myBoking;		
	}


	public void checkin()  {
		// TODO Auto-generated method stub
	}


	public void checkout(Booking booking) throw Exception {
		// TODO Auto-generated method stub

		for (Guest guest: guests) { 
   			if (guest.getName() == guestname) { 
    			Room room = guest.getRoom(); 
    			if (guest.checkout()) { 
     				room.setGuest(null); 
     				room.getSafe().deactivate(); 
     				room.getSafe().close(); 
    			} else { 
     				throw new Exception("Guest couldn't check out"); 
    			} 
   			} 
  		} 
	}


}
