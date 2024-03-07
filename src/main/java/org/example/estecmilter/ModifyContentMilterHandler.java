package org.example.estecmilter;

import org.jetbrains.annotations.Nullable;
import org.nightcode.milter.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ModifyContentMilterHandler extends AbstractMilterHandler {

    private final Map<String, String> bodies = new HashMap();

    ModifyContentMilterHandler(Actions milterActions, ProtocolSteps milterProtocolSteps) {
        super(milterActions, milterProtocolSteps);
    }

    @Override
    public void body(MilterContext context, byte[] bodyChunk) throws MilterException {
        String id = context.id().toString();
        bodies.put(id, bodies.getOrDefault(id, "") + bodyChunk);
        super.body(context, bodyChunk);
    }

    @Override
    public void eom(MilterContext context, @Nullable byte[] bodyChunk) throws MilterException {
        String id = context.id().toString();
        bodies.put(id, (String)bodies.getOrDefault(id, "") + bodyChunk);
        String newBoody = (String)bodies.getOrDefault(id, "");
        bodies.remove(id);
        newBoody = newBoody.replaceAll("FEET PRAGUE 2020", "FEET PRAGUE 2022");
        newBoody = newBoody.replaceAll("420 233 107 508", "420 720 261 577");
        this.messageModificationService.replaceBody(context, newBoody.getBytes(StandardCharsets.UTF_8));
        super.eom(context, bodyChunk);
    }

    @Override
    public void quit(MilterContext milterContext) {

    }
}
