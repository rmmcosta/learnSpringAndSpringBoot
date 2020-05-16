package com.rmmcosta.MyCrud.services.mapServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.DomainObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService {
    Map<Integer, DomainObject> domainObjectMap;

    public AbstractMapService() {
        bootstrapObjects();
    }

    public List<?> listAllObjects() {
        return new ArrayList<>(domainObjectMap.values());
    }

    public DomainObject getObjectById(int id) throws DomainObjectNotFound {
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

    public void deleteObject(int id) throws DomainObjectNotFound {
        if (domainObjectMap.containsKey(id)) {
            domainObjectMap.remove(id);
        } else {
            throw new DomainObjectNotFound();
        }
    }

    public int getNextKey() {
        return domainObjectMap.size()+1;
    }

    public int getCount() {
        return domainObjectMap.size();
    }

    protected abstract void bootstrapObjects();
}
