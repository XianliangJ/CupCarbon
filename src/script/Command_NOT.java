package script;

import wisen_simulation.SimLog;
import device.SensorNode;

public class Command_NOT extends Command {

	protected String arg1 = "";
	protected String arg2 = "";
	
	public Command_NOT(SensorNode sensor, String arg1, String arg2) {
		this.sensor = sensor ;
		this.arg1 = arg1 ;
		this.arg2 = arg2 ;
	}

	@Override
	public double execute() {
		String v = sensor.getScript().getVariableValue(arg2);
		System.out.println("-->"+v);
		int z = 0;
		z = ~Integer.parseInt(v);
		System.out.println("-->"+z);
		SimLog.add("S" + sensor.getId() + " " + arg1 + " = ~" + Integer.valueOf(v) + " -> " + z);
		sensor.getScript().addVariable(arg1, "" + z);
		return 0 ;
	}

	@Override
	public String toString() {
		return "NOT";
	}
}
