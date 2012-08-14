package home_automation.main;

import home_automation.rooms.MainBedroom;


public class ArduinoMain {

	// Ip Adress and Port, where the Arduino Server is running on
	private static String serverIP ;
	private static int serverPort;
	
	public static void main(String argv[]) throws Exception {
		MainBedroom main = new MainBedroom();
//		JAXBContext context = JAXBContext
//				.newInstance(new Class[] { Settings.class });
//		Unmarshaller unmarshaller = context.createUnmarshaller();
//		Settings settings = (Settings) unmarshaller
//				.unmarshal(new FileInputStream(
//						"src/main/resources/schemas/settings1.xml"));
//		serverPort = settings.getArduino().get(1).getAddress().getPort();
//		serverIP = settings.getArduino().get(1).getAddress().getHost().getIp();
//		ArduinoCommunication a = ArduinoCommunication.getInstance(serverIP, serverPort);
//
//		ArrayList<Integer> lmotor = new ArrayList<Integer>();
//		lmotor.add(22);
//		lmotor.add(23);
//		
//		Kitchen k = new Kitchen("K");
//		LightApplication light = k.addLight(new LightApplication(LightType.PWM,a,k, "ceiling light", 3));
//		MotorApplication motor = k.addMotor(new MotorApplication(a, k, "blindes", lmotor));
//		Sensor sensor = k.addSensor(new Sensor(SensorType.ANGLE_SENSOR,a,k,"sensor",6));
//		
//		
//		for(int x =0;x<101;x+=4){
//		light.setPWM(x);
//		Thread.sleep(100);
//		}
//		
//		motor.setMotorDirection(true);
//		for(int y=0;y<20;y++){
//		Thread.sleep(100);
//		motor.toggle();
//		Thread.sleep(100);
//		}
//		motor.stopMotor();
//	double y =a.readAnalog(6);
//	
//		Print.ln(String.valueOf(y));
	

//		for(int x =100;x>-3;x--){
//		//	System.out.println("_________________________");
//			l.setPWM(x);
//			Thread.sleep(100);
//			}
//	Thread.sleep(3000);
//	m.stopMotor();
//	a.closeConnection();
		
		
		

		
		
//		System.out.println("----------- Kitchen 1 Lights--------------");
//		ArrayList<LightApplication> kitchenLight =k.getKitchenLights();
//		for(LightApplication l:kitchenLight){
//			if(l.getlightName().equals("ceiling light")){
//				System.out.println("found");
//			System.out.println(l.getPortNo());
//			l.toggle();
//			Thread.sleep(5000);
//			l.toggle();
//			}
//		}
//		a.closeConnection();
//		
//		System.out.println("----------- All Lights--------------");
//		
//		ArrayList<LightApplication> allLight =ClassList.getAllLights();
//		for(LightApplication l:allLight){
//			if(l.getlightName().equals("floor light"))
//				System.out.println("++++++++++++++++++++++");
//			System.out.print(l.getlightName()+" ");
//			System.out.print(l.getPortNo()+" ");
//			System.out.println(l.getRoom().getRoomName());
//		}
	}
}
