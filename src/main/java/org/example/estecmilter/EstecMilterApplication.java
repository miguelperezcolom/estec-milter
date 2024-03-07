package org.example.estecmilter;

import org.nightcode.milter.Actions;
import org.nightcode.milter.MilterHandler;
import org.nightcode.milter.ProtocolSteps;
import org.nightcode.milter.net.MilterGatewayManager;
import org.nightcode.milter.net.ServerFactory;
import org.nightcode.milter.util.NetUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class EstecMilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstecMilterApplication.class, args);
    }

}
