package com.lstfight.dao.dao;

import com.lstfight.dao.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Repository工厂 为具体DAO生产依赖
 *
 * @author 李尚庭
 */
public class RepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    public RepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new CustomJapRepositoryFactory(entityManager);
    }

    private static class CustomJapRepositoryFactory<T extends BaseEntity, I extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public CustomJapRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseDao.class;
        }


        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseDaoImpl<T, I>((Class <T> )information.getDomainType(), entityManager);
        }
    }


}
