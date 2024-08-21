package com.amazon.drool.controller;

import com.amazon.drool.dto.CardInput;
import com.amazon.drool.dto.CardOutput;
import com.amazon.drool.services.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping
    public ResponseEntity<CardOutput> runRule(@RequestBody CardInput cardInput) {
        CardOutput cardOutput = ruleService.getDiscount(cardInput);
        return ResponseEntity.ok(cardOutput);
    }
}
