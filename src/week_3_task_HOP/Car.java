package week_3_task_HOP;


class CarClass{
	private String carNum;
	private String carBreand;
	private int wheel;
	private String color;
	private double milage;
	private boolean engineOn;
	private int speed;
	
	public CarClass(String carNum, String carBreand, int wheel, String color, double milage) {
		this.carNum = carNum;
		this.carBreand = carBreand;
		this.wheel = wheel;
		this.color = color;
		this.milage = milage;
		this.engineOn=false;
		this.speed=0;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getCarBreand() {
		return carBreand;
	}

	public void setCarBreand(String carBreand) {
		this.carBreand = carBreand;
	}

	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getMilage() {
		return milage;
	}

	public void setMilage(double milage) {
		this.milage = milage;
	}
	

	public boolean isEngineOn() {
		return engineOn;
	}

	public void setEngineOn(boolean engineOn) {
		this.engineOn = engineOn;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	
	
	@Override
	public String toString() {
		return "CarClass [carNum=" + carNum + ", carBreand=" + carBreand + ", wheel=" + wheel + ", color=" + color
				+ ", milage=" + milage + ", engineOn=" + engineOn + ", speed=" + speed + "]";
	}

	public void start() {
		if(!engineOn) {
			engineOn=true;
			System.out.println("Car is Start...........");
		}else {
			System.out.println("Car is already Start...........");
		}
	}
	
	public void stop() {
		if(engineOn) {
			engineOn=false;
			System.out.println("Car is stop...........");
		}else {
			System.out.println("Car is already stop...........");
		}
	}
	
    public void accelerate(double amount) {
        if (!engineOn) {
            System.out.println("Start the car first.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Acceleration must be greater than 0.");
            return;
        }

        speed += amount;
        System.out.println("Car accelerated to " + speed + " km/h.");
    }

    public void brake(double amount) {
        if (amount <= 0) {
            System.out.println("Brake amount must be greater than 0.");
            return;
        }

        speed -= amount;

        if (speed < 0) {
            speed = 0;
        }

        System.out.println("Current speed: " + speed + " km/h.");
    }
	
	
	
	
}
public class Car {
	
	 public static void main(String[] args) {

	        CarClass car = new CarClass("WB 32A 6666","Toyota", 4 , "red",123.00);


	        System.out.println(car.toString());

	        car.start();

	        car.accelerate(40);
	        car.accelerate(30);

	        car.brake(20);

	        System.out.println();


	        System.out.println(car.toString());

	        car.brake(50);

	        car.stop();

	        System.out.println();

	        System.out.println(car.toString());
	    }

}
