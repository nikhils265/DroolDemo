package com.amazon.drool.services;

import lombok.Getter;
import lombok.Setter;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class RuleEngine {

    private KieContainer kieContainer;

    public RuleEngine() {
        initializeEngine();
    }

    private void initializeEngine() {
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
            loadRule(kieFileSystem);
            KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
            kb.buildAll();
            KieModule kieModule = kb.getKieModule();
            this.kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        } catch (Exception e) {
            System.out.println("Exception in creating engine" + e.getMessage());
        }
    }

    private KieFileSystem loadRule(KieFileSystem kieFileSystem) {
        Resource resource = ResourceFactory.newClassPathResource("rules/rules.drl");
        kieFileSystem.write(resource);
        return kieFileSystem;
    }
}
