/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scavi.de.gw2imp.communication.handler;

import com.scavi.de.gw2imp.model.AbstractModel;
import com.scavi.de.gw2imp.ui.view.IStatusView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class DefaultCallback<T> implements Callback<T> {
    private final AbstractModel mModel;
    private final IStatusView mStatusView;


    /**
     * Constructor
     *
     * @param model      the abstract model that gives access to error information
     * @param statusView the view to present the status information
     */
    public DefaultCallback(final IStatusView statusView,
                           final AbstractModel model) {
        mStatusView = statusView;
        mModel = model;
    }

    /**
     * @param call     the call
     * @param response the response
     */
    @Override
    public void onResponse(final Call<T> call,
                           final Response<T> response) {
        mStatusView.onHideProgress();
        processResponse(call, response);
    }


    /**
     * The method to process the call response
     *
     * @param call     the call
     * @param response the response
     */
    protected abstract void processResponse(final Call<T> call,
                                            final Response<T> response);


    /**
     * @param call the call
     * @param t    the exception
     */
    @Override
    public void onFailure(final Call<T> call,
                          final Throwable t) {
        mStatusView.onHideProgress();
        String connectionException = mModel.getConnectionExceptionError();
        mStatusView.showUserError(connectionException);
    }
}
