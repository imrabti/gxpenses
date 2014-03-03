/**
 * Copyright 2012 Nuvola Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.nuvola.gxpenses.common.client.rest;

import com.google.common.base.Strings;
import com.leyton.taxevt.common.client.event.RequestEvent;
import com.leyton.taxevt.common.shared.dispatch.ValidatedResponse;

import java.util.Map;

public abstract class ValidatedAsyncCallbackImpl<T extends ValidatedResponse> extends AsyncCallbackImpl<T> {
    public ValidatedAsyncCallbackImpl() {
        RequestEvent.fire(this, RequestEvent.State.SENT, this);
    }

    @Override
    public void onSuccess(T response) {
        RequestEvent.fire(this, RequestEvent.State.RECEIVED, this);

        if (Strings.isNullOrEmpty(response.getExceptionMessage())) {
            if (response.getErrors().isEmpty()) {
                onReceive(response);
            } else {
                onValidationError(response.getErrors());
            }
        } else {
            Exception ex = new Exception(response.getExceptionMessage());
            onFailure(ex);
        }
    }

    public abstract void onValidationError(Map<String, String> errors);
}
