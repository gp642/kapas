package com.kapas.workorder.controller;

import com.kapas.KapasApplication;
import com.kapas.user.model.LoggedInUser;
import com.kapas.user.model.Login;
import com.kapas.user.service.UserService;
import com.kapas.workorder.model.Workflow;
import com.kapas.workorder.service.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WorkflowController {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowController.class);

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping(value = "/getWorkflowByWorkflowId")
    public ResponseEntity<Workflow> getWorkflowByWorkflowId(String workorder_id) throws Exception {
        logger.debug("Getting workorder_id : {}", workorder_id);
        Workflow workflow = workflowService.loadWorkflow(workorder_id);
        return new ResponseEntity<>(workflow, HttpStatus.OK);
    }
}
