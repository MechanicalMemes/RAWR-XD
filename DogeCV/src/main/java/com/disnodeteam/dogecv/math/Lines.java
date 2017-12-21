
import com.disnodeteam.dogecv.math.Line;
import com.disnodeteam.dogecv.math.MathFTC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.LineSegmentDetector;

public class Lines {


    public static double getAngularDistance(Line line1, Line line2) {
        double ang1 = line1.angle();
        double ang2 = line2.angle();
        double ang_diff = 57.296*(ang1 - ang2);
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(Math.abs(ang_diff));
        list.add(Math.abs(180-Math.abs(ang_diff)));
        list.add(Math.abs(360-Math.abs(ang_diff)));
        return Collections.min(list);
    }

    public static double crossSign(Line line, Point point) {
        double ABx = line.x2 - line.x1;
        double ACx = point.x - line.x1;
        double ABy = line.y2 - line.y1;
        double ACy = point.y - line.y1;
        return Math.signum((ABx*ACy - ABy*ACx));
    }

    public static Line vectorExtend(Line line, double lengthFinal, Size size) {
        double scalar = lengthFinal/line.length();
        line.x2 = (int) (MathFTC.clip(scalar*(line.x2 - line.x1) + line.x1, 0, size.width - 1));
        line.y2 = (int) (MathFTC.clip(scalar*(line.y2 - line.y1) + line.y1, 0, size.height - 1));
        line.point2 = new Point(line.x2,line.y2);
        return line;
    }

    public static List<Line> vectorExtend(List<Line> lines, double lengthFinal, Size size) {
        List<Line> newLines = new ArrayList<Line>();
        for (Line line : lines) {
            newLines.add(vectorExtend(line, lengthFinal, size));
        }
        return newLines;
    }

    public static Line linearExtend(Line line, double scale, Size size) {
        scale *= 2;
        double xN1 = line.x1 + (line.x1 - line.x2)/scale;
        double yN1 = line.y1 + (line.y1 - line.y2)/scale;
        double xN2 = line.x2 + (line.x2 - line.x1)/scale;
        double yN2 = line.y2 + (line.y2 - line.y1)/scale;
        Point p1 = new Point(MathFTC.clip((int)xN1, 0, size.width -1),MathFTC.clip((int)yN1, 0, size.height-1));
        Point p2 = new Point(MathFTC.clip((int)xN2, 0, size.width -1),MathFTC.clip((int)yN2, 0, size.height-1));
        return new Line(p1, p2);
    }

    public static List<Line> linearExtend(List<Line> lines, double scale, Size size) {
        List<Line> newLines = new ArrayList<Line>();
        for (Line line : lines) {
            newLines.add(linearExtend(line, scale, size));
        }
        return newLines;
    }

    public static Line trigExtend(Line line) {
        double angle = Lines.getAngularDistance(line, new Line(new Point(0,0), new Point(100,0)));
        line.x1 = line.x1 + 5000*Math.cos(line.x1*Math.PI/180);
        line.y1 = line.y1 + 5000*Math.cos(line.y1*Math.PI/180);
        line.x2 = line.x2 - 5000*Math.cos(line.x2*Math.PI/180);
        line.y2 = line.y2 - 5000*Math.cos(line.y2*Math.PI/180);
        line.point1 = new Point(line.x1, line.y1);
        line.point2 = new Point(line.x2, line.y2);
        return line;
    }

    public static Line constructLine(Point point, double angle, double length) {
        double dx = Math.cos(angle*Math.PI/180);
        double dy = Math.sin(angle*Math.PI/180);
        Point p1 = new Point(point.x + 0.5*length*dx, point.y + 0.5*length*dy);
        Point p2 = new Point(point.x - 0.5*length*dx, point.y - 0.5*length*dy);
        return new Line(p1,p2);
    }



