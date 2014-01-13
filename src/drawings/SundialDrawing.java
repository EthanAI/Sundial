package drawings;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

import javax.swing.*;

public class SundialDrawing extends JPanel {
	static double[] hourLineAngles;
	static int[] lineLabels = new int[13];
	static double gnomonAngle;
	static boolean isNorthernHemisphere;

 // Create a constructor method
 public SundialDrawing(double[] hourLineAngles, int[] lineLabels, double gnomonAngle ,boolean isNorthernHemisphere){
	 super();
	 SundialDrawing.hourLineAngles = hourLineAngles;
	 SundialDrawing.lineLabels = lineLabels;
	 SundialDrawing.gnomonAngle = gnomonAngle;
	 SundialDrawing.isNorthernHemisphere = isNorthernHemisphere;
 }

 //converts the angles on the left side of the 12 line to a x coordinate value
 public double leftSideX(double angle, int length){
	    double x = 800/2 - (length * Math.cos(Math.toRadians(90 + (angle))));
	    return x;
	}

 //converts the angles on the left side of the 12 line to a y coordinate value
 public double leftSideY(double angle, int length){
	    double y = 620 - (length * Math.sin(Math.toRadians(90 + (angle))));
	    return y;
	}
 //converts the angles on the right side of the 12 line to a x coordinate value
 public double rightSideX(double angle, int length){
	    double x = 800/2 - (length * Math.sin(Math.toRadians(180+(angle))));
	    return x;
	}
 //converts the angles on the right side of the 12 line to a y coordinate value
 public double rightSideY(double angle, int length){
	    double y = 620 + (length * Math.cos(Math.toRadians(180+(angle))));
	    return y;
	}
 //converts the gnomon angle to a x coordinate value
 public double gX(double angle, int length){
		    double x = 1340 - (length * Math.cos(Math.toRadians(90-(angle))));
		    return x;
		}
//converts the gnomon angle to a y coordinate value
 public double gY(double angle, int length){
		    double y = 210 - (length * Math.sin(Math.toRadians(90-(angle))));
		    return y;
		}
 //paint method that is written specifically for the sundial.
 public void paint(Graphics g) {
	 Graphics2D g2 = (Graphics2D) g;
		 
	 /**"
	  * dial" of the sundial 
	  * draws out each line of the sundial accordingly and adds labels to each line
	  * all lines are the same length "350", except for the two 6 hour lines if they are < -90 or >90
	  * all lines start at position 400,600 and go out from there based  on the angles.
	 **/
	 double x;
	 double y;
	 if(hourLineAngles[0] < -100.00) {
		 x = leftSideX(hourLineAngles[0], 245);
		 y = leftSideY(hourLineAngles[0], 245);
		 Line2D line1 = new Line2D.Double(400, 600, x, y);
		 g2.draw(line1);
		 g2.drawString(Integer.toString(lineLabels[0]), (int)x, (int)y-10);
	} else {
		x = leftSideX(hourLineAngles[0], 350);
		y = leftSideY(hourLineAngles[0], 350);
		Line2D line1 = new Line2D.Double(400, 600, x, y);
		g2.draw(line1);
		g2.drawString(Integer.toString(lineLabels[0]), (int)x, (int)y+12);
	}
	 
	 x = leftSideX(hourLineAngles[1], 350);
	 y = leftSideY(hourLineAngles[1], 350);
	 Line2D line2 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line2);
	 g2.drawString(Integer.toString(lineLabels[1]), (int)x, (int)y);
	 
