package at.paulk.misc;

import java.util.EventObject;

public class EventCellException extends EventObject
{
	private static final long serialVersionUID = 1L;
	private final String message;

	public EventCellException(Object source)
	{
		this(source, "not defined");
	}

	public EventCellException(Object source, String message)
	{
		super(source);
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
