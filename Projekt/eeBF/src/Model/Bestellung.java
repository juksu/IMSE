package Model;

import java.util.ArrayList;
import java.util.Calendar;

public class Bestellung
{
	// enum orderState {
	// OFFEN, BEZAHLT, LIEFERND, GELIEFERT, ABGESCHLOSSEN
	// };
	private class OrderState
	{
		private boolean ordered = false; // if not ordered than only in shopping
											// cart
		// private boolean open = false; // TODO what is open?
		private boolean paid = false;
		private boolean sending = false;
		private boolean sent = false;
		private boolean complete = false;

		public boolean isOrdered()
		{
			return ordered;
		}

		public void setOrdered( boolean ordered )
		{
			this.ordered = ordered;
		}

		// public boolean isOpen() {
		// return open;
		// }
		//
		// public void setOpen(boolean open) {
		// this.open = open;
		// }

		public boolean isPaid()
		{
			return paid;
		}

		public void setPaid( boolean paid )
		{
			this.paid = paid;
			checkComplete();
		}

		public boolean isSending()
		{
			return sending;
		}

		public void setSending( boolean sending )
		{
			this.sending = sending;
		}

		public boolean isSent()
		{
			return sent;
		}

		public void setSent( boolean sent )
		{
			this.sent = sent;
			if( sent )
				sending = false;
			else
				sending = true;
			checkComplete();
		}

		public boolean isComplete()
		{
			return complete;
		}

		public void setComplete( boolean complete )
		{
			this.complete = complete;
			checkComplete();
		}

		public boolean checkComplete()
		{
			if( complete || (sent && paid) )
			{
				ordered = true;
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
	private OrderState currentState;
	private Kunde customer;
	private ArrayList<Bestellposition> items;
	private String paypalTNr;

//	public Bestellung(int id, Calendar date, Kunde customer, ArrayList<Bestellposition> items, String paypalTNr)
//	{
//		// super();
//		this.id = id;
//		this.date = date;
//		this.currentState = new OrderState();
//		this.customer = customer;
//		this.items = items;
//		this.paypalTNr = paypalTNr;
//	}

	public Bestellung()
	{
		this.date = Calendar.getInstance();
		this.currentState = new OrderState();
		this.items = new ArrayList<Bestellposition>();
	}

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
	}

	 public Calendar getDate() {
		 return date;
	 }

	public void setDate( Calendar date )
	{
		this.date = date;
	}

	public OrderState getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState( OrderState currentState )
	{
		this.currentState = currentState;
	}

	public Kunde getCustomer()
	{
		return customer;
	}

	public void setCustomer( Kunde customer )
	{
		this.customer = customer;
	}

	public ArrayList<Bestellposition> getItems()
	{
		return items;
	}

	public void setItems( ArrayList<Bestellposition> items )
	{
		this.items = items;
	}

	public String getPaypalTNr()
	{
		return paypalTNr;
	}

	public void setPaypalTNr( String paypalTNr )
	{
		this.paypalTNr = paypalTNr;
	}

	public void addItem( Bestellposition item )
	{
		// only allow to add items if shopping cart
//		if( !currentState.isOrdered() )
			items.add(item);
	}

	public float getTotalPrice()
	{
		float totalPrice = 0;
		
		for( Bestellposition item : items )
		{
			totalPrice += (item.getPricePerUnit() * item.getQuantity());
		}
		
		return totalPrice;
	}
	
	public void setOrderStateOrdered( boolean isOrdered )
	{
		currentState.setOrdered( isOrdered );
	}
	
	public boolean isOrderStateOrdered()
	{
		return currentState.isOrdered();
	}
	
	public void setOrderStatePaid( boolean isPaid )
	{
		currentState.setPaid( isPaid );
	}
	
	public boolean isOrderStatePaid()
	{
		return currentState.isPaid();
	}
	
	public void setOrderStateSending( boolean isSending )
	{
		currentState.setSending( isSending );
	}
	
	public boolean isOrderStateSending()
	{
		return currentState.isSending();
	}
	
	public void setOrderStateSent( boolean isSent )
	{
		currentState.setSent( isSent );
	}
	
	public boolean isOrderStateSent()
	{
		return currentState.isSent();
	}
	
	public void setOrderStateComplete( boolean isComplete )
	{
		currentState.setComplete( isComplete );
	}
	
	public boolean isOrderStateComplete()
	{
		return currentState.isComplete();
	}
	
	/**
	 * shortfunction for setting the order state
	 * @param state 5 entry long boolean array in following order {isOrdered, isPaid, isSending, isSent, isComplete} 
	 */
	public void setOrderState( boolean[] state )
	{
		if( state.length <= 5 )
		{
			setOrderStateOrdered( state[0] );
			setOrderStatePaid( state[1] );
			setOrderStateSending( state[2] );
			setOrderStateSent( state[3] );
			setOrderStateComplete( state[4] );
		}
	}
	
	/**
	 * shortfunction for getting the order state 
	 * @return 5 entry long boolean array in following order {isOrdered, isPaid, isSending, isSent, isComplete} 
	 */
	public boolean[] getOrderState()
	{
		boolean orderState[] =  {isOrderStateOrdered(), isOrderStatePaid(),
				isOrderStateSending(), isOrderStateSent(), isOrderStateComplete() };
		
		return orderState;
	}
	

}
