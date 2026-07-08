package org.objectTest;

public class Person {
	long id;

	public Person(long id) {
		super();
		this.id = id;
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null && obj instanceof Person)
		{
			return id == ((Person)obj).id;
		}
		else
		{
			return false;
		}
	}
}
