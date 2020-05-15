package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.bootstrap.BootstrapProducts;
import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.DomainObject;
import com.rmmcosta.MyCrud.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractService implements ProductService {
    public ProductServiceImpl() {
        super();
    }

    @Override
    protected void bootstrapObjects() {
        domainObjectMap = new HashMap<>(4);
        for (Product product : BootstrapProducts.getBootstrapProducts()) {
            domainObjectMap.put(product.getId(), product);
        }
    }

    @Override
    public List<DomainObject> listAllObjects() {
        return super.listAllObjects();
    }

    @Override
    public Product getObjectById(int id) throws DomainObjectNotFound {
        return (Product) super.getObjectById(id);
    }

    @Override
    public Product createOrUpdateObject(Product product) throws DomainObjectNotFound {
        return (Product) super.createOrUpdateObject(product);
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        super.deleteObject(id);
    }
}
