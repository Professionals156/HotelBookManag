package hotel.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hotel.credit.CreditCard;
import hotel.utils.IOUtils;

public class Hotel {
	
	private Map<Integer, Guest> guests;
	public Map<RoomType, Map<Integer,Room>> roomsByType;
	public Map<Long, Booking> bookingsByConfirmationNumber;
	public Map<Integer, Booking> activeBookingsByRoomId;
	
	
	public Hotel() {
		guests = new HashMap<>();
		roomsByType = new HashMap<>();
		for (RoomType rt : RoomType.values()) {
			Map<Integer, Room> rooms = new HashMap<>();
			roomsByType.put(rt, rooms);
		}
		bookingsByConfirmationNumber = new HashMap<>();
		activeBookingsByRoomId = new HashMap<>();
	}

	
	public void addRoom(RoomType roomType, int id) {
		IOUtils.trace("Hotel: addRoom");
		for (Map<Integer, Room> rooms : roomsByType.values()) {
			if (rooms.containsKey(id)) {
				throw new RuntimeException("Hotel: addRoom : room number already exists");
			}
		}
		Map<Integer, Room> rooms = roomsByType.get(roomType);
		Room room = new Room(id, roomType);
		rooms.put(id, room);
	}

	
	public boolean isRegistered(int phoneNumber) {
		return guests.containsKey(phoneNumber);
	}

	
	public Guest registerGuest(String name, String address, int phoneNumber) {
		if (guests.containsKey(phoneNumber)) {
			throw new RuntimeException("Phone number already registered");
		}
		Guest guest = new Guest(name, address, phoneNumber);
		guests.put(phoneNumber, guest);		
		return guest;
	}

	
	public Guest findGuestByPhoneNumber(int phoneNumber) {
		Guest guest = guests.get(phoneNumber);
		return guest;
	}

	
	public Booking findActiveBookingByRoomId(int roomId) {
		Booking booking = activeBookingsByRoomId.get(roomId);;
		return booking;
	}


	public Room findAvailableRoom(RoomType selectedRoomType, Date arrivalDate, int stayLength) {
		IOUtils.trace("Hotel: checkRoomAvailability");
		Map<Integer, Room> rooms = roomsByType.get(selectedRoomType);
		for (Room room : rooms.values()) {
			IOUtils.trace(String.format("Hotel: checking room: %d",room.getId()));
			if (room.isAvailable(arrivalDate, stayLength)) {
				return room;
			}			
		}
		return null;
	}

	
	public Booking findBookingByConfirmationNumber(long confirmationNumber) {
		return bookingsByConfirmationNumber.get(confirmationNumber);
	}

	
	public long book(Room room, Guest guest, 
			Date arrivalDate, int stayLength, int occupantNumber,
			CreditCard creditCard) {
		// TODO Auto-generated method stub


		// A booking should exist for the room (this method should call room.book())
		Booking booking = room.book(guest, arrivalDate, stayLength, occupantNumber, creditCard);

             // The room should not be available for the specified arrivalDate and staylength
		if(booking.getArrivalDate() != arrivalDate && booking.getStayLength() != stayLength) {
			// The booking should be returned from findBookingByConfirmationNumber()
			return findBookingByConfirmationNumber(booking.getConfirmationNumber()).getConfirmationNumber();
		}

		return 0L;		
	}

	
	public void checkin(long confirmationNumber) {
		// TODO Auto-generated method stub

// throws a RuntimeException if no booking for confirmation number exists
		Booking booking  = findBookingByConfirmationNumber(confirmationNumber);
		if (booking.isCheckedIn()) {
			String mesg = String.format("CheckIn: confirmationNumberEntered : already exists : %s", confirmationNumber);
			throw new RuntimeException(mesg);
		}


String message = null;
		// The Booking referenced by confirmationNumber should be returned by getActiveBookingByRoomId()
		if(findActiveBookingByRoomId(booking.getRoomId()).isCheckedIn()) {
			message = String.format("Booking %d has already been checked in", confirmationNumber);
		}


// The Booking referenced by confirmationNumber should have a state of CHECKED_IN
		if(booking.isCheckedIn()){
			message = String.format("Booking %d has already been checked in", confirmationNumber);
		}



	}


	public void addServiceCharge(int roomId, ServiceType serviceType, double cost) {
		// TODO Auto-generated method stub

// throws a RuntimeException if no active booking associated with the room identified by roomID can be found
		Booking booking = findActiveBookingByRoomId(roomId);

if (booking.isPending()) {
			String mesg = String.format("addServiceCharge: roomIdEntered : No active booking associated with the room  : %d", roomId);
			throw new RuntimeException(mesg);
		}else

// A ServiceCharge should have been added to the active booking.
		if (!booking.isPending()) {
			booking.addServiceCharge(serviceType, cost);
		}

	}

	
	public void checkout(int roomId) {
		// TODO Auto-generated method stub

// throws a RuntimeException if no active booking associated with the room identified by roomID can be found
		Booking booking = findActiveBookingByRoomId(roomId);

	}


}
