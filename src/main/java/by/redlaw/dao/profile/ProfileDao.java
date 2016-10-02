package by.redlaw.dao.profile;

import by.redlaw.entity.Profile;

/**
 * Created by Redlaw on 10.09.2016.
 */

public interface ProfileDao {

    Profile createProfile(Profile profile);

    Profile getProfileById(Long profileId);

    void updateProfile(Profile profile);

    void deleteProfile(Profile profile);
}
