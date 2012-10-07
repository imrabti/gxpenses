package com.nuvola.gxpenses.client.rest;

import com.nuvola.gxpenses.shared.domaine.User;
import com.nuvola.gxpenses.shared.dto.Password;
import com.nuvola.gxpenses.shared.dto.ValidatedResponse;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

public interface SettingService extends RestService {
    @GET
    @Path("/setting")
    void getLoggedInUserSettings(MethodCallback<User> callback);

    @PUT
    @Path("/setting")
    void updateUserSettings(User user, MethodCallback<ValidatedResponse<User>> callback);

    @PUT
    @Path("/setting/password")
    void updateUserPassword(Password password, MethodCallback<ValidatedResponse<Password>> callback);

    @PUT
    @Path("/setting/tag")
    void updateUserTags(List<String> tags, MethodCallback<Void> callback);
}