	 x = leftSideX(hourLineAngles[2], 350);
	 y = leftSideY(hourLineAngles[2], 350);
	 Line2D line3 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line3);
	 g2.drawString(Integer.toString(lineLabels[2]), (int)x, (int)y);
	 
	 x = leftSideX(hourLineAngles[3], 350);
	 y = leftSideY(hourLineAngles[3], 350);
	 Line2D line4 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line4);
	 g2.drawString(Integer.toString(lineLabels[3]), (int)x, (int)y);
	 
	 x = leftSideX(hourLineAngles[4], 350);
	 y = leftSideY(hourLineAngles[4], 350);
	 Line2D line5 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line5);
	 g2.drawString(Integer.toString(lineLabels[4]), (int)x, (int)y);
	 
	 x = leftSideX(hourLineAngles[5], 350);
	 y = leftSideY(hourLineAngles[5], 350);
	 Line2D line6 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line6);
	 g2.drawString(Integer.toString(lineLabels[5]), (int)x, (int)y);
	 
	 x= leftSideX(hourLineAngles[6], 350);
	 y = leftSideY(hourLineAngles[6], 350);
	 Line2D line7 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line7);
	 g2.drawString(Integer.toString(lineLabels[6]), (int)x-15, (int)y);
	 
	 x = rightSideX(hourLineAngles[7], 350);
	 y = rightSideY(hourLineAngles[7], 350);
	 Line2D line8 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line8);
	 g2.drawString(Integer.toString(lineLabels[7]), (int)x, (int)y);
	 
	 x = rightSideX(hourLineAngles[8], 350);
	 y = rightSideY(hourLineAngles[8], 350);
	 Line2D line9 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line9);
	 g2.drawString(Integer.toString(lineLabels[8]), (int)x, (int)y);
	 
	 x = rightSideX(hourLineAngles[9], 350);
	 y = rightSideY(hourLineAngles[9], 350);
	 Line2D line10 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line10);
	 g2.drawString(Integer.toString(lineLabels[9]), (int)x, (int)y);
		 
	 x = rightSideX(hourLineAngles[10], 350);
	 y = rightSideY(hourLineAngles[10], 350);
	 Line2D line11 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line11);
	 g2.drawString(Integer.toString(lineLabels[10]), (int)x, (int)y);
		 
	 x = rightSideX(hourLineAngles[11], 350);
	 y = rightSideY(hourLineAngles[11], 350);
	 Line2D line12 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line12);
	 g2.drawString(Integer.toString(lineLabels[11]), (int)x, (int)y);
		 
	 if(hourLineAngles[12] > 100.00) {
		 x = rightSideX(hourLineAngles[12], 245);
		 y = rightSideY(hourLineAngles[12], 245);
		 Line2D lines13 = new Line2D.Double(400, 600, x, y);
		 g2.draw(lines13);
		 g2.drawString(Integer.toString(lineLabels[12]), (int)x, (int)y-10);
	}else {
		x = rightSideX(hourLineAngles[12], 350);
		y = rightSideY(hourLineAngles[12], 350);
		Line2D lines13 = new Line2D.Double(400, 600, x, y);
		g2.draw(lines13);
		g2.drawString(Integer.toString(lineLabels[12]), (int)x, (int)y+12);
	}

	 //lowers the positioning of the AM and PM labels if needed
	 if(hourLineAngles[0] < -90.00 || hourLineAngles[12] > 90.00) {
		 g2.drawString("AM", 300, 675);
		 g2.drawString("PM", 500, 675);
	}else {
		g2.drawString("AM", 300, 650);
		g2.drawString("PM", 500, 650);
	}
	 
	 //setting up the line that the user will place the gnomon on
	 Line2D gline = new Line2D.Double(400, 600, 400, 235);
	 g2.draw(gline);
	 Line2D glineleft = new Line2D.Double(400, 235, 390, 245);
	 g2.draw(glineleft);
	 Line2D glineright = new Line2D.Double(400, 235, 410, 245);
	 g2.draw(glineright);
		 
	 //checks if the user is located in the northern or southern hemisphere, and prints the direction accordingly
	 if(isNorthernHemisphere) {
		 g2.drawString("True North", 375, 225);
	 }else {
		 g2.drawString("True South", 375, 225);
	 }
		 
	 //setting up the gnomon triangle
	 Line2D linega = new Line2D.Double(1340, 5, 1340, 200);
	 g2.draw(linega);
	 Line2D linego = new Line2D.Double(1340, 5, 0, 5);
	 g2.draw(linego);
	 
	 g2.drawString("a", 1343, 100);
		 
	 x=gX(gnomonAngle, 2000);
	 y=gY(gnomonAngle, 2000);
	 Line2D linegh = new Line2D.Double(1340, 200, x, y);
	 g2.draw(linegh);
		 
	 //draws the horizontal and vertical lines for the user to cut
	 Line2D middlecut = new Line2D.Double (1500, 210, 0, 210);
	 g2.draw(middlecut);
	 Line2D verticalcut = new Line2D.Double(800, 210, 800, 800);
	 g2.draw(verticalcut);
		 
	 //listing the instructions
	 g2.drawString("Step 1: Print out this screen with the following settings:", 820, 230);
	 g2.drawString("Orientation: Landscape", 860, 245);
	 g2.drawString("IMPORTANT!: All margins: 1 (or else sizing will be off)", 860, 260);
	 g2.drawString("IMPORTANT!: Print Range: Pages 1 to 1", 860, 275);
	 g2.drawString("Step 2: Cut the 2 lines surrounding the sundial", 820, 305);
	 g2.drawString("Step 3: Cut out a triangle where the lines meet to create a gnomon", 820, 335);
	 g2.drawString("(if the lines do not meet, just cut it till the lines stop,", 860, 350);
	 g2.drawString("then cut a straight line parallel to the border)", 860, 365);
	 g2.drawString("Step 4: Attach the side labeled 'a' on the gnomon to the sundial", 820, 395);
	 g2.drawString("on the long line with the arrow.", 860, 410);
	 g2.drawString("Position the sundial with the arrow pointing to the direction specified", 860, 425); 
	 g2.drawString("at the top.", 860, 440);
	 g2.drawString("Now you can use your sundial!", 860, 470);
 }
}

