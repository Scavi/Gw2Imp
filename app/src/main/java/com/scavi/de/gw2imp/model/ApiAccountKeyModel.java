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
package com.scavi.de.gw2imp.model;

import android.content.Context;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.response.account.Account;
import com.scavi.de.gw2imp.communication.util.HttpErrorMapping;
import com.scavi.de.gw2imp.data.preferences.IPreferenceAccess;

import java.net.HttpURLConnection;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import retrofit2.Callback;

@ParametersAreNonnullByDefault
public class ApiAccountKeyModel extends AbstractModel {
    private static final int API_KEY_LENGTH = 72;
    private static final int EXPECTED_TOKEN_COUNT = 9;
    private static final String API_KEY_TOKEN_DELIM = "-";
    private static final String API_KEY_FORMAT =
            "^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{20}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$";
    private final IAccountAccess mAccountAccess;
    private final IPreferenceAccess mPreferenceAccess;

    /**
     * Constructor
     *
     * @param context          the context to global information about the application environment
     * @param accountAccess    the server side access the account data
     * @param preferenceAccess access to the shared preferences of this application
     */
    @Inject
    public ApiAccountKeyModel(final Context context,
                              final IAccountAccess accountAccess,
                              final IPreferenceAccess preferenceAccess) {
        super(context);
        mAccountAccess = accountAccess;
        mPreferenceAccess = preferenceAccess;
    }


    /**
     * Verifies the api key format
     *
     * @param apiKey the api key to verify
     * @return <code>true</code> api key format is valid
     * <code>false</code> api key is not valid
     */
    public boolean isValidApiKeyFormat(final String apiKey) {
        Pattern apiKeyPattern = Pattern.compile(API_KEY_FORMAT, Pattern.CASE_INSENSITIVE);
        return apiKeyPattern.matcher(apiKey).matches();
    }

    /**
     * Stores the given api key in the settings of the android application
     *
     * @param apiKey the api key to store
     */
    public void storeApiKey(final String apiKey) {
        mPreferenceAccess.writeApiKey(mContext, apiKey);
    }


    /**
     * Tries to analyse the api key to give a better explanation about the error of the entered
     * api key.
     *
     * @param apiKey the api key to verify
     * @return the error within the api key.
     */
    public String analyseApiKeyError(final String apiKey) {
        // api key has an invalid length
        if (apiKey.length() != API_KEY_LENGTH) {
            return String.format(mContext.getString(R.string.api_account_key_error_token_length),
                    String.valueOf(apiKey.length()),
                    String.valueOf(API_KEY_LENGTH));
        }
        StringTokenizer tokenizer = new StringTokenizer(apiKey, API_KEY_TOKEN_DELIM);
        // api key has an invalid token count
        if (tokenizer.countTokens() != EXPECTED_TOKEN_COUNT) {
            return String.format(mContext.getString(R.string.api_account_key_error_token_count),
                    String.valueOf(tokenizer.countTokens()),
                    String.valueOf(EXPECTED_TOKEN_COUNT));
        }
        // the expected token length of the current position
        int[] expectedTokenLength = new int[]{8, 4, 4, 4, 20, 4, 4, 4, 12};
        int pos = 0;
        while (tokenizer.hasMoreTokens() && pos < expectedTokenLength.length) {
            String token = tokenizer.nextToken();
            // the current token doesn't have the expected length
            if (token.length() != expectedTokenLength[pos]) {
                return String.format(mContext.getString(R.string
                                .api_account_key_error_token_format),
                        String.valueOf(pos + 1), // for better readability not 0 based :-)
                        String.valueOf(token.length()),
                        String.valueOf(expectedTokenLength[pos]));
            }
            pos++;
        }
        return mContext.getString(R.string.api_account_key_error_unknown);
    }


    /**
     * @return the format info of the api key
     */
    public String getApiKeyFormatInfo() {
        return mContext.getString(R.string.api_account_key_error_format);
    }


    /**
     * @return the error about the connection
     */
    public String getConnectionExceptionError() {
        return mContext.getString(R.string.connection_exception);
    }

    /**
     * Determines the information to the http error code. Calling the GW2 API with an invalid api
     * key caused a {@link HttpURLConnection#HTTP_BAD_REQUEST}
     *
     * @return the information to the error code
     */
    public String determineAccountHttpError(final int httpError) {
        return httpError == HttpURLConnection.HTTP_BAD_REQUEST ?
                mContext.getString(R.string.api_account_key_error) :
                HttpErrorMapping.map(mContext, httpError);
    }

    /**
     * Determines the account data using the given account access.
     *
     * @param callback the callback to process the account result
     */
    public void determineAccount(final Callback<Account> callback) {
        mAccountAccess.getAccount(callback);
    }
}
