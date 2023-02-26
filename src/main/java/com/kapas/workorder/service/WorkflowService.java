package com.kapas.workorder.service;

import com.kapas.workorder.controller.WorkflowController;
import com.kapas.workorder.model.Workflow;
import com.kapas.workorder.repository.WorkflowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowService.class);

    private final WorkflowRepository workflowRepository;

    public WorkflowService(WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }

    public Workflow loadWorkflow(String workflow_id){

        com.kapas.workorder.entity.Workflow workflowEntity =
                workflowRepository.getLatestWorkflowByWorkflowId(workflow_id);
        logger.debug("Getting result workflowEntity : {} ", workflowEntity);

        return null;
    }

}
