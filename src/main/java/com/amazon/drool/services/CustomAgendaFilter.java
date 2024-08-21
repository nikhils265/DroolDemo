package com.amazon.drool.services;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

import java.util.Set;

public class CustomAgendaFilter implements AgendaFilter {
    private final Set<String> allowedRuleName;

    public CustomAgendaFilter(Set<String> allowedRuleName) {
        this.allowedRuleName = allowedRuleName;
    }

    @Override
    public boolean accept(Match match) {
        return allowedRuleName.contains(match.getRule().getName());
    }
}
