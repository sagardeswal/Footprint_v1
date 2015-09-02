package com.sacri.footprint_v1;

import com.sacri.footprint_v1.Entities.UserDetails;

/**
 *
 */

interface GetUserCallback {
    public abstract void done(UserDetails returnedUserDetails);
}
