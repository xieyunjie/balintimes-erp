package com.balintimes.erp.center.tuples;

/**
 * Created by Administrator on 2015/10/29.
 */
public class TupleResult<l,i> {
    public final l isSuccess;
    public final i objectMessage;

    public TupleResult(l isSuccess, i message)
    {
        this.isSuccess = isSuccess;
        this.objectMessage = message;

    }
}
