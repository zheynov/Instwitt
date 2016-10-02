package by.redlaw.dao.profile.impl;

import by.redlaw.dao.profile.ProfileDao;
import by.redlaw.entity.Profile;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Locale;

/**
 *
 */

@Repository("profileDao")
@Transactional
public class ProfileDaoImpl implements ProfileDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    // Profile creation
    public Profile createProfile(Profile profile) {
        sessionFactory.getCurrentSession().save(profile);
        LOGGER.info(messageSource.getMessage("dao.profile.save", new Object[]{profile}, Locale.ENGLISH));
        return profile;
    }

    // Getting profile by ID
    public Profile getProfileById(Long profileId) {
        Profile profile = sessionFactory.getCurrentSession().get(Profile.class, profileId);
        LOGGER.info(messageSource.getMessage("dao.profile.getById", new Object[]{profileId}, Locale.ENGLISH));
        return profile;
    }

    //Profile update
    public void updateProfile(Profile profile) {
        sessionFactory.getCurrentSession().update(profile);
        LOGGER.info(messageSource.getMessage("dao.profile.update", new Object[]{profile}, Locale.ENGLISH));
    }

    // Profile destroy
    public void deleteProfile(Profile profile) {
        Profile mergedProfile = (Profile) sessionFactory.getCurrentSession().merge(profile);
        sessionFactory.getCurrentSession().delete(mergedProfile);
        LOGGER.info(messageSource.getMessage("dao.profile.delete", new Object[]{profile}, Locale.ENGLISH));
    }
}
