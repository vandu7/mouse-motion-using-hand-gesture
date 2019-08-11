/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

import com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvFlip;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_ANY;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvNamedWindow;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
/**
 *
 * @author HOME
 */
public class JavaCamFilt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IplImage img1,imghsv,imgbin;
        CvCapture cap=cvCreateCameraCapture(CV_CAP_ANY);
        CvScalar minc=cvScalar(160,50,75,0), maxc=cvScalar(180,255,255,0);
        
        cvNamedWindow("Cam",CV_WINDOW_AUTOSIZE);
        for(;;)
        {
            img1=cvQueryFrame(cap);
            cvFlip(img1,img1,1);
            imghsv=cvCreateImage(cvGetSize(img1),8,3);
            imgbin=cvCreateImage(cvGetSize(img1),8,1);
            cvCvtColor(img1,imghsv,CV_BGR2HSV);
            cvInRangeS(imghsv,minc,maxc,imgbin);
            
            cvShowImage("Cam",img1);
            //cvShowImage("hsv",imghsv);
            cvShowImage("filt",imghsv);
            char c=(char) cvWaitKey(30);
             if(c==27) break;
        }
    }
    
}
