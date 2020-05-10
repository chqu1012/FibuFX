package de.dc.fibufx.client.event;

public class EventContext<T> {

	private String id;
	private T input;

	public EventContext(String id, T input) {
		this.id = id;
		this.input = input;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public T getInput() {
		return input;
	}

	public void setInput(T input) {
		this.input = input;
	}

}
