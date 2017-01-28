package com.github.netelli.dao;

import com.github.netelli.dao.jdbc.BrandsDAOByJDBC;
import com.github.netelli.dao.jpa.BrandsDAOByJPA;
import com.github.netelli.model.DataSourceWrapper;

public class DaoFactory implements AutoCloseable {

    private final PersistenceType type;
    private DataSourceWrapper dataSourceWrapper;

    public DaoFactory(DataSourceWrapper dataSourceWrapper, PersistenceType type) {
        this.dataSourceWrapper = dataSourceWrapper;
        this.type = type;
    }

    public BaseDAO getBrandsDao() {
        if (type == PersistenceType.JDBC) {
            return new BrandsDAOByJDBC(dataSourceWrapper.getDataSource());
        }

        if (type == PersistenceType.JPA) {
            return new BrandsDAOByJPA(dataSourceWrapper.getEntityManger());
        }

        throw new RuntimeException("unknown persistence type provided=" + type);
    }

    @Override
    public void close() throws Exception {
        dataSourceWrapper.close();
    }

}
