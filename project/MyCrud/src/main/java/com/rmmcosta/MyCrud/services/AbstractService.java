package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.DomainObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractService {
    Map<Integer, DomainObject> domainObjectMap;

    public AbstractService() {
        bootstrapObjects();
    }

    List<DomainObject> listAllObjects() {
        return new ArrayList<>(domainObjectMap.values());
    }

    DomainObject getObjectById(int id) throws DomainObjectNotFound {
        if (domainObjectMap.containsKey(id)) {
            return domainObjectMap.get(id);
        } else {
            throw new DomainObjectNotFound();
        }
    }

    DomainObject createOrUpdateObject(DomainObject object) throws DomainObjectNotFound {
        if (object.getId() == 0){
            object.setId(getNextKey());
        }
        domainObjectMap.put(object.getId(), object);
        return object;
    }

    void deleteObject(int id) throws DomainObjectNotFound {
        if (domainObjectMap.containsKey(id)) {
            domainObjectMap.remove(id);
        } else {
            throw new DomainObjectNotFound();
        }
    }

    int getNextKey() {
        return domainObjectMap.size()+1;
    }

    int getNumObjects() {
        return domainObjectMap.size();
    }

    protected abstract void bootstrapObjects();
}
