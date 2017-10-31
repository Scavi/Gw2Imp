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

import io.reactivex.functions.Consumer;

public class ExceptionConsumer implements Consumer<Throwable> {
    private final IStatusView mStatusView;
    private final AbstractModel mModel;

    /**
     * Constructor
     *
     * @param model      the abstract model that gives access to error information
     * @param statusView the view to present the status information
     */
    public ExceptionConsumer(final IStatusView statusView,
                             final AbstractModel model) {
        mStatusView = statusView;
        mModel = model;
    }


    /**
     * The method to process the exception
     *
     * @param t the exception
     */
    @Override
    public void accept(final Throwable t) throws Exception {
        mStatusView.onHideProgress();
        String connectionException = mModel.getConnectionExceptionError();
        mStatusView.showUserError(connectionException);
    }
}
