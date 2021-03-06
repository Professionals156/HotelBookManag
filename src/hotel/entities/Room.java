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
	Room room;

	
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
		
		Booking myBoking = new Booking(guest, room, arrivalDate, stayLength, numberOfOccupants, creditCard);
 		

		bookings.add(myBoking);

		isAvailable(arrivalDate, stayLength);

		return myBoking;		
	}


	public void checkin() throws Exception {
		// TODO Auto-generated method stub v
		
		if (isReady() != true)
			throw new Exception("room is not availble");
		else
			state = State.OCCUPIED;
	}


	public void checkout(Booking booking) throws Exception {
		// TODO Auto-generated method stub
		if (isReady() == true) {
			throw new Exception("room is not occupied");
		}else{
			bookings.remove(booking);
			state = State.READY;
		}
	}


}