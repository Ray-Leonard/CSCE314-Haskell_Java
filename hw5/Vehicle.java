import java.util.*;

class Vehicle
{
	private int speed;
	private int direction;
	private String owner;
	private int id;
	public static int nextId = 0;
	public static final short TURN_LEFT = -90;
	public static final short TURN_RIGHT = 90;

	public static int highestId()
	{
		return nextId - 1;
	}

	// default constructor
	Vehicle()
	{
		id = nextId++;
		// generate a random speed within the range of 0-120
		speed = (int) (Math.random() * 10000) % 121;
		// direction is within the range of 0 to 359
		direction = (int) (Math.random() * 10000) % 360;
		// any car that was not assigned a driver initially belongs to our god
		owner = "Jesus";
	}

	// param constructor: takes in initial owner's name
	Vehicle(String _owner)
	{
		// calling the default constructor to get some initial values
		this();
		// then set the owner
		owner = _owner;
	}


	// getters
	public int getSpeed()
	{
		return speed;
	}

	public int getDir()
	{
		return direction;
	}

	public String getOwner()
	{
		return owner;
	}

	public int getId()
	{
		return id;
	}

	// modifiers
	public void setSpeed(int s)
	{
		speed = s;
	}

	public void setDir(int d)
	{
		// check the validity of direction
		if(d < 0 || d > 359)
		{
			System.out.println("Invalid direction, fail to set direction!");
			return;
		}

		direction = d;
	}

	public void setOwner(String o)
	{
		owner = o;
	}

	public void setId(int i)
	{
		id = i;
	}

	public String toString()
	{
		String s = "";
		s += "*ID* " + getId() + "\n";
		s += "*Owner* " + getOwner() + "\n";
		s += "*Speed* " + getSpeed() + "\n";
		s += "*Direction* " + getDir() + "\n";

		return s;
	}

	public void changeSpeed(int s)
	{
		setSpeed(s);
	}

	public void stop()
	{
		setSpeed(0);
	}

	public void turn(int d)
	{
		if(d > 359 || d < -359)
		{
			d -= 360 * (d / 360);
		}

		// to check for overflow
		int temp = direction + d;
		if(temp > 359)
		{
			direction = temp - 360;
		}
		else if(temp < 0)
		{
			direction = 360 + temp;
		}
		else
		{
			direction = temp;
		}
	}

	public void turn(final short d)
	{
		int _d = (int) d;
		turn(_d);
	}
}


class VehicleTestP4 
{
	public static void main(String[] args)
	{
		Vehicle[] vehicles = new Vehicle[5];
		String[] names = {"George", "Ray", "Zoom", "Shinn", "Leonard"};

		// create 5 vehicle objects and initialize their values
		for(int i = 0; i < 5; i++)
		{
			Vehicle v = new Vehicle();
			// note that this line after adding constructors for Vehicle will not
			// be needed anymore because that will make the id double if not deleted.
			v.setId(Vehicle.nextId++);
			v.setSpeed(10*(i+1));
			v.setDir(45*i);
			v.setOwner(names[i]);
			vehicles[i] = v;
		}

		// print out the values
		System.out.println("------------------------");
		for(int i = 0; i < 5; i++)
		{
			System.out.println("Vehicle id: " + vehicles[i].getId());
			System.out.println("Vehicle owner: " + vehicles[i].getOwner());
			System.out.println("Current speed: " + vehicles[i].getSpeed());
			System.out.println("Current direction: " + vehicles[i].getDir());
			System.out.println("------------------------");
		}


	}
}


class VehicleTest
{
	public static void main(String[] args)
	{
		Vehicle[] vehicles = new Vehicle[5];

		// create 5 vehicle objects and initialize their values
		Vehicle v1 = new Vehicle();
		vehicles[0] = v1;
		Vehicle v2 = new Vehicle();
		vehicles[1] = v2;
		Vehicle v3 = new Vehicle("Fibonacci");
		vehicles[2] = v3;
		Vehicle v4 = new Vehicle("Trump");
		vehicles[3] = v4;
		Vehicle v5 = new Vehicle("Mickey Mouse");
		vehicles[4] = v5;

		// print out the values
		System.out.println("------------------------");
		for(int i = 0; i < 5; i++)
		{
			System.out.println("Vehicle id: " + vehicles[i].getId());
			System.out.println("Vehicle owner: " + vehicles[i].getOwner());
			System.out.println("Current speed: " + vehicles[i].getSpeed());
			System.out.println("Current direction: " + vehicles[i].getDir());
			System.out.println("------------------------");
		}

		// now try printing out the values using toString()
		System.out.println("\n----test for toString()----");
		for(int i = 0; i < 5; i++)
		{
			System.out.println(vehicles[i]);
		}

		// print the highest id number used so far
		System.out.println("Highest ID number: " + Vehicle.highestId());

		System.out.println("\n----test for changeSpeed() and stop()----");
		// try to change the speed for Trump's car and stop Mickey Mouse's car
		v4.changeSpeed(2333);
		v5.stop();
		// then print out the fields for both vehicles
		System.out.println(v4);
		System.out.println(v5);

		System.out.println("\n----test for turn()----");
		// try to turn v3 and v4
		v4.turn(230);
		v5.turn(Vehicle.TURN_RIGHT);
		System.out.println(v4);
		System.out.println(v5);

	}
}