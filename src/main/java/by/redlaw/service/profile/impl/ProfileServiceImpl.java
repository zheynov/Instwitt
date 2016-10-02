package by.redlaw.service.profile.impl;

import by.redlaw.dao.profile.ProfileDao;
import by.redlaw.entity.Profile;
import by.redlaw.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Created by Redlaw on 10.09.2016.
 */

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDao profileDao;

    @Transactional
    public Profile getProfileById(Long id) {
        return profileDao.getProfileById(id);
    }

    @Transactional
    public void updateProfile(Profile profile) {
        profileDao.updateProfile(profile);
    }

    @Transactional
    public void deleteProfile(Profile profile) {
        profileDao.deleteProfile(profile);
    }
}