    public static ArrayList<Line> mergeLines(ArrayList<Line> lines, double angularDeviation, double seperation, double alignmentDeviation) {

        ArrayList<Joint> joints = new ArrayList<Joint>();
        for (List<Line> b : MathFTC.combinations(lines, 2)) {
            Line jointLine = new Line(b.get(0).center(), b.get(1).center());
            if (Lines.getAngularDistance(b.get(0),b.get(1)) < angularDeviation && jointLine.length() < seperation && Lines.getAngularDistance(jointLine, b.get(0)) < alignmentDeviation) {
                joints.add(new Joint(b.get(0),b.get(1),jointLine.center()));
            }
        }

        ArrayList<MergeFocus> mergeCollections = new ArrayList<MergeFocus>();
        for (Joint joint : joints) {
            int close = -1;
            for (int i = 0; i < mergeCollections.size(); i++) {
                Line connection = new Line(mergeCollections.get(i).center,joint.center);
                if(connection.length() < seperation  && Lines.getAngularDistance(connection, joint.line1) < angularDeviation) {
                    close = i;
                    break;
                }
            }
            if (close >= 0) {
                MergeFocus focus = mergeCollections.get(close);
                if(!focus.lines.contains(joint.line1)) {
                    focus.lines.add(joint.line1);
                }
                if(!focus.lines.contains(joint.line2)) {
                    focus.lines.add(joint.line2);
                }
            } else {
                ArrayList<Line> jointLines = new ArrayList<Line>();
                jointLines.add(joint.line1);
                jointLines.add(joint.line2);
                mergeCollections.add(new MergeFocus(joint.center,jointLines));
            }
        }
        for (MergeFocus focus : mergeCollections) {
            Collections.sort(focus.lines);
            Collections.reverse(focus.lines);
            for (Line line : focus.lines) {
                lines.remove(line);
            }
            lines.add(mergeTwoLines(focus.lines.get(0),focus.lines.get(1)));
        }
        return lines;
    }

    private static class Joint {

        Line line1, line2;
        Point center;

        Joint(Line line1, Line line2, Point center) {
            this.center = center;
            this.line1 = line1;
            this.line2 = line2;
        }
    }

    private static class MergeFocus {

        List<Line> lines;
        Point center;

        MergeFocus(Point center, List<Line> lines) {
            this.center = center;
            this.lines = lines;
        }
    }

    private static Line mergeTwoLines(Line line1, Line line2) {
        double lAC = new Line(line1.point1,line2.point1).length();
        double lAD = new Line(line1.point1,line2.point2).length();
        double lBC = new Line(line1.point2,line2.point1).length();
        double lBD =  new Line(line1.point2,line2.point2).length();
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(lAC);
        list.add(lAD);
        list.add(lBC);
        list.add(lBD);
        double max = Collections.max(list);
        Line ret;
        if (max == lAC) {
            ret = new Line(line1.point1,line2.point1);
        } else if (max == lAD) {
            ret = new Line(line1.point1,line2.point2);
        } else if (max == lBC) {
            ret = new Line(line1.point2,line2.point1);
        } else {
            ret = new Line(line1.point2,line2.point2);
        }
        if(Lines.getAngularDistance(ret, line2) < 12 && Lines.getAngularDistance(ret, line1) < 12) {
            return ret;
        } else if (line1.length() > line2.length()) {
            return line1;
        } else {
            return line2;
        }
    }

    public static List<Line> getOpenCvLines(Mat original, int scale, double minLength) {
        Mat raw = new Mat();
        Imgproc.resize(original, raw, new Size((int) (original.size().width/scale), (int) (original.size().height/scale)));
        if(raw.channels() > 1) {
            Imgproc.cvtColor(raw, raw, Imgproc.COLOR_BGR2GRAY);
        }
        Imgproc.equalizeHist(raw, raw);
        Imgproc.blur(raw, raw, new Size(3,3));

        Mat linesM1 = new Mat();
        LineSegmentDetector detector = Imgproc.createLineSegmentDetector(Imgproc.LSD_REFINE_STD, 0.8, 0.6,2.0, 22.5, 0, 0.7, 32);
        detector.detect(raw, linesM1);
        ArrayList<Line> lines = new ArrayList<Line>();
        for (int x = 0; x < linesM1.rows(); x++)  {
            double[] vec = linesM1.get(x, 0);
            Point start = new Point(vec[0],vec[1]);
            Point end = new Point(vec[2], vec[3]);
            Line line = new Line(start, end);
            line = new Line(new Point((int)line.x1*scale, (int) line.y1*scale), new Point((int)line.x2*scale, (int)line.y2*scale));
            if(line.length() > minLength) lines.add(line);
        }
        return lines;
    }

    public static List<Line> resize(List<Line> lines, double scale) {
        List<Line> linesN = new ArrayList<Line>();
        for (Line line : lines) {
            line.resize(scale);
            linesN.add(line);
        }
        return linesN;
    }

