package com.ostdlabs.bank_account.db.dao.impl;

import com.ostdlabs.bank_account.db.dao.UserDao;
import com.ostdlabs.bank_account.db.exception.IncorrectDBData;
import com.ostdlabs.bank_account.db.pojo.organizations.User;
import com.ostdlabs.bank_account.db.qualifier.Organizations;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.List;

/** {@inheritDoc} */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<Integer, User> implements UserDao {
    /** Фабрика сессий для базы данных ORGANIZATIONS. */
    @Inject
    @Organizations
    private SessionFactory sessionFactory;//NOPMD

    /** {@inheritDoc} */
    protected SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    /** {@inheritDoc} */
    public User getByName(final String name) {
        List<User> users;
        try {
            users = createEntityCriteria().add(Restrictions.eq("userName", name)).list();//NOPMD
        }catch(ObjectNotFoundException onfe){
                throw new IncorrectDBData("Пользователь не найден в базе данных.", onfe);
        }
        if (CollectionUtils.isEmpty(users)) {
            throw new IncorrectDBData("Пользователь не найден в базе данных.", null);
        }
        return users.get(0);//NOPMD
    }
}
