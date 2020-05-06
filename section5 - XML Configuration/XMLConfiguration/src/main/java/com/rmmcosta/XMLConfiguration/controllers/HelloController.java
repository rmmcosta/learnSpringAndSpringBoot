package com.rmmcosta.XMLConfiguration.controllers;

import com.rmmcosta.XMLConfiguration.services.HelloService;

public class HelloController {
    private HelloService helloServicePt, helloServiceEn, helloServiceFr;
    public String getHello() {
        String hello = String.format("Pt: %s\nEn: %s\nFr: %s",
                helloServicePt.getGreeting(),
                helloServiceEn.getGreeting(),
                helloServiceFr.getGreeting());
        return hello;
    }

    public void setHelloServicePt(HelloService helloServicePt) {
        this.helloServicePt = helloServicePt;
    }

    public void setHelloServiceEn(HelloService helloServiceEn) {
        this.helloServiceEn = helloServiceEn;
    }

    public void setHelloServiceFr(HelloService helloServiceFr) {
        this.helloServiceFr = helloServiceFr;
    }
}
