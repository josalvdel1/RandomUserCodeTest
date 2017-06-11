package com.josalvdel1.randomusercodetest.data.api.mapper.base;

import java.util.ArrayList;
import java.util.List;

public class ListMapper<M, P> implements Mapper<List<M>, List<P>> {

    private Mapper<M, P> mapper;

    public ListMapper(Mapper<M, P> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<P> modelToApi(List<M> models) {
        ArrayList<P> persistences = new ArrayList<>();
        if (models != null && models.size() > 0) {
            for (M model : models) {
                persistences.add(mapper.modelToApi(model));
            }
        }
        return persistences;
    }

    @Override
    public List<M> apiToModel(List<P> apiData) {
        ArrayList<M> models = new ArrayList<>();
        if (apiData != null && apiData.size() > 0) {
            for (P persistence : apiData) {
                models.add(mapper.apiToModel(persistence));
            }
        }
        return models;
    }
}
