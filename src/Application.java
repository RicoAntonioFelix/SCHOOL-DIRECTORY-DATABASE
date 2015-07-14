/*
 * Copyright (C) 2014-2015 Rico Antonio Felix
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Rico Antonio Felix <ricoantoniofelix@yahoo.com>
 */

package com.rico.felix.app;

/*
 * Local Dependencies
 */
import com.rico.felix.data.Directory;
import com.rico.felix.view.View;

/**
 * This class is used as the application's launcher.
 */
final class Application
{
    /**
     * This method launches the application, loads application data
     * and presents a view of the data to the user.
     */
    public static void main(String[] args)
    {
        Directory.loadDirectory();
        View.loadView();
    }
}
