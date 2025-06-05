package edu.depaul.group_project.hot_properties.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AgentController {
//    private final AgentService agentService;
//    @Autowired
//    public AgentController(AgentService agentService) {
//        this.agentService = agentService;
//    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        return "agent.dashboard";
    }
}
