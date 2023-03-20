package application;

import javax.swing.JComboBox;
import javafx.scene.control.Label;

public class DaySelect {
	protected Label lbl_dayText;
	protected JComboBox<String> cbStartTime;
	protected JComboBox<String> cbFinishTime;
	public DaySelect(String day) {
		lbl_dayText.setText(day);
		this.cbStartTime = new JComboBox<String>();
		this.cbFinishTime = new JComboBox<String>();
		setComboBoxes();
	}

	public void setComboBoxes() {
		for(int i=0; i<24; i++) {
			if(i<10){
				cbStartTime.insertItemAt("0"+i+":00", i);
				cbFinishTime.insertItemAt("0"+i+":00",i);
			}
			else{
				cbStartTime.insertItemAt(i+":00", i);
				cbFinishTime.insertItemAt(i+":00",i);
			}
		}
	}

	public JComboBox<String> getCbStartTime() {
		return cbStartTime;
	}

	public JComboBox<String> getCbFinishTime() {
		return cbFinishTime;
	}

}
