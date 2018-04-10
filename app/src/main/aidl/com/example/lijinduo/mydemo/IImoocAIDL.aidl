// IImoocAIDL.aidl
package com.example.lijinduo.mydemo;

// Declare any non-default types here with import statements

interface IImoocAIDL {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
     int add(int num1,int num2);

     int remove(String a,String b);

     void notic(double a,String b);
}
