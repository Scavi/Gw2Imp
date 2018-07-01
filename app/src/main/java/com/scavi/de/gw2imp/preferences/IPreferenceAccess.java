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
package com.scavi.de.gw2imp.preferences;

import android.app.Activity;
import android.content.Context;

import com.scavi.de.gw2imp.util.RoutingState;

public interface IPreferenceAccess {


    /**
     * Writes the api key synchronized into the preferences of the application
     *
     * @param context the context to global information about the application environment
     * @param apiKey  the api key to write
     */
    void writeApiKey(final Context context,
                     final String apiKey);


    /**
     * Reads the api key from the preferences. If the key doesn't exist "" will be returned
     *
     * @param context the context to global information about the application environment
     * @return the api key from the preferences or ""
     */
    String readApiKey(final Context context);


    /**
     * Writes the dictionary iteration key synchronized into the preferences of the application.
     * The dictionary iteration defines how many times the application iterated through all known
     * words.
     *
     * @param context             the context to global information about the application
     *                            environment
     * @param dictionaryIteration the api key to write
     */
    void writeDictionaryIteration(final Context context,
                                  final int dictionaryIteration);


    /**
     * Reads Writes the dictionary iteration key synchronized the preferences of the application.
     * The dictionary iteration defines how many times the
     * application iterated through all known words.
     *
     * @param context the context to global information about the application environment
     * @return the dictionary iteration or ß
     */
    int readDictionaryIteration(final Context context);


    /**
     * Writes the word index key synchronized into the preferences of the application.
     * The word index defines the current index while iterating through all known words to find
     * new words
     *
     * @param context             the context to global information about the application
     *                            environment
     * @param dictionaryIteration the api key to write
     */
    void writeWordIndex(final Context context,
                        final int dictionaryIteration);


    /**
     * Reads the word index key synchronized the preferences of the application.
     * The word index defines the current index while iterating through all known words to find
     * new words
     *
     * @param context the context to global information about the application environment
     * @return the dictionary iteration or 0
     */
    int readWordIndex(final Context context);


    /**
     * Writes the routing state synchronized into the preferences of the application
     *
     * @param context      the context to global information about the application environment
     * @param routingState the current routing state
     */
    void writeRoutingState(final Context context,
                           final RoutingState routingState);


    /**
     * Reads the current routing state from the preferences.
     *
     * @param context the context to global information about the application environment
     * @return the current routing state. In case, the value is not  set,
     * {@link RoutingState#ApiKeyRegistration} will be returned
     */
    RoutingState readRoutingState(final Context context);
}
