/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacv2;
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
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_ANY;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateFileCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvNamedWindow;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import com.googlecode.javacv.cpp.opencv_objdetect.*;
import com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
/**
 *
 * @author HOME
 */
public class JavaCv2 {
     public static void main(String[] args) {
         
         CvCapture cap= cvCreateCameraCapture(CV_CAP_ANY); //cvCreateFileCapture("vid.mp4");
         IplImage frame;
         IplImage hsvimg;
         cvNamedWindow("Video",CV_WINDOW_AUTOSIZE);
         
         for(;;)
         {
             frame=cvQueryFrame(cap);
             hsvimg=cvCreateImage(cvGetSize(frame),IPL_DEPTH_8U,3);
             cvCvtColor(frame,hsvimg,CV_BGR2HSV);
             cvShowImage("Video",hsvimg);
             char c=(char) cvWaitKey(30);
             if(c==27) break;
         }
     }
     
     
    
}
