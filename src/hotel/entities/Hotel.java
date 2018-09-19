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

public long book(Room room, Guest guest,Date arrivalDate, int stayLength, int occupantNumber,CreditCard creditCard) {
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
	}


	public void addServiceCharge(int roomId, ServiceType serviceType, double cost) {
		// TODO Auto-generated method stub
	}

	
	public void checkout(int roomId) {
		// TODO Auto-generated method stub
	}


}
