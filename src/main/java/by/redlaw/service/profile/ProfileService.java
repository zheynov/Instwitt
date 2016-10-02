package by.redlaw.service.profile;

import by.redlaw.entity.Profile;

/**
 * Created by Redlaw on 10.09.2016.
 */


public interface ProfileService {
    Profile getProfileById(Long profile_id);
    void updateProfile(Profile profile);
    void deleteProfile(Profile profile);
}
