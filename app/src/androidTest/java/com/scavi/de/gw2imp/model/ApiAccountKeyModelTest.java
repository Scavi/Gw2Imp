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
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.data.preferences.IPreferenceAccess;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ApiAccountKeyModelTest {
    private Context mContext;


    @Before
    public void setUp() {
        mContext = InstrumentationRegistry.getTargetContext();
    }

    /**
     * Verifies the validation and the error message for an api key with illegal length
     */
    @Test
    public void testApiAccountKeyIllegalLength() {
        ApiAccountKeyModel apiAccountKeyModel = getModel();
        String apiKey = "cookies";
        Assert.assertFalse(apiAccountKeyModel.isValidApiKeyFormat(apiKey));
        String error = apiAccountKeyModel.analyseApiKeyError(apiKey);
        String expectedError = String.format(
                mContext.getString(R.string.api_account_key_error_token_length), "7", "72");
        Assert.assertEquals(expectedError, error);
    }


    /**
     * Verifies the validation and the error message of an api key that has an illegal token
     * count is correct.
     */
    @Test
    public void testApiKeyKeyErrorTokenCount() {
        ApiAccountKeyModel apiAccountKeyModel = getModel();
        String apiKey = "XXXXXXXXXXXXXXXXXXXXXXX-XXXXXXXXXXXXXXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX";
        Assert.assertFalse(apiAccountKeyModel.isValidApiKeyFormat(apiKey));
        String error = apiAccountKeyModel.analyseApiKeyError(apiKey);
        String expectedError = String.format(
                mContext.getString(R.string.api_account_key_error_token_count), "6", "9");
        Assert.assertEquals(expectedError, error);
    }


    /**
     * Verifies the validation and the error message of an api key that has an illegal token
     * count is correct.
     */
    @Test
    public void testApiKeyKeyErrorTokenFormat() {
        ApiAccountKeyModel apiAccountKeyModel = getModel();

        String apiKey = "XXXXXXXX-XXXXXXXX-XXXX-XXXXXXXXXXXX-XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX";
        Assert.assertFalse(apiAccountKeyModel.isValidApiKeyFormat(apiKey));
        String error = apiAccountKeyModel.analyseApiKeyError(apiKey);
        String expectedError = String.format(
                mContext.getString(R.string.api_account_key_error_token_format), "2", "8", "4");
        Assert.assertEquals(expectedError, error);
    }


    /**
     * @return a default mocked {@link ApiAccountKeyModel} for the test
     */
    private ApiAccountKeyModel getModel() {
        IPreferenceAccess preferenceAccessMock = Mockito.mock(IPreferenceAccess.class);
        IAccountAccess accountAccessMock = Mockito.mock(IAccountAccess.class);
        return new ApiAccountKeyModel(mContext, accountAccessMock, preferenceAccessMock);
    }
}
