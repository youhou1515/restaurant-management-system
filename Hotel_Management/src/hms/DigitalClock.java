package hms;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import java.util.*;
import java.text.*;

public class DigitalClock {

	public static void main(String[] arguments) {

		ClockLabel dateLable = new ClockLabel("date");
		ClockLabel timeLable = new ClockLabel("time");
		timeLable.setForeground(new Color(255, 0, 0));
		ClockLabel dayLable = new ClockLabel("day");

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame f = new JFrame("Digital Clock");
		f.setSize(300, 150);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(new GridLayout(3, 1));

		f.getContentPane().add(dateLable);
		f.getContentPane().add(timeLable);
		f.getContentPane().add(dayLable);

		f.getContentPane().setBackground(Color.black);

		f.setVisible(true);
	}
}

class ClockLabel extends JLabel implements ActionListener {

	String type;
	SimpleDateFormat sdf;

	public ClockLabel(String type) {
		this.type = type;
		setForeground(Color.black);

		switch (type) {
		case "date":
			sdf = new SimpleDateFormat("  MMMM dd yyyy");
			setFont(new Font("sans-serif", Font.PLAIN, 15));
			setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case "time":
			sdf = new SimpleDateFormat("hh:mm:ss a");
			setFont(new Font("sans-serif", Font.PLAIN, 15));
			setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case "day":
			sdf = new SimpleDateFormat("EEEE  ");
			setFont(new Font("sans-serif", Font.PLAIN, 16));
			setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		default:
			sdf = new SimpleDateFormat();
			break;
		}

		Timer t = new Timer(1000, this);
		t.start();
	}

	public void actionPerformed(ActionEvent ae) {
		Date d = new Date();
		setText(sdf.format(d));
	}
}
