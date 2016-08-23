import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Created by Łukasz on 2016-08-22.
 */
public class LifeTimeInSeconds extends JFrame
{
    private static JPanel mainPanel = new JPanel();
    private static JTextField birthDay = new JTextField("" , 9);
    private static JTextField birthMonth = new JTextField("" , 9);
    private static JTextField birthYear = new JTextField("" , 9);
    private static JLabel birthdayLabel = new JLabel("Your date of birth:");
    private static JLabel dayLabel = new JLabel("Day");
    private static JLabel monthLabel = new JLabel("Month");
    private static JLabel yearLabel = new JLabel("Year");
    private static JLabel lifeTimeLabel = new JLabel("Enter your date of birth.");
    private static JButton calculateButton = new JButton("Calculate");
    private static SpringLayout mainPanelLayout = new SpringLayout();
    private static Font mainFont = new Font("Arial" , Font.PLAIN , 20);
    private static Calendar currentDate;
    private static Calendar birthDate = Calendar.getInstance();
    private static long timeInSeconds;

    public LifeTimeInSeconds()
    {
        setMinimumSize(new Dimension(400,300));
        setMaximumSize(new Dimension(400,300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Lifetime Calculator");
        setup();
        add(mainPanel);
        pack();
    }

    private static void setup()
    {
        mainPanel.setLayout(mainPanelLayout);

        birthdayLabel.setFont(mainFont);
        birthdayLabel.setPreferredSize(new Dimension(180,50));
        dayLabel.setFont(mainFont);
        monthLabel.setFont(mainFont);
        yearLabel.setFont(mainFont);
        lifeTimeLabel.setFont(mainFont);
        lifeTimeLabel.setMinimumSize(new Dimension(0,0));
        birthDay.setFont(mainFont);
        birthMonth.setFont(mainFont);
        birthYear.setFont(mainFont);

        mainPanel.add(birthdayLabel);
        mainPanelLayout.putConstraint(SpringLayout.WEST , birthdayLabel , 5 , SpringLayout.WEST , mainPanel);
        mainPanelLayout.putConstraint(SpringLayout.NORTH , birthdayLabel , 5 , SpringLayout.NORTH , mainPanel);
        mainPanel.add(dayLabel);
        mainPanelLayout.putConstraint(SpringLayout.WEST , dayLabel , 5 , SpringLayout.WEST , mainPanel);
        mainPanelLayout.putConstraint(SpringLayout.NORTH , dayLabel , 5 , SpringLayout.SOUTH , birthdayLabel);
        mainPanel.add(monthLabel);
        mainPanelLayout.putConstraint(SpringLayout.WEST , monthLabel , 5 , SpringLayout.WEST , mainPanel);
        mainPanelLayout.putConstraint(SpringLayout.NORTH , monthLabel , 5 , SpringLayout.SOUTH , dayLabel);
        mainPanel.add(yearLabel);
        mainPanelLayout.putConstraint(SpringLayout.WEST , yearLabel , 5 , SpringLayout.WEST , mainPanel);
        mainPanelLayout.putConstraint(SpringLayout.NORTH , yearLabel , 5 , SpringLayout.SOUTH , monthLabel);
        mainPanel.add(calculateButton);
        mainPanelLayout.putConstraint(SpringLayout.WEST , calculateButton , 5 , SpringLayout.EAST , birthdayLabel);
        mainPanelLayout.putConstraint(SpringLayout.NORTH , calculateButton , 5 , SpringLayout.SOUTH , yearLabel);
        mainPanel.add(lifeTimeLabel);
        mainPanelLayout.putConstraint(SpringLayout.WEST , lifeTimeLabel , 5 , SpringLayout.WEST , mainPanel);
        mainPanelLayout.putConstraint(SpringLayout.NORTH , lifeTimeLabel , 5 , SpringLayout.SOUTH , calculateButton);
        mainPanel.add(birthDay);
        mainPanelLayout.putConstraint(SpringLayout.WEST , birthDay , 5 , SpringLayout.EAST , birthdayLabel);
        mainPanelLayout.putConstraint(SpringLayout.BASELINE , birthDay , 0 , SpringLayout.BASELINE , dayLabel);
        mainPanel.add(birthMonth);
        mainPanelLayout.putConstraint(SpringLayout.WEST , birthMonth , 5 , SpringLayout.EAST , birthdayLabel);
        mainPanelLayout.putConstraint(SpringLayout.BASELINE , birthMonth , 0 , SpringLayout.BASELINE , monthLabel);
        mainPanel.add(birthYear);
        mainPanelLayout.putConstraint(SpringLayout.WEST , birthYear , 5 , SpringLayout.EAST , birthdayLabel);
        mainPanelLayout.putConstraint(SpringLayout.BASELINE , birthYear , 0 , SpringLayout.BASELINE , yearLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws NumberFormatException {
                try
                {
                    currentDate = Calendar.getInstance();
                    birthDate.set(Integer.parseInt(birthYear.getText()) , Integer.parseInt(birthMonth.getText()) - 1 , Integer.parseInt(birthDay.getText()) , 0 , 0 , 0);
                    timeInSeconds = ( currentDate.getTimeInMillis() - birthDate.getTimeInMillis() ) / 1000;
                    if(timeInSeconds < 0 || Integer.parseInt(birthMonth.getText()) > 12 || Integer.parseInt(birthMonth.getText()) < 1
                            || Integer.parseInt(birthDay.getText()) < 1 || Integer.parseInt(birthDay.getText()) > 31)
                    {
                        lifeTimeLabel.setText("Wrong date format.");
                    }
                    else
                    {
                        lifeTimeLabel.setText("Your already live " + timeInSeconds + " seconds.");
                    }
                }
                catch (NumberFormatException nfe)
                {
                    lifeTimeLabel.setText("Wrong date format.");
                }
            }

        });
    }

    public static void main(String [] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LifeTimeInSeconds();
            }
        });
    }
}
