//Jonathan Phu
//500776576
//March 21,2017
//CPS209

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;


public class AppointmentFrame extends JFrame {
	
	//size constants for GUI
	private static final int FRAME_WIDTH = 750;
	private static final int FRAME_HEIGHT = 800;
	
	private static final int APPOINTMENT_ROWS = 10;
	private static final int APPOINTMENT_COLUMNS = 10;
	
	private static final int DESCRIPTION_ROWS = 5;
	private static final int DESCRIPTION_COLUMNS = 20;
	
	//declaring reference variables 
	JTextField dayField;
	JTextField monthField;
	JTextField yearField;
	JTextField hourField;
	JTextField minuteField;
	
	JTextField firstField;
	JTextField lastField;
	JTextField telField;
	JTextField emailField;
	JTextField addressField;
	static JTextArea appointmentArea;
	static JTextArea descriptionArea;
	
	private JLabel dateLabel;
	private JLabel monthLabel;
	private Calendar currentDate;
	private SimpleDateFormat sdf;
	private SimpleDateFormat sdfMonth;
	//declare list of appointment objects
	ArrayList<Appointment> appointments;
	//stack of appointment objects
	Stack<Appointment> recall = new Stack<Appointment>();
	
	
	public AppointmentFrame(){
		//creating objects for previously declared reference variables with sizes as parameters
		currentDate = new GregorianCalendar();
		sdf = new SimpleDateFormat();
		appointments = new ArrayList<Appointment>();
		
		minuteField = new JTextField(4);
		hourField = new JTextField(4);
		dayField = new JTextField(2);
		monthField = new JTextField(2);
		yearField = new JTextField(4);
		
		firstField = new JTextField();
	    lastField = new JTextField();
		telField = new JTextField();
		emailField = new JTextField();
		addressField = new JTextField();
		
		descriptionArea = new JTextArea(DESCRIPTION_ROWS,DESCRIPTION_COLUMNS);
		appointmentArea = new JTextArea(APPOINTMENT_ROWS,APPOINTMENT_COLUMNS);
		
		//reads contacts file and checks if there are any errors with reading
		try {
			Contacts.readContactsFile();
		} catch (Exception e) {
			Contacts.contacts.clear();
			descriptionArea.setText("ERROR READING FILE");
		}
		
		//set layout of frame to BorderLayout
		this.setLayout(new BorderLayout());
		
		//add formatted date label to top of frame
		sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
		dateLabel = new JLabel(sdf.format(currentDate.getTime()));
		add(dateLabel, BorderLayout.NORTH);
		
		//add appointment display JTextArea
		appointmentArea.setEditable(false);
		add(appointmentArea, BorderLayout.CENTER);
		
		//add control panel using a method and setting layout then set size of frame
		add(createControlPanel(), BorderLayout.SOUTH);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}
		
	
	public JPanel createControlPanel(){
		//creating JPanel objects
		JPanel controlPanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel actionPanel = new JPanel();
		JPanel descriptionPanel = new JPanel();
		JPanel contactPanel = new JPanel();
		//set control panel layout to GridLayout
		controlPanel.setLayout(new GridLayout(2,2));
		
		
		//------------------------------------------------
		//	Date panel of the control panel
		//------------------------------------------------
		//set layout and add date panel to control panel
		datePanel.setBorder(BorderFactory.createTitledBorder("Date"));
		controlPanel.add(datePanel);
		datePanel.setLayout(new GridLayout(3,1));
		
		//creating sub panels to add to date panel
		JPanel dateSubPanel1 = new JPanel();
		JPanel dateSubPanel2 = new JPanel();
		JPanel dateSubPanel3 = new JPanel();
		
		//creating and adding JButtons to first date sub panel
		dateSubPanel1.setLayout(new FlowLayout(1));
		JButton leftButton = new JButton("<");
		JButton rightButton = new JButton(">");
		dateSubPanel1.add(leftButton);
		dateSubPanel1.add(rightButton);
		
		//creating and adding JLabels and JTextFields to second date sub panel
		dateSubPanel2.setLayout(new FlowLayout(1));
		dateSubPanel2.add(new JLabel("Day"));
		dateSubPanel2.add(dayField);
		dateSubPanel2.add(new JLabel("Month"));
		dateSubPanel2.add(monthField);
		dateSubPanel2.add(new JLabel("Year"));
		dateSubPanel2.add(yearField);
		
		//creating and adding JButton to third date sub panel
		dateSubPanel3.setLayout(new FlowLayout(1));
		JButton showButton = new JButton("Show");
		JButton todayButton = new JButton("Today");
		dateSubPanel3.add(showButton);
		dateSubPanel3.add(todayButton);
		
		//adding sub panels to main date panel
		datePanel.add(dateSubPanel1);
		datePanel.add(dateSubPanel2);
		datePanel.add(dateSubPanel3);
		
		
		//------------------------------------------------
		//	Contact panel of the control panel
		//------------------------------------------------
		//set layout and add contactpanel to controlpanel
		contactPanel.setBorder(BorderFactory.createTitledBorder("Contact"));
		controlPanel.add(contactPanel);
		contactPanel.setLayout(new GridLayout(3,1));
		
		JPanel contactSubPanel1 = new JPanel();
		JPanel contactSubPanel2 = new JPanel();
		JPanel contactSubPanel3 = new JPanel();
		//add labels and textfields
		contactSubPanel1.setLayout(new GridLayout(4,2));
		contactSubPanel1.add(new JLabel("First Name"));
		contactSubPanel1.add(new JLabel("Last Name"));
		contactSubPanel1.add(firstField);
		contactSubPanel1.add(lastField);
		contactSubPanel1.add(new JLabel("tel"));
		contactSubPanel1.add(new JLabel("email"));
		contactSubPanel1.add(telField);
		contactSubPanel1.add(emailField);
		
		contactSubPanel2.setLayout(new GridLayout(2,1));
		contactSubPanel2.add(new JLabel("address"));
		contactSubPanel2.add(addressField);
		//add buttons
		JButton findButton = new JButton("Find");
		JButton clearButton = new JButton("Clear");
		contactSubPanel3.add(findButton);
		contactSubPanel3.add(clearButton);
		
		contactPanel.add(contactSubPanel1);
		contactPanel.add(contactSubPanel2);
		contactPanel.add(contactSubPanel3);
		
		//------------------------------------------------
		//	Action panel of the control panel
		//------------------------------------------------
		//set layout and add action panel to control panel
		actionPanel.setBorder(BorderFactory.createTitledBorder("Appointment"));
		controlPanel.add(actionPanel);
		actionPanel.setLayout(new GridLayout(2,1));
		
		//creating sub panels to add to action panel
		JPanel actionSubPanel1 = new JPanel();
		JPanel actionSubPanel2 = new JPanel();
		
		//create and add JLabels and JTextFields to first sub panel
		actionSubPanel1.setLayout(new FlowLayout(1));
		actionSubPanel1.add(new JLabel("Hour"));
		actionSubPanel1.add(hourField);
		actionSubPanel1.add(new JLabel("Minute"));
		actionSubPanel1.add(minuteField);
	
		//create and add JButtons to second sub panel
		actionSubPanel2.setLayout(new FlowLayout(1));
		JButton createButton = new JButton("CREATE");
		JButton cancelButton = new JButton("CANCEL");
		JButton recallButton = new JButton("RECALL");
		actionSubPanel2.add(createButton);
		actionSubPanel2.add(cancelButton);
		actionSubPanel2.add(recallButton);
		
		//add sub panels to main action panel
		actionPanel.add(actionSubPanel1);
		actionPanel.add(actionSubPanel2);
		
		
		//------------------------------------------------
		//	Description panel for control panel
		//------------------------------------------------
		//set to FlowLayout and adds description JTextArea to description panel
		descriptionPanel.setBorder(BorderFactory.createTitledBorder("Description"));
		descriptionPanel.setLayout(new FlowLayout(1));
		descriptionPanel.add(descriptionArea);
		
		descriptionArea.setLineWrap(true);
	
		controlPanel.add(descriptionPanel);
		
		//------------------------------------------------
		//------------------------------------------------
		
		
		//connect buttons to appropriate classes
		ActionListener previousListener = new PreviousDayListener();
	    leftButton.addActionListener(previousListener);
	    
	    ActionListener nextListener = new NextDayListener();
	    rightButton.addActionListener(nextListener);
	    
	    ActionListener showDayListener = new ShowListener();
	    showButton.addActionListener(showDayListener);
	    
	    ActionListener createAppointmentListener = new CreateListener();
	    createButton.addActionListener(createAppointmentListener);
	    
	    ActionListener cancelAppointmentListener = new CancelListener();
	    cancelButton.addActionListener(cancelAppointmentListener);
	    
	    ActionListener findListener = new FindListener();
	    findButton.addActionListener(findListener);
	    
	    ActionListener clearListener = new ClearListener();
	    clearButton.addActionListener(clearListener);
	    
	    ActionListener recallListener = new RecallListener();
	    recallButton.addActionListener(recallListener);
	    
	    ActionListener todayListener = new TodayListener();
	    todayButton.addActionListener(todayListener);
		
		//return control panel
		return controlPanel;
		
	}
	
	
	
	
	//
	//	HELPER METHODS
	//
	
	
	public void printAppointments(){
		for (int i = 0; i < appointments.size(); i++){
			//prints appointments that occur on current date shown in GUI
			if (appointments.get(i).occursOn(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH))){
				//checks if either telephone or email is empty
				if ( appointments.get(i).getPerson().getTelephone().isEmpty() || 
						appointments.get(i).getPerson().getEmail().isEmpty() )
					appointmentArea.append(appointments.get(i).print() + "\n\n");
				else{
					appointmentArea.append(appointments.get(i).print() + 
							appointments.get(i).getPerson().toString() + "\n\n");
				}
			}
		}
	}
	
	
	public void createAppointment(){
		//get dates and put them into int variables
		String description = descriptionArea.getText();
		Appointment newAppointment;
		Person newPerson;
		boolean found = false;
		int year = currentDate.get(Calendar.YEAR);
		int month = currentDate.get(Calendar.MONTH);
		int day = currentDate.get(Calendar.DAY_OF_MONTH);
		int hour = Integer.parseInt(hourField.getText());
		//get contact info and creates new person object
		String first = firstField.getText();
		String last = lastField.getText();
		String address = addressField.getText();
		String tel = telField.getText();
		String email = emailField.getText();
		newPerson = new Person(last, first, tel, address, email);
		
		int minute;
		//sets minute to 0 if minute JTextField is empty or 0s, otherwise sets minute to input given
		if (minuteField.getText().isEmpty() || minuteField.getText().equals("0") || minuteField.getText().equals("00"))
			minute = 0;
		else
			minute = Integer.parseInt(minuteField.getText());
		
		//checks if an appointment object with current date is found
		for (int i = 0; i < appointments.size(); i++){
			if ( findAppointment(year, month, day, hour, minute, i) ){
				found = true; 
				break;
			}
		}
		
		//prints CONFLICT if there is already an object with same time and date, otherwise create new appointment
		if ( found ){
			descriptionArea.setText("CONFLICT!!");
		}
		else {
			newAppointment = new Appointment(year, month, day, hour, minute, description, newPerson);
			//adds new appointment object to arraylist then sorts the arraylist
			appointments.add(newAppointment);
			Collections.sort(appointments);
			recall.push(newAppointment);
		}	


	}

	
	public void cancelAppointment(){
		int i;
		Stack<Appointment> tmp = new Stack<Appointment>();
		//get dates and put them into int variables
		int year = currentDate.get(Calendar.YEAR);
		int month = currentDate.get(Calendar.MONTH);
		int day = currentDate.get(Calendar.DAY_OF_MONTH);
		int hour = Integer.parseInt(hourField.getText());
		int minute;
		if (minuteField.getText().isEmpty() || minuteField.getText().equals("0") || minuteField.getText().equals("00"))
			minute = 0;
		else
			minute = Integer.parseInt(minuteField.getText());
		
		//search list and remove any found appointment
		for (i = 0; i < appointments.size(); i++){
			//pops from main stack and push to temporary stack
			tmp.add(recall.pop());
			if ( findAppointment(year, month, day, hour, minute, i) ){
				appointments.remove(i);
				//remove found appointment from stack
				tmp.pop();
				break;
			}
		}
		//add appointments back to stack
		for (int j = 0; j < i; j++){
			recall.push(tmp.pop());
		}
	}
	
	
	//given the date, time, and index, checks if there is an appointment that exists
	public boolean findAppointment(int findYear, int findMonth, int findDay, int findHour, int findMinute, int index){
			if (appointments.get(index).getYear() == findYear && appointments.get(index).getMonth() == findMonth &&
				appointments.get(index).getDay() == findDay && appointments.get(index).getHour() == findHour &&
				appointments.get(index).getMinute() == findMinute){
				return true;
			}
		return false;
	}
	
	
	
	//
	// ActionListener classes
	//
	class PreviousDayListener implements ActionListener{
		public void actionPerformed(ActionEvent click){
			//subtract a day from current date
			currentDate.add(Calendar.DAY_OF_MONTH, -1);
			dateLabel.setText(sdf.format(currentDate.getTime()));
			//remove any text and reprints appointments for current date
			appointmentArea.setText("");
			printAppointments();
		}
	}
	
	class NextDayListener implements ActionListener{
		public void actionPerformed(ActionEvent click){
			//add a day to current date
			currentDate.add(Calendar.DAY_OF_MONTH, 1);
			dateLabel.setText(sdf.format(currentDate.getTime()));
			//remove any text and reprints appointments for current date
			appointmentArea.setText("");
			printAppointments();
		}
	}
	
	class ShowListener implements ActionListener{
		public void actionPerformed(ActionEvent click){
			//set current date to given input
			currentDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayField.getText()));
			currentDate.set(Calendar.MONTH, Integer.parseInt(monthField.getText()) - 1);
			currentDate.set(Calendar.YEAR, Integer.parseInt(yearField.getText()));
			dateLabel.setText(sdf.format(currentDate.getTime()));
	
			appointmentArea.setText("");
			printAppointments();
		}
	}
	
	class CreateListener implements ActionListener{
		public void actionPerformed(ActionEvent click){
			//remove any text and calls create appointment method then print appointment method
			appointmentArea.setText("");
			createAppointment();
			printAppointments();
		}
	}
	
	class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent click) {
			//remove any text and calls cancel appointment method then print appointment method
			appointmentArea.setText("");
			cancelAppointment();
			printAppointments();
		}
	}
	
	class FindListener implements ActionListener{
		public void actionPerformed(ActionEvent click) {
			Person personFound;
			//checks if last name field is empty, and if it is not then search using that parameter
			if (!lastField.getText().isEmpty()){
				personFound = Contacts.FindPerson(lastField.getText(), firstField.getText());
				//if no person found output to description area
				if (personFound == null)
					descriptionArea.setText("No such person found.");
				else{
					//else set text fields
					lastField.setText(personFound.getLast());
					firstField.setText(personFound.getFirst());
					addressField.setText(personFound.getAddress());
					telField.setText(personFound.getTelephone());
					emailField.setText(personFound.getEmail());
				}
			}
			//else if no last name given check telephone
			else if (!telField.getText().isEmpty()){
				personFound = Contacts.FindPersonTelephone(telField.getText());
				if (personFound == null)
					descriptionArea.setText("No such person found.");
				else{
					lastField.setText(personFound.getLast());
					firstField.setText(personFound.getFirst());
					addressField.setText(personFound.getAddress());
					telField.setText(personFound.getTelephone());
					emailField.setText(personFound.getEmail());
				}
			}
			//else if no telephone given check email
			else if (!emailField.getText().isEmpty()){
				personFound = Contacts.FindPersonEmail(emailField.getText());
				if (personFound == null)
					descriptionArea.setText("No such person found.");
				else{
					lastField.setText(personFound.getLast());
					firstField.setText(personFound.getFirst());
					addressField.setText(personFound.getAddress());
					telField.setText(personFound.getTelephone());
					emailField.setText(personFound.getEmail());
				}
			}
			//default output
			else
				descriptionArea.setText("Please enter a valid search field");
		}
		
	}
	
	class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent click) {
			//sets text fields to empty string
			lastField.setText("");
			firstField.setText("");
			addressField.setText("");
			telField.setText("");
			emailField.setText("");
		}
	}
	
	class RecallListener implements ActionListener{
		public void actionPerformed(ActionEvent click) {
			//set current date using peek()
			currentDate.set(Calendar.DAY_OF_MONTH, recall.peek().getDay());
			currentDate.set(Calendar.MONTH, recall.peek().getMonth());
			currentDate.set(Calendar.YEAR, recall.peek().getYear());
			dateLabel.setText(sdf.format(currentDate.getTime()));
			//set fields using peek()
			minuteField.setText("" + recall.peek().getMinute());
			hourField.setText("" + recall.peek().getHour());
			
			appointmentArea.setText("");
			printAppointments();
		}
	}
	

	class TodayListener implements ActionListener{
		public void actionPerformed(ActionEvent click) {
			//create a new calendar object with default current date
			Calendar cal = new GregorianCalendar();
			currentDate.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
			currentDate.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			currentDate.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			dateLabel.setText(sdf.format(currentDate.getTime()));
			
			appointmentArea.setText("");
			printAppointments();
		}
	}
	
	
	
}