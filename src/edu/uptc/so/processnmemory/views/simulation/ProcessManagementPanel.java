package edu.uptc.so.processnmemory.views.simulation;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import com.spirit.custom.SButton;
import com.spirit.custom.SPanel;
import com.spirit.custom.SSpinner;

import edu.uptc.so.processnmemory.config.Config;
import edu.uptc.so.processnmemory.models.Process;
import edu.uptc.so.processnmemory.views.queue.CPUPanel;

public class ProcessManagementPanel extends SPanel {
	private static final long serialVersionUID = 513817896876762788L;

	public ProcessManagementPanel(CPUPanel cpuPanel) {
		this.init(cpuPanel);
	}

	private void init(CPUPanel cpuPanel) {
		this.setPreferredSize(220, 0);
		SPanel content = new SPanel("Agregar proceso");
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.setPreferredSize(150, 250);
		SPanel pidP = new SPanel("PID");
		pidP.setSize(200, 35);
		JLabel pidL;
		pidP.add(pidL = new JLabel("P " + Config.currentPID));

		SPanel exTimeP = new SPanel("Tiempo de ejecución");
		SSpinner exTimeSpinner;
		exTimeP.add(exTimeSpinner = new SSpinner(1));
		exTimeSpinner.setPreferredSize(60, 20);
		exTimeSpinner.setValue(25);

		SPanel sizeP = new SPanel("Tamaño (bytes)");
		SSpinner sizeSpinner;
		sizeP.add(sizeSpinner = new SSpinner(1));
		sizeSpinner.setPreferredSize(60, 20);
		sizeSpinner.setValue(30);

		SPanel confirmP = new SPanel();
		confirmP.add(new SButton("Ok", (ActionEvent e) -> {
			pidL.setText("P " + (Config.currentPID + 1));
			cpuPanel.addProcess(new Process("P " + Config.currentPID++, exTimeSpinner.getNumberValue(),
					sizeSpinner.getNumberValue()));
		}));

		content.add(pidP);
		content.add(exTimeP);
		content.add(sizeP);
		content.add(confirmP);

		this.add(content);
	}
}
