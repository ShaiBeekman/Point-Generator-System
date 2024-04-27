import io.github.pixee.security.BoundedLineReader;
import java.io.File;
import java.io.IOException;  // Import the IOException class to handle errors */ !2
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.nio.file.Files;
import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JLabel;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
//        import java.io.BufferedWriter; /*imported just in case it could be useful
//        import java.io.PrintWriter;    /*imported just in case it could be useful

//delayed gratification points +1,000,000 points (not yet implemented)
//unlocks (not yet implemented)

//point system for completed tasks and for gaining new access to tasks

//set interest! Gain more points for being interested more in a specific activity (not yet implemented)

//#! Highlighted Note

//file crashes if int point becomes too large, implement fix

public class PointSystem extends JFrame {

    static BigInteger points = new BigInteger("0");
    public static void updatePoints(File newFile1, File newFile2, File newFile3) {
        JFrame frame = new JFrame("Update Text");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);

        JLabel label = new JLabel("This is some text");
        frame.add(label);
        frame.setVisible(true);

        Timer timer = new Timer();
        label.setText("" + points);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                appendTokenStack(newFile1, newFile2, newFile3);
                label.setText("" + getPoints());
                System.out.println("Points: " +points);
            }
        }, 1000,1000);
    }

    public static void main(String[] args) {

        File newFile1 = new File("Points.txt");
        setPoints(newFile1);

        File newFile2 = new File("TimeStack.txt");

        File newFile3 = new File("TokenStack.txt");
        updatePoints(newFile1, newFile2, newFile3);
    }

    public static String appendPointsFile(File filePath1) {
        BigInteger newPoints = new BigInteger("10000000000");// 20 billion

        BigInteger newPoints2 = new BigInteger("0");

        points.add(newPoints);
        FileWriter writer = null;
        BufferedReader reader = null;

        try {
            //read file replace number in file with +20 every second
            if (filePath1.createNewFile()) {
                System.out.println("File created: " + filePath1.getName());

                points = points.add(newPoints);

                writer = new FileWriter(filePath1);
                writer.write("" + points); //adding points to file
                writer.close();

                reader = new BufferedReader(new FileReader(filePath1)); //ll>
                String line = BoundedLineReader.readLine(reader, 5_000_000);
                points = new BigInteger(line);
                return ""+points;
            } else {
//              File already exists. Updating...

                points = points.add(newPoints);

                writer = new FileWriter(filePath1);
                writer.write("" + points); //adding points to file...
                writer.close();

                return "" + points;
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "" + points;
    }

    public static String getPoints() {

        File newFile1 = new File("Points.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(newFile1));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String stuffInReader = "";
        try {
            stuffInReader = BoundedLineReader.readLine(reader, 5_000_000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stuffInReader == null) {
            return "0";
        } else {
            BigInteger pointsInFile = new BigInteger(stuffInReader);
            return pointsInFile.toString();
        }
    }

    public static void setPoints(File filePath1) {
        points = new BigInteger (getPoints());

        File newFile1 = new File("Points.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(newFile1));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String stuffInReader = "";
        try {
            stuffInReader = BoundedLineReader.readLine(reader, 5_000_000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stuffInReader == null) {
            BigInteger pointsInFile = new BigInteger("" + 1000);
            points.add(pointsInFile);
        } else {
            BigInteger pointsInFile = new BigInteger(stuffInReader);
            points.add(pointsInFile);
        }
    }
    public static String appendTimeFile(File file) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timeStamp = dtf.format(now);
        String fileName = file.getName();


        FileWriter writer;
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file + ".");
                writer = new FileWriter(file, true);
                writer.write(timeStamp + "\n");
                writer.close();
            } else {
//                System.out.println("File already exists.");
                writer = new FileWriter(file, true);
                writer.write(timeStamp + "\n");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return timeStamp;
    }

    public static String appendTokenStack(File filePath1, File filePath2, File filePath3) {//rename 3 to "point file"
        BufferedReader reader;
        FileWriter writer;

        String points = appendPointsFile(filePath1);
        String time = appendTimeFile(filePath2);
        String token = "" + points + " points at " + time + "\n";
        try {
            if (filePath3.createNewFile()) {
                System.out.println("File created: " + filePath3 + ".");

                writer = new FileWriter(filePath3, true);
                writer.write(""+token);
                writer.close(); //important to close writer and reader

                reader = new BufferedReader(new FileReader(filePath3));
                String line = BoundedLineReader.readLine(reader, 5_000_000);

                return line;
            } else {
//              File already exists. Updating...!
                writer = new FileWriter(filePath3, true);
                writer.write(""+token);
                writer.close(); //important to close writer and reader

                reader = new BufferedReader(new FileReader(filePath3));
                String line = BoundedLineReader.readLine(reader, 5_000_000);
                return token;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
}
