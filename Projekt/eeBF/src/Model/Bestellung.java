package Model;

import java.util.ArrayList;
import java.util.Calendar;

public class Bestellung {
//	enum orderState {
//		OFFEN, BEZAHLT, LIEFERND, GELIEFERT, ABGESCHLOSSEN
//	};
	public class OrderState{
		private boolean ordered = false;  // if not ordered than only in shopping cart 
		private boolean open = false;  // TODO what is open?
		private boolean paid = false;
		private boolean sending = false;
		private boolean sent = false;
		private boolean complete = false;
		
		public boolean isOrdered() {
			return ordered;
		}
		
		public void setOrdered(boolean ordered) {
			this.ordered = ordered;
		}
		
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
				ordered = true;
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
	private ArrayList<Bestellposition> items;
	private int paypalTNr;
	
	public Bestellung(int id, Calendar date, OrderState currentState, Kunde customer, ArrayList<Bestellposition> items, int paypalTNr) {
		super();
		this.id = id;
		this.date = date;
		this.currentState = currentState;
		this.customer = customer;
		this.items = items;
		this.paypalTNr = paypalTNr;
	}
	
	public void addItem( Bestellposition item )
	{
		items.add(item);
	}
}
