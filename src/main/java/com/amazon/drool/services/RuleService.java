package com.amazon.drool.services;

import com.amazon.drool.dto.CardInput;
import com.amazon.drool.dto.CardOutput;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RuleService {
    @Autowired
    RuleEngine ruleEngine;

    public CardOutput getDiscount(CardInput cardInput) {
        log.info("Incoming request " + cardInput);
        long start = System.currentTimeMillis();
        CardOutput cardOutput = new CardOutput();
        KieSession kieSession = ruleEngine.getKieContainer().newKieSession();
        kieSession.setGlobal("cardOutput", cardOutput);
        kieSession.insert(cardInput);

        Set<String> allowedRules = new HashSet<>();
        allowedRules.add("HDFC Discount");
        allowedRules.add("ICICI Discount");
        allowedRules.add("SBI Discount");
        CustomAgendaFilter customAgendaFilter = new CustomAgendaFilter(allowedRules);



        kieSession.fireAllRules(customAgendaFilter);
        kieSession.dispose();
        long end = System.currentTimeMillis();
        log.info("Rule Execution time is " + (end-start));
        return cardOutput;
    }
}
