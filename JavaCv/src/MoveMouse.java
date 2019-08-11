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
import com.googlecode.javacv.cpp.opencv_core.CvArr;
import com.googlecode.javacv.cpp.opencv_core.CvContour;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCircle;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvDrawContours;
import static com.googlecode.javacv.cpp.opencv_core.cvFlip;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvOr;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_ANY;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_PROP_FRAME_WIDTH;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvNamedWindow;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSetCaptureProperty;
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
/**
 *
 * @author HOME
 */
public class MoveMouse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AWTException {
        // TODO code application logic here
        IplImage img1,imghsv,imgbinB,imgbinG,imgF;
        CvScalar Bmin=cvScalar(95,50,75,0), Bmax=cvScalar(145,255,255,0);
         CvScalar Gmin=cvScalar(160,50,75,0), Gmax=cvScalar(180,255,255,0);
         int w=640,h=480;
         CvArr mask;
         imghsv=cvCreateImage(cvSize(w,h),8,3);
         imgbinG=cvCreateImage(cvSize(w,h),8,1);
         imgbinB=cvCreateImage(cvSize(w,h),8,1);
         imgF=cvCreateImage(cvSize(w,h),8,1);
         CvSeq con1=new CvSeq(), con2=null;
         CvMemStorage storage=CvMemStorage.create();
         CvMoments moments=new CvMoments(Loader.sizeof(CvMoments.class));
         CvCapture cap=cvCreateCameraCapture(CV_CAP_ANY);
         cvSetCaptureProperty(cap,CV_CAP_PROP_FRAME_WIDTH,w);
         cvSetCaptureProperty(cap,CV_CAP_PROP_FRAME_HEIGHT,h);
         int i=1;
         while(i==1)
         {
             img1=cvQueryFrame(cap);
             cvFlip(img1,img1,1);
             cvCvtColor(img1,imghsv,CV_BGR2HSV);
            
             imgbinB=ColorFiltering.Filtering(img1, imghsv, imgbinB, Bmin, Bmax, con1, con2, storage, moments, 1, 0);
             //imgbinG=ColorFiltering.Filtering(img1, imghsv, imgbinG, Gmin, Gmax, con1, con2, storage, moments, 0, 1);
             cvOr(imgbinB,imgbinG,imgF,mask=null);
             cvShowImage("Original",img1);
             cvShowImage("Filtered",imgF);
             char c=(char)cvWaitKey(15);
             if(c==27) System.exit(0);
                     
         }
    }
    
}
