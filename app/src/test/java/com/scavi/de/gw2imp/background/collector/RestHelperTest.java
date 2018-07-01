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
 *
 */
package com.scavi.de.gw2imp.background.collector;

import com.scavi.androidimp.util.RestHelper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

// TODO move to different project
public class RestHelperTest {

    /**
     * Tests the concatenation of in params to comma separated string
     */
    @Test
    public void testParamIntList() {
        String uriParam = RestHelper.intToGetParamList(1, 2, 3, 4, 5);
        assertThat(uriParam).isEqualTo("1,2,3,4,5");
    }


    /**
     * Tests the split up of int arrays into smaller strings
     */
    @Test
    public void testSplitParamIntList() {
        List<Integer> data = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int jump = 4;
        int pos = 0;
        for (int i = 0; i < 3; ++i) {
            String current = RestHelper.splitIntToGetParamList(data, pos, jump);
            switch (i) {
                case 0:
                    assertThat(current).isEqualTo("1,2,3,4");
                    break;
                case 1:
                    assertThat(current).isEqualTo("5,6,7,8");
                    break;
                case 2:
                    assertThat(current).isEqualTo("9,10");
                    break;
            }
            pos += jump;
        }
    }
}