    public static Line findRightMost(List<Line> lines) {
        double maxX = 0;
        int maxXi = -1;
        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).center().x > maxX) {
                maxX = lines.get(i).center().x;
                maxXi = i;
            }
        }
        return lines.get(maxXi);
    }

    public static Line findLeftMost(List<Line> lines) {
        double minX = 1000000;
        int minXi = -1;
        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).center().x < minX) {
                minX = lines.get(i).center().x;
                minXi = i;
            }
        }
        return lines.get(minXi);
    }

    public static Line getPerpindicular(Line line, double sign) {
        double angle = Lines.getAngularDistance(line, new Line(new Point(0,0), new Point(100,0)));
        angle += 90;
        double x = line.center().x + 50*Math.cos(angle*Math.PI/180);
        double y = line.center().y + 50*Math.sin(angle*Math.PI/180);
        if(Lines.crossSign(line, new Point(x,y)) != sign) {
            x = line.center().x - 50*Math.cos(angle*Math.PI/180);
            y = line.center().y - 50*Math.sin(angle*Math.PI/180);
        }
        Line perp = new Line(line.center(), new Point(x,y));
        return perp;
    }

    public static double getPerpindicularDistance(Line line1, Line line2) {
        Line perp = Lines.getPerpindicular(line1, Lines.crossSign(line1, line2.center()));
        Line joint = new Line(line1.center(), line2.center());
        return Math.cos(Lines.getAngularDistance(perp, joint)*Math.PI/180)*joint.length();
    }

    public static double getPerpindicularDistance(Line line1, Point point) {
        Line perp = Lines.getPerpindicular(line1, Lines.crossSign(line1, point));
        Line joint = new Line(line1.center(), point);
        return Math.cos(Lines.getAngularDistance(perp, joint)*Math.PI/180)*joint.length();
    }

    public static List<List<Line>> groupLines(ArrayList<Line> lines, double seperation) {

        ArrayList<Joint> joints = new ArrayList<Joint>();
        for (List<Line> b : MathFTC.combinations(lines, 2)) {
            Line jointLine = new Line(b.get(0).center(), b.get(1).center());
            if (Lines.getPerpindicularDistance(b.get(0),b.get(1)) < seperation) {
                joints.add(new Joint(b.get(0),b.get(1),jointLine.center()));
            }
        }

        ArrayList<MergeFocus> mergeCollections = new ArrayList<MergeFocus>();
        for (Joint joint : joints) {
            int close = -1;
            for (int i = 0; i < mergeCollections.size(); i++) {
                if(Lines.getPerpindicularDistance(joint.line1, mergeCollections.get(i).center) < seperation) {
                    close = i;
                    break;
                }
            }
            if (close >= 0) {
                MergeFocus focus = mergeCollections.get(close);
                if(!focus.lines.contains(joint.line1)) {
                    focus.lines.add(joint.line1);
                }
                if(!focus.lines.contains(joint.line2)) {
                    focus.lines.add(joint.line2);
                }
            } else {
                ArrayList<Line> jointLines = new ArrayList<Line>();
                jointLines.add(joint.line1);
                jointLines.add(joint.line2);
                mergeCollections.add(new MergeFocus(joint.center,jointLines));
            }
        }
        List<List<Line>> mergeCollectionsList= new ArrayList<List<Line>>();
        for (MergeFocus focus : mergeCollections) {
            List<Line> list = new ArrayList<Line>();
            for (Line line : focus.lines) {
                lines.remove(line);
                list.add(line);
            }
            mergeCollectionsList.add(list);
        }
        return mergeCollectionsList;
    }

    public static Point getMeanPoint(List<Line> lines) {
        if (lines.size() == 0) return null;
        double x = 0;
        double y = 0;
        for(Line line : lines) {
            x += line.center().x;
            y += line.center().y;
        }
        return new Point(x/lines.size(), y/lines.size());
    }

    public static double getMeanAngle(List<Line> lines) {
        if(lines.size() == 0) return Double.NaN;
        double angle = 0;
        for (Line line : lines) {
            angle += MathFTC.normalizeAngle(line.angle()*180/Math.PI);
        }
        return angle/lines.size();
    }

    public static double stdDevX(List<Line> lines) {
        if (lines.size() == 0) return Double.NaN;
        List<Double> data = new ArrayList<Double>();
        for(Line line : lines) {
            data.add(line.center().x);
        }
        return MathFTC.getStdDev(data);
    }

    public static double stdDevY(List<Line> lines) {
        List<Double> data = new ArrayList<Double>();
        for(Line line : lines) {
            data.add(line.center().y);
        }
        return MathFTC.getStdDev(data);
    }

}