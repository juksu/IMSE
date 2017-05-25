package Model;

import java.util.Calendar;

public class Bestellung {
//	enum orderState {
//		OFFEN, BEZAHLT, LIEFERND, GELIEFERT, ABGESCHLOSSEN
//	};
	public class OrderState{
		private boolean open;
		private boolean paid;
		private boolean sending;
		private boolean sent;
		private boolean complete;
		
		public boolean isOpen() {
			return open;
		}
		
		public void setOpen(boolean open) {
			this.open = open;
		}
		
		public boolean isPaid() {
			return paid;
		}
		
		public void setPaid(boolean paid) {
			this.paid = paid;
			checkComplete();
		}
		
		public boolean isSending() {
			return sending;
		}
		
		public void setSending(boolean sending) {
			this.sending = sending;
		}
		
		public boolean isSent() {
			return sent;
		}
		
		public void setSent(boolean sent) {
			this.sent = sent;
			checkComplete();
		}
		
		public boolean isComplete() {
			return complete;
		}
		
		public void setComplete(boolean complete) {
			this.complete = complete;
			checkComplete();
		}
		
		public boolean checkComplete(){
			if( complete || (sent && paid) )
			{
				open = false;
				paid = true;
				sending = false;
				sent = true;
				return true;
			}
			return false;
		}	
	}
	
	private int id;
	private Calendar date;
	private OrderState currentState ;
	private Kunde customer;
	private int paypalTNr;
	
	public Bestellung(int id, Calendar date, OrderState currentState, Kunde customer, int paypalTNr) {
		super();
		this.id = id;
		this.date = date;
		this.currentState = currentState;
		this.customer = customer;
		this.paypalTNr = paypalTNr;
	}
}
