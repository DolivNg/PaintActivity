package com.vassa.paintactivity.gsdk.vector;

public class VectorMath {
    /**One radian*/
    public static double ONE_RADIAN = 57.2958;
    /**Radian To Angle*/
    public static double angleToRadian(float angle){
        return angle/ONE_RADIAN;
    }

    public static double radianToAngle(float rad){
        return rad*ONE_RADIAN;
    }

    /**Scalar product*/
    public static float scalarValue2D(float x1,float y1,float x2,float y2){
        return x1*x2+y1*y2;
    }


    /*/**Scalar product
    public static float scalarValue3D(float x1,float y1,float x2,float y2){
        return x1*x2+y1*y2;
    }*/
    /**Vector module2d*/
    public static float moduleVector2D(PointF p)
    {
        return (float) Math.sqrt((double) (p.x*p.x+p.y*p.y) );
    }
    /**Vector module3d*/
    public static float moduleVectorD(PointF p)
    {
        return (float) Math.sqrt((double) (p.x*p.x+p.y*p.y+p.z*p.z));
    }
}
