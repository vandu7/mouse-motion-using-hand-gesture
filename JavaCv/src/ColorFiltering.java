/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import static com.googlecode.javacv.cpp.opencv_core.CV_FILLED;
import static com.googlecode.javacv.cpp.opencv_core.CV_RGB;
import static com.googlecode.javacv.cpp.opencv_core.CV_WHOLE_SEQ;
import com.googlecode.javacv.cpp.opencv_core.CvContour;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCircle;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvDrawContours;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_ANY;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvNamedWindow;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_LINK_RUNS;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_RETR_LIST;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvContourArea;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvFindContours;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvGetCentralMoment;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvGetSpatialMoment;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvMoments;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
/**
 *
 * @author HOME
 */
public class ColorFiltering {
    public static int t;
    public static IplImage Filtering(IplImage img1,IplImage imghsv,IplImage imgbin,CvScalar minc,CvScalar maxc,CvSeq con1,CvSeq con2,CvMemStorage storage,CvMoments moments,int b,int g) throws AWTException
    {
        double areaMax=1000,areaC=0;
        double m10,m01,m_area=0;
        int posX=0,posY=0;
        cvInRangeS(imghsv,minc,maxc,imgbin);
        cvFindContours(imgbin,storage,con1,Loader.sizeof(CvContour.class),CV_RETR_LIST,CV_LINK_RUNS,cvPoint(0,0));
            con2=con1;
            Robot robot=new Robot();
            while(con1 != null && !con1.isNull())
            {
                areaC=cvContourArea(con1,CV_WHOLE_SEQ,1);
                if(areaC>areaMax)
                    areaMax=areaC;
                con1=con1.h_next();
            }
             
            while(con2 != null && !con2.isNull())
            {
                areaC=cvContourArea(con2,CV_WHOLE_SEQ,1);
                if(areaC<areaMax)
                {
                    cvDrawContours(imgbin,con2,CV_RGB(0,0,0),CV_RGB(0,0,0),0,CV_FILLED,8,cvPoint(0,0));
                }
                   con2=con2.h_next();
            }
            cvMoments(imgbin,moments,1);
            m10=cvGetSpatialMoment(moments,1,0);
            m01=cvGetSpatialMoment(moments,0,1);
            m_area=cvGetCentralMoment(moments,0,0);
            posX=(int)(m10/m_area);
            posY=(int)(m01/m_area);
            cvCircle(img1,cvPoint(posX,posY),5,cvScalar(0,255,0,0),9,0,0);
            if(b==1)
            {
                if(posX>0 && posY>0)
            {
                //robot.mouseMove((posX*4)-60, (posY*3)-60);
                robot.mouseMove((posX*4), (posY*3));
                 System.out.println("x="+posX+" y="+posY);
            }
            }
            if(g==1)
            {
                if(posX>0 && posY>0)
                {
                    robot.mousePress(InputEvent.BUTTON2_MASK);
                    
                    t++;
                }
                else if(t>0)
                {
                    robot.mouseRelease(InputEvent.BUTTON2_MASK);
                    t=0;
                }
            }
        return imgbin;
    }
    
}
