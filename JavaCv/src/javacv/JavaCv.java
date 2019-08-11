/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacv;
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
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
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
public class JavaCv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IplImage img=cvLoadImage("EV.jpg");
        IplImage hsvimg=cvCreateImage(cvGetSize(img),IPL_DEPTH_8U,3);
        IplImage grayimg=cvCreateImage(cvGetSize(img),IPL_DEPTH_8U,1);
        
        cvCvtColor(img,hsvimg,CV_BGR2HSV);
        cvCvtColor(img,grayimg,CV_BGR2GRAY);
        
        cvShowImage("Original",img);
        cvShowImage("HSV",hsvimg);
        cvShowImage("GRAY",grayimg);
        cvWaitKey();
        
        cvSaveImage("hsv.jpg",hsvimg);
        cvSaveImage("gray.jpg",grayimg);
    }
    
}
