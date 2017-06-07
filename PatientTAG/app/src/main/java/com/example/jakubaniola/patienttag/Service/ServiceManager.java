package com.example.jakubaniola.patienttag.Service;

public class ServiceManager {

    private static ServiceRequest serviceRequest = new ServiceRequest();

    public static  ServiceRequest getInstance(){
        return serviceRequest;
    }

}
