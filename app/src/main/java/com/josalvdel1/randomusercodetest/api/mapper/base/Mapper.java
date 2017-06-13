package com.josalvdel1.randomusercodetest.api.mapper.base;

public interface Mapper<M, P> {
    P modelToApi(M model);

    M apiToModel(P data);
}