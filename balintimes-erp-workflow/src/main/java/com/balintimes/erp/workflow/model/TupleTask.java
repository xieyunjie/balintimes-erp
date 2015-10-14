package com.balintimes.erp.workflow.model;

/**
 * Created by AlexXie on 2015/10/12.
 */
public class TupleTask<tasks, total> {

    public final tasks taskList;
    public final total taskTotalCount;

    public TupleTask(tasks taskList, total totalCount)
    {
        this.taskList = taskList;
        this.taskTotalCount = totalCount;
    }
}
