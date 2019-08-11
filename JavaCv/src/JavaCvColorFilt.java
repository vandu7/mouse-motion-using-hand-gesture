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
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
/**
 *
 * @author HOME
 */
public class JavaCvColorFilt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IplImage img1,imghsv,imgbin;
        img1=cvLoadImage("EV.jpg");
        imghsv=cvCreateImage(cvGetSize(img1),8,3);
        imgbin=cvCreateImage(cvGetSize(img1),8,1);
        cvCvtColor(img1,imghsv,CV_BGR2HSV);
        CvScalar minc=cvScalar(95,50,75,0), maxc=cvScalar(145,255,255,0);
        cvInRangeS(imghsv,minc,maxc,imgbin);
        cvShowImage("color",img1);
        cvShowImage("hsv",imgbin);
        cvSaveImage("filtblue.jpg",imgbin);
        cvWaitKey();
    }
    
}
