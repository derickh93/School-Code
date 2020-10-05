//Derick Hansraj
//10/05/2018
//Exercise
//CS211 Exercise 6

#include <iostream>

//Time Class
class Time {

	//private member variables
	private:
		int hour;
		int minute;
		int second;
		static int count;
		
	//public members
	public:
			
		//Constructors
		Time();
		Time(int h);
		Time(int h, int m);
		Time(int h, int m, int s);
			
		//Inline Accessors functions
		int getHour() const {return hour;}
		int getMinute() const {return minute;}
		int getSecond() const {return second;}
			
		//Inline Mutator functions
		void setHour(int h) { hour = h;}
		void setMinute(int m) { minute = m;}
		void setSecond(int s) { second = s; }
			
		//static Accessor function
		static int getCount();
		
		//Input/ Output functions
		void input();
		void output() const;
			};
			
		//static function initalization
		int Time::count = 0;
	
			
	//default constructor that initalizes time to 0:00:00					
	Time::Time() {
		hour , minute, second = 0;
		count++;
	}
	
	//Parameterized constructor with 1 parameter for the hour
	Time::Time(int h) {
		hour = h;
		minute = 0;
		second = 0;
		count++;
	}
	
	//Parameterized constructor with 2 parameters for the hour and minute
	Time::Time(int h, int m) {
		hour = h;
		minute = m;
		second = 0;
		count++;
	}
	
	//Parameterized constructor with 3 parameters for the hour, minute and second
	Time::Time(int h, int m, int s) {
		hour = h;
		minute = m;
		second = s;
		count++;
	}
	
	//accessor function for static int member count
	int Time::getCount() {
		return count;
	}
	
	//input function that asks the user for the hour, minute, and second
	void Time::input() {
		std::cout << "Enter the Hour: ";
		std::cin >> hour;
		std::cout << "Enter the Minute: ";
		std::cin >> minute;
		std::cout << "Enter the Second: ";
		std::cin >> second;
	}
	
	//output function that displays the hour, minute, and seconds to the console
	void Time::output() const {
		std::cout << hour << ":" << minute << ":" << second << std::endl << "Count: " << count << std::endl;
	}
	
	//main program to test Time class and its functions
	int main() {
		
		Time first;
		first.output();
		first.input();
		first.output();
		
		Time second(3,45,00);
		second.output();
		second.input();
		second.output();
		
		Time third(2);
		third.output();
		
		Time fourth(12,55);
		fourth.output();
		return 0;
	}



