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
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public interface IExecutorAccess {

    /**
     * @return Executor for the main thread access
     */
    Executor getUiThreadExecutor();


    /**
     * @return Executor for the background access
     */
    ListeningExecutorService getBackgroundThreadExecutor();

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
    <T> ListenableFuture<T> executeBackgroundTask(final Callable<T> callable,
                                                final FutureCallback<T> callback);
}
