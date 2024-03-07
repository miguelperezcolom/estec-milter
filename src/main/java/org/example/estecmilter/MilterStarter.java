package org.example.estecmilter;

import jakarta.annotation.PostConstruct;
import org.nightcode.milter.Actions;
import org.nightcode.milter.MilterHandler;
import org.nightcode.milter.ProtocolSteps;
import org.nightcode.milter.net.MilterGatewayManager;
import org.nightcode.milter.net.ServerFactory;
import org.nightcode.milter.util.NetUtils;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
public class MilterStarter {

    @PostConstruct
    public void init() {
        // indicates what changes you intend to do with messages
        Actions milterActions = Actions.builder()
                .addHeader()
                .build();

        // indicates which steps you want to skip
        ProtocolSteps milterProtocolSteps = ProtocolSteps.builder()
                .noHelo()
                .noData()
                .noBody()
                .build();

        // gateway address
        InetSocketAddress address = NetUtils.parseAddress(System.getProperty("jmilter.address", "0.0.0.0:4545"));
        ServerFactory<InetSocketAddress> serverFactory = ServerFactory.tcpIpFactory(address);

        // a simple milter handler that only adds header "X-Received"
        MilterHandler milterHandler = new ModifyContentMilterHandler(milterActions, milterProtocolSteps);

        MilterGatewayManager<InetSocketAddress> gatewayManager = new MilterGatewayManager<>(serverFactory, milterHandler);

        gatewayManager.bind();

    }

}
