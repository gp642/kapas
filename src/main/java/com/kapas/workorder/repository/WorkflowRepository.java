package com.kapas.workorder.repository;

import com.kapas.user.entity.User;
import com.kapas.workorder.entity.Workflow;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowRepository extends CrudRepository<Workflow, Integer> {

    @Query("select wf from Workflow wf where wf.workflow_id = :workflow_id")
    Workflow getLatestWorkflowByWorkflowId(String workflow_id);

}