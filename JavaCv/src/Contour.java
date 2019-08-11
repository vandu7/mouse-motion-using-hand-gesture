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
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvDrawContours;
import static com.googlecode.javacv.cpp.opencv_core.cvFlip;
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
import static com.googlecode.javacv.cpp.opencv_imgproc.cvContourArea;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvFindContours;
/**
 *
 * @author HOME
 */
public class Contour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IplImage img1,imghsv,imgbin;
        //CvScalar minc=cvScalar(95,50,75,0), maxc=cvScalar(145,255,255,0);
        CvScalar minc=cvScalar(0,45,0,0), maxc=cvScalar(20,255,255,0);
        
        CvSeq con1, con2;
        CvMemStorage storage=CvMemStorage.create();
        CvCapture cap=cvCreateCameraCapture(CV_CAP_ANY);
        imghsv=cvCreateImage(cvSize(640,480),8,3);
            imgbin=cvCreateImage(cvSize(640,480),8,1);
            //cvNamedWindow("cam",cvSize(640,480));
            cvNamedWindow("cam",CV_WINDOW_AUTOSIZE);
        double areaMax=1000,areaC=0;
        int i=1;
        while(i==1)
        {
            img1=cvQueryFrame(cap);
            cvFlip(img1,img1,1);
            cvCvtColor(img1,imghsv,CV_BGR2HSV);
            cvInRangeS(imghsv,minc,maxc,imgbin);
            con1=new CvSeq();
            areaMax=1000;
            cvFindContours(imgbin,storage,con1,Loader.sizeof(CvContour.class),CV_RETR_LIST,CV_LINK_RUNS,cvPoint(0,0));
            con2=con1;
            //System.out.println("1");
            while(con1 != null && !con1.isNull())
            {
                areaC=cvContourArea(con1,CV_WHOLE_SEQ,1);
                if(areaC>areaMax)
                    areaMax=areaC;
                con1=con1.h_next();
            }
             //System.out.println("2");
            while(con2 != null && !con2.isNull())
            {
                areaC=cvContourArea(con2,CV_WHOLE_SEQ,1);
                if(areaC<areaMax)
                {
                    cvDrawContours(imgbin,con2,CV_RGB(0,0,0),CV_RGB(0,0,0),0,CV_FILLED,8,cvPoint(0,0));
                }
                   con2=con2.h_next();
            }
             System.out.println("3");
            cvShowImage("cam",imghsv);
            cvShowImage("cf",imgbin);
            char c=(char) cvWaitKey(15);
             if(c==27) System.exit(0);
        }
        
    }
    
}
