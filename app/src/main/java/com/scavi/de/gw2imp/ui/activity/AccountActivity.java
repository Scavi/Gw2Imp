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
package com.scavi.de.gw2imp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerAccountComponent;
import com.scavi.de.gw2imp.dagger2.module.AccountModule;
import com.scavi.de.gw2imp.presenter.AccountPresenter;
import com.scavi.de.gw2imp.ui.view.IAccountView;

import javax.inject.Inject;

public class AccountActivity extends AppCompatActivity implements IAccountView {
    @Inject
    AccountPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        injectComponent(((IApplication) getApplication()).getComponent());
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    protected void injectComponent(final ApplicationComponent applicationComponent) {
        AccountModule module = new AccountModule(this);
        DaggerAccountComponent.builder()
                .applicationComponent(applicationComponent)
                .accountModule(module)
                .build()
                .inject(this);
    }
}
