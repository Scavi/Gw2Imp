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
package com.scavi.de.gw2imp.async;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorAccess implements IExecutorAccess {
    private final Executor mUiExecutor = new UiThreadExecutor();
    private final ListeningExecutorService mBackgroundExecutor = MoreExecutors.listeningDecorator
            (Executors.newFixedThreadPool(5));


    /**
     * @return Executor for the main thread access
     */
    @Override
    public Executor getUiThreadExecutor() {
        return mUiExecutor;
    }


    /**
     * @return Executor for the background access
     */
    @Override
    public ListeningExecutorService getBackgroundThreadExecutor() {
        return mBackgroundExecutor;
    }


    /**
     * Executes the given callable at some time in the future.  The command may execute in a new
     * thread, in a pooled thread, or in the calling thread, at the discretion of the {@code
     * Executor} implementation.
     *
     * @param callable the callable
     * @param callback the callback
     * @return the listenable future to the executed task
     * @throws NullPointerException if command is null
     */
    @Override
    public <T> ListenableFuture<T> executeBackgroundTask(final Callable<T> callable,
                                                       final FutureCallback<T> callback) {

        ListenableFuture<T> listenableFuture = mBackgroundExecutor.submit(callable);
        Futures.addCallback(listenableFuture, callback);
        return listenableFuture;
    }
}